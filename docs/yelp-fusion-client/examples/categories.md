---
layout: page
title: "Categories examples https://docs.developer.yelp.com/reference/v3_all_categories"
permalink: /examples/categories
---

#### Endpoint: https://api.yelp.com/v3/categories 

```
        String apiKey = System.getenv("YELP_API_KEY");

        YelpFusionClient client = YelpFusionClient.createClient(apiKey);

        GetCategoriesResponse response = client.categories(c -> c.locale("en_US"));

        assertThat(response.categories().size()).isEqualTo(1295);

        for(Categories cat : response.categories()) {
            if (cat != null) {
                logger.info(" " + cat);
            }
        }
```
```
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
```
