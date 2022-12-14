package io.github.stewseo.clients.functional;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.stewseo.clients.elasticsearch.ElasticsearchTestCase;
import io.github.stewseo.clients.json.JsonData;
import io.github.stewseo.clients.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.clients.transport.restclient.RestClientTransport;
import io.github.stewseo.clients.yelpfusion.YelpFusionClient;
import io.github.stewseo.clients.yelpfusion._types.Category;
import io.github.stewseo.clients.yelpfusion._types.Result;
import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetails;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessRequest;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessResponse;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessResult;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionClientTestCase;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static io.github.stewseo.clients.yelpfusion._types.TestData.FIELD_TIMESTAMP;
import static io.github.stewseo.clients.yelpfusion._types.TestData.INDEX_SEARCH_SF_RESTAURANTS;
import static io.github.stewseo.clients.yelpfusion._types.TestData.INDEX_SF_RESTAURANTS;
import static io.github.stewseo.clients.yelpfusion._types.TestData.LIMIT;
import static io.github.stewseo.clients.yelpfusion._types.TestData.LOCALE;
import static io.github.stewseo.clients.yelpfusion._types.TestData.MAX_RESULTS;
import static io.github.stewseo.clients.yelpfusion._types.TestData.TIMESTAMP_PIPELINE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FunctionalTest extends YelpFusionClientTestCase {

    private ElasticsearchAsyncClient esAsyncClient;
    private static YelpFusionClient yelpFusionClient;

    private final RestClientTransport restClientTransport = restClientTransport();
    static int offset = 0;
    static int total = 0;

    private Set<String> categoriesGt = new HashSet<>();

    @Test
    public void testSearchBusinessRequest() throws Exception {

        yelpFusionClient = new YelpFusionClient(restClientTransport());

        Set<Category> categories = categoriesWithRestaurantParent();

        esAsyncClient = new ElasticsearchTestCase().elasticsearchService.getAsyncClient();

        for(Category category: categories) {
            offset = 0;
            total = 0;

            do {

                SearchBusinessRequest searchBusinessRequest = searchBusinessRequest(category, offset);

                SearchBusinessResponse searchBusinessResponse = yelpFusionClient.businesses()
                        .businessSearch(searchBusinessRequest);

                assertThat(searchBusinessResponse).isNotNull();

                total = searchBusinessResponse.total();

                assertThat(total).isGreaterThanOrEqualTo(0).isLessThanOrEqualTo(1000);

                List<SearchBusinessResult> results = searchBusinessResponse.businesses();

                for(SearchBusinessResult searchBusinessResult: results) {

                    String businessId = searchBusinessResult
                            .id().replaceAll("[^\\p{IsAlphabetic}\\p{IsDigit}_\\-]", "");

                    IndexRequest<JsonData> request = IndexRequest.of(i -> i
                            .index(INDEX_SEARCH_SF_RESTAURANTS)
                            .id(businessId)
                            .pipeline(TIMESTAMP_PIPELINE)
                            .document(JsonData.of(searchBusinessResult.toString()))
                    );

                    IndexResponse response = esAsyncClient.index(request).get();

                    assertThat(response.result()).isNotEqualTo(Result.NotFound);
                }

                offset += LIMIT;

                if(offset >= 950) {
                    categoriesGt.add(category.alias());
                }

            } while (offset <= 1000 && offset < (total + LIMIT));
        }

        System.out.println(categoriesGt.size() + " , categories gt 1000 results: " + categoriesGt.toString());
    }

    private SearchBusinessRequest searchBusinessRequest(Category category, int offset) {

        return SearchBusinessRequest.of(s -> s
                .term("restaurants")
                .limit(LIMIT)
                .offset(offset)
                .location("san+francisco")
                .categories(c -> c
                        .alias(category.alias()))
                .sort_by("review_count")
        );
    }

    @Test
    public void testBusinessDetailsRequest() throws Exception {

        esAsyncClient = new ElasticsearchTestCase().elasticsearchService.getAsyncClient();

        SearchResponse<ObjectNode> searchResponse = searchWithMatchAllQuery();

        yelpFusionClient = new YelpFusionClient(restClientTransport());

        searchResponse.hits().hits().stream()
                .map(Hit::id)
                .map(this::testBusinessDetails)
                .map(this::indexResponse);

        searchResponse.hits().hits().forEach(hit -> {

            String id = hit.id();

            try {

                BusinessDetails businessDetails = testBusinessDetails(id);

                indexResponse(businessDetails);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private BusinessDetails testBusinessDetails(String businessId)  {

        try {
            return yelpFusionClient.businesses().businessDetails(b -> b
                            .id(businessId))
                    .result();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private CompletableFuture<Object> indexResponse(BusinessDetails businessDetails) {

        JsonNode node = asJsonNode(businessDetails);

        return esAsyncClient.index(i -> i
                .index(INDEX_SF_RESTAURANTS)
                .id(businessDetails.id())
                .pipeline(TIMESTAMP_PIPELINE)
                .document(node)
        ).whenComplete((response, exception) -> {
            if (exception != null) {
                assertThat(response.result()).isEqualTo(Result.NotFound);
                System.out.println("log failed IndexResponse: " + exception);
            } else {
                assertThat(response.result()).isNotEqualTo(Result.NotFound);
            }
        }).thenApply(f -> f.result());
    }

    private JsonNode asJsonNode(BusinessDetails businessDetails) {
        try {
            return new JacksonJsonpMapper()
                    .objectMapper()
                    .readValue(businessDetails.toString()
                            , ObjectNode.class
                    );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private Set<Category> categoriesWithRestaurantParent() {

        Set<Category> restCats = new HashSet<>();
        try {
            List<Category> categories = yelpFusionClient
                    .categories().all(c -> c
                            .locale(LOCALE))
                    .categories();

            for(Category category: categories) {
                if (category.parent_aliases() != null) {
                    for (String parent : category.parent_aliases()) {
                        if (parent.equals("restaurants")) {
                            restCats.add(category);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return restCats;
    }

    private SearchResponse<ObjectNode> searchWithMatchAllQuery() {
        try {
            return esAsyncClient.search(s -> s
                    .query(q -> q
                            .matchAll(m -> m
                                    .queryName("_id"))
                    ).size(MAX_RESULTS)
                    ,
                    ObjectNode.class).get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private SearchResponse<ObjectNode> searchWithRangeQueryGte(String timestamp) {

        Query queryByTimestamp = rangeQueryGteTimestamp(timestamp)._toQuery();

        try {
            return esAsyncClient.search(s -> s
                            .query(q -> q
                                    .bool(b -> b
                                            .must(queryByTimestamp)
                                    )
                            )
                            .size(MAX_RESULTS)
                    ,
                    ObjectNode.class).get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private RangeQuery rangeQueryGteTimestamp(String timestamp) {

        return RangeQuery.of(r -> r
                .field(FIELD_TIMESTAMP)
                .gte(co.elastic.clients.json.JsonData.of(timestamp))
        );
    }

    private RangeQuery rangeQueryLteTimestamp(String timestamp) {

        return RangeQuery.of(r -> r
                .field(FIELD_TIMESTAMP)
                .lte(co.elastic.clients.json.JsonData.of(timestamp))
        );
    }

}
