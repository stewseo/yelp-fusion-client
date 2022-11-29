package io.github.elasticsearch.client.transport;


import io.github.elasticsearch.client.json.JsonpDeserializer;
import java.util.*;

// //An endpoint links requests and responses to HTTP protocol encoding.
// It also defines the error response when the server cannot perform the request.
public interface Endpoint<RequestT, ResponseT, ErrorT> {

    String id();
    String method(RequestT request);

    String requestUrl(RequestT request);

    default Map<String, String> queryParameters(RequestT request) {
        return Collections.emptyMap();
    }

    default Map<String, String> headers(RequestT request) {
        return Collections.emptyMap();
    }

    boolean hasRequestBody();
    boolean isError(int statusCode);

    JsonpDeserializer<ErrorT> errorDeserializer(int statusCode);
}