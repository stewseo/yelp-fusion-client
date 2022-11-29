package io.github.yelp.fusion.client.transport;

import co.elastic.clients.transport.endpoints.BinaryEndpoint;
import co.elastic.clients.util.MissingRequiredPropertyException;
import io.github.elasticsearch.client.transport.Endpoint;
import io.github.elasticsearch.client.transport.JsonEndpoint;
import io.github.elasticsearch.client.transport.TransportException;
import io.github.elasticsearch.client.transport.TransportOptions;
import io.github.lowlevel.restclient.*;
import io.github.yelp.fusion.client.exception.YelpFusionException;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.util.EntityUtils;
import io.github.elasticsearch.client._types.ErrorResponse;
import io.github.elasticsearch.client.json.JsonpDeserializer;
import io.github.elasticsearch.client.json.JsonpMapper;
import io.github.elasticsearch.client.json.NdJsonpSerializable;
import io.github.elasticsearch.client.util.ApiTypeHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CompletableFuture;


public class YelpRestTransport implements YelpFusionTransport {

    private static final Logger logger = LoggerFactory.getLogger(YelpRestTransport.class);

    @Override
    public void close() throws IOException {
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

    private final RestClient restClient;

    private final JsonpMapper mapper;

    private final YelpRestTransportOptions transportOptions;

    public YelpRestTransport(RestClient restClient, JsonpMapper mapper, TransportOptions options) throws IOException { // TransportOptions
        this.restClient = restClient;
        this.mapper = mapper;
        String optionsString = null;
        if (options == null) {
            transportOptions = YelpRestTransportOptions.initialOptions();
        } else {
            transportOptions = YelpRestTransportOptions.of(options);
        }

    }

    public YelpRestTransport(RestClient restClient, JsonpMapper mapper) throws IOException {
        this(restClient, mapper, null);
    }


    public RestClient restClient() {
        return restClient;
    }


    public <RequestT, ResponseT, ErrorT> ResponseT performRequest(
            RequestT request,
            Endpoint<RequestT, ResponseT, ErrorT> endpoint,
            TransportOptions transportOptions) throws IOException, HttpException {

        Request clientRequest = prepareLowLevelRequest(request, endpoint, transportOptions);
        logger.info(PrintUtils.debug("Low Level Request Prepared. " + clientRequest.getEndpoint() + " " + clientRequest.getOptions()));
        Response clientResponse = restClient.performRequest(clientRequest);
        logger.info(PrintUtils.debug("Response from restClient.performRequest: "));
        return getHighLevelResponse(clientResponse, endpoint);
    }

    public <RequestT, ResponseT, ErrorT> CompletableFuture<ResponseT> performRequestAsync(
            RequestT request,
            Endpoint<RequestT, ResponseT, ErrorT> endpoint,
            @Nullable TransportOptions options
    ) {
        Request clientReq = prepareLowLevelRequest(request, endpoint, options);

        RequestFuture<ResponseT> future = new RequestFuture<>();

        // Propagate required property checks to the thread that will decode the response
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
            logger.info(PrintUtils.debug("setting options headers: " + restOptions.getHeaders()));
            clientReq.setOptions(restOptions);
        }
        logger.info(PrintUtils.debug("adding params keys: " + params.keySet() + " values: " + params.values()));
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
                logger.debug("JsonpDeserializer<ResponseT> responseParser.acceptedEvents().size() " + responseParser.acceptedEvents().size() +
                        "responseParser.nativeEvents().size(): " + responseParser.nativeEvents().size()
                );
                InputStream content = entity.getContent();
                logger.debug("InputStream content = entity.getContent() " + entity.getContentType()
                );

                JsonParser parser = mapper.jsonProvider().createParser(content);
                logger.debug("mapper.jsonProvider().createParser(content) " + parser.getClass().getSimpleName()
                );
                response = responseParser.deserialize(parser, mapper);
            }
            return response;
        }
        else {
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

    private void checkHeaders() {

    }


}
