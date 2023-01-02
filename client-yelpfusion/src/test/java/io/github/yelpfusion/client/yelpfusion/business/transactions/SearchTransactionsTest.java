package io.github.yelpfusion.client.yelpfusion.business.transactions;

import io.github.stewseo.client.yelpfusion.YelpFusionAsyncClient;
import io.github.stewseo.client.yelpfusion.YelpFusionClient;
import io.github.stewseo.client.yelpfusion.business.search.SearchBusiness;
import io.github.stewseo.client.yelpfusion.business.transactions.SearchTransactionResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

        YelpFusionClient yelpClient = YelpFusionClient.createClient(apiKey);

        SearchTransactionResponse searchTransactionResponse = yelpClient.businesses().searchTransaction(s -> s
                .location("sf")
                .transaction_type("delivery")
                .categories("restaurants")
                .price(1));

        assertThat(searchTransactionResponse).isNotNull();

        assertSearchTransactionResponse(searchTransactionResponse);

        searchTransactionResponse.businesses().forEach(this::assertSearchBusiness);
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

        SearchTransactionResponse searchTransactionResponse = response.get();

        assertThat(searchTransactionResponse).isNotNull();

        assertSearchTransactionResponse(searchTransactionResponse);

        searchTransactionResponse.businesses().forEach(this::assertSearchBusiness);
    }

    void assertSearchTransactionResponse(final SearchTransactionResponse searchTransactionResponse) {
        assertThat(searchTransactionResponse.total()).isGreaterThanOrEqualTo(1);
        assertThat(searchTransactionResponse.region()).isNotNull();
        assertThat(searchTransactionResponse.businesses().size()).isGreaterThanOrEqualTo(1);
    }

    void assertSearchBusiness(final SearchBusiness searchBusiness) {

        assertThat(searchBusiness.id()).isNotNull();
        Assertions.assertThat(searchBusiness.location().address1()).isNotNull();
        Assertions.assertThat(searchBusiness.location().city()).isNotNull();
        Assertions.assertThat(searchBusiness.coordinates().latitude()).isInstanceOf(Double.class);
        Assertions.assertThat(searchBusiness.coordinates().longitude()).isInstanceOf(Double.class);
    }
}
