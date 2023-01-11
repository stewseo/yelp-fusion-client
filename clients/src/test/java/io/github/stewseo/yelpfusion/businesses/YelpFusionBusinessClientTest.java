package io.github.stewseo.yelpfusion.businesses;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.stewseo.clients.transport.restclient.RestClientTransport;
import io.github.stewseo.clients.yelpfusion.businesses.YelpFusionBusinessAsyncClient;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionClientTestCase;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static io.github.stewseo.clients.yelpfusion._types.TestData.ID;
import static io.github.stewseo.clients.yelpfusion._types.TestData.QueryParameter;
import static io.github.stewseo.clients.yelpfusion._types.TestData.LATITUDE;
import static io.github.stewseo.clients.yelpfusion._types.TestData.LONGITUDE;
import static io.github.stewseo.clients.yelpfusion._types.TestData.PRICE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class YelpFusionBusinessClientTest extends YelpFusionClientTestCase {

    @Test
    public void testWithTransportOptions() {

        try (RestClientTransport restClientTransport = restClientTransport()) {

            YelpFusionBusinessAsyncClient client = new YelpFusionBusinessAsyncClient(restClientTransport)
                    .withTransportOptions(restClientTransport.options()
                    );

            assertThat(client._transportOptions()).isNotNull();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final YelpFusionBusinessAsyncClient businessAsyncClient =
            new YelpFusionBusinessAsyncClient(restClientTransport());

    @Test
    void testSearchBusinessesAsyncClient() throws Exception {

        String expectedUri = "URI [v3/businesses/id]";

        String expected = buildExpectedExecutionExceptionMessage(expectedUri);

        ExecutionException exception = assertThrows(ExecutionException.class,
                () -> businessAsyncClient.businessDetails(s -> s.id(ID)
                        )
                        .get()
        );

        assertThat(exception.getMessage()).isEqualTo(expected);
    }

    @Test
    void testBusinessDetailsAsyncClient() throws Exception {

        String expectedUri = "URI [v3/businesses/search?location=locationValue]";

        String expected = buildExpectedExecutionExceptionMessage(expectedUri);

        ExecutionException exception = assertThrows(ExecutionException.class,
                () -> businessAsyncClient.search(s -> s
                                        .location(List.of("locationValue")
                                        )
                                , ObjectNode.class)
                        .get()
        );

        assertThat(exception.getMessage()).isEqualTo(expected);
    }

    @Test
    void testBusinessReviewsAsyncClient() {

        String expectedUri = "URI [v3/businesses/id/reviews]";

        String expected = buildExpectedExecutionExceptionMessage(expectedUri);

        ExecutionException executionException = assertThrows(ExecutionException.class,
                () -> businessAsyncClient.businessReviews(s -> s
                        .id(ID)
                        )
                        .get()
        );

        assertThat(executionException.getMessage()).isEqualTo(expected);
    }

    @Test
    void testBusinessTransactionsAsyncClient() {

        String expectedUri = "URI " +
                "[v3/transactions/delivery/search?transaction_type=delivery&price=3&latitude=37.7829&longitude=-122.4189]";
        String expected = buildExpectedExecutionExceptionMessage(expectedUri);

        ExecutionException executionException = assertThrows(ExecutionException.class,
                () -> businessAsyncClient.searchTransaction(s -> s
                                .latitude(LATITUDE)
                                .longitude(LONGITUDE)
                                .price(PRICE)
                                .transaction_type("delivery")
                        )
                        .get()
        );

        assertThat(executionException.getMessage()).isEqualTo(expected);
    }

}
