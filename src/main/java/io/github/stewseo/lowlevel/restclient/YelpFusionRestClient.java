package io.github.stewseo.lowlevel.restclient;

import org.apache.http.*;
import org.apache.http.client.AuthCache;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.GzipCompressingEntity;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.nio.client.HttpAsyncClient;
import org.apache.http.nio.client.methods.HttpAsyncMethods;
import org.apache.http.nio.protocol.HttpAsyncRequestProducer;
import org.apache.http.nio.protocol.HttpAsyncResponseConsumer;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLHandshakeException;
import java.io.*;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.zip.GZIPOutputStream;


public class YelpFusionRestClient implements Closeable {
    //    private static final Log logger = LogFactory.getLog(RestClient.class);
    private static final Logger logger = LoggerFactory.getLogger(YelpFusionRestClient.class);
    private final CloseableHttpAsyncClient client;
    private final HttpHost httpHost;

    final List<Header> defaultHeaders;
    private final String pathPrefix;

    private final WarningsHandler warningsHandler;
    private final boolean compressionEnabled;
    private final boolean metaHeaderEnabled;

    YelpFusionRestClient(
            CloseableHttpAsyncClient client,
            Header[] defaultHeaders,
            HttpHost httpHost,
            String pathPrefix,
            boolean strictDeprecationMode,
            boolean compressionEnabled,
            boolean metaHeaderEnabled
    ) {
        this.client = client;
        this.defaultHeaders = Collections.unmodifiableList(Arrays.asList(defaultHeaders));
        this.httpHost = httpHost;
        this.pathPrefix = pathPrefix;
        this.warningsHandler = strictDeprecationMode ? WarningsHandler.STRICT : WarningsHandler.PERMISSIVE;
        this.compressionEnabled = compressionEnabled;
        this.metaHeaderEnabled = metaHeaderEnabled;
    }

    public static YelpFusionRestClientBuilder builder(String apiKey) {
        if (apiKey == null) {
            throw new IllegalArgumentException("apiKey must not be null");
        }
        return new YelpFusionRestClientBuilder(apiKey);
    }

    public static YelpFusionRestClientBuilder builder(HttpHost host, HttpHost... hosts) {
        if (host == null) {
            throw new IllegalArgumentException("hosts must not be null nor empty");
        }
        return new YelpFusionRestClientBuilder(host);
    }


    public HttpAsyncClient getHttpClient() {
        return this.client;
    }
    public HttpHost getHttpHost() {
        return this.httpHost;
    }

    public boolean isRunning() {
        return client.isRunning();
    }

    public String add(String string, Function<String, String> fn) {
        return fn.apply(string);
    }


    public Response performRequest(Request request) throws IOException {
        InternalRequest internalRequest = new InternalRequest(request);
        return performRequest(httpHost, internalRequest, null);
    }

    private Response performRequest(HttpHost httpHost, final InternalRequest request, Exception previousException)
            throws IOException {

        RequestContext context = request.createContextForNextAttempt(httpHost);

        HttpResponse httpResponse;
        try {
            httpResponse = client.execute(context.requestProducer, context.asyncResponseConsumer, context.context, null).get();

        } catch (Exception e) {
            YelpRequestLogger.logFailedRequest(logger, request.httpRequest, httpHost, e);
            Exception cause = extractAndWrapCause(e);
            addSuppressedException(previousException, cause);
            if (cause instanceof IOException) {
                throw (IOException) cause;
            }
            if (cause instanceof RuntimeException) {
                throw (RuntimeException) cause;
            }
            throw new IllegalStateException("unexpected exception type: must be either RuntimeException or IOException", cause);
        }

        ResponseOrResponseException responseOrResponseException = convertResponse(request, context.httpHost, httpResponse);

        if (responseOrResponseException.responseException == null) {
            return responseOrResponseException.response;
        }

        addSuppressedException(previousException, responseOrResponseException.responseException);

        throw responseOrResponseException.responseException;
    }


    private ResponseOrResponseException convertResponse(InternalRequest request, HttpHost host, HttpResponse httpResponse) throws IOException {
        YelpRequestLogger.logResponse(logger, request.httpRequest, host, httpResponse);

        int statusCode = httpResponse.getStatusLine().getStatusCode();

        HttpEntity entity = httpResponse.getEntity();
        if (entity != null) {
            Header header = entity.getContentEncoding();
            if (header != null && "gzip".equals(header.getValue())) {
                // Decompress and cleanup response headers
                httpResponse.setEntity(new GzipDecompressingEntity(entity));
                httpResponse.removeHeaders(HTTP.CONTENT_ENCODING);
                httpResponse.removeHeaders(HTTP.CONTENT_LEN);
            }
        }

        Response response = new Response(request.httpRequest.getRequestLine(), host, httpResponse);
        if (isSuccessfulResponse(statusCode) || request.ignoreErrorCodes.contains(response.getStatusLine().getStatusCode())) {

            if (request.warningsHandler.warningsShouldFailRequest(response.getWarnings())) {
                throw new WarningFailureException(response);
            }
            return new ResponseOrResponseException(response);
        }
        ResponseException responseException = new ResponseException(response);
        if (isRetryStatus(statusCode)) {
            // mark host dead and retry against next one
            return new ResponseOrResponseException(responseException);
        }
        throw responseException;
    }


