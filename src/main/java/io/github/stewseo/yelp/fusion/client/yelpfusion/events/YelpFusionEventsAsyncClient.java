package io.github.stewseo.yelp.fusion.client.yelpfusion.events;

import io.github.stewseo.yelp.fusion.client.ApiClient;
import io.github.stewseo.yelp.fusion.client.transport.TransportOptions;
import io.github.stewseo.yelp.fusion.client.transport.YelpFusionTransport;
import io.github.stewseo.yelp.fusion.client.yelpfusion.categories.YelpFusionCategoriesAsyncClient;

import javax.annotation.Nullable;
public class YelpFusionEventsAsyncClient extends ApiClient<YelpFusionTransport, YelpFusionEventsAsyncClient> {

    public YelpFusionEventsAsyncClient(YelpFusionTransport transport) {
        super(transport, null);
    }

    public YelpFusionEventsAsyncClient(YelpFusionTransport transport, @Nullable TransportOptions transportOptions) {
        super(transport, transportOptions);
    }
    @Override
    public YelpFusionEventsAsyncClient withTransportOptions(@Nullable TransportOptions transportOptions) {
        return new YelpFusionEventsAsyncClient(this.transport, transportOptions);
    }
}
