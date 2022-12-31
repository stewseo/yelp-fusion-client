package io.github.stewseo.client.connection;

import io.github.stewseo.client.transport.YelpFusionTransport;
import io.github.stewseo.client.yelpfusion.YelpFusionClient;
import io.github.stewseo.lowlevel.restclient.RestClient;
import io.github.stewseo.client.json.JsonpMapper;
import io.github.stewseo.client.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.client.transport.restclient.YelpRestClientTransport;
import io.github.stewseo.client.yelpfusion.YelpFusionAsyncClient;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;

public class YelpFusionConnection {

    private static YelpFusionTransport yelpTransport;

    @BeforeAll
    static void beforeAll() {

        yelpTransport = createTransport();
    }

    static YelpFusionClient yelpFusionClient;

    static YelpFusionAsyncClient yelpFusionAsyncClient;


    private static JsonpMapper mapper;

    public static void initYelpFusionAsyncClient() {
        // only one client allowed per instance
//        if(YelpFusionClient != null) {
//            YelpFusionClient = null;
//        }

        if(yelpFusionAsyncClient != null) {
            return;
        }

        yelpFusionAsyncClient = new YelpFusionAsyncClient(yelpTransport);
    }
    
    public static YelpFusionAsyncClient createOrGetYelpFusionAsyncClient(){
        if(yelpTransport == null) {
            yelpTransport = createTransport();
        }
        if(yelpFusionAsyncClient == null) {
            initYelpFusionAsyncClient();
        }
        return yelpFusionAsyncClient;
    }

    public static YelpFusionClient createOrGetYelpFusionSynchronousBlockingClient()  {

        if(yelpTransport == null) {
            yelpTransport = createTransport();
        }

        if(yelpFusionClient == null) {
            initYelpFusionClient();
        }

        return yelpFusionClient;
    }
    
    public static void initYelpFusionClient()  {

        if(yelpFusionClient != null) {
            return;
        }

        yelpFusionClient = new YelpFusionClient(yelpTransport);

    }

    private static String API_BASE_URL;

    private static YelpFusionTransport createTransport() {

        final String yelpFusionHost = "api.yelp.com";

        final int port = 443;

        final String scheme = "https";

        API_BASE_URL = scheme + "://" + yelpFusionHost + ":" + port;

        HttpHost httpHost = new HttpHost(yelpFusionHost, port, scheme);

        String apiKey = System.getenv("YELP_API_KEY");

        Header[] defaultHeaders = {new BasicHeader("Authorization", "Bearer " + apiKey)};

        RestClient restClient = RestClient.builder(httpHost)
                .setDefaultHeaders(defaultHeaders)
                .build();

        JacksonJsonpMapper mapper = new JacksonJsonpMapper();

        try {

            return new YelpRestClientTransport(restClient, mapper);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
