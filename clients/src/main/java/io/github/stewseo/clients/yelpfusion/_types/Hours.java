package io.github.stewseo.clients.yelpfusion._types;

import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ApiTypeHelper;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.util.WithJsonObjectBuilderBase;
import jakarta.json.stream.JsonGenerator;

import java.util.List;
import java.util.function.Function;

@JsonpDeserializable
public class Hours implements JsonpSerializable {
    private final List<Open> open;
    private final String hours_type;
    private final Boolean is_open_now;

    private Hours(Builder builder) {
        this.open = ApiTypeHelper.unmodifiable(builder.open);
        this.hours_type = builder.hours_type;
        this.is_open_now = builder.is_open_now;
    }

    public static Hours of(Function<Builder, ObjectBuilder<Hours>> fn) {
        return fn.apply(new Builder()).build();
    }

    public final List<Open> open() {
        return open;
    }

    public final String hours_type() {
        return hours_type;
    }

    public final Boolean is_open_now() {
        return is_open_now;
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
        private String hours_type;
        private Boolean is_open_now;

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


    public static final JsonpDeserializer<Hours> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
            Hours::setupHoursDeserializer);

    protected static void setupHoursDeserializer(ObjectDeserializer<Builder> op) {
        op.add(Builder::open, JsonpDeserializer.arrayDeserializer(Open._DESERIALIZER), "open");
        op.add(Builder::hours_type, JsonpDeserializer.stringDeserializer(), "hours_type");
        op.add(Builder::is_open_now, JsonpDeserializer.booleanDeserializer(), "is_open_now");
    }
}
