package io.github.stewseo.lowlevel.restclient;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.RequestLine;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public abstract class LLRestClientTestCase {

    // connect to host for each
    private static RestClient restClient;

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

}
