import io.github.stewseo.lowlevel.restclient.PrintUtils;
import io.github.stewseo.lowlevel.restclient.Request;
import io.github.stewseo.lowlevel.restclient.Response;
import io.github.stewseo.lowlevel.restclient.RestClient;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.RequestLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class LLRestClientTestCase implements HttpRequestTestCase {

    // connect to host for each
    private static RestClient restClient;

    private static Process process;

    public static void connect() {
        String yelpFusionHost = "api.yelp.com";
        int port = 443;
        HttpHost httpHost = new HttpHost(yelpFusionHost, port, "https");

        Header[] defaultHeaders = {new BasicHeader("Authorization", "Bearer " + System.getenv("YELP_API_KEY"))};

        restClient = RestClient.builder(httpHost)
                .setMetaHeaderEnabled(false)
                .setDefaultHeaders(defaultHeaders)
                .build();

    }

    public static RestClient restClient() {
        return restClient;
    }


    public void testResponseCode(Response response) {

    }

    static String buildTraceRequest(HttpUriRequest request, HttpHost host) throws IOException {

        String requestLine = "curl -XGET \"" + host + getUri(request.getRequestLine()) + "\"";

        String header = " -H \"Authorization: Bearer " + System.getenv("YELP_API_KEY") + "\"";
        requestLine += header;

        if (request instanceof HttpEntityEnclosingRequest enclosingRequest) {
            if (enclosingRequest.getEntity() != null) {
                requestLine += " -d '";
                HttpEntity entity = enclosingRequest.getEntity();
                if (!entity.isRepeatable()) {
                    entity = new BufferedHttpEntity(enclosingRequest.getEntity());
                    enclosingRequest.setEntity(entity);
                }
                requestLine += EntityUtils.toString(entity, StandardCharsets.UTF_8) + "'";
            }
        }
        return requestLine;
    }

    private static String getUri(RequestLine requestLine) {
        if (requestLine.getUri().charAt(0) != '/') {
            return "/" + requestLine.getUri();
        }
        return requestLine.getUri();
    }

    @Override
    public void testRequestLine() {

    }

    @Override
    public void testRequestWithCurl() {

    }

    @Override
    public void testHeaders() {

    }
}
