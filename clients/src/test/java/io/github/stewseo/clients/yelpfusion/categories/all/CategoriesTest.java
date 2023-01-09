package io.github.stewseo.clients.yelpfusion.categories.all;


import io.github.stewseo.clients.yelpfusion.YelpFusionClient;
import io.github.stewseo.clients.yelpfusion._types.Category;
import io.github.stewseo.clients.yelpfusion.testcases.FunctionalTestCase;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static io.github.stewseo.clients.yelpfusion._types.TestData.LOCALE;
import static org.assertj.core.api.Assertions.assertThat;

public class CategoriesTest extends FunctionalTestCase {

    private final CategoriesRequest categoriesRequest = CategoriesRequest.of(c -> c
            .locale(LOCALE));
    @Test
    public void testCategoriesEndpoint() {

        assertThat("v3/categories")
                .isEqualTo(CategoriesRequest._ENDPOINT.requestUrl(categoriesRequest));

    }

    @Test
    public void getAllCategoriesTest() throws Exception {

        YelpFusionClient yelpFusionSyncBlockClient = YelpFusionClient.createClient(System.getenv("YELP_API_KEY"));

        CategoriesResponse response = yelpFusionSyncBlockClient.categories().all(c -> c.locale("en_US"));

        List<Category> categories = response.categories();

        assertThat(categories.size()).isGreaterThanOrEqualTo(1);
        testGetAllCategories(categories);
    }

    @Test
    public void getAllCategoriesAsyncTest() throws Exception {

        CompletableFuture<CategoriesResponse> future = yelpFusionService.yelpFusionAsyncClient().categories().all(c -> c.locale("en_US"));

        List<Category> categories = future.get().categories();

        assertThat(categories.size()).isGreaterThanOrEqualTo(1);

        testGetAllCategories(future.get().categories());
    }

    public void testGetAllCategories(List<Category> categories) {

        assertThat(categories.size()).isEqualTo(1295);
        Category category = categories.get(0);
        assertThat(category.title()).isNotNull();
        assertThat(category.alias()).isNotNull();
        assertThat(category.parent_aliases()).isNotNull();
        assertThat(category.country_blacklist()).isNotNull();
        assertThat(category.country_whitelist()).isNotNull();
    }


}
