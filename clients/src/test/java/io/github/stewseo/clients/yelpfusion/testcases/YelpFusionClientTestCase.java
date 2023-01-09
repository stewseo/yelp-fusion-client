package io.github.stewseo.clients.yelpfusion.testcases;

import io.github.stewseo.clients.transport.YelpFusionTransport;
import io.github.stewseo.clients.transport.restclient.RestClientTransport;
import io.github.stewseo.clients.yelpfusion.testcases.context.YelpFusionTestService;
import io.github.stewseo.clients.yelpfusion.testcases.context.YelpFusionTransportCtx;

public abstract class YelpFusionClientTestCase {

    private final RestClientTransport restClientTransport;

    public YelpFusionClientTestCase() {
        YelpFusionTransportCtx transportCtx = new YelpFusionTransportCtx();
        restClientTransport = (RestClientTransport) transportCtx.getTransport();
    }

    public RestClientTransport restClientTransport() {
        return restClientTransport;
    }

}
