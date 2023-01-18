package io.github.stewseo.clients.yelpfusion._types;

import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.util.WithJsonObjectBuilderBase;
import jakarta.json.stream.JsonGenerator;

import java.util.function.Function;

@JsonpDeserializable
public class Open implements JsonpSerializable {

    private final Boolean is_overnight;
    private final String start;
    private final String end;
    private final Integer day;

    private Open(Builder builder) {
        this.is_overnight = builder.is_overnight;
        this.start = builder.start;
        this.end = builder.end;
        this.day = builder.day;
    }

    public static Open of(Function<Open.Builder, ObjectBuilder<Open>> fn) {
        return fn.apply(new Open.Builder()).build();
    }
    
    public final Boolean is_overnight() {
        return is_overnight;
    }

    public final String start() {
        return start;
    }

    public final String end() {
        return end;
    }

    public final Integer day() {
        return day;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {
        if (this.is_overnight != null) {
            generator.writeKey("is_overnight");
            generator.write(this.is_overnight);
        }
        if (this.day != null) {
            generator.writeKey("day");
            generator.write(this.day);
        }
        if (this.start != null) {
            generator.writeKey("start");
            generator.write(this.start);
        }
        if (this.end != null) {
            generator.writeKey("end");
            generator.write(this.end);
        }
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }


    public static class Builder extends WithJsonObjectBuilderBase<Builder> implements ObjectBuilder<Open> {
        private Boolean is_overnight;

        private String start;

        private String end;

        private Integer day;

        public final Builder is_overnight(Boolean value) {
            this.is_overnight = value;
            return this;
        }

        public final Builder start(String value) {
            this.start = value;
            return this;
        }

        public final Builder end(String value) {
            this.end = value;
            return this;
        }

        public final Builder day(Integer value) {
            this.day = value;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        public Open build() {
            _checkSingleUse();
            return new Open(this);
        }
    }

    public static final JsonpDeserializer<Open> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
            Open::setupOpenDeserializer);

    protected static void setupOpenDeserializer(ObjectDeserializer<Builder> op) {
        op.add(Builder::is_overnight, JsonpDeserializer.booleanDeserializer(), "is_overnight");
        op.add(Builder::day, JsonpDeserializer.integerDeserializer(), "day");
        op.add(Builder::start, JsonpDeserializer.stringDeserializer(), "start");
        op.add(Builder::end, JsonpDeserializer.stringDeserializer(), "end");
    }

}