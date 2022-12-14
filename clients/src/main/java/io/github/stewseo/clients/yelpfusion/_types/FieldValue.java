package io.github.stewseo.clients.yelpfusion._types;


import io.github.stewseo.clients.json.JsonData;
import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.util.ApiTypeHelper;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.util.ObjectBuilderBase;
import io.github.stewseo.clients.util.TaggedUnion;
import io.github.stewseo.clients.util.TaggedUnionUtils;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;

import java.util.EnumSet;
import java.util.function.Consumer;

@JsonpDeserializable
public class FieldValue implements TaggedUnion<FieldValue.Kind, Object>, JsonpSerializable {

    public static final FieldValue NULL = new FieldValue(Kind.Null, null);
    public static final FieldValue TRUE = new FieldValue(Kind.Boolean, Boolean.TRUE);
    public static final FieldValue FALSE = new FieldValue(Kind.Boolean, Boolean.FALSE);

    public static final JsonpDeserializer<FieldValue> _DESERIALIZER = JsonpDeserializer
            .lazy(() -> JsonpDeserializer.of(EnumSet.of(JsonParser.Event.VALUE_STRING, JsonParser.Event.VALUE_NUMBER,
                    JsonParser.Event.VALUE_NULL, JsonParser.Event.VALUE_TRUE, JsonParser.Event.VALUE_FALSE,
                    JsonParser.Event.START_OBJECT, JsonParser.Event.START_ARRAY), (parser, mapper, event) -> {
                switch (event) {
                    case VALUE_NULL:
                        return NULL;
                    case VALUE_STRING:
                        return FieldValue.of(parser.getString());
                    case VALUE_TRUE:
                        return FieldValue.of(true);
                    case VALUE_FALSE:
                        return FieldValue.of(false);
                    case VALUE_NUMBER:
                        if (parser.isIntegralNumber()) {
                            return FieldValue.of(parser.getLong());
                        } else {
                            return FieldValue.of(parser.getBigDecimal().doubleValue());
                        }
                    case START_OBJECT:
                    case START_ARRAY:
                        return FieldValue.of(JsonData.of(parser.getValue()));
                }
                return null;
            }));
    private final Kind _kind;
    private final Object _value;

    private FieldValue(Builder builder) {
        this(builder._kind, builder._value);
    }

    private FieldValue(Kind kind, Object value) {
        this._kind = ApiTypeHelper.requireNonNull(kind, this, "<variant kind>");
        this._value = kind == Kind.Null ? null : ApiTypeHelper.requireNonNull(value, this, "<variant value>");
    }

    public static FieldValue of(long value) {
        return new FieldValue(Kind.Long, value);
    }

    public static FieldValue of(double value) {
        return new FieldValue(Kind.Double, value);
    }

    public static FieldValue of(boolean value) {
        return value ? TRUE : FALSE;
    }

    public static FieldValue of(String value) {
        return new FieldValue(Kind.String, value);
    }

    public static FieldValue of(JsonData value) {
        return new FieldValue(Kind.Any, value);
    }

    public static FieldValue of(Consumer<Builder> fn) {
        Builder builder = new Builder();
        fn.accept(builder);
        return builder.build();
    }

    @Override
    public final Kind _kind() {
        return _kind;
    }

    @Override
    public final Object _get() {
        return _value;
    }

    public String _toJsonString() {
        return switch (_kind) {
            case Double -> String.valueOf(this.doubleValue());
            case Long -> String.valueOf(this.longValue());
            case Boolean -> String.valueOf(this.booleanValue());
            case String -> this.stringValue();
            case Null -> "null";
            case Any -> throw new IllegalStateException("Composite field value cannot be formatted as a string");
            default -> throw new IllegalStateException("Unknown kind " + _kind);
        };
    }

    /**
     * Is this variant instance of kind {@code double}?
     */
    public boolean isDouble() {
        return _kind == Kind.Double;
    }

    /**
     * Get the {@code double} variant value.
     *
     * @throws IllegalStateException if the current variant is not of the {@code double} kind.
     */
    public double doubleValue() {
        return TaggedUnionUtils.get(this, Kind.Double);
    }

    /**
     * Is this variant instance of kind {@code long}?
     */
    public boolean isLong() {
        return _kind == Kind.Long;
    }

    /**
     * Get the {@code long} variant value.
     *
     * @throws IllegalStateException if the current variant is not of the {@code long} kind.
     */
    public long longValue() {
        return TaggedUnionUtils.get(this, Kind.Long);
    }

    /**
     * Is this variant instance of kind {@code boolean}?
     */
    public boolean isBoolean() {
        return _kind == Kind.Boolean;
    }

    /**
     * Get the {@code boolean} variant value.
     *
     * @throws IllegalStateException if the current variant is not of the {@code boolean} kind.
     */
    public boolean booleanValue() {
        return TaggedUnionUtils.get(this, Kind.Boolean);
    }

    /**
     * Is this variant instance of kind {@code string}?
     */
    public boolean isString() {
        return _kind == Kind.String;
    }

    /**
     * Get the {@code string} variant value.
     *
     * @throws IllegalStateException if the current variant is not of the {@code string} kind.
     */
    public String stringValue() {
        return TaggedUnionUtils.get(this, Kind.String);
    }

    /**
     * Is this variant instance of kind {@code any}?
     */
    public boolean isAny() {
        return _kind == Kind.Any;
    }

    /**
     * Get the {@code any} variant value, used to represent non-scalar values (i.e.
     * objects and arrays)
     *
     * @throws IllegalStateException if the current variant is not of the {@code string} kind.
     */
    public JsonData anyValue() {
        return TaggedUnionUtils.get(this, Kind.Any);
    }

    /**
     * Is this variant instance of kind {@code null}?
     */
    public boolean isNull() {
        return _kind == Kind.Null;
    }

    @Override
    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        switch (_kind) {
            case Double -> generator.write(((Double) this._value));
            case Long -> generator.write(((Long) this._value));
            case Boolean -> generator.write(((Boolean) this._value));
            case String -> generator.write(((String) this._value));
            case Null -> generator.writeNull();
            case Any -> ((JsonData) this._value).serialize(generator, mapper);
            default -> System.out.println("should not get here");
        }
    }

    public enum Kind {
        Double, Long, Boolean, String, Null, Any
    }

    public static class Builder extends ObjectBuilderBase implements ObjectBuilder<FieldValue> {
        private Kind _kind;
        private Object _value;

        public ObjectBuilder<FieldValue> doubleValue(double v) {
            this._kind = Kind.Double;
            this._value = v;
            return this;
        }

        public ObjectBuilder<FieldValue> longValue(long v) {
            this._kind = Kind.Long;
            this._value = v;
            return this;
        }

        public ObjectBuilder<FieldValue> booleanValue(boolean v) {
            this._kind = Kind.Boolean;
            this._value = v;
            return this;
        }

        public ObjectBuilder<FieldValue> stringValue(String v) {
            this._kind = Kind.String;
            this._value = v;
            return this;
        }

        public ObjectBuilder<FieldValue> anyValue(JsonData v) {
            this._kind = Kind.Any;
            this._value = v;
            return this;
        }

        public ObjectBuilder<FieldValue> nullValue() {
            this._kind = Kind.Null;
            this._value = null;
            return this;
        }

        public FieldValue build() {
            _checkSingleUse();
            return new FieldValue(this);
        }
    }
}
