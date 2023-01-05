package io.github.stewseo.clients.yelpfusion.business.transactions;

import io.github.stewseo.clients.testcase.YelpFusionTestCase;
import io.github.stewseo.clients.yelpfusion.YelpFusionAsyncClient;
import io.github.stewseo.clients.yelpfusion.YelpFusionClient;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusiness;
import io.github.stewseo.clients.yelpfusion.businesses.transactions.SearchTransactionRequest;
import io.github.stewseo.clients.yelpfusion.businesses.transactions.SearchTransactionResponse;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchTransactionsTest extends YelpFusionTestCase {

    private final SearchTransactionRequest searchTransactionRequest = SearchTransactionRequest.of(s -> s
            .location("sf")
            .transaction_type("delivery")
            .categories("restaurants")
            .price(1));

    @Test
    void searchTransactionsTest() throws Exception {

        YelpFusionClient yelpClient = YelpFusionClient.createClient(System.getenv("YELP_API_KEY"));

        SearchTransactionResponse searchTransactionResponse = yelpClient.businesses().searchTransaction(searchTransactionRequest);

        assertSearchTransactionResponse(searchTransactionResponse);

        searchTransactionResponse.businesses().forEach(this::assertSearchBusiness);
    }

    @Test
    void searchTransactionsAsyncTest() throws Exception {

        YelpFusionAsyncClient asyncClient = yelpFusionServiceCtx.getYelpFusionAsyncClient();

        SearchTransactionResponse searchTransactionResponse = asyncClient.businesses()
                .searchTransaction(searchTransactionRequest).get();

        assertSearchTransactionResponse(searchTransactionResponse);

        searchTransactionResponse.businesses().forEach(this::assertSearchBusiness);
    }

    void assertSearchTransactionResponse(final SearchTransactionResponse searchTransactionResponse) {
        assertThat(searchTransactionResponse).isNotNull();
        assertThat(searchTransactionResponse.total()).isGreaterThanOrEqualTo(1);
        assertThat(searchTransactionResponse.region()).isNull();
        assertThat(searchTransactionResponse.businesses().size()).isGreaterThanOrEqualTo(1);
    }

    void assertSearchBusiness(final SearchBusiness searchBusiness) {

        assertThat(searchBusiness.id()).isNotNull();
        assertThat(searchBusiness.location().address1()).isNotNull();
        assertThat(searchBusiness.location().city()).isNotNull();
        assertThat(searchBusiness.coordinates().latitude()).isInstanceOf(Double.class);
        assertThat(searchBusiness.coordinates().longitude()).isInstanceOf(Double.class);
    }
}
