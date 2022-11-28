package org.example.yelp.fusion.client;


import org.example.elasticsearch.client.ApiClient;
import org.example.elasticsearch.client._types.ErrorResponse;
import org.example.elasticsearch.client.transport.JsonEndpoint;
import org.example.elasticsearch.client.transport.TransportOptions;
import org.example.elasticsearch.client.transport.endpoints.EndpointWithResponseMapperAttr;
import org.example.elasticsearch.client.util.ObjectBuilder;
import org.example.lowlevel.restclient.RequestOptions;
import org.example.yelp.fusion.client.business.BusinessDetailsRequest;
import org.example.yelp.fusion.client.business.BusinessSearchRequest;
import org.example.yelp.fusion.client.business.BusinessSearchResponse;
import org.example.yelp.fusion.client.business.BusinessDetailsResponse_;
import org.example.yelp.fusion.client.exception.YelpFusionException;
import org.example.yelp.fusion.client.transport.YelpFusionTransport;
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

    public BusinessDetailsResponse_ businessDetails(BusinessDetailsRequest request) throws Exception {
        @SuppressWarnings("unchecked")
        JsonEndpoint<BusinessDetailsRequest, BusinessDetailsResponse_, ErrorResponse> endpoint =
                (JsonEndpoint<BusinessDetailsRequest, BusinessDetailsResponse_, ErrorResponse>) BusinessDetailsRequest._ENDPOINT;

        return this.transport.performRequest(request, endpoint, this.transportOptions);
    }

    public final BusinessDetailsResponse_ businessDetails(
            Function<BusinessDetailsRequest.Builder, ObjectBuilder<BusinessDetailsRequest>> fn)
            throws Exception {
        return businessDetails(fn.apply(new BusinessDetailsRequest.Builder()).build());
    }



}

