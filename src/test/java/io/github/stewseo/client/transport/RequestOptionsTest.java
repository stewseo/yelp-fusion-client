package io.github.stewseo.client.transport;

import com.sun.net.httpserver.HttpServer;
import io.github.stewseo.client.json.jsonb.JsonbJsonpMapper;
import io.github.stewseo.client.transport.restclient.EsRestClientTransport;
import io.github.stewseo.client.transport.restclient.TestElasticsearchClient;
import io.github.stewseo.lowlevel.restclient.EsRestClient;
import io.github.stewseo.lowlevel.restclient.ResponseException;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RequestOptionsTest extends Assertions {

    private static HttpServer httpServer;
    private static EsRestClient restClient;

    @BeforeEach
    public void classSetup() throws IOException {

        httpServer = HttpServer.create(new InetSocketAddress(InetAddress.getLoopbackAddress(), 0), 0);
        httpServer.createContext("/", ex -> {
            if (ex.getRequestMethod().equals("HEAD")) {
                // Call to ping()
                ex.sendResponseHeaders(200, -1);
            }

            // Call to info()
            // Send back all request headers with a 418 that will cause an exception where we can access the LLRC response
            ex.getResponseHeaders().putAll(ex.getRequestHeaders());
            ex.sendResponseHeaders(418, 0);
            OutputStreamWriter out = new OutputStreamWriter(ex.getResponseBody(), StandardCharsets.UTF_8);
            for (Map.Entry<String, List<String>> header: ex.getRequestHeaders().entrySet()) {
                out.write("header-");
                out.write(header.getKey().toLowerCase(Locale.ROOT));
                out.write("=");
                out.write(header.getValue().get(0));
                out.write("\n");
            }
            final List<NameValuePair> params = URLEncodedUtils.parse(ex.getRequestURI(), String.valueOf(StandardCharsets.UTF_8));
            for (NameValuePair param: params) {
                out.write("param-");
                out.write(param.getName());
                out.write("=");
                out.write(param.getValue());
            }
            out.close();
        });

        httpServer.start();
        InetSocketAddress address = httpServer.getAddress();
        restClient = EsRestClient.builder(new HttpHost(address.getHostString(), address.getPort(), "http"))
                .build();
    }

    @AfterEach
    public void classTearDown() throws IOException {
        httpServer.stop(0);
        restClient.close();
    }

    private Properties getProps(TestElasticsearchClient client) throws IOException {
        ResponseException ex = assertThrows(ResponseException.class, client::info);
        assertEquals(418, ex.getResponse().getStatusLine().getStatusCode());
        Properties result = new Properties();
        result.load(ex.getResponse().getEntity().getContent());
        return result;
    }

    @Test
    public void testNonNullClientOptions() {

        final EsRestClientTransport trsp = new EsRestClientTransport(restClient, new JsonbJsonpMapper());
        final TestElasticsearchClient client = new TestElasticsearchClient(trsp);

        assertNotNull(client._transportOptions());
        assertSame(trsp.options(), client._transportOptions());
    }

    @Test
    public void testDefaultHeaders() throws IOException {
        final EsRestClientTransport trsp = new EsRestClientTransport(restClient, new JsonbJsonpMapper());
        final TestElasticsearchClient client = new TestElasticsearchClient(trsp);

        Properties props = getProps(client);

        assertTrue(props.getProperty("header-user-agent").startsWith("elastic-java/" + Version.VERSION.toString()));
        assertTrue(props.getProperty("header-x-elastic-client-meta").contains("es="));
        assertTrue(props.getProperty("header-x-elastic-client-meta").contains("hl=2"));
        assertEquals(
                "application/vnd.elasticsearch+json; compatible-with=" + Version.VERSION.major(),
                props.getProperty("header-accept")
        );
    }

    @Test
    public void testClientHeader() throws IOException {
        final EsRestClientTransport trsp = new EsRestClientTransport(restClient, new JsonbJsonpMapper());
        final TestElasticsearchClient client = new TestElasticsearchClient(trsp)
                .withTransportOptions(b -> b
                        .addHeader("X-Foo", "Bar")
                        .addHeader("uSer-agEnt", "MegaClient/1.2.3")
                );

        Properties props = getProps(client);
        assertThat("Bar").isEqualTo(props.getProperty("header-x-foo"));
        assertEquals("MegaClient/1.2.3", props.getProperty("header-user-agent"));
    }

    @Test
    public void testQueryParameter() throws IOException {
        final EsRestClientTransport trsp = new EsRestClientTransport(restClient, new JsonbJsonpMapper());
        final TestElasticsearchClient client = new TestElasticsearchClient(trsp)
                .withTransportOptions(trsp.options().with(
                                b -> b.setParameter("format", "pretty")
                        )
                );

        Properties props = getProps(client);
        assertEquals("pretty", props.getProperty("param-format"));
    }

    @Test
    public void testMissingProductHeader() {
        final EsRestClientTransport trsp = new EsRestClientTransport(restClient, new JsonbJsonpMapper());
        final TestElasticsearchClient client = new TestElasticsearchClient(trsp);

        final TransportException ex = assertThrows(TransportException.class, client::ping);
        assertTrue(ex.getMessage().contains("Missing [X-Elastic-Product] header"));
    }
}