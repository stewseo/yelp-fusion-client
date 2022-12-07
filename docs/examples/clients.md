---
layout: page
title: "Blocking and Async client examples"
permalink: /yelp-fusion-client/examples/clients
---

#### Use your Yelp Fusion API key and initialize the client

```
        String apiKey = System.getenv("YELP_API_KEY");

        YelpFusionClient yelpFusionClient = YelpFusionClient.createClient(apiKey);

        AutoCompleteResponse response = yelpFusionClient.autocomplete(a -> a.text("piz"));
        
        logger.info("categories: " + response.categories());
        logger.info("businesses: " + response.businesses());
        logger.info("terms: " + response.terms());
        
```
#### console output

![Screenshot_20221207_015126](https://user-images.githubusercontent.com/54422342/206304471-ba73e057-e602-421f-8eda-0a054d4da327.png)
```
