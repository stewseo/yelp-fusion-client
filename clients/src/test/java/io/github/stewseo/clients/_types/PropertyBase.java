package io.github.stewseo.clients._types;

import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ApiTypeHelper;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.util.WithJsonObjectBuilderBase;
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.Function;

public abstract class PropertyBase implements JsonpSerializable {

    private final Map<String, String> meta;

    private final Map<String, Property> properties;

    // ---------------------------------------------------------------------------------------------

    protected PropertyBase(PropertyBase.AbstractBuilder<?> builder) {

        this.meta = ApiTypeHelper.unmodifiable(builder.meta);
        this.properties = ApiTypeHelper.unmodifiable(builder.properties);
    }

    public final Map<String, String> meta() {
        return this.meta;
    }

    public final Map<String, Property> properties() {
        return this.properties;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        if (ApiTypeHelper.isDefined(this.meta)) {
            generator.writeKey("meta");
            generator.writeStartObject();
            for (Map.Entry<String, String> item0 : this.meta.entrySet()) {
                generator.writeKey(item0.getKey());
                generator.write(item0.getValue());

            }
            generator.writeEnd();

        }

        if (ApiTypeHelper.isDefined(this.properties)) {
            generator.writeKey("properties");
            generator.writeStartObject();
            for (Map.Entry<String, Property> item0 : this.properties.entrySet()) {
                generator.writeKey(item0.getKey());
                item0.getValue().serialize(generator, mapper);

            }
            generator.writeEnd();

        }

    }

    @Override
    public String toString() {
        return JsonpUtils.typedKeysToString(this);
    }

    protected abstract static class AbstractBuilder<BuilderT extends PropertyBase.AbstractBuilder<BuilderT>>
            extends
            WithJsonObjectBuilderBase<BuilderT> {
        @Nullable
        private Map<String, String> meta;

        @Nullable
        private Map<String, Property> properties;

        public final BuilderT meta(Map<String, String> map) {
            this.meta = _mapPutAll(this.meta, map);
            return self();
        }


        public final BuilderT meta(String key, String value) {
            this.meta = _mapPut(this.meta, key, value);
            return self();
        }


        public final BuilderT properties(Map<String, Property> map) {
            this.properties = _mapPutAll(this.properties, map);
            return self();
        }

        public final BuilderT properties(String key, Property value) {
            this.properties = _mapPut(this.properties, key, value);
            return self();
        }


        public final BuilderT properties(String key, Function<Property.Builder, ObjectBuilder<Property>> fn) {
            return properties(key, fn.apply(new Property.Builder()).build());
        }

        protected abstract BuilderT self();

    }

    // ---------------------------------------------------------------------------------------------
    protected static <BuilderT extends PropertyBase.AbstractBuilder<BuilderT>> void setupPropertyBaseDeserializer(
            ObjectDeserializer<BuilderT> op) {

        op.add(PropertyBase.AbstractBuilder::meta, JsonpDeserializer.stringMapDeserializer(JsonpDeserializer.stringDeserializer()),
                "meta");

        op.add(PropertyBase.AbstractBuilder::properties, JsonpDeserializer.stringMapDeserializer(Property._DESERIALIZER),
                "properties");

    }

}

