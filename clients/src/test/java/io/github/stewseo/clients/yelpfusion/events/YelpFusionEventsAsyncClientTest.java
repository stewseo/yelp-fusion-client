package io.github.stewseo.clients.yelpfusion.events;

import io.github.stewseo.clients.transport.restclient.RestClientTransport;
import io.github.stewseo.clients.yelpfusion.YelpFusionTest;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionClientTestCase;
import org.junit.jupiter.api.Tag;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.ErrorMessages.VALIDATION_ERROR_DOES_NOT_MATCH;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.TestReqVars.BAD_LOCALE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@Tag("events")
class YelpFusionEventsAsyncClientTest extends YelpFusionClientTestCase {

    @YelpFusionTest
    public void testWithTransportOptions() {

        try(RestClientTransport restClientTransport = restClientTransport()) {

            YelpFusionEventsAsyncClient client = new YelpFusionEventsAsyncClient(restClientTransport)
                    .withTransportOptions(restClientTransport.options()
                    );

            assertThat(client._transportOptions()).isNotNull();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final YelpFusionEventsAsyncClient eventsClient = new YelpFusionEventsAsyncClient(restClientTransport());

    @YelpFusionTest
    void testSearchEvents() {
        String expectedUri = "URI [v3/events?locale=en_US]";

        String expected = buildExpectedExecutionExceptionMessage(expectedUri);

        ExecutionException executionException =
                assertThrows(ExecutionException.class, () -> eventsClient.search(s -> s.locale(BAD_LOCALE)).get());

        assertThat(executionException.getMessage()).contains(VALIDATION_ERROR_DOES_NOT_MATCH);

    }


    @YelpFusionTest
    void testFeaturedEvents() {

        String expectedUri = "URI [v3/events/featured?locale=en_US]";

        String expected = buildExpectedExecutionExceptionMessage(expectedUri);

        ExecutionException executionException = assertThrows(ExecutionException.class,
                () -> eventsClient.featured(s -> s.locale(BAD_LOCALE)).get()
        );

        assertThat(executionException.getMessage()).contains(VALIDATION_ERROR_DOES_NOT_MATCH);
    }
}