package io.github.stewseo.clients.yelpfusion._types;

import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ObjectBuilder;
import jakarta.json.stream.JsonGenerator;

import java.util.function.Function;

@JsonpDeserializable
public class LocationQueryParameter extends QueryParameterBase implements QueryParameterVariant {

    private final LocationEnum locationEnum;

    private LocationQueryParameter(Builder builder) {
        super(builder);
        this.locationEnum = builder.locationEnum;
    }

    public static LocationQueryParameter of(Function<Builder, ObjectBuilder<LocationQueryParameter>> fn) {
        return fn.apply(new Builder()).build();
    }

    @Override
    public QueryParameter.Kind _queryFieldKind() {
        return QueryParameter.Kind.Location;
    }


	public final LocationEnum location() {
		return this.locationEnum;
	}

    @Override
    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        super.serializeInternal(generator, mapper);

        if(this.locationEnum != null) {
            generator.writeKey("location");
            locationEnum.serialize(generator, mapper);
        }

    }

    // ---------------------------------------------------------------------------------------------


    public static class Builder extends QueryParameterBase.AbstractBuilder<Builder>
            implements
            ObjectBuilder<LocationQueryParameter> {

        private LocationEnum locationEnum;

        public final Builder location(LocationEnum value) {
            this.locationEnum = value;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        public LocationQueryParameter build() {
            _checkSingleUse();

            return new LocationQueryParameter(this);
        }
    }

    // ---------------------------------------------------------------------------------------------

    public static final JsonpDeserializer<LocationQueryParameter> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
            LocationQueryParameter::setupLocationQueryParamDeserializer);

    protected static void setupLocationQueryParamDeserializer(ObjectDeserializer<Builder> op) {
        setupQueryFieldBaseDeserializer(op);
        op.add(Builder::location, LocationEnum._DESERIALIZER, "location");
    }
}
