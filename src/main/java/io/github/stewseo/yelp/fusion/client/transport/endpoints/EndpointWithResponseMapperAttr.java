package io.github.stewseo.yelp.fusion.client.transport.endpoints;

import io.github.stewseo.yelp.fusion.client.json.DelegatingDeserializer;
import io.github.stewseo.yelp.fusion.client.json.JsonpDeserializer;
import io.github.stewseo.yelp.fusion.client.json.JsonpMapper;
import io.github.stewseo.yelp.fusion.client.transport.JsonEndpoint;
import jakarta.json.stream.JsonParser;


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
        return new DelegatingDeserializer.SameType<>() {
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
