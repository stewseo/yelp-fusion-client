package io.github.stewseo.clients.transport.restclient;


import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.NdJsonpSerializable;
import io.github.stewseo.clients.transport.Endpoint;
import io.github.stewseo.clients.transport.JsonEndpoint;
import io.github.stewseo.clients.transport.TransportException;
import io.github.stewseo.clients.transport.TransportOptions;
import io.github.stewseo.clients.transport.YelpFusionTransport;
import io.github.stewseo.clients.transport.endpoints.BinaryEndpoint;
import io.github.stewseo.clients.transport.endpoints.BooleanEndpoint;
import io.github.stewseo.clients.transport.endpoints.BooleanResponse;
import io.github.stewseo.clients.util.ApiTypeHelper;
import io.github.stewseo.clients.util.MissingRequiredPropertyException;
import io.github.stewseo.clients.yelpfusion._types.ErrorResponse;
import io.github.stewseo.clients.yelpfusion._types.YelpFusionException;
import io.github.stewseo.lowlevel.restclient.Cancellable;
import io.github.stewseo.lowlevel.restclient.Request;
import io.github.stewseo.lowlevel.restclient.RequestOptions;
import io.github.stewseo.lowlevel.restclient.Response;
import io.github.stewseo.lowlevel.restclient.ResponseException;
import io.github.stewseo.lowlevel.restclient.ResponseListener;
import io.github.stewseo.lowlevel.restclient.RestClientInterface;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.util.EntityUtils;

import javax.annotation.Nullable;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CompletableFuture;


public class RestClientTransport implements YelpFusionTransport {

    private final RestClientInterface restClientInterface;
    private final JsonpMapper mapper;

    private final RestClientOptions transportOptions;

    private JsonpMapper esMapper;

    public RestClientTransport(RestClientInterface restClientInterface, JsonpMapper mapper, TransportOptions options) { // TransportOptions
        this.restClientInterface = restClientInterface;
        this.mapper = mapper;
        if (options == null) {
            transportOptions = RestClientOptions.initialOptions();
        } else {
            transportOptions = RestClientOptions.of(options);
        }

    }

    public RestClientTransport(RestClientInterface restClientInterface, JsonpMapper mapper) throws IOException {
        this(restClientInterface, mapper, null);
    }

    @Override
    public void close() throws IOException {
        this.restClientInterface.close();
    }

    public RestClientInterface restClientInterface() {
        return restClientInterface;
    }

    public <RequestT, ResponseT, ErrorT> ResponseT performRequest(
            RequestT request,
            Endpoint<RequestT, ResponseT, ErrorT> endpoint,
            TransportOptions transportOptions) throws IOException {

        Request clientRequest = prepareLowLevelRequest(request, endpoint, transportOptions);

        Response clientResponse = restClientInterface.performRequest(clientRequest);

        return getHighLevelResponse(clientResponse, endpoint);
    }

    public <RequestT, ResponseT, ErrorT> CompletableFuture<ResponseT> performRequestAsync(
            RequestT request,
            Endpoint<RequestT, ResponseT, ErrorT> endpoint,
            @Nullable TransportOptions options
    ) {
        Request clientReq = prepareLowLevelRequest(request, endpoint, options);

        RequestFuture<ResponseT> future = new RequestFuture<>();

        boolean disableRequiredChecks = ApiTypeHelper.requiredPropertiesCheckDisabled();

        future.cancellable = restClientInterface.performRequestAsync(clientReq, new ResponseListener() {

            @Override
            public void onSuccess(Response clientResp) {
                try (ApiTypeHelper.DisabledChecksHandle h =
                             ApiTypeHelper.DANGEROUS_disableRequiredPropertiesCheck(disableRequiredChecks)) {
                    ResponseT response = getHighLevelResponse(clientResp, endpoint);

                    future.complete(response);
                } catch (Exception e) {
                    future.completeExceptionally(e);
                }
            }

            @Override
            public void onFailure(Exception e) {
                future.completeExceptionally(e);
            }
        });
        return future;
    }

    private <RequestT, ResponseT, ErrorT> Request prepareLowLevelRequest(
            RequestT request,
            Endpoint<RequestT, ResponseT, ErrorT> endpoint,
            TransportOptions options) {

        String method = endpoint.method(request);

        String path = endpoint.requestUrl(request);

        Request clientReq = new Request(method, path);

        Map<String, String> params = endpoint.queryParameters(request);

        if (options != null) {
            RequestOptions restOptions =
                    RestClientOptions.of(options).restClientRequestOptions();
            clientReq.setOptions(restOptions);
        }

        clientReq.addParameters(params);

        return clientReq;
    }

