package io.github.stewseo.yelp.fusion.client.yelpfusion;

import io.github.stewseo.lowlevel.restclient.RequestOptions;
import io.github.stewseo.lowlevel.restclient.RestClient;
import io.github.stewseo.yelp.fusion.client.ApiClient;
import io.github.stewseo.yelp.fusion.client._types.ErrorResponse;
import io.github.stewseo.yelp.fusion.client.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.yelp.fusion.client.transport.JsonEndpoint;
import io.github.stewseo.yelp.fusion.client.transport.TransportOptions;
import io.github.stewseo.yelp.fusion.client.transport.YelpFusionTransport;
import io.github.stewseo.yelp.fusion.client.transport.restclient.YelpRestClientTransport;
import io.github.stewseo.yelp.fusion.client.util.ObjectBuilder;
import io.github.stewseo.yelp.fusion.client.yelpfusion.autocomplete.AutoCompleteRequest;
import io.github.stewseo.yelp.fusion.client.yelpfusion.autocomplete.AutoCompleteResponse;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.YelpFusionBusinessClient;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.details.BusinessDetailsRequest;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.details.BusinessDetailsResponse;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.match.BusinessMatchRequest;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.match.BusinessMatchResponse;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.reviews.BusinessReviewsRequest;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.reviews.BusinessReviewsResponse;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.search.BusinessSearchRequest;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.search.BusinessSearchResponse;
import io.github.stewseo.yelp.fusion.client.yelpfusion.categories.GetCategoriesAliasRequest;
import io.github.stewseo.yelp.fusion.client.yelpfusion.categories.GetCategoriesAliasResponse;


import io.github.stewseo.yelp.fusion.client.yelpfusion.categories.GetCategoriesRequest;
import io.github.stewseo.yelp.fusion.client.yelpfusion.categories.GetCategoriesResponse;
import io.github.stewseo.yelp.fusion.client.yelpfusion.categories.YelpFusionCategoriesClient;
import io.github.stewseo.yelp.fusion.client.yelpfusion.events.YelpFusionEventsClient;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.function.Function;



public class YelpFusionClient extends ApiClient<YelpFusionTransport, YelpFusionClient> {

    private static final Logger logger = LoggerFactory.getLogger(YelpFusionClient.class);

    public static final RequestOptions YELP_AUTHORIZATION_HEADER;

    static {

        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
        builder.addHeader("Authorization", "Bearer " + System.getenv("YELP_API_KEY"));
        YELP_AUTHORIZATION_HEADER = builder.build();
    }

    public static YelpFusionClient createClient(String apiKey) throws IOException {
        String hostName = "api.yelp.com";
        int port = 443;
        String scheme = "https";
        HttpHost host = new HttpHost(hostName, port, scheme);

        Header[] defaultHeader  = {new BasicHeader("Authorization", "Bearer " + apiKey)};

        RestClient restClient = RestClient.builder(host)
                .setDefaultHeaders(defaultHeader)
                .build();

        YelpRestClientTransport transport = new YelpRestClientTransport(restClient, new JacksonJsonpMapper());
        return new YelpFusionClient(transport);
    }

    public YelpFusionClient(YelpFusionTransport transport) {
        super(transport, null);
    }

    public YelpFusionClient(YelpFusionTransport transport, TransportOptions transportOptions) {
        super(transport, transportOptions);
    }

    // Businesses Endpoint client
    public YelpFusionBusinessClient businesses() {
        return new YelpFusionBusinessClient(this.transport, this.transportOptions);
    }

    // Categories Endpoint client
    public YelpFusionCategoriesClient categories() {
        return new YelpFusionCategoriesClient(this.transport, this.transportOptions);
    }
    // Events Endpoint client
    public YelpFusionEventsClient events() {
        return new YelpFusionEventsClient(this.transport, this.transportOptions);
    }

    @Override
    public YelpFusionClient withTransportOptions(TransportOptions transportOptions) {
        return new YelpFusionClient(this.transport, transportOptions);
    }

    // Returns results matching a query.
    //Params:
    //fn – a function that initializes a builder to create the BusinessSearchRequest

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

    public GetCategoriesResponse categories(GetCategoriesRequest request) throws Exception {
        @SuppressWarnings("unchecked")
        JsonEndpoint<GetCategoriesRequest, GetCategoriesResponse, ErrorResponse> endpoint =
                (JsonEndpoint<GetCategoriesRequest, GetCategoriesResponse, ErrorResponse>) GetCategoriesRequest._ENDPOINT;
        return this.transport.performRequest(request, endpoint, this.transportOptions);
    }


    public final GetCategoriesResponse categories(
            Function<GetCategoriesRequest.Builder, ObjectBuilder<GetCategoriesRequest>> fn)
            throws Exception {
        return categories(fn.apply(new GetCategoriesRequest.Builder()).build());
    }

    public GetCategoriesAliasResponse categoriesAlias(GetCategoriesAliasRequest request) throws Exception {
        @SuppressWarnings("unchecked")
        JsonEndpoint<GetCategoriesAliasRequest, GetCategoriesAliasResponse, ErrorResponse> endpoint =

                (JsonEndpoint<GetCategoriesAliasRequest, GetCategoriesAliasResponse, ErrorResponse>) GetCategoriesAliasRequest._ENDPOINT;
        return this.transport.performRequest(request, endpoint, this.transportOptions);
    }

    public final GetCategoriesAliasResponse categoriesAlias(
            Function<GetCategoriesAliasRequest.Builder, ObjectBuilder<GetCategoriesAliasRequest>> fn)
            throws Exception {

        return categoriesAlias(fn.apply(new GetCategoriesAliasRequest.Builder()).build());
    }

}

