package io.github.yelp.fusion.client.end_to_end;


import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import io.github.yelp.fusion.client.ElasticsearchRequestTestCase;
import io.github.yelp.fusion.client.json.JsonpMapper;
import io.github.yelp.fusion.client.json.jackson.JacksonJsonpMapper;
import io.github.yelp.fusion.client.transport.YelpRestClientTransport;
import io.github.yelp.fusion.client.yelpfusion.BusinessDetailsResponse;
import io.github.yelp.fusion.client.yelpfusion.BusinessSearchResponse;
import io.github.yelp.fusion.client.yelpfusion.YelpFusionClient;
import io.github.yelp.fusion.client.yelpfusion.business.Business;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TransportTest extends ElasticsearchRequestTestCase {

    static HttpHost httpHost;
    static YelpFusionClient yelpClient;
    private final static Logger logger = LoggerFactory.getLogger(TransportTest.class);
    private static JsonpMapper mapper;

    // create YelpSearchResponse object
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

    //09:00:07.562 [Test worker] INFO  i.g.elasticsearch.client.util.ObjectBuilderBase - list: [Business:
    String result = "" +
            "{\"id\":\"wu3w6IlUct9OvYmYXDMGJA\"," +
            "\"alias\":\"huitlacoche-taqueria-restaurant-ridgewood-2\"," +
            "\"name\":\"Huitlacoche Taqueria Restaurant\"," +
            "\"image_url\":\"https://s3-media3.fl.yelpcdn.com/bphoto/xi-xz-sPIEjQ5xCX4fPZ_Q/o.jpg\"," +
            "\"is_claimed\":true," +
            "\"url\":\"https://www.yelp.com/biz/huitlacoche-taqueria-restaurant-ridgewood-2?adjust_creative=ccj3y1UCH-4gsdWSMdEDOw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_lookup&utm_source=ccj3y1UCH-4gsdWSMdEDOw\"," +
            "\"phone\":\"+13479873581\"," +
            "\"display_phone\":\"(347) 987-3581\"," +
            "\"review_count\":7," +
            "\"hours\":" +
            "   [" +
            "   {" +
            "       \"open\":[{\"is_overnight\":false,\"day\":0,\"start\":\"1100\",\"end\":\"2230\"},{\"is_overnight\":false,\"day\":1,\"start\":\"1100\",\"end\":\"2230\"},{\"is_overnight\":false,\"day\":2,\"start\":\"1100\",\"end\":\"2230\"},{\"is_overnight\":false,\"day\":3,\"start\":\"1100\",\"end\":\"2230\"},{\"is_overnight\":false,\"day\":4,\"start\":\"1100\",\"end\":\"2330\"},{\"is_overnight\":false,\"day\":5,\"start\":\"1100\",\"end\":\"2330\"},{\"is_overnight\":false,\"day\":6,\"start\":\"1100\",\"end\":\"2230\"}],\"hours_type\":\"REGULAR\",\"is_open_now\":true}]," +
            "\"transactions\":[\"pickup\",\"delivery\"],\"rating\":5}]";
    @Test
    void businessDetailsTest() throws Exception {
        initElasticsearchClient();
        String id = "wu3w6IlUct9OvYmYXDMGJA";

        BusinessDetailsResponse businessDetailsResponse = yelpClient.businessDetails(s -> s.id(id)
        );

        Business business = businessDetailsResponse.result().get(0);

        logger.info("is_claimed " + business.is_claimed());
        logger.info("is_closed " + business.is_closed());
        logger.info("categories " + business.categories());

        logger.info("id " + business.id());

        logger.info("alias " + business.alias());
        logger.info("coordinates " + business.coordinates());
        logger.info("name " + business.name());
//        logger.info("price " + business.price());
        logger.info("location " + business.location());
        logger.info("review_count " + business.review_count());
        logger.info("rating " + business.rating());
//        logger.info("display_phone " + business.display_phone());
        logger.info("image_url " + business.image_url());
        logger.info("hours " + business.hours());

//        logger.info("phone " + business.phone());
//        logger.info("photos " + business.photos());
//        logger.info("url " + business.url());
//        logger.info("attributes " + business.attributes());
//        logger.info("special_hours " + business.special_hours());
//        logger.info("transactions " + business.transactions());
//        assertThat(business.id()).isEqualTo( "wu3w6IlUct9OvYmYXDMGJA");

        IndexRequest<Business> request = IndexRequest.of(i -> i
                .index(indexNyc)
                .id(indexNyc + "-" + business.id())
                .document(business));

        IndexResponse resp = elasticSearch.client().index(request);

//        logger.info(resp.result().name());
    }

    @Test
    void businessSearchTest() throws Exception {

        String id = "wu3w6IlUct9OvYmYXDMGJA";

        int radius = 6000;
        int limit = 50;
        String sort_by = "distance";
        String term = "restaurants";
        String category = "bagels";
        String neighborhood = "bronx";
        Double latitude = 40.713272;
        Double longitude = -73.828461;
        Integer offset = 0;

        BusinessSearchResponse businessSearchResponse = yelpClient.businessSearch(s -> s
                        .location(neighborhood)
                        .coordinates(c -> c
                                        .latitude(latitude)
                                        .longitude(longitude))
                        .term(term)
                        .categories(cat -> cat
                                .alias(category))
                        .limit(limit)
                        .offset(offset)
                        .sort_by(sort_by)
                        .radius(radius)
        );

        assertThat(businessSearchResponse.total()).isEqualTo(38);
        assertThat(businessSearchResponse.region().longitude()).isEqualTo(-73.828461);
        assertThat(businessSearchResponse.region().latitude()).isEqualTo(40.713272);

        int maxResultsPerPage = 50;
        assertThat(businessSearchResponse.businesses().size()).isEqualTo(businessSearchResponse.total());

        List<String> businessIds = businessSearchResponse.businesses().stream().map(BusinessSearch::id).distinct().toList();
        assertThat(businessIds.size()).isEqualTo(businessSearchResponse.total());

        BusinessSearch business = businessSearchResponse.businesses().get(0);
        logger.info("Creating document from: " + business);

        initElasticsearchClient();

        IndexRequest<BusinessSearch> request = IndexRequest.of(i -> i
                .index(indexNyc)
                .id(indexNyc + "-" + business.id())
                .document(business)
        );

        IndexResponse resp = elasticSearch.client().index(request);

        logger.info("response id: " + resp.id());
        logger.info("response index: " + resp.index());
        logger.info("response result: " + resp.result().name());

    }


}
