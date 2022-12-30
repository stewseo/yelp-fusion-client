package io.github.stewseo.client.transport;

import io.github.stewseo.client.transport.restclient.EsRestClientOptions;
import io.github.stewseo.client.transport.restclient.EsRestClientTransport;
import io.github.stewseo.client.transport.restclient.TestElasticsearchClient;
import io.github.stewseo.lowlevel.restclient.EsRestClient;
import io.github.stewseo.lowlevel.restclient.RequestOptions;
import org.junit.jupiter.api.Assertions;
import io.github.stewseo.client.json.SimpleJsonpMapper;
import io.github.stewseo.client.transport.endpoints.BooleanResponse;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpServer;
import org.apache.http.HttpHost;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static io.github.stewseo.client.transport.Version.VERSION;


class EsRestClientOptionsTest extends Assertions {

    /** Collected headers by test name */
    private static Map<String, Headers> collectedHeaders;

    private static final AtomicInteger testCounter = new AtomicInteger();

    private static HttpServer httpServer;

    private static final String MIME_TYPE = "application/vnd.elasticsearch+json; compatible-with=" + VERSION.major();

    @BeforeAll
    public static void setup() throws IOException {
        collectedHeaders = new ConcurrentHashMap<>();
        httpServer = HttpServer.create(new InetSocketAddress(InetAddress.getLoopbackAddress(), 0), 0);

        // Register a handler on the core.exists("capture-handler/{name}") endpoint that will capture request headers.
        httpServer.createContext("/capture-headers/_doc/", exchange -> {
            String testName = exchange.getRequestURI().getPath().substring("/capture-headers/_doc/".length());
            collectedHeaders.put(testName, exchange.getRequestHeaders());

            // Reply with an empty 200 response
            exchange.getResponseHeaders().set("X-Elastic-Product", "Elasticsearch");
            exchange.sendResponseHeaders(200, -1);
            exchange.close();
        });

        httpServer.start();
    }

    @AfterAll
    public static void cleanup() {
        httpServer.stop(0);
        httpServer = null;
        collectedHeaders = null;
    }

    /**
     * Make a server call, capture request headers and check their consistency.
     *
     * @return the name of the entry in <code>collectedHeaders</code> for further inspection.
     */
    private String checkHeaders(TestElasticsearchClient testClient) throws IOException {
        String testName = "test-" + testCounter.incrementAndGet();

        assertTrue(testClient.exists(testName).value());

        Headers headers = collectedHeaders.get(testName);
        assertNotNull(headers, "No headers collected for test " + testName);

        assertNotNull(headers.get("X-elastic-client-meta"), "Missing client meta header");
        assertEquals(EsRestClientOptions.CLIENT_META_VALUE, headers.get("X-elastic-client-meta").get(0));
        assertNotNull(headers.get("Accept"), "Missing 'Accept' header");
        assertEquals(MIME_TYPE, headers.get("Accept").get(0));

        for (Map.Entry<String, List<String>> entry: headers.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        return testName;
    }

    private void checkUserAgent(String testName, String value) {
        Headers headers = collectedHeaders.get(testName);
        assertNotNull(headers, "No headers collected for test " + testName);
        assertNotNull(headers.get("User-Agent"), "Missing 'User-Agent' header");
        assertEquals(value, headers.get("User-Agent").get(0));
    }

    @Test
    void testNoRequestOptions() throws Exception {

//        EsRestClient llrc = EsRestClient.builder(
//                new HttpHost(httpServer.getAddress().getHostString(), httpServer.getAddress().getPort(), "http")
//        ).build();

//        EsRestClientTransport transport = new EsRestClientTransport(llrc, new SimpleJsonpMapper());
//        TestElasticsearchClient esClient = new TestElasticsearchClient(transport);
//
//        String id = checkHeaders(esClient);
//        checkUserAgent(id, EsRestClientOptions.USER_AGENT_VALUE);
    }

    @Test
    void testTransportRequestOptions() throws Exception {
        EsRestClient llrc = EsRestClient.builder(
                new HttpHost(httpServer.getAddress().getHostString(), httpServer.getAddress().getPort(), "http")
        ).build();

        EsRestClientTransport transport = new EsRestClientTransport(llrc, new SimpleJsonpMapper(),
                new EsRestClientOptions.Builder(RequestOptions.DEFAULT.toBuilder()).build()
        );
        TestElasticsearchClient esClient = new TestElasticsearchClient(transport);

        String id = checkHeaders(esClient);
        checkUserAgent(id, EsRestClientOptions.USER_AGENT_VALUE);
    }

    @Test
    void testClientRequestOptions() throws Exception {
        EsRestClient llrc = EsRestClient.builder(
                new HttpHost(httpServer.getAddress().getHostString(), httpServer.getAddress().getPort(), "http")
        ).build();

        EsRestClientTransport transport = new EsRestClientTransport(llrc, new SimpleJsonpMapper());
        TestElasticsearchClient esClient = new TestElasticsearchClient(transport).withTransportOptions(
                new EsRestClientOptions.Builder(RequestOptions.DEFAULT.toBuilder()).build()
        );

        String id = checkHeaders(esClient);

        checkUserAgent(id, EsRestClientOptions.USER_AGENT_VALUE);
    }

    @Test
    void testLambdaOptionsBuilder() throws Exception {
        EsRestClient llrc = EsRestClient.builder(
                new HttpHost(httpServer.getAddress().getHostString(), httpServer.getAddress().getPort(), "http")
        ).build();

        EsRestClientTransport transport = new EsRestClientTransport(llrc, new SimpleJsonpMapper());
        TestElasticsearchClient esClient = new TestElasticsearchClient(transport)
                .withTransportOptions(o -> o
                        .addHeader("Foo", "bar")
                        .addHeader("x-elastic-client-meta", "foo-bar-client")
                );

        String id = checkHeaders(esClient);
        checkUserAgent(id, EsRestClientOptions.USER_AGENT_VALUE);
    }

    @Test
    void testRequestOptionsOverridingBuiltin() throws Exception {
        RequestOptions options = RequestOptions.DEFAULT.toBuilder()
                .addHeader("user-agent", "FooBarAgent/1.0")
                .addHeader("x-elastic-client-meta", "foo-bar-client")
                .build();

        EsRestClient llrc = EsRestClient.builder(
                new HttpHost(httpServer.getAddress().getHostString(), httpServer.getAddress().getPort(), "http")
        ).build();

        EsRestClientTransport transport = new EsRestClientTransport(llrc, new SimpleJsonpMapper(), new EsRestClientOptions(options));
        TestElasticsearchClient esClient = new TestElasticsearchClient(transport);
        // Should not override client meta
        String id = checkHeaders(esClient);
        // overriding user-agent is ok
        checkUserAgent(id, "FooBarAgent/1.0");
    }
}