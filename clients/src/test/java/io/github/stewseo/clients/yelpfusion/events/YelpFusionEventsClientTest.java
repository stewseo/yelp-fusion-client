package io.github.stewseo.clients.yelpfusion.events;

import io.github.stewseo.clients.transport.restclient.RestClientTransport;
import io.github.stewseo.clients.yelpfusion.YelpFusionTest;
import io.github.stewseo.clients.yelpfusion.events.featured.FeaturedEventResponse;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionClientTestCase;
import io.github.stewseo.lowlevel.restclient.ResponseException;
import org.junit.jupiter.api.Tag;

import java.io.IOException;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.ErrorMessages.LOCATION_MISSING_ERROR;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.ErrorMessages.VALIDATION_ERROR_DOES_NOT_MATCH;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.TestReqVars.BAD_LOCALE;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.LATITUDE;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.TestReqVars.LOCALE;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.LONGITUDE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Tag("events")
class YelpFusionEventsClientTest extends YelpFusionClientTestCase {

    @YelpFusionTest
    public void testClient() {

    }

    @YelpFusionTest
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
    @YelpFusionTest
    void testSearchEvents() {

        ResponseException responseException = assertThrows(ResponseException.class,
                () -> eventsClient.search(s -> s.locale(BAD_LOCALE)));

        assertThat(responseException.getMessage()).contains(VALIDATION_ERROR_DOES_NOT_MATCH);

    }

    // if rate limited
    @YelpFusionTest
    void testFeaturedEvents() {

        String expectedUri = "URI [v3/events/featured?locale=en_US]";

        String expected = buildExpectedResponseExceptionMessage(expectedUri);

        ResponseException responseException = assertThrows(ResponseException.class,
                () -> eventsClient.featured(s -> s.locale(LOCALE))
        );

        assertThat(responseException.getMessage()).contains(LOCATION_MISSING_ERROR);
    }

    @YelpFusionTest
    void testFeaturedEventsBiFunctionParam() {

        try {
            FeaturedEventResponse response = eventsClient.featured(s -> s
                    .locale(LOCALE)
                    .latitude(LATITUDE)
                    .longitude(LONGITUDE)
            );
            assertThat(response).isNotNull();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}