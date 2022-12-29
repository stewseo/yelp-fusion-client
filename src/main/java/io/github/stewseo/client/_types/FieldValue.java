package io.github.stewseo.client._types;


import io.github.stewseo.client.json.JsonData;
import io.github.stewseo.client.json.JsonpDeserializable;
import io.github.stewseo.client.json.JsonpDeserializer;
import io.github.stewseo.client.json.JsonpMapper;
import io.github.stewseo.client.json.JsonpSerializable;
import io.github.stewseo.client.util.ApiTypeHelper;
import io.github.stewseo.client.util.ObjectBuilder;
import io.github.stewseo.client.util.ObjectBuilderBase;
import io.github.stewseo.client.util.TaggedUnion;
import io.github.stewseo.client.util.TaggedUnionUtils;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;

import java.util.EnumSet;
import java.util.function.Consumer;

@JsonpDeserializable
public class FieldValue implements TaggedUnion<FieldValue.Kind, Object>, JsonpSerializable {

    public static FieldValue of(long value) {
        return new FieldValue(FieldValue.Kind.Long, value);
    }

    public static FieldValue of(double value) {
        return new FieldValue(FieldValue.Kind.Double, value);
    }

    public static FieldValue of(boolean value) {
        return value ? TRUE : FALSE;
    }

    public static FieldValue of(String value) {
        return new FieldValue(FieldValue.Kind.String, value);
    }

    public static FieldValue of(JsonData value) {
        return new FieldValue(FieldValue.Kind.Any, value);
    }

    public static final FieldValue NULL = new FieldValue(FieldValue.Kind.Null, null);
    public static final FieldValue TRUE = new FieldValue(FieldValue.Kind.Boolean, Boolean.TRUE);
    public static final FieldValue FALSE = new FieldValue(FieldValue.Kind.Boolean, Boolean.FALSE);

    public enum Kind {
        Double, Long, Boolean, String, Null, Any
    }

    private final FieldValue.Kind _kind;
    private final Object _value;

    @Override
    public final FieldValue.Kind _kind() {
        return _kind;
    }

    @Override
    public final Object _get() {
        return _value;
    }

    public String _toJsonString() {
        switch (_kind) {
            case Double :
                return String.valueOf(this.doubleValue());
            case Long :
                return String.valueOf(this.longValue());
            case Boolean :
                return String.valueOf(this.booleanValue());
            case String :
                return this.stringValue();
            case Null :
                return "null";
            case Any :
                throw new IllegalStateException("Composite field value cannot be formatted as a string");

            default :
                throw new IllegalStateException("Unknown kind " + _kind);
        }
    }

    private FieldValue(FieldValue.Builder builder) {
        this(builder._kind, builder._value);
    }

    private FieldValue(FieldValue.Kind kind, Object value) {
        this._kind = ApiTypeHelper.requireNonNull(kind, this, "<variant kind>");
        this._value = kind == FieldValue.Kind.Null ? null : ApiTypeHelper.requireNonNull(value, this, "<variant value>");
    }

    public static FieldValue of(Consumer<FieldValue.Builder> fn) {
        FieldValue.Builder builder = new FieldValue.Builder();
        fn.accept(builder);
        return builder.build();
    }

    /**
     * Is this variant instance of kind {@code double}?
     */
    public boolean isDouble() {
        return _kind == FieldValue.Kind.Double;
    }

    /**
     * Get the {@code double} variant value.
     *
     * @throws IllegalStateException
     *             if the current variant is not of the {@code double} kind.
     */
    public double doubleValue() {
        return TaggedUnionUtils.get(this, FieldValue.Kind.Double);
    }

    /**
     * Is this variant instance of kind {@code long}?
     */
    public boolean isLong() {
        return _kind == FieldValue.Kind.Long;
    }

    /**
     * Get the {@code long} variant value.
     *
     * @throws IllegalStateException
     *             if the current variant is not of the {@code long} kind.
     */
    public long longValue() {
        return TaggedUnionUtils.get(this, FieldValue.Kind.Long);
    }

    /**
     * Is this variant instance of kind {@code boolean}?
     */
    public boolean isBoolean() {
        return _kind == FieldValue.Kind.Boolean;
    }

