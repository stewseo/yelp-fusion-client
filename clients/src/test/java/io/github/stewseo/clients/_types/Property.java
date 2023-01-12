package io.github.stewseo.clients._types;

import io.github.stewseo.clients.json.JsonData;
import io.github.stewseo.clients.json.JsonEnum;
import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ApiTypeHelper;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.util.OpenTaggedUnion;
import io.github.stewseo.clients.util.TaggedUnionUtils;
import io.github.stewseo.clients.util.WithJsonObjectBuilderBase;
import jakarta.json.stream.JsonGenerator;

import java.util.function.Function;

@JsonpDeserializable
public class Property implements OpenTaggedUnion<Property.Kind, Object>, JsonpSerializable {

    public enum Kind implements JsonEnum {
        Binary("binary"),
        _Custom(null);

        private final String jsonValue;

        Kind(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String jsonValue() {
            return this.jsonValue;
        }

    }
    private final Kind _kind;

    private final Object _value;

    private final String _customKind;


    @Override
    public final Kind _kind() {
        return _kind;
    }

    @Override
    public final Object _get() {
        return _value;
    }


    Property(PropertyVariant value) {

        this._kind = ApiTypeHelper.requireNonNull(value._propertyKind(), this, "<variant kind>");
        this._value = ApiTypeHelper.requireNonNull(value, this, "<variant value>");
        this._customKind = null;
    }

    private Property(Builder builder) {

        this._kind = ApiTypeHelper.requireNonNull(builder._kind, builder, "<variant kind>");
        this._value = ApiTypeHelper.requireNonNull(builder._value, builder, "<variant value>");
        this._customKind = builder._customKind;
    }

    public Property(String kind, JsonData value) {
        this._kind = Kind._Custom;
        this._value = value;
        this._customKind = kind;
    }

    public Property(Kind kind, Object value) {
        this._kind = Kind._Custom;
        this._value = value;
        this._customKind = null;
    }

    public static Property of(Function<Builder, ObjectBuilder<Property>> fn) {
        return fn.apply(new Builder()).build();
    }


    public boolean _isCustom() {
        return _kind == Kind._Custom;
    }

    public final String _customKind() {
        return _customKind;
    }

    public boolean isBinary() {
        return _kind == Kind.Binary;
    }

    public BinaryProperty binary() {
        return TaggedUnionUtils.get(this, Kind.Binary);
    }


    @Override
    public void serialize(JsonGenerator generator, JsonpMapper mapper) {

        mapper.serialize(_value, generator);
    }

    public String toString() {
        return JsonpUtils.typedKeysToString(this);
    }

    public static class Builder extends WithJsonObjectBuilderBase<Builder> implements ObjectBuilder<Property> {
        private Kind _kind;
        private Object _value;
        private String _customKind;

        public final Builder kind(Kind value) {
            this._kind = value;
            return this;
        }

        public final Builder _value(Object value) {
            this._value = value;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        public ObjectBuilder<Property> binary(BinaryProperty v) {
            this._kind = Kind.Binary;
            this._value = v;
            return this;
        }

        public ObjectBuilder<Property> binary(Function<BinaryProperty.Builder, ObjectBuilder<BinaryProperty>> fn) {
            return this.binary(fn.apply(new BinaryProperty.Builder()).build());
        }

        public ObjectBuilder<Property> _custom(String name, Object data) {
            this._kind = Kind._Custom;
            this._customKind = name;
            this._value = JsonData.of(data);
            return this;
        }
        @Override
        public Property build() {
            _checkSingleUse();
            return new Property(this);
        }
    }

    public static final JsonpDeserializer<Property> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
            Property::setupPropertyDeserializer, Builder::build);

    protected static void setupPropertyDeserializer(ObjectDeserializer<Builder> op) {

        op.add(Builder::binary, BinaryProperty._DESERIALIZER, "binary");
    }

}
