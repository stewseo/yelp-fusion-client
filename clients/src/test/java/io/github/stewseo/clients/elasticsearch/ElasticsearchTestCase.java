package io.github.stewseo.clients.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch._types.aggregations.StringTermsBucket;
import org.junit.jupiter.api.Tag;

import java.util.List;

public class ElasticsearchTestCase {

    public static final String INDEX_NYC = "yelp-fusion-businesses-restaurants-sf";

    public final ElasticsearchService elasticsearchService;

    public List<StringTermsBucket> termsAggregationByCategory;

    public static int docsCount;

    public String index;

    public ElasticsearchTestCase() {
        index = INDEX_NYC;
        ElasticsearchAsyncClient asyncClient = ElasticsearchServiceCtx.createElasticsearchAsyncClient();
        elasticsearchService = new ElasticsearchService(asyncClient);
    }

    public int getDocsCount() {
        return elasticsearchService.docsCount(index);
    }

    public List<StringTermsBucket> getStringTermsBucketsByCategory() {

        int numCategoriesWithParentRestaurant = 320;

        return elasticsearchService.termsAggregationByCategory(numCategoriesWithParentRestaurant);
    }
}
