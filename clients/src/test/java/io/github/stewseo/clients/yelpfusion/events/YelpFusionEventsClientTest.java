package io.github.stewseo.clients.yelpfusion.events;

import io.github.stewseo.clients.transport.restclient.RestClientTransport;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionClientTestCase;
import io.github.stewseo.lowlevel.restclient.ResponseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.ErrorMessages.LOCATION_MISSING_ERROR;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.ErrorMessages.VALIDATION_SPECIFY_LOCATION_ERROR;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.ErrorMessages.VALIDATION_ERROR_DOES_NOT_MATCH;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.BAD_LOCALE;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.LOCALE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class YelpFusionEventsClientTest extends YelpFusionClientTestCase {

    @Test
    public void testClient() {

    }

    @Test
    public void testWithTransportOptions() {

        try(RestClientTransport restClientTransport = restClientTransport()) {

            YelpFusionEventsClient client = new YelpFusionEventsClient(restClientTransport)
                    .withTransportOptions(restClientTransport.options()
                    );

            assertThat(client._transportOptions()).isNotNull();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final YelpFusionEventsClient eventsClient = new YelpFusionEventsClient(restClientTransport());

    // if rate limited
    @Test
    void testSearchEvents() {
        String expectedUri = "URI [v3/events?locale=en_US]";

        String expected = buildExpectedResponseExceptionMessage(expectedUri);

        ResponseException responseException = assertThrows(ResponseException.class,
                () -> eventsClient.search(s -> s.locale(BAD_LOCALE)));

        assertThat(responseException.getMessage()).contains(VALIDATION_ERROR_DOES_NOT_MATCH);

    }

    // if rate limited
    @Test
    void testFeaturedEvents() {

        String expectedUri = "URI [v3/events/featured?locale=en_US]";

        String expected = buildExpectedResponseExceptionMessage(expectedUri);

        ResponseException responseException = assertThrows(ResponseException.class,
                () -> eventsClient.featured(s -> s.locale(LOCALE))
        );

        assertThat(responseException.getMessage()).contains(LOCATION_MISSING_ERROR);
    }
}