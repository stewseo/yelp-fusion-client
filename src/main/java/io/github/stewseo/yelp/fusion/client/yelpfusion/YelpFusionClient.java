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
import io.github.stewseo.yelp.fusion.client.yelpfusion.misc.AutoCompleteRequest;
import io.github.stewseo.yelp.fusion.client.yelpfusion.misc.AutoCompleteResponse;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.YelpFusionBusinessClient;


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

    public YelpFusionClient(YelpFusionTransport transport) {
        super(transport, null);
    }

    public YelpFusionClient(YelpFusionTransport transport, TransportOptions transportOptions) {
        super(transport, transportOptions);
    }

    @Override
    public YelpFusionClient withTransportOptions(TransportOptions transportOptions) {
        return new YelpFusionClient(this.transport, transportOptions);
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

    /** Businesses Endpoint client
     *
     *@return YelpFusionBusinessClient
     */
    public YelpFusionBusinessClient businesses() {
        return new YelpFusionBusinessClient(this.transport, this.transportOptions);
    }

    /** Categories Endpoint client
     *
     * @return YelpFusionCategoriesClient
     */
    public YelpFusionCategoriesClient categories() {
        return new YelpFusionCategoriesClient(this.transport, this.transportOptions);
    }

    /** Events Endpoint client
     *
     * @return YelpFusionEventsClient
     */
    public YelpFusionEventsClient events() {
        return new YelpFusionEventsClient(this.transport, this.transportOptions);
    }

    /** AutoComplete Endpoint
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

