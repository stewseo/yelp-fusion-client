package io.github.stewseo.clients.transport.endpoints;

import io.github.stewseo.clients.transport.endpoints.SimpleEndpoint;
import io.github.stewseo.clients.yelpfusion._types.ErrorResponse;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.transport.JsonEndpoint;

import java.util.Map;
import java.util.function.Function;

public class SimpleJsonEndpoint<RequestT, ResponseT> extends SimpleEndpoint<RequestT, ResponseT>
        implements JsonEndpoint<RequestT, ResponseT, ErrorResponse> {

    public SimpleJsonEndpoint(
            String id,
            Function<RequestT, String> method,
            Function<RequestT, String> requestUrl,
            Function<RequestT,
                    Map<String, String>> queryParameters,
            Function<RequestT, Map<String, String>> headers,
            boolean hasRequestBody,
            JsonpDeserializer<ResponseT> responseParser
    ) {
        super(id, method, requestUrl, queryParameters, headers, hasRequestBody, responseParser);
    }
}