    private <ResponseT, ErrorT> ResponseT getHighLevelResponse(
            Response clientResp,
            Endpoint<?, ResponseT, ErrorT> endpoint
    ) throws IOException {

        int statusCode = clientResp.getStatusLine().getStatusCode();
        try {

            if (statusCode == 200) {
                checkHeaders();
            }

            if (endpoint.isError(statusCode)) {
                JsonpDeserializer<ErrorT> errorDeserializer = endpoint.errorDeserializer(statusCode);
                if (errorDeserializer == null) {
                    throw new TransportException(
                            "Request failed with status code '" + statusCode + "'",
                            endpoint.id(), new ResponseException(clientResp)
                    );
                }

                HttpEntity entity = clientResp.getEntity();
                if (entity == null) {
                    throw new TransportException(
                            "Expecting a response body, but none was sent",
                            endpoint.id(), new ResponseException(clientResp)
                    );
                }

                // We may have to replay it.
                entity = new BufferedHttpEntity(entity);
                try {
                    InputStream content = entity.getContent();
                    try (JsonParser parser = mapper.jsonProvider().createParser(content)) {
                        ErrorT error = errorDeserializer.deserialize(parser, mapper);
                        // TODO: have the endpoint provide the exception constructor
                        throw new YelpFusionException(endpoint.id(), (ErrorResponse) error);
                    }
                } catch (MissingRequiredPropertyException errorEx) {
                    // Could not decode exception, try the response type
                    try {
                        ResponseT response = decodeResponse(statusCode, entity, clientResp, endpoint);
                        return response;
                    } catch (Exception respEx) {
                        // No better luck: throw the original error decoding exception
                        throw new TransportException("Failed to decode error response", endpoint.id(), new ResponseException(clientResp));
                    }
                }
            } else {
                return decodeResponse(statusCode, clientResp.getEntity(), clientResp, endpoint);
            }

        } finally {
            // Consume the entity unless this is a successful binary endpoint, where the user must consume the entity
            if (!(endpoint instanceof BinaryEndpoint && !endpoint.isError(statusCode))) {
                EntityUtils.consume(clientResp.getEntity());
            }

        }
    }

    private <ResponseT> ResponseT decodeResponse(
            int statusCode, @Nullable HttpEntity entity, Response clientResp, Endpoint<?, ResponseT, ?> endpoint
    ) throws IOException {

        if (endpoint instanceof JsonEndpoint) {
            @SuppressWarnings("unchecked")
            JsonEndpoint<?, ResponseT, ?> jsonEndpoint = (JsonEndpoint<?, ResponseT, ?>) endpoint;
            // Successful response
            ResponseT response = null;
            JsonpDeserializer<ResponseT> responseParser = jsonEndpoint.responseDeserializer();

            if (responseParser != null) {
                if (entity == null) {
                    throw new TransportException(
                            "Expecting a response body, but none was sent",
                            endpoint.id(), new ResponseException(clientResp)
                    );
                }
//                logger.info("entity = new BufferedHttpEntity(entity): " + EntityUtils.toString(entity));
                InputStream content = entity.getContent();

                JsonParser parser = mapper.jsonProvider().createParser(content);

                response = responseParser.deserialize(parser, mapper);
            }
            return response;
        } else if (endpoint instanceof BooleanEndpoint<?> bep) {

            @SuppressWarnings("unchecked")
            ResponseT response = (ResponseT) new BooleanResponse(bep.getResult(statusCode));
            return response;


        } else if (endpoint instanceof BinaryEndpoint<?> bep) {

            @SuppressWarnings("unchecked")
            ResponseT response = (ResponseT) new HttpClientBinaryResponse(entity);
            return response;

        } else {
            throw new TransportException("Unhandled endpoint type: '" + endpoint.getClass().getName() + "'", endpoint.id());
        }
    }

    private void writeNdJson(NdJsonpSerializable value, ByteArrayOutputStream baos) {
        Iterator<?> values = value._serializables();
        while (values.hasNext()) {
            Object item = values.next();
            if (item instanceof NdJsonpSerializable && item != value) { // do not recurse on the item itself
                writeNdJson((NdJsonpSerializable) item, baos);
            } else {
                JsonGenerator generator = mapper.jsonProvider().createGenerator(baos);
                mapper.serialize(item, generator);
                generator.close();
                baos.write('\n');
            }
        }
    }

    @Override
    public JsonpMapper jsonpMapper() {
        return this.mapper;
    }

    @Override
    public TransportOptions options() {
        return transportOptions;
    }

    @SuppressWarnings("EmptyMethod")
    private void checkHeaders() {

    }

    private static class RequestFuture<T> extends CompletableFuture<T> {
        private volatile Cancellable cancellable;

        @Override
        public boolean cancel(boolean mayInterruptIfRunning) {
            boolean cancelled = super.cancel(mayInterruptIfRunning);
            if (cancelled && cancellable != null) {
                cancellable.cancel();
            }
            return cancelled;
        }
    }


}
