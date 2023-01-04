package io.github.stewseo.clients.yelpfusion._types;

import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.json.UnionDeserializer;
import io.github.stewseo.clients.util.ApiTypeHelper;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.util.ObjectBuilderBase;
import io.github.stewseo.clients.util.TaggedUnion;
import io.github.stewseo.clients.util.TaggedUnionUtils;
import jakarta.json.stream.JsonGenerator;

import java.util.function.Function;

@JsonpDeserializable
public class Time implements TaggedUnion<Time.Kind, Object>, JsonpSerializable {

    public static final JsonpDeserializer<Time> _DESERIALIZER = JsonpDeserializer.lazy(Time::buildTimeDeserializer);
    private final Kind _kind;
    private final Object _value;

    private Time(Kind kind, Object value) {
        this._kind = kind;
        this._value = value;
    }

    private Time(Builder builder) {

        this._kind = ApiTypeHelper.requireNonNull(builder._kind, builder, "<variant kind>");
        this._value = ApiTypeHelper.requireNonNull(builder._value, builder, "<variant value>");

    }

    public static Time of(Function<Builder, ObjectBuilder<Time>> fn) {
        return fn.apply(new Builder()).build();
    }

    private static JsonpDeserializer<Time> buildTimeDeserializer() {
        return new UnionDeserializer.Builder<Time, Kind, Object>(Time::new, false)
                .addMember(Kind.Offset, JsonpDeserializer.integerDeserializer())
                .addMember(Kind.Time, JsonpDeserializer.stringDeserializer()).build();
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
            case Offset -> String.valueOf(this.offset());
            case Time -> this.time();
        };
    }

    public boolean isOffset() {
        return _kind == Kind.Offset;
    }

    public Integer offset() {
        return TaggedUnionUtils.get(this, Kind.Offset);
    }

    public boolean isTime() {
        return _kind == Kind.Time;
    }

    public String time() {
        return TaggedUnionUtils.get(this, Kind.Time);
    }

    @Override
    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        if (_value instanceof JsonpSerializable) {
            ((JsonpSerializable) _value).serialize(generator, mapper);
        } else {
            switch (_kind) {
                case Offset -> generator.write(((Integer) this._value));
                case Time -> generator.write(((String) this._value));
            }
        }

    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    public enum Kind {
        Offset, Time
    }

    public static class Builder extends ObjectBuilderBase implements ObjectBuilder<Time> {
        private Kind _kind;
        private Object _value;

        public ObjectBuilder<Time> offset(Integer v) {
            this._kind = Kind.Offset;
            this._value = v;
            return this;
        }

        public ObjectBuilder<Time> time(String v) {
            this._kind = Kind.Time;
            this._value = v;
            return this;
        }

        public Time build() {
            _checkSingleUse();
            return new Time(this);
        }

    }
}