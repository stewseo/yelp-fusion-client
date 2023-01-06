package io.github.stewseo.clients.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch._types.aggregations.StringTermsBucket;

import java.util.List;

public abstract class ElasticsearchTestCase {

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
