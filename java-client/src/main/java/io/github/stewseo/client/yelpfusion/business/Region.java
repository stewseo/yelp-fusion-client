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

import jakarta.json.stream.*;


import java.util.function.*;

@JsonpDeserializable
public class Region implements JsonpSerializable {

    public static final JsonpDeserializer<Region> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
            Region::setupRegionDeserializer);
    private final Center center;

    private Region(Builder builder) {
        this.center = builder.center;
    }

    public static Region of(Function<Builder, ObjectBuilder<Region>> fn) {
        return fn.apply(new Builder()).build();
    }

    protected static void setupRegionDeserializer(ObjectDeserializer<Builder> op) {
        op.add(Builder::center, Center._DESERIALIZER, "center");
    }

    public Center center() {
        return center;
    }

    public Double latitude() {
        return center.latitude;
    }

    public Double longitude() {
        return center.longitude;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {
        if (this.center != null) {
            generator.writeKey("center");
            center.serialize(generator, mapper);
        }
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    public static class Builder extends WithJsonObjectBuilderBase<Builder> implements ObjectBuilder<Region> {
        private Center center;

        public final Builder center(Center value) {
            this.center = value;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        public Region build() {
            _checkSingleUse();

            return new Region(this);
        }
    }

    static class Center implements JsonpSerializable {
        public static final JsonpDeserializer<Center> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
                Center::setupCenterDeserializer);
        private final Double latitude;
        private final Double longitude;

        private Center(Builder builder) {
            this.latitude = builder.latitude;
            this.longitude = builder.longitude;
        }

        public static Center of(Function<Builder, ObjectBuilder<Center>> fn) {
            return fn.apply(new Builder()).build();
        }

        protected static void setupCenterDeserializer(ObjectDeserializer<Builder> op) {
            op.add(Builder::latitude, JsonpDeserializer.doubleDeserializer(), "latitude");
            op.add(Builder::longitude, JsonpDeserializer.doubleDeserializer(), "longitude");
        }

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

        public static class Builder extends WithJsonObjectBuilderBase<Builder> implements ObjectBuilder<Center> {
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

            public Center build() {
                _checkSingleUse();

                return new Center(this);
            }
        }

    }
}