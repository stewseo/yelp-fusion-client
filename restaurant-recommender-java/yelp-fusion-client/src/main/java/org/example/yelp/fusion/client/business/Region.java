package org.example.yelp.fusion.client.business;

import jakarta.json.stream.*;
import org.example.elasticsearch.client.json.*;
import org.example.elasticsearch.client.util.*;

import java.util.function.*;

@JsonpDeserializable
public class Region implements JsonpSerializable {


        private Double latitude;

        private Double longitude;


        private Region(Builder builder) {
            this.latitude = builder.latitude;
            this.longitude = builder.longitude;
        }

        public static Region of(Function<Builder, ObjectBuilder<Region>> fn) {
            return fn.apply(new Region.Builder()).build();
        }

        private Double latitude() {
            return latitude;
        }


        private Double longitude() {
            return longitude;
        }

        public static class Builder extends WithJsonObjectBuilderBase<Builder> implements ObjectBuilder<Region> {
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

            public Region build() {
                _checkSingleUse();

                return new Region(this);
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

        public static final JsonpDeserializer<Region> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
                Region::setupRegionDeserializer);

        protected static void setupRegionDeserializer(ObjectDeserializer<Region.Builder> op) {
            op.add(Builder::latitude, JsonpDeserializer.doubleDeserializer(), "latitude");
            op.add(Builder::longitude,  JsonpDeserializer.doubleDeserializer(), "longitude");
        }

    }
