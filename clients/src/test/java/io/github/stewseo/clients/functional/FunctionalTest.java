package io.github.stewseo.clients.functional;//package io.github.stewseo.clients.functional;
//
//import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
//import co.elastic.clients.elasticsearch._types.SortOptions;
//import co.elastic.clients.elasticsearch._types.SortOrder;
//import co.elastic.clients.elasticsearch._types.query_dsl.Query;
//import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
//import co.elastic.clients.elasticsearch.core.IndexRequest;
//import co.elastic.clients.elasticsearch.core.IndexResponse;
//import co.elastic.clients.elasticsearch.core.SearchResponse;
//import co.elastic.clients.elasticsearch.core.search.Hit;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//import io.github.stewseo.clients.elasticsearch.ElasticsearchService;
//import io.github.stewseo.clients.elasticsearch.ElasticsearchTestCase;
//import io.github.stewseo.clients.json.JsonData;
//import io.github.stewseo.clients.json.jackson.JacksonJsonpMapper;
//import io.github.stewseo.clients.transport.restclient.RestClientTransport;
//import io.github.stewseo.clients.yelpfusion.YelpFusionClient;
//import io.github.stewseo.clients.yelpfusion._types.Category;
//import io.github.stewseo.clients.yelpfusion._types.Result;
//import io.github.stewseo.clients.yelpfusion._types.SortBy;
//import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetails;
//import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessRequest;
//import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessResponse;
//import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessResult;
//import io.github.stewseo.clients.yelpfusion.testcases.FunctionalTestCase;
//import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionClientTestCase;
//import io.github.stewseo.clients.yelpfusion.testcases.context.YelpFusionTestService;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.FIELD_TIMESTAMP;
//import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.INDEX_SEARCH_SF_RESTAURANTS;
//import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.INDEX_SF_RESTAURANTS;
//import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.LIMIT;
//import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.LOCALE;
//import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.MAX_RESULTS;
//import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.META_FIELD_ID;
//import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.TERM_RESTAURANTS;
//import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.TIMESTAMP_PIPELINE;
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//public class FunctionalTest extends FunctionalTestCase {
//
//    private ElasticsearchAsyncClient esAsyncClient;
//    private static YelpFusionClient yelpFusionClient;
//
//    static int offset = 0;
//
//    static int total = 0;
//
//    private Set<String> categoriesGt = new HashSet<>();
//
//    @Test
//    public void testSearchBusinessRequest() throws Exception {
//
//        yelpFusionClient = getYelpFusionClient();
//
//        Set<Category> categories = categoriesWithRestaurantParent();
//
//        esAsyncClient = new ElasticsearchTestCase().elasticsearchService.getAsyncClient();
//
//        for(Category category: categories) {
//
//            offset = 0;
//
//            total = 0;
//
//            do {
//
//                SearchBusinessRequest searchBusinessRequest = searchBusinessRequest(category, offset);
//
//                SearchBusinessResponse searchBusinessResponse = yelpFusionClient.businesses()
//                        .businessSearch(searchBusinessRequest);
//
//                assertThat(searchBusinessResponse).isNotNull();
//
//                total = searchBusinessResponse.total();
//
//                assertThat(total).isGreaterThanOrEqualTo(0).isLessThanOrEqualTo(1000);
//
//                List<SearchBusinessResult> results = searchBusinessResponse.businesses();
//
//                for(SearchBusinessResult searchBusinessResult: results) {
//
//                    String businessId = searchBusinessResult
//                            .id().replaceAll("[^\\p{IsAlphabetic}\\p{IsDigit}_\\-]", "");
//
//                    IndexRequest<JsonData> request = IndexRequest.of(i -> i
//                            .index(INDEX_SEARCH_SF_RESTAURANTS)
//                            .id(businessId)
//                            .pipeline(TIMESTAMP_PIPELINE)
//                            .document(JsonData.of(searchBusinessResult.toString()))
//                    );
//
//                    IndexResponse response = esAsyncClient.index(request).get();
//
//                    assertThat(response.result()).isNotEqualTo(Result.NotFound);
//                }
//
//                offset += LIMIT;
//
//                if(offset >= 950) {
//                    categoriesGt.add(category.alias());
//                }
//
//            } while (offset <= 1000 && offset < (total + LIMIT));
//        }
//
//        System.out.println(categoriesGt.size() + " , categories gt 1000 results: " + categoriesGt.toString());
//    }
//
//    private SearchBusinessRequest searchBusinessRequest(Category category, int offset) {
//
//        return SearchBusinessRequest.of(s -> s
//                .term(TERM_RESTAURANTS)
//                .limit(LIMIT)
//                .offset(offset)
//                .location("san+francisco")
//                .categories(c -> c
//                        .alias(category.alias()))
//                .sort_by(SortBy.REVIEW_COUNT.name())
//        );
//    }
//
//    @Test
//    public void testBusinessDetailsRequest() throws Exception {
//
//        esAsyncClient = new ElasticsearchTestCase().elasticsearchService.getAsyncClient();
//
//        // get id of doc with greatest timestamp
////        String id = searchWithMatchAllQuery(FIELD_TIMESTAMP, MAX_RESULTS, SortOrder.Asc)
////                .hits()
////                .hits()
////                .get(0)
////                .id();
//
//        // build range query of timestamps greater than or equal timestamp
//        Query byTimestamp = RangeQuery.of(r -> r
//                .field(FIELD_TIMESTAMP)
//                .gte(co.elastic.clients.json.JsonData.of("2023-01-09T07:21:58.817173731Z")))._toQuery();
//
//        // search restaurants-sf index with range query by timestamp
//        SearchResponse<ObjectNode> searchResponse = searchWithRangeQueryGte(INDEX_SEARCH_SF_RESTAURANTS, byTimestamp);
//
//        yelpFusionClient = getYelpFusionClient();
//
//        List<co.elastic.clients.elasticsearch._types.Result> results = searchResponse.hits().hits().stream()
//                .map(Hit::id)
//                .map(this::testBusinessDetails) // BusinessDetailsRequest
//                .map(this::indexResponse)
//                .toList(); // IndexRequest
//
//        System.out.println("results.size(): " + results.size());
//    }
//
//    private SearchResponse<ObjectNode> searchWithMatchAllQuery(String queryName, int size, SortOrder sortOrder) {
//
//        ElasticsearchService elasticsearchService = new ElasticsearchService(esAsyncClient);
//
//        SortOptions byTimestampDesc = elasticsearchService.buildSortOptions(FIELD_TIMESTAMP, sortOrder);
//
//        try {
//            return esAsyncClient.search(s -> s
//                            .query(q -> q
//                                    .matchAll(m -> m
//                                            .queryName(queryName))
//                            ).size(size)
//                            .sort(byTimestampDesc)
//                    ,
//                    ObjectNode.class).get();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private BusinessDetails testBusinessDetails(String businessId)  {
//
//        try {
//            return yelpFusionClient.businesses().businessDetails(b -> b
//                            .id(businessId))
//                    .result();
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private co.elastic.clients.elasticsearch._types.Result indexResponse(BusinessDetails businessDetails) {
//
//        JsonNode jsonNode = asJsonNode(businessDetails);
//
//        return esAsyncClient.index(i -> i
//                .index(INDEX_SF_RESTAURANTS)
//                .id(businessDetails.id())
//                .pipeline(TIMESTAMP_PIPELINE)
//                .document(jsonNode)
//        ).whenComplete((response, exception) -> {
//            if (exception != null) {
//                assertThat(response.result()).isEqualTo(Result.NotFound);
//                System.out.println("log failed IndexResponse: " + exception);
//            } else {
//                assertThat(response.result()).isNotEqualTo(Result.NotFound);
//            }
//        }).thenApply(e -> e.result()).join();
//    }
//
//    private SearchResponse<ObjectNode> searchWithMatchAllQuery() {
//        try {
//            return esAsyncClient.search(s -> s
//                    .query(q -> q
//                            .matchAll(m -> m
//                                    .queryName(META_FIELD_ID))
//                    ).size(MAX_RESULTS)
//                    ,
//                    ObjectNode.class).get();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private SearchResponse<ObjectNode> searchWithRangeQueryGte(String index, Query queryByTimestamp) {
//
//        try {
//            return esAsyncClient.search(s -> s
//                            .query(q -> q
//                                    .bool(b -> b
//                                            .must(queryByTimestamp)
//                                    )
//                            )
//                            .index(index)
//                            .size(MAX_RESULTS)
//                    ,
//                    ObjectNode.class).get();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private JsonNode asJsonNode(BusinessDetails businessDetails) {
//        try {
//            return new JacksonJsonpMapper()
//                    .objectMapper()
//                    .readValue(businessDetails.toString()
//                            , ObjectNode.class
//                    );
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private Set<Category> categoriesWithRestaurantParent() {
//
//        Set<Category> restCats = new HashSet<>();
//        try {
//            List<Category> categories = yelpFusionClient
//                    .categories().all(c -> c
//                            .locale(LOCALE))
//                    .categories();
//
//            for(Category category: categories) {
//                if (category.parent_aliases() != null) {
//                    for (String parent : category.parent_aliases()) {
//                        if (parent.equals("restaurants")) {
//                            restCats.add(category);
//                        }
//                    }
//                }
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return restCats;
//    }
//
//}
