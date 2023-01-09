package io.github.stewseo.clients.yelpfusion.testcases.context;

import io.github.stewseo.clients.transport.TransportOptions;
import io.github.stewseo.clients.transport.YelpFusionTransport;
import io.github.stewseo.clients.yelpfusion.YelpFusionAsyncClient;
import io.github.stewseo.clients.yelpfusion.YelpFusionClient;

public class YelpFusionTestService {

    private final YelpFusionTransport yelpFusionTransport;

    public YelpFusionTestService() {
        this.yelpFusionTransport = new YelpFusionTransportCtx().getTransport();
    }

    protected YelpFusionTransport yelpFusionTransport() {
        return yelpFusionTransport;
    }

    public YelpFusionClient yelpFusionClient() {
        return new YelpFusionClient(yelpFusionTransport);
    }

    public YelpFusionAsyncClient yelpFusionAsyncClient() {
        return new YelpFusionAsyncClient(yelpFusionTransport);
    }

    public TransportOptions transportOptions() {
        return yelpFusionTransport.options();
    }
}
