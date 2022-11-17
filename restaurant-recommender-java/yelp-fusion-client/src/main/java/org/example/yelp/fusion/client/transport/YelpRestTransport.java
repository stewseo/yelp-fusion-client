package org.example.yelp.fusion.client.transport;

import com.fasterxml.jackson.databind.*;
import jakarta.json.stream.*;
import org.apache.http.*;
import org.apache.http.entity.*;
import org.apache.http.impl.nio.client.*;
import org.apache.http.message.*;
import org.apache.http.util.*;
import org.example.elasticsearch.client.json.*;
import org.example.elasticsearch.client.json.jackson.*;
import org.example.elasticsearch.client.transport.*;
import org.example.elasticsearch.client.transport.MissingRequiredPropertyException;
import org.example.elasticsearch.client.transport.endpoints.*;
import org.example.elasticsearch.client.transport.restclient.*;
import org.example.elasticsearch.client.util.*;
import org.example.lowlevel.restclient.*;

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.*;
import java.util.stream.*;


public class YelpRestTransport implements YelpFusionTransport {

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
    public void close() throws IOException {}


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
        if(options == null){
            transportOptions = YelpRestTransportOptions.initialOptions();
        }else{
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
        PrintUtils.green("\n\nRequest clientRequest from prepareLowLevelRequest output:\nendpoint= " + clientRequest.getEndpoint() + " method= " + clientRequest.getMethod() + " params= " + clientRequest.getParameters());

        Response clientResponse = restClient.performRequestWithJdkHttpClient(clientRequest);
        PrintUtils.green("\n\nResponse clientResponse from restClient.performRequestWithJdkHttpClient output:\njdkHttpResponse.request = " + clientResponse.jdkHttpResponse().request());

        ResponseT responseT = getHighLevelResponse(clientResponse, endpoint);
        PrintUtils.green("\n\nResponseT responseT from getJdkHighLevelResponse output:\nresponseT = ");

        return responseT;
    }


    @Override
    public <RequestT, ResponseT, ErrorT> CompletableFuture<ResponseT> performRequestAsync(RequestT request, Endpoint<RequestT, ResponseT, ErrorT> endpoint, TransportOptions options) {
        return null;
    }

    private <RequestT, ResponseT, ErrorT> Request prepareLowLevelRequest(
            RequestT request, 
            Endpoint<RequestT,ResponseT,ErrorT> endpoint, 
            TransportOptions options) {
        String method = endpoint.method(request);
        String path = endpoint.requestUrl(request);
        Map<String, String> params = endpoint.queryParameters(request);

        Request clientReq = new Request(method, path);

        if(options != null) {
            RequestOptions restOptions = RestClientOptions.of(options).restClientRequestOptions();

            if (restOptions != null) {
                clientReq.setOptions(restOptions);
            }
        }
        
        clientReq.addParameters(params);

        if (endpoint.hasRequestBody()) {
            // Request has a body and must implement JsonpSerializable or NdJsonpSerializable
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            if (request instanceof NdJsonpSerializable) {
                writeNdJson((NdJsonpSerializable) request, baos);

            }
            else {
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

        java.net.http.HttpResponse<String> jdkHttpResponse = clientResp.jdkHttpResponse();

        return decodeResponse(jdkHttpResponse.statusCode(), jdkHttpResponse.body(), clientResp, endpoint);
    }

    private <ResponseT, ErrorT> ResponseT decodeResponse(int statusCode, String body, Response clientResp, Endpoint<?,ResponseT,ErrorT> endpoint) throws IOException {
        if (endpoint instanceof BooleanEndpoint) {
            PrintUtils.cyan("org.example.yelp.fusion.client.transport.YelpRestTransport.decodeJdkResponse()  endpoint instanceof BooleanEndpoint");
            BooleanEndpoint<?> bep = (BooleanEndpoint<?>) endpoint;
            @SuppressWarnings("unchecked")
            ResponseT response = (ResponseT) new BooleanResponse(bep.getResult(statusCode));
            return response;
        }

        else if (endpoint instanceof JsonEndpoint){
            @SuppressWarnings("unchecked")
            JsonEndpoint<?, ResponseT, ?> jsonEndpoint = (JsonEndpoint<?, ResponseT, ?>)endpoint;
            // Successful response
            ResponseT response = null;
            JsonpDeserializer<ResponseT> responseParser = jsonEndpoint.responseDeserializer();
            if (responseParser != null) {
                // Expecting a body
                if (body == null) {
                    throw new TransportException(
                            "Expecting a response body, but none was sent",
                            endpoint.id(), new ResponseException(clientResp)
                    );
                }
                InputStream content = new ByteArrayInputStream(body.getBytes());
                try (JsonParser parser = mapper.jsonProvider().createParser(content)) {
                    response = responseParser.deserialize(parser, mapper);
                };
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

}
