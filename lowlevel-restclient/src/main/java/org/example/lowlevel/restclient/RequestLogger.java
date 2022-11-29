package org.example.lowlevel.restclient;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.*;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.http.HttpClient;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class RequestLogger {
    private static final Log tracer = LogFactory.getLog("tracer");

    private RequestLogger() {
    }

    /**
     * Logs a request that failed
     */
    static void logFailedRequest(Log logger, HttpUriRequest request, Node node, Exception e) {

        if(tracer.isTraceEnabled()) {
            tracer.trace(PrintUtils.tracer("Logging Failed Request: " + request.getRequestLine()));
        }

        if(logger.isDebugEnabled()) {
            logger.debug(PrintUtils.debug("request [" + request.getMethod() + " " + node.getHost() + getUri(request.getRequestLine()) + "] failed"), e);

            String traceReq;

//            try {
//                traceReq = buildTraceRequest(request, node.getHost());
//                tracer.trace(PrintUtils.tracer("Executing command in separate process: " + traceReq));
//                Process process = Runtime.getRuntime().exec(traceReq);
//
//                tracer.trace(PrintUtils.tracer("Information about the process: " + process.info()));
//
//                tracer.trace(PrintUtils.tracer("Direct children of the process: " + process.children()));
//
//
//                try(BufferedReader errorReader = process.errorReader())
//                {
//                    if(errorReader.ready()) {
//                        tracer.trace(PrintUtils.tracer("errorReader: " + errorReader.readLine()));
//                    }
//                }
//
//                InputStream inputStream = process.getInputStream();
//                if(inputStream.available() != 0) {
//                    String response = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
//                    tracer.trace(PrintUtils.tracer("cURL response: " + response));
//                }
//
//                inputStream.close();
//                process.destroy();
//
//            } catch (IOException e1) {
//                tracer.trace(PrintUtils.red("error while reading request for trace purposes"), e);
//                traceReq = "";
//            }
        }

    }

    static void logFailedRequest(Logger logger, HttpUriRequest request, Node node, Exception e) {

        if(tracer.isTraceEnabled()) {
            tracer.trace(PrintUtils.tracer("Logging Failed Request: " + request.getRequestLine()));
        }

        if(logger.isDebugEnabled()) {
            logger.debug(PrintUtils.debug("request [" + request.getMethod() + " " + node.getHost() + getUri(request.getRequestLine()) + "] failed"), e.getCause());

            String traceReq;

            try {
                traceReq = buildTraceRequest(request, node.getHost());
                tracer.trace(PrintUtils.tracer("Executing command in separate process: " + traceReq));
                Process process = Runtime.getRuntime().exec(traceReq);

                tracer.trace(PrintUtils.tracer("Information about the process: " + process.info()));

                tracer.trace(PrintUtils.tracer("Direct children of the process: " + process.children()));


                try(BufferedReader errorReader = process.errorReader())
                {
                    if(errorReader.ready()) {
                        tracer.trace(PrintUtils.tracer("errorReader: " + errorReader.readLine()));
                    }
                }

                InputStream inputStream = process.getInputStream();
                if(inputStream.available() != 0) {
                    String response = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
                    tracer.trace(PrintUtils.tracer("cURL response: " + response));
                }

                inputStream.close();
                process.destroy();

            } catch (IOException e1) {
                tracer.trace(PrintUtils.red("error while reading request for trace purposes"), e);
            }
        }

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
    static void logResponse(Logger logger, HttpUriRequest request, HttpHost host, HttpResponse httpResponse) {
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

    public static void logResponse(Logger logger, HttpRequestBase httpRequest, HttpResponse httpResponse, Exception e) {
        logger.debug("status code: " + httpResponse.getStatusLine());
    }
}