    /**
     * Get the {@code boolean} variant value.
     *
     * @throws IllegalStateException
     *             if the current variant is not of the {@code boolean} kind.
     */
    public boolean booleanValue() {
        return TaggedUnionUtils.get(this, FieldValue.Kind.Boolean);
    }

    /**
     * Is this variant instance of kind {@code string}?
     */
    public boolean isString() {
        return _kind == FieldValue.Kind.String;
    }

    /**
     * Get the {@code string} variant value.
     *
     * @throws IllegalStateException
     *             if the current variant is not of the {@code string} kind.
     */
    public String stringValue() {
        return TaggedUnionUtils.get(this, FieldValue.Kind.String);
    }

    /**
     * Is this variant instance of kind {@code any}?
     */
    public boolean isAny() {
        return _kind == FieldValue.Kind.Any;
    }

    /**
     * Get the {@code any} variant value, used to represent non-scalar values (i.e.
     * objects and arrays)
     *
     * @throws IllegalStateException
     *             if the current variant is not of the {@code string} kind.
     */
    public JsonData anyValue() {
        return TaggedUnionUtils.get(this, FieldValue.Kind.Any);
    }

    /**
     * Is this variant instance of kind {@code null}?
     */
    public boolean isNull() {
        return _kind == FieldValue.Kind.Null;
    }

    @Override
    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        switch (_kind) {
            case Double :
                generator.write(((Double) this._value));
                break;
            case Long :
                generator.write(((Long) this._value));
                break;
            case Boolean :
                generator.write(((Boolean) this._value));
                break;
            case String :
                generator.write(((String) this._value));
                break;
            case Null :
                generator.writeNull();
                break;
            case Any :
                ((JsonData) this._value).serialize(generator, mapper);
                break;
        }
    }

    public static class Builder extends ObjectBuilderBase implements ObjectBuilder<FieldValue> {
        private FieldValue.Kind _kind;
        private Object _value;

        public ObjectBuilder<FieldValue> doubleValue(double v) {
            this._kind = FieldValue.Kind.Double;
            this._value = v;
            return this;
        }

        public ObjectBuilder<FieldValue> longValue(long v) {
            this._kind = FieldValue.Kind.Long;
            this._value = v;
            return this;
        }

        public ObjectBuilder<FieldValue> booleanValue(boolean v) {
            this._kind = FieldValue.Kind.Boolean;
            this._value = v;
            return this;
        }

        public ObjectBuilder<FieldValue> stringValue(String v) {
            this._kind = FieldValue.Kind.String;
            this._value = v;
            return this;
        }

        public ObjectBuilder<FieldValue> anyValue(JsonData v) {
            this._kind = FieldValue.Kind.Any;
            this._value = v;
            return this;
        }

        public ObjectBuilder<FieldValue> nullValue() {
            this._kind = FieldValue.Kind.Null;
            this._value = null;
            return this;
        }

        public FieldValue build() {
            _checkSingleUse();
            return new FieldValue(this);
        }
    }

    public static final JsonpDeserializer<FieldValue> _DESERIALIZER = JsonpDeserializer
            .lazy(() -> JsonpDeserializer.of(EnumSet.of(JsonParser.Event.VALUE_STRING, JsonParser.Event.VALUE_NUMBER,
                    JsonParser.Event.VALUE_NULL, JsonParser.Event.VALUE_TRUE, JsonParser.Event.VALUE_FALSE,
                    JsonParser.Event.START_OBJECT, JsonParser.Event.START_ARRAY), (parser, mapper, event) -> {
                switch (event) {
                    case VALUE_NULL :
                        return NULL;
                    case VALUE_STRING :
                        return FieldValue.of(parser.getString());
                    case VALUE_TRUE :
                        return FieldValue.of(true);
                    case VALUE_FALSE :
                        return FieldValue.of(false);
                    case VALUE_NUMBER :
                        if (parser.isIntegralNumber()) {
                            return FieldValue.of(parser.getLong());
                        } else {
                            return FieldValue.of(parser.getBigDecimal().doubleValue());
                        }
                    case START_OBJECT :
                    case START_ARRAY :
                        return FieldValue.of(JsonData.of(parser.getValue()));
                }
                return null;
            }));
}
