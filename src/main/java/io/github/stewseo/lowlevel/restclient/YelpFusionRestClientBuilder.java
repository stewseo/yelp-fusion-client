package io.github.stewseo.lowlevel.restclient;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.VersionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Properties;

public final class YelpFusionRestClientBuilder {

    Logger logger = LoggerFactory.getLogger(YelpFusionRestClientBuilder.class);
    public static final int DEFAULT_CONNECT_TIMEOUT_MILLIS = 1000;
    public static final int DEFAULT_SOCKET_TIMEOUT_MILLIS = 30000;
    public static final int DEFAULT_MAX_CONN_PER_ROUTE = 10;
    public static final int DEFAULT_MAX_CONN_TOTAL = 30;

    public static final String VERSION;

    private static final Header[] EMPTY_HEADERS = new Header[0];

    private final HttpHost host;
    private Header[] defaultHeaders = EMPTY_HEADERS;
    private HttpClientConfigCallback httpClientConfigCallback;
    private RequestConfigCallback requestConfigCallback;
    private String pathPrefix;
    private boolean strictDeprecationMode = false;
    private boolean compressionEnabled = false;
    private boolean metaHeaderEnabled = true;

    public static boolean userAgentEnable = true;


    static {

        // Never fail on unknown version, even if an environment messed up their classpath enough that we can't find it.
        // Better have incomplete telemetry than crashing user applications.
        String version = null;
        try (InputStream is = YelpFusionRestClient.class.getResourceAsStream("version.properties")) {
            if (is != null) {
                Properties versions = new Properties();
                versions.load(is);
                version = versions.getProperty("elasticsearch-client");
            }
        } catch (IOException e) {
            // Keep version unknown
        }

        if (version == null) {
            version = ""; // unknown values are reported as empty strings in X-Elastic-Client-Meta
        }

        VERSION = version;

        VersionInfo httpClientVersion = null;
        try {

            httpClientVersion = VersionInfo.loadVersionInfo(
                    "org.apache.http.nio.client",
                    HttpAsyncClientBuilder.class.getClassLoader()
            );

        } catch (Exception e) {
            // Keep unknown
        }

    }

    YelpFusionRestClientBuilder(String apiKey) {
        if (apiKey == null) {
            throw new IllegalArgumentException("apiKey must not be null");
        }
        String yelpFusionHost = "api.yelp.com";
        int port = 80;
        host = new HttpHost(yelpFusionHost, port, "http");

        Header[] defaultHeaders = {new BasicHeader("Authorization", "Bearer " + apiKey)};
        setDefaultHeaders(defaultHeaders);
    }
    YelpFusionRestClientBuilder(HttpHost host) {
        if (host == null) {
            throw new IllegalArgumentException("host must not be null");
        }
        this.host = host;
    }

    public YelpFusionRestClientBuilder setDefaultHeaders(Header[] defaultHeaders) {
        Objects.requireNonNull(defaultHeaders, "defaultHeaders must not be null");
        for (Header defaultHeader : defaultHeaders) {
            Objects.requireNonNull(defaultHeader, "default header must not be null");
        }
        this.defaultHeaders = defaultHeaders;
        return this;
    }

    public YelpFusionRestClientBuilder setHttpClientConfigCallback(HttpClientConfigCallback httpClientConfigCallback) {
        Objects.requireNonNull(httpClientConfigCallback, "httpClientConfigCallback must not be null");
        this.httpClientConfigCallback = httpClientConfigCallback;
        return this;
    }

    public YelpFusionRestClientBuilder setRequestConfigCallback(RequestConfigCallback requestConfigCallback) {
        Objects.requireNonNull(requestConfigCallback, "requestConfigCallback must not be null");
        this.requestConfigCallback = requestConfigCallback;
        return this;
    }

    public YelpFusionRestClientBuilder setPathPrefix(String pathPrefix) {
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


    public YelpFusionRestClientBuilder setStrictDeprecationMode(boolean strictDeprecationMode) {
        this.strictDeprecationMode = strictDeprecationMode;
        return this;
    }

    public YelpFusionRestClientBuilder setCompressionEnabled(boolean compressionEnabled) {
        this.compressionEnabled = compressionEnabled;
        return this;
    }

    public YelpFusionRestClientBuilder setMetaHeaderEnabled(boolean metadataEnabled) {
        this.metaHeaderEnabled = metadataEnabled;
        return this;
    }


    public YelpFusionRestClient build() {

        CloseableHttpAsyncClient httpClient = createHttpClient();

        YelpFusionRestClient restClient = new YelpFusionRestClient(
                httpClient,
                defaultHeaders,
                host,
                pathPrefix,
                strictDeprecationMode,
                compressionEnabled,
                metaHeaderEnabled
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

    public interface RequestConfigCallback {
        RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder);
    }

    public interface HttpClientConfigCallback {
        HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder);
    }
}

