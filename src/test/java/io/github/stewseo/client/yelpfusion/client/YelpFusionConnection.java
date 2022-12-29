package io.github.stewseo.client.yelpfusion.client;

import io.github.stewseo.client.transport.YelpFusionTransport;
import io.github.stewseo.lowlevel.restclient.RestClient;
import io.github.stewseo.client.json.JsonpMapper;
import io.github.stewseo.client.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.client.transport.restclient.YelpRestClientTransport;
import io.github.stewseo.client.yelpfusion.YelpFusionAsyncClient;
import io.github.stewseo.client.yelpfusion.YelpFusionSyncBlockingClient;
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

    static YelpFusionSyncBlockingClient yelpFusionSyncBlockingClient;

    static YelpFusionAsyncClient yelpFusionAsyncClient;


    private static JsonpMapper mapper;

    public static void initYelpFusionAsyncClient() {
        // only one client allowed per instance
//        if(yelpFusionSyncBlockingClient != null) {
//            yelpFusionSyncBlockingClient = null;
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

    public static YelpFusionSyncBlockingClient createOrGetYelpFusionSynchronousBlockingClient()  {

        if(yelpTransport == null) {
            yelpTransport = createTransport();
        }

        if(yelpFusionSyncBlockingClient == null) {
            initYelpFusionSyncBlockingClient();
        }

        return yelpFusionSyncBlockingClient;
    }
    
    public static void initYelpFusionSyncBlockingClient()  {
        // only one client allowed per instance
//        if(yelpFusionAsyncClient != null) {
//            yelpFusionAsyncClient = null;
//        }

        if(yelpFusionSyncBlockingClient != null) {
            System.out.println("not null");
            return;
        }

        yelpFusionSyncBlockingClient = new YelpFusionSyncBlockingClient(yelpTransport);

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
