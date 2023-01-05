package io.github.stewseo.clients.yelpfusion.business.match;

import io.github.stewseo.clients.testcase.YelpFusionTestCase;
import io.github.stewseo.clients.yelpfusion.YelpFusionClient;
import io.github.stewseo.clients.yelpfusion.businesses.match.BusinessMatch;
import io.github.stewseo.clients.yelpfusion.businesses.match.BusinessMatchRequest;
import io.github.stewseo.clients.yelpfusion.businesses.match.BusinessMatchResponse;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class BusinessMatchTest extends YelpFusionTestCase {

    private static final Logger logger = LoggerFactory.getLogger(BusinessMatchTest.class);

    private final BusinessMatchRequest businessMatchRequest = BusinessMatchRequest.of(a -> a
            .city("sf")
            .name("Brenda's+French+Soul+Food")
            .address1("625+polk+st")
            .state("ca")
            .country("US")
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

        yelpFusionServiceCtx.getYelpFusionAsyncClient().businesses().businessMatch(businessMatchRequest
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
    }
}
