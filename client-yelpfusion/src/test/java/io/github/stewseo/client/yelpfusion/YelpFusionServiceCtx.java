package io.github.stewseo.client.yelpfusion;

import io.github.stewseo.client.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.client.transport.YelpFusionTransport;
import io.github.stewseo.client.transport.restclient.YelpRestClientTransport;
import io.github.stewseo.lowlevel.restclient.RestClient;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;

import java.io.IOException;

public class YelpFusionServiceCtx {

    private final YelpFusionAsyncClient yelpFusionAsyncClient;

    public YelpFusionServiceCtx() {
        this(createTransport());
    }

    public YelpFusionServiceCtx(YelpFusionTransport yelpFusionTransport) {
        this.yelpFusionAsyncClient = new YelpFusionAsyncClient(yelpFusionTransport);
    }

    private static YelpFusionTransport createTransport() {

        final String yelpFusionHost = "api.yelp.com";

        final int port = 443;

        final String scheme = "https";

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

    public YelpFusionAsyncClient getYelpFusionAsyncClient() {
        return yelpFusionAsyncClient;
    }


}
