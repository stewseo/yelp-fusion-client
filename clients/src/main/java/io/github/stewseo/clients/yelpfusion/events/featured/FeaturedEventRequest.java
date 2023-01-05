package io.github.stewseo.clients.yelpfusion.events.featured;

import co.elastic.clients.elasticsearch._types.RequestBase;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.transport.endpoints.SimpleEndpoint;
import io.github.stewseo.clients.util.ObjectBuilder;
import jakarta.json.stream.JsonGenerator;

import java.util.HashMap;
import java.util.function.Function;

public class FeaturedEventRequest extends RequestBase implements JsonpSerializable {

    // class fields
    private final String location;
    private final Double longitude;
    private final Double latitude;
    private final String locale;

    // constructor
    private FeaturedEventRequest(Builder builder) {
        this.location = builder.location;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
        this.locale = builder.locale;
    }

    public static FeaturedEventRequest of(Function<Builder, ObjectBuilder<FeaturedEventRequest>> fn) {
        return fn.apply(new Builder()).build();
    }

    public final String location() {
        return location;
    }

    public final Double longitude() {
        return longitude;
    }

    public final Double latitude() {
        return latitude;
    }

    public final String locale() {
        return locale;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        if (this.location != null) {
            generator.writeKey("location");
            generator.write(this.location);
        }
        generator.writeEnd();
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    // getters
    // create with Function
    // serialize
    // Builder
    public static class Builder extends AbstractBuilder<Builder>
            implements
            ObjectBuilder<FeaturedEventRequest> {
        private String location;
        private Double longitude;
        private Double latitude;
        private String locale;

        public final Builder location(String value) {
            this.location = value;
            return this;
        }

        public final Builder latitude(Double value) {
            this.latitude = value;
            return this;
        }

        public final Builder longitude(Double value) {
            this.longitude = value;
            return this;
        }

        public final Builder locale(String value) {
            this.locale = value;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        public FeaturedEventRequest build() {
            _checkSingleUse();
            return new FeaturedEventRequest(this);
        }
    }

    public static final SimpleEndpoint<FeaturedEventRequest, ?> _ENDPOINT = new SimpleEndpoint<>("v3/events/featured",// Request method

            request -> "GET",
            // Request path
            request -> "v3/events/featured",

            request -> {
                HashMap<String, String> params = new HashMap<>();
                if (request.location() != null) {
                    params.put("location", request.location());
                }
                if (request.latitude() != null) {
                    params.put("latitude", String.valueOf(request.latitude()));
                }
                if (request.longitude() != null) {
                    params.put("longitude", String.valueOf(request.longitude()));
                }
                if (request.locale() != null) {
                    params.put("locale", request.locale());
                }
                return params;

            }, SimpleEndpoint.emptyMap(), false, FeaturedEventResponse._DESERIALIZER);

}
