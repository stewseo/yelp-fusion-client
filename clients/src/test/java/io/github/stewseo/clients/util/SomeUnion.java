package io.github.stewseo.clients.util;

import io.github.stewseo.clients._types.SomeUnionVariant;
import io.github.stewseo.clients._types.UVariantA;
import io.github.stewseo.clients._types.UVariantB;
import io.github.stewseo.clients.json.JsonEnum;
import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import jakarta.json.stream.JsonGenerator;

import java.util.function.Function;

@JsonpDeserializable
public class SomeUnion implements TaggedUnion<SomeUnion.Kind, SomeUnionVariant>, JsonpSerializable {

    public enum Kind implements JsonEnum {
        VariantA("variant_a"),
        VariantB("variant_b");

        private final String jsonValue;

        Kind(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        @Override
        public String jsonValue() {
            return null;
        }
    }

    private final SomeUnionVariant _value;
    private final Kind _type;

    public SomeUnion(Builder builder) {
        this._value = builder._value;
        this._type = builder._type;
    }

    @Override
    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        // Delegate to the variant object
        if (_value instanceof JsonpSerializable) {
            ((JsonpSerializable)_value).serialize(generator, mapper);
        }
    }

    @Override
    public Kind _kind() {
        return _type;
    }

    @Override
    public SomeUnionVariant _get() {
        return _value;
    }

    public UVariantA variantA() {
        return TaggedUnionUtils.get(this, Kind.VariantA);
    }

    public UVariantB variantB() {
        return TaggedUnionUtils.get(this, Kind.VariantB);
    }

    static class Builder {

        private SomeUnionVariant _value;
        private Kind _type;

        // This "builder" doesn't allow building objects. It only contains methods to set a variant.
        // These methods return a builder class with no other methods. This enforces the choice of one and only one
        // variant at the type level (i.e. at compile time).

        // variant_a

        public ObjectBuilder<SomeUnion> variantA(UVariantA value) {
            this._value = value;
            this._type = Kind.VariantA;
            final SomeUnion result = build();
            return () -> result;
        }

        public ObjectBuilder<SomeUnion> variantA(Function<UVariantA.Builder, UVariantA.Builder> fn) {
            return this.variantA(fn.apply(new UVariantA.Builder()).build());
        }

        // variant_b

        public ObjectBuilder<SomeUnion> variantB(UVariantB value) {
            this._value = value;
            this._type = Kind.VariantB;
            final SomeUnion result = build();
            return () -> result;
        }

        public ObjectBuilder<SomeUnion> variantB(Function<UVariantB.Builder, UVariantB.Builder> fn) {
            return this.variantB(fn.apply(new UVariantB.Builder()).build());
        }

        protected SomeUnion build() {
            return new SomeUnion(this);
        }
    }

    public static final JsonpDeserializer<SomeUnion> _DESERIALIZER = ObjectBuilderDeserializer.lazy(SomeUnion.Builder::new,
            SomeUnion::setupSomeUnionDeserializer, SomeUnion.Builder::build);

    protected static void setupSomeUnionDeserializer(ObjectDeserializer<Builder> op) {
        op.add(SomeUnion.Builder::variantA, UVariantA._DESERIALIZER, "variant_a");
        op.add(SomeUnion.Builder::variantB, UVariantB._DESERIALIZER, "variant_b");

        op.setTypeProperty("type", null);
    }
}