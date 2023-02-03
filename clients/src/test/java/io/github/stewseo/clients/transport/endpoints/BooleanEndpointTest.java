package io.github.stewseo.clients.transport.endpoints;

import io.github.stewseo.clients.transport.Endpoint;
import io.github.stewseo.clients.transport.TransportTest;
import io.github.stewseo.clients.yelpfusion._types.ErrorResponse;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class BooleanEndpointTest extends Assertions {

    public static final Endpoint<?, BooleanResponse, ErrorResponse> _ENDPOINT = new BooleanEndpoint<>(
            "test/exists",

            // Request method
            request -> {
                return "HEAD";

            },

            // Request path
            request -> {

                StringBuilder buf = new StringBuilder();
                buf.append("/_doc");
                return buf.toString();
            },

            // Request parameters
            request -> {
                Map<String, String> params = new HashMap<>();
                params.put("routing", "routing");
                params.put("stored_fields", "[storedField1, storedField2]");
                return params;

            }, SimpleEndpoint.emptyMap(), false, null);

    io.github.stewseo.clients.transport.endpoints.BooleanEndpoint<?> booleanEndpoint = (io.github.stewseo.clients.transport.endpoints.BooleanEndpoint<?>) _ENDPOINT;

    @TransportTest
    public void testHasRequestBody() {
        assertThat(booleanEndpoint.hasRequestBody()).isFalse();
    }

    @TransportTest
    void testIsError() {
        assertThat(booleanEndpoint.isError(200)).isFalse();
    }

    @TransportTest
    void testGetResult() {
        assertThat(booleanEndpoint.getResult(200)).isEqualTo(true);

    }

}