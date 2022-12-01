package io.github.yelp.fusion.client.yelpfusion;


import io.github.yelp.fusion.client.transport.TransportOptions;
import io.github.yelp.fusion.client.util.ObjectBuilder;
import io.github.yelp.fusion.client.ApiClient;
import io.github.yelp.fusion.client._types.ErrorResponse;
import io.github.yelp.fusion.client.transport.JsonEndpoint;

import io.github.yelp.fusion.restclient.RequestOptions;
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

    // Returns results matching a query.
    //Params:
    //fn – a function that initializes a builder to create the BusinessSearchRequest
    public BusinessSearchResponse businessSearch(BusinessSearchRequest request)
            throws Exception {
        @SuppressWarnings("unchecked")
        JsonEndpoint<BusinessSearchRequest, BusinessSearchResponse, ErrorResponse> endpoint = (JsonEndpoint<BusinessSearchRequest, BusinessSearchResponse, ErrorResponse>) BusinessSearchRequest._ENDPOINT;

        return this.transport.performRequest(request, endpoint, this.transportOptions);
    }

    public final BusinessSearchResponse businessSearch(
            Function<BusinessSearchRequest.Builder, ObjectBuilder<BusinessSearchRequest>> fn)
            throws Exception {

        return businessSearch(fn.apply(new BusinessSearchRequest.Builder()).build());
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

