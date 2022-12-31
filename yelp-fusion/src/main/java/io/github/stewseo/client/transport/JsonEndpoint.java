package io.github.stewseo.client.transport;


import io.github.stewseo.client.json.JsonpDeserializer;


public interface JsonEndpoint<RequestT, ResponseT, ErrorT> extends Endpoint<RequestT, ResponseT, ErrorT> {
        JsonpDeserializer<ResponseT> responseDeserializer();
}