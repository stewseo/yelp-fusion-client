package io.github.stewseo.clients.yelpfusion.categories.alias;

import io.github.stewseo.clients.yelpfusion.YelpFusionAsyncClient;
import io.github.stewseo.clients.yelpfusion.YelpFusionClient;
import io.github.stewseo.clients.yelpfusion._types.Category;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoriesAliasTest {
    private static final Logger logger = LoggerFactory.getLogger(CategoriesAliasTest.class);

    private final CategoriesAliasRequest categoriesAliasRequest = CategoriesAliasRequest.of(c -> c
            .alias("sushi"));
    @Test
    public void testCategoriesAliasEndpoint() {

        assertThat("v3/categories/sushi")
                .isEqualTo(CategoriesAliasRequest._ENDPOINT.requestUrl(categoriesAliasRequest));

    }

    @Test
    void categoriesAliasTest() throws Exception {

        // Specify the Yelp category alias
        YelpFusionClient client = YelpFusionClient.createClient(System.getenv("YELP_API_KEY"));

        CategoriesAliasResponse getCategoriesAliasResponse = client.categories().alias(categoriesAliasRequest);

        testCategoriesResponse(getCategoriesAliasResponse);

    }

    @Test
    void categoriesAliasAsyncTest() throws Exception {

        YelpFusionAsyncClient asyncClient = YelpFusionAsyncClient.createAsyncClient(System.getenv("YELP_API_KEY"));

        asyncClient.categories().categoriesAlias(categoriesAliasRequest)
                .whenComplete((response, exception) -> {
                    if (exception != null) {
                        logger.error("Failed", exception);
                    } else {
                        testCategoriesResponse(response);
                        logger.info("Successful");
                    }
                }).get();

    }

    private void testCategoriesResponse(CategoriesAliasResponse getCategoriesAliasResponse) {
        assertThat(getCategoriesAliasResponse).isNotNull();
        testCategory(getCategoriesAliasResponse.category());
    }
    private void testCategory(Category category) {
        assertThat(category.alias()).isNotNull();
        assertThat(category.title()).isNotNull();
        assertThat(category.country_whitelist()).isNotNull();
        assertThat(category.country_blacklist()).isNotNull();

        assertThat(category.toString()).isEqualTo(
                "{\"alias\":\"sushi\",\"title\":\"Sushi Bars\"," +
                        "\"parent_aliases\":[\"restaurants\"],\"country_whitelist\":[],\"country_blacklist\":[]}");

    }

}
