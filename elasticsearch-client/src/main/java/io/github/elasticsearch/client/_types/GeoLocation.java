package io.github.elasticsearch.client._types;

import io.github.elasticsearch.client.json.*;
import io.github.elasticsearch.client.util.*;
import jakarta.json.stream.*;


import java.util.*;
import java.util.function.*;

@JsonpDeserializable
public class GeoLocation implements TaggedUnion<GeoLocation.Kind, Object>, JsonpSerializable {

    public enum Kind {
        Coords, Geohash, Latlon, Text

    }

    private final Kind _kind;
    private final Object _value;

    @Override
    public final Kind _kind() {
        return _kind;
    }

    @Override
    public final Object _get() {
        return _value;
    }

    private GeoLocation(Kind kind, Object value) {
        this._kind = kind;
        this._value = value;
    }

    private GeoLocation(Builder builder) {

        this._kind = ApiTypeHelper.requireNonNull(builder._kind, builder, "<variant kind>");
        this._value = ApiTypeHelper.requireNonNull(builder._value, builder, "<variant value>");

    }

    public static GeoLocation of(Function<Builder, ObjectBuilder<GeoLocation>> fn) {
        return fn.apply(new Builder()).build();
    }


    public boolean isCoords() {
        return _kind == Kind.Coords;
    }


    public List<Double> coords() {
        return TaggedUnionUtils.get(this, Kind.Coords);
    }

    public boolean isGeohash() {
        return _kind == Kind.Geohash;
    }


    public GeoHashLocation geohash() {
        return TaggedUnionUtils.get(this, Kind.Geohash);
    }


    public boolean isLatlon() {
        return _kind == Kind.Latlon;
    }


    public LatLonGeoLocation latlon() {
        return TaggedUnionUtils.get(this, Kind.Latlon);
    }


    public boolean isText() {
        return _kind == Kind.Text;
    }


    public String text() {
        return TaggedUnionUtils.get(this, Kind.Text);
    }

    @Override
    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        if (_value instanceof JsonpSerializable) {
            ((JsonpSerializable) _value).serialize(generator, mapper);
        } else {
            switch (_kind) {
                case Coords :
                    generator.writeStartArray();
                    for (Double item0 : ((List<Double>) this._value)) {
                        generator.write(item0);

                    }
                    generator.writeEnd();

                    break;
                case Text :
                    generator.write(((String) this._value));

                    break;
            }
        }

    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    public static class Builder extends ObjectBuilderBase implements ObjectBuilder<GeoLocation> {
        private Kind _kind;
        private Object _value;

        public ObjectBuilder<GeoLocation> coords(List<Double> v) {
            this._kind = Kind.Coords;
            this._value = v;
            return this;
        }

        public ObjectBuilder<GeoLocation> geohash(GeoHashLocation v) {
            this._kind = Kind.Geohash;
            this._value = v;
            return this;
        }

        public ObjectBuilder<GeoLocation> geohash(
                Function<GeoHashLocation.Builder, ObjectBuilder<GeoHashLocation>> fn) {
            return this.geohash(fn.apply(new GeoHashLocation.Builder()).build());
        }

        public ObjectBuilder<GeoLocation> latlon(LatLonGeoLocation v) {
            this._kind = Kind.Latlon;
            this._value = v;
            return this;
        }

        public ObjectBuilder<GeoLocation> latlon(
                Function<LatLonGeoLocation.Builder, ObjectBuilder<LatLonGeoLocation>> fn) {
            return this.latlon(fn.apply(new LatLonGeoLocation.Builder()).build());
        }

        public ObjectBuilder<GeoLocation> text(String v) {
            this._kind = Kind.Text;
            this._value = v;
            return this;
        }

        public GeoLocation build() {
            _checkSingleUse();
            return new GeoLocation(this);
        }

    }

    private static JsonpDeserializer<GeoLocation> buildGeoLocationDeserializer() {
        return new UnionDeserializer.Builder<GeoLocation, Kind, Object>(GeoLocation::new, false)
                .addMember(Kind.Coords, JsonpDeserializer.arrayDeserializer(JsonpDeserializer.doubleDeserializer()))
                .addMember(Kind.Geohash, GeoHashLocation._DESERIALIZER)
                .addMember(Kind.Latlon, LatLonGeoLocation._DESERIALIZER)
                .addMember(Kind.Text, JsonpDeserializer.stringDeserializer()).build();
    }

    public static final JsonpDeserializer<GeoLocation> _DESERIALIZER = JsonpDeserializer
            .lazy(GeoLocation::buildGeoLocationDeserializer);
}