package io.github.stewseo.yelp.fusion.client.yelpfusion.business.search;

import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import io.github.stewseo.yelp.fusion.client.YelpFusionTestCase;
import io.github.stewseo.yelp.fusion.client.connection.ElasticsearchConnection;
import io.github.stewseo.yelp.fusion.client.connection.YelpConnection;
import io.github.stewseo.yelp.fusion.client.json.JsonData;
import io.github.stewseo.yelp.fusion.client.yelpfusion.YelpFusionClient;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Reader;
import java.io.StringReader;

import static io.github.stewseo.yelp.fusion.client.connection.ElasticsearchConnection.elasticSearchService;
import static org.assertj.core.api.Assertions.assertThat;

public class BusinessSearchTest extends YelpFusionTestCase {

    private static YelpFusionClient yelpClient;
    private static final String indexNyc = "yelp-businesses-restaurants-nyc";

    // index results from BusinessSearch Endpoint
    @Test
    void businessSearchTest() throws Exception {

        YelpConnection.initYelpFusionClient();

        yelpClient = YelpConnection.getYelpClient();

        int radius = 1610;
        int limit = 50;
        String sort_by = "distance";
        String term = "restaurants";
        String category = "pizza";
        String neighborhood = "manhattan";
        Double latitude = 40.7580;
        Double longitude = -73.9855;
        Integer offset = 0;

        SearchBusinessResponse response = yelpClient.businesses().businessSearch(s -> s
                .location("manhattan")
                .coordinates(c -> c
                        .latitude(40.7580)
                        .longitude(-73.9855))
                .term( "restaurants")
                .categories(cat -> cat
                        .alias("pizza"))
                .offset(offset)
                .limit(50)
                .radius(1610)
                .sort_by("distance")
        );
        assertThat(response.toString().length()).isEqualTo(10027);
        assertThat(response.total()).isEqualTo(3300); // total results
        assertThat(response.businesses().size()).isEqualTo(50); // max results per page

        assertThat(response.region().latitude()).isEqualTo(40.7580); //meta field region.latitude
        assertThat(response.region().longitude()).isEqualTo(-73.9855); //meta field region.longitude

        // closest pizza restaurant to times square coordinates provided by: https://www.latlong.net/place/times-square-nyc-usa-7560.html
        SearchBusiness business = response.businesses().get(0);
        assertThat(business.alias()).isEqualTo("slice-of-ny-pizza-new-york");
        assertThat(business.review_count()).isEqualTo(17);
        assertThat(business.is_closed()).isEqualTo(false); // current time: 12:15 PM EST
        assertThat(business.id()).isEqualTo("vyjGW5fqWjxM7i-2cX_Zxw");

        int jsonStart = business.toString().indexOf("{");

        String withoutClassName = business.toString().substring(jsonStart);

        assertThat(withoutClassName.codePointAt(0)).isEqualTo("{".hashCode());


        Reader input = new StringReader(withoutClassName);

        IndexRequest<JsonData> request = IndexRequest.of(i -> i
                .index(indexNyc)
                .withJson(input)
        );

        IndexResponse indexResponse = elasticSearchService.getAsyncClient().index(request).get();

        assertThat(indexResponse.version()).isEqualTo(1);

        assertThat(indexResponse.result().name()).isEqualTo("Created");
    }

}
