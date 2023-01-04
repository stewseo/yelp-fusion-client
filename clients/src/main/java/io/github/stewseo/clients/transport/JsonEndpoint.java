package io.github.stewseo.clients.transport;


import io.github.stewseo.clients.json.JsonpDeserializer;


public interface JsonEndpoint<RequestT, ResponseT, ErrorT> extends Endpoint<RequestT, ResponseT, ErrorT> {
    JsonpDeserializer<ResponseT> responseDeserializer();
}