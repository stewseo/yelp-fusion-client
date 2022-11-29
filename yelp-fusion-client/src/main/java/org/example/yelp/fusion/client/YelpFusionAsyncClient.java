package org.example.yelp.fusion.client;


import org.example.elasticsearch.client.ApiClient;
import org.example.elasticsearch.client._types.ErrorResponse;
import org.example.elasticsearch.client.transport.JsonEndpoint;
import org.example.elasticsearch.client.transport.TransportOptions;
import org.example.elasticsearch.client.transport.endpoints.EndpointWithResponseMapperAttr;
import org.example.elasticsearch.client.util.ObjectBuilder;
import org.example.yelp.fusion.client.business.BusinessSearchRequest;
import org.example.yelp.fusion.client.business.BusinessSearchResponse;
import org.example.yelp.fusion.client.transport.YelpFusionTransport;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class YelpFusionAsyncClient extends ApiClient<YelpFusionTransport, YelpFusionAsyncClient> {

    public YelpFusionAsyncClient(YelpFusionTransport transport) {
        super(transport, null);
    }

    public YelpFusionAsyncClient(YelpFusionTransport transport, @Nullable TransportOptions transportOptions) {
        super(transport, transportOptions);
    }

    @Override
    public YelpFusionAsyncClient withTransportOptions(@Nullable TransportOptions transportOptions) {
        return new YelpFusionAsyncClient(this.transport, transportOptions);
    }

    public <TDocument> CompletableFuture<BusinessSearchResponse<TDocument>> search(BusinessSearchRequest request,
                                                                           Class<TDocument> tDocumentClass) {
        @SuppressWarnings("unchecked")
        JsonEndpoint<BusinessSearchRequest, BusinessSearchResponse<TDocument>, ErrorResponse> endpoint = (JsonEndpoint<BusinessSearchRequest, BusinessSearchResponse<TDocument>, ErrorResponse>) BusinessSearchRequest._ENDPOINT;
        endpoint = new EndpointWithResponseMapperAttr<>(endpoint,
                "org.example.clients:Deserializer:_global.search.TDocument", getDeserializer(tDocumentClass));

        return this.transport.performRequestAsync(request, endpoint, this.transportOptions);
    }


    public final <TDocument> CompletableFuture<BusinessSearchResponse<TDocument>> search(

            Function<BusinessSearchRequest.Builder, ObjectBuilder<BusinessSearchRequest>> fn, Class<TDocument> tDocumentClass) {

        return search(fn.apply(new BusinessSearchRequest.Builder()).build(), tDocumentClass);
    }

}
