package io.github.stewseo.clients.yelpfusion.autocomplete;

import io.github.stewseo.clients.testcase.YelpFusionTestCase;
import io.github.stewseo.clients.yelpfusion.YelpFusionClient;
import io.github.stewseo.clients.yelpfusion._types.Category;
import io.github.stewseo.clients.yelpfusion._types.Term;
import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetails;
import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetailsRequest;
import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetailsResponse;
import io.github.stewseo.clients.yelpfusion.misc.AutoCompleteRequest;
import io.github.stewseo.clients.yelpfusion.misc.AutoCompleteResponse;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoCompleteTest extends YelpFusionTestCase {

    private static final Logger logger = LoggerFactory.getLogger(AutoCompleteTest.class);

    private final double lat = 37.7829;

    private final double lon = -122.4189;

    private final AutoCompleteRequest autoCompleteRequest = AutoCompleteRequest.of(a -> a
            .text("sush")
            .locale("en_US")
            .latitude(lat)
            .longitude(lon));

    @Test
    public void testAutocompleteEndpoint() {

        final AutoCompleteRequest autoCompleteRequest = AutoCompleteRequest.of(b -> b
                .text("text")
                .longitude(-122.0)
                .latitude(67.0)
                .locale("locale")
        );

        assertThat("v3/autocomplete").isEqualTo(AutoCompleteRequest
                ._ENDPOINT.requestUrl(autoCompleteRequest));
    }

    @Test
    public void testAutocompleteResponse() {

        final AutoCompleteResponse autoCompleteResponse = AutoCompleteResponse.of(b -> b
                .businesses(BusinessDetails.of(bu -> bu.id("id"))));

        assertThat(autoCompleteResponse).isNotNull();

    }

    @Test
    public void testAutoCompleteAsyncClient() throws Exception {

        yelpFusionServiceCtx.getYelpFusionAsyncClient().autocomplete(autoCompleteRequest)
                .whenComplete((response, exception) -> {
                    if (exception != null) {
                        logger.error("Failed " + exception);
                    } else {
                        testAutocompleteResponse(response);
                        logger.info("Success ");
                    }
                }).get();

    }

    @Test
    public void testAutocompleteBlockingClient() throws Exception {

        YelpFusionClient client = YelpFusionClient.createClient(System.getenv("YELP_API_KEY"));

        AutoCompleteResponse autoCompleteResponse = client.autocomplete(autoCompleteRequest);

        testAutocompleteResponse(autoCompleteResponse);

        yelpFusionServiceCtx.getYelpFusionAsyncClient().autocomplete(autoCompleteRequest)
                .whenComplete((response, exception) -> {
                    if (exception != null) {
                        logger.error("Failed " + exception);
                    } else {
                        testAutocompleteResponse(response);
                        logger.info("Success ");
                    }
                }).get();
    }

    private void testAutocompleteResponse(AutoCompleteResponse autoCompleteResponse) {

        assertThat(Objects.requireNonNull(autoCompleteResponse).toString())
                .isEqualTo(
                        "{\"categories\":[{\"alias\":\"sushi\",\"title\":\"Sushi Bars\"}," +
                                "{\"alias\":\"conveyorsushi\",\"title\":\"Conveyor Belt Sushi\"}]," +
                                "\"terms\":[{\"text\":\"Sushi\"}," +
                                "{\"text\":\"Sushi Near Me\"}," +
                                "{\"text\":\"Sushi Restaurant\"}],\"businesses\":[]}");

        assertThat(Objects.requireNonNull(autoCompleteResponse.terms()).toString())
                .isEqualTo("[{\"text\":\"Sushi\"}, " +
                        "{\"text\":\"Sushi Near Me\"}, {\"text\":\"Sushi Restaurant\"}]");

        assertThat(Objects.requireNonNull(autoCompleteResponse.categories()).toString())
                .isEqualTo("[{\"alias\":\"sushi\",\"title\":\"Sushi Bars\"}, " +
                        "{\"alias\":\"conveyorsushi\",\"title\":\"Conveyor Belt Sushi\"}]");

        assertThat(Objects.requireNonNull(autoCompleteResponse.businesses()).toString()).isEqualTo("[]");
    }
}
