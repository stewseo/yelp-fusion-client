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
public class Region implements JsonpSerializable {

    private final Coordinate center;

    private Region(Builder builder) {
        this.center = builder.center;
    }

    public static Region of(Function<Builder, ObjectBuilder<Region>> fn) {
        return fn.apply(new Builder()).build();
    }

    protected static void setupRegionDeserializer(ObjectDeserializer<Builder> op) {
        op.add(Builder::center, Coordinate._DESERIALIZER, "center");
    }

    public final Coordinate center() {
        return center;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {
        if (this.center != null) {
            generator.writeKey("center");
            this.center.serialize(generator, mapper);
        }
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    public static class Builder extends WithJsonObjectBuilderBase<Builder> implements ObjectBuilder<Region> {
         private Coordinate center;

        public final Builder center(Coordinate value) {
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

    public static final JsonpDeserializer<Region> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new, Region::setupRegionDeserializer);


}