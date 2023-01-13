package io.github.stewseo.clients._type;

import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ObjectBuilder;
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;

@JsonpDeserializable
public class UVariantA implements SomeUnionVariant, JsonpSerializable {

    private final String name;

    @Override
    public String _variantType() {
        return "variant_a";
    }

    @Nullable
    public String name() {
        return this.name;
    }

    public UVariantA(Builder builder) {
        this.name = builder.name;
    }

    @Override
    public void serialize(JsonGenerator builder, JsonpMapper mapper) {
        builder.writeStartObject();
        // Write variant value
        builder.write("type", _variantType());
        if (this.name != null) builder.write("name", this.name);
        builder.writeEnd();
    }

    public static class Builder implements ObjectBuilder<UVariantA> {
        private String name;

        public Builder name(@Nullable String name) {
            this.name = name;
            return this;
        }

        public UVariantA build() {
            return new UVariantA(this);
        }
    }

    public static final JsonpDeserializer<UVariantA> _DESERIALIZER;

    static {
        ObjectDeserializer<Builder> op = new ObjectDeserializer<>(Builder::new);
        op.add(Builder::name, JsonpDeserializer.stringDeserializer(), "name");
        op.ignore("type");
        _DESERIALIZER = new ObjectBuilderDeserializer<>(op);
    }
}