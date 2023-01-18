package io.github.stewseo.clients.yelpfusion._types;

import co.elastic.clients.util.ApiTypeHelper;
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
public class Coordinate implements JsonpSerializable {
    private final Double latitude;
    private final Double longitude;
    
    private Coordinate(Builder builder) {
        this.latitude = ApiTypeHelper.requireNonNull(builder.latitude, this, "latitude");
        this.longitude = ApiTypeHelper.requireNonNull(builder.longitude, this, "longitude");
    }

    public static Coordinate of(Function<Builder, ObjectBuilder<Coordinate>> fn) {
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
        generator.writeKey("latitude");
        generator.write(this.latitude);

        generator.writeKey("longitude");
        generator.write(this.longitude);
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    public static class Builder extends WithJsonObjectBuilderBase<Builder> implements ObjectBuilder<Coordinate> {
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

        @Override
        protected Builder self() {
            return this;
        }

        public Coordinate build() {
            _checkSingleUse();

            return new Coordinate(this);
        }
    }

    public static final JsonpDeserializer<Coordinate> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
            Coordinate::setupCenterDeserializer);

    protected static void setupCenterDeserializer(ObjectDeserializer<Builder> op) {
        op.add(Builder::latitude, JsonpDeserializer.doubleDeserializer(), "latitude");
        op.add(Builder::longitude, JsonpDeserializer.doubleDeserializer(), "longitude");
    }
}
