package io.github.elasticsearch.client._types;

import io.github.elasticsearch.client.json.*;
import io.github.elasticsearch.client.util.ApiTypeHelper;
import io.github.elasticsearch.client.util.ObjectBuilder;
import io.github.elasticsearch.client.util.WithJsonObjectBuilderBase;
import jakarta.json.stream.*;


import java.util.function.*;

@JsonpDeserializable
@SuppressWarnings("unused")
public class LatLonGeoLocation implements JsonpSerializable {
    private final double lat;

    private final double lon;

    // ---------------------------------------------------------------------------------------------

    private LatLonGeoLocation(Builder builder) {

        this.lat = ApiTypeHelper.requireNonNull(builder.lat, this, "lat");
        this.lon = ApiTypeHelper.requireNonNull(builder.lon, this, "lon");

    }

    public static LatLonGeoLocation of(Function<Builder, ObjectBuilder<LatLonGeoLocation>> fn) {
        return fn.apply(new Builder()).build();
    }

    public final double lat() {
        return this.lat;
    }


    public final double lon() {
        return this.lon;
    }


    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        generator.writeKey("lat");
        generator.write(this.lat);

        generator.writeKey("lon");
        generator.write(this.lon);

    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    // ---------------------------------------------------------------------------------------------

    public static class Builder extends WithJsonObjectBuilderBase<Builder> implements ObjectBuilder<LatLonGeoLocation> {
        private Double lat;

        private Double lon;

        public final Builder lat(double value) {
            this.lat = value;
            return this;
        }

        public final Builder lon(double value) {
            this.lon = value;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        public LatLonGeoLocation build() {
            _checkSingleUse();

            return new LatLonGeoLocation(this);
        }
    }

    public static final JsonpDeserializer<LatLonGeoLocation> _DESERIALIZER = ObjectBuilderDeserializer
            .lazy(Builder::new, LatLonGeoLocation::setupLatLonGeoLocationDeserializer);

    protected static void setupLatLonGeoLocationDeserializer(ObjectDeserializer<LatLonGeoLocation.Builder> op) {

        op.add(Builder::lat, JsonpDeserializer.doubleDeserializer(), "lat");
        op.add(Builder::lon, JsonpDeserializer.doubleDeserializer(), "lon");

    }

}