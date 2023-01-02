package io.github.stewseo.client.yelpfusion.business.details;

import io.github.stewseo.client.yelpfusion.YelpFusionTestCase;
import io.github.stewseo.client.yelpfusion.business.Business;
import io.github.stewseo.client.yelpfusion.business.Hours;
import io.github.stewseo.client.yelpfusion.business.search.SearchBusiness;
import io.github.stewseo.client.yelpfusion.business.search.SearchBusinessResponse;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BusinessDetailsTest extends YelpFusionTestCase {

    private static final Logger logger = LoggerFactory.getLogger(BusinessDetailsTest.class);

    @Test
    void indexBusinessDetailsTest() throws Exception {

        CompletableFuture<List<SearchBusiness>> future = yelpFusionServiceCtx.getYelpFusionAsyncClient().businesses().search(s -> s
                                .location("sf")
                                .term("restaurants")
                                .sort_by("review_count")
                                .limit(3),
                        SearchBusiness.class)
                .whenComplete((response, exception) -> {
                            if (exception != null) {
                                logger.error("no businesses found: ", exception);
                            } else {
                                logger.info("number of businesses found: " + response.total());
                            }
                        }
                ).thenApply(SearchBusinessResponse::businesses);

        future.get().stream().map(SearchBusiness::id).forEach(this::businessDetails);

    }

    private void businessDetails(String id) {
        CompletableFuture<Business> future;
        try {
            future = yelpFusionServiceCtx.getYelpFusionAsyncClient().businesses().businessDetails(b -> b.id(id))
                    .whenComplete((response, exception) -> {

                        if (exception != null) {

                            logger.error("id: " + id + " did not return a business from the business details endpoint: ", exception);

                        } else {
                            logger.info("Business Details returned successfully : ");

                        }
                    }).thenApply(BusinessDetailsResponse::result);

            testBusinessDetails(future.get());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    // By review count desc:
    private void testBusinessDetails(Business business) {
        System.out.println(business);

        assertThat(business.id()).isNotNull();

        assertThat(business.review_count()).isGreaterThanOrEqualTo(4000);

        assertThat(business.rating()).isGreaterThanOrEqualTo(3.5);

        assertThat(business.categories()).isNotNull();

        Hours hours = Objects.requireNonNull(business.hours()).get(0);

        assertThat(hours.open().size()).isGreaterThanOrEqualTo(5);
    }
}