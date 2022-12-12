package io.github.stewseo.yelp.fusion.client.yelpfusion.business;

import io.github.stewseo.yelp.fusion.client.ApiClient;
import io.github.stewseo.yelp.fusion.client.transport.TransportOptions;
import io.github.stewseo.yelp.fusion.client.transport.YelpFusionTransport;

import javax.annotation.Nullable;

public class YelpFusionBusinessAsyncClient extends ApiClient<YelpFusionTransport, YelpFusionBusinessAsyncClient> {

    public YelpFusionBusinessAsyncClient(YelpFusionTransport transport) {
        super(transport, null);
    }

    public YelpFusionBusinessAsyncClient(YelpFusionTransport transport, @Nullable TransportOptions transportOptions) {
        super(transport, transportOptions);
    }
    @Override
    public YelpFusionBusinessAsyncClient withTransportOptions(@Nullable TransportOptions transportOptions) {
        return new YelpFusionBusinessAsyncClient(this.transport, transportOptions);
    }

}
