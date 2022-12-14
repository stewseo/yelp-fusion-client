package io.github.stewseo.clients._types;

import co.elastic.clients.elasticsearch._types.mapping.BooleanProperty;
import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ObjectBuilder;
import jakarta.json.stream.JsonGenerator;

import java.util.function.Function;

@JsonpDeserializable
public class BinaryProperty extends DocValuesPropertyBase implements PropertyVariant {
    // ---------------------------------------------------------------------------------------------

    private BinaryProperty(BinaryProperty.Builder builder) {
        super(builder);

    }

    public static BinaryProperty of(Function<BinaryProperty.Builder, ObjectBuilder<BinaryProperty>> fn) {
        return fn.apply(new BinaryProperty.Builder()).build();
    }

    @Override
    public Property.Kind _propertyKind() {
        return Property.Kind.Binary;
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {
        generator.write("type", "binary");
        super.serializeInternal(generator, mapper);

    }

    // ---------------------------------------------------------------------------------------------


    public static class Builder extends DocValuesPropertyBase.AbstractBuilder<BinaryProperty.Builder>
            implements
            ObjectBuilder<BinaryProperty> {
        @Override
        protected BinaryProperty.Builder self() {
            return this;
        }

        public BinaryProperty build() {
            _checkSingleUse();

            return new BinaryProperty(this);
        }
    }

    // ---------------------------------------------------------------------------------------------

    public static final JsonpDeserializer<BinaryProperty> _DESERIALIZER = ObjectBuilderDeserializer.lazy(BinaryProperty.Builder::new,
            BinaryProperty::setupBinaryPropertyDeserializer);

    protected static void setupBinaryPropertyDeserializer(ObjectDeserializer<Builder> op) {

        DocValuesPropertyBase.setupDocValuesPropertyBaseDeserializer(op);

        op.ignore("type");
    }

}