package io.github.stewseo.clients.yelpfusion.business;

import io.github.stewseo.clients.yelpfusion.business.details.BusinessDetailsRequest;
import io.github.stewseo.clients.yelpfusion.business.details.BusinessDetailsResponse;
import io.github.stewseo.clients.yelpfusion.business.match.BusinessMatchResponse;
import io.github.stewseo.clients.yelpfusion.business.reviews.ReviewsRequest;
import io.github.stewseo.clients.yelpfusion.business.reviews.ReviewsResponse;
import io.github.stewseo.clients.yelpfusion.business.search.SearchBusinessRequest;
import io.github.stewseo.clients.yelpfusion.business.search.SearchBusinessResponse;
import io.github.stewseo.clients.yelpfusion.business.transactions.SearchTransactionResponse;
import io.github.stewseo.clients.ApiClient;
import io.github.stewseo.clients.yelpfusion._types.ErrorResponse;
import io.github.stewseo.clients.transport.JsonEndpoint;
import io.github.stewseo.clients.transport.TransportOptions;
import io.github.stewseo.clients.transport.YelpFusionTransport;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.yelpfusion.business.match.BusinessMatchRequest;
import io.github.stewseo.clients.yelpfusion.business.transactions.SearchTransactionRequest;

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

    public ReviewsResponse businessReviews(ReviewsRequest request) throws Exception {
        @SuppressWarnings("unchecked")
        JsonEndpoint<ReviewsRequest, ReviewsResponse, ErrorResponse> endpoint =
                (JsonEndpoint<ReviewsRequest, ReviewsResponse, ErrorResponse>) ReviewsRequest._ENDPOINT;

        return this.transport.performRequest(request, endpoint, this.transportOptions);
    }

    public final ReviewsResponse businessReviews(
            Function<ReviewsRequest.Builder, ObjectBuilder<ReviewsRequest>> fn)
            throws Exception {
        return businessReviews(fn.apply(new ReviewsRequest.Builder()).build());
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

    public SearchTransactionResponse searchTransaction(SearchTransactionRequest request) throws Exception {
        @SuppressWarnings("unchecked")
        JsonEndpoint<SearchTransactionRequest, SearchTransactionResponse, ErrorResponse> endpoint =
                (JsonEndpoint<SearchTransactionRequest, SearchTransactionResponse, ErrorResponse>) SearchTransactionRequest._ENDPOINT;

        return this.transport.performRequest(request, endpoint, this.transportOptions);
    }

    public final SearchTransactionResponse searchTransaction(
            Function<SearchTransactionRequest.Builder, ObjectBuilder<SearchTransactionRequest>> fn)
            throws Exception {
        return searchTransaction(fn.apply(new SearchTransactionRequest.Builder()).build());
    }

}
