package io.github.stewseo.clients.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.aggregations.StringTermsBucket;
import co.elastic.clients.elasticsearch._types.aggregations.TermsAggregation;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import co.elastic.clients.elasticsearch.cat.IndicesResponse;
import co.elastic.clients.elasticsearch.cat.indices.IndicesRecord;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.HitsMetadata;
import co.elastic.clients.elasticsearch.core.search.SourceFilter;
import co.elastic.clients.json.JsonData;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.stewseo.clients.yelpfusion._types.MappingProperties;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static io.github.stewseo.clients.yelpfusion._types.QueryParam.TIMESTAMP;
public class ElasticsearchService {

    int MAX_RESULTS = 10000;

    private final ElasticsearchAsyncClient asyncClient;

    public ElasticsearchService(ElasticsearchAsyncClient client) {
        this.asyncClient = client;
    }


    // accepts a timestamp, an index name or alias, sortOrder
    // returns HitsMetaData
    public HitsMetadata<ObjectNode> searchWithRangeQuery(String timestamp, String index, SortOrder sortOrder) {

        SourceFilter sourceFilter = sourceFilter(TIMESTAMP.name());

        Query byTimestamp = rangeQueryGteTimestamp(timestamp)._toQuery();

        SortOptions sortOptions = sortOptions(TIMESTAMP.name(), sortOrder);

        return asyncClient.search(s -> s
                                .index(index)
                                .query(q -> q
                                        .bool(b -> b
                                                .must(byTimestamp)
                                        )
                                ).source(src -> src
                                        .filter(sourceFilter)

                                ).sort(sortOptions)
                                .size(1)
                        , ObjectNode.class
                ).join()
                .hits();
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
        TermsAggregation termsAggregation = termsAggregation(MappingProperties.ALIAS.jsonValue(), size);

        final String buildTermsAggs = "termsAggregation";

        try {
            return asyncClient.search(b -> b
                                    .index(index)
                                    .size(0) // Set the number of matching documents to zero
                                    .aggregations(buildTermsAggs, a -> a // Create an aggregation named "all-aggs"
                                            .terms(termsAggregation) // Select the terms aggregation variant.
                                    )
                            , Void.class // Using Void will ignore any document in the response.
                    )
                    .get()
                    .aggregations()
                    .get(buildTermsAggs)
                    .sterms()
                    .buckets().array();


        } catch (ExecutionException e) {
            throw new RuntimeException(e);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    // return all business ids up to 10k, starting at specified timestamp.
    public List<Hit<ObjectNode>> searchWithRangeQuery(String timestampRange, String index) {

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

        try {
            return asyncClient.search(s -> s
                            .index(index)
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
            ).get().hits().hits();

        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return null;
    }

    public int docsCount(String index) {

        IndicesRecord indicesRecord = asyncClient.cat().indices(indicesReq -> indicesReq
                        .index(index)
                )
                .thenApply(IndicesResponse::valueBody)
                .join()
                .get(0);

        String docsCount = indicesRecord.docsCount();
        if (docsCount != null) {
            return Integer.parseInt(docsCount);
        }
        return 0;
    }

    private TermsAggregation termsAggregation(String field, int size) {
        return TermsAggregation.of(t -> t
                .field(field)
                .size(size)
        );
    }

    public RangeQuery rangeQuery(String field, long timestamp) {
        return RangeQuery.of(r -> r
                .field(field)
                .gte(JsonData.of(timestamp))
        );
    }

    private RangeQuery rangeQueryGteTimestamp(String timestamp) {

        return RangeQuery.of(r -> r
                .field(TIMESTAMP.name())
                .gte(JsonData.of(timestamp))
        );
    }

    public MatchAllQuery matchAllQuery(String queryName) {
        return MatchAllQuery.of(m -> m
                .queryName(queryName)
        );
    }

    private SourceFilter sourceFilter(String type) {

        return SourceFilter.of(source -> source
                .includes(type)
        );
    }

    public SortOptions sortOptions(String type, SortOrder sortOrder) {

        return SortOptions.of(options -> options
                .field(f -> f
                        .field(type)
                        .order(sortOrder))
        );
    }

}





