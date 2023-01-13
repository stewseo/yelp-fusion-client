package io.github.stewseo.clients.yelpfusion.businesses;

import io.github.stewseo.clients.transport.restclient.RestClientTransport;
import io.github.stewseo.clients.yelpfusion._types.test_constants.TestData;
import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetailsRequest;
import io.github.stewseo.clients.yelpfusion.businesses.match.BusinessMatchRequest;
import io.github.stewseo.clients.yelpfusion.businesses.reviews.BusinessReviewsRequest;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessRequest;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessResult;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchResponse;
import io.github.stewseo.clients.yelpfusion.businesses.transactions.SearchTransactionRequest;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionClientTestCase;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.ErrorMessages.BUSINESS_NOT_FOUND;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.ErrorMessages.VALIDATION_SPECIFY_LOCATION_ERROR;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.LATITUDE;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.TRANSACTION_TYPE;
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

    private final YelpFusionBusinessAsyncClient yelpFusionBusinessesAsyncClient = new YelpFusionBusinessAsyncClient(restClientTransport());

    @Test
    void testBusinessReviewsAsyncClient() {

//        String expectedUri = "URI [v3/businesses/id/reviews]";

        ExecutionException executionException = assertThrows(ExecutionException.class,
                () -> yelpFusionBusinessesAsyncClient.businessReviews(s -> s
                                .id(TestData.ID)
                        )
                        .get()
        );

        assertThat(executionException.getMessage()).contains(BUSINESS_NOT_FOUND);

    }

    @Test
    void testSearchTransactionsFunctionParam() {

        ExecutionException executionException = assertThrows(ExecutionException.class,
                () -> yelpFusionBusinessesAsyncClient.searchTransaction(s -> s
                                        .latitude(LATITUDE)
                                        .price(TestData.PRICE)
                                        .transaction_type("delivery")
                                , SearchBusinessResult.class)
                        .get()
        );

        assertThat(executionException.getMessage()).contains(VALIDATION_SPECIFY_LOCATION_ERROR);
    }

    @Test
    void testSearchTransactionsPerformRequest() {

        SearchTransactionRequest searchTransactionRequest = SearchTransactionRequest.of(s -> s
                .transaction_type(TRANSACTION_TYPE)
                .location("sf")
        );

        try {
            yelpFusionBusinessesAsyncClient
                    .searchTransaction(searchTransactionRequest
                            , SearchBusinessResult.class)
                    .whenComplete((response, exception) -> {
                        if (exception != null) {
                            System.out.println("SearchTransactionRequest failed. " + exception);
                        } else {
                            assertThat(response.hits().size()).isGreaterThanOrEqualTo(1);
                            assertThat(response.total()).isGreaterThanOrEqualTo(1);
                            assertThat(response.region()).isNull();
                        }
                    }).join();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void testSearchBusinessesFunctionParam() {

        yelpFusionBusinessesAsyncClient.searchBusinesses(s -> s
                        .location("sf")
                        .term("restaurants")
                        .categories(cat -> cat
                                .alias("pizza")
                        )
                        .price("1")
                        .limit(1)
                ,
                SearchBusinessResult.class
        ).whenComplete((response, exception) -> {
            if (exception != null) {
                System.out.println("SearchTransactionRequest failed. " + exception);
            } else {

                assertThat(response.hits().size()).isGreaterThanOrEqualTo(1);
                assertThat(response.total()).isGreaterThanOrEqualTo(1);
                assertThat(response.region()).isNotNull();
            }
        }).join();

    }

    @Test
    void testSearchBusinessesPerformRequest() {

        SearchBusinessRequest req = SearchBusinessRequest.of(s -> s
                .location("sf")
                .term("restaurants")
                .categories(cat -> cat
                        .alias("pizza")
                )
        );

        try {
            CompletableFuture<SearchResponse<SearchBusinessResult>> cf = yelpFusionBusinessesAsyncClient
                    .searchBusinesses(req,
                            SearchBusinessResult.class)
                            .whenComplete((response, exception) -> {
                                if(exception != null) {
                                    System.out.println("SearchTransactionRequest failed. " + exception);
                                }else {
                                    assertThat(response).isExactlyInstanceOf(SearchResponse.class);
                                }
                            });

            SearchResponse<SearchBusinessResult> searchResp = cf.get();
            assertThat(searchResp.total()).isGreaterThanOrEqualTo(1);
            assertThat(searchResp.hits().size()).isGreaterThanOrEqualTo(1);
            assertThat(searchResp.region()).isNotNull();

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void testBusinessReviewsFunctionParam() {

        ExecutionException executionException = assertThrows(ExecutionException.class,
                () -> yelpFusionBusinessesAsyncClient.businessReviews(s -> s
                        .id(TestData.ID)
                ).get()
        );

        assertThat(executionException.getMessage()).contains(BUSINESS_NOT_FOUND);

    }

    @Test
    void testBusinessDetails() {
        BusinessDetailsRequest req = BusinessDetailsRequest.of(m -> m.id(TestData.ID));

        ExecutionException executionException = assertThrows(ExecutionException.class,
                () -> yelpFusionBusinessesAsyncClient.businessDetails(req).get()
        );
        assertThat(executionException.getMessage()).isNotNull();
    }

    @Test
    void testBusinessReviews() {
        BusinessReviewsRequest req = BusinessReviewsRequest.of(m -> m.id(TestData.ID));

        ExecutionException executionException = assertThrows(ExecutionException.class,
                () -> yelpFusionBusinessesAsyncClient.businessReviews(req).get()
        );
        assertThat(executionException.getMessage()).isNotNull();
    }

    @Test
    void testBusinessMatch() {
        BusinessMatchRequest req = BusinessMatchRequest.of(m -> m.latitude(LATITUDE));

        ExecutionException executionException = assertThrows(ExecutionException.class,
                () -> yelpFusionBusinessesAsyncClient.businessMatch(req).get()
        );
        assertThat(executionException.getMessage()).isNotNull();
    }
}
