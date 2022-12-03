package io.github.stewseo.yelp.fusion.client.transport.endpoints;

import io.github.stewseo.yelp.fusion.client.transport.EndpointBase;

import java.util.Map;
import java.util.function.Function;

public class BinaryEndpoint<RequestT> extends EndpointBase<RequestT, BinaryResponse> {

    public BinaryEndpoint(
            String id,
            Function<RequestT, String> method,
            Function<RequestT, String> requestUrl,
            Function<RequestT,
                    Map<String, String>> queryParameters,
            Function<RequestT, Map<String, String>> headers,
            boolean hasRequestBody,
            Object ignored // same number of arguments as SimpleEndpoint
    ) {
        super(id, method, requestUrl, queryParameters, headers, hasRequestBody);
    }

    @Override
    public boolean isError(int statusCode) {
        return statusCode >= 400;
    }
}