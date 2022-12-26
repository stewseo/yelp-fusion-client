package io.github.stewseo.yelp.fusion.client.end_to_end;

import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkResponseItem;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.stewseo.yelp.fusion.client.elasticsearch.ElasticsearchService;
import io.github.stewseo.yelp.fusion.client.connection.ElasticsearchConnection;
import io.github.stewseo.yelp.fusion.client.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.yelp.fusion.client.yelpfusion.YelpFusionClient;
import io.github.stewseo.yelp.fusion.client.yelpfusion.misc.AutoCompleteResponse;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.Business_;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;


public class ReadJsonTest {

    private static final Logger logger = LoggerFactory.getLogger(ReadJsonTest.class);
    public final String timestampPipeline = "timestamp-pipeline";

    public final String testIndex = "yelp-test-index";

    public static String index = "yelp-businesses-restaurants-nyc";

    private static ElasticsearchService elasticsearchService;

    @BeforeAll
    static void setup() {
        setOfBusinessIds = new HashSet<>();
        String host = "my-deployment-274408.es.us-east-2.aws.elastic-cloud.com";
        int port = 443;
        String scheme = "https";
        String apiKeyId = System.getenv("API_KEY_SECRET");
        String apiKeySecret = System.getenv("API_KEY_SECRET");

        ElasticsearchConnection.createElasticsearchService();

        bucketCategoryTest();
    }
    static Set<String> setOfBusinessIds;
    static String timestamp;

    static void bucketCategoryTest() {

        int docsCount = elasticsearchService.getDocsCount(index);

        int iterations = (int) Math.ceil((double) docsCount / 10000);

        for (int j = 0; j < iterations; j++) {

            List<Hit<ObjectNode>> nodes = elasticsearchService.getBusinessIdsWithTimestamps("1");

            for (int i = 0; i < nodes.size(); i++) {
                JsonNode sourceNode = nodes.get(i).source();

                if (sourceNode != null) {
                    setOfBusinessIds.add(sourceNode.get("id").asText());
                    if (i + 1 == nodes.size()) {
                        timestamp = sourceNode.get("timestamp").asText();
                    }
                }
            }

            logger.info("timestamp: " + timestamp);
            logger.info("setOfBusinessIds: " + setOfBusinessIds.size());
        }
    }


    @Test
    public void searchTest() throws Exception {

        String apiKey = System.getenv("YELP_API_KEY");

        YelpFusionClient yelpFusionClient = YelpFusionClient.createClient(apiKey);

        AutoCompleteResponse response = yelpFusionClient.autocomplete(a -> a.text("piz"));

        logger.info("all: " + response.categories());
        logger.info("businesses: " + response.businesses());
        logger.info("terms: " + response.terms());


//        ElasticsearchConnection.createElasticsearchService();
//        SearchResponse<ObjectNode> response = Elasticsearch.getInstance().client().search(s -> s
//                        .index(index)
//                        .storedFields("_none_")
//                        .query(q -> q
//                                .match(m -> m
//                                        .field("all.alias")
//                                        .query("japanese")
//                                )
//                        )
//                ,
//                ObjectNode.class
//        );
    }


    @Test
    public void readJsonTest() throws IOException {

        URL file = ReadJsonTest.class.getResource("nyc-restaurants.json");

        JsonNode node = new JacksonJsonpMapper().objectMapper().readValue(file, JsonNode.class);

        List<JsonNode> mNodes = new ArrayList<>();

        logger.info("node: " + node.get("businesses").size());

        List<JsonNode> sourceNodes = new ArrayList<>();

        for(JsonNode hit : node.get("businesses")) {
            if(hit.get("_source") != null) {
                sourceNodes.add(hit.get("_source"));
            }
        }

        logger.info("size : " + sourceNodes.size());

        JacksonJsonpMapper mapper = new JacksonJsonpMapper();
        List<Business_> bus = Arrays.stream(mapper.objectMapper().readValue(sourceNodes.toString(), Business_[].class)).toList();

        BulkRequest.Builder br = new BulkRequest.Builder();
        String index = "yelp-businesses-restaurants-nyc";

        for (Business_ business : bus) {

            if (setOfBusinessIds.add(business.getId())) {


                br.operations(op -> op
                        .index(idx -> idx
                                .index(index)
                                .id(index + "-" + business.getId())
                                .document(business)
                                .pipeline(timestampPipeline)
                        )
                );
            }

        }

        ElasticsearchConnection.createElasticsearchService();

        BulkResponse result = null;
        try {
            result = elasticsearchService.getAsyncClient().bulk(br.build()).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

// Log errors, if any
        if (result.errors()) {
            logger.error("Bulk had errors");
            for (BulkResponseItem item : result.items()) {
                if (item.error() != null) {
                    logger.error(item.error().reason());
                }
            }
        }

        logger.info("size : " + bus.size());

    }
}
