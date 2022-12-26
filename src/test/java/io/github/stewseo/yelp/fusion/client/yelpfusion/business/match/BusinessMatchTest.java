package io.github.stewseo.yelp.fusion.client.yelpfusion.business.match;

import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.stewseo.yelp.fusion.client.YelpFusionTestCase;
import io.github.stewseo.yelp.fusion.client.connection.YelpConnection;
import io.github.stewseo.yelp.fusion.client.yelpfusion.YelpFusionClient;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BusinessMatchTest extends YelpFusionTestCase {


    // http://api.yelp.com/v3/businesses/matches?city=sanfrancisco&name=brendas+french+soul+food&address1=625+polk+street&state=ca&country=US
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

        BusinessMatch businessMatch = response.businesses().get(0);

        assertThat(businessMatch.coordinates().toString()).isEqualTo("Coordinates: {\"latitude\":37.7829016035273,\"longitude\":-122.419043442957}");
        assertThat(businessMatch.location().toString()).isEqualTo("Location: {\"address1\":\"652 Polk St\",\"address2\":\"\",\"address3\":\"\",\"city\":\"San Francisco\",\"zip_code\":\"94102\",\"country\":\"US\",\"display_address\":[\"652 Polk St\",\"San Francisco, CA 94102\"]}");

        assertThat(businessMatch.name()).isEqualTo("Brenda's French Soul Food");
        assertThat(businessMatch.alias()).isEqualTo("brendas-french-soul-food-san-francisco-6");

    }

    @Test
    public void businessMatchSendRequestASyncTest() throws Exception {

        YelpConnection.initYelpFusionClient();

        YelpFusionClient client = YelpConnection.getYelpClient();

        BusinessMatchResponse response  = client.businesses().businessMatch(s -> s
                .city("sf")
                .name("Brenda's+French+Soul+Food")
                .address1("625+polk+st")
                .state("ca")
                .country("US")
                .match_threshold("none")
        );

        new SearchRequest.Builder().storedFields("_none_");

        SearchResponse<ObjectNode> respon = elasticsearchService.getAsyncClient().search(s -> s
                .storedFields("_none_")
                        .index("index"),
                ObjectNode.class)
                .get();

        assertThat(response.businesses().size()).isEqualTo(1);
        BusinessMatch businessMatch = response.businesses().get(0);

        assertThat(businessMatch.coordinates().toString()).isEqualTo("Coordinates: {\"latitude\":37.7829016035273,\"longitude\":-122.419043442957}");
        assertThat(businessMatch.location().toString()).isEqualTo("Location: {\"address1\":\"652 Polk St\",\"address2\":\"\",\"address3\":\"\",\"city\":\"San Francisco\",\"zip_code\":\"94102\",\"country\":\"US\",\"display_address\":[\"652 Polk St\",\"San Francisco, CA 94102\"]}");

        assertThat(businessMatch.name()).isEqualTo("Brenda's French Soul Food");
        assertThat(businessMatch.alias()).isEqualTo("brendas-french-soul-food-san-francisco-6");

    }

}
