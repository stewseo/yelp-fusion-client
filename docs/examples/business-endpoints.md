---
layout: page
title: "Business Examples"
permalink: /yelp-fusion-client/examples/business
---

#### Get the business details of each restaurant in New York City
```

 try {
 //   Submit a request to the Business Search endpoint 
 
 BusinessSearchResponse resposne = yelpClient.businessSearch(s -> s
                .location("nyc")
                .term("rrestaurants")
                .categories(cat -> cat
                        .alias(category))
                .limit(50)
                .offset(finalOffset)
                .sort_by(review_count)
        );
        for(BusinessSearch bSearch: resposne.businesses()) {
             
            if (setOfBusinessIds.add(bSearch.id())) {
           
                // In this example, Business Details API objects are only built if a business' id is not in the database.        
                
                BusinessDetailsResponse resp = yelpClient.businessDetails(s -> s.id(businessId));
 ```               
                         


