package io.github.stewseo.clients.yelpfusion;

import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionClientTestCase;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;


public class YelpFusionAsyncClientTest extends YelpFusionClientTestCase {

    @Test
    public void asyncClientTest() throws Exception {

        // Asynchronous non-blocking client
        YelpFusionAsyncClient asyncClient = YelpFusionAsyncClient.createAsyncClient(getApiKey());

        assertThat(asyncClient).isNotNull();
        assertThat(asyncClient).isInstanceOf(YelpFusionAsyncClient.class);
    }

    @Test
    public void synchronousBlockingClientTest() throws Exception {

        // Synchronous blocking client
        YelpFusionClient client = YelpFusionClient.createClient(getApiKey());
        assertThat(client).isNotNull();
        assertThat(client).isInstanceOf(YelpFusionClient.class);
    }

}
