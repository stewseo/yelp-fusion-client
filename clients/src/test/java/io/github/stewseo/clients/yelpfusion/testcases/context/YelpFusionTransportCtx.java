package io.github.stewseo.clients.yelpfusion.testcases.context;

import io.github.stewseo.clients.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.clients.transport.YelpFusionTransport;
import io.github.stewseo.clients.transport.restclient.RestClientTransport;
import io.github.stewseo.lowlevel.restclient.RestClient;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;

import java.io.IOException;

public class YelpFusionTransportCtx {
    private final YelpFusionTransport transport;

    public YelpFusionTransportCtx() {
        this.transport = createTransport();
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

            return new RestClientTransport(restClient, mapper);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public YelpFusionTransport getTransport() {
        return transport;
    }
}
