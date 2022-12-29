package io.github.stewseo.client.yelpfusion.business.transactions;

import io.github.stewseo.client.yelpfusion.YelpFusionAsyncClient;
import io.github.stewseo.client.yelpfusion.YelpFusionSyncBlockingClient;
import io.github.stewseo.client.yelpfusion.business.transactions.SearchTransactionResponse;
import io.github.stewseo.client.yelpfusion.business.search.SearchBusiness;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchTransactionsTest {

    //  {\"id\":\"VHeDQKCT81P3i0edsMs9rw\"," +
    //                "\"alias\":\"nobhill-pizza-and-shawarma-san-francisco\"," +
    //                "\"name\":\"Nobhill Pizza & Shawarma\"," +
    //                "\"image_url\":\"https://s3-media4.fl.yelpcdn.com/bphoto/b6ZjYI7L-lt34sJAsibVOg/o.jpg\"," +
    //                "\"url\":\"https://www.yelp.com/biz/nobhill-pizza-and-shawarma-san-francisco?adjust_creative=ccj3y1UCH-4gsdWSMdEDOw&utm_campaign=yelp_api_v3&utm_medium=api_v3_transactions_search_delivery&utm_source=ccj3y1UCH-4gsdWSMdEDOw\"," +
    //                "\"phone\":\"+14157752525\"," +
    //                "\"price\":\"$\"," +
    //                "\"display_phone\":\"(415) 775-2525\"," +
    //                "\"is_closed\":false," +
    //                "\"rating\":4.0," +
    //                "\"review_count\":322," +
    //                "\"transactions\":[\"pickup\",\"delivery\"]," +
    //                "\"location\":{\"address1\":\"1534 California St\",\"address2\":\"\",\"city\":\"San Francisco\",\"zip_code\":\"94109\",\"country\":\"US\",\"display_address\":[\"1534 California St\",\"San Francisco, CA 94109\"]}," +
    //                "\"coordinates\":{\"latitude\":37.79085,\"longitude\":-122.41979}}

    @Test
    void searchTransactionsTest() throws Exception {

        String apiKey = System.getenv("YELP_API_KEY");

        YelpFusionSyncBlockingClient yelpClient = YelpFusionSyncBlockingClient.createClient(apiKey);

        SearchTransactionResponse response = yelpClient.businesses().searchTransaction(s -> s
                .location("sf")
                .transaction_type("delivery")
                .categories("restaurants")
                .price(1));

        assertThat(response.total()).isGreaterThanOrEqualTo(1);
        assertThat(response.businesses().size()).isGreaterThanOrEqualTo(1);

        testSearchTransactionResponse(response.businesses().stream());
    }

    @Test
    void searchTransactionsAsyncTest() throws Exception {

        String apiKey = System.getenv("YELP_API_KEY");

        YelpFusionAsyncClient asyncClient = YelpFusionAsyncClient.createAsyncClient(apiKey);

        CompletableFuture<SearchTransactionResponse> response = asyncClient.businesses().searchTransaction(s -> s
                .location("sf")
                .transaction_type("delivery")
                .categories("restaurants")
                .price(1));

        testSearchTransactionResponse(response.get().businesses().stream());
    }

    void testSearchTransactionResponse(Stream<SearchBusiness> response) {
        response.forEach(SearchBusiness -> {
            assertThat(SearchBusiness.id()).isNotNull();
            assertThat(SearchBusiness.location().address1()).isNotNull();
            assertThat(SearchBusiness.location().city()).isNotNull();
            assertThat(SearchBusiness.coordinates().latitude()).isInstanceOf(Double.class);
            assertThat(SearchBusiness.coordinates().longitude()).isInstanceOf(Double.class);
        });

    }
}
