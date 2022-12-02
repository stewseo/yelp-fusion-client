package io.github.yelp.fusion.client.transport.restclient;


import io.github.lowlevel.restclient.*;
import io.github.yelp.fusion.client._types.ErrorResponse;
import io.github.yelp.fusion.client._types.YelpFusionException;
import io.github.yelp.fusion.client.json.JsonpDeserializer;
import io.github.yelp.fusion.client.json.JsonpMapper;
import io.github.yelp.fusion.client.json.NdJsonpSerializable;
import io.github.yelp.fusion.client.transport.*;
import io.github.yelp.fusion.client.transport.endpoints.BinaryEndpoint;
import io.github.yelp.fusion.client.transport.endpoints.BooleanEndpoint;
import io.github.yelp.fusion.client.transport.endpoints.BooleanResponse;
import io.github.yelp.fusion.client.util.ApiTypeHelper;
import io.github.yelp.fusion.client.util.MissingRequiredPropertyException;

import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CompletableFuture;


public class YelpRestClientTransport implements YelpFusionTransport {

    private static final Logger logger = LoggerFactory.getLogger(YelpRestClientTransport.class);

    @Override
    public void close() throws IOException {
        this.restClient.close();
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

    private final YelpFusionRestClient restClient;
    private final JsonpMapper mapper;

    private co.elastic.clients.json.JsonpMapper esMapper;

    private final YelpRestTransportOptions transportOptions;

    public YelpRestClientTransport(YelpFusionRestClient restClient, JsonpMapper mapper, TransportOptions options) { // TransportOptions
        this.restClient = restClient;
        this.mapper = mapper;
        String optionsString = null;
        if (options == null) {
            transportOptions = YelpRestTransportOptions.initialOptions();
        } else {
            transportOptions = YelpRestTransportOptions.of(options);
        }

    }
    public YelpRestClientTransport(YelpFusionRestClient restClient, JsonpMapper mapper) throws IOException {
        this(restClient, mapper, null);
    }

    public YelpFusionRestClient restClient() {
        return restClient;
    }


    public <RequestT, ResponseT, ErrorT> ResponseT performRequest(
            RequestT request,
            Endpoint<RequestT, ResponseT, ErrorT> endpoint,
            TransportOptions transportOptions) throws IOException {

        Request clientRequest = prepareLowLevelRequest(request, endpoint, transportOptions);

        Response clientResponse = restClient.performRequest(clientRequest);

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

        future.cancellable = restClient.performRequestAsync(clientReq, new ResponseListener() {

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

        return null;
    }


    private <RequestT, ResponseT, ErrorT> Request prepareLowLevelRequest(
            RequestT request,
            Endpoint<RequestT, ResponseT, ErrorT> endpoint,
            TransportOptions options) {

        String method = endpoint.method(request);
        
        String path = endpoint.requestUrl(request);
        
        Request clientReq = new Request(method, path);

        Map<String, String> params = endpoint.queryParameters(request);
        
        if(options != null) {
            RequestOptions restOptions =
                    YelpRestTransportOptions.of(options).restClientRequestOptions();
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

                InputStream content = entity.getContent();

                JsonParser parser = mapper.jsonProvider().createParser(content);

                response = responseParser.deserialize(parser, mapper);
            }
            return response;
        }
        else if(endpoint instanceof BooleanEndpoint<?> bep) {

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


}
