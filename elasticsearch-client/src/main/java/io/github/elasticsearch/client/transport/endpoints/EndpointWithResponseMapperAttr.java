package io.github.elasticsearch.client.transport.endpoints;

import io.github.elasticsearch.client.json.DelegatingDeserializer;
import io.github.elasticsearch.client.json.JsonpDeserializer;
import io.github.elasticsearch.client.json.JsonpMapper;
import io.github.elasticsearch.client.transport.JsonEndpoint;
import jakarta.json.stream.*;


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
