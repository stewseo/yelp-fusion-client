package io.github.stewseo.yelp.fusion.client;

import io.github.stewseo.yelp.fusion.lowlevel.restclient.RestClient;
import io.github.stewseo.yelp.fusion.client.json.JsonpMapper;
import io.github.stewseo.yelp.fusion.client.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.yelp.fusion.client.transport.restclient.YelpRestClientTransport;
import io.github.stewseo.yelp.fusion.client.yelpfusion.YelpFusionAsyncClient;
import io.github.stewseo.yelp.fusion.client.yelpfusion.YelpFusionClient;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;

import java.io.IOException;

public class YelpConnection {

    static HttpHost httpHost;
    static YelpFusionClient yelpClient;

    static YelpFusionAsyncClient yelpFusionAsyncClient;
    private static JsonpMapper mapper;
    public static void initYelpFusionAsyncClient() {
        String yelpFusionHost = "api.yelp.com";
        int port = 443;
        HttpHost httpHost = new HttpHost(yelpFusionHost, port, "https");

        Header[] defaultHeaders = {new BasicHeader("Authorization", "Bearer " + System.getenv("YELP_API_KEY"))};

        RestClient restClient = RestClient.builder(httpHost)
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
        yelpFusionAsyncClient = new YelpFusionAsyncClient(yelpTransport);
    }
    public static YelpFusionAsyncClient getYelpFusionAsyncClient(){
        return yelpFusionAsyncClient;
    }

    public static YelpFusionClient getYelpClient(){
        return yelpClient;
    }

    public static void initYelpFusionClient() throws IOException {

        final String yelpFusionHost = "api.yelp.com";
        final int port = 443;
        final String scheme = "https";
        httpHost = new HttpHost(yelpFusionHost, port, scheme);

        String apiKey = System.getenv("YELP_API_KEY");
        Header[] defaultHeaders = {new BasicHeader("Authorization", "Bearer " + apiKey)};

        RestClient restClient = RestClient.builder(httpHost)
                .setDefaultHeaders(defaultHeaders)
                .build();

        JacksonJsonpMapper mapper = new JacksonJsonpMapper();

        YelpRestClientTransport yelpTransport = new YelpRestClientTransport(restClient, mapper);

        yelpClient = new YelpFusionClient(yelpTransport);

    }
}
