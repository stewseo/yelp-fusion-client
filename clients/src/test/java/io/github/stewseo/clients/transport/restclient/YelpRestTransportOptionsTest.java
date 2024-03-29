package io.github.stewseo.clients.transport.restclient;

import io.github.stewseo.clients.json.jsonb.JsonbJsonpMapper;
import io.github.stewseo.clients.transport.TransportOptions;
import io.github.stewseo.clients.transport.restclient.RestClientOptions;
import io.github.stewseo.clients.transport.restclient.RestClientTransport;
import io.github.stewseo.clients.yelpfusion.YelpFusionClient;
import io.github.stewseo.lowlevel.restclient.RestClient;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
@Tag("transport")
class YelpRestTransportOptionsTest {

    private final HttpHost host = new HttpHost("api.yelp.com", 443, "https");

    private final Header[] defaultHeader = {new BasicHeader("Authorization", "Bearer " + System.getenv("YELP_API_KEY"))};

    private final RestClient restClient = RestClient.builder(host)
            .setDefaultHeaders(defaultHeader)
            .build();

    @Test
    void of() throws IOException {

        final RestClientTransport trsp = new RestClientTransport(
                restClient, new JsonbJsonpMapper());

        final YelpFusionClient client = new YelpFusionClient(trsp);

        RestClientOptions restClientOptions = RestClientOptions.of(client._transportOptions());

        assertThat(restClientOptions).isNotNull();
    }

    private final TransportOptions transportOptions = RestClientOptions.initialOptions();

    @Test
    void initialOptions() {
        assertThat(transportOptions).isNotNull();
    }

    @Test
    void restClientRequestOptions() {
        assertThat(transportOptions.headers()).isNotNull();
    }

    @Test
    void headers() {
        assertThat(transportOptions.headers()).isNotNull();
    }

    @Test
    void onWarnings() {

        assertThat(transportOptions.onWarnings()).isNull();
    }

    @Test
    void queryParameters() {
        assertThat(transportOptions.queryParameters()).isNotNull();
    }

}