package org.example.yelp.fusion.client.exception;


import org.apache.commons.logging.*;
import org.apache.http.*;
import org.apache.http.client.methods.*;
import org.apache.http.entity.*;
import org.apache.http.util.*;
import org.example.yelp.fusion.client.business.search.*;

import java.io.*;
import java.nio.charset.*;
import java.util.*;
import java.util.concurrent.*;

public class YelpRequestLogger {
    private static final Log tracer = LogFactory.getLog("tracer");

    private YelpRequestLogger() {
    }

    public static <TDocument> void logEmptyResponseBody(Log logger, BusinessSearchResponse<TDocument> businessSearchResponse) {
        if (logger.isDebugEnabled()) {
            logger.debug(
                    "request [ request.getMethod() host getUri(request.getRequestLine()) ] returned [ httpResponse.getStatusLine() ]"
            );
        }
        if(logger.isWarnEnabled()) {
            logger.debug(
                    "warn: add headers ]"
            );
        }
        if (tracer.isTraceEnabled()) {
            String requestLine;
            tracer.trace("requestLine = buildTraceRequest(request, host)");

            String responseLine;
            tracer.trace("requestLine = buildTraceResponse(response, host)");
            tracer.trace("requestLine + '\n' + responseLine");
        }
    }

    public static <TDocument> void logNullResponseBody(Log logger, BusinessSearchResponse<TDocument> businessSearchResponse) {
        if (logger.isDebugEnabled()) {
            logger.debug(
                    "request [ request.getMethod() host getUri(request.getRequestLine()) ] returned [ httpResponse.getStatusLine() ]"
            );
        }
        if(logger.isWarnEnabled()) {
            logger.debug(
                    "warn: add headers ]"
            );
        }
        if (tracer.isTraceEnabled()) {
            String requestLine;
            tracer.trace("requestLine = buildTraceRequest(request, host)");

            String responseLine;
            tracer.trace("requestLine = buildTraceResponse(response, host)");
            tracer.trace("requestLine + '\n' + responseLine");
        }
    }



    static String buildWarningMessage(HttpUriRequest request, HttpHost host, Header[] warnings) {
        StringBuilder message = new StringBuilder("request [").append(request.getMethod())
                .append(" ")
                .append(host)
                .append(request.getRequestLine())
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
        String requestLine = "curl -iX " + request.getMethod() + " '" + host + request.getRequestLine() + "'";
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

}



