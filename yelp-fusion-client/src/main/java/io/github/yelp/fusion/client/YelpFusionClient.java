package io.github.yelp.fusion.client;


import io.github.yelp.fusion.client.business.BusinessDetailsRequest;
import io.github.elasticsearch.client.ApiClient;
import io.github.elasticsearch.client._types.ErrorResponse;
import io.github.elasticsearch.client.transport.JsonEndpoint;
import io.github.elasticsearch.client.transport.TransportOptions;
import io.github.elasticsearch.client.transport.endpoints.EndpointWithResponseMapperAttr;
import io.github.elasticsearch.client.util.ObjectBuilder;
import io.github.lowlevel.restclient.RequestOptions;
import io.github.yelp.fusion.client.business.BusinessSearchRequest;
import io.github.yelp.fusion.client.business.BusinessSearchResponse;
import io.github.yelp.fusion.client.business.BusinessDetailsResponse;
import io.github.yelp.fusion.client.exception.YelpFusionException;
import io.github.yelp.fusion.client.transport.YelpFusionTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;


public class YelpFusionClient extends ApiClient<YelpFusionTransport, YelpFusionClient> {

    private static final Logger logger = LoggerFactory.getLogger(YelpFusionClient.class);

    public static final RequestOptions YELP_AUTHORIZATION_HEADER;


    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
        builder.addHeader("Authorization", "Bearer " + System.getenv("YELP_API_KEY"));
        YELP_AUTHORIZATION_HEADER = builder.build();
    }

    public YelpFusionClient(YelpFusionTransport transport) {
        super(transport, null);
    }

    public YelpFusionClient(YelpFusionTransport transport, TransportOptions transportOptions) {
        super(transport, transportOptions);
    }

    @Override
    public YelpFusionClient withTransportOptions(TransportOptions transportOptions) {
        return new YelpFusionClient(this.transport, transportOptions);
    }

    public final <TDocument> BusinessSearchResponse<TDocument> businessSearch(
            Function<BusinessSearchRequest.Builder, ObjectBuilder<BusinessSearchRequest>> fn, Class<TDocument> tDocumentClass)
            throws YelpFusionException {
        return businessSearch(fn.apply(new BusinessSearchRequest.Builder()).build(), tDocumentClass);
    }


    public <TDocument> BusinessSearchResponse<TDocument> businessSearch(BusinessSearchRequest request, Class<TDocument> tDocumentClass) {

        @SuppressWarnings("unchecked")
        JsonEndpoint<BusinessSearchRequest, BusinessSearchResponse<TDocument>, ErrorResponse> endpoint =
                (JsonEndpoint<BusinessSearchRequest, BusinessSearchResponse<TDocument>, ErrorResponse>) BusinessSearchRequest._ENDPOINT;

        endpoint = new EndpointWithResponseMapperAttr<>(endpoint,
                "org.example.clients:Deserializer:_global.search.TDocument", getDeserializer(tDocumentClass));

        try {
            return this.transport.performRequest(request, endpoint, this.transportOptions);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public BusinessDetailsResponse businessDetails(BusinessDetailsRequest request) throws Exception {
        @SuppressWarnings("unchecked")
        JsonEndpoint<BusinessDetailsRequest, BusinessDetailsResponse, ErrorResponse> endpoint =
                (JsonEndpoint<BusinessDetailsRequest, BusinessDetailsResponse, ErrorResponse>) BusinessDetailsRequest._ENDPOINT;

        return this.transport.performRequest(request, endpoint, this.transportOptions);
    }

    public final BusinessDetailsResponse businessDetails(
            Function<BusinessDetailsRequest.Builder, ObjectBuilder<BusinessDetailsRequest>> fn)
            throws Exception {
        return businessDetails(fn.apply(new BusinessDetailsRequest.Builder()).build());
    }



}

