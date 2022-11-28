package org.example.yelp.fusion.client.end_to_end;


import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.example.elasticsearch.client.json.JsonpMapper;

import org.example.elasticsearch.client.json.jackson.JacksonJsonpMapper;
import org.example.lowlevel.restclient.RestClient;
import org.example.lowlevel.restclient.RestClientBuilder;
import org.example.yelp.fusion.client.YelpFusionClient;
import org.example.yelp.fusion.client.business.BusinessDetailsResponse_;
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

public class TransportTest extends ModelTestCase {

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

        BusinessDetailsResponse_ response = yelpClient.businessDetails(s -> s.id(id)
        );

        logger.debug("response.result(): " + response.result());
        Business business = response.result().get(0);
        assertThat(business.id()).isEqualTo( "wu3w6IlUct9OvYmYXDMGJA");

    }

    @Test
    void businessSearchTest() throws Exception {

        String id = "wu3w6IlUct9OvYmYXDMGJA";

        BusinessSearchResponse<Business> response = yelpClient.businessSearch(s -> s
                        .location("nyc")
                        .latitude(40.70544486444615)
                        .longitude(-73.99429321289062)
                        .term("restaurants")
                        .price("2")
                        .categories(c -> c
                                .alias("bagels"))
                        .limit(50)
                        .offset(0)
                ,
                Business.class
        );

        assertThat(response.total()).isEqualTo(65);
        assertThat(response.region().longitude()).isEqualTo(-73.99429321289062);
        assertThat(response.region().latitude()).isEqualTo(40.70544486444615);

        int maxResultsPerPage = 50;
        assertThat(response.hits().size()).isEqualTo(maxResultsPerPage);

        List<String> businessIds = response.hits().stream().map(Business::id).distinct().toList();

        assertThat(businessIds.size()).isEqualTo(maxResultsPerPage);

    }


}
