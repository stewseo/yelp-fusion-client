package io.github.stewseo.client.yelpfusion.events;

import io.github.stewseo.client.ApiClient;
import io.github.stewseo.client._types.ErrorResponse;
import io.github.stewseo.client.transport.JsonEndpoint;
import io.github.stewseo.client.transport.TransportOptions;
import io.github.stewseo.client.transport.YelpFusionTransport;
import io.github.stewseo.client.util.ObjectBuilder;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

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

    public CompletableFuture<FeaturedEventResponse> featuredEvent(FeaturedEventRequest request) throws Exception {
        @SuppressWarnings("unchecked")
        JsonEndpoint<FeaturedEventRequest, FeaturedEventResponse, ErrorResponse> endpoint =
                (JsonEndpoint<FeaturedEventRequest, FeaturedEventResponse, ErrorResponse>) FeaturedEventRequest._ENDPOINT;
        return this.transport.performRequestAsync(request, endpoint, this.transportOptions);
    }

    public final CompletableFuture<FeaturedEventResponse> featuredEvent(
            Function<FeaturedEventRequest.Builder, ObjectBuilder<FeaturedEventRequest>> fn)
            throws Exception {
        return featuredEvent(fn.apply(new FeaturedEventRequest.Builder()).build());
    }

    public CompletableFuture<EventSearchResponse> search(EventSearchRequest request) throws Exception {
        @SuppressWarnings("unchecked")
        JsonEndpoint<EventSearchRequest, EventSearchResponse, ErrorResponse> endpoint =
                (JsonEndpoint<EventSearchRequest, EventSearchResponse, ErrorResponse>) EventSearchRequest._ENDPOINT;
        return this.transport.performRequestAsync(request, endpoint, this.transportOptions);
    }

    public final CompletableFuture<EventSearchResponse> search(
            Function<EventSearchRequest.Builder, ObjectBuilder<EventSearchRequest>> fn)
            throws Exception {
        return search(fn.apply(new EventSearchRequest.Builder()).build());
    }
}
