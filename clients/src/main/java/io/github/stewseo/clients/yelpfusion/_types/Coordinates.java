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
public class Coordinates implements JsonpSerializable {

    private final Double latitude;

    private final Double longitude;

    private Coordinates(Builder builder) {
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
    }

    public static Coordinates of(Function<Builder, ObjectBuilder<Coordinates>> fn) {
        return fn.apply(new Builder()).build();
    }

    public final Double latitude() {
        return latitude;
    }

    public final Double longitude() {
        return longitude;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        if (this.latitude != null) {
            generator.writeKey("latitude");
            generator.write(this.latitude);
        }
        if (this.longitude != null) {
            generator.writeKey("longitude");
            generator.write(this.longitude);
        }
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    public static class Builder extends WithJsonObjectBuilderBase<Builder> implements ObjectBuilder<Coordinates> {
        private Double latitude;
        private Double longitude;

        public Builder() {
        }

        public final Builder latitude(Double value) {
            this.latitude = value;
            return this;
        }

        public final Builder longitude(Double value) {
            this.longitude = value;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        public Coordinates build() {
            _checkSingleUse();
            return new Coordinates(this);
        }
    }


    public static final JsonpDeserializer<Coordinates> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
            Coordinates::setUpCoordinatesDeserializer);
    protected static void setUpCoordinatesDeserializer(ObjectDeserializer<Builder> op) {
        op.add(Builder::latitude, JsonpDeserializer.doubleDeserializer(), "latitude");
        op.add(Builder::longitude, JsonpDeserializer.doubleDeserializer(), "longitude");
    }
}
