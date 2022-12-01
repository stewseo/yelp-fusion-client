package io.github.yelp.fusion.client;


import io.github.yelp.fusion.client.json.jackson.JacksonJsonpMapper;
import io.github.yelp.fusion.client.yelpfusion.YelpFusionClient;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// build all requests before transporting
public abstract class ElasticsearchRequestTestCase extends Assertions {

    protected static Logger logger = LoggerFactory.getLogger(ElasticsearchRequestTestCase.class);

    protected static JacksonJsonpMapper mapper;

    protected static YelpFusionClient yelpClient;
    public static String timestampPipeline = "timestamp-pipeline";

    public static String postalPipeline = "postal-lookup";
    public static String indexNyc = "yelp-businesses-restaurants-nyc-new";

    protected static Elasticsearch elasticSearch;

    public static void initElasticsearchClient() {
        elasticSearch = Elasticsearch.getInstance();

        String host = "my-deployment-357c12.es.us-west-1.aws.found.io";
        int port = 443;
        String scheme = "https";
        String apiKey = System.getenv("API_KEY_SECRET");
        String apiKeySecret = System.getenv("API_KEY_SECRET");
        org.elasticsearch.client.RestClient restClient = elasticSearch.createRestClient(host, port, scheme, apiKey, apiKeySecret);

        co.elastic.clients.transport.ElasticsearchTransport transport = elasticSearch.createTransport(restClient, new co.elastic.clients.json.jackson.JacksonJsonpMapper());

        elasticSearch.createESClient(transport);
    }
}

