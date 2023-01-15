package io.github.stewseo.clients.yelpfusion.businesses;

import io.github.stewseo.clients.yelpfusion.YelpFusionTest;

import io.github.stewseo.clients.transport.restclient.RestClientTransport;
import io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars;
import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetailsRequest;
import io.github.stewseo.clients.yelpfusion.businesses.match.BusinessMatchRequest;
import io.github.stewseo.clients.yelpfusion.businesses.reviews.BusinessReviewsRequest;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessRequest;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessResult;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchResponse;
import io.github.stewseo.clients.yelpfusion.businesses.transactions.SearchTransactionRequest;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionClientTestCase;
import io.github.stewseo.lowlevel.restclient.ResponseException;

import java.io.IOException;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.ErrorMessages.BUSINESS_NOT_FOUND;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.ErrorMessages.NOT_OF_TYPE_INTEGER;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.ErrorMessages.SPECIFY_LOCATION_ERROR;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.TRANSACTION_TYPE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class YelpFusionBusinessClientTest extends YelpFusionClientTestCase {

    @YelpFusionTest
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

    private final YelpFusionBusinessesClient yelpFusionBusinessClient =
            new YelpFusionBusinessesClient(restClientTransport());

    @YelpFusionTest
    void testSearchTransactionsFunctionParam() {

        ResponseException responseException = assertThrows(ResponseException.class,
                () -> yelpFusionBusinessClient.searchTransactions(s -> s
                                .latitude(TestVars.LATITUDE)
                                .price(TestVars.PRICE)
                                .transaction_type("delivery")
                        , SearchBusinessResult.class
                )
        );

        assertThat(responseException.getMessage()).contains(SPECIFY_LOCATION_ERROR);
    }

    @YelpFusionTest
    void testSearchTransactionsPerformRequest() {

        SearchTransactionRequest searchTransactionRequest = SearchTransactionRequest.of(s -> s
                .transaction_type(TRANSACTION_TYPE)
                .location("sf")
        );

        try {
            SearchResponse<SearchBusinessResult> result =
                    yelpFusionBusinessClient.searchTransactions(searchTransactionRequest, SearchBusinessResult.class);
            int size = result.hits().size();
            assertThat(size).isGreaterThanOrEqualTo(1);
            assertThat(result.total()).isGreaterThanOrEqualTo(size);
            assertThat(result.region()).isNull();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @YelpFusionTest
    void testSearchBusinessesFunctionParam() {

        ResponseException exception = assertThrows(ResponseException.class,
                () -> yelpFusionBusinessClient.searchBusinesses(s -> s
                                .location("sf")
                                .term("restaurants")
                                .categories(cat -> cat
                                        .alias("pizza")
                                )
                                .price("$")
                        ,
                        SearchBusinessResult.class
                )
        );

        assertThat(exception.getMessage()).contains(NOT_OF_TYPE_INTEGER);
    }

    @YelpFusionTest
    void testSearchBusinessesPerformRequest() {

        SearchBusinessRequest req = SearchBusinessRequest.of(s -> s
                .location("sf")
                .term("restaurants")
                .categories(cat -> cat
                        .alias("pizza")
                )
                .limit(1)
        );

        try {
            SearchResponse<SearchBusinessResult> response = yelpFusionBusinessClient.searchBusinesses(req, SearchBusinessResult.class);

            assertThat(response.hits().size()).isGreaterThanOrEqualTo(1);
            assertThat(response.total()).isGreaterThanOrEqualTo(response.hits().size());
            assertThat(response.region()).isNotNull();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @YelpFusionTest
    void testBusinessReviewsFunctionParam() {


        ResponseException executionException = assertThrows(ResponseException.class,
                () -> yelpFusionBusinessClient.businessReviews(s -> s
                        .id(TestVars.ID)
                )

        );

        assertThat(executionException.getMessage()).contains(BUSINESS_NOT_FOUND);

    }

    @YelpFusionTest
    void testBusinessReviews() {
        BusinessReviewsRequest req = BusinessReviewsRequest.of(m -> m.id(TestVars.ID));

        ResponseException executionException = assertThrows(ResponseException.class,
                () -> yelpFusionBusinessClient.businessReviews(req)
        );
        assertThat(executionException.getMessage()).isNotNull();
    }

    @YelpFusionTest
    void testBusinessDetailsFunctionParam() {

//        String expectedUri = "URI [v3/businesses/id]";

        ResponseException exception = assertThrows(ResponseException.class,
                () -> yelpFusionBusinessClient.businessDetails(s -> s.id(TestVars.ID)
                )
        );

        assertThat(exception.getMessage()).contains(BUSINESS_NOT_FOUND);
    }

    @YelpFusionTest
    void testBusinessDetails() {
        BusinessDetailsRequest req = BusinessDetailsRequest.of(m -> m.id(TestVars.ID));

        ResponseException executionException = assertThrows(ResponseException.class,
                () -> yelpFusionBusinessClient.businessDetails(req)
        );
        assertThat(executionException.getMessage()).isNotNull();
    }

    @YelpFusionTest
    void testBusinessMatch() {
        BusinessMatchRequest req = BusinessMatchRequest.of(m -> m.latitude(TestVars.LATITUDE));

        ResponseException executionException = assertThrows(ResponseException.class,
                () -> yelpFusionBusinessClient.matchBusinesses(req)
        );
        assertThat(executionException.getMessage()).isNotNull();
    }

}
