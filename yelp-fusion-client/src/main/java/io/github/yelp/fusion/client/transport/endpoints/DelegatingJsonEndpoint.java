package io.github.yelp.fusion.client.transport.endpoints;

import io.github.yelp.fusion.client.json.JsonpDeserializer;
import io.github.yelp.fusion.client.transport.JsonEndpoint;

import java.util.Map;

public class DelegatingJsonEndpoint<Req, Res, Err> implements JsonEndpoint<Req, Res, Err> {

    protected final JsonEndpoint<Req, Res, Err> endpoint;

    public DelegatingJsonEndpoint(JsonEndpoint<Req, Res, Err> endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public String id() {
        return endpoint.id();
    }

    @Override
    public String method(Req request) {
        return endpoint.method(request);
    }

    @Override
    public String requestUrl(Req request) {
        return endpoint.requestUrl(request);
    }

    @Override
    public Map<String, String> queryParameters(Req request) {
        return endpoint.queryParameters(request);
    }

    @Override
    public Map<String, String> headers(Req request) {
        return endpoint.headers(request);
    }

    @Override
    public boolean hasRequestBody() {
        return endpoint.hasRequestBody();
    }

    @Override
    public JsonpDeserializer<Res> responseDeserializer() {
        return endpoint.responseDeserializer();
    }

    @Override
    public boolean isError(int statusCode) {
        return endpoint.isError(statusCode);
    }

    @Override
    public JsonpDeserializer<Err> errorDeserializer(int statusCode) {
        return endpoint.errorDeserializer(statusCode);
    }
}
