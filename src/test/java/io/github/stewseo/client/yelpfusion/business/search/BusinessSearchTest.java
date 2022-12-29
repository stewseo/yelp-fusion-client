package io.github.stewseo.client.yelpfusion.business.search;

import com.brein.domain.results.temporaldataparts.BreinLocationResult;
import io.github.stewseo.client.yelpfusion.YelpFusionTestCase;
import io.github.stewseo.client.yelpfusion.client.YelpFusionConnection;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;

public class BusinessSearchTest extends YelpFusionTestCase {

    private static final Logger logger = LoggerFactory.getLogger(BusinessSearchTest.class);

    private static final String indexNyc = "yelp-businesses-restaurants-nyc";


    // index results from BusinessSearch Endpoint
    @Test
    void businessSearchTest() throws Exception {

        yelpFusionAsyncClient = YelpFusionConnection.createOrGetYelpFusionAsyncClient();

        String sort_by = "distance";
        String term = "restaurants";
        String categoryAlias = "pizza";

        getStreamOfTemporalData().forEach(breinTemporalDataResult -> {

            // only works for cities in California, use Brein TemporalData API for non-CA locations
            BreinLocationResult locationResult = breinTemporalDataResult.getLocation();

            Double latitude = locationResult.getLat();
            Double longitude = locationResult.getLon();
            String resultCity = locationResult.getCity();
            System.out.println(resultCity);
            int limit = 50;
            Integer offset = 0;
            int radius = 1610;

            CompletableFuture<SearchBusinessResponse> searchBusinessResponse = yelpFusionAsyncClient.businesses().search(s -> s
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

            int numberOfDependents = searchBusinessResponse.getNumberOfDependents();

            try {

                testSearchBusinessResponse(searchBusinessResponse.get());

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });
    }

    // assert that Response fields total and region are of the expected type
    private void testSearchBusinessResponse(SearchBusinessResponse resp) {

        assertThat(resp.total()).isInstanceOf(Integer.class);
        assertThat(resp.region().latitude()).isInstanceOf(Double.class);
        assertThat(resp.region().longitude()).isInstanceOf(Double.class);

        resp.businesses().forEach(this::testSearchBusiness);
    }

    static int minNumChars = 5;
    private void testSearchBusiness(SearchBusiness searchBusiness) {

        assertThat(searchBusiness.id().length()).isGreaterThanOrEqualTo(minNumChars);

        assertThat(searchBusiness.review_count()).isGreaterThanOrEqualTo(1);

        assertThat(searchBusiness.location().city()).isNotNull();

    }
}
