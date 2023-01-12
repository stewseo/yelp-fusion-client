package io.github.stewseo.clients.yelpfusion._types;

import io.github.stewseo.clients.yelpfusion._types.ErrorCause;
import io.github.stewseo.clients.yelpfusion._types.ErrorResponse;

public class YelpFusionException extends RuntimeException {
    private final ErrorResponse response;
    private final String endpointId;

    public YelpFusionException(String endpointId, ErrorResponse response) {

        super("[" + endpointId + "] failed: [" + response.error().type() + "] " + response.error().reason());
        this.response = response;
        this.endpointId = endpointId;

    }

    public String endpointId() {
        return this.endpointId;
    }


    public ErrorResponse response() {
        return this.response;
    }


    public ErrorCause error() {
        return this.response.error();
    }


    public int status() {
        return this.response.status();
    }
}
