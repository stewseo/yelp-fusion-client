package io.github.stewseo.clients.yelpfusion;

import io.github.stewseo.clients.yelpfusion.businesses.YelpFusionBusinessesClient;
import io.github.stewseo.clients.yelpfusion.categories.YelpFusionCategoriesClient;
import io.github.stewseo.clients.yelpfusion.events.YelpFusionEventsClient;
import io.github.stewseo.clients.yelpfusion.misc.AutoCompleteRequest;
import io.github.stewseo.clients.yelpfusion.misc.AutoCompleteResponse;
import io.github.stewseo.clients.ApiClient;
import io.github.stewseo.clients.yelpfusion._types.ErrorResponse;
import io.github.stewseo.clients.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.clients.transport.JsonEndpoint;
import io.github.stewseo.clients.transport.TransportOptions;
import io.github.stewseo.clients.transport.YelpFusionTransport;
import io.github.stewseo.clients.transport.restclient.RestClientTransport;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.lowlevel.restclient.RestClient;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;

import java.io.IOException;
import java.util.function.Function;


public class YelpFusionClient extends ApiClient<YelpFusionTransport, YelpFusionClient> {

    public YelpFusionClient(YelpFusionTransport transport) {
        super(transport, null);
    }

    public YelpFusionClient(YelpFusionTransport transport, TransportOptions transportOptions) {
        super(transport, transportOptions);
    }

    public static YelpFusionClient createClient(String apiKey) throws IOException {

        String hostName = "api.yelp.com";

        int port = 443;

        String scheme = "https";

        HttpHost host = new HttpHost(hostName, port, scheme);

        Header[] defaultHeader = {new BasicHeader("Authorization", "Bearer " + apiKey)};

        RestClient restClient = RestClient.builder(host)
                .setDefaultHeaders(defaultHeader)
                .build();

        RestClientTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        return new YelpFusionClient(transport);
    }

    @Override
    public YelpFusionClient withTransportOptions(TransportOptions transportOptions) {
        return new YelpFusionClient(this.transport, transportOptions);
    }

    /**
     * Businesses Endpoint client
     *
     * @return YelpFusionBusinessClient
     */
    public YelpFusionBusinessesClient businesses() {
        return new YelpFusionBusinessesClient(this.transport, this.transportOptions);
    }

    /**
     * Categories Endpoint client
     *
     * @return YelpFusionCategoriesClient
     */
    public YelpFusionCategoriesClient categories() {
        return new YelpFusionCategoriesClient(this.transport, this.transportOptions);
    }

    /**
     * Events Endpoint client
     *
     * @return YelpFusionEventsClient
     */
    public YelpFusionEventsClient events() {
        return new YelpFusionEventsClient(this.transport, this.transportOptions);
    }

    /**
     * AutoComplete Endpoint
     *
     * @param request AutoCompleteRequest request
     * @return AutoCompleteResponse
     */
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



}

