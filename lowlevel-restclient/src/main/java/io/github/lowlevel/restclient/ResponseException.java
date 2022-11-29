package io.github.lowlevel.restclient;

import org.apache.http.*;
import org.apache.http.entity.*;
import org.apache.http.util.*;

import java.io.*;
import java.net.http.HttpResponse;
import java.util.*;

public final class ResponseException extends IOException {

    private final Response response;
    private String body;

    public ResponseException(Response response) throws IOException {
        super(buildMessage(response));
        this.response = response;
    }

    public ResponseException(Response response, Boolean jdkHttpResponse) throws IOException {
        super(buildMessage(response));
        this.response = response;
    }

    private static String buildMessage(String body) {
        return body;
    }

    public static String buildMessage(Response response) throws IOException {
        String message;
        if(response.jdkHttpResponse() != null) {
            HttpResponse<String> jdkResponse = response.jdkHttpResponse();
            message = String.format(
                    Locale.ROOT,
                    "method [%s], host [%s], URI [%s], status line [%s]",
                    jdkResponse.request().method(),
                    response.getHost(),
                    jdkResponse.uri(),
                    jdkResponse.request().toString()
            );

            String body = jdkResponse.body();
            if (body != null) {
                message += "\n" + body;
            }
        }
        else {
            message = String.format(
                    Locale.ROOT,
                    "method [%s], host [%s], URI [%s], status line [%s]",
                    response.getRequestLine().getMethod(),
                    response.getHost(),
                    response.getRequestLine().getUri(),
                    response.getStatusLine().toString()
            );

            if (response.hasWarnings()) {
                message += "\n" + "Warnings: " + response.getWarnings();
            }

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                if (!entity.isRepeatable()) {
                    entity = new BufferedHttpEntity(entity);
                    response.getHttpResponse().setEntity(entity);
                }
                message += "\n" + EntityUtils.toString(entity);
            }
        }

        return message;
    }

    /**
     * Returns the {@link Response} that caused this exception to be thrown.
     */
    public Response getResponse() {
        return response;
    }
}
