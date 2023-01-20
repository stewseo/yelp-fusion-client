package io.github.stewseo.clients.yelpfusion;

import co.elastic.clients.elasticsearch._types.Result;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.WriteResponseBase;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.stewseo.clients.elasticsearch.ElasticsearchService;
import io.github.stewseo.clients.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.clients.yelpfusion._types.Category;
import io.github.stewseo.clients.yelpfusion._types.QueryParameter;
import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetails;
import io.github.stewseo.clients.yelpfusion.testcases.FunctionalTestCase;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RequestBusinessDetailsAndStoreTest extends FunctionalTestCase {
    private static final String INDEX_SF_RESTAURANTS = "yelp-fusion-restaurants-sf";

    // Build a CategoriesRequest and filter categories: "parent_alias":"restaurants".
    // Build a SearchBusinessRequest for each category with "parent_alias":"restaurants"
    // Build a BusinessDetailsRequest for each id contained in each SearchBusinessResult
    // Build an IndexRequest for each BusinessDetailsResult and store as a JSON document.
    @Test
    public void testBusinessDetailsRequest() {

        // build SearchRequest with SortOptions.desc.
        // Returns a document containing the greatest timestamp from index: restaurants-sf
        // searchBusinesses for the document containing the unique _id field from index: searchBusinesses-business-results-sf
        // searchBusinesses for all documents with a timestamp field greater than or equal to the matched document.

        Query byTimestamp = RangeQuery.of(r -> r
                .field("timestamp")
                .gte(co.elastic.clients.json.JsonData.of("2023-01-09T07:24:55.561678124Z")))._toQuery();


        SearchResponse<ObjectNode> searchResponse = searchWithRangeQueryGte("yelp-fusion-restaurants-sf-search", byTimestamp);

        List<ObjectNode> list = searchResponse.hits().hits().stream()
                .map(hit ->
                        Objects.requireNonNull(hit.source())
                )
                .toList();


        list.stream().map(objectNode -> objectNode.get("id").asText())
                .map(this::testBusinessDetails)
                .map(this::indexResponse);



    }

    private SearchResponse<ObjectNode> searchWithMatchAllQuery(String queryName, int size, SortOrder sortOrder) {

        ElasticsearchService elasticsearchService = new ElasticsearchService(esAsyncClient);

        SortOptions byTimestampDesc = elasticsearchService.buildSortOptions("timestamp", sortOrder);

        try {
            return esAsyncClient.search(s -> s
                            .query(q -> q
                                    .matchAll(m -> m
                                            .queryName(queryName))
                            ).size(size)
                            .sort(byTimestampDesc)
                    ,
                    ObjectNode.class).get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    private co.elastic.clients.elasticsearch._types.Result indexResponse(BusinessDetails businessDetails) {

        JsonNode jsonNode = asJsonNode(businessDetails);

        return esAsyncClient.index(i -> i
                .index(INDEX_SF_RESTAURANTS)
                .id(businessDetails.id())
                .pipeline("timestamp-pipeline")
                .document(jsonNode)
        ).whenComplete((response, exception) -> {
            if (exception != null) {
                assertThat(response.result()).isEqualTo(io.github.stewseo.clients.yelpfusion._types.Result.NotFound);
                System.out.println("log failed IndexResponse: " + exception);
            } else {
                assertThat(response.result()).isNotEqualTo(io.github.stewseo.clients.yelpfusion._types.Result.NotFound);
            }
        }).thenApply(WriteResponseBase::result).join();
    }

//    private SearchResponse<ObjectNode> searchWithMatchAllQuery() {
//        try {
//            return esAsyncClient.search(s -> s
//                            .query(q -> q
//                                    .matchAll(m -> m
//                                            .queryName(META_FIELD_ID.name()))
//                            ).size(MAX_RESULTS)
//                    ,
//                    ObjectNode.class).get();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    private SearchResponse<ObjectNode> searchWithRangeQueryGte(String index, Query queryByTimestamp) {

        try {
            return esAsyncClient.search(s -> s
                            .query(q -> q
                                    .bool(b -> b
                                            .must(queryByTimestamp)
                                    )
                            )
                            .index(index)
                            .size(10000)
                    ,
                    ObjectNode.class).get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

}