    public Cancellable performRequestAsync(Request request, ResponseListener responseListener) {
        try {
            FailureTrackingResponseListener failureTrackingResponseListener = new FailureTrackingResponseListener(responseListener);
            InternalRequest internalRequest = new InternalRequest(request);

            performRequestAsync(httpHost, internalRequest, failureTrackingResponseListener);
            return internalRequest.cancellable;
        } catch (Exception e) {
            responseListener.onFailure(e);
            return Cancellable.NO_OP;
        }
    }


    private void performRequestAsync(
            final HttpHost host,
            final InternalRequest request,
            final FailureTrackingResponseListener listener
    ) {
        request.cancellable.runIfNotCancelled(() -> {
            final RequestContext context = request.createContextForNextAttempt(host);

            client.execute(
                    context.requestProducer,
                    context.asyncResponseConsumer,
                    context.context,
                    new FutureCallback<>() { // A callback interface that gets invoked upon completion of a java.util.concurrent.Future.

                        @Override
                        public void completed(HttpResponse httpResponse) {
                            try {
                                ResponseOrResponseException responseOrResponseException = convertResponse(request, context.httpHost, httpResponse);
                                if (responseOrResponseException.responseException == null) {
                                    listener.onSuccess(responseOrResponseException.response);
                                } else {
                                    listener.onDefinitiveFailure(responseOrResponseException.responseException);

                                }
                            } catch (Exception e) {
                                listener.onDefinitiveFailure(e);
                            }
                        }

                        @Override
                        public void failed(Exception failure) {
                            try {
                                YelpRequestLogger.logFailedRequest(logger, request.httpRequest, context.httpHost, failure);

                            } catch (Exception e) {
                                listener.onDefinitiveFailure(e);
                            }
                        }

                        @Override
                        public void cancelled() {
                            listener.onDefinitiveFailure(Cancellable.newCancellationException());
                        }
                    });
        });
    }

    @Override
    public void close() throws IOException {
        client.close();
    }


    private static boolean isSuccessfulResponse(int statusCode) {
        return statusCode < 300;
    }

    private static boolean isRetryableException(Throwable e) {
        if (e instanceof ExecutionException) {
            e = e.getCause();
        }
        if (e instanceof ContentTooLongException) {
            return false;
        }
        return true;
    }

    private static boolean isRetryStatus(int statusCode) {
        return switch (statusCode) {
            case 502, 503, 504 -> true;
            default -> false;
        };
    }

    private static void addSuppressedException(Exception suppressedException, Exception currentException) {
        if (suppressedException != null && suppressedException != currentException) {
            currentException.addSuppressed(suppressedException);
        }
    }


