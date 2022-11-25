package org.example.yelp.fusion.client.transport;

import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicNameValuePair;
import org.example.elasticsearch.client._types.ErrorResponse;
import org.example.elasticsearch.client.json.JsonpDeserializer;
import org.example.elasticsearch.client.json.JsonpMapper;
import org.example.elasticsearch.client.json.NdJsonpSerializable;
import org.example.elasticsearch.client.transport.*;
import org.example.elasticsearch.client.transport.restclient.RestClientOptions;
import org.example.elasticsearch.client.util.ApiTypeHelper;
import org.example.lowlevel.restclient.*;
import org.example.yelp.fusion.client.business.search.Business;
import org.example.yelp.fusion.client.business.BusinessDetailsResponse;
import org.example.yelp.fusion.client.exception.YelpFusionException;
import org.example.yelp.fusion.client.exception.YelpRequestLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static java.nio.charset.StandardCharsets.UTF_8;


public class YelpRestTransport implements YelpFusionTransport {

    private static final Logger logger = LoggerFactory.getLogger(YelpRestTransport.class);
    static final ContentType JsonContentType;

    static {

        if (Version.VERSION == null) {
            JsonContentType = ContentType.APPLICATION_JSON;
        } else {

            JsonContentType = ContentType.create(  //Creates a new instance of ContentType with the given parameters.
                    "application/vnd.elasticsearch+json",    //mimeType – MIME type. It may not be null or empty. It may not contain characters <">, <;>, <,> reserved by the HTTP specification. params – parameters.
                    new BasicNameValuePair("compatible-with",  // A name-value pair parameter used as an element of HTTP messages.
                            String.valueOf(Version.VERSION.major())) // Returns: content type
            );
        }
    }

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

        logger.info("Request will be initialized with: " + endpoint.method(request) + endpoint.id());
        Request clientRequest = prepareLowLevelRequest(request, endpoint, transportOptions);
        logger.info("Request prepared. method: " + clientRequest.getMethod() + "endpoint: " + clientRequest.getEndpoint());

        //get a Response, wrapped by the http response and the request line
        Response clientResponse = restClient.performRequestWithJdkHttpClient(clientRequest);
        logger.info("Response returned with: " +
                "http request line: " + clientResponse.jdkHttpResponse().request() +
                "response body byte[] length: " + clientResponse.jdkHttpResponse().body().getBytes().length
        );

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
        Map<String, String> params = endpoint.queryParameters(request);

        Request clientReq = new Request(method, path);

        if (options != null) {
            RequestOptions restOptions = RestClientOptions.of(options).restClientRequestOptions();
            logger.info(" " + restOptions.getHeaders().size());

            if (restOptions != null) {
                clientReq.setOptions(restOptions);
            }
        }

        Logger logger = LoggerFactory.getLogger(this.getClass());
        clientReq.addParameters(params);

