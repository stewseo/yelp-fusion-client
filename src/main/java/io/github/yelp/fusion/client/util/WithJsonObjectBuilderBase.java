package io.github.yelp.fusion.client.util;

import io.github.yelp.fusion.client.json.*;
import jakarta.json.stream.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class WithJsonObjectBuilderBase<B> extends ObjectBuilderBase implements WithJson<B> {

    private static final Logger logger = LoggerFactory.getLogger(WithJsonObjectBuilderBase.class);

    protected abstract B self();

    @Override
    public B withJson(JsonParser parser, JsonpMapper mapper) {
        JsonpDeserializer<?> classDeser = JsonpMapperBase.findDeserializer(this.getClass().getEnclosingClass());
        if (classDeser == null) {
            throw new IllegalArgumentException("Class " + this.getClass().getEnclosingClass() + " cannot be read from JSON");
        }

        // Generic parameters are always deserialized to JsonData unless the parent mapper can provide a deserializer
        mapper = new WithJsonMapper(mapper);

        @SuppressWarnings("unchecked")
        ObjectDeserializer<B> builderDeser = (ObjectDeserializer<B>) DelegatingDeserializer.unwrap(classDeser);
        return builderDeser.deserialize(self(), parser, mapper, parser.next());
    }

    private static class WithJsonMapper extends DelegatingJsonpMapper {
        WithJsonMapper(JsonpMapper parent) {
            super(parent);
        }

        @Override
        public <T> T attribute(String name) {
            T attr = mapper.attribute(name);
            if (attr == null && name.startsWith("co.elastic.clients:Deserializer")) {
                @SuppressWarnings("unchecked")
                T result = (T) JsonData._DESERIALIZER;
                return result;
            } else {
                return attr;
            }
        }

        @Override
        public <T> JsonpMapper withAttribute(String name, T value) {
            return new WithJsonMapper(this.mapper.withAttribute(name, value));
        }
    }
}