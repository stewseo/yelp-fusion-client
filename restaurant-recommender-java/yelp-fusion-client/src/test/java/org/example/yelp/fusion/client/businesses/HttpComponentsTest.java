package org.example.yelp.fusion.client.businesses;

import org.apache.http.*;
import org.example.elasticsearch.client.json.jackson.*;
import org.example.elasticsearch.client.transport.*;
import org.example.lowlevel.restclient.*;
import org.example.yelp.fusion.client.*;
import org.example.yelp.fusion.client.businesses.search.*;
import org.example.yelp.fusion.client.transport.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.net.*;

import static org.assertj.core.api.Assertions.assertThat;

public class HttpComponentsTest extends AbstractRequestTestCase {

    private static final RequestOptions COMMON_OPTIONS;
    
    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
        builder.addHeader("Authorization", "Bearer " + System.getenv("YELP_API_KEY"));
        builder.setHttpAsyncResponseConsumerFactory(
                new HttpAsyncResponseConsumerFactory
                        .HeapBufferedResponseConsumerFactory(30 * 1024 * 1024 * 1024));
        COMMON_OPTIONS = builder.build();
    }
    @Test
    void authenticationBearerTest() throws IOException, URISyntaxException {
        assertThat(yelpClient).isNotNull();
        HttpHost host = new HttpHost("api.yelp.com",8443,"https");
        RestClientBuilder restBuilder = RestClient.builder(host);
        RestClient client = restBuilder.build();

        int offset = 0;
        double longitude = -122.43;
        double latitude = 38.33;
        String location = "SF";

        JacksonJsonpMapper mapper = new JacksonJsonpMapper();
        YelpFusionTransport transport = new YelpRestTransport(client, mapper);
        YelpFusionClient yelpClient = new YelpFusionClient(transport);

        BusinessSearchResponse<Business> request = yelpClient.search(s-> s
                        .location(location)
                        .longitude(longitude)
                        .latitude(latitude)
                        .categories("restaurants")
                        .offset(offset)
                        .limit(50)
                        .terms("restaurants"),
                Business.class);

        
    }
}
