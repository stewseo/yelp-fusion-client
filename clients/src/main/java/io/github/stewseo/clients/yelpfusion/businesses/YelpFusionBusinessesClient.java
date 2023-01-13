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
import io.github.stewseo.clients.yelpfusion.businesses.match.BusinessMatchRequest;
import io.github.stewseo.clients.yelpfusion.businesses.match.MatchBusinessesResponse;
import io.github.stewseo.clients.yelpfusion.businesses.reviews.BusinessReviewsRequest;
import io.github.stewseo.clients.yelpfusion.businesses.reviews.BusinessReviewsResponse;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessRequest;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchResponse;
import io.github.stewseo.clients.yelpfusion.businesses.transactions.SearchTransactionRequest;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.function.Function;

public class YelpFusionBusinessesClient extends ApiClient<YelpFusionTransport, YelpFusionBusinessesClient> {

    public YelpFusionBusinessesClient(YelpFusionTransport transport) {
        super(transport, null);
    }

    public YelpFusionBusinessesClient(YelpFusionTransport transport, @Nullable TransportOptions transportOptions) {
        super(transport, transportOptions);
    }

    @Override
    public YelpFusionBusinessesClient withTransportOptions(@Nullable TransportOptions transportOptions) {
        return new YelpFusionBusinessesClient(this.transport, transportOptions);
    }

    /**
     * Returns results matching
     *
     * @param fn
     *            a function that initializes a builder to create the
     *            {@link SearchTransactionRequest}
     */
    public final <ResultT> SearchResponse<ResultT> searchTransactions(
            Function<SearchTransactionRequest.Builder, ObjectBuilder<SearchTransactionRequest>>fn, Class<ResultT> resultTClass)
            throws IOException {

        return this.searchTransactions(fn.apply(new SearchTransactionRequest.Builder()).build(), resultTClass);
    }

    public final <ResultT> SearchResponse<ResultT> searchTransactions(SearchTransactionRequest request, Class<ResultT> resultTClass)
            throws IOException {

        @SuppressWarnings("unchecked")
        JsonEndpoint<SearchTransactionRequest, SearchResponse<ResultT>, ErrorResponse> endpoint =
                (JsonEndpoint<SearchTransactionRequest, SearchResponse<ResultT>, ErrorResponse>) SearchTransactionRequest._ENDPOINT;

        endpoint = new EndpointWithResponseMapperAttr<>(endpoint,
                "io.github.stewseo.clients:Deserializer:_global.searchBusinesses.ResultT", getDeserializer(resultTClass));

        return this.transport.performRequest(request, endpoint, this.transportOptions);
    }

    /**
     * Returns results matching
     *
     * @param fn
     *            a function that initializes a builder to create the
     *            {@link SearchBusinessRequest}
     */
    public final <ResultT> SearchResponse<ResultT> searchBusinesses(
            Function<SearchBusinessRequest.Builder, ObjectBuilder<SearchBusinessRequest>>fn, Class<ResultT> resultTClass)
            throws IOException {

        return this.searchBusinesses(fn.apply(new SearchBusinessRequest.Builder()).build(), resultTClass);
    }

    public final <ResultT> SearchResponse<ResultT> searchBusinesses(SearchBusinessRequest request, Class<ResultT> resultTClass)
            throws IOException {

        @SuppressWarnings("unchecked")
        JsonEndpoint<SearchBusinessRequest, SearchResponse<ResultT>, ErrorResponse> endpoint =
                (JsonEndpoint<SearchBusinessRequest, SearchResponse<ResultT>, ErrorResponse>) SearchBusinessRequest._ENDPOINT;

        endpoint = new EndpointWithResponseMapperAttr<>(endpoint,
                "io.github.stewseo.clients:Deserializer:_global.searchBusinesses.ResultT", getDeserializer(resultTClass));

        return this.transport.performRequest(request, endpoint, this.transportOptions);
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

    public MatchBusinessesResponse matchBusinesses(BusinessMatchRequest request) throws Exception {
        @SuppressWarnings("unchecked")
        JsonEndpoint<BusinessMatchRequest, MatchBusinessesResponse, ErrorResponse> endpoint =
                (JsonEndpoint<BusinessMatchRequest, MatchBusinessesResponse, ErrorResponse>) BusinessMatchRequest._ENDPOINT;

        return this.transport.performRequest(request, endpoint, this.transportOptions);
    }

    public final MatchBusinessesResponse matchBusinesses(
            Function<BusinessMatchRequest.Builder, ObjectBuilder<BusinessMatchRequest>> fn)
            throws Exception {
        return matchBusinesses(fn.apply(new BusinessMatchRequest.Builder()).build());
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


}
