package org.example.elasticsearch.client.transport.endpoints;

import jakarta.json.stream.*;
import org.example.elasticsearch.client.json.*;
import org.example.elasticsearch.client.transport.*;
import org.example.lowlevel.restclient.*;


public class EndpointWithResponseMapperAttr<Req, Res, Err> extends DelegatingJsonEndpoint<Req, Res, Err> {

    private final String attrName;
    private final Object attrValue;

    public EndpointWithResponseMapperAttr(JsonEndpoint<Req, Res, Err> endpoint, String attrName, Object attrValue) {
        super(endpoint);
        this.attrName = attrName;
        this.attrValue = attrValue;
    }
    
    @Override
    public JsonpDeserializer<Res> responseDeserializer() {
        return new DelegatingDeserializer.SameType<Res>() {
            @Override
            protected JsonpDeserializer<Res> unwrap() {
                return endpoint.responseDeserializer();
            }

            @Override
            public Res deserialize(JsonParser parser, JsonpMapper mapper) {
                mapper = mapper.withAttribute(attrName, attrValue);
                return super.deserialize(parser, mapper);
            }

            @Override
            public Res deserialize(JsonParser parser, JsonpMapper mapper, JsonParser.Event event) {
                mapper = mapper.withAttribute(attrName, attrValue);
                return super.deserialize(parser, mapper, event);
            }
        };
    }
}
