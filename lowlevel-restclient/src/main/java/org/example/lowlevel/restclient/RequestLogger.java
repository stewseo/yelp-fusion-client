package org.example.lowlevel.restclient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.*;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.http.HttpClient;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class RequestLogger {
    private static final Log tracer = LogFactory.getLog("tracer");

    private RequestLogger() {
    }

    static void  logResponse(Log logger, HttpClient request, java.net.http.HttpResponse<String> httpResponse) {
        if (logger.isInfoEnabled()) {
            logger.info(
                    "request ["
                            + httpResponse.request()
                            + " "
                            + "] returned ["
                            + httpResponse.statusCode()
                            + "]"
            );
        }

        if (logger.isDebugEnabled()) {
            logger.debug(
                    "request ["
                            + httpResponse.request()
                            + " "
                            + "] returned ["
                            + httpResponse.statusCode()
                            + "]"
            );
        }
        if (tracer.isTraceEnabled()) {
            String requestLine;
            requestLine = "";
            String responseLine = "";

            tracer.trace(requestLine + '\n' + responseLine);
        }
    }

    /**
     * Logs a request that failed
     */
    static void logFailedRequest(Log logger, HttpUriRequest request, Node node, Exception e) {
        if (logger.isDebugEnabled()) {
            logger.debug("request [" + request.getMethod() + " " + node.getHost() + getUri(request.getRequestLine()) + "] failed", e);
        }
        if (tracer.isTraceEnabled()) {
            String traceRequest;
            try {
                traceRequest = buildTraceRequest(request, node.getHost());
            } catch (IOException e1) {
                tracer.trace("error while reading request for trace purposes", e);
                traceRequest = "";
            }
            tracer.trace(traceRequest);
        }
    }

    static String buildWarningMessage(HttpUriRequest request, HttpHost host, Header[] warnings) {
        StringBuilder message = new StringBuilder("request [").append(request.getMethod())
                .append(" ")
                .append(host)
                .append(getUri(request.getRequestLine()))
                .append("] returned ")
                .append(warnings.length)
                .append(" warnings: ");
        for (int i = 0; i < warnings.length; i++) {
            if (i > 0) {
                message.append(",");
            }
            message.append("[").append(warnings[i].getValue()).append("]");
        }
        return message.toString();
    }

    /**
     * Creates curl output for given request
     */
    static String buildTraceRequest(HttpUriRequest request, HttpHost host) throws IOException {
        String requestLine = "curl -iX " + request.getMethod() + " '" + host + getUri(request.getRequestLine()) + "'";
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


    /**
     * Creates curl output for given response
     */
    static String buildTraceResponse(HttpResponse httpResponse) throws IOException {
        StringBuilder responseLine = new StringBuilder();
        responseLine.append("# ").append(httpResponse.getStatusLine());
        for (Header header : httpResponse.getAllHeaders()) {
            responseLine.append("\n# ").append(header.getName()).append(": ").append(header.getValue());
        }
        responseLine.append("\n#");
        HttpEntity entity = httpResponse.getEntity();
        if (entity != null) {
            if (!entity.isRepeatable()) {
                entity = new BufferedHttpEntity(entity);
            }
            httpResponse.setEntity(entity);
            ContentType contentType = ContentType.get(entity);
            Charset charset = StandardCharsets.UTF_8;
            if (contentType != null && contentType.getCharset() != null) {
                charset = contentType.getCharset();
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), charset))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    responseLine.append("\n# ").append(line);
                }
            }
        }
        return responseLine.toString();
    }

    private static String getUri(RequestLine requestLine) {
        if (requestLine.getUri().charAt(0) != '/') {
            return "/" + requestLine.getUri();
        }
        return requestLine.getUri();
    }


    public static void logFailedRequest(Log logger, java.net.http.HttpRequest request, Node node, Exception e) {
        if (logger.isDebugEnabled()) {
            logger.debug("request [" + request.method() + " " + node.getHost() + request.uri() + "] failed", e);
        }
        if (tracer.isTraceEnabled()) {
            String traceRequest;
            traceRequest = buildTraceRequestFromJdkHttpRequest(request, node.getHost());
            tracer.trace(traceRequest);
        }
    }

    public static void logFailedRequest(Logger logger, Exception e) {
        if (logger.isDebugEnabled()) {
            logger.debug("Failed Request when performing a http request " + e);
        }

        if (tracer.isTraceEnabled()) {
            String traceRequest;
            tracer.trace("Failed Request when performing a http request " + e);
        }
    }

    private static String buildTraceRequestFromJdkHttpRequest(java.net.http.HttpRequest request, HttpHost host) {
        // yelp fusion only accepts query params on path
        return "curl -iX " + request.method() + " '" + host + request.uri() + "'";
    }

    public static<T> void logFailedRequest(Log logger, java.net.http.HttpRequest jdkHttpRequest, Node node, java.net.http.HttpResponse<T> httpResponse, Exception e) {
    }

    public static void logResponse(Log logger, java.net.http.HttpRequest jdkHttpRequest, HttpHost host, HttpResponse httpResponse) {
    }

}


