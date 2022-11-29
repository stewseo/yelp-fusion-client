package org.example.yelp.fusion.client;


import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.example.elasticsearch.client.json.jackson.JacksonJsonpMapper;
import org.example.lowlevel.restclient.HttpClientImpl;
import org.example.lowlevel.restclient.RestClient;
import org.example.lowlevel.restclient.RestClientBuilder;
import org.example.yelp.fusion.client.transport.YelpRestTransport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.http.HttpClient;
import java.time.Duration;

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

    private static void initYelpFusionClient() throws IOException {

        String hostName = "api.yelp.com";
        int port = 80;
        String scheme = "http";
        HttpHost host = new HttpHost(hostName, port, scheme);

        Header[] defaultHeaders = {new BasicHeader("Authorization", "Bearer " + System.getenv("YELP_API_KEY"))};

        RestClientBuilder restBuilder = RestClient.builder(
                        host)
                .setMetaHeaderEnabled(false)
                .setUserAgentEnable(false)
                .setDefaultHeaders(defaultHeaders);

        mapper = new JacksonJsonpMapper();
        YelpRestTransport yelpTransport;

        try (RestClient restClient = restBuilder.build()) {

            yelpTransport = new YelpRestTransport(restClient, mapper);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        logger.info("yelp client: " + yelpClient);
        yelpClient = new YelpFusionClient(yelpTransport);
    }

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

