---
layout: page
title: "Blocking and Async client examples"
permalink: /yelp-fusion-client/examples/clients
---

#### Use your Yelp Fusion API key for a Synchronous blocking client

```
     String apiKey = System.getenv("YELP_API_KEY");
     
        // Synchronous blocking client
        YelpFusionClient client = YelpFusionClient.createClient(apiKey);

        if (client.businessDetails(a -> a.alias("hinata-san-francisco")) != null) {
            logger.info("business exists");
        }
        
```
#### Use your Yelp Fusion API key for an Asynchronous non-blocking client
```
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
```

