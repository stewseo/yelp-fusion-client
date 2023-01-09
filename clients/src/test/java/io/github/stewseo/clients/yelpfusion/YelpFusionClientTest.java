package io.github.stewseo.clients.yelpfusion;

import io.github.stewseo.clients.transport.restclient.RestClientOptions;
import io.github.stewseo.clients.yelpfusion.businesses.YelpFusionBusinessClient;
import io.github.stewseo.clients.yelpfusion.categories.YelpFusionCategoriesClient;
import io.github.stewseo.clients.yelpfusion.events.YelpFusionEventsClient;
import io.github.stewseo.clients.yelpfusion.misc.AutoCompleteRequest;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionClientTestCase;
import io.github.stewseo.lowlevel.restclient.RequestOptions;
import io.github.stewseo.lowlevel.restclient.ResponseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class YelpFusionClientTest extends YelpFusionClientTestCase {


    private final YelpFusionClient client = new YelpFusionClient(restClientTransport());

    @Test
    void createClient() throws IOException {

        assertThat(client).isNotNull();
    }

    @Test
    void withTransportOptions() throws IOException {

        RestClientOptions transportOptions = new RestClientOptions(RequestOptions.DEFAULT);

        YelpFusionClient client = new YelpFusionClient(restClientTransport()).withTransportOptions(transportOptions);

        assertThat(client._transportOptions()).isNotNull();
    }

    @Test
    void businesses() throws IOException {

        assertThat(client.businesses()).isInstanceOf(YelpFusionBusinessClient.class);
    }


    @Test
    void test() throws IOException {

        assertThat(client.categories()).isInstanceOf(YelpFusionCategoriesClient.class);
    }

    @Test
    void testEvents() throws IOException {

        assertThat(client.events()).isInstanceOf(YelpFusionEventsClient.class);
    }

    @Test
    void testAutocomplete() throws Exception {

        AutoCompleteRequest autoCompleteRequest = AutoCompleteRequest.of(a -> a.text("textValue"));

        Exception exception = assertThrows(Exception.class,
                () -> client.autocomplete(autoCompleteRequest)
        );

        assertThat(exception).isInstanceOf(ResponseException.class);

        exception = assertThrows(Exception.class,
                () -> client.autocomplete(a -> a.text("text"))
        );

        assertThat(exception).isInstanceOf(ResponseException.class);
    }
}