package io.github.stewseo.yelp.fusion.client.yelpfusion;


import io.github.stewseo.lowlevel.restclient.RequestOptions;
import io.github.stewseo.yelp.fusion.client.transport.TransportOptions;
import io.github.stewseo.yelp.fusion.client.util.ObjectBuilder;
import io.github.stewseo.yelp.fusion.client.ApiClient;
import io.github.stewseo.yelp.fusion.client._types.ErrorResponse;
import io.github.stewseo.yelp.fusion.client.transport.JsonEndpoint;

import io.github.stewseo.yelp.fusion.client.transport.YelpFusionTransport;
import io.github.stewseo.yelp.fusion.client.yelpfusion.autocomplete.AutoCompleteRequest;
import io.github.stewseo.yelp.fusion.client.yelpfusion.autocomplete.AutoCompleteResponse;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business_details.BusinessDetailsRequest;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business_details.BusinessDetailsResponse;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business_reviews.BusinessReviewsRequest;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business_reviews.BusinessReviewsResponse;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business_search.BusinessSearchRequest;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business_search.BusinessSearchResponse;
import io.github.stewseo.yelp.fusion.client.yelpfusion.businss_match.BusinessMatchRequest;
import io.github.stewseo.yelp.fusion.client.yelpfusion.businss_match.BusinessMatchResponse;
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

    public AutoCompleteResponse autocomplete(AutoCompleteRequest request) throws Exception {
        @SuppressWarnings("unchecked")
        JsonEndpoint<AutoCompleteRequest, AutoCompleteResponse, ErrorResponse> endpoint =
                (JsonEndpoint<AutoCompleteRequest, AutoCompleteResponse, ErrorResponse>) AutoCompleteRequest._ENDPOINT;

        return this.transport.performRequest(request, endpoint, this.transportOptions);
    }

    public final AutoCompleteResponse autocomplete(
            Function<AutoCompleteRequest.Builder, ObjectBuilder<AutoCompleteRequest>> fn)
            throws Exception {
        return autocomplete(fn.apply(new AutoCompleteRequest.Builder()).build());
    }

    public BusinessReviewsResponse businessReviews(BusinessReviewsRequest request) throws Exception {
        @SuppressWarnings("unchecked")
        JsonEndpoint<BusinessReviewsRequest, BusinessReviewsResponse, ErrorResponse> endpoint =
                (JsonEndpoint<BusinessReviewsRequest, BusinessReviewsResponse, ErrorResponse>) BusinessReviewsRequest._ENDPOINT;

        return this.transport.performRequest(request, endpoint, this.transportOptions);
    }

    public final BusinessReviewsResponse businessReviews(
            Function<BusinessReviewsRequest.Builder, ObjectBuilder<BusinessReviewsRequest>> fn)
            throws Exception {
        return businessReviews(fn.apply(new BusinessReviewsRequest.Builder()).build());
    }

    public BusinessMatchResponse businessMatch(BusinessMatchRequest request) throws Exception {
        @SuppressWarnings("unchecked")
        JsonEndpoint<BusinessMatchRequest, BusinessMatchResponse, ErrorResponse> endpoint =
                (JsonEndpoint<BusinessMatchRequest, BusinessMatchResponse, ErrorResponse>) BusinessMatchRequest._ENDPOINT;

        return this.transport.performRequest(request, endpoint, this.transportOptions);
    }

    public final BusinessMatchResponse businessMatch(
            Function<BusinessMatchRequest.Builder, ObjectBuilder<BusinessMatchRequest>> fn)
            throws Exception {
        return businessMatch(fn.apply(new BusinessMatchRequest.Builder()).build());
    }

}

