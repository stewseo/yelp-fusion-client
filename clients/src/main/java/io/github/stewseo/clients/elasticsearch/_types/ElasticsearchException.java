package io.github.stewseo.clients.elasticsearch._types;

import io.github.stewseo.clients.yelpfusion._types.ErrorCause;
import io.github.stewseo.clients.yelpfusion._types.ErrorResponse;

public class ElasticsearchException extends RuntimeException {

    private final ErrorResponse response;
    private final String endpointId;

    public ElasticsearchException(String endpointId, ErrorResponse response) {
        super("[" + endpointId + "] failed: [" + response.error().type() + "] " + response.error().reason());
        this.response = response;
        this.endpointId = endpointId;
    }

    /**
     * Identifier of the API endpoint that failed to be called.
     */
    public String endpointId() {
        return this.endpointId;
    }

    /**
     * The error response sent by Elasticsearch
     */
    public ErrorResponse response() {
        return this.response;
    }

    /**
     * The cause of the error. Shortcut for {@code response().error()}.
     */
    public ErrorCause error() {
        return this.response.error();
    }

    /**
     * Status code returned by Elasticsearch. Shortcut for
     * {@code response().status()}.
     */
    public int status() {
        return this.response.status();
    }
}
