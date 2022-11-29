package io.github.elasticsearch.client._types;


import io.github.elasticsearch.client.json.*;
import io.github.elasticsearch.client.util.ApiTypeHelper;
import io.github.elasticsearch.client.util.ObjectBuilder;
import io.github.elasticsearch.client.util.WithJsonObjectBuilderBase;
import jakarta.json.stream.*;


import java.util.function.*;

@SuppressWarnings("unused")
@JsonpDeserializable
public class GeoHashLocation implements JsonpSerializable {
    private final String geohash;
    
    private GeoHashLocation(Builder builder) {

        this.geohash = ApiTypeHelper.requireNonNull(builder.geohash, this, "geohash");

    }

    public static GeoHashLocation of(Function<Builder, ObjectBuilder<GeoHashLocation>> fn) {
        return fn.apply(new Builder()).build();
    }


    public final String geohash() {
        return this.geohash;
    }


    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        generator.writeKey("geohash");
        generator.write(this.geohash);

    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static class Builder extends WithJsonObjectBuilderBase<Builder> implements ObjectBuilder<GeoHashLocation> {
        private String geohash;

        public final Builder geohash(String value) {
            this.geohash = value;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }


        public GeoHashLocation build() {
            _checkSingleUse();

            return new GeoHashLocation(this);
        }
    }


    public static final JsonpDeserializer<GeoHashLocation> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
            GeoHashLocation::setupGeoHashLocationDeserializer);

    protected static void setupGeoHashLocationDeserializer(ObjectDeserializer<GeoHashLocation.Builder> op) {

        op.add(Builder::geohash, JsonpDeserializer.stringDeserializer(), "geohash");

    }

}