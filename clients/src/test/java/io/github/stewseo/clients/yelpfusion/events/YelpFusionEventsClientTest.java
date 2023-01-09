package io.github.stewseo.clients.yelpfusion.events;

import io.github.stewseo.clients.transport.restclient.RestClientTransport;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionClientTestCase;
import io.github.stewseo.lowlevel.restclient.ResponseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.github.stewseo.clients.yelpfusion._types.TestData.LOCALE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class YelpFusionEventsClientTest extends YelpFusionClientTestCase {

    @Test
    void withTransportOptions() throws IOException {

        try(RestClientTransport restClientTransport = restClientTransport()) {

            YelpFusionEventsClient client = new YelpFusionEventsClient(restClientTransport)
                    .withTransportOptions(restClientTransport.options()
                    );

            assertThat(client._transportOptions()).isNotNull();
        }
    }

    private final YelpFusionEventsClient eventsClient = new YelpFusionEventsClient(restClientTransport());

    // if rate limited
    @Test
    void testSearchEvents() throws Exception {

        String expectedError = "" +
                "method [GET], " +
                "host [https://api.yelp.com:443], " +
                "URI [v3/events?locale=en_US], " +
                "status line [HTTP/1.1 429 Too Many Requests]\n" +
                "{\"error\": {\"code\": \"ACCESS_LIMIT_REACHED\", " +
                "\"description\": \"You've reached the access limit for this client. " +
                "See instructions for requesting a higher access limit at https://www.yelp.com/developers/documentation/v3/rate_limiting\"}}";

        Exception exception = assertThrows(ResponseException.class,
                () -> eventsClient.search(s -> s.locale(LOCALE))
        );

        assertThat(exception.getMessage()).isEqualTo(expectedError);
    }

    // if rate limited
    @Test
    void testFeaturedEvents() throws Exception {
        String expected = "" +
                "method [GET], " +
                "host [https://api.yelp.com:443], " +
                "URI [v3/events/featured?locale=en_US], " +
                "status line [HTTP/1.1 429 Too Many Requests]\n" +
                    "{\"error\": " +
                        "{\"code\": \"ACCESS_LIMIT_REACHED\", " +
                        "\"description\": \"You've reached the access limit for this client. " +
                        "See instructions for requesting a higher access limit at https://www.yelp.com/developers/documentation/v3/rate_limiting\"}}";

        Exception exception = assertThrows(ResponseException.class,
                () -> eventsClient.featured(s -> s.locale(LOCALE))
        );

        assertThat(exception.getMessage()).isEqualTo(expected);

    }
}