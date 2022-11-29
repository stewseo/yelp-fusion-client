package io.github.elasticsearch.client.transport.endpoints;

import io.github.elasticsearch.client._types.ErrorResponse;
import io.github.elasticsearch.client.json.JsonpDeserializer;
import io.github.elasticsearch.client.transport.JsonEndpoint;


import java.util.*;
import java.util.function.*;

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