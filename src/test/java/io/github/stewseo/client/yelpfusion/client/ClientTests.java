package io.github.stewseo.client.yelpfusion.client;

import io.github.stewseo.client.yelpfusion.YelpFusionAsyncClient;
import io.github.stewseo.client.yelpfusion.YelpFusionSyncBlockingClient;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ClientTests {
    Logger logger = LoggerFactory.getLogger(ClientTests.class);

    @Test
    public void asyncClientTest() throws Exception {

        String apiKey = System.getenv("YELP_API_KEY");

        // Asynchronous non-blocking client
        YelpFusionAsyncClient asyncClient = YelpFusionAsyncClient.createAsyncClient(apiKey);

        asyncClient.businesses().businessDetails(a -> a.alias("hinata-san-francisco"))
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
        YelpFusionSyncBlockingClient client = YelpFusionSyncBlockingClient.createClient(apiKey);

        if (client.businesses().businessDetails(a -> a.alias("hinata-san-francisco")) != null) {
            logger.info("business exists");
        }
    }

}
