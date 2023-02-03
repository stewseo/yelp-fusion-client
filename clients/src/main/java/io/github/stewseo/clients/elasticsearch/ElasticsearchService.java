package io.github.stewseo.clients.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.elasticsearch._types.aggregations.StringTermsBucket;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.cat.IndicesResponse;
import co.elastic.clients.elasticsearch.cat.indices.IndicesRecord;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.SourceFilter;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;
public class ElasticsearchService {

    int MAX_RESULTS = 10000;

    private final ElasticsearchAsyncClient asyncClient;

    public ElasticsearchService(ElasticsearchAsyncClient client) {
        this.asyncClient = client;
    }

    public <T>void createOrUpdate(String indexName, String id, T document) {
        // Create or update a document in an index.
        IndexRequest<T> req = IndexRequest.of(i -> i
                .index(indexName)
                .id(id)
                .pipeline("timestamp-pipeline")
                .document(document)
        );

        asyncClient.index(req);
    }

    /**
     * @param size  Integer size number of docs to return
     * @param index String index
     * @return a multi-bucket value source based aggregation where buckets are dynamically built - one per unique value.
     */
    public List<StringTermsBucket> termsAggregation(String term, Integer size, String index) {

        if (size == null) {
            size = MAX_RESULTS;
        }

        // Dynamically build each unique bucket by field: all alias
        Aggregation termsAggregation = AggregationBuilderUtils.termsAggregation(term, size);

        final String termsAggsName = "termsAggregation";

        return asyncClient.search(b -> b
                                .index(index)
                                .size(0) // Set the number of matching documents to zero
                                .aggregations(termsAggsName, termsAggregation)
                        , Void.class // Using Void will ignore any document in the response.
                )
                .join()
                .aggregations()
                .get(termsAggsName)
                .sterms()
                .buckets()
                .array();

    }


    public List<Hit<ObjectNode>> searchWithRangeQuery(String timestampRange, String index) {

        String timestampField = "timestamp";

        Query byTimeStamp = QueryBuilderUtils.rangeQueryByTimestamp(timestampField, timestampRange);

        SourceFilter sourceFilter = SourceFilter.of(source -> source
                .includes("id")
                .includes(timestampField)
        );

        SortOptions sortOptions = SortOptions.of(options -> options
                .field(f -> f
                        .field(timestampField)
                        .order(SortOrder.Asc))
        );

        return asyncClient.search(s -> s
                        .index(index)
                        .query(q -> q
                                .bool(b -> b
                                        .must(byTimeStamp)
                                )
                        ).source(src -> src
                                .filter(sourceFilter)
                        )
                        .sort(sortOptions)
                        .size(MAX_RESULTS)
                , ObjectNode.class
        )
                .join()
                .hits()
                .hits();

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





