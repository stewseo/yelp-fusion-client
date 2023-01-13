package io.github.stewseo.clients._type;

import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ObjectBuilder;
import jakarta.json.stream.JsonGenerator;

import java.util.function.Function;

@JsonpDeserializable
public class TermProperty extends DocValuesPropertyBase implements PropertyVariant {
    // ---------------------------------------------------------------------------------------------

    private TermProperty(Builder builder) {
        super(builder);

    }

    public static TermProperty of(Function<Builder, ObjectBuilder<TermProperty>> fn) {
        return fn.apply(new Builder()).build();
    }

    @Override
    public Property.Kind _propertyKind() {
        return Property.Kind.Term;
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {
        generator.write("type", "binary");
        super.serializeInternal(generator, mapper);

    }

    // ---------------------------------------------------------------------------------------------


    public static class Builder extends AbstractBuilder<Builder>
            implements
            ObjectBuilder<TermProperty> {
        @Override
        protected Builder self() {
            return this;
        }

        public TermProperty build() {
            _checkSingleUse();

            return new TermProperty(this);
        }
    }

    // ---------------------------------------------------------------------------------------------

    public static final JsonpDeserializer<TermProperty> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
            TermProperty::setupTermPropertyDeserializer);

    protected static void setupTermPropertyDeserializer(ObjectDeserializer<Builder> op) {

        DocValuesPropertyBase.setupDocValuesPropertyBaseDeserializer(op);

        op.ignore("type");
    }

}
