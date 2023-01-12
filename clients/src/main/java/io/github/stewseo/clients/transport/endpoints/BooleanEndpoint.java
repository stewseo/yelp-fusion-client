package io.github.stewseo.clients.transport.endpoints;


import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.transport.endpoints.BooleanResponse;
import io.github.stewseo.clients.transport.endpoints.SimpleEndpoint;

import java.util.Map;
import java.util.function.Function;

public class BooleanEndpoint<RequestT> extends SimpleEndpoint<RequestT, BooleanResponse> {

    public BooleanEndpoint(
            String id,
            Function<RequestT, String> method,
            Function<RequestT, String> requestUrl,
            Function<RequestT,
                    Map<String, String>> queryParameters,
            Function<RequestT, Map<String, String>> headers,
            boolean hasRequestBody,
            JsonpDeserializer<?> responseParser // always null
    ) {
        super(id, method, requestUrl, queryParameters, headers, hasRequestBody, null);
    }

    @Override
    public boolean isError(int statusCode) {
        return statusCode >= 500;
    }

    public boolean getResult(int statusCode) {
        return statusCode < 400;
    }
}
