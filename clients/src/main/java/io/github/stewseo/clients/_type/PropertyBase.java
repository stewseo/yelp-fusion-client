package io.github.stewseo.clients._type;

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

    private final Map<String, Property> properties;

    // ---------------------------------------------------------------------------------------------

    protected PropertyBase(AbstractBuilder<?> builder) {
        this.properties = ApiTypeHelper.unmodifiable(builder.properties);
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

    protected abstract static class AbstractBuilder<BuilderT extends AbstractBuilder<BuilderT>>
            extends
            WithJsonObjectBuilderBase<BuilderT> {
        @Nullable
        private Map<String, String> meta;

        @Nullable
        private Map<String, Property> properties;

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
    protected static <BuilderT extends AbstractBuilder<BuilderT>> void setupPropertyBaseDeserializer(
            ObjectDeserializer<BuilderT> op) {

        op.add(AbstractBuilder::properties, JsonpDeserializer.stringMapDeserializer(Property._DESERIALIZER),
                "properties");

    }

}