    public static URI buildUri(String pathPrefix, String path, Map<String, String> params) {
        Objects.requireNonNull(path, "path must not be null");

        try {
            String fullPath;
            if (pathPrefix != null && !pathPrefix.isEmpty()) {
                if (pathPrefix.endsWith("/") && path.startsWith("/")) {

                    fullPath = pathPrefix.substring(0, pathPrefix.length() - 1) + path; // remove additional "/"
                } else if (pathPrefix.endsWith("/") || path.startsWith("/")) {
                    fullPath = pathPrefix + path;

                } else {
                    fullPath = pathPrefix + "/" + path;
                }
            } else {
                fullPath = path;
            }

            URIBuilder uriBuilder;
            if (fullPath.endsWith("/businesses")) {
                uriBuilder = new URIBuilder(fullPath + "/" + params.get("id"));
            }
            else {

                uriBuilder = new URIBuilder(fullPath);
                for (Map.Entry<String, String> param : params.entrySet()) {
                    uriBuilder.addParameter(param.getKey(), param.getValue());
                }
            }
            return uriBuilder.build();

        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }


    public class InternalRequest {
        private final Request request;
        private final Set<Integer> ignoreErrorCodes;
        private final HttpRequestBase httpRequest;
        private final AuthCache authCache;
        private final Cancellable cancellable;
        private final WarningsHandler warningsHandler;



        InternalRequest(Request request) {
            this.request = request;
            Map<String, String> params = new HashMap<>(request.getParameters());
            params.putAll(request.getOptions().getParameters());
            // ignore is a special parameter supported by the clients, shouldn't be sent to es
            String ignoreString = params.remove("ignore");
            this.ignoreErrorCodes = getIgnoreErrorCodes(ignoreString, request.getMethod());

            this.authCache = new BasicAuthCache();

            URI uri = buildUri(pathPrefix, request.getEndpoint(), params);

            this.httpRequest = createHttpRequest(request.getMethod(), uri, request.getEntity(), compressionEnabled);

            this.cancellable = Cancellable.fromRequest(httpRequest);

            setHeaders(httpRequest, request.getOptions().getHeaders());

            setRequestConfig(httpRequest, request.getOptions().getRequestConfig());

            this.warningsHandler = request.getOptions().getWarningsHandler() == null
                    ? YelpFusionRestClient.this.warningsHandler
                    : request.getOptions().getWarningsHandler();
        }

        private void setHeaders(HttpRequest req, Collection<Header> requestHeaders) {
            // request headers override default headers, so we don't add default headers if they exist as request headers
            final Set<String> requestNames = new HashSet<>(requestHeaders.size());

            for (Header requestHeader : requestHeaders) {
                req.addHeader(requestHeader);
                requestNames.add(requestHeader.getName());
            }

            for (Header defaultHeader : defaultHeaders) {
                if (!requestNames.contains(defaultHeader.getName())) {
                    req.setHeader(defaultHeader);
                }
            }

            if (compressionEnabled) {
                req.addHeader("Accept-Encoding", "gzip");
            }

            Header[] header = req.getHeaders("Accept");

            if(header.length > 0) {
//                logger.debug(PrintUtils.cyan("all headers: " + Arrays.stream(req.getAllHeaders()).map(Header::getName).toList()));
                req.removeHeader(Arrays.stream(req.getHeaders("Accept")).toList().get(0));
            }

        }

        private void setRequestConfig(HttpRequestBase requestBase, RequestConfig requestConfig) {
            if (requestConfig != null) {
                requestBase.setConfig(requestConfig);
            }
        }

        RequestContext createContextForNextAttempt(HttpHost host) {
            this.httpRequest.reset();
            return new RequestContext(this, host);
        }

    }

    public static class RequestContext {
        private final HttpHost httpHost;
        private final HttpAsyncRequestProducer requestProducer;
        private final HttpAsyncResponseConsumer<HttpResponse> asyncResponseConsumer;

        // Adaptor class that provides convenience type safe setters and getters for common HttpContext attributes used in the course of HTTP request execution.
        private final HttpClientContext context;

        RequestContext(InternalRequest request, HttpHost host) {

            this.httpHost = host;

            this.requestProducer = HttpAsyncMethods.create(httpHost, request.httpRequest);

            this.asyncResponseConsumer = request.request.getOptions()
                    .getHttpAsyncResponseConsumerFactory()
                    .createHttpAsyncResponseConsumer();

            this.context = HttpClientContext.create();
            context.setAuthCache(request.authCache);
        }
    }


    public static HttpRequestBase createHttpRequest(String method, URI uri, HttpEntity entity, boolean compressionEnabled) {
        switch (method.toUpperCase(Locale.ROOT)) {
            case HttpDeleteWithEntity.METHOD_NAME:
                return addRequestBody(new HttpDeleteWithEntity(uri), entity, compressionEnabled);
            case HttpGetWithEntity.METHOD_NAME:
                return addRequestBody(new HttpGetWithEntity(uri), entity, compressionEnabled);
            case HttpHead.METHOD_NAME:
                return addRequestBody(new HttpHead(uri), entity, compressionEnabled);
            case HttpOptions.METHOD_NAME:
                return addRequestBody(new HttpOptions(uri), entity, compressionEnabled);
            case HttpPatch.METHOD_NAME:
                return addRequestBody(new HttpPatch(uri), entity, compressionEnabled);
            case HttpPost.METHOD_NAME:
                HttpPost httpPost = new HttpPost(uri);
                addRequestBody(httpPost, entity, compressionEnabled);
                return httpPost;
            case HttpPut.METHOD_NAME:
                return addRequestBody(new HttpPut(uri), entity, compressionEnabled);
            case HttpTrace.METHOD_NAME:
                return addRequestBody(new HttpTrace(uri), entity, compressionEnabled);
            default:
                throw new UnsupportedOperationException("http method not supported: " + method);
        }
    }

    private static HttpRequestBase addRequestBody(HttpRequestBase httpRequest, HttpEntity entity, boolean compressionEnabled) {
        if (entity != null) {
            if (httpRequest instanceof HttpEntityEnclosingRequestBase) {
                if (compressionEnabled) {
                    entity = new ContentCompressingEntity(entity);
                }
                ((HttpEntityEnclosingRequestBase) httpRequest).setEntity(entity);
            } else {
                throw new UnsupportedOperationException(httpRequest.getMethod() + " with body is not supported");
            }
        }
        return httpRequest;
    }


    static class FailureTrackingResponseListener {
        private final ResponseListener responseListener;
        private volatile Exception exception;

        FailureTrackingResponseListener(ResponseListener responseListener) {
            this.responseListener = responseListener;
        }

        /**
         * Notifies the caller of a response through the wrapped listener
         */
        void onSuccess(Response response) {
            responseListener.onSuccess(response);
        }

        /**
         * Tracks one last definitive failure and returns to the caller by notifying the wrapped listener
         */
        void onDefinitiveFailure(Exception e) {
            trackFailure(e);
            responseListener.onFailure(this.exception);
        }

        /**
         * Tracks an exception, which caused a retry hence we should not return yet to the caller
         */
        void trackFailure(Exception e) {
            addSuppressedException(this.exception, e);
            this.exception = e;
        }
    }


    private static Set<Integer> getIgnoreErrorCodes(String ignoreString, String requestMethod) {
        Set<Integer> ignoreErrorCodes;
        if (ignoreString == null) {
            if (HttpHead.METHOD_NAME.equals(requestMethod)) {
                // 404 never causes error if returned for a HEAD request
                ignoreErrorCodes = Collections.singleton(404);
            } else {
                ignoreErrorCodes = Collections.emptySet();
            }
        } else {
            String[] ignoresArray = ignoreString.split(",");
            ignoreErrorCodes = new HashSet<>();
            if (HttpHead.METHOD_NAME.equals(requestMethod)) {
                // 404 never causes error if returned for a HEAD request
                ignoreErrorCodes.add(404);
            }
            for (String ignoreCode : ignoresArray) {
                try {
                    ignoreErrorCodes.add(Integer.valueOf(ignoreCode));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("ignore value should be a number, found [" + ignoreString + "] instead", e);
                }
            }
        }
        return ignoreErrorCodes;
    }

    private static class ResponseOrResponseException {
        private final Response response;
        private final ResponseException responseException;

        ResponseOrResponseException(Response response) {
            this.response = Objects.requireNonNull(response);
            this.responseException = null;
        }

        ResponseOrResponseException(ResponseException responseException) {
            this.responseException = Objects.requireNonNull(responseException);
            this.response = null;
        }
    }

    private static Exception extractAndWrapCause(Exception exception) {
        if (exception instanceof InterruptedException) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("thread waiting for the response was interrupted", exception);
        }
        if (exception instanceof ExecutionException executionException) {
            Throwable t = executionException.getCause() == null ? executionException : executionException.getCause();
            if (t instanceof Error) {
                throw (Error) t;
            }
            exception = (Exception) t;
        }
        if (exception instanceof ConnectTimeoutException) {
            ConnectTimeoutException e = new ConnectTimeoutException(exception.getMessage());
            e.initCause(exception);
            return e;
        }
        if (exception instanceof SocketTimeoutException) {
            SocketTimeoutException e = new SocketTimeoutException(exception.getMessage());
            e.initCause(exception);
            return e;
        }
        if (exception instanceof ConnectionClosedException) {
            ConnectionClosedException e = new ConnectionClosedException(exception.getMessage());
            e.initCause(exception);
            return e;
        }
        if (exception instanceof SSLHandshakeException) {
            SSLHandshakeException e = new SSLHandshakeException(exception.getMessage());
            e.initCause(exception);
            return e;
        }
        if (exception instanceof ConnectException) {
            ConnectException e = new ConnectException(exception.getMessage());
            e.initCause(exception);
            return e;
        }
        if (exception instanceof IOException) {
            return new IOException(exception.getMessage(), exception);
        }
        if (exception instanceof RuntimeException) {
            return new RuntimeException(exception.getMessage(), exception);
        }
        return new RuntimeException("error while performing request", exception);
    }

    public static class ContentCompressingEntity extends GzipCompressingEntity {

        public ContentCompressingEntity(HttpEntity entity) {
            super(entity);
        }

        @Override
        public InputStream getContent() throws IOException {
            ByteArrayInputOutputStream out = new ByteArrayInputOutputStream(1024);
            try (GZIPOutputStream gzipOut = new GZIPOutputStream(out)) {
                wrappedEntity.writeTo(gzipOut);
            }
            return out.asInput();
        }
    }

    private static class ByteArrayInputOutputStream extends ByteArrayOutputStream {
        ByteArrayInputOutputStream(int size) {
            super(size);
        }

        public InputStream asInput() {
            return new ByteArrayInputStream(this.buf, 0, this.count);
        }
    }
}

