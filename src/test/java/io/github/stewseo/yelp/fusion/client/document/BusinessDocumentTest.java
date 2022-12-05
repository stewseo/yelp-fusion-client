package io.github.stewseo.yelp.fusion.client.document;

import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import io.github.stewseo.lowlevel.restclient.PrintUtils;
import io.github.stewseo.lowlevel.restclient.YelpFusionRestClient;
import io.github.stewseo.yelp.fusion.client.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.yelp.fusion.client.transport.restclient.YelpRestClientTransport;
import io.github.stewseo.yelp.fusion.client.ElasticsearchRequestTestCase;
import io.github.stewseo.yelp.fusion.client.json.JsonData;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business_details.BusinessDetailsResponse;
import io.github.stewseo.yelp.fusion.client.yelpfusion.YelpFusionClient;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.Business;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Reader;
import java.io.StringReader;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BusinessDocumentTest extends ElasticsearchRequestTestCase {

    private final static Logger logger = LoggerFactory.getLogger(BusinessDocumentTest.class);

    static YelpFusionClient yelpClient;


    @Test
    void indexBusinessDetailsTest() throws Exception {

        String apiKey = System.getenv("YELP_API_KEY");

        YelpFusionRestClient restClient = YelpFusionRestClient.builder(apiKey).build();

        YelpRestClientTransport yelpTransport = new YelpRestClientTransport(restClient, new JacksonJsonpMapper());

        yelpClient =  new YelpFusionClient(yelpTransport);

        String id = "wu3w6IlUct9OvYmYXDMGJA";

        BusinessDetailsResponse response = yelpClient.businessDetails(s -> s.id(id)
        );

        Assertions.assertThat(response.result().size()).isEqualTo(1); // business/{id} endpoint returns a single business with additional fields

        Business business = response.result().get(0);
        logger.debug(PrintUtils.debug("business details response: " + business));

        assertThat(business.id()).isEqualTo("wu3w6IlUct9OvYmYXDMGJA");
        assertThat(Objects.requireNonNull(business.hours()).toString()).isEqualTo("[Hours: {\"open\":[{\"is_overnight\":false,\"day\":0,\"start\":\"1100\",\"end\":\"2230\"},{\"is_overnight\":false,\"day\":1,\"start\":\"1100\",\"end\":\"2230\"},{\"is_overnight\":false,\"day\":2,\"start\":\"1100\",\"end\":\"2230\"},{\"is_overnight\":false,\"day\":3,\"start\":\"1100\",\"end\":\"2230\"},{\"is_overnight\":false,\"day\":4,\"start\":\"1100\",\"end\":\"2330\"},{\"is_overnight\":false,\"day\":5,\"start\":\"1100\",\"end\":\"2330\"},{\"is_overnight\":false,\"day\":6,\"start\":\"1100\",\"end\":\"2230\"}],\"hours_type\":\"REGULAR\",\"is_open_now\":true}]");
        assertThat(business.alias()).isEqualTo("huitlacoche-taqueria-restaurant-ridgewood-2");
        assertThat(business.review_count()).isEqualTo(7);
        assertThat(business.is_closed()).isEqualTo(false); // current time: 12:15 PM EST

        assertThat(business.categories().toString()).isEqualTo("[Categories: {\"alias\":\"mexican\",\"title\":\"Mexican\"}]");
        assertThat(Objects.requireNonNull(business.coordinates()).toString()).isEqualTo("Coordinates: {\"latitude\":40.701692,\"longitude\":-73.906044}");
        assertThat(business.rating()).isEqualTo(5);
        assertThat(Objects.requireNonNull(business.location()).toString()).isEqualTo("Location: {\"address1\":\"778 Seneca Ave\",\"city\":\"Ridgewood\",\"zip_code\":\"11385\",\"country\":\"US\",\"display_address\":[\"778 Seneca Ave\",\"Ridgewood, NY 11385\"]}");

        initElasticsearchClient();

        int jsonStart = business.toString().indexOf("{");

        String withoutClassName = business.toString().substring(jsonStart);

        assertThat(withoutClassName.codePointAt(0)).isEqualTo("{".hashCode());

        Reader input = new StringReader(withoutClassName);

        initElasticsearchClient();

        IndexRequest<JsonData> request = IndexRequest.of(i -> i
                .index(testIndex)
                .pipeline(timestampPipeline)
                .withJson(input)
        );

        IndexResponse indexResponse = elasticSearch.client().index(request);

        Assertions.assertThat(indexResponse.version()).isEqualTo(1);
        logger.info("response id: " + indexResponse.id());
        logger.info("response index: " + indexResponse.index());

        Assertions.assertThat(indexResponse.result().name()).isEqualTo("Created");

    }
}
