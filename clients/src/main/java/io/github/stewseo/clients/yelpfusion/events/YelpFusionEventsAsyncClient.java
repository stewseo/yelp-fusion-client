package io.github.stewseo.clients.yelpfusion.events;

import io.github.stewseo.clients.ApiClient;
import io.github.stewseo.clients.yelpfusion._types.ErrorResponse;
import io.github.stewseo.clients.transport.JsonEndpoint;
import io.github.stewseo.clients.transport.TransportOptions;
import io.github.stewseo.clients.transport.YelpFusionTransport;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.yelpfusion.events.featured.FeaturedEventRequest;
import io.github.stewseo.clients.yelpfusion.events.featured.FeaturedEventResponse;
import io.github.stewseo.clients.yelpfusion.events.search.SearchEventsRequest;
import io.github.stewseo.clients.yelpfusion.events.search.SearchEventsResponse;

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

    public CompletableFuture<FeaturedEventResponse> featured(FeaturedEventRequest request) throws Exception {
        @SuppressWarnings("unchecked")
        JsonEndpoint<FeaturedEventRequest, FeaturedEventResponse, ErrorResponse> endpoint =
                (JsonEndpoint<FeaturedEventRequest, FeaturedEventResponse, ErrorResponse>) FeaturedEventRequest._ENDPOINT;
        return this.transport.performRequestAsync(request, endpoint, this.transportOptions);
    }

    public final CompletableFuture<FeaturedEventResponse> featured(
            Function<FeaturedEventRequest.Builder, ObjectBuilder<FeaturedEventRequest>> fn)
            throws Exception {
        return featured(fn.apply(new FeaturedEventRequest.Builder()).build());
    }

    public CompletableFuture<SearchEventsResponse> search(SearchEventsRequest request) throws Exception {
        @SuppressWarnings("unchecked")
        JsonEndpoint<SearchEventsRequest, SearchEventsResponse, ErrorResponse> endpoint =
                (JsonEndpoint<SearchEventsRequest, SearchEventsResponse, ErrorResponse>) SearchEventsRequest._ENDPOINT;
        return this.transport.performRequestAsync(request, endpoint, this.transportOptions);
    }

    public final CompletableFuture<SearchEventsResponse> search(
            Function<SearchEventsRequest.Builder, ObjectBuilder<SearchEventsRequest>> fn)
            throws Exception {
        return search(fn.apply(new SearchEventsRequest.Builder()).build());
    }
}
