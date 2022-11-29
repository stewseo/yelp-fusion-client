package io.github.elasticsearch.client.transport;


import io.github.elasticsearch.client.json.JsonpDeserializer;


public interface JsonEndpoint<RequestT, ResponseT, ErrorT> extends Endpoint<RequestT, ResponseT, ErrorT> {
        JsonpDeserializer<ResponseT> responseDeserializer();
}