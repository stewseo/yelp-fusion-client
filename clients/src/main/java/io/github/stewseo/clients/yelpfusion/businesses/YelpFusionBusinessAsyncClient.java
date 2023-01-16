package io.github.stewseo.clients.yelpfusion.businesses;

import io.github.stewseo.clients.ApiClient;
import io.github.stewseo.clients.transport.JsonEndpoint;
import io.github.stewseo.clients.transport.TransportOptions;
import io.github.stewseo.clients.transport.YelpFusionTransport;
import io.github.stewseo.clients.transport.endpoints.EndpointWithResponseMapperAttr;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.yelpfusion._types.ErrorResponse;
import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetailsRequest;
import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetailsResponse;
import io.github.stewseo.clients.yelpfusion.businesses.match.MatchBusinessesRequest;
import io.github.stewseo.clients.yelpfusion.businesses.match.MatchBusinessesResponse;
import io.github.stewseo.clients.yelpfusion.businesses.reviews.BusinessReviewsRequest;
import io.github.stewseo.clients.yelpfusion.businesses.reviews.BusinessReviewsResponse;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessesRequest;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchResponse;
import io.github.stewseo.clients.yelpfusion.businesses.transactions.SearchTransactionRequest;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class YelpFusionBusinessAsyncClient extends ApiClient<YelpFusionTransport, YelpFusionBusinessAsyncClient> {

    public YelpFusionBusinessAsyncClient(YelpFusionTransport transport) {
        super(transport, null);
    }

    public YelpFusionBusinessAsyncClient(YelpFusionTransport transport, @Nullable TransportOptions transportOptions) {
        super(transport, transportOptions);
    }

    @Override
    public YelpFusionBusinessAsyncClient withTransportOptions(@Nullable TransportOptions transportOptions) {
        return new YelpFusionBusinessAsyncClient(this.transport, transportOptions);
    }

    public CompletableFuture<BusinessDetailsResponse> businessDetails(BusinessDetailsRequest request) {
        @SuppressWarnings("unchecked")
        JsonEndpoint<BusinessDetailsRequest, BusinessDetailsResponse, ErrorResponse> endpoint =
                (JsonEndpoint<BusinessDetailsRequest, BusinessDetailsResponse, ErrorResponse>) BusinessDetailsRequest._ENDPOINT;

        return this.transport.performRequestAsync(request, endpoint, this.transportOptions);
    }

    public final CompletableFuture<BusinessDetailsResponse> businessDetails(
            Function<BusinessDetailsRequest.Builder, ObjectBuilder<BusinessDetailsRequest>> fn) {
        return businessDetails(fn.apply(new BusinessDetailsRequest.Builder()).build());
    }

    public <ResultT> CompletableFuture<SearchResponse<ResultT>> searchBusinesses(SearchBusinessesRequest request,
                                                                                 Class<ResultT> resultTClass) {

        @SuppressWarnings("unchecked")
        JsonEndpoint<SearchBusinessesRequest, SearchResponse<ResultT>, ErrorResponse> endpoint =
                (JsonEndpoint<SearchBusinessesRequest, SearchResponse<ResultT>, ErrorResponse>) SearchBusinessesRequest._ENDPOINT;

        endpoint = new EndpointWithResponseMapperAttr<>(endpoint,
                "clients.yelpfusion:Deserializer:_global.searchBusinesses.TDocument", getDeserializer(resultTClass));

        return this.transport.performRequestAsync(request, endpoint, this.transportOptions);
    }

    public final <ResultT> CompletableFuture<SearchResponse<ResultT>> searchBusinesses(

            Function<SearchBusinessesRequest.Builder, ObjectBuilder<SearchBusinessesRequest>> fn, Class<ResultT> tDocumentClass) {

        return searchBusinesses(fn.apply(new SearchBusinessesRequest.Builder()).build(), tDocumentClass);
    }

    public CompletableFuture<BusinessReviewsResponse> businessReviews(BusinessReviewsRequest request) {
        @SuppressWarnings("unchecked")
        JsonEndpoint<BusinessReviewsRequest, BusinessReviewsResponse, ErrorResponse> endpoint =
                (JsonEndpoint<BusinessReviewsRequest, BusinessReviewsResponse, ErrorResponse>) BusinessReviewsRequest._ENDPOINT;

        return this.transport.performRequestAsync(request, endpoint, this.transportOptions);
    }

    public final CompletableFuture<BusinessReviewsResponse> businessReviews(
            Function<BusinessReviewsRequest.Builder, ObjectBuilder<BusinessReviewsRequest>> fn) {
        return businessReviews(fn.apply(new BusinessReviewsRequest.Builder()).build());
    }

    public CompletableFuture<MatchBusinessesResponse> businessMatch(MatchBusinessesRequest request) {
        @SuppressWarnings("unchecked")
        JsonEndpoint<MatchBusinessesRequest, MatchBusinessesResponse, ErrorResponse> endpoint =
                (JsonEndpoint<MatchBusinessesRequest, MatchBusinessesResponse, ErrorResponse>) MatchBusinessesRequest._ENDPOINT;

        return this.transport.performRequestAsync(request, endpoint, this.transportOptions);
    }

    public final CompletableFuture<MatchBusinessesResponse> businessMatch(
            Function<MatchBusinessesRequest.Builder, ObjectBuilder<MatchBusinessesRequest>> fn) {
        return businessMatch(fn.apply(new MatchBusinessesRequest.Builder()).build());
    }

    public <ResultT> CompletableFuture<SearchResponse<ResultT>> searchTransaction(
            SearchTransactionRequest request, Class<ResultT> resultTClass) {

        @SuppressWarnings("unchecked")
        JsonEndpoint<SearchTransactionRequest, SearchResponse<ResultT>, ErrorResponse> endpoint =
                (JsonEndpoint<SearchTransactionRequest, SearchResponse<ResultT>, ErrorResponse>) SearchTransactionRequest._ENDPOINT;
        endpoint = new EndpointWithResponseMapperAttr<>(endpoint,
                "clients.yelpfusion:Deserializer:_global.search.ResultT", getDeserializer(resultTClass));

        return this.transport.performRequestAsync(request, endpoint, this.transportOptions);
    }

    public final<ResultT> CompletableFuture<SearchResponse<ResultT>> searchTransaction(
            Function<SearchTransactionRequest.Builder, ObjectBuilder<SearchTransactionRequest>> fn, Class<ResultT> resultTClass) {
        return searchTransaction(fn.apply(new SearchTransactionRequest.Builder()).build(), resultTClass);
    }
}
