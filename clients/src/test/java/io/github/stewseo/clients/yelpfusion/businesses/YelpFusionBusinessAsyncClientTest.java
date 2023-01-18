package io.github.stewseo.clients.yelpfusion.businesses;

import io.github.stewseo.clients.yelpfusion.YelpFusionTest;

import io.github.stewseo.clients.transport.restclient.RestClientTransport;
import io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars;
import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetailsRequest;
import io.github.stewseo.clients.yelpfusion.businesses.match.MatchBusinessesRequest;
import io.github.stewseo.clients.yelpfusion.businesses.reviews.BusinessReviewsRequest;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessesRequest;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessesResult;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchResponse;
import io.github.stewseo.clients.yelpfusion.businesses.transactions.SearchTransactionRequest;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionClientTestCase;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.ErrorMessages.BUSINESS_NOT_FOUND;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.ErrorMessages.VALIDATION_SPECIFY_LOCATION_ERROR;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.LATITUDE;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.LOCATION;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.LONGITUDE;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.PRICE;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.TRANSACTION_TYPE;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.TestBusinessVars.CITY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LONG;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class YelpFusionBusinessAsyncClientTest extends YelpFusionClientTestCase {

    @YelpFusionTest
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

    private final YelpFusionBusinessAsyncClient yelpFusionBusinessesAsyncClient =
            new YelpFusionBusinessAsyncClient(restClientTransport());

    @YelpFusionTest
    void testBusinessReviewsAsyncClient() {

//        String expectedUri = "URI [v3/businesses/id/reviews]";

        ExecutionException executionException = assertThrows(ExecutionException.class,
                () -> yelpFusionBusinessesAsyncClient.businessReviews(s -> s
                                .id(TestVars.ID)
                        )
                        .get()
        );

        assertThat(executionException.getMessage()).contains(BUSINESS_NOT_FOUND);

    }

    @YelpFusionTest
    void testSearchTransactionsFunctionParam() {

        ExecutionException executionException = assertThrows(ExecutionException.class,
                () -> yelpFusionBusinessesAsyncClient.searchTransaction(s -> s
                                        .latitude(LATITUDE)
                                        .price(PRICE)
                                        .transaction_type("delivery")
                                , SearchBusinessesResult.class)
                        .get()
        );

        assertThat(executionException.getMessage()).contains(VALIDATION_SPECIFY_LOCATION_ERROR);
    }

    @YelpFusionTest
    void testSearchTransactionsPerformRequest() {

        SearchTransactionRequest searchTransactionRequest = SearchTransactionRequest.of(s -> s
                .transaction_type(TRANSACTION_TYPE)
                .latitude(LATITUDE)
                .longitude(LONGITUDE)
        );

        try {
            yelpFusionBusinessesAsyncClient
                    .searchTransaction(searchTransactionRequest
                            , SearchBusinessesResult.class)
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

    @YelpFusionTest
    void testSearchBusinessesFunctionParam() {

        yelpFusionBusinessesAsyncClient.searchBusinesses(s -> s
                        .location(l -> l
                                .city(CITY))
                        .term("restaurants")
                        .categories(cat -> cat
                                .alias("pizza")
                        )
                        .price(PRICE)
                        .limit(1)
                ,
                SearchBusinessesResult.class
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

    @YelpFusionTest
    void testSearchBusinessesPerformRequest() {

        SearchBusinessesRequest req = SearchBusinessesRequest.of(s -> s
                .location(l -> l
                        .city(CITY))
                .term("restaurants")
                .categories(cat -> cat
                        .alias("pizza")
                )
        );

        try {
            CompletableFuture<SearchResponse<SearchBusinessesResult>> cf = yelpFusionBusinessesAsyncClient
                    .searchBusinesses(req,
                            SearchBusinessesResult.class)
                            .whenComplete((response, exception) -> {
                                if(exception != null) {
                                    System.out.println("SearchTransactionRequest failed. " + exception);
                                }else {
                                    assertThat(response).isExactlyInstanceOf(SearchResponse.class);
                                }
                            });

            SearchResponse<SearchBusinessesResult> searchResp = cf.get();
            assertThat(searchResp.total()).isGreaterThanOrEqualTo(1);
            assertThat(searchResp.hits().size()).isGreaterThanOrEqualTo(1);
            assertThat(searchResp.region()).isNotNull();

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

    }

    @YelpFusionTest
    void testBusinessReviewsFunctionParam() {

        ExecutionException executionException = assertThrows(ExecutionException.class,
                () -> yelpFusionBusinessesAsyncClient.businessReviews(s -> s
                        .id(TestVars.ID)
                ).get()
        );

        assertThat(executionException.getMessage()).contains(BUSINESS_NOT_FOUND);

    }

    @YelpFusionTest
    void testBusinessDetails() {
        BusinessDetailsRequest req = BusinessDetailsRequest.of(m -> m.id(TestVars.ID));

        ExecutionException executionException = assertThrows(ExecutionException.class,
                () -> yelpFusionBusinessesAsyncClient.businessDetails(req).get()
        );
        assertThat(executionException.getMessage()).isNotNull();
    }

    @YelpFusionTest
    void testBusinessReviews() {
        BusinessReviewsRequest req = BusinessReviewsRequest.of(m -> m.id(TestVars.ID));

        ExecutionException executionException = assertThrows(ExecutionException.class,
                () -> yelpFusionBusinessesAsyncClient.businessReviews(req).get()
        );
        assertThat(executionException.getMessage()).isNotNull();
    }

    @YelpFusionTest
    void testBusinessMatch() {
        MatchBusinessesRequest req = MatchBusinessesRequest.of(m -> m.latitude(LATITUDE));

        ExecutionException executionException = assertThrows(ExecutionException.class,
                () -> yelpFusionBusinessesAsyncClient.businessMatch(req).get()
        );
        assertThat(executionException.getMessage()).isNotNull();
    }
}
