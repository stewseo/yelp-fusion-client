package org.example.yelp.fusion.client;



import co.elastic.clients.elasticsearch.*;
import co.elastic.clients.elasticsearch._types.*;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;
import org.apache.http.*;
import org.example.elasticsearch.client.*;
import org.example.elasticsearch.client._types.*;
import org.example.elasticsearch.client._types.ErrorResponse;
import org.example.elasticsearch.client.json.jackson.*;
import org.example.elasticsearch.client.transport.*;
import org.example.elasticsearch.client.transport.endpoints.*;
import org.example.elasticsearch.client.util.*;
import org.example.lowlevel.restclient.*;
import org.example.yelp.fusion.client.business.*;
import org.example.yelp.fusion.client.business.search.*;
import org.example.yelp.fusion.client.category.*;
import org.example.yelp.fusion.client.exception.*;
import org.example.yelp.fusion.client.transport.*;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

import static com.fasterxml.jackson.databind.deser.std.JsonNodeDeserializer.getDeserializer;


public class YelpFusionClient extends ApiClient<YelpFusionTransport, YelpFusionClient> {
    public static final RequestOptions YELP_AUTHORIZATION_HEADER;

    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
        builder.addHeader("Authorization", "Bearer " + System.getenv("YELP_API_KEY"));
        YELP_AUTHORIZATION_HEADER = builder.build();
    }

    public YelpFusionClient(YelpFusionTransport transport) {
        super(transport, null);
    }

    public YelpFusionClient(YelpFusionTransport transport, TransportOptions transportOptions) {
        super(transport, transportOptions);
    }

    @Override
    public YelpFusionClient withTransportOptions(TransportOptions transportOptions) {
        return new YelpFusionClient(this.transport, transportOptions);
    }


    public <TDocument> BusinessSearchResponse<TDocument> businessSearch(BusinessSearchRequest request, Class<TDocument> tDocumentClass)
            throws IOException {
        @SuppressWarnings("unchecked")
        JsonEndpoint<BusinessSearchRequest, BusinessSearchResponse<TDocument>, ErrorResponse> endpoint =
                (JsonEndpoint<BusinessSearchRequest, BusinessSearchResponse<TDocument>, ErrorResponse>) BusinessSearchRequest._ENDPOINT;

        endpoint = new EndpointWithResponseMapperAttr<>(endpoint,
                "co.elastic.clients:Deserializer:_global.search.TDocument:_global.search.TDocument", getDeserializer(tDocumentClass));

        try {
            return this.transport.performRequest(request, endpoint, this.transportOptions);

        } catch (HttpException | URISyntaxException | ExecutionException | InterruptedException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }


    public final <TDocument> BusinessSearchResponse<TDocument> businessSearch(
            Function<BusinessSearchRequest.Builder, ObjectBuilder<BusinessSearchRequest>> fn, Class<TDocument> tDocumentClass)
            throws IOException, YelpFusionException, URISyntaxException, HttpException {

        return businessSearch(fn.apply(new BusinessSearchRequest.Builder()).build(), tDocumentClass);
    }

    public <TDocument> BusinessDetailsResponse<TDocument> businessDetails(BusinessDetailsRequest request, Class<TDocument> tDocumentClass)
            throws IOException, YelpFusionException, URISyntaxException, HttpException {
        @SuppressWarnings("unchecked")
        JsonEndpoint<BusinessDetailsRequest, BusinessDetailsResponse<TDocument>, ErrorResponse> endpoint =
                (JsonEndpoint<BusinessDetailsRequest, BusinessDetailsResponse<TDocument>, ErrorResponse>) BusinessDetailsRequest._ENDPOINT;

        endpoint = new EndpointWithResponseMapperAttr<>(endpoint,
                "co.elastic.clients:Deserializer:_global.search.TDocument", getDeserializer(tDocumentClass));

        try {
            return this.transport.performRequest(request, endpoint, this.transportOptions);
        } catch (ExecutionException | InterruptedException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }


    public final <TDocument> BusinessDetailsResponse<TDocument> businessDetails(
            Function<BusinessDetailsRequest.Builder, ObjectBuilder<BusinessDetailsRequest>> fn, Class<TDocument> tDocumentClass)
            throws IOException, YelpFusionException, URISyntaxException, HttpException {

        return businessDetails(fn.apply(new BusinessDetailsRequest.Builder()).build(), tDocumentClass);
    }


    public void getAllBusinessIds() {
    }
}

