package io.github.stewseo.clients.yelpfusion.businesses;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.stewseo.clients.transport.restclient.RestClientTransport;
import io.github.stewseo.clients.yelpfusion._types.test_constants.TestData;
import io.github.stewseo.clients.yelpfusion.businesses.YelpFusionBusinessAsyncClient;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionClientTestCase;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.ID;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.ErrorCodes.BUSINESS_NOT_FOUND;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.ErrorCodes.LOCATION_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class YelpFusionBusinessAsyncClientTest extends YelpFusionClientTestCase {

    @Test
    public void testWithTransportOptions() {

        try(RestClientTransport restClientTransport = restClientTransport()) {

            YelpFusionBusinessAsyncClient client = new YelpFusionBusinessAsyncClient(restClientTransport)
                    .withTransportOptions(restClientTransport.options()
                    );

            assertThat(client._transportOptions()).isNotNull();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final YelpFusionBusinessAsyncClient businessAsyncClient = new YelpFusionBusinessAsyncClient(restClientTransport());

    // if rate limited
    @Test
    void testSearchEvents() throws Exception {

        Exception exception = assertThrows(ExecutionException.class,
                () -> businessAsyncClient.businessDetails(s -> s.id(TestData.ID)
                        )
                        .get()
        );

        assertThat(exception.getMessage()).contains(BUSINESS_NOT_FOUND);
    }

    // if rate limited
    @Test
    void testFeaturedEvents() throws Exception {

        Exception exception = assertThrows(ExecutionException.class,
                () -> businessAsyncClient.search(s -> s
                                        .location(List.of("locationValue")
                                        )
                                , ObjectNode.class)
                        .get()
        );

        assertThat(exception.getMessage()).contains(LOCATION_NOT_FOUND);
    }
}
