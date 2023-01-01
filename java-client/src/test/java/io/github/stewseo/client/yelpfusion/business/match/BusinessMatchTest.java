package io.github.stewseo.client.yelpfusion.business.match;

import io.github.stewseo.client.yelpfusion.YelpFusionClient;
import io.github.stewseo.client.yelpfusion.YelpFusionTestCase;
import io.github.stewseo.client.yelpfusion.business.Location;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class BusinessMatchTest extends YelpFusionTestCase {

    private static final Logger logger = LoggerFactory.getLogger(BusinessMatchTest.class);

    // http://api.yelp.com/v3/businesses/matches?city=sanfrancisco&name=brendas+french+soul+food&address1=625+polk+street&state=ca&country=US
    String businessMatchBrendas = "{\"id\":\"lJAGnYzku5zSaLnQ_T6_GQ\"," +
            "\"alias\":\"brendas-french-soul-food-san-francisco-6\"," +
            "\"name\":\"Brenda's French Soul Food\"," +
            "\"location\":{\"address1\":\"652 Polk St\",\"address2\":\"\",\"address3\":\"\",\"city\":\"San Francisco\",\"country\":\"US\",\"state\":\"CA\",\"zip_code\":\"94102\",\"display_address\":[\"652 Polk St\",\"San Francisco, CA 94102\"]},\"coordinates\":{\"latitude\":37.78291531984934,\"longitude\":-122.41889950001861},\"postal_code\":\"+14153458100\"}";

    @Test
    public void businessMatchSendRequestSynchronizedTest() throws Exception {

        String apiKey = System.getenv("YELP_API_KEY");

        YelpFusionClient client = YelpFusionClient.createClient(apiKey);

        BusinessMatchRequest request = BusinessMatchRequest.of(a -> a
                .city("sf")
                .name("Brenda's+French+Soul+Food")
                .address1("625+polk+st")
                .state("ca")
                .country("US")
                .match_threshold("none")
        );

        BusinessMatchResponse response = client.businesses().businessMatch(request);

        BusinessMatch businessMatch = response.businesses();

        assertBusinessMatch(businessMatch);
    }

    @Test
    public void businessMatchSendRequestASyncTest() throws Exception {

        Location location = yelpFusionServiceCtx.getYelpFusionAsyncClient().businesses().businessMatch(m -> m
                        .city("sf")
                        .name("Brenda's+French+Soul+Food")
                        .address1("625+polk+st")
                        .state("ca")
                        .country("US")
                        .match_threshold("none")
                ).whenComplete((response, exception) -> {
                    if (exception != null) {
                        logger.error("no businesses matched : ", exception);
                    } else {
                        assertBusinessMatch(response.businesses());
                        logger.info("business match found ");
                    }
                }).thenApply(t -> t.businesses())
                .get().
                location();
    }

    void assertBusinessMatch(BusinessMatch businessMatch) {

        assertThat(businessMatch.coordinates()).isNotNull();
        assertThat(businessMatch.location()).isNotNull();
        assertThat(businessMatch.name()).isEqualTo("Brenda's French Soul Food");
        assertThat(businessMatch.alias()).isEqualTo("brendas-french-soul-food-san-francisco-6");
    }

}
