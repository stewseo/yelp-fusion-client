---
layout: page
title: "Example Transactions Request"
permalink: /examples/transactions
---

### Searching for restaurants in SF that deliver with price: $ 
```
        String apiKey = System.getenv("YELP_API_KEY");

        YelpFusionClient yelpClient = YelpFusionClient.createClient(apiKey);

        SearchTransactionResponse response = yelpClient.businesses().searchTransaction(s -> s
                .location("sf")
                .transaction_type("delivery")
                .categories("restaurants")
                .price(1));

        assertThat(response.total()).isEqualTo(30);
        assertThat(response.businesses().size()).isEqualTo(20);
        assertThat(response.businesses().get(1).toString()).isEqualTo("" +
                "SearchBusiness: {\"id\":\"VHeDQKCT81P3i0edsMs9rw\"," +
                "\"alias\":\"nobhill-pizza-and-shawarma-san-francisco\"," +
                "\"name\":\"Nobhill Pizza & Shawarma\"," +
                "\"image_url\":\"https://s3-media4.fl.yelpcdn.com/bphoto/b6ZjYI7L-lt34sJAsibVOg/o.jpg\"," +
                "\"url\":\"https://www.yelp.com/biz/nobhill-pizza-and-shawarma-san-francisco?adjust_creative=ccj3y1UCH-4gsdWSMdEDOw&utm_campaign=yelp_api_v3&utm_medium=api_v3_transactions_search_delivery&utm_source=ccj3y1UCH-4gsdWSMdEDOw\"," +
                "\"phone\":\"+14157752525\"," +
                "\"price\":\"$\"," +
                "\"display_phone\":\"(415) 775-2525\"," +
                "\"is_closed\":false," +
                "\"rating\":4.0," +
                "\"review_count\":322," +
                "\"transactions\":[\"pickup\",\"delivery\"]," +
                "\"location\":{\"address1\":\"1534 California St\",\"address2\":\"\",\"city\":\"San Francisco\",\"zip_code\":\"94109\",\"country\":\"US\",\"display_address\":[\"1534 California St\",\"San Francisco, CA 94109\"]}," +
                "\"coordinates\":{\"latitude\":37.79085,\"longitude\":-122.41979}}");
```
<br/>

```
        CompletableFuture<SearchTransactionResponse> response = asyncClient.businesses().searchTransaction(s -> s
                .location("sf")
                .transaction_type("delivery")
                .categories("restaurants")
                .price(1));

        SearchTransactionResponse searchResponse = response.get();
        assertThat(response.get().total()).isEqualTo(30);

        List<SearchBusiness> searchBusinesses = searchResponse.businesses();

        assertThat(searchBusinesses.size()).isEqualTo(20);

        assertThat(searchBusinesses.get(1).toString()).isEqualTo("" +
                "SearchBusiness: {\"id\":\"VHeDQKCT81P3i0edsMs9rw\"," +
                "\"alias\":\"nobhill-pizza-and-shawarma-san-francisco\"," +
                "\"name\":\"Nobhill Pizza & Shawarma\"," +
                "\"image_url\":\"https://s3-media4.fl.yelpcdn.com/bphoto/b6ZjYI7L-lt34sJAsibVOg/o.jpg\"," +
                "\"url\":\"https://www.yelp.com/biz/nobhill-pizza-and-shawarma-san-francisco?adjust_creative=ccj3y1UCH-4gsdWSMdEDOw&utm_campaign=yelp_api_v3&utm_medium=api_v3_transactions_search_delivery&utm_source=ccj3y1UCH-4gsdWSMdEDOw\"," +
                "\"phone\":\"+14157752525\"," +
                "\"price\":\"$\"," +
                "\"display_phone\":\"(415) 775-2525\"," +
                "\"is_closed\":false," +
                "\"rating\":4.0," +
                "\"review_count\":322," +
                "\"transactions\":[\"pickup\",\"delivery\"]," +
                "\"location\":{\"address1\":\"1534 California St\",\"address2\":\"\",\"city\":\"San Francisco\",\"zip_code\":\"94109\",\"country\":\"US\",\"display_address\":[\"1534 California St\",\"San Francisco, CA 94109\"]}," +
                "\"coordinates\":{\"latitude\":37.79085,\"longitude\":-122.41979}}");
```
