package io.github.stewseo.yelp.fusion.client.yelpfusion;


import io.github.stewseo.lowlevel.restclient.RestClient;
import io.github.stewseo.yelp.fusion.client.ApiClient;
import io.github.stewseo.yelp.fusion.client._types.ErrorResponse;
import io.github.stewseo.yelp.fusion.client.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.yelp.fusion.client.transport.JsonEndpoint;
import io.github.stewseo.yelp.fusion.client.transport.TransportOptions;
import io.github.stewseo.yelp.fusion.client.transport.YelpFusionTransport;
import io.github.stewseo.yelp.fusion.client.transport.restclient.YelpRestClientTransport;
import io.github.stewseo.yelp.fusion.client.util.ObjectBuilder;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.YelpFusionBusinessAsyncClient;
import io.github.stewseo.yelp.fusion.client.yelpfusion.categories.YelpFusionCategoriesAsyncClient;
import io.github.stewseo.yelp.fusion.client.yelpfusion.events.YelpFusionEventsAsyncClient;
import io.github.stewseo.yelp.fusion.client.yelpfusion.misc.AutoCompleteRequest;
import io.github.stewseo.yelp.fusion.client.yelpfusion.misc.AutoCompleteResponse;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;

import javax.annotation.Nullable;
import java.io.IOException;
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

    public static YelpFusionAsyncClient createAsyncClient(String apiKey) throws IOException {
        String hostName = "api.yelp.com";
        int port = 443;
        String scheme = "https";
        HttpHost host = new HttpHost(hostName, port, scheme);

        Header[] defaultHeader  = {new BasicHeader("Authorization", "Bearer " + apiKey)};

        RestClient restClient = RestClient.builder(host)
                .setDefaultHeaders(defaultHeader)
                .build();

        YelpRestClientTransport transport = new YelpRestClientTransport(restClient, new JacksonJsonpMapper());
        return new YelpFusionAsyncClient(transport);
    }

    // Businesses Endpoint client
    public YelpFusionBusinessAsyncClient businesses() {
        return new YelpFusionBusinessAsyncClient(this.transport, this.transportOptions);
    }

    // Categories Endpoint client
    public YelpFusionCategoriesAsyncClient categories() {
        return new YelpFusionCategoriesAsyncClient(this.transport, this.transportOptions);
    }

    // Events Endpoint client
    public YelpFusionEventsAsyncClient events() {
        return new YelpFusionEventsAsyncClient(this.transport, this.transportOptions);
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

}
