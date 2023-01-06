package io.github.stewseo.clients._types;

import co.elastic.clients.elasticsearch.core.search.Suggestion;
import co.elastic.clients.util.ObjectBuilder;
import co.elastic.clients.util.WithJsonObjectBuilderBase;
import io.github.stewseo.clients.json.JsonData;
import io.github.stewseo.clients.json.JsonEnum;
import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.util.ApiTypeHelper;
import io.github.stewseo.clients.util.OpenTaggedUnion;
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;

@JsonpDeserializable
public class Property implements OpenTaggedUnion<Property.Kind, Object>, JsonpSerializable {

    @Override
    public void serialize(JsonGenerator generator, JsonpMapper mapper) {

    }

    @Nullable
    @Override
    public String _customKind() {
        return null;
    }

    public enum Kind implements JsonEnum {
        Binary("binary"),

        Boolean("boolean"),

        Byte("byte"),

        Completion("completion");

        private final String jsonValue;

        Kind(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String jsonValue() {
            return this.jsonValue;
        }

        }

    private final Property.Kind _kind;
    private final Object _value;

    @Override
    public final Property.Kind _kind() {
        return _kind;
    }

    @Override
    public final Object _get() {
        return _value;
    }

    private Property(Property.Builder builder) {

        this._kind = ApiTypeHelper.requireNonNull(builder._kind, builder, "<variant kind>");
        this._value = ApiTypeHelper.requireNonNull(builder._value, builder, "<variant value>");
    }

    public Property(String kind, JsonData value) {
        this._kind = Kind.Binary;
        this._value = value;
    }

    public static class Builder extends WithJsonObjectBuilderBase<Property.Builder> implements ObjectBuilder<Property> {
        private Property.Kind _kind;
        private Object _value;
        private String _customKind;

        @Override
        protected Builder self() {
            return null;
        }

        @Override
        public Property build() {
            _checkSingleUse();
            return new Property(this);
        }
    }
}