        if (endpoint.hasRequestBody()) {
            // Request has a body and must implement JsonpSerializable or NdJsonpSerializable

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            logger.info("byte array output stream size: " + baos.size());
            if (request instanceof NdJsonpSerializable) {
                logger.info("NdJsonpSerializable");
                writeNdJson((NdJsonpSerializable) request, baos);

            } else {
                logger.info("JsonpSerializable");
                JsonGenerator generator = mapper.jsonProvider().createGenerator(baos);
                mapper.serialize(request, generator);
                generator.close();
            }

            clientReq.setEntity(new ByteArrayEntity(baos.toByteArray(), JsonContentType));
        }
        // Request parameter intercepted by LLRC
        clientReq.addParameter("ignore", "400,401,403,404,405");
        return clientReq;
    }


    private <ResponseT, ErrorT> ResponseT getHighLevelResponse(
            Response clientResp,
            Endpoint<?, ResponseT, ErrorT> endpoint
    ) throws IOException {

        int statusCode = clientResp.getJdkHttpResponse().statusCode();
        logger.info("status code: " + statusCode);
        if(statusCode != 200) {
            throw new TransportException(" exception", endpoint.id());
        }

        if (endpoint.isError(statusCode)) {
            logger.info("endpoint.isError(status code) = " + endpoint.isError(statusCode) + statusCode);

            JsonpDeserializer<ErrorT> errorDeserializer = endpoint.errorDeserializer(statusCode);
            logger.info("JsonpDeserializer<ErrorT> returned = " + errorDeserializer);
            if (errorDeserializer == null) {
                throw new TransportException(
                        "Request failed with status code '" + statusCode + "'",
                        endpoint.id(), new ResponseException(clientResp)
                );
            }

            try {
                String response = clientResp.getJdkHttpResponse().body();

                InputStream content = new ByteArrayInputStream(response.getBytes(UTF_8));

                logger.info("InputStream content number of bytes: " + content.available());

                try (JsonParser parser = mapper.jsonProvider().createParser(content)) {
                    ErrorT error = errorDeserializer.deserialize(parser, mapper);
                    // TODO: have the endpoint provide the exception constructor
                    throw new YelpFusionException(endpoint.id(), (ErrorResponse) error);
                }

            } catch (MissingRequiredPropertyException errorEx) {
                YelpRequestLogger.logResponse(logger, clientResp);
                // Could not decode exception, try the response type
                ResponseT response = null;
                try {
                    response = decodeResponse(statusCode, clientResp, endpoint);

                    return response;
                } catch(Exception respEx) {
                    YelpRequestLogger.logResponse(logger, response);
                }
            }
            java.net.http.HttpResponse<String> jdkHttpResponse = clientResp.jdkHttpResponse();

        }

        return decodeResponse(clientResp.jdkHttpResponse().statusCode(), clientResp, endpoint);
    }

    private <ResponseT, ErrorT> ResponseT decodeResponse(
            int statusCode, Response clientResp, Endpoint<?, ResponseT, ErrorT> endpoint)
            throws IOException {

        if (endpoint instanceof JsonEndpoint) {

            @SuppressWarnings("unchecked")
            JsonEndpoint<?, ResponseT, ?> jsonEndpoint = (JsonEndpoint<?, ResponseT, ?>) endpoint;

            // Successful response
            ResponseT response = null;
            JsonpDeserializer<ResponseT> responseParser = jsonEndpoint.responseDeserializer();

            if (responseParser != null) {
                // Expecting a body
                if (clientResp.getJdkHttpResponse().body() == null) {
                    logger.info("responseParser != null but body == null");
                    throw new TransportException(
                            "Expecting a response body, but none was sent",
                            endpoint.id(), new ResponseException(clientResp)
                    );
                }

                String resp = clientResp.getJdkHttpResponse().body();
                logger.info("length of response body in chars: " + resp.length());
                InputStream is = new ByteArrayInputStream(resp.getBytes(StandardCharsets.UTF_8));
                logger.info("byte array input stream: " + is.available());


                try (JsonParser parser = mapper.jsonProvider().createParser(is)) {

                    logger.info("JsonParser parser = mapper.jsonProvider().createParser(new StringReader(resp): " + parser.hasNext());

                    response = responseParser.deserialize(parser, mapper);

                    if(response == null) {
                        logger.info("response == null");
                    }

                    if(response instanceof BusinessDetailsResponse) {
                        BusinessDetailsResponse<Business> res = (BusinessDetailsResponse) response;

                        logger.info("response total " + res.total());

                        logger.info("response businesses size " + res.hits().size());
                    }

                }
            }

            logger.info("response = " + response);

            return response;
        }
        logger.info("returning a null Response");
        return null;
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
                // Expecting a body
                if (entity == null) {
                    throw new TransportException(
                            "Expecting a response body, but none was sent",
                            endpoint.id(), new ResponseException(clientResp)
                    );
                }

                InputStream content = entity.getContent();
                try (JsonParser parser = mapper.jsonProvider().createParser(content)) {
                    response = responseParser.deserialize(parser, mapper);
                }
            }
            logger.info("returning response" + response);
            return response;
        }
        logger.info("returning a null response");
        return null;
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

}
