package io.github.stewseo.clients.yelpfusion;

import io.github.stewseo.clients.transport.restclient.RestClientOptions;
import io.github.stewseo.clients.yelpfusion.businesses.YelpFusionBusinessAsyncClient;
import io.github.stewseo.clients.yelpfusion.categories.YelpFusionCategoriesAsyncClient;
import io.github.stewseo.clients.yelpfusion.events.YelpFusionEventsAsyncClient;
import io.github.stewseo.clients.yelpfusion.misc.AutoCompleteRequest;
import io.github.stewseo.clients.yelpfusion.misc.AutoCompleteResponse;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionClientTestCase;
import io.github.stewseo.lowlevel.restclient.RequestOptions;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.LOCALE;
import static org.assertj.core.api.Assertions.assertThat;


public class YelpFusionAsyncClientTest extends YelpFusionClientTestCase {

    YelpFusionAsyncClient asyncClient = new YelpFusionAsyncClient(restClientTransport());

    @YelpFusionTest
    public void testClient() {
        assertThat(asyncClient).isNotNull();
        assertThat(asyncClient).isInstanceOf(YelpFusionAsyncClient.class);
    }

    @YelpFusionTest
    public void testWithTransportOptions() {

        RestClientOptions transportOptions = new RestClientOptions(RequestOptions.DEFAULT);

        YelpFusionAsyncClient asyncClient =
                new YelpFusionAsyncClient(restClientTransport()).withTransportOptions(transportOptions);

        assertThat(asyncClient._transportOptions()).isNotNull();
    }

    @YelpFusionTest
    void testClients() {

        assertThat(asyncClient.businesses()).isInstanceOf(YelpFusionBusinessAsyncClient.class);

        assertThat(asyncClient.categories()).isInstanceOf(YelpFusionCategoriesAsyncClient.class);

        assertThat(asyncClient.events()).isInstanceOf(YelpFusionEventsAsyncClient.class);
    }

    private final String autocompleteValue = "californi";
    private final String expected = "" +
            "{" +
                "\"categories\":[]," +
                    "\"terms\":" +
                    "[" +
                        "{" +
                            "\"text\":\"California Chicken Cafe\"}," +
                            "{\"text\":\"California Fish Gril\"}," +
                            "{\"text\":\"Californian\"" +
                        "}" +
                    "]," +
                "\"businesses\":[]" +
            "}";

    @YelpFusionTest
    void testAutocompleteFunction() throws Exception {

        assertThat(asyncClient.autocomplete(a -> a
                                .text(autocompleteValue)
                                .locale(LOCALE)
                        ).whenComplete((response, exception) -> {
                                    if (exception != null) {
                                        System.out.println("Exception != null " + exception);
                                    } else {
                                        assertThat(response.businesses()).isNotNull();
                                        assertThat(response.categories()).isNotNull();
                                        assertThat(response.terms()).isNotNull();
                                    }
                                }
                        )
                        .thenApply(AutoCompleteResponse::toString)
                        .join()
        )
                .isEqualTo(expected);

    }

    @YelpFusionTest
    void testPerformAutocompleteRequest() throws Exception {

        AutoCompleteRequest autoCompleteRequest = AutoCompleteRequest.of(a -> a.text(autocompleteValue));

        assertThat(asyncClient.autocomplete(autoCompleteRequest)).isNotNull();

        assertThat(asyncClient.autocomplete(autoCompleteRequest
                        ).whenComplete((response, exception) -> {
            if (exception != null) {
                System.out.println("Exception != null " + exception);
            } else {
                assertThat(response.businesses()).isNotNull();
                assertThat(response.categories()).isNotNull();
                assertThat(response.terms()).isNotNull();
            }
                        }
                        )
                .thenApply(AutoCompleteResponse::toString)
                .join()
        )
                .isEqualTo(expected);


    }

}
