package io.github.stewseo.client.yelpfusion.categories;

import io.github.stewseo.client.ApiClient;
import io.github.stewseo.client._types.ErrorResponse;
import io.github.stewseo.client.transport.JsonEndpoint;
import io.github.stewseo.client.transport.YelpFusionTransport;
import io.github.stewseo.client.util.ObjectBuilder;
import io.github.stewseo.client.transport.TransportOptions;

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

    public GetCategoriesResponse all(GetCategoriesRequest request) throws Exception {
        @SuppressWarnings("unchecked")
        JsonEndpoint<GetCategoriesRequest, GetCategoriesResponse, ErrorResponse> endpoint =
                (JsonEndpoint<GetCategoriesRequest, GetCategoriesResponse, ErrorResponse>) GetCategoriesRequest._ENDPOINT;
        return this.transport.performRequest(request, endpoint, this.transportOptions);
    }

    public final GetCategoriesResponse all(
            Function<GetCategoriesRequest.Builder, ObjectBuilder<GetCategoriesRequest>> fn)
            throws Exception {
        return all(fn.apply(new GetCategoriesRequest.Builder()).build());
    }

    public GetCategoriesAliasResponse alias(GetCategoriesAliasRequest request) throws Exception {
        @SuppressWarnings("unchecked")
        JsonEndpoint<GetCategoriesAliasRequest, GetCategoriesAliasResponse, ErrorResponse> endpoint =

                (JsonEndpoint<GetCategoriesAliasRequest, GetCategoriesAliasResponse, ErrorResponse>) GetCategoriesAliasRequest._ENDPOINT;
        return this.transport.performRequest(request, endpoint, this.transportOptions);
    }

    public final GetCategoriesAliasResponse alias(
            Function<GetCategoriesAliasRequest.Builder, ObjectBuilder<GetCategoriesAliasRequest>> fn)
            throws Exception {

        return alias(fn.apply(new GetCategoriesAliasRequest.Builder()).build());
    }

}
