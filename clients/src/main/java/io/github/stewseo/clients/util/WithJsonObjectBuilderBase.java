package io.github.stewseo.clients.util;

import io.github.stewseo.clients.json.DelegatingDeserializer;
import io.github.stewseo.clients.json.DelegatingJsonpMapper;
import io.github.stewseo.clients.json.JsonData;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpMapperBase;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.json.WithJson;
import io.github.stewseo.clients.util.ObjectBuilderBase;
import jakarta.json.stream.JsonParser;

public abstract class WithJsonObjectBuilderBase<B> extends ObjectBuilderBase implements WithJson<B> {

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
            if (attr == null && name.startsWith("io.github.stewseo:Deserializer")) {
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
