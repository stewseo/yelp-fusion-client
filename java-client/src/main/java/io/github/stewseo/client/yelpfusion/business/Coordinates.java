package io.github.stewseo.client.yelpfusion.business;


import io.github.stewseo.client.json.JsonpDeserializable;
import io.github.stewseo.client.json.JsonpDeserializer;
import io.github.stewseo.client.json.JsonpMapper;
import io.github.stewseo.client.json.JsonpSerializable;
import io.github.stewseo.client.json.JsonpUtils;
import io.github.stewseo.client.json.ObjectBuilderDeserializer;
import io.github.stewseo.client.json.ObjectDeserializer;
import io.github.stewseo.client.util.ObjectBuilder;
import io.github.stewseo.client.util.WithJsonObjectBuilderBase;

import jakarta.json.stream.JsonGenerator;

import java.util.function.Function;

@JsonpDeserializable
public class Coordinates implements JsonpSerializable {

    public static final JsonpDeserializer<Coordinates> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
            Coordinates::setUpCoordinatesDeserializer);
    private final Double latitude;
//    @Nullable
//    private final Object geo_coordinates;
    private final Double longitude;

    private Coordinates(Builder builder) {
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
//        this.geo_coordinates = builder.geo_coordinates;
    }

    public static Coordinates of(Function<Builder, ObjectBuilder<Coordinates>> fn) {
        return fn.apply(new Builder()).build();
    }

    protected static void setUpCoordinatesDeserializer(ObjectDeserializer<Builder> op) {
        op.add(Builder::latitude, JsonpDeserializer.doubleDeserializer(), "latitude");
        op.add(Builder::longitude, JsonpDeserializer.doubleDeserializer(), "longitude");
//        op.add(Builder::geo_coordinates, JsonpDeserializer.stringDeserializer(), "geo_coordinates");
    }

    //    public Object geo_coordinates() {
//        return geo_coordinates;
//    }
    public Double latitude() {
        return latitude;
    }

    public Double longitude() {
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
//        if (this.geo_coordinates != null) {
//            generator.writeKey("geo_coordinates");
//            generator.write((JsonValue) this.geo_coordinates);
//        }
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    public static class Builder extends WithJsonObjectBuilderBase<Builder> implements ObjectBuilder<Coordinates> {
        private Double latitude;

        private Double longitude;
//        @Nullable
//        private Object geo_coordinates;

        public Builder() {
        }

        //        public final Builder geo_coordinates(Object value) {
//            this.geo_coordinates = value;
//            return this;
//        }
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
}
