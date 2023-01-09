package io.github.stewseo.clients.yelpfusion.businesses;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.stewseo.clients.transport.restclient.RestClientTransport;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionClientTestCase;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static io.github.stewseo.clients.yelpfusion._types.TestData.ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class YelpFusionBusinessAsyncClientTest extends YelpFusionClientTestCase {

    @Test
    void withTransportOptions() throws IOException {

        try(RestClientTransport restClientTransport = restClientTransport()) {

            YelpFusionBusinessAsyncClient client = new YelpFusionBusinessAsyncClient(restClientTransport)
                    .withTransportOptions(restClientTransport.options()
                    );

            assertThat(client._transportOptions()).isNotNull();
        }
    }

    private final YelpFusionBusinessAsyncClient businessAsyncClient = new YelpFusionBusinessAsyncClient(restClientTransport());

    // if rate limited
    @Test
    void testSearchEvents() throws Exception {

        String expectedError = "io.github.stewseo.lowlevel.restclient.ResponseException: method [GET], host [https://api.yelp.com:443], URI [v3/businesses/id], status line [HTTP/1.1 429 Too Many Requests]\n" +
                "{\"error\": {\"code\": \"ACCESS_LIMIT_REACHED\", \"description\": \"You've reached the access limit for this client. See instructions for requesting a higher access limit at https://www.yelp.com/developers/documentation/v3/rate_limiting\"}}";

        Exception exception = assertThrows(ExecutionException.class,
                () -> businessAsyncClient.businessDetails(s -> s.id(ID)
                )
                        .get()
        );

        assertThat(exception.getMessage()).isEqualTo(expectedError);
    }

    // if rate limited
    @Test
    void testFeaturedEvents() throws Exception {

        String expected = "io.github.stewseo.lowlevel.restclient.ResponseException: method [GET], host [https://api.yelp.com:443], URI [v3/businesses/search?location=locationValue], status line [HTTP/1.1 429 Too Many Requests]\n" +
                "{\"error\": {\"code\": \"ACCESS_LIMIT_REACHED\", \"description\": \"You've reached the access limit for this client. See instructions for requesting a higher access limit at https://www.yelp.com/developers/documentation/v3/rate_limiting\"}}";


        Exception exception = assertThrows(ExecutionException.class,
                () -> businessAsyncClient.search(s -> s
                        .location(List.of("locationValue")
                        )
                        , ObjectNode.class)
                        .get()
        );

        assertThat(exception.getMessage()).isEqualTo(expected);

    }
}
