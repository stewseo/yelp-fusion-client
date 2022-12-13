package io.github.stewseo.yelp.fusion.client.yelpfusion.autocomplete;

import io.github.stewseo.yelp.fusion.client.YelpConnection;
import io.github.stewseo.yelp.fusion.client.yelpfusion.YelpFusionAsyncClient;
import io.github.stewseo.yelp.fusion.client.yelpfusion.YelpFusionClient;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoCompleteTest {

    private static final Logger logger = LoggerFactory.getLogger(AutoCompleteTest.class);

    @Test
    public void autoCompleteTest() throws Exception {
        String apiKey = System.getenv("YELP_API_KEY");
        YelpFusionClient client = YelpFusionClient.createClient(apiKey);
        AutoCompleteResponse response = client.autocomplete(a -> a.text("del"));

        assertThat(Objects.requireNonNull(response.terms()).toString()).isEqualTo("[Term: {\"text\":\"Delivery\"}, Term: {\"text\":\"Delivery Food\"}, Term: {\"text\":\"Deli Sandwich\"}]");
        assertThat(Objects.requireNonNull(response.categories()).toString()).isEqualTo("[Categories: {\"alias\":\"delis\",\"title\":\"Delis\"}, Categories: {\"alias\":\"icedelivery\",\"title\":\"Ice Delivery\"}, Categories: {\"alias\":\"waterdelivery\",\"title\":\"Water Delivery\"}]");
        assertThat(Objects.requireNonNull(response.businesses()).toString()).isEqualTo("[]");
    }

    @Test
    public void autoCompleteAsyncTest() throws Exception {

        String apiKey = System.getenv("YELP_API_KEY");
        YelpFusionAsyncClient asyncClient = YelpFusionAsyncClient.createAsyncClient(apiKey);

        CompletableFuture<AutoCompleteResponse> future = asyncClient.autocomplete(a -> a.text("del"))
                .whenComplete((response, exception) -> {
                    if (exception != null) {
                        logger.error("Failed " + exception);
                    } else {
                        logger.info("Success ");
                    }
                });

        AutoCompleteResponse response = future.get();
        assertThat(Objects.requireNonNull(response.terms()).toString()).isEqualTo("[Term: {\"text\":\"Delicatessen\"}, Term: {\"text\":\"Delivery\"}, Term: {\"text\":\"Delivery Food\"}]");
        assertThat(Objects.requireNonNull(response.categories()).toString()).isEqualTo("[Categories: {\"alias\":\"delis\",\"title\":\"Delis\"}, Categories: {\"alias\":\"icedelivery\",\"title\":\"Ice Delivery\"}, Categories: {\"alias\":\"waterdelivery\",\"title\":\"Water Delivery\"}]");
        assertThat(Objects.requireNonNull(response.businesses()).toString()).isEqualTo("[]");
    }
}
