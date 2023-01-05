package io.github.stewseo.clients.yelpfusion.businesses.details;

import io.github.stewseo.clients.testcase.YelpFusionTestCase;
import io.github.stewseo.clients.yelpfusion.YelpFusionClient;
import io.github.stewseo.clients.yelpfusion._types.Hours;
import io.github.stewseo.clients.yelpfusion.businesses.match.BusinessMatchResponse;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessRequest;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessResponse;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BusinessDetailsTest extends YelpFusionTestCase {

    private static final Logger logger = LoggerFactory.getLogger(BusinessDetailsTest.class);

    private final SearchBusinessRequest searchBusinessRequest = SearchBusinessRequest.of(s -> s
            .location("sf")
            .term("restaurants")
            .sort_by("review_count")
            .limit(1));
    @Test
    void indexBusinessDetailsTest() throws Exception {

        CompletableFuture<List<SearchBusinessResult>> future = yelpFusionServiceCtx.getYelpFusionAsyncClient().businesses().search(searchBusinessRequest
                        , SearchBusinessResult.class)
                .whenComplete((response, exception) -> {
                            if (exception != null) {
                                logger.error("no businesses found: ", exception);
                            } else {
                                logger.info("number of businesses found: " + response.total());
                            }
                        }
                ).thenApply(SearchBusinessResponse::businesses);

        future.get().stream().map(SearchBusinessResult::id).forEach(this::testAsyncClient);

    }

    private void testAsyncClient(String id) {

        CompletableFuture<BusinessDetails> future;

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

    private YelpFusionClient yelpFusionClient;
    @Test
    public void syncBlockingClientTest() {

        try {

            yelpFusionClient = YelpFusionClient.createClient(System.getenv("YELP_API_KEY"));

            yelpFusionClient.businesses().businessSearch(searchBusinessRequest)
                    .businesses()
                    .stream()
                    .map(SearchBusinessResult::alias)
                    .forEach(this::testBusinessDetailsWithBlockingClient);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void testBusinessDetailsWithBlockingClient(String alias) {
        try {

            BusinessDetails business = yelpFusionClient.businesses().businessDetails(b -> b.alias(alias)).result();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    // By review count desc:
    private void testBusinessDetails(BusinessDetails business) {

        assertThat(business.toString()).isNotNull();

        assertThat(business.id()).isNotNull();
        assertThat(business.review_count()).isGreaterThanOrEqualTo(4000);
        assertThat(business.rating()).isGreaterThanOrEqualTo(3.5);
        assertThat(business.categories()).isNotNull();
        assertThat(business.hours()).isNotNull();
        assertThat(business.attributes()).isNotNull();
        assertThat(business.display_phone()).isNotNull();
        assertThat(business.coordinates()).isNotNull();
        assertThat(business.image_url()).isNotNull();
        Hours hours = Objects.requireNonNull(business.hours()).get(0);

        assertThat(hours.open().size()).isGreaterThanOrEqualTo(5);

        assertThat(business.is_claimed()).isNull();

        assertThat(business.is_closed()).isNotNull();

        assertThat(business.special_hours()).isNotNull();

        assertThat(business.transactions()).isNotNull();

        final BusinessDetailsResponse businessDetailsResponse = BusinessDetailsResponse.of(b -> b
                .result(business));

        Assertions.assertThat(businessDetailsResponse).isNotNull();
    }
}