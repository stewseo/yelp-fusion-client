package io.github.stewseo.clients.yelpfusion.businesses;

import io.github.stewseo.clients.ApiClient;
import io.github.stewseo.clients.transport.JsonEndpoint;
import io.github.stewseo.clients.transport.TransportOptions;
import io.github.stewseo.clients.transport.YelpFusionTransport;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.yelpfusion._types.ErrorResponse;
import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetailsRequest;
import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetailsResponse;
import io.github.stewseo.clients.yelpfusion.businesses.match.BusinessMatchRequest;
import io.github.stewseo.clients.yelpfusion.businesses.match.BusinessMatchResponse;
import io.github.stewseo.clients.yelpfusion.businesses.reviews.BusinessReviewsRequest;
import io.github.stewseo.clients.yelpfusion.businesses.reviews.BusinessReviewsResponse;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessRequest;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessResponse;
import io.github.stewseo.clients.yelpfusion.businesses.transactions.SearchTransactionRequest;

import javax.annotation.Nullable;
import java.io.IOException;
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

    public SearchBusinessResponse businessSearch(SearchBusinessRequest request)
            throws Exception {
        @SuppressWarnings("unchecked")
        JsonEndpoint<SearchBusinessRequest, SearchBusinessResponse, ErrorResponse> endpoint =
                (JsonEndpoint<SearchBusinessRequest, SearchBusinessResponse, ErrorResponse>) SearchBusinessRequest._ENDPOINT;

        return this.transport.performRequest(request, endpoint, this.transportOptions);
    }

    public final SearchBusinessResponse businessSearch(
            Function<SearchBusinessRequest.Builder, ObjectBuilder<SearchBusinessRequest>> fn)
            throws Exception {

        return businessSearch(fn.apply(new SearchBusinessRequest.Builder()).build());
    }

    public BusinessDetailsResponse businessDetails(BusinessDetailsRequest request) throws IOException {
        @SuppressWarnings("unchecked")
        JsonEndpoint<BusinessDetailsRequest, BusinessDetailsResponse, ErrorResponse> endpoint =
                (JsonEndpoint<BusinessDetailsRequest, BusinessDetailsResponse, ErrorResponse>) BusinessDetailsRequest._ENDPOINT;

        return this.transport.performRequest(request, endpoint, this.transportOptions);
    }

    public final BusinessDetailsResponse businessDetails(
            Function<BusinessDetailsRequest.Builder, ObjectBuilder<BusinessDetailsRequest>> fn) throws IOException {
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

    public SearchBusinessResponse searchTransaction(SearchTransactionRequest request) throws Exception {
        @SuppressWarnings("unchecked")
        JsonEndpoint<SearchTransactionRequest, SearchBusinessResponse, ErrorResponse> endpoint =
                (JsonEndpoint<SearchTransactionRequest, SearchBusinessResponse, ErrorResponse>) SearchTransactionRequest._ENDPOINT;

        return this.transport.performRequest(request, endpoint, this.transportOptions);
    }

    public final SearchBusinessResponse searchTransaction(
            Function<SearchTransactionRequest.Builder, ObjectBuilder<SearchTransactionRequest>> fn)
            throws Exception {
        return searchTransaction(fn.apply(new SearchTransactionRequest.Builder()).build());
    }

}
