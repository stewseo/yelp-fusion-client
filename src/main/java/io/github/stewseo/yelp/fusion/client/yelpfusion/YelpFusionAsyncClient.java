package io.github.stewseo.yelp.fusion.client.yelpfusion;


import io.github.stewseo.yelp.fusion.client.transport.endpoints.EndpointWithResponseMapperAttr;
import io.github.stewseo.yelp.fusion.client._types.ErrorResponse;
import io.github.stewseo.yelp.fusion.client.transport.JsonEndpoint;
import io.github.stewseo.yelp.fusion.client.transport.TransportOptions;
import io.github.stewseo.yelp.fusion.client.util.ObjectBuilder;
import io.github.stewseo.yelp.fusion.client.ApiClient;

import io.github.stewseo.yelp.fusion.client.transport.YelpFusionTransport;
import io.github.stewseo.yelp.fusion.client.yelpfusion.autocomplete.AutoCompleteRequest;
import io.github.stewseo.yelp.fusion.client.yelpfusion.autocomplete.AutoCompleteResponse;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business_details.BusinessDetailsRequest;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business_details.BusinessDetailsResponse;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business_reviews.BusinessReviewsRequest;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business_reviews.BusinessReviewsResponse;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business_search.BusinessSearchRequest;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business_search.BusinessSearchResponse;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business_match.BusinessMatchRequest;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business_match.BusinessMatchResponse;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class YelpFusionAsyncClient extends ApiClient<YelpFusionTransport, YelpFusionAsyncClient> {

    public YelpFusionAsyncClient(YelpFusionTransport transport) {
        super(transport, null);
    }

    public YelpFusionAsyncClient(YelpFusionTransport transport, @Nullable TransportOptions transportOptions) {
        super(transport, transportOptions);
    }

    @Override
    public YelpFusionAsyncClient withTransportOptions(@Nullable TransportOptions transportOptions) {
        return new YelpFusionAsyncClient(this.transport, transportOptions);
    }

    public <TDocument> CompletableFuture<BusinessSearchResponse> search(BusinessSearchRequest request,
                                                                        Class<TDocument> tDocumentClass) {
        @SuppressWarnings("unchecked")
        JsonEndpoint<BusinessSearchRequest, BusinessSearchResponse, ErrorResponse> endpoint = (JsonEndpoint<BusinessSearchRequest, BusinessSearchResponse, ErrorResponse>) BusinessSearchRequest._ENDPOINT;
        endpoint = new EndpointWithResponseMapperAttr<>(endpoint,
                "org.example.clients:Deserializer:_global.search.TDocument", getDeserializer(tDocumentClass));
        return this.transport.performRequestAsync(request, endpoint, this.transportOptions);
    }

    public final <TDocument> CompletableFuture<BusinessSearchResponse> search(

            Function<BusinessSearchRequest.Builder, ObjectBuilder<BusinessSearchRequest>> fn, Class<TDocument> tDocumentClass) {

        return search(fn.apply(new BusinessSearchRequest.Builder()).build(), tDocumentClass);
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

    public CompletableFuture<AutoCompleteResponse> autocomplete(AutoCompleteRequest request) throws Exception {
        @SuppressWarnings("unchecked")
        JsonEndpoint<AutoCompleteRequest, AutoCompleteResponse, ErrorResponse> endpoint =
                (JsonEndpoint<AutoCompleteRequest, AutoCompleteResponse, ErrorResponse>) AutoCompleteRequest._ENDPOINT;

        return this.transport.performRequestAsync(request, endpoint, this.transportOptions);
    }

    public final CompletableFuture<AutoCompleteResponse> autocomplete(
            Function<AutoCompleteRequest.Builder, ObjectBuilder<AutoCompleteRequest>> fn)
            throws Exception {
        return autocomplete(fn.apply(new AutoCompleteRequest.Builder()).build());
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

}
