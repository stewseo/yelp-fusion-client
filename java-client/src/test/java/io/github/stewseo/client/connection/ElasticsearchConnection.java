package io.github.stewseo.client.connection;


import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import io.github.stewseo.client.elasticsearch.ElasticsearchService;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

// build all requests before transporting
public abstract class ElasticsearchConnection {
    public static final String timestampPipeline = "timestamp-pipeline";
    public static final String testIndex = "yelp-test-index";
    public static String postalPipeline = "postal-lookup";
    public static ElasticsearchService elasticSearchService;

    public static ElasticsearchService createElasticsearchService() {

        String host = "1ff0acb6626441789a7e846726159410.us-east-2.aws.elastic-cloud.com";
        int port = 443;
        String scheme = "https";
        String apiKey = System.getenv("API_KEY_ID");
        String apiKeySecret = System.getenv("API_KEY_SECRET");

        RestClient restClient = createRestClient(host, port, scheme, apiKey, apiKeySecret);

        co.elastic.clients.transport.ElasticsearchTransport transport = new RestClientTransport(restClient, new co.elastic.clients.json.jackson.JacksonJsonpMapper());

        ElasticsearchAsyncClient client = new ElasticsearchAsyncClient(transport);

        return new ElasticsearchService(client);
    }

    public static RestClient createRestClient(String host, int port, String scheme, String apiKeyId, String apiKeySecret) {

        String apiKeyIdAndSecret = apiKeyId + ":" + apiKeySecret;

        String encodedApiKey = Base64.getEncoder() // The encoder maps the input to a set of characters in the A-Za-z0-9+/ character set
                .encodeToString((apiKeyIdAndSecret) // Encodes the specified byte array into a String using the Base64 encoding scheme.
                        .getBytes(StandardCharsets.UTF_8));

        RestClientBuilder builder = RestClient.builder(new HttpHost(host, port, scheme));

        Header[] defaultHeaders = {new BasicHeader("Authorization", "ApiKey " + encodedApiKey)};

        builder.setDefaultHeaders(defaultHeaders);

        return builder.build();
    }

    public ElasticsearchService getElasticsearchService() {
        return elasticSearchService;
    }

}

