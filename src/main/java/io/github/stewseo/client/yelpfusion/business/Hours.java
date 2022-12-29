package io.github.stewseo.client.yelpfusion.business;

import io.github.stewseo.client.json.JsonpDeserializable;
import io.github.stewseo.client.json.JsonpDeserializer;
import io.github.stewseo.client.json.JsonpMapper;
import io.github.stewseo.client.json.JsonpSerializable;
import io.github.stewseo.client.json.JsonpUtils;
import io.github.stewseo.client.json.ObjectBuilderDeserializer;
import io.github.stewseo.client.json.ObjectDeserializer;
import io.github.stewseo.client.util.ApiTypeHelper;
import io.github.stewseo.client.util.ObjectBuilder;
import io.github.stewseo.client.util.WithJsonObjectBuilderBase;

import jakarta.json.stream.JsonGenerator;


import java.util.List;

@JsonpDeserializable
public class Hours implements JsonpSerializable {
    private final List<Open> open;
    private final String hours_type;
    private final Boolean is_open_now;

    public List<Open> open() {
        return open;
    }

    public String hours_type() {
        return hours_type;
    }

    public Boolean is_open_now() {
        return is_open_now;
    }

    private Hours(Builder builder) {
        this.open = builder.open;
        this.hours_type = builder.hours_type;
        this.is_open_now = builder.is_open_now;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {

        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        if (ApiTypeHelper.isDefined(this.open)) {
            generator.writeKey("open");
            generator.writeStartArray();
            for (Open item0 : this.open) {
                item0.serialize(generator, mapper);
            }
            generator.writeEnd();
        }
        if (this.hours_type != null) {
            generator.writeKey("hours_type");
            generator.write(this.hours_type);
        }
        if (this.is_open_now != null) {
            generator.writeKey("is_open_now");
            generator.write(this.is_open_now);
        }
    }
    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static class Builder extends WithJsonObjectBuilderBase<Builder> implements ObjectBuilder<Hours> {
        private List<Open> open;

//        private List<String> open;
        private String hours_type;
        private Boolean is_open_now;

//        public final Builder open(List<String> value) {
//            this.open = _listAddAll(this.open, value);
//            return this;
//        }
//        public final Builder open(String value, String... values) {
//            this.open = _listAdd(this.open, value, values);
//            return this;
//        }
        public final Builder open(List<Open> value) {
            this.open = _listAddAll(this.open, value);
            return this;
        }
        public final Builder open(Open value, Open... values) {
            this.open = _listAdd(this.open, value, values);
            return this;
        }

        public final Builder hours_type(String value) {
            this.hours_type = value;
            return this;
        }

        public final Builder is_open_now(Boolean value) {
            this.is_open_now = value;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        public Hours build() {
            _checkSingleUse();
            return new Hours(this);
        }
    }


    public static final JsonpDeserializer<Hours> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Hours.Builder::new,
            Hours::setupHoursDeserializer);

    protected static void setupHoursDeserializer(ObjectDeserializer<Builder> op) {
        op.add(Hours.Builder::open, JsonpDeserializer.arrayDeserializer(Open._DESERIALIZER), "open");
        op.add(Hours.Builder::hours_type, JsonpDeserializer.stringDeserializer(), "hours_type");
        op.add(Hours.Builder::is_open_now, JsonpDeserializer.booleanDeserializer(), "is_open_now");
    }

    @JsonpDeserializable
    static class Open implements JsonpSerializable {
        private final Boolean is_overnight;
        private final String start;
        private final String end;
        private final Integer day;
        public Boolean is_overnight() {
            return is_overnight;
        }
        public String start() {
            return start;
        }
        public String end() {
            return end;
        }
        public Integer day() {
            return day;
        }

        private Open(Builder builder) {
            this.is_overnight = builder.is_overnight;
            this.start = builder.start;
            this.end = builder.end;
            this.day = builder.day;
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
        public static class Builder extends WithJsonObjectBuilderBase<Open.Builder> implements ObjectBuilder<Open> {
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


        public static final JsonpDeserializer<Open> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Open.Builder::new,
                Open::setupOpenDeserializer);

        protected static void setupOpenDeserializer(ObjectDeserializer<Builder> op) {
            op.add(Builder::is_overnight, JsonpDeserializer.booleanDeserializer(), "is_overnight");
            op.add(Builder::day, JsonpDeserializer.integerDeserializer(), "day");
            op.add(Builder::start, JsonpDeserializer.stringDeserializer(), "start");
            op.add(Builder::end, JsonpDeserializer.stringDeserializer(), "end");
        }

    }
}
