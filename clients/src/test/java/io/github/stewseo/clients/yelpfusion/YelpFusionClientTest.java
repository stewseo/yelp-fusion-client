package io.github.stewseo.clients.yelpfusion;

import io.github.stewseo.clients.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.clients.transport.YelpFusionTransport;
import io.github.stewseo.clients.transport.restclient.RestClientOptions;
import io.github.stewseo.clients.transport.restclient.RestClientTransport;
import io.github.stewseo.clients.yelpfusion.businesses.YelpFusionBusinessClient;
import io.github.stewseo.clients.yelpfusion.categories.YelpFusionCategoriesClient;
import io.github.stewseo.clients.yelpfusion.events.YelpFusionEventsClient;
import io.github.stewseo.lowlevel.restclient.RequestOptions;
import io.github.stewseo.lowlevel.restclient.RestClient;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class YelpFusionClientTest {

    private YelpFusionTransport transport() throws IOException {

        String hostName = "api.yelp.com";

        int port = 443;

        String scheme = "https";

        HttpHost host = new HttpHost(hostName, port, scheme);

        Header[] defaultHeader =
                {new BasicHeader("Authorization", "Bearer " + System.getenv("YELP_API_KEY"))};

        RestClient restClient = RestClient.builder(host)
                .setDefaultHeaders(defaultHeader)
                .build();

        return new RestClientTransport(restClient, new JacksonJsonpMapper());
    }

    @Test
    void createClient() throws IOException {
        YelpFusionClient client = new YelpFusionClient(transport());
        assertThat(client).isNotNull();
    }

    @Test
    void withTransportOptions() throws IOException {

        RestClientOptions transportOptions = new RestClientOptions(RequestOptions.DEFAULT);

        YelpFusionClient client = new YelpFusionClient(transport()).withTransportOptions(transportOptions);
        assertThat(client._transportOptions()).isNotNull();
    }

    @Test
    void businesses() throws IOException {
        YelpFusionClient client = new YelpFusionClient(transport());
        assertThat(client.businesses()).isInstanceOf(YelpFusionBusinessClient.class);
    }


    @Test
    void categories() throws IOException {
        YelpFusionClient client = new YelpFusionClient(transport());
        assertThat(client.categories()).isInstanceOf(YelpFusionCategoriesClient.class);
    }

    @Test
    void events() throws IOException {
        YelpFusionClient client = new YelpFusionClient(transport());
        assertThat(client.events()).isInstanceOf(YelpFusionEventsClient.class);
    }

    @Test
    void autocomplete() throws Exception {
        YelpFusionClient client = new YelpFusionClient(transport());
        assertThat(client.autocomplete(a -> a.text("text"))).isNotNull();
    }

    @Test
    void testAutocomplete() {
    }
}