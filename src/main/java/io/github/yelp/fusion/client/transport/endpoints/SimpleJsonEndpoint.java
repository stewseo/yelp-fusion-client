package io.github.yelp.fusion.client.transport.endpoints;

import io.github.yelp.fusion.client._types.ErrorResponse;
import io.github.yelp.fusion.client.json.JsonpDeserializer;
import io.github.yelp.fusion.client.transport.JsonEndpoint;

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