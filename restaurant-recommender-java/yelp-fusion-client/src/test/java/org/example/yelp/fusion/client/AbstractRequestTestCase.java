package org.example.yelp.fusion.client;


import org.apache.http.*;
import org.apache.http.message.*;
import org.example.elasticsearch.client.json.jackson.*;
import org.example.lowlevel.restclient.*;
import org.example.yelp.fusion.client.transport.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.net.http.*;
import java.time.*;

// build all requests before transporting
public abstract class AbstractRequestTestCase extends Assertions {

    @BeforeAll
    static void beforeAll() throws IOException {
        initYelpFusionClient();
        initElasticsearchClient();
    }

    protected static org.example.elasticsearch.client.json.jackson.JacksonJsonpMapper mapper;
    protected static YelpFusionClient yelpClient;
    public static String timestampPipeline = "timestamp-pipeline"; // format:
    public static String geoPointPipeline = "geo_location-pipeline";

    public static String indexNyc = "yelp-businesses-restaurants-nyc";

    private static void initYelpFusionClient() throws IOException {

        mapper = new JacksonJsonpMapper();
        String yelpFusionHost = "api.yelp.com";
        int port = 443;

        // create rest client
        RestClientBuilder restBuilder = RestClient.builder(new HttpHost("api.yelp.com/v3"))
                .setMetaHeaderEnabled(false)
                .setHttpClient(new HttpClientImpl(
                        HttpClient.newBuilder()
                                .followRedirects(HttpClient.Redirect.NORMAL)
                                .connectTimeout(Duration.ofSeconds(10))
                                .version(HttpClient.Version.HTTP_2)
                                .build()
                        )
                );

        Header[] defaultHeaders = {new BasicHeader("Authorization", "Bearer " + System.getenv("YELP_API_KEY"))};

        restBuilder.setDefaultHeaders(defaultHeaders);

        YelpRestTransport yelpTransport;

        try (RestClient restClient = restBuilder.build()) {

            yelpTransport = new YelpRestTransport(restClient, mapper);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        yelpClient = new YelpFusionClient(yelpTransport);
    }

    protected static Elasticsearch elasticSearch;

    private static void initElasticsearchClient() {
        elasticSearch = Elasticsearch.getInstance();

        String host = "my-deployment-e7b376.es.us-west-1.aws.found.io";
        int port = 443;
        String scheme = "https";
        String apiKey = System.getenv("API_KEY_ID");
        String apiKeySecret = System.getenv("API_KEY_SECRET");
        org.elasticsearch.client.RestClient restClient = elasticSearch.createRestClient(host, port, scheme, apiKey, apiKeySecret);

        co.elastic.clients.transport.ElasticsearchTransport transport = elasticSearch.createTransport(restClient, new co.elastic.clients.json.jackson.JacksonJsonpMapper());

        elasticSearch.createESClient(transport);
    }
}

