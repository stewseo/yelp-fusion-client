---
layout: page
title: "Business Details Example"
permalink: /examples/business-details
---

#### Example request and response body containing detailed business content.
```

        String id = "wu3w6IlUct9OvYmYXDMGJA";

        BusinessDetailsResponse response = yelpClient.businesses().businessDetails(s -> s.id(id)
        );

        Assertions.assertThat(response.result().size()).isEqualTo(1); // business/{id} endpoint returns a single business with additional fields

        Business business = response.result().get(0);
        
        assertThat(business.toString().length()).isEqualTo(1471);
        
        assertThat(business.id()).isEqualTo("wu3w6IlUct9OvYmYXDMGJA");
        
        assertThat(Objects.requireNonNull(business.hours()).toString()).isEqualTo("[Hours: {\"open\":[{\"is_overnight\":false,\"day\":0,\"start\":\"1100\",\"end\":\"2230\"},{\"is_overnight\":false,\"day\":1,\"start\":\"1100\",\"end\":\"2230\"},{\"is_overnight\":false,\"day\":2,\"start\":\"1100\",\"end\":\"2230\"},{\"is_overnight\":false,\"day\":3,\"start\":\"1100\",\"end\":\"2230\"},{\"is_overnight\":false,\"day\":4,\"start\":\"1100\",\"end\":\"2330\"},{\"is_overnight\":false,\"day\":5,\"start\":\"1100\",\"end\":\"2330\"},{\"is_overnight\":false,\"day\":6,\"start\":\"1100\",\"end\":\"2230\"}],\"hours_type\":\"REGULAR\",\"is_open_now\":true}]");
        
        assertThat(business.alias()).isEqualTo("huitlacoche-taqueria-restaurant-ridgewood-2");
        
        assertThat(business.review_count()).isEqualTo(7);
        
        assertThat(business.is_closed()).isEqualTo(false); // current time: 12:15 PM EST

        assertThat(business.categories().toString()).isEqualTo("[Categories: {\"alias\":\"mexican\",\"title\":\"Mexican\"}]");
        
        assertThat(Objects.requireNonNull(business.coordinates()).toString()).isEqualTo("Coordinates: {\"latitude\":40.701692,\"longitude\":-73.906044}");
        
        assertThat(business.rating()).isEqualTo(5);
        
        assertThat(Objects.requireNonNull(business.location()).toString()).isEqualTo("Location: {\"address1\":\"778 Seneca Ave\",\"city\":\"Ridgewood\",\"zip_code\":\"11385\",\"country\":\"US\",\"display_address\":[\"778 Seneca Ave\",\"Ridgewood, NY 11385\"]}");

 ```               
                         


