package io.github.stewseo.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch._types.aggregations.StringTermsBucket;
import io.github.stewseo.clients.elasticsearch.ElasticsearchService;
import io.github.stewseo.clients.elasticsearch.ElasticsearchServiceCtx;

import java.util.List;

public class ElasticsearchTestCase {

    public static final String INDEX_NYC = "yelp-fusion-businesses-restaurants-sf";

    public final ElasticsearchService elasticsearchService;

    public List<StringTermsBucket> termsAggregationByCategory;

    public static int docsCount;

    public String index;

    public ElasticsearchTestCase() {
        index = INDEX_NYC;
        ElasticsearchAsyncClient asyncClient = io.github.stewseo.clients.elasticsearch.ElasticsearchServiceCtx.createElasticsearchAsyncClient();
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
