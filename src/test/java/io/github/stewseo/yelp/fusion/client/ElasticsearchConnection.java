package io.github.stewseo.yelp.fusion.client;


import io.github.stewseo.yelp.fusion.client.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.yelp.fusion.client.yelpfusion.YelpFusionClient;
import org.junit.jupiter.api.Assertions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// build all requests before transporting
public abstract class ElasticsearchConnection extends Assertions {

    protected static Logger logger = LoggerFactory.getLogger(ElasticsearchConnection.class);

    protected static JacksonJsonpMapper mapper;

    protected static YelpFusionClient yelpClient;
    public static final String timestampPipeline = "timestamp-pipeline";

    public static String postalPipeline = "postal-lookup";

    public static final String testIndex = "yelp-test-index";

    protected static Elasticsearch elasticSearch;

    public static void initElasticsearchClient() {

        elasticSearch = Elasticsearch.getInstance();

        String host = "1ff0acb6626441789a7e846726159410.us-east-2.aws.elastic-cloud.com";
        int port = 443;
        String scheme = "https";
        String apiKey = System.getenv("API_KEY_ID");
        String apiKeySecret = System.getenv("API_KEY_SECRET");
        org.elasticsearch.client.RestClient restClient = elasticSearch.createRestClient(host, port, scheme, apiKey, apiKeySecret);

        co.elastic.clients.transport.ElasticsearchTransport transport = elasticSearch.createTransport(restClient, new co.elastic.clients.json.jackson.JacksonJsonpMapper());

        elasticSearch.createESClient(transport);
    }
}

