package io.github.stewseo.yelp.fusion.client.yelpfusion.categories;

import io.github.stewseo.yelp.fusion.client.ApiClient;
import io.github.stewseo.yelp.fusion.client.transport.TransportOptions;
import io.github.stewseo.yelp.fusion.client.transport.YelpFusionTransport;

import javax.annotation.Nullable;

public class YelpFusionCategoriesClient extends ApiClient<YelpFusionTransport, YelpFusionCategoriesClient> {
    public YelpFusionCategoriesClient(YelpFusionTransport transport) {
        super(transport, null);
    }

    public YelpFusionCategoriesClient(YelpFusionTransport transport, @Nullable TransportOptions transportOptions) {
        super(transport, transportOptions);
    }

    @Override
    public YelpFusionCategoriesClient withTransportOptions(@Nullable TransportOptions transportOptions) {
        return new YelpFusionCategoriesClient(this.transport, transportOptions);
    }
}
