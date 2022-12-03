package io.github.stewseo.yelp.fusion.client.transport;


import io.github.stewseo.yelp.fusion.client.json.JsonpDeserializer;


public interface JsonEndpoint<RequestT, ResponseT, ErrorT> extends Endpoint<RequestT, ResponseT, ErrorT> {
        JsonpDeserializer<ResponseT> responseDeserializer();
}