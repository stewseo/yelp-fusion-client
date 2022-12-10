---
layout: page
title: "Autocomplate examples https://docs.developer.yelp.com/reference/v3_autocomplete"
permalink: /yelp-fusion-client/examples/autocomplete
---
    
#### Endpoint: [https://api.yelp.com/v3/autocomplete](https://docs.developer.yelp.com/reference/v3_autocomplete)

```
        String apiKey = System.getenv("YELP_API_KEY");
        
        // create a yelp fusion client with your api key
        YelpFusionClient client = YelpFusionClient.createClient(apiKey);
        
        AutoCompleteResponse response = client.autocomplete(a -> a.text("del"));
        
        assertThat(Objects.requireNonNull(response.terms()).toString()).isEqualTo("[Term: {\"text\":\"Delivery\"}, Term: {\"text\":\"Delivery Food\"}, Term: {\"text\":\"Deli Sandwich\"}]");
        
        assertThat(Objects.requireNonNull(response.categories()).toString()).isEqualTo("[Categories: {\"alias\":\"delis\",\"title\":\"Delis\"}, Categories: {\"alias\":\"icedelivery\",\"title\":\"Ice Delivery\"}, Categories: {\"alias\":\"waterdelivery\",\"title\":\"Water Delivery\"}]");
        
        assertThat(Objects.requireNonNull(response.businesses()).toString()).isEqualTo("[]");
     
```

```
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
        
```
