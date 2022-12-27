package io.github.stewseo.yelp.fusion.client.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.aggregations.StringTermsBucket;
import co.elastic.clients.elasticsearch._types.aggregations.TermsAggregation;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.SourceFilter;
import co.elastic.clients.elasticsearch.core.search.TotalHits;
import co.elastic.clients.json.JsonData;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.stewseo.yelp.fusion.client.yelpfusion.categories.Category;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class ElasticsearchService {

    int MAX_RESULTS = 10000;

    private String index;

    private long timestamp;

    private ElasticsearchAsyncClient asyncClient;

    public ElasticsearchService(ElasticsearchAsyncClient client) {
        this.asyncClient = client;
    }

    public ElasticsearchService(ElasticsearchAsyncClient client, String index, Long timestamp) {
        this.asyncClient = client;

        this.index = index;

        if(timestamp == null) {
            timestamp = getTimestamp(1L, SortOrder.Asc).get(0);
        }
    }


    public List<Long> getTimestamp(Long timestamp, SortOrder sortOrder) {

        String field = "timestamp";

        SourceFilter sourceFilter = buildSourceFilter(field);

        Query byTimestamp = buildRangeQuery(field, timestamp)._toQuery();

        SortOptions sortOptions = getSortOptions(field, sortOrder);

        try {
            List<ObjectNode> list = asyncClient.search(s -> s
                                    .index("yelp-businesses-restaurants-nyc")
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
                    .toList();

            for (ObjectNode source : list) {
                System.out.println(source);
            }

            List<Long> longList = list.stream().map(e -> Long.parseLong(e.toString())).toList();

            return longList;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private SourceFilter buildSourceFilter(String field) {
        return SourceFilter.of(source -> source
                .includes(field));

    }

    private SortOptions getSortOptions(String field, SortOrder sortOrder) {
        return SortOptions.of(options -> options
                .field(f -> f
                        .field(field)
                        .order(sortOrder))
        );
    }

    public Long getTimestamp() {
        return timestamp;
    }


    /**
     *
     * @param size Integer size number of docs to return
     * @param index String index
     * @return restaurants aggregated into categories
     */
    public List<StringTermsBucket> termsAggregationByCategory(Integer size, String index) {

        if (size == null) {
            size = MAX_RESULTS;
        }

        // Dynamically build each unique bucket by field: all alias
        TermsAggregation termsAggregation = buildTermsAggregation(Category.MappingProperties.ALIAS.jsonValue(), size);

        // match all documents containing the queryName: "all"
        Query matchAll = buildMatchAllQuery("*")._toQuery();

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
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return response.aggregations()
                .get("buildTermsAggregation")
                .sterms()
                .buckets().array();
    }

    public List<StringTermsBucket> termsAggregationByCategory(Integer size) {

        if(size == null) {
            size = MAX_RESULTS;
        }
        return termsAggregationByCategory(size, null);
    }

    // return all business ids up to 10k, starting at specified timestamp.
    public List<Hit<ObjectNode>> getBusinessIdsWithTimestamps(String timestampRange) {

        Query byTimestamp = RangeQuery.of(r -> r
                .field("timestamp")
                .gte(JsonData.of(timestampRange))
        )._toQuery();

//        Query matchAllQuery = MatchAllQuery.of(r -> r
//                .queryName("id")
//        )._toQuery();

        SourceFilter sourceFilter = SourceFilter.of(source -> source
                .includes("id")
                .includes("timestamp"));

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

    public int getDocsCount(String index) {
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

    public ElasticsearchAsyncClient getAsyncClient() {
      return asyncClient;
    }


    public MatchAllQuery buildMatchAllQuery(String queryName) {
        return MatchAllQuery.of(m -> m
                .queryName(queryName)
        );
    }

    public RangeQuery buildRangeQuery(String field, long timestamp) {
        return RangeQuery.of(r -> r
                .field(field)
                .gte(JsonData.of(timestamp))
        );
    }

}




