package io.github.yelpfusion.client.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch._types.aggregations.StringTermsBucket;
import io.github.stewseo.client.elasticsearch.ElasticsearchService;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public abstract class ElasticsearchTestCase {

    public static final String TIMESTAMP_PIPELINE = "timestamp-pipeline";

    public static final String INDEX_NYC = "yelp-businesses-restaurants-nyc";

    public final ElasticsearchService elasticsearchService;


    public List<StringTermsBucket> termsAggregationByCategory;

    public static int docsCount;

    public String index;

    public ElasticsearchTestCase() {
        this(null);
    }

    public ElasticsearchTestCase(String index) {
        if (index == null) {
            index = INDEX_NYC;
        } else {
            this.index = index;
        }
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

//    private void loadCategoriesMap() {
//
//        String timestamp = String.valueOf(elasticsearchService.getTimestamp(1L, SortOrder.Asc));
//
//        assertThat(timestamp).isEqualTo("2022-11-10T07:16:35.187452598Z");
//
//        setOfBusinessIds = new HashSet<>();
//
//        int iterations = (int) Math.ceil((double) docsCount / 10000);
//
//        for (int j = 0; j < iterations; j++) {
//
//            List<Hit<ObjectNode>> nodes = elasticsearchService.getBusinessIdsWithTimestamps(timestamp);
//
//            for (int i = 0; i < nodes.size(); i++) {
//                JsonNode sourceNode = nodes.get(i).source();
//
//                if (sourceNode != null) {
//                    setOfBusinessIds.add(sourceNode.get("id").asText());
//                    if (i + 1 == nodes.size()) {
//                        timestamp = sourceNode.get("timestamp").asText();
//                    }
//
//                }
//
//            }
//        }
//    }
}
