package org.example.yelp.fusion.client.business.search;

import jakarta.json.stream.JsonGenerator;
import org.example.elasticsearch.client.json.*;
import org.example.elasticsearch.client.util.ObjectBuilder;
import org.example.elasticsearch.client.util.WithJsonObjectBuilderBase;

import java.util.function.Function;

@JsonpDeserializable
public class Coordinates implements JsonpSerializable {

    private Double latitude;

    private Double longitude;

    private GeoCoordinates geo_coordinates;

    public Coordinates() {
        geo_coordinates = GeoCoordinates.of(g -> g
                .latitude(latitude)
                .longitude(longitude));
    }

    public void setLatitude(Double latitude){
        this.latitude = latitude;
    }
    public void setLongitude(Double longitude){
        this.longitude = longitude;
    }
    public void setGeo_coordinates(GeoCoordinates geo_coordinates){
        this.geo_coordinates = geo_coordinates;
    }

    private Coordinates(Builder builder) {
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
        this.geo_coordinates = builder.geo_coordinates;
    }

    public static Coordinates of(Function<Builder, ObjectBuilder<Coordinates>> fn) {
        return fn.apply(new Builder()).build();
    }

    public Double latitude() {
        return latitude;
    }

    public Double longitude() {
        return longitude;
    }

    public GeoCoordinates geo_coordinates() {
        return geo_coordinates;
    }


    public static class Builder extends WithJsonObjectBuilderBase<Builder>  implements ObjectBuilder<Coordinates> {
        private Double latitude;
        private Double longitude;

        private GeoCoordinates geo_coordinates;

        public Builder(){}

        public final Builder latitude(Double value) {
            this.latitude = value;
            return this;
        }
        public final Builder longitude(Double value) {
            this.longitude = value;
            return this;
        }
        public final Builder geo_coordinates(GeoCoordinates value) {
            this.geo_coordinates = value;
            return this;
        }

        public final Builder geo_coordinates(Function<GeoCoordinates.Builder, ObjectBuilder<GeoCoordinates>> fn) {
            return geo_coordinates(fn.apply(new GeoCoordinates.Builder()).build());
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

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {
        if (this.longitude != 0) {
            generator.writeKey("longitude");
            generator.write(this.longitude);
        }
        if (this.latitude != 0) {
            generator.writeKey("latitude");
            generator.write(this.latitude);
        }
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    public static final JsonpDeserializer<Coordinates> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
            Coordinates::setUpCoordinatesDeserializer);

    protected static void setUpCoordinatesDeserializer(ObjectDeserializer<Builder> op) {
        op.add(Builder::latitude, JsonpDeserializer.doubleDeserializer(), "latitude");
        op.add(Builder::longitude,  JsonpDeserializer.doubleDeserializer(), "longitude");
        op.add(Builder::geo_coordinates, GeoCoordinates._DESERIALIZER, "geo_coordinates");
    }

    @JsonpDeserializable
    public static class GeoCoordinates implements JsonpSerializable {
        private final Double latitude;
        private final Double longitude;

        public GeoCoordinates(Builder build){
            this.latitude = build.latitude;
            this.longitude = build.longitude;
        }

        public static GeoCoordinates of(Function<Builder, ObjectBuilder<GeoCoordinates>> fn) {
            return fn.apply(new Builder()).build();
        }

        public Double latitude() {
            return latitude;
        }

        public Double longitude() {
            return longitude;
        }

        protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {
            if (this.longitude != null) {
                generator.writeKey("longitude");
                generator.write(this.longitude);
            }
            if (this.latitude != null) {
                generator.writeKey("latitude");
                generator.write(this.latitude);
            }
        }

        @Override
        public String toString() {
            return JsonpUtils.toString(this);
        }

        public static final JsonpDeserializer<GeoCoordinates> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
                GeoCoordinates::setupGeoCoordinateDeserializer);

        public void serialize(JsonGenerator generator, JsonpMapper mapper) {
            generator.writeStartObject();
            serializeInternal(generator, mapper);
            generator.writeEnd();
        }

        public static class Builder extends WithJsonObjectBuilderBase<Builder> implements ObjectBuilder<GeoCoordinates> {
            private Double latitude;
            private Double longitude;

            public final Builder latitude(Double value) {
                this.latitude = value;
                return this;
            }
            public final Builder longitude(Double value) {
                this.longitude = value;
                return this;
            }

            public Builder(){}

            @Override
            protected Builder self() {
                return this;
            }

            public GeoCoordinates build() {
                _checkSingleUse();

                return new GeoCoordinates(this);
            }
        }

        private static void setupGeoCoordinateDeserializer(ObjectDeserializer<Builder> op) {
            op.add(Builder::latitude, JsonpDeserializer.doubleDeserializer(), "latitude");
            op.add(Builder::longitude,  JsonpDeserializer.doubleDeserializer(), "longitude");
        }
    }
}
