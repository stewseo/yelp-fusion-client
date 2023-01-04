package io.github.stewseo.clients._types;

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

import javax.annotation.Nullable;
import java.util.function.Function;

@JsonpDeserializable
public class TestSpecialHours implements JsonpSerializable {
    public static final JsonpDeserializer<TestSpecialHours> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
            TestSpecialHours::setUpSpecialHoursDeserializer);
    private final String date;
    private final Boolean is_closed;
    private final String start;
    private final String end;
    private final Boolean is_overnight;

    public TestSpecialHours(Builder builder) {
        this.date = builder.date;
        this.is_closed = builder.is_closed;
        this.start = builder.start;
        this.end = builder.end;
        this.is_overnight = builder.is_overnight;
    }

    public static TestSpecialHours of(Function<Builder, ObjectBuilder<TestSpecialHours>> fn) {
        return fn.apply(new Builder()).build();
    }

    protected static void setUpSpecialHoursDeserializer(ObjectDeserializer<Builder> op) {
        op.add(Builder::is_closed, JsonpDeserializer.booleanDeserializer(), "is_closed");
        op.add(Builder::is_overnight, JsonpDeserializer.booleanDeserializer(), "is_overnight");
        op.add(Builder::start, JsonpDeserializer.stringDeserializer(), "start");
        op.add(Builder::end, JsonpDeserializer.stringDeserializer(), "end");
        op.add(Builder::date, JsonpDeserializer.stringDeserializer(), "date");

    }

    public String date() {
        return date;
    }

    public Boolean is_closed() {
        return is_closed;
    }

    public String start() {
        return start;
    }

    public String end() {
        return end;
    }

    public Boolean is_overnight() {
        return is_overnight;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    // ---------------------------------------------- Builder ---------------------------------- //

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        if (this.date != null) {
            generator.writeKey("date");
            generator.write(this.date);
        }
        if (this.is_closed != null) {
            generator.writeKey("is_closed");
            generator.write(this.is_closed);
        }
        if (this.start != null) {
            generator.writeKey("start");
            generator.write(this.start);
        }
        if (this.end != null) {
            generator.writeKey("end");
            generator.write(this.end);
        }
        if (this.is_overnight != null) {
            generator.writeKey("is_overnight");
            generator.write(this.is_overnight);
        }
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    public static class Builder extends WithJsonObjectBuilderBase<Builder> implements ObjectBuilder<TestSpecialHours> {
        // ---------------------------------------------- Builder Fields ---------------------------------- //
        @Nullable
        private String date;
        @Nullable
        private Boolean is_closed;
        @Nullable
        private String start;
        @Nullable
        private String end;
        @Nullable
        private Boolean is_overnight;

        public final Builder date(String value) {
            this.date = value;
            return this;
        }

        public final Builder is_closed(Boolean value) {
            this.is_closed = value;
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

        public final Builder is_overnight(Boolean value) {
            this.is_overnight = value;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        public TestSpecialHours build() {
            _checkSingleUse();
            return new TestSpecialHours(this);
        }
    }
}
