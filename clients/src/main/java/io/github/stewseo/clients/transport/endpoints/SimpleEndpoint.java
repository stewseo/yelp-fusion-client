package io.github.stewseo.clients.transport.endpoints;

import io.github.stewseo.clients.yelpfusion._types.ErrorResponse;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.transport.EndpointBase;
import io.github.stewseo.clients.transport.JsonEndpoint;
import org.apache.http.client.utils.URLEncodedUtils;

import java.util.Map;
import java.util.function.Function;


public class SimpleEndpoint<RequestT, ResponseT> extends EndpointBase<RequestT, ResponseT>
        implements JsonEndpoint<RequestT, ResponseT, ErrorResponse> {
    private final JsonpDeserializer<ResponseT> responseParser;

    public SimpleEndpoint(
            String id,
            Function<RequestT, String> method,
            Function<RequestT, String> requestUrl,
            Function<RequestT, Map<String, String>> queryParameters,
            Function<RequestT, Map<String, String>> headers,
            boolean hasRequestBody,
            JsonpDeserializer<ResponseT> responseParser
    ) {
        super(id, method, requestUrl, queryParameters, headers, hasRequestBody);
        this.responseParser = responseParser;
    }

    public static RuntimeException noPathTemplateFound(String what) {
        return new RuntimeException("Could not find a request " + what + " with this set of properties. " +
                "Please check the API documentation, or raise an issue if this should be a valid request.");
    }

    public static void pathEncode(String src, StringBuilder dest) {
        // TODO: avoid dependency on HttpClient here (and use something more efficient)
        dest.append(URLEncodedUtils.formatSegments(src).substring(1));
    }

    @Override
    public JsonpDeserializer<ResponseT> responseDeserializer() {
        return this.responseParser;
    }

    @Override
    public JsonpDeserializer<ErrorResponse> errorDeserializer(int statusCode) {
        return ErrorResponse._DESERIALIZER;
    }

    public <NewResponseT> SimpleEndpoint<RequestT, NewResponseT> withResponseDeserializer(
            JsonpDeserializer<NewResponseT> newResponseParser
    ) {
        return new SimpleEndpoint<>(
                id,
                method,
                requestUrl,
                queryParameters,
                headers,
                hasRequestBody,
                newResponseParser
        );
    }
}