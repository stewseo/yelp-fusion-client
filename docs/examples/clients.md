---
layout: page
title: "Blocking and Async client examples"
permalink: /yelp-fusion-client/examples/clients
---

#### Create a Yelp Fusion Client with your Api Key

```
        
        String apiKey = System.getenv("YELP_API_KEY");
     
        // Synchronous blocking client
        
        YelpFusionClient client = YelpFusionClient.createClient(apiKey);

        if (client.businessDetails(a -> a.alias("hinata-san-francisco")) != null) {
            logger.info("business exists");
        }
        
```

```
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

#### Create all required components HttpHost, RestClient, a Json Object mapper, and Transport

```
        Header[] defaultHeaders = {new BasicHeader("Authorization", "Bearer " + apiKey)};
        
        // API base URL and authorzation header
        RestClient restClient = RestClient.builder(new HttpHost("api.yelp.com", 443, "https"))
                .setDefaultHeaders(defaultHeaders)
                .build();
     
        YelpRestClientTransport yelpTransport = new YelpRestClientTransport(restClient, new JacksonJsonpMapper());
     
        YelpFusionClient yelpFusionClient = new YelpFusionClient(yelpTransport);

```
