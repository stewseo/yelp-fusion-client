package io.github.stewseo.lowlevel.restclient;

import org.apache.http.Header;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.nio.conn.SchemeIOSessionStrategy;
import org.apache.http.util.VersionInfo;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Properties;

public final class EsRestClientBuilder {
    public static final int DEFAULT_CONNECT_TIMEOUT_MILLIS = 1000;
    public static final int DEFAULT_SOCKET_TIMEOUT_MILLIS = 30000;
    public static final int DEFAULT_MAX_CONN_PER_ROUTE = 10;
    public static final int DEFAULT_MAX_CONN_TOTAL = 30;

    public static final String VERSION;
    static final String META_HEADER_NAME = "X-Elastic-Client-Meta";
    static final String META_HEADER_VALUE;
    private static final String USER_AGENT_HEADER_VALUE;

    private static final Header[] EMPTY_HEADERS = new Header[0];

    static {

        // Never fail on unknown version, even if an environment messed up their classpath enough that we can't find it.
        // Better have incomplete telemetry than crashing user applications.
        String version = null;
        try (InputStream is = EsRestClient.class.getResourceAsStream("version.properties")) {
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

        USER_AGENT_HEADER_VALUE = String.format(
                Locale.ROOT,
                "elasticsearch-java/%s (Java/%s)",
                VERSION.isEmpty() ? "Unknown" : VERSION,
                System.getProperty("java.version")
        );

        VersionInfo httpClientVersion = null;
        try {
            httpClientVersion = VersionInfo.loadVersionInfo(
                    "org.apache.http.nio.client",
                    HttpAsyncClientBuilder.class.getClassLoader()
            );
        } catch (Exception e) {
            // Keep unknown
        }

        // Use a single 'p' suffix for all prerelease versions (snapshot, beta, etc).
        String metaVersion = version;
        int dashPos = metaVersion.indexOf('-');
        if (dashPos > 0) {
            metaVersion = metaVersion.substring(0, dashPos) + "p";
        }

        // service, language, transport, followed by additional information
        META_HEADER_VALUE = "es="
                + metaVersion
                + ",jv="
                + System.getProperty("java.specification.version")
                + ",t="
                + metaVersion
                + ",hc="
                + (httpClientVersion == null ? "" : httpClientVersion.getRelease())
                + LanguageRuntimeVersions.getRuntimeMetadata();
    }

    private final List<Node> nodes;
    private Header[] defaultHeaders = EMPTY_HEADERS;
    private EsRestClient.FailureListener failureListener;
    private HttpClientConfigCallback httpClientConfigCallback;
    private RequestConfigCallback requestConfigCallback;
    private String pathPrefix;
    private NodeSelector nodeSelector = NodeSelector.ANY;
    private boolean strictDeprecationMode = false;
    private boolean compressionEnabled = false;
    private boolean metaHeaderEnabled = true;

    /**
     * Creates a new builder instance and sets the hosts that the client will send requests to.
     *
     * @throws IllegalArgumentException if {@code nodes} is {@code null} or empty.
     */
    EsRestClientBuilder(List<Node> nodes) {
        if (nodes == null || nodes.isEmpty()) {
            throw new IllegalArgumentException("nodes must not be null or empty");
        }
        for (Node node : nodes) {
            if (node == null) {
                throw new IllegalArgumentException("node cannot be null");
            }
        }
        this.nodes = nodes;
    }

    public static String cleanPathPrefix(String pathPrefix) {
        Objects.requireNonNull(pathPrefix, "pathPrefix must not be null");

        if (pathPrefix.isEmpty()) {
            throw new IllegalArgumentException("pathPrefix must not be empty");
        }

        String cleanPathPrefix = pathPrefix;
        if (cleanPathPrefix.startsWith("/") == false) {
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

    /**
     * Sets the default request headers, which will be sent along with each request.
     * <p>
     * Request-time headers will always overwrite any default headers.
     *
     * @throws NullPointerException if {@code defaultHeaders} or any header is {@code null}.
     */
    public EsRestClientBuilder setDefaultHeaders(Header[] defaultHeaders) {
        Objects.requireNonNull(defaultHeaders, "defaultHeaders must not be null");
        for (Header defaultHeader : defaultHeaders) {
            Objects.requireNonNull(defaultHeader, "default header must not be null");
        }
        this.defaultHeaders = defaultHeaders;
        return this;
    }

    public EsRestClientBuilder setFailureListener(EsRestClient.FailureListener failureListener) {
        Objects.requireNonNull(failureListener, "failureListener must not be null");
        this.failureListener = failureListener;
        return this;
    }

    /**
     * Sets the {@link HttpClientConfigCallback} to be used to customize http client configuration
     *
     * @throws NullPointerException if {@code httpClientConfigCallback} is {@code null}.
     */
    public EsRestClientBuilder setHttpClientConfigCallback(HttpClientConfigCallback httpClientConfigCallback) {
        Objects.requireNonNull(httpClientConfigCallback, "httpClientConfigCallback must not be null");
        this.httpClientConfigCallback = httpClientConfigCallback;
        return this;
    }

    /**
     * Sets the {@link RequestConfigCallback} to be used to customize http client configuration
     *
     * @throws NullPointerException if {@code requestConfigCallback} is {@code null}.
     */
    public EsRestClientBuilder setRequestConfigCallback(RequestConfigCallback requestConfigCallback) {
        Objects.requireNonNull(requestConfigCallback, "requestConfigCallback must not be null");
        this.requestConfigCallback = requestConfigCallback;
        return this;
    }

    /**
     * Sets the path's prefix for every request used by the http client.
     * <p>
     * For example, if this is set to "/my/path", then any client request will become <code>"/my/path/" + endpoint</code>.
     * <p>
     * In essence, every request's {@code endpoint} is prefixed by this {@code pathPrefix}. The path prefix is useful for when
     * Elasticsearch is behind a proxy that provides a base path or a proxy that requires all paths to start with '/';
     * it is not intended for other purposes and it should not be supplied in other scenarios.
     *
     * @throws NullPointerException     if {@code pathPrefix} is {@code null}.
     * @throws IllegalArgumentException if {@code pathPrefix} is empty, or ends with more than one '/'.
     */
    public EsRestClientBuilder setPathPrefix(String pathPrefix) {
        this.pathPrefix = cleanPathPrefix(pathPrefix);
        return this;
    }

    /**
     * Sets the {@link NodeSelector} to be used for all requests.
     *
     * @throws NullPointerException if the provided nodeSelector is null
     */
    public EsRestClientBuilder setNodeSelector(NodeSelector nodeSelector) {
        Objects.requireNonNull(nodeSelector, "nodeSelector must not be null");
        this.nodeSelector = nodeSelector;
        return this;
    }

    /**
     * Whether the REST client should return any response containing at least
     * one warning header as a failure.
     */
    public EsRestClientBuilder setStrictDeprecationMode(boolean strictDeprecationMode) {
        this.strictDeprecationMode = strictDeprecationMode;
        return this;
    }

    /**
     * Whether the REST client should compress requests using gzip content encoding and add the "Accept-Encoding: gzip"
     * header to receive compressed responses.
     */
    public EsRestClientBuilder setCompressionEnabled(boolean compressionEnabled) {
        this.compressionEnabled = compressionEnabled;
        return this;
    }

    /**
     * Whether to send a {@code X-Elastic-Client-Meta} header that describes the runtime environment. It contains
     * information that is similar to what could be found in {@code User-Agent}. Using a separate header allows
     * applications to use {@code User-Agent} for their own needs, e.g. to identify application version or other
     * environment information. Defaults to {@code true}.
     */
    public EsRestClientBuilder setMetaHeaderEnabled(boolean metadataEnabled) {
        this.metaHeaderEnabled = metadataEnabled;
        return this;
    }

    /**
     * Creates a new {@link EsRestClient} based on the provided configuration.
     */
    public EsRestClient build() {
        if (failureListener == null) {
            failureListener = new EsRestClient.FailureListener();
        }
        CloseableHttpAsyncClient httpClient = createHttpClient();
        EsRestClient EsRestClient = new EsRestClient(
                httpClient,
                defaultHeaders,
                nodes,
                pathPrefix,
                failureListener,
                nodeSelector,
                strictDeprecationMode,
                compressionEnabled,
                metaHeaderEnabled
        );
        httpClient.start();
        return EsRestClient;
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
                    .setUserAgent(USER_AGENT_HEADER_VALUE)
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

    /**
     * Callback used the default {@link RequestConfig} being set to the {@link CloseableHttpClient}
     *
     * @see HttpClientBuilder#setDefaultRequestConfig
     */
    public interface RequestConfigCallback {
        /**
         * Allows to customize the {@link RequestConfig} that will be used with each request.
         * It is common to customize the different timeout values through this method without losing any other useful default
         * value that the {@link EsRestClientBuilder} internally sets.
         */
        RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder);
    }

    /**
     * Callback used to customize the {@link CloseableHttpClient} instance used by a {@link EsRestClient} instance.
     * Allows to customize default {@link RequestConfig} being set to the client and any parameter that
     * can be set through {@link HttpClientBuilder}
     */
    public interface HttpClientConfigCallback {
        /**
         * Allows to customize the {@link CloseableHttpAsyncClient} being created and used by the {@link EsRestClient}.
         * Commonly used to customize the default {@link org.apache.http.client.CredentialsProvider} for authentication
         * or the {@link SchemeIOSessionStrategy} for communication through ssl without losing any other useful default
         * value that the {@link EsRestClientBuilder} internally sets, like connection pooling.
         */
        HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder);
    }
}
