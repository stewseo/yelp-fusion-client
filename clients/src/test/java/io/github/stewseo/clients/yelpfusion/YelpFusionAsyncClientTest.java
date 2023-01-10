package io.github.stewseo.clients.yelpfusion;

import io.github.stewseo.clients.transport.restclient.RestClientOptions;
import io.github.stewseo.clients.yelpfusion.businesses.YelpFusionBusinessAsyncClient;
import io.github.stewseo.clients.yelpfusion.categories.YelpFusionCategoriesAsyncClient;
import io.github.stewseo.clients.yelpfusion.events.YelpFusionEventsAsyncClient;
import io.github.stewseo.clients.yelpfusion.misc.AutoCompleteRequest;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionClientTestCase;
import io.github.stewseo.lowlevel.restclient.RequestOptions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


public class YelpFusionAsyncClientTest extends YelpFusionClientTestCase {

    YelpFusionAsyncClient asyncClient = new YelpFusionAsyncClient(restClientTransport());

    @Test
    public void testClient() {
        assertThat(asyncClient).isNotNull();
        assertThat(asyncClient).isInstanceOf(YelpFusionAsyncClient.class);
    }

    @Test
    public void testWithTransportOptions() {

        RestClientOptions transportOptions = new RestClientOptions(RequestOptions.DEFAULT);

        YelpFusionAsyncClient asyncClient =
                new YelpFusionAsyncClient(restClientTransport()).withTransportOptions(transportOptions);

        assertThat(asyncClient._transportOptions()).isNotNull();
    }

    @Test
    void testClients() throws IOException {

        assertThat(asyncClient.businesses()).isInstanceOf(YelpFusionBusinessAsyncClient.class);

        assertThat(asyncClient.categories()).isInstanceOf(YelpFusionCategoriesAsyncClient.class);

        assertThat(asyncClient.events()).isInstanceOf(YelpFusionEventsAsyncClient.class);
    }

    @Test
    void testAutocomplete() throws Exception {

        AutoCompleteRequest autoCompleteRequest = AutoCompleteRequest.of(a -> a.text("textValue"));

        assertThat(asyncClient.autocomplete(autoCompleteRequest)).isNotNull();

        assertThat(asyncClient.autocomplete(a -> a.text("text"))).isNotNull();
    }

}
