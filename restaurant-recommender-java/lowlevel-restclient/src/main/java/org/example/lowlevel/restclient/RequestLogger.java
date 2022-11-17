package org.example.lowlevel.restclient;

import org.apache.commons.logging.*;
import org.apache.http.*;
import org.apache.http.client.methods.*;
import org.apache.http.entity.*;
import org.apache.http.util.*;

import java.io.*;
import java.nio.charset.*;
import java.util.*;
import java.util.concurrent.*;

public class RequestLogger {
    private static final Log tracer = LogFactory.getLog("tracer");

    private RequestLogger() {
    }

    static void logResponse(Log logger, HttpUriRequest request, HttpHost host, HttpResponse httpResponse) {
        if (logger.isDebugEnabled()) {
            logger.debug(
                    "request ["
                            + request.getMethod()
                            + " "
                            + host
                            + getUri(request.getRequestLine())
                            + "] returned ["
                            + httpResponse.getStatusLine()
                            + "]"
            );
        }
        if (logger.isWarnEnabled()) {
            Header[] warnings = httpResponse.getHeaders("Warning");
            if (warnings != null && warnings.length > 0) {
                logger.warn(buildWarningMessage(request, host, warnings));
            }
        }
        if (tracer.isTraceEnabled()) {
            String requestLine;
            try {
                requestLine = buildTraceRequest(request, host);
            } catch (IOException e) {
                requestLine = "";
                tracer.trace("error while reading request for trace purposes", e);
            }
            String responseLine;
            try {
                responseLine = buildTraceResponse(httpResponse);
            } catch (IOException e) {
                responseLine = "";
                tracer.trace("error while reading response for trace purposes", e);
            }
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
        if (request instanceof HttpEntityEnclosingRequest) {
            HttpEntityEnclosingRequest enclosingRequest = (HttpEntityEnclosingRequest) request;
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

    public static void logFailedRequest(Log logger, Exception e) {
        if(logger.isWarnEnabled()){
            logger.warn("warn ");
        }
        if (logger.isDebugEnabled()) {
            logger.debug("debug " + e);
        }

        if (tracer.isTraceEnabled()) {
            String traceRequest;
            tracer.trace("tracer.isTraceEnabled " + e);
        }
    }

    private static String buildTraceRequestFromJdkHttpRequest(java.net.http.HttpRequest request, HttpHost host) {
        // yelp fusion only accepts query params on path
        return "curl -iX " + request.method() + " '" + host + request.uri() + "'";
    }

    public static void logResponse(Log logger, java.net.http.HttpRequest jdkHttpRequest, HttpHost host, java.net.http.HttpResponse<String> httpResponse) {
        if (logger.isDebugEnabled()) {
            logger.debug("debug");
        }
        if (logger.isWarnEnabled()) {
            logger.warn("warn");

        }
        if (tracer.isTraceEnabled()) {
            logger.trace("trace");
        }
    }

}


