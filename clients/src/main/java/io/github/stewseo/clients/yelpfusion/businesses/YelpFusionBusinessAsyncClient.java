package io.github.stewseo.clients.yelpfusion.businesses;

import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetailsRequest;
import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetailsResponse;
import io.github.stewseo.clients.yelpfusion.businesses.match.BusinessMatchResponse;
import io.github.stewseo.clients.yelpfusion.businesses.reviews.BusinessReviewsRequest;
import io.github.stewseo.clients.yelpfusion.businesses.reviews.BusinessReviewsResponse;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessRequest;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessResponse;
import io.github.stewseo.clients.yelpfusion.businesses.transactions.SearchTransactionResponse;
import io.github.stewseo.clients.ApiClient;
import io.github.stewseo.clients.yelpfusion._types.ErrorResponse;
import io.github.stewseo.clients.transport.JsonEndpoint;
import io.github.stewseo.clients.transport.TransportOptions;
import io.github.stewseo.clients.transport.YelpFusionTransport;
import io.github.stewseo.clients.transport.endpoints.EndpointWithResponseMapperAttr;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.yelpfusion.businesses.match.BusinessMatchRequest;
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

    public CompletableFuture<BusinessDetailsResponse> businessDetails(BusinessDetailsRequest request) throws Exception {
        @SuppressWarnings("unchecked")
        JsonEndpoint<BusinessDetailsRequest, BusinessDetailsResponse, ErrorResponse> endpoint =
                (JsonEndpoint<BusinessDetailsRequest, BusinessDetailsResponse, ErrorResponse>) BusinessDetailsRequest._ENDPOINT;

        return this.transport.performRequestAsync(request, endpoint, this.transportOptions);
    }

    public final CompletableFuture<BusinessDetailsResponse> businessDetails(
            Function<BusinessDetailsRequest.Builder, ObjectBuilder<BusinessDetailsRequest>> fn)
            throws Exception {
        return businessDetails(fn.apply(new BusinessDetailsRequest.Builder()).build());
    }

    public <TDocument> CompletableFuture<SearchBusinessResponse> search(SearchBusinessRequest request,
                                                                        Class<TDocument> tDocumentClass) {
        @SuppressWarnings("unchecked")
        JsonEndpoint<SearchBusinessRequest, SearchBusinessResponse, ErrorResponse> endpoint = 
                (JsonEndpoint<SearchBusinessRequest, SearchBusinessResponse, ErrorResponse>) SearchBusinessRequest._ENDPOINT;
        endpoint = new EndpointWithResponseMapperAttr<>(endpoint,
                "clients.yelpfusion:Deserializer:_global.search.TDocument", getDeserializer(tDocumentClass));
        return this.transport.performRequestAsync(request, endpoint, this.transportOptions);
    }

    public final <TDocument> CompletableFuture<SearchBusinessResponse> search(

            Function<SearchBusinessRequest.Builder, ObjectBuilder<SearchBusinessRequest>> fn, Class<TDocument> tDocumentClass) {

        return search(fn.apply(new SearchBusinessRequest.Builder()).build(), tDocumentClass);
    }

    public CompletableFuture<BusinessReviewsResponse> businessReviews(BusinessReviewsRequest request) throws Exception {
        @SuppressWarnings("unchecked")
        JsonEndpoint<BusinessReviewsRequest, BusinessReviewsResponse, ErrorResponse> endpoint =
                (JsonEndpoint<BusinessReviewsRequest, BusinessReviewsResponse, ErrorResponse>) BusinessReviewsRequest._ENDPOINT;

        return this.transport.performRequestAsync(request, endpoint, this.transportOptions);
    }

    public final CompletableFuture<BusinessReviewsResponse> businessReviews(
            Function<BusinessReviewsRequest.Builder, ObjectBuilder<BusinessReviewsRequest>> fn)
            throws Exception {
        return businessReviews(fn.apply(new BusinessReviewsRequest.Builder()).build());
    }

    public CompletableFuture<BusinessMatchResponse> businessMatch(BusinessMatchRequest request) throws Exception {
        @SuppressWarnings("unchecked")
        JsonEndpoint<BusinessMatchRequest, BusinessMatchResponse, ErrorResponse> endpoint =
                (JsonEndpoint<BusinessMatchRequest, BusinessMatchResponse, ErrorResponse>) BusinessMatchRequest._ENDPOINT;

        return this.transport.performRequestAsync(request, endpoint, this.transportOptions);
    }

    public final CompletableFuture<BusinessMatchResponse> businessMatch(
            Function<BusinessMatchRequest.Builder, ObjectBuilder<BusinessMatchRequest>> fn)
            throws Exception {
        return businessMatch(fn.apply(new BusinessMatchRequest.Builder()).build());
    }

    public CompletableFuture<SearchTransactionResponse> searchTransaction(SearchTransactionRequest request) throws Exception {
        @SuppressWarnings("unchecked")
        JsonEndpoint<SearchTransactionRequest, SearchTransactionResponse, ErrorResponse> endpoint =
                (JsonEndpoint<SearchTransactionRequest, SearchTransactionResponse, ErrorResponse>) SearchTransactionRequest._ENDPOINT;

        return this.transport.performRequestAsync(request, endpoint, this.transportOptions);
    }

    public final CompletableFuture<SearchTransactionResponse> searchTransaction(
            Function<SearchTransactionRequest.Builder, ObjectBuilder<SearchTransactionRequest>> fn)
            throws Exception {
        return searchTransaction(fn.apply(new SearchTransactionRequest.Builder()).build());
    }
}
