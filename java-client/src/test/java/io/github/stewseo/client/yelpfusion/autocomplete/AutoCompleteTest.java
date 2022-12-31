package io.github.stewseo.client.yelpfusion.autocomplete;

import io.github.stewseo.client.yelpfusion.YelpFusionAsyncClient;

import io.github.stewseo.client.yelpfusion.YelpFusionClient;
import io.github.stewseo.client.yelpfusion.YelpFusionTestCase;
import io.github.stewseo.client.yelpfusion.misc.AutoCompleteResponse;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoCompleteTest extends YelpFusionTestCase {

    private static final Logger logger = LoggerFactory.getLogger(AutoCompleteTest.class);

    @Test
    public void autoCompleteTest() throws Exception {
        String apiKey = System.getenv("YELP_API_KEY");
        YelpFusionClient client = YelpFusionClient.createClient(apiKey);
        AutoCompleteResponse response = client.autocomplete(a -> a.text("sush"));


        assertThat(Objects.requireNonNull(response).toString()).isEqualTo(
                "{\"categories\":[{\"alias\":\"sushi\",\"title\":\"Sushi Bars\"},{\"alias\":\"conveyorsushi\",\"title\":\"Conveyor Belt Sushi\"}],\"terms\":[{\"text\":\"Sushi\"},{\"text\":\"Sushi Near Me\"},{\"text\":\"Sushi Restaurant\"}],\"businesses\":[]}");

        assertThat(Objects.requireNonNull(response.terms()).toString()).isEqualTo("[{\"text\":\"Sushi\"}, {\"text\":\"Sushi Near Me\"}, {\"text\":\"Sushi Restaurant\"}]");

        assertThat(Objects.requireNonNull(response.categories()).toString()).isEqualTo("[{\"alias\":\"sushi\",\"title\":\"Sushi Bars\"}, {\"alias\":\"conveyorsushi\",\"title\":\"Conveyor Belt Sushi\"}]");
        assertThat(Objects.requireNonNull(response.businesses()).toString()).isEqualTo("[]");
    }

    @Test
    public void autoCompleteAsyncTest() throws Exception {

        String apiKey = System.getenv("YELP_API_KEY");
        YelpFusionAsyncClient asyncClient = YelpFusionAsyncClient.createAsyncClient(apiKey);

        CompletableFuture<AutoCompleteResponse> future = asyncClient.autocomplete(a -> a.text("sush"))
                .whenComplete((response, exception) -> {
                    if (exception != null) {
                        logger.error("Failed " + exception);
                    } else {
                        logger.info("Success ");
                    }
                });

        AutoCompleteResponse response = future.get();
        assertThat(Objects.requireNonNull(response).toString()).isEqualTo(
                "{\"categories\":[{\"alias\":\"sushi\",\"title\":\"Sushi Bars\"},{\"alias\":\"conveyorsushi\",\"title\":\"Conveyor Belt Sushi\"}],\"terms\":[{\"text\":\"Sushi\"},{\"text\":\"Sushi Near Me\"},{\"text\":\"Sushi Restaurant\"}],\"businesses\":[]}");

        assertThat(Objects.requireNonNull(response.terms()).toString()).isEqualTo("[{\"text\":\"Sushi\"}, {\"text\":\"Sushi Near Me\"}, {\"text\":\"Sushi Restaurant\"}]");

        assertThat(Objects.requireNonNull(response.categories()).toString()).isEqualTo("[{\"alias\":\"sushi\",\"title\":\"Sushi Bars\"}, {\"alias\":\"conveyorsushi\",\"title\":\"Conveyor Belt Sushi\"}]");
        assertThat(Objects.requireNonNull(response.businesses()).toString()).isEqualTo("[]");
    }
}
