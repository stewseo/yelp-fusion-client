package io.github.yelp.fusion.client.document;

import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import io.github.yelp.fusion.client.ElasticsearchRequestTestCase;
import io.github.yelp.fusion.client.json.JsonData;
import io.github.yelp.fusion.client.json.JsonpMapper;
import io.github.yelp.fusion.client.json.jackson.JacksonJsonpMapper;
import io.github.yelp.fusion.client.transport.YelpRestClientTransport;
import io.github.yelp.fusion.client.yelpfusion.BusinessDetailsResponse;
import io.github.yelp.fusion.client.yelpfusion.BusinessSearchResponse;
import io.github.yelp.fusion.client.yelpfusion.YelpFusionClient;
import io.github.yelp.fusion.client.yelpfusion.business.BusinessSearch;
import io.github.yelp.fusion.restclient.YelpFusionRestClient;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.setMaxStackTraceElementsDisplayed;

public class BusinessSearchDocumentTest extends ElasticsearchRequestTestCase {

    private final static Logger logger = LoggerFactory.getLogger(BusinessSearchDocumentTest.class);

    static HttpHost httpHost;
    static YelpFusionClient yelpClient;
    private static JsonpMapper mapper;

    @BeforeAll
    static void beforeAll() {
        String yelpFusionHost = "api.yelp.com";
        int port = 80;
        httpHost = new HttpHost(yelpFusionHost, port, "http");
        Header[] defaultHeaders = {new BasicHeader("Authorization", "Bearer " + System.getenv("YELP_API_KEY"))};

        YelpFusionRestClient restClient = YelpFusionRestClient.builder(
                        httpHost)
                .setMetaHeaderEnabled(false)
                .setDefaultHeaders(defaultHeaders)
                .build();

        mapper = new JacksonJsonpMapper();

        YelpRestClientTransport yelpTransport;
        try {
            yelpTransport = new YelpRestClientTransport(restClient, mapper);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        yelpClient = new YelpFusionClient(yelpTransport);

    }

    // index results from BusinessSearch Endpoint
    @Test
    void indexMappingTest() throws Exception {

        int radius = 1610;
        int limit = 50;
        String sort_by = "distance";
        String term = "restaurants";
        String category = "pizza";
        String neighborhood = "manhattan";
        Double latitude = 40.7580;
        Double longitude = -73.9855;
        Integer offset = 0;

        BusinessSearchResponse response = yelpClient.businessSearch(s -> s
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

        assertThat(response.total()).isEqualTo(239); // // max results per page total
        assertThat(response.businesses().size()).isEqualTo(50); // total results max results per page

        assertThat(response.region().latitude()).isEqualTo(40.7580); //meta field region.latitude
        assertThat(response.region().longitude()).isEqualTo(-73.9855); //meta field region.longitude

        // closest pizza restaurant to times square coordinates provided by: https://www.latlong.net/place/times-square-nyc-usa-7560.html
        BusinessSearch business = response.businesses().get(0);
        assertThat(business.alias()).isEqualTo("slice-of-ny-pizza-new-york");
        assertThat(business.review_count()).isEqualTo(17);
        assertThat(business.is_closed()).isEqualTo(false); // current time: 12:15 PM EST
        assertThat(business.id()).isEqualTo("vyjGW5fqWjxM7i-2cX_Zxw");

        int jsonStart = business.toString().indexOf("{");

        String withoutClassName = business.toString().substring(jsonStart);

        assertThat(withoutClassName.codePointAt(0)).isEqualTo("{".hashCode());


        Reader input = new StringReader(withoutClassName);

        initElasticsearchClient();

        IndexRequest<JsonData> request = IndexRequest.of(i -> i
                .index(indexNyc)
                .withJson(input)
        );

        IndexResponse indexResponse = elasticSearch.client().index(request);

        assertThat(indexResponse.version()).isEqualTo(1);
        logger.info("response id: " + indexResponse.id());
        logger.info("response index: " + indexResponse.index());

        assertThat(indexResponse.result().name()).isEqualTo("Created");
    }

}
