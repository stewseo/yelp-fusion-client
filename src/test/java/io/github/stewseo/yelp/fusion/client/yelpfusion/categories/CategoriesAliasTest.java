package io.github.stewseo.yelp.fusion.client.yelpfusion.categories;

import io.github.stewseo.yelp.fusion.client.yelpfusion.YelpFusionAsyncClient;
import io.github.stewseo.yelp.fusion.client.yelpfusion.YelpFusionClient;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoriesAliasTest {
    private final static Logger logger = LoggerFactory.getLogger(CategoriesAliasTest.class);

    @Test
    void categoriesAliasTest() throws Exception {

        // Specify the Yelp category alias
        YelpFusionClient client = YelpFusionClient.createClient(System.getenv("YELP_API_KEY"));

        GetCategoriesAliasResponse response = client.categories().alias(c -> c.alias("sushi"));

        assertThat(response.category().toString()).isEqualTo(
                "Categories: {\"alias\":\"sushi\",\"title\":\"Sushi Bars\",\"parent_aliases\":[\"restaurants\"],\"country_whitelist\":[],\"country_blacklist\":[]}");

    }

    @Test
    void categoriesAliasAsyncTest() throws Exception {
        String apiKey = System.getenv("YELP_API_KEY");

        YelpFusionAsyncClient asyncClient = YelpFusionAsyncClient.createAsyncClient(apiKey);

        CompletableFuture<GetCategoriesAliasResponse> future = asyncClient.categories().categoriesAlias(c -> c.alias("sushi"))
                .whenComplete((response, exception) -> {
                    if (exception != null) {
                        logger.error("Failed", exception);
                    } else {
                        logger.info("Successful");
                    }
                });

        Categories category = future.get().category();
        assertThat(category.toString()).isEqualTo(
                "Categories: {\"alias\":\"sushi\",\"title\":\"Sushi Bars\",\"parent_aliases\":[\"restaurants\"],\"country_whitelist\":[],\"country_blacklist\":[]}");

    }

}
