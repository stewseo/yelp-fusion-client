package org.example.yelp.fusion.client.exception;


import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.*;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.example.elasticsearch.client.json.jackson.JacksonJsonpMapper;
import org.example.lowlevel.restclient.PrintUtils;
import org.example.yelp.fusion.client.business.BusinessDetailsResponse;
import org.example.yelp.fusion.client.business.BusinessSearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class YelpRequestLogger {
    private static final Logger tracer = LoggerFactory.getLogger("tracer");

    private YelpRequestLogger() {
    }


    public static <ResponseT> void logEmptyResponseBody(Logger logger, BusinessSearchResponse<ResponseT> businessSearchResponse) {
        logger.info(PrintUtils.red("void logEmptyResponseBody buildTraceRequest, buildWarnMessage"));

        if (logger.isDebugEnabled()) {
            logger.debug(PrintUtils.red("request [ request.getMethod() host getUri(request.getRequestLine()) ] returned [ httpResponse.getStatusLine() ]"
            ));
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


    public static void logGreaterThanMaxResults(Log logger, Double latitude, Double longitude, Integer distance) {
        logger.info(PrintUtils.red("void logGreaterThanMaxResponse store last non null latitude, longitude, distance. " +
                "Track direction by latitude +-? longitude +-? distance?"));

        if(logger.isDebugEnabled()) {
            logger.debug("request line = "+
                    "starting point for next request: latitude ="+latitude+", longitude = "+longitude+", " +
                    "distance from starting point: " + distance);
        }
        if(logger.isTraceEnabled()) {
            logger.trace("request line, starting latitude, longitude, distance of each business, if latitude / longitude are ");
        }
    }


    public static void logFailedYelpRequest(Logger logger, Exception e) {
        logger.info(PrintUtils.red("logging null failed request. ") + e.getCause() + " " + e);
        if (logger.isDebugEnabled()) {
            logger.debug(PrintUtils.red("logging null failed request. ") + e.getCause() + " " + e);
        }
        if (tracer.isTraceEnabled()) {
            String traceRequest;
            logger.trace(PrintUtils.red("logging null failed request. ") + e.getCause() + " " + e);
        }
    }


    public static<ResponseT> void logFailedElasticsearchRequest(Logger logger, ResponseT responseT) {
        logger.info(PrintUtils.red("logging Failed Elasticsearch Request."));

        if(responseT instanceof BusinessSearchResponse<?> business) {
            logger.info("Request for Elasticsearch failed.");
        }

        if (logger.isDebugEnabled()) {
            logger.debug(PrintUtils.red("logger.isDebugEnabled()"));
        }
        if (tracer.isTraceEnabled()) {
            String traceRequest;
            logger.trace(PrintUtils.red("tracer.isTraceEnabled()"));
        }
    }


    public static void logResponseError(Logger logger, Object error) {
        try {
            JsonNode node = new JacksonJsonpMapper().objectMapper().readValue(error.toString(), JsonNode.class);

            logger.info("logging error from response body. status: " + node.getNodeType()+ " reason: " + node.size());

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }



    public static<ResponseT> void logResponse(Logger logger, ResponseT response) {
        if(response instanceof BusinessDetailsResponse<?>) {
            BusinessDetailsResponse<?> resp = (BusinessDetailsResponse<?>) response;

            if (resp.hits() != null) {
                if (resp.hits() != null) {
                    logger.info("businesses = " + resp.hits().size());
                }
            }

            if (logger.isDebugEnabled()) {
                logger.debug("");
            }
            if (tracer.isTraceEnabled()) {
                String traceRequest;
                logger.trace("");
            }

        }

    }

    public static void logResponse(Logger logger, SearchResponse<ObjectNode> response) {
        if (logger.isDebugEnabled()) {
            logger.debug("response:");
        }
        if (tracer.isTraceEnabled()) {
            String traceRequest;
            logger.trace("logging null response. routing: ");
        }
    }
}



