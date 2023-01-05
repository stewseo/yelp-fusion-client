package io.github.stewseo.clients.yelpfusion.business.search;

import com.brein.domain.results.temporaldataparts.BreinLocationResult;
import io.github.stewseo.clients.yelpfusion._types.SearchBusiness;
import io.github.stewseo.clients.yelpfusion._types.SearchBusinessRegion;
import io.github.stewseo.clients.testcase.YelpFusionTestCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;

public class BusinessSearchTest extends YelpFusionTestCase {

    private static final Logger logger = LoggerFactory.getLogger(BusinessSearchTest.class);
    static int minNumChars = 5;

    @Test
    void businessSearchTest() throws Exception {

        String sort_by = "distance";
        String term = "restaurants";
        String categoryAlias = "pizza";
        int limit = 50;
        Integer offset = 0;
        int radius = 1610;

        getStreamOfTemporalData().forEach(breinTemporalDataResult -> {

            BreinLocationResult locationResult = breinTemporalDataResult.getLocation();

            Double latitude = locationResult.getLat();
            Double longitude = locationResult.getLon();
            String resultCity = locationResult.getCity();

            CompletableFuture<SearchBusinessResponse> searchBusinessResponse = yelpFusionServiceCtx.getYelpFusionAsyncClient().businesses().search(s -> s
                            .location(resultCity)
                            .coordinates(c -> c
                                    .latitude(latitude)
                                    .longitude(longitude))
                            .term(term)
                            .categories(cat -> cat
                                    .alias(categoryAlias))
                            .offset(offset)
                            .limit(limit)
                            .radius(radius)
                            .sort_by(sort_by),
                    SearchBusiness.class
            ).whenComplete((response, exception) -> {

                if (exception != null) {

                    logger.error("Search Business failed: " + exception);

                } else {

                    logger.info("Search Business successful: : ");

                }
            });

            try {

                testSearchBusinessResponse(searchBusinessResponse.get());

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });
    }

    // assert that Response fields total and region are of the expected type
    private void testSearchBusinessResponse(SearchBusinessResponse resp) {
        SearchBusinessRegion.Center center = resp.region().center();

        assertThat(resp.total()).isInstanceOf(Integer.class);

        Assertions.assertThat(center.latitude()).isInstanceOf(Double.class);
        Assertions.assertThat(center.longitude()).isInstanceOf(Double.class);

        resp.businesses().forEach(this::testSearchBusiness);
    }

    private void testSearchBusiness(SearchBusiness searchBusiness) {

        assertThat(searchBusiness.id().length()).isGreaterThanOrEqualTo(minNumChars);

        assertThat(searchBusiness.review_count()).isGreaterThanOrEqualTo(1);

        Assertions.assertThat(searchBusiness.location().city()).isNotNull();

    }
}
