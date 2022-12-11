package io.github.stewseo.yelp.fusion.client.yelpfusion;

import io.github.stewseo.yelp.fusion.client.yelpfusion.autocomplete.AutoCompleteResponse;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;

import static org.assertj.core.api.Assertions.assertThat;


public class AsyncClientTest {
    Logger logger = LoggerFactory.getLogger(AsyncClientTest.class);

    @Test
    public void asyncClientTest() throws Exception {
        String apiKey = System.getenv("YELP_API_KEY");

        // Asynchronous non-blocking client
        YelpFusionAsyncClient asyncClient = YelpFusionAsyncClient.createAsyncClient(apiKey);

        asyncClient.businessDetails(a -> a.alias("hinata-san-francisco"))
                .whenComplete((response, exception) -> {
                    if (exception != null) {
                        logger.error("business does not exist", exception);
                    } else {
                        logger.info("business exists");
                    }
                });
    }

    @Test
    public void synchronousBlockingClientTest() throws Exception {
        String apiKey = System.getenv("YELP_API_KEY");
        // Synchronous blocking client
        YelpFusionClient client = YelpFusionClient.createClient(apiKey);

        if (client.businesses().businessDetails(a -> a.alias("hinata-san-francisco")) != null) {
            logger.info("business exists");
        }
    }

}
