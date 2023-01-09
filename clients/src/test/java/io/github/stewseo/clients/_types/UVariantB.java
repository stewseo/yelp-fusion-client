package io.github.stewseo.clients._types;

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
public class UVariantB implements SomeUnionVariant, JsonpSerializable {

    private final Integer number;

    @Override
    public String _variantType() {
        return "variant_a";
    }

    @Nullable
    public Integer number () {
        return this.number;
    }

    public UVariantB(UVariantB.Builder builder) {
        this.number = builder.number;
    }

    @Override
    public void serialize(JsonGenerator builder, JsonpMapper mapper) {
        builder.writeStartObject();
        // Write variant value
        builder.write("type", _variantType());
        if (this.number != null) builder.write("number", this.number);
        builder.writeEnd();
    }

    public static class Builder implements ObjectBuilder<UVariantB> {
        private Integer number;

        public Builder number(@Nullable Integer number) {
            this.number = number;
            return this;
        }

        public UVariantB build() {
            return new UVariantB(this);
        }
    }

    public static final JsonpDeserializer<UVariantB> _DESERIALIZER;

    static {
        ObjectDeserializer<Builder> op = new ObjectDeserializer<>(UVariantB.Builder::new);
        op.add(UVariantB.Builder::number, JsonpDeserializer.integerDeserializer(), "number");
        op.ignore("type");
        _DESERIALIZER = new ObjectBuilderDeserializer<>(op);
    }
}