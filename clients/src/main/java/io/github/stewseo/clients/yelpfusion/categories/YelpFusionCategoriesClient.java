package io.github.stewseo.clients.yelpfusion.categories;

import io.github.stewseo.clients.ApiClient;
import io.github.stewseo.clients.yelpfusion._types.ErrorResponse;
import io.github.stewseo.clients.transport.JsonEndpoint;
import io.github.stewseo.clients.transport.TransportOptions;
import io.github.stewseo.clients.transport.YelpFusionTransport;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.yelpfusion.categories.alias.CategoriesAliasRequest;
import io.github.stewseo.clients.yelpfusion.categories.alias.CategoriesAliasResponse;
import io.github.stewseo.clients.yelpfusion.categories.all.CategoriesRequest;
import io.github.stewseo.clients.yelpfusion.categories.all.CategoriesResponse;

import javax.annotation.Nullable;
import java.util.function.Function;

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

    public CategoriesResponse all(CategoriesRequest request) throws Exception {
        @SuppressWarnings("unchecked")
        JsonEndpoint<CategoriesRequest, CategoriesResponse, ErrorResponse> endpoint =
                (JsonEndpoint<CategoriesRequest, CategoriesResponse, ErrorResponse>) CategoriesRequest._ENDPOINT;
        return this.transport.performRequest(request, endpoint, this.transportOptions);
    }

    public final CategoriesResponse all(
            Function<CategoriesRequest.Builder, ObjectBuilder<CategoriesRequest>> fn)
            throws Exception {
        return all(fn.apply(new CategoriesRequest.Builder()).build());
    }

    public CategoriesAliasResponse alias(CategoriesAliasRequest request) throws Exception {
        @SuppressWarnings("unchecked")
        JsonEndpoint<CategoriesAliasRequest, CategoriesAliasResponse, ErrorResponse> endpoint =

                (JsonEndpoint<CategoriesAliasRequest, CategoriesAliasResponse, ErrorResponse>) CategoriesAliasRequest._ENDPOINT;
        return this.transport.performRequest(request, endpoint, this.transportOptions);
    }

    public final CategoriesAliasResponse alias(
            Function<CategoriesAliasRequest.Builder, ObjectBuilder<CategoriesAliasRequest>> fn)
            throws Exception {

        return alias(fn.apply(new CategoriesAliasRequest.Builder()).build());
    }

}
