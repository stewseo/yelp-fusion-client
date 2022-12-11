package io.github.stewseo.yelp.fusion.client.yelpfusion.events;

import io.github.stewseo.yelp.fusion.client.ApiClient;
import io.github.stewseo.yelp.fusion.client._types.ErrorResponse;
import io.github.stewseo.yelp.fusion.client.transport.JsonEndpoint;
import io.github.stewseo.yelp.fusion.client.transport.TransportOptions;
import io.github.stewseo.yelp.fusion.client.transport.YelpFusionTransport;
import io.github.stewseo.yelp.fusion.client.util.ObjectBuilder;

import javax.annotation.Nullable;
import java.util.function.Function;

public class YelpFusionEventsClient extends ApiClient<YelpFusionTransport, YelpFusionEventsClient> {
    public YelpFusionEventsClient(YelpFusionTransport transport) {
        super(transport, null);
    }

    public YelpFusionEventsClient(YelpFusionTransport transport, @Nullable TransportOptions transportOptions) {
        super(transport, transportOptions);
    }
    @Override
    public YelpFusionEventsClient withTransportOptions(@Nullable TransportOptions transportOptions) {
        return new YelpFusionEventsClient(this.transport, transportOptions);
    }

    public EventSearchResponse eventSearch(EventSearchRequest request) throws Exception {
        @SuppressWarnings("unchecked")
        JsonEndpoint<EventSearchRequest, EventSearchResponse, ErrorResponse> endpoint =
                (JsonEndpoint<EventSearchRequest, EventSearchResponse, ErrorResponse>) EventSearchRequest._ENDPOINT;
        return this.transport.performRequest(request, endpoint, this.transportOptions);
    }

    public final EventSearchResponse eventSearch(
            Function<EventSearchRequest.Builder, ObjectBuilder<EventSearchRequest>> fn)
            throws Exception {

        return eventSearch(fn.apply(new EventSearchRequest.Builder()).build());
    }
}
