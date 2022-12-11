package io.github.stewseo.yelp.fusion.client.transport;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import io.github.stewseo.lowlevel.restclient.PrintUtils;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.details.BusinessDetailsResponse;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.search.BusinessSearchResponse;
import io.github.stewseo.yelp.fusion.client.json.jackson.JacksonJsonpMapper;
import org.apache.http.*;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
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


    public static <ResponseT> void logEmptyResponseBody(Logger logger, BusinessSearchResponse businessSearchResponse) {

        if (logger.isDebugEnabled()) {
            logger.debug(PrintUtils.red("request [ request.getMethod() host getUri(request.getRequestLine()) ] returned [ httpResponse.getStatusLine() ]"
            ));
        }

        if (tracer.isTraceEnabled()) {
            String requestLine;
            tracer.trace("requestLine = buildTraceRequest(request, host)");

            String responseLine;

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


    static String buildTraceRequest(HttpUriRequest request, HttpHost host) throws IOException {
        String requestLine = "curl -iX " + request.getMethod() + " '" + host + request.getRequestLine() + "'";

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

        if(responseT instanceof BusinessSearchResponse business) {
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
        if(response instanceof BusinessDetailsResponse resp) {

            if (resp.result() != null) {
                logger.info("businesses = " + resp.result().size());
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

}



