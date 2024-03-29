package io.github.stewseo.lowlevel.restclient;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.message.BasicHeader;

import javax.net.ssl.SSLContext;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public final class RestClientBuilder {

    public static final int DEFAULT_CONNECT_TIMEOUT_MILLIS = 1000;
    public static final int DEFAULT_SOCKET_TIMEOUT_MILLIS = 30000;
    public static final int DEFAULT_MAX_CONN_PER_ROUTE = 10;
    public static final int DEFAULT_MAX_CONN_TOTAL = 30;
    private static final Header[] EMPTY_HEADERS = new Header[0];
    private final HttpHost host;
    private Header[] defaultHeaders = EMPTY_HEADERS;

//    private final List<Node> nodes;
//    private NodeSelector nodeSelector = NodeSelector.ANY;

    private HttpClientConfigCallback httpClientConfigCallback;
    private RequestConfigCallback requestConfigCallback;
    private String pathPrefix;
    private boolean strictDeprecationMode = false;
    private boolean compressionEnabled = false;

    RestClientBuilder(String apiKey) {
        if (apiKey == null) {
            throw new IllegalArgumentException("apiKey must not be null");
        }
        String yelpFusionHost = "api.yelp.com";
        int port = 80;
        host = new HttpHost(yelpFusionHost, port, "http");

        Header[] defaultHeaders = {new BasicHeader("Authorization", "Bearer " + apiKey)};
        setDefaultHeaders(defaultHeaders);
    }

    RestClientBuilder(HttpHost host) {
        if (host == null) {
            throw new IllegalArgumentException("host must not be null");
        }
        this.host = host;
    }

    public RestClientBuilder setDefaultHeaders(Header[] defaultHeaders) {
        Objects.requireNonNull(defaultHeaders, "defaultHeaders must not be null");
        for (Header defaultHeader : defaultHeaders) {
            Objects.requireNonNull(defaultHeader, "default header must not be null");
        }
        this.defaultHeaders = defaultHeaders;
        return this;
    }

    public RestClientBuilder setHttpClientConfigCallback(HttpClientConfigCallback httpClientConfigCallback) {
        Objects.requireNonNull(httpClientConfigCallback, "httpClientConfigCallback must not be null");
        this.httpClientConfigCallback = httpClientConfigCallback;
        return this;
    }

    public RestClientBuilder setRequestConfigCallback(RequestConfigCallback requestConfigCallback) {
        Objects.requireNonNull(requestConfigCallback, "requestConfigCallback must not be null");
        this.requestConfigCallback = requestConfigCallback;
        return this;
    }

    public RestClientBuilder setPathPrefix(String pathPrefix) {
        this.pathPrefix = cleanPathPrefix(pathPrefix);
        return this;
    }

    public static String cleanPathPrefix(String pathPrefix) {
        Objects.requireNonNull(pathPrefix, "pathPrefix must not be null");

        if (pathPrefix.isEmpty()) {
            throw new IllegalArgumentException("pathPrefix must not be empty");
        }

        String cleanPathPrefix = pathPrefix;
        if (!cleanPathPrefix.startsWith("/")) {
            cleanPathPrefix = "/" + cleanPathPrefix;
        }

        // best effort to ensure that it looks like "/base/path" rather than "/base/path/"
        if (cleanPathPrefix.endsWith("/") && cleanPathPrefix.length() > 1) {
            cleanPathPrefix = cleanPathPrefix.substring(0, cleanPathPrefix.length() - 1);

            if (cleanPathPrefix.endsWith("/")) {
                throw new IllegalArgumentException("pathPrefix is malformed. too many trailing slashes: [" + pathPrefix + "]");
            }
        }
        return cleanPathPrefix;
    }

    public RestClientBuilder setStrictDeprecationMode(boolean strictDeprecationMode) {
        this.strictDeprecationMode = strictDeprecationMode;
        return this;
    }

    public RestClientBuilder setCompressionEnabled(boolean compressionEnabled) {
        this.compressionEnabled = compressionEnabled;
        return this;
    }

    public RestClient build() {

        CloseableHttpAsyncClient httpClient = createHttpClient();

        RestClient restClient = new RestClient(
                httpClient,
                defaultHeaders,
                host,
                pathPrefix,
                strictDeprecationMode,
                compressionEnabled
        );

        httpClient.start();
        return restClient;
    }


    private CloseableHttpAsyncClient createHttpClient() {

        // default timeouts are all infinite
        RequestConfig.Builder requestConfigBuilder = RequestConfig.custom()
                .setConnectTimeout(DEFAULT_CONNECT_TIMEOUT_MILLIS)
                .setSocketTimeout(DEFAULT_SOCKET_TIMEOUT_MILLIS);
        if (requestConfigCallback != null) {
            requestConfigBuilder = requestConfigCallback.customizeRequestConfig(requestConfigBuilder);
        }

        try {
            HttpAsyncClientBuilder httpClientBuilder = HttpAsyncClientBuilder.create()
                    .setDefaultRequestConfig(requestConfigBuilder.build())
                    // default settings for connection pooling may be too constraining
                    .setMaxConnPerRoute(DEFAULT_MAX_CONN_PER_ROUTE)
                    .setMaxConnTotal(DEFAULT_MAX_CONN_TOTAL)
                    .setSSLContext(SSLContext.getDefault())
                    .setTargetAuthenticationStrategy(new PersistentCredentialsAuthenticationStrategy());


            if (httpClientConfigCallback != null) {
                httpClientBuilder = httpClientConfigCallback.customizeHttpClient(httpClientBuilder);
            }

            final HttpAsyncClientBuilder finalBuilder = httpClientBuilder;
            return finalBuilder.build();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("could not create the default ssl context", e);
        }
    }

    @FunctionalInterface
    public interface RequestConfigCallback {
        RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder);
    }

    @FunctionalInterface
    public interface HttpClientConfigCallback {
        HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder);
    }
}

