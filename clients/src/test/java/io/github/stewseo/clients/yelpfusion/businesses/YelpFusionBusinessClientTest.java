package io.github.stewseo.clients.yelpfusion.businesses;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.stewseo.clients.transport.restclient.RestClientTransport;
import io.github.stewseo.clients.yelpfusion._types.test_constants.TestData;
import io.github.stewseo.clients.yelpfusion.businesses.YelpFusionBusinessAsyncClient;
import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetailsRequest;
import io.github.stewseo.clients.yelpfusion.businesses.match.BusinessMatchRequest;
import io.github.stewseo.clients.yelpfusion.businesses.reviews.BusinessReviewsRequest;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessRequest;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessResponse;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessResult;
import io.github.stewseo.clients.yelpfusion.businesses.transactions.SearchTransactionRequest;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionClientTestCase;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.ID;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.LATITUDE;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.LONGITUDE;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.PRICE;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.ErrorCodes.BUSINESS_NOT_FOUND;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.ErrorCodes.LOCATION_NOT_FOUND;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.ErrorCodes.SPECIFY_LOCATION_ERROR;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.CATEGORY;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.TRANSACTION_TYPE;
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
    void testSearchBusinessesAsyncClient() {

//        String expectedUri = "URI [v3/businesses/id]";

        ExecutionException exception = assertThrows(ExecutionException.class,
                () -> businessAsyncClient.businessDetails(s -> s.id(TestData.ID)
                        )
                        .get()
        );

        assertThat(exception.getMessage()).contains(BUSINESS_NOT_FOUND);
    }

    @Test
    void testBusinessDetailsAsyncClient() throws Exception {

//        String expectedUri = "URI [v3/businesses/search?location=locationValue]";

        ExecutionException exception = assertThrows(ExecutionException.class,
                () -> businessAsyncClient.search(s -> s
                                        .location(List.of("locationValue")
                                        )
                                , ObjectNode.class)
                        .get()
        );

        assertThat(exception.getMessage()).contains(LOCATION_NOT_FOUND);
    }

    @Test
    void testBusinessReviewsAsyncClient() {

//        String expectedUri = "URI [v3/businesses/id/reviews]";

        ExecutionException executionException = assertThrows(ExecutionException.class,
                () -> businessAsyncClient.businessReviews(s -> s
                                .id(TestData.ID)
                        )
                        .get()
        );

        assertThat(executionException.getMessage()).contains(BUSINESS_NOT_FOUND);

    }

    @Test
    void testBusinessTransactionsAsyncClient() {

        String expectedUri = "URI " +
                "[v3/transactions/delivery/search?transaction_type=delivery&price=3&latitude=37.7829&longitude=-122.4189]";

        ExecutionException executionException = assertThrows(ExecutionException.class,
                () -> businessAsyncClient.searchTransaction(s -> s
                                .latitude(TestData.LATITUDE)
                                .price(TestData.PRICE)
                                .transaction_type("delivery")
                        )
                        .get()
        );

        assertThat(executionException.getMessage()).contains(SPECIFY_LOCATION_ERROR);
    }

    @Test
    void testBusinessSearch() {
        SearchBusinessRequest req = SearchBusinessRequest.of(m -> m.categories(CATEGORY));

        ExecutionException executionException = assertThrows(ExecutionException.class,
                () -> businessAsyncClient.search(req, SearchBusinessResult.class).get()
        );
        assertThat(executionException.getMessage()).isNotNull();
    }

    @Test
    void testBusinessDetails() {
        BusinessDetailsRequest req = BusinessDetailsRequest.of(m -> m.id(TestData.ID));

        ExecutionException executionException = assertThrows(ExecutionException.class,
                () -> businessAsyncClient.businessDetails(req).get()
        );
        assertThat(executionException.getMessage()).isNotNull();
    }

    @Test
    void testBusinessReviews() {
        BusinessReviewsRequest req = BusinessReviewsRequest.of(m -> m.id(TestData.ID));

        ExecutionException executionException = assertThrows(ExecutionException.class,
                () -> businessAsyncClient.businessReviews(req).get()
        );
        assertThat(executionException.getMessage()).isNotNull();
    }

    @Test
    void testBusinessMatch() {
        BusinessMatchRequest req = BusinessMatchRequest.of(m -> m.latitude(TestData.LATITUDE));

        ExecutionException executionException = assertThrows(ExecutionException.class,
                () -> businessAsyncClient.businessMatch(req).get()
        );
        assertThat(executionException.getMessage()).isNotNull();
    }

    @Test
    void testSearchTransaction() {
        SearchTransactionRequest req = SearchTransactionRequest.of(s -> s.transaction_type(TRANSACTION_TYPE));

        ExecutionException executionException = assertThrows(ExecutionException.class,
                () -> businessAsyncClient.searchTransaction(req).get()
        );

        SearchTransactionRequest searchTransactionRequest = SearchTransactionRequest.of(s -> s.transaction_type(TRANSACTION_TYPE).location("sf"));

        try {
            SearchBusinessResponse resp = businessAsyncClient.searchTransaction(searchTransactionRequest).get();
            assertThat(resp.businesses().size()).isGreaterThanOrEqualTo(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
