package org.example.yelp.fusion.client.end_to_end;


import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.example.elasticsearch.client.json.JsonpMapper;

import org.example.elasticsearch.client.json.jackson.JacksonJsonpMapper;
import org.example.lowlevel.restclient.RestClient;
import org.example.lowlevel.restclient.RestClientBuilder;
import org.example.yelp.fusion.client.AbstractRequestTestCase;
import org.example.yelp.fusion.client.YelpFusionClient;
import org.example.yelp.fusion.client.business.BusinessDetailsResponse;
import org.example.yelp.fusion.client.business.BusinessSearchResponse;
import org.example.yelp.fusion.client.business.model.Business;
import org.example.yelp.fusion.client.model.ModelTestCase;
import org.example.yelp.fusion.client.transport.YelpRestTransport;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TransportTest extends AbstractRequestTestCase {

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

        RestClientBuilder builder = RestClient.builder(
                        httpHost)
                .setMetaHeaderEnabled(false)
                .setUserAgentEnable(false)
                .setDefaultHeaders(defaultHeaders);

        RestClient restClient = builder.build();

        mapper = new JacksonJsonpMapper();

        YelpRestTransport yelpTransport = null;
        try {
            yelpTransport = new YelpRestTransport(restClient, mapper);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        yelpClient = new YelpFusionClient(yelpTransport);

    }


    @Test
    void businessDetailsTest() throws Exception {

        String id = "wu3w6IlUct9OvYmYXDMGJA";

        BusinessDetailsResponse response = yelpClient.businessDetails(s -> s.id(id)
        );

        logger.debug("response.result(): " + response.result());
        Business business = response.result().get(0);
        assertThat(business.id()).isEqualTo( "wu3w6IlUct9OvYmYXDMGJA");

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

        BusinessSearchResponse<Business> businessSearchResponse = yelpClient.businessSearch(s -> s
                        .location(neighborhood)
                        .coordinates(coord -> coord
                                .geo_coordinates(geo -> geo
                                        .latitude(latitude)
                                        .longitude(longitude)))
                        .term(term)
                        .categories(c -> c
                                .alias(category))
                        .limit(limit)
                        .offset(offset)
                        .sort_by(sort_by)
                        .radius(radius)
                ,
                Business.class
        );

        assertThat(businessSearchResponse.total()).isEqualTo(38);
        assertThat(businessSearchResponse.region().longitude()).isEqualTo(-73.828461);
        assertThat(businessSearchResponse.region().latitude()).isEqualTo(40.713272);

        int maxResultsPerPage = 50;
        assertThat(businessSearchResponse.hits().size()).isEqualTo(businessSearchResponse.total());

        List<String> businessIds = businessSearchResponse.hits().stream().map(Business::id).distinct().toList();
        assertThat(businessIds.size()).isEqualTo(businessSearchResponse.total());

        Business business = businessSearchResponse.hits().get(0);

    }


}
