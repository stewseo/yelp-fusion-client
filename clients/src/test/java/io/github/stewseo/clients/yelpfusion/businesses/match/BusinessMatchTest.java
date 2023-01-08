package io.github.stewseo.clients.yelpfusion.businesses.match;

import io.github.stewseo.clients.yelpfusion.testcases.FunctionalTestCase;
import io.github.stewseo.clients.yelpfusion.YelpFusionClient;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BusinessMatchTest extends FunctionalTestCase {

    private static final Logger logger = LoggerFactory.getLogger(BusinessMatchTest.class);

    private final BusinessMatchRequest businessMatchRequest = BusinessMatchRequest.of(a -> a
            .city("sf")
            .name("Brenda's+French+Soul+Food")
            .address1("625+polk+st")
            .state("ca")
            .country("US")
            .postal_code("94115")
            .match_threshold("none")
    );

    @Test
    public void businessMatchSendRequestSynchronizedTest() throws Exception {

        String apiKey = System.getenv("YELP_API_KEY");

        YelpFusionClient client = YelpFusionClient.createClient(apiKey);

        BusinessMatchResponse response = client.businesses().businessMatch(businessMatchRequest);

        BusinessMatch businessMatch = response.businesses().get(0);

        assertBusinessMatch(businessMatch);
    }

    @Test
    public void businessMatchSendRequestASyncTest() throws Exception {

        yelpFusionService.yelpFusionAsyncClient().businesses().businessMatch(businessMatchRequest
                ).whenComplete((response, exception) -> {
                    if (exception != null) {
                        logger.error("no businesses matched : ", exception);
                    } else {
                        assertBusinessMatch(response.businesses().get(0));
                        logger.info("business match found ");
                    }
                }).thenApply(BusinessMatchResponse::businesses)
                .get();
    }

    void assertBusinessMatch(BusinessMatch businessMatch) {

        assertThat(businessMatch.coordinates()).isNotNull();
        assertThat(businessMatch.location()).isNotNull();
        assertThat(businessMatch.name()).isEqualTo("Brenda's French Soul Food");
        assertThat(businessMatch.alias()).isEqualTo("brendas-french-soul-food-san-francisco-6");
        assertThat(businessMatch.phone()).isNotNull();
        assertThat(businessMatch.display()).isNull();

        List<BusinessMatch> businessMatches = List.of(businessMatch);

        final BusinessMatchResponse businessMatchResponse = BusinessMatchResponse.of(b -> b
                .businesses(businessMatches));

        assertThat(businessMatchResponse).isNotNull();
    }
    @Test
    public void testBusinessMatchEndpoint() {

        assertThat("v3/businesses/matches")
                .isEqualTo(BusinessMatchRequest._ENDPOINT.requestUrl(businessMatchRequest));

    }

}
