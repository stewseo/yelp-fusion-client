package io.github.stewseo.clients.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.aggregations.StringTermsBucket;
import co.elastic.clients.elasticsearch._types.aggregations.TermsAggregation;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.SourceFilter;
import co.elastic.clients.elasticsearch.core.search.TotalHits;
import co.elastic.clients.json.JsonData;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.stewseo.clients.yelpfusion._types.Category;
import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetails;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class ElasticsearchService {

    int MAX_RESULTS = 10000;

    private long timestamp;

    private final ElasticsearchAsyncClient asyncClient;

    public ElasticsearchService(ElasticsearchAsyncClient client) {
        this.asyncClient = client;
    }

    public ElasticsearchAsyncClient getAsyncClient() {
        return asyncClient;
    }

    public List<Long> getTimestamp(Long timestamp, String index, SortOrder sortOrder) {

        String field = "timestamp";

        SourceFilter sourceFilter = buildSourceFilter(field);

        Query byTimestamp = buildRangeQuery(field, timestamp)._toQuery();

        SortOptions sortOptions = buildSortOptions(field, sortOrder);

        try {
            return asyncClient.search(s -> s
                                    .index(index)
                                    .query(q -> q
                                            .bool(b -> b
                                                    .must(byTimestamp) // greater than or equal to range parameter
                                            )
                                    ).source(src -> src
                                            .filter(sourceFilter)

                                    ).sort(sortOptions)
                                    .size(1)

                            , ObjectNode.class
                    ).get()
                    .hits()
                    .hits()
                    .stream()
                    .filter(hit -> hit.source() != null)
                    .map(Hit::source)
                    .map(source -> Long.parseLong(source.toString()))
                    .toList();

        } catch (Exception e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    public Long getTimestamp() {
        return timestamp;
    }


    public SearchResponse<BusinessDetails> occurences(String index, int size,
                                                      MatchQuery matchQuery,
                                                      RangeQuery rangeQuery
    ) {

        Query byCategory = matchQuery._toQuery();

        Query byTimestamp = rangeQuery._toQuery();

        SearchResponse<JsonData> response = null;

        try {
            return asyncClient.search(s -> s
                            .index(index)
                            .query(q -> q
                                    .bool(b -> b
                                            .must(byCategory)
                                            .must(byTimestamp)
                                    )
                            )
                            .size(size),
                    BusinessDetails.class // Using Void will ignore any document in the response.
            ).get();
        } catch (ExecutionException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    /**
     * @param size  Integer size number of docs to return
     * @param index String index
     * @return a multi-bucket value source based aggregation where buckets are dynamically built - one per unique value.
     */
    public List<StringTermsBucket> termsAggregationByCategory(Integer size, String index) {

        if (size == null) {
            size = MAX_RESULTS;
        }

        // Dynamically build each unique bucket by field: all alias
        TermsAggregation termsAggregation = buildTermsAggregation(Category.MappingProperties.ALIAS.jsonValue(), size);

        SearchResponse<Void> response = null;

        try {
            response = asyncClient.search(b -> b
                            .index(index)
                            .size(0) // Set the number of matching documents to zero
                            .aggregations("buildTermsAggregation", a -> a // Create an aggregation named "all-aggs"
                                    .terms(termsAggregation) // Select the terms aggregation variant.
                            ),
                    Void.class // Using Void will ignore any document in the response.
            ).get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return response.aggregations()
                .get("buildTermsAggregation")
                .sterms()
                .buckets().array();
    }

    public List<StringTermsBucket> termsAggregationByCategory(Integer size) {

        if (size == null) {
            size = MAX_RESULTS;
        }
        return termsAggregationByCategory(size, null);
    }


    // return all business ids up to 10k, starting at specified timestamp.
    public List<Hit<ObjectNode>> searchWithRangeQuery(String timestampRange) {

        Query byTimestamp = RangeQuery.of(r -> r
                .field("timestamp")
                .gte(JsonData.of(timestampRange))
        )._toQuery();

        SourceFilter sourceFilter = SourceFilter.of(source -> source
                .includes("id")
                .includes("timestamp")
        );

        SortOptions sortOptions = SortOptions.of(options -> options
                .field(f -> f
                        .field("timestamp")
                        .order(SortOrder.Asc))
        );

        SearchResponse<ObjectNode> response;

        List<Hit<ObjectNode>> nodes;

        try {
            response = asyncClient.search(s -> s
                            .index("yelp-businesses-restaurants-nyc")
                            .query(q -> q
                                    .bool(b -> b
                                            .must(byTimestamp)
                                    )
                            ).source(src -> src
                                    .filter(sourceFilter)
                            )
                            .sort(sortOptions)
                            .size(10000)
                    , ObjectNode.class
            ).get();

            TotalHits total = response.hits().total();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return response.hits().hits();
    }

    public int docsCount(String index) {
        try {

            return Integer.parseInt(Objects.requireNonNull(asyncClient.cat().count(c -> c
                            .index(index)
                    ).get()
                    .valueBody()
                    .get(0)
                    .count()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private TermsAggregation buildTermsAggregation(String field, int size) {
        return TermsAggregation.of(t -> t
                .field(field)
                .size(size)
        );
    }

    public RangeQuery buildRangeQuery(String field, long timestamp) {
        return RangeQuery.of(r -> r
                .field(field)
                .gte(JsonData.of(timestamp))
        );
    }

    public MatchAllQuery buildMatchAllQuery(String queryName) {
        return MatchAllQuery.of(m -> m
                .queryName(queryName)
        );
    }

    private SourceFilter buildSourceFilter(String field) {
        return SourceFilter.of(source -> source
                .includes(field));

    }

    private SortOptions buildSortOptions(String field, SortOrder sortOrder) {
        return SortOptions.of(options -> options
                .field(f -> f
                        .field(field)
                        .order(sortOrder))
        );
    }

}





