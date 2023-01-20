package io.github.stewseo.clients.yelpfusion;

import io.github.stewseo.clients.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.clients.transport.YelpFusionTransport;
import io.github.stewseo.clients.transport.restclient.RestClientTransport;
import io.github.stewseo.lowlevel.restclient.RestClient;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;

import java.io.IOException;

public class YelpFusionCtx {

    private static volatile YelpFusionCtx instance;

    private static final Object mutex = new Object();

    // Create the private constructor to avoid any new object creation with new operator.
    private YelpFusionCtx() {
    }

    public static YelpFusionCtx getInstance() {
        YelpFusionCtx result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null)
                    instance = result = new YelpFusionCtx();
            }
        }
        return result;
    }

    public YelpFusionTransport createYelpFusionTransport() {

        String hostName = "api.yelp.com";

        int port = 443;

        String scheme = "https";

        try {

            HttpHost host = new HttpHost(hostName, port, scheme);

            Header[] defaultHeader = {new BasicHeader("Authorization", "Bearer " + System.getenv("YELP_API_KEY"))};

            RestClient restClient = RestClient.builder(host)
                    .setDefaultHeaders(defaultHeader)
                    .build();

            return new RestClientTransport(restClient, new JacksonJsonpMapper());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
