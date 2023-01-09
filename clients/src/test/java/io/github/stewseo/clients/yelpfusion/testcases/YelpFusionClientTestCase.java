package io.github.stewseo.clients.yelpfusion.testcases;

import io.github.stewseo.clients.transport.YelpFusionTransport;
import io.github.stewseo.clients.transport.restclient.RestClientTransport;
import io.github.stewseo.clients.yelpfusion.testcases.context.YelpFusionTestService;
import io.github.stewseo.clients.yelpfusion.testcases.context.YelpFusionTransportCtx;

public abstract class YelpFusionClientTestCase {

    private final RestClientTransport restClientTransport;
    private final String apiKey;

    public YelpFusionClientTestCase() {
        YelpFusionTransportCtx transportCtx = new YelpFusionTransportCtx();
        restClientTransport = (RestClientTransport) transportCtx.getTransport();
        apiKey = System.getenv("YELP_API_KEY");
    }

    public RestClientTransport restClientTransport() {
        return restClientTransport;
    }

    public String getApiKey() {
        return this.apiKey;
    }
}
