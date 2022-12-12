package io.github.stewseo.yelp.fusion.client.yelpfusion.categories;

import io.github.stewseo.yelp.fusion.client.YelpConnection;
import io.github.stewseo.yelp.fusion.client.yelpfusion.YelpFusionAsyncClient;
import io.github.stewseo.yelp.fusion.client.yelpfusion.YelpFusionClient;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;

public class GetAllCategoriesTest {
    private final static Logger logger = LoggerFactory.getLogger(GetAllCategoriesTest.class);

    @Test
    public void getAllCategoriesTest() throws Exception {
        String apiKey = System.getenv("YELP_API_KEY");

        YelpFusionClient client = YelpFusionClient.createClient(apiKey);

        GetCategoriesResponse response = client.categories().all(c -> c.locale("en_US"));
        assertThat(response.categories().toString().length()).isEqualTo(199188);
        assertThat(response.categories().size()).isEqualTo(1295);

        for(Categories cat : response.categories()) {
            if (cat != null) {
                logger.info(" " + cat);
            }
        }
    }

    @Test
    public void getAllCategoriesAsyncTest() throws Exception {
        String apiKey = System.getenv("YELP_API_KEY");

        YelpFusionAsyncClient asyncClient = YelpFusionAsyncClient.createAsyncClient(apiKey);

        CompletableFuture<GetCategoriesResponse> future = asyncClient.categories(c -> c.locale("en_US"));

        List<Categories> categories = future.get().categories();
        assertThat(categories.size()).isEqualTo(1295);
        for(Categories category : categories) {
            if (category != null) {
                logger.info(" " + category);
            }
        }
    }


}
