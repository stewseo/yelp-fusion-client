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
public class Center implements JsonpSerializable {
    
    private final Double latitude;
    private final Double longitude;
    
    private Center(Builder builder) {
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
    }

    public static Center of(Function<Builder, ObjectBuilder<Center>> fn) {
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

    public static final JsonpDeserializer<Center> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
            Center::setupCenterDeserializer);

    protected static void setupCenterDeserializer(ObjectDeserializer<Builder> op) {
        op.add(Builder::latitude, JsonpDeserializer.doubleDeserializer(), "latitude");
        op.add(Builder::longitude, JsonpDeserializer.doubleDeserializer(), "longitude");
    }
}
