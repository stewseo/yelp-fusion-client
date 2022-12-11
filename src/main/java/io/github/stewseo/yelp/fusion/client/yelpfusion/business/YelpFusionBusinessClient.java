package io.github.stewseo.yelp.fusion.client.yelpfusion.business;

import io.github.stewseo.yelp.fusion.client.ApiClient;
import io.github.stewseo.yelp.fusion.client._types.ErrorResponse;
import io.github.stewseo.yelp.fusion.client.transport.JsonEndpoint;
import io.github.stewseo.yelp.fusion.client.transport.TransportOptions;
import io.github.stewseo.yelp.fusion.client.transport.YelpFusionTransport;
import io.github.stewseo.yelp.fusion.client.util.ObjectBuilder;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.details.BusinessDetailsRequest;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.details.BusinessDetailsResponse;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.match.BusinessMatchRequest;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.match.BusinessMatchResponse;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.reviews.BusinessReviewsRequest;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.reviews.BusinessReviewsResponse;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.search.BusinessSearchRequest;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.search.BusinessSearchResponse;
import io.github.stewseo.yelp.fusion.client.yelpfusion.categories.YelpFusionCategoriesClient;

import javax.annotation.Nullable;
import java.util.function.Function;

public class YelpFusionBusinessClient extends ApiClient<YelpFusionTransport, YelpFusionBusinessClient> {

    public YelpFusionBusinessClient(YelpFusionTransport transport) {
        super(transport, null);
    }

    public YelpFusionBusinessClient(YelpFusionTransport transport, @Nullable TransportOptions transportOptions) {
        super(transport, transportOptions);
    }

    @Override
    public YelpFusionBusinessClient withTransportOptions(@Nullable TransportOptions transportOptions) {
        return new YelpFusionBusinessClient(this.transport, transportOptions);
    }

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
