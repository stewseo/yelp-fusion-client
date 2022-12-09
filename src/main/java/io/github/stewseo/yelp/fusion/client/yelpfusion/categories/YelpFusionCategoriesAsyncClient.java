package io.github.stewseo.yelp.fusion.client.yelpfusion.categories;

import io.github.stewseo.yelp.fusion.client.ApiClient;
import io.github.stewseo.yelp.fusion.client._types.ErrorResponse;
import io.github.stewseo.yelp.fusion.client.transport.JsonEndpoint;
import io.github.stewseo.yelp.fusion.client.transport.TransportOptions;
import io.github.stewseo.yelp.fusion.client.transport.YelpFusionTransport;
import io.github.stewseo.yelp.fusion.client.transport.restclient.YelpRestTransportOptions;
import io.github.stewseo.yelp.fusion.client.util.ObjectBuilder;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;


public class YelpFusionCategoriesAsyncClient extends ApiClient<YelpFusionTransport, YelpFusionCategoriesAsyncClient> {

    public YelpFusionCategoriesAsyncClient(YelpFusionTransport transport) {
        super(transport, null);
    }

    public YelpFusionCategoriesAsyncClient(YelpFusionTransport transport,
                                          @Nullable TransportOptions transportOptions) {
        super(transport, transportOptions);
    }

    @Override
    public YelpFusionCategoriesAsyncClient withTransportOptions(@Nullable TransportOptions transportOptions) {
        return new YelpFusionCategoriesAsyncClient(this.transport, transportOptions);
    }

    public CompletableFuture<GetCategoriesResponse> categories(GetCategoriesRequest request) throws Exception {
        @SuppressWarnings("unchecked")
        JsonEndpoint<GetCategoriesRequest, GetCategoriesResponse, ErrorResponse> endpoint =
                (JsonEndpoint<GetCategoriesRequest, GetCategoriesResponse, ErrorResponse>) GetCategoriesRequest._ENDPOINT;
        return this.transport.performRequestAsync(request, endpoint, this.transportOptions);
    }

    public final CompletableFuture<GetCategoriesResponse> categories(
            Function<GetCategoriesRequest.Builder, ObjectBuilder<GetCategoriesRequest>> fn)
            throws Exception {
        return categories(fn.apply(new GetCategoriesRequest.Builder()).build());
    }

    public CompletableFuture<GetCategoriesAliasResponse> categoriesAlias(GetCategoriesAliasRequest request) throws Exception {
        @SuppressWarnings("unchecked")
        JsonEndpoint<GetCategoriesAliasRequest, GetCategoriesAliasResponse, ErrorResponse> endpoint =
                (JsonEndpoint<GetCategoriesAliasRequest, GetCategoriesAliasResponse, ErrorResponse>) GetCategoriesAliasRequest._ENDPOINT;
        return this.transport.performRequestAsync(request, endpoint, this.transportOptions);
    }

    public final CompletableFuture<GetCategoriesAliasResponse> categoriesAlias(
            Function<GetCategoriesAliasRequest.Builder, ObjectBuilder<GetCategoriesAliasRequest>> fn)
            throws Exception {
        return categoriesAlias(fn.apply(new GetCategoriesAliasRequest.Builder()).build());
    }
}
