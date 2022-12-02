package io.github.stewseo.lowlevel.restclient;

import org.apache.http.HttpEntity;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.util.EntityUtils;


import java.io.IOException;

import java.util.Locale;

public final class ResponseException extends IOException {

    private final Response response;

    public ResponseException(Response response) throws IOException {
        super(buildMessage(response));
        this.response = response;
    }

    static String buildMessage(Response response) throws IOException {
        String message = String.format(
                Locale.ROOT,
                "method [%s], host [%s], URI [%s], status line [%s]",
                response.getRequestLine().getMethod(),
                response.getHost(),
                response.getRequestLine().getUri(),
                response.getStatusLine().toString()
        );

        if (response.hasWarnings()) {
            message += "\nWarnings: " + response.getWarnings();
        }

        HttpEntity entity = response.getEntity();
        if (entity != null) {
            if (!entity.isRepeatable()) {
                entity = new BufferedHttpEntity(entity);
                response.getHttpResponse().setEntity(entity);
            }
            message += "\n" + EntityUtils.toString(entity);
        }
        return message;
    }

    /**
     * Returns the {@link org.elasticsearch.client.Response} that caused this exception to be thrown.
     */
    public Response getResponse() {
        return response;
    }
}
