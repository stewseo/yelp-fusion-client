package io.github.stewseo.yelp.fusion.client.yelpfusion.business;

import io.github.stewseo.yelp.fusion.client.ApiClient;
import io.github.stewseo.yelp.fusion.client._types.ErrorResponse;
import io.github.stewseo.yelp.fusion.client.transport.JsonEndpoint;
import io.github.stewseo.yelp.fusion.client.transport.TransportOptions;
import io.github.stewseo.yelp.fusion.client.transport.YelpFusionTransport;
import io.github.stewseo.yelp.fusion.client.transport.endpoints.EndpointWithResponseMapperAttr;
import io.github.stewseo.yelp.fusion.client.util.ObjectBuilder;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.details.BusinessDetailsRequest;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.details.BusinessDetailsResponse;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.match.BusinessMatchRequest;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.match.BusinessMatchResponse;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.reviews.ReviewsRequest;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.reviews.ReviewsResponse;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.search.SearchBusinessRequest;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.search.SearchBusinessResponse;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.transactions.SearchTransactionRequest;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.transactions.SearchTransactionResponse;

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
        JsonEndpoint<SearchBusinessRequest, SearchBusinessResponse, ErrorResponse> endpoint = (JsonEndpoint<SearchBusinessRequest, SearchBusinessResponse, ErrorResponse>) SearchBusinessRequest._ENDPOINT;
        endpoint = new EndpointWithResponseMapperAttr<>(endpoint,
                "org.example.clients:Deserializer:_global.search.TDocument", getDeserializer(tDocumentClass));
        return this.transport.performRequestAsync(request, endpoint, this.transportOptions);
    }

    public final <TDocument> CompletableFuture<SearchBusinessResponse> search(

            Function<SearchBusinessRequest.Builder, ObjectBuilder<SearchBusinessRequest>> fn, Class<TDocument> tDocumentClass) {

        return search(fn.apply(new SearchBusinessRequest.Builder()).build(), tDocumentClass);
    }

    public CompletableFuture<ReviewsResponse> businessReviews(ReviewsRequest request) throws Exception {
        @SuppressWarnings("unchecked")
        JsonEndpoint<ReviewsRequest, ReviewsResponse, ErrorResponse> endpoint =
                (JsonEndpoint<ReviewsRequest, ReviewsResponse, ErrorResponse>) ReviewsRequest._ENDPOINT;

        return this.transport.performRequestAsync(request, endpoint, this.transportOptions);
    }

    public final CompletableFuture<ReviewsResponse> businessReviews(
            Function<ReviewsRequest.Builder, ObjectBuilder<ReviewsRequest>> fn)
            throws Exception {
        return businessReviews(fn.apply(new ReviewsRequest.Builder()).build());
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
