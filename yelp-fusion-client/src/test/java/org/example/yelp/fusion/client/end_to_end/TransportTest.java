package org.example.yelp.fusion.client.end_to_end;


import io.github.yelp.fusion.client.json.JsonpMapper;
import io.github.yelp.fusion.client.json.jackson.JacksonJsonpMapper;
import io.github.yelp.fusion.client.test_models.ModelTestCase;
import io.github.yelp.fusion.client.transport.YelpRestClientTransport;
import io.github.yelp.fusion.client.yelpfusion.BusinessDetailsResponse;
import io.github.yelp.fusion.client.yelpfusion.BusinessSearchResponse;
import io.github.yelp.fusion.client.yelpfusion.YelpFusionClient;
import io.github.yelp.fusion.client.yelpfusion.business.Business;
import io.github.yelp.fusion.client.yelpfusion.business.BusinessSearch;
import io.github.yelp.fusion.restclient.RestClient;
import io.github.yelp.fusion.restclient.RestClientBuilder;
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

        YelpFusionRestClient restClient = YelpFusionRestClient.builder(
                        httpHost)
                .setMetaHeaderEnabled(false)
                .setDefaultHeaders(defaultHeaders).build();


        mapper = new JacksonJsonpMapper();

        YelpRestClientTransport yelpTransport = null;
        try {
            yelpTransport = new YelpRestClientTransport(restClient, mapper);
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

        BusinessSearchResponse response = yelpClient.businessSearch(s -> s
                        .location("nyc")
                .coordinates(c-> c
                        .latitude(40.70544486444615)
                        .longitude(-73.99429321289062)
                ).term("restaurants")
                .price("2")
                .categories(c -> c
                        .alias("bagels"))
                        .limit(50)
                        .offset(0)

        );

        assertThat(response.total()).isEqualTo(65);
        assertThat(response.region().longitude()).isEqualTo(-73.99429321289062);
        assertThat(response.region().latitude()).isEqualTo(40.70544486444615);

        int maxResultsPerPage = 50;
        assertThat(response.businesses().size()).isEqualTo(maxResultsPerPage);

        List<String> businessIds = response.businesses().stream().map(BusinessSearch::id).distinct().toList();

        assertThat(businessIds.size()).isEqualTo(maxResultsPerPage);

    }


}
