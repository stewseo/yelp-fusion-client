package io.github.stewseo.clients.yelpfusion;

import io.github.stewseo.clients.transport.restclient.RestClientOptions;
import io.github.stewseo.clients.yelpfusion.businesses.YelpFusionBusinessesClient;
import io.github.stewseo.clients.yelpfusion.categories.YelpFusionCategoriesClient;
import io.github.stewseo.clients.yelpfusion.events.YelpFusionEventsClient;
import io.github.stewseo.clients.yelpfusion.misc.AutoCompleteRequest;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionClientTestCase;
import io.github.stewseo.lowlevel.restclient.RequestOptions;
import io.github.stewseo.lowlevel.restclient.ResponseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.LATITUDE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class YelpFusionClientTest extends YelpFusionClientTestCase {


    private final YelpFusionClient client = new YelpFusionClient(restClientTransport());

    @Test
    public void testClient()  {

        assertThat(client).isNotNull();
    }

    @Test
    public void testWithTransportOptions()  {

        RestClientOptions transportOptions = new RestClientOptions(RequestOptions.DEFAULT);

        YelpFusionClient client = new YelpFusionClient(restClientTransport()).withTransportOptions(transportOptions);

        assertThat(client._transportOptions()).isNotNull();
    }

    @Test
    void testBusinesses()  {

        assertThat(client.businesses()).isInstanceOf(YelpFusionBusinessesClient.class);
    }


    @Test
    void testCategories()  {

        assertThat(client.categories()).isInstanceOf(YelpFusionCategoriesClient.class);
    }

    @Test
    void testEvents() throws IOException {

        assertThat(client.events()).isInstanceOf(YelpFusionEventsClient.class);
    }

    @Test
    void testAutocomplete() throws Exception {

        String expectedUri = "URI [v3/autocomplete?text=textValue]";

        String expectedTextValue = "textValue";

        String expected = buildExpectedResponseExceptionMessage(expectedUri);

        AutoCompleteRequest autoCompleteRequest = AutoCompleteRequest.of(a -> a.latitude(LATITUDE));

        ResponseException responseException = assertThrows(ResponseException.class,
                () -> client.autocomplete(autoCompleteRequest));

        assertThat(responseException.getMessage()).contains("status line [HTTP/1.1 400 Bad Request]\n" +
                "{\"error\": {\"code\": \"VALIDATION_ERROR\", \"description\": \"'' is too short\", \"field\": \"text\", \"instance\": \"\"}}");

        responseException = assertThrows(ResponseException.class,  () -> client.autocomplete(a -> a.latitude(LATITUDE)));

        assertThat(responseException.getMessage()).contains("status line [HTTP/1.1 400 Bad Request]\n" +
                "{\"error\": {\"code\": \"VALIDATION_ERROR\", \"description\": \"'' is too short\", \"field\": \"text\", \"instance\": \"\"}}");


    }
}