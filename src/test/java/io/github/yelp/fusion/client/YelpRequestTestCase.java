package io.github.yelp.fusion.client;

import io.github.lowlevel.restclient.YelpFusionRestClient;
import io.github.yelp.fusion.client.json.JsonpMapper;
import io.github.yelp.fusion.client.json.jackson.JacksonJsonpMapper;
import io.github.yelp.fusion.client.transport.restclient.YelpRestClientTransport;
import io.github.yelp.fusion.client.yelpfusion.YelpFusionClient;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;

import java.io.IOException;

public class YelpRequestTestCase {

    static HttpHost httpHost;
    static YelpFusionClient yelpClient;
    private static JsonpMapper mapper;

    public static YelpFusionClient getYelpClient(){
        return yelpClient;
    }

    public static void initYelpFusionClient() {
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
}
