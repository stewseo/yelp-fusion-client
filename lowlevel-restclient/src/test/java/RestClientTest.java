import io.github.stewseo.lowlevel.restclient.Node;
import io.github.stewseo.lowlevel.restclient.PrintUtils;
import io.github.stewseo.lowlevel.restclient.Request;
import io.github.stewseo.lowlevel.restclient.RestClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.AuthCache;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.nio.client.methods.HttpAsyncMethods;
import org.apache.http.nio.protocol.HttpAsyncRequestProducer;
import org.apache.http.nio.protocol.HttpAsyncResponseConsumer;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RestClientTest extends LLRestClientTestCase {

    private static final Logger logger = LoggerFactory.getLogger(RestClientTest.class);

    final String SCHEME = "https";

    final String HOST = "api.yelp.com";

    final int PORT = 443;

    // https://swagger.io/docs/specification/2-0/api-host-and-base-path/

    final String API_BASE_URL = SCHEME + "://" + HOST + ":" + PORT + "/v3";

    final String BEARER_APIKEY = "Bearer " + System.getenv("YELP_API_KEY");

    @Test
    public void executeHttpRequestBaseTest() {

        String uri = "http://api.yelp.com/v3/businesses/search?location=sf";

        try(CloseableHttpAsyncClient client = HttpAsyncClients.createDefault()) {

            client.start();

            HttpRequestBase httpRequestBase = new HttpGet(uri);

            HttpResponse response = client.execute(httpRequestBase, null).get();

            client.close();

            assertThat(response.getStatusLine().toString()).isEqualTo("HTTP/1.1 200 OK");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static final Node node = new Node(new HttpHost("api.yelp.com"));
    static final Request request = new Request("Get", "v3/businesses/search");


    private HttpEntity sendRequestAsync() throws Exception {
        CloseableHttpAsyncClient client = HttpAsyncClients.createDefault();

        client.start();
        AuthCache authCache = new BasicAuthCache();

        URI uri = new URI("v3/businesses/wu3w6IlUct9OvYmYXDMGJA");

        URI u = RestClient.buildUri(null, "v3/businesses/wu3w6IlUct9OvYmYXDMGJA", new HashMap<>());

        HttpRequestBase httpRequestBase = RestClient.createHttpRequest(request.getMethod(), u, request.getEntity(), true);

        httpRequestBase.setHeader("Authorization", "Bearer " + System.getenv("YELP_API_KEY"));


        HttpAsyncRequestProducer requestProducer = HttpAsyncMethods.create(node.getHost(), httpRequestBase);

        HttpAsyncResponseConsumer<HttpResponse> asyncResponseConsumer = request.getOptions()
                .getHttpAsyncResponseConsumerFactory()
                .createHttpAsyncResponseConsumer();


        HttpClientContext context = HttpClientContext.create();

        context.setAuthCache(authCache);

        Future<HttpResponse> future = client.execute(requestProducer, asyncResponseConsumer, context, null);

        HttpResponse httpResponse = future.get();
        client.close();
        return httpResponse.getEntity();
    }

}
