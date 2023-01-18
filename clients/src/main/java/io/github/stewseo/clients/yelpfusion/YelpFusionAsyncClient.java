package io.github.stewseo.clients.yelpfusion;


import io.github.stewseo.clients.ApiClient;
import io.github.stewseo.clients.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.clients.transport.JsonEndpoint;
import io.github.stewseo.clients.transport.TransportOptions;
import io.github.stewseo.clients.transport.YelpFusionTransport;
import io.github.stewseo.clients.transport.restclient.RestClientTransport;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.yelpfusion._types.ErrorResponse;
import io.github.stewseo.clients.yelpfusion.businesses.YelpFusionBusinessAsyncClient;
import io.github.stewseo.clients.yelpfusion.categories.YelpFusionCategoriesAsyncClient;
import io.github.stewseo.clients.yelpfusion.events.YelpFusionEventsAsyncClient;
import io.github.stewseo.clients.yelpfusion.misc.AutoCompleteRequest;
import io.github.stewseo.clients.yelpfusion.misc.AutoCompleteResponse;
import io.github.stewseo.lowlevel.restclient.RestClient;
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

    public static YelpFusionAsyncClient createAsyncClient(String apiKey) throws IOException {
        String hostName = "api.yelp.com";
        int port = 443;
        String scheme = "https";
        HttpHost host = new HttpHost(hostName, port, scheme);

        Header[] defaultHeader = {new BasicHeader("Authorization", "Bearer " + apiKey)};

        RestClient restClient = RestClient.builder(host)
                .setDefaultHeaders(defaultHeader)
                .build();

        RestClientTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        return new YelpFusionAsyncClient(transport);
    }

    @Override
    public YelpFusionAsyncClient withTransportOptions(@Nullable TransportOptions transportOptions) {
        return new YelpFusionAsyncClient(this.transport, transportOptions);
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
