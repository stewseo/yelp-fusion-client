package org.example.yelp.fusion.client.restclient;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.RequestLine;
import org.apache.http.StatusLine;
import org.apache.http.message.BasicHeader;
import org.example.elasticsearch.client.transport.restclient.RestClientTransport;
import org.example.lowlevel.restclient.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class RestClientTest {
    private static final Logger logger = LoggerFactory.getLogger(RestClientTest.class);
    private static RestClient restClient;
    static RequestOptions YELP_AUTHORIZATION_HEADER;

    @BeforeAll
    static void beforeAll() {
        HttpHost host = new HttpHost("api.yelp.com", 80, "http");
        Header[] defaultHeaders = {new BasicHeader("Authorization", "Bearer " + System.getenv("YELP_API_KEY"))};

        RestClientBuilder builder = RestClient.builder(
                        host)
                .setMetaHeaderEnabled(false)
                .setUserAgentEnable(false)
                .setDefaultHeaders(defaultHeaders);

        restClient = builder.build();

        RequestOptions.Builder optionsBuilder = RequestOptions.DEFAULT.toBuilder();
        optionsBuilder.addHeader("Authorization", "Bearer " + System.getenv("YELP_API_KEY"));
        YELP_AUTHORIZATION_HEADER = optionsBuilder.build();

    }

    @Test
    void httpHostTest() {
        HttpHost host = restClient.getNodes().get(0).getHost();
        assertThat(host.getHostName()).isEqualTo("api.yelp.com");
        assertThat(host.getPort()).isEqualTo(80);
        assertThat(host.getSchemeName()).isEqualTo("http");
    }

    @Test
    void httpClientTest() {
        logger.info(PrintUtils.cyan("RestClient isRunning: " + restClient.isRunning()));
    }

    @Test
    void uriTest() {
        String method = "GET";
        String endpoint = "businesses/search";
        Request request = new Request(method, endpoint);
        Map<String, String> params = Map.of("location", "cabo");

        request.addParameters(params);

        request.setOptions(YELP_AUTHORIZATION_HEADER);

        assertThat(request.getMethod()).isEqualTo("GET");

        assertThat(request.getOptions()).isEqualTo(YELP_AUTHORIZATION_HEADER);

        String cleanedParamKey = request
                .getParameters()
                .keySet()
                .stream()
                .toList()
                .get(0)
                .replaceAll("[^A-Za-z]", "");

        assertThat(cleanedParamKey).isEqualTo("location");

    }

    @Test
    void performBusinessSearchRequestTest() {
        Request request = new Request("GET", "v3/businesses/search");
        Map<String, String> params = Map.of("location", "nyc", "categories", "pizza");
        request.addParameters(params);

        try {
            Response response = restClient.performRequest(request);
            assertThat(response).isNotNull();

            StatusLine statusLine = response.getStatusLine();


            assertThat(statusLine.getStatusCode()).isEqualTo(200);
            assertThat(statusLine.getReasonPhrase()).isEqualTo("OK");
            assertThat(statusLine.getProtocolVersion().toString()).isEqualTo("HTTP/1.1");

            RequestLine requestLine = response.getRequestLine();
            assertThat(requestLine.toString()).isEqualTo("Get uri http/1.1");
            assertThat(response.getEntity()).isNotNull();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void performBusinessDetailsRequestTest() {
        Request request = new Request("GET", "v3/businesses/wu3w6IlUct9OvYmYXDMGJA");


        logger.info("request.getOptions.getHeaders: " + request.getOptions().getHeaders());

        try {
            Response response = restClient.performRequest(request);
            assertThat(response).isNotNull();

            StatusLine statusLine = response.getStatusLine();


            assertThat(statusLine.getStatusCode()).isEqualTo(200);
            assertThat(statusLine.getReasonPhrase()).isEqualTo("OK");
            assertThat(statusLine.getProtocolVersion().toString()).isEqualTo("HTTP/1.1");

            RequestLine requestLine = response.getRequestLine();
            assertThat(requestLine.toString()).isEqualTo("Get uri http/1.1");
            assertThat(response.getEntity()).isNotNull();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
