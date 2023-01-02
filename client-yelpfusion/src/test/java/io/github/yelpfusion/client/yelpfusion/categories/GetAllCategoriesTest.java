package io.github.yelpfusion.client.yelpfusion.categories;


import io.github.yelpfusion.client.yelpfusion.YelpFusionTestCase;
import io.github.stewseo.client.yelpfusion.categories.Category;
import io.github.stewseo.client.yelpfusion.categories.GetCategoriesResponse;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;

public class GetAllCategoriesTest extends YelpFusionTestCase {

    private final static Logger logger = LoggerFactory.getLogger(GetAllCategoriesTest.class);

//    @Test
//    public void getAllCategoriesTest() throws Exception {
//
//        YelpFusionClient yelpFusionSyncBlockClient = yelpFusionServiceCtx.getYelpFusionAsyncClient().createOrGetYepFusionSynchronousBlockingClient();
//
//        GetCategoriesResponse response = yelpFusionSyncBlockClient.categories().all(c -> c.locale("en_US"));
//        List<Category> categories = response.categories();
//
//        assertThat(categories.size()).isGreaterThanOrEqualTo(1);
//        testGetAllCategories(categories);
//    }

    @Test
    public void getAllCategoriesAsyncTest() throws Exception {

        CompletableFuture<GetCategoriesResponse> future = yelpFusionServiceCtx.getYelpFusionAsyncClient().categories().all(c -> c.locale("en_US"));

        List<Category> categories = future.get().categories();

        assertThat(categories.size()).isGreaterThanOrEqualTo(1);

        testGetAllCategories(future.get().categories());
    }

    public void testGetAllCategories(List<Category> categories) {

        assertThat(categories.size()).isEqualTo(1295);
    }


}