### restaurant-recommender-java provides a java-client to
- Build yelp fusion request objects
- Handle http transport using the java 11 http client
- Parse json results from the response body
- Map json data to objects
- Create and store documents in Elasticsearch


#### Build a request, parsing the response, and mapping to a Business object

```
int docsCount = elasticSearch.getDocsCount(indexNyc);

Map<String, List<String>> map = elasticSearch.getCategoriesMap(indexNyc, maxResults, timestamp)

Map<String, Set<String>> categoriesMap = yelpClient.getCategoriesMap(index, docsCount, SortOrder.Asc);

assertThat(categoriesMap.keySet().size()).isEqualTo(310);

for(String category: categoriesMap.values()
        .stream()
        .flatMap(Set::stream).collect(Collectors.toSet())) {

                do {
                    final int finalOffset = offset;

                    BusinessSearchResponse<Business> businessSearchResponse = yelpClient.businessSearch(s -> s
                                    .location("nyc")
                                    .term("restaurants")
                                    .categories(c -> c
                                            .alias(category))
                                    .sort_by(sort_by)
                                    .offset(finalOffset)
                                    .limit(limit)
                            , Business.class);
                            
                    total = businessSearchResponse.total();