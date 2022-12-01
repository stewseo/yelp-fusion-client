package io.github.yelp.fusion.client.yelpfusion.business;


import io.github.yelp.fusion.client.json.*;
import jakarta.json.JsonValue;
import jakarta.json.stream.JsonGenerator;
import io.github.yelp.fusion.client.util.ObjectBuilder;
import io.github.yelp.fusion.client.util.WithJsonObjectBuilderBase;

import javax.annotation.Nullable;
import java.util.function.Function;

@JsonpDeserializable
public class Coordinates implements JsonpSerializable {

    private final Double latitude;

    private final Double longitude;
//    @Nullable
//    private final Object geo_coordinates;

    private Coordinates(Builder builder) {
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
//        this.geo_coordinates = builder.geo_coordinates;
    }

    public static Coordinates of(Function<Builder, ObjectBuilder<Coordinates>> fn) {
        return fn.apply(new Builder()).build();
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


    public static class Builder extends WithJsonObjectBuilderBase<Builder>  implements ObjectBuilder<Coordinates> {
        private Double latitude;

        private Double longitude;
//        @Nullable
//        private Object geo_coordinates;

        public Builder(){}

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

    public static final JsonpDeserializer<Coordinates> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
            Coordinates::setUpCoordinatesDeserializer);

    protected static void setUpCoordinatesDeserializer(ObjectDeserializer<Builder> op) {
        op.add(Builder::latitude, JsonpDeserializer.doubleDeserializer(), "latitude");
        op.add(Builder::longitude,  JsonpDeserializer.doubleDeserializer(), "longitude");
//        op.add(Builder::geo_coordinates, JsonpDeserializer.stringDeserializer(), "geo_coordinates");
    }
}
