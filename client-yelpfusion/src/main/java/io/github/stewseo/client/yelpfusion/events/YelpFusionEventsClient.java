package io.github.stewseo.client.yelpfusion.events;

import io.github.stewseo.client.ApiClient;
import io.github.stewseo.client._types.ErrorResponse;
import io.github.stewseo.client.transport.JsonEndpoint;
import io.github.stewseo.client.transport.TransportOptions;
import io.github.stewseo.client.transport.YelpFusionTransport;
import io.github.stewseo.client.util.ObjectBuilder;

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

    public EventSearchResponse search(EventSearchRequest request) throws Exception {
        @SuppressWarnings("unchecked")
        JsonEndpoint<EventSearchRequest, EventSearchResponse, ErrorResponse> endpoint =
                (JsonEndpoint<EventSearchRequest, EventSearchResponse, ErrorResponse>) EventSearchRequest._ENDPOINT;
        return this.transport.performRequest(request, endpoint, this.transportOptions);
    }

    public final EventSearchResponse search(
            Function<EventSearchRequest.Builder, ObjectBuilder<EventSearchRequest>> fn)
            throws Exception {

        return search(fn.apply(new EventSearchRequest.Builder()).build());
    }

    public FeaturedEventResponse featured(FeaturedEventRequest request) throws Exception {
        @SuppressWarnings("unchecked")
        JsonEndpoint<FeaturedEventRequest, FeaturedEventResponse, ErrorResponse> endpoint =
                (JsonEndpoint<FeaturedEventRequest, FeaturedEventResponse, ErrorResponse>) FeaturedEventRequest._ENDPOINT;
        return this.transport.performRequest(request, endpoint, this.transportOptions);
    }

    public final FeaturedEventResponse featured(
            Function<FeaturedEventRequest.Builder, ObjectBuilder<FeaturedEventRequest>> fn)
            throws Exception {

        return featured(fn.apply(new FeaturedEventRequest.Builder()).build());
    }
}
