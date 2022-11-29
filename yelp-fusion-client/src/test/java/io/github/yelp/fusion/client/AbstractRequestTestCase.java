package io.github.yelp.fusion.client;


import io.github.elasticsearch.client.json.jackson.JacksonJsonpMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

// build all requests before transporting
public abstract class AbstractRequestTestCase extends Assertions {

    protected static Logger logger = LoggerFactory.getLogger(AbstractRequestTestCase.class);

    @BeforeAll
    static void beforeAll() throws IOException {
        initElasticsearchClient();
    }

    protected static JacksonJsonpMapper mapper;

    protected static YelpFusionClient yelpClient;
    public static String timestampPipeline = "timestamp-pipeline";

    public static String postalPipeline = "postal-lookup";
    public static String indexNyc = "yelp-businesses-restaurants-nyc";

    protected static Elasticsearch elasticSearch;

    private static void initElasticsearchClient() {
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

