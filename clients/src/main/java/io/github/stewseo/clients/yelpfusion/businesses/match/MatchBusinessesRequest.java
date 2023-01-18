package io.github.stewseo.clients.yelpfusion.businesses.match;

import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.transport.endpoints.SimpleEndpoint;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.yelpfusion._types.Location;
import io.github.stewseo.clients.yelpfusion.businesses.BusinessesRequestBase;
import jakarta.json.stream.JsonGenerator;

import java.util.HashMap;
import java.util.function.Function;

@JsonpDeserializable
public class MatchBusinessesRequest extends BusinessesRequestBase {

    private final String postal_code;
    private final Integer limit;
    private final String match_threshold;

    private MatchBusinessesRequest(Builder builder) {
        super(builder);
        this.postal_code = builder.postal_code;
        this.limit = builder.limit;
        this.match_threshold = builder.match_threshold;
    }

    public static MatchBusinessesRequest of(Function<Builder, ObjectBuilder<MatchBusinessesRequest>> fn) {
        return fn.apply(new Builder()).build();
    }

    public final String postal_code() {
        return postal_code;
    }

    public final Integer limit() {
        return limit;
    }

    public final String match_threshold() {
        return match_threshold;
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        super.serializeInternal(generator, mapper);

        if (this.postal_code != null) {
            generator.writeKey("postal_code");
            generator.write(this.postal_code);
        }
        if (this.limit != null) {
            generator.writeKey("limit");
            generator.write(this.limit);
        }
        if (this.match_threshold != null) {
            generator.writeKey("match_threshold");
            generator.write(this.match_threshold);
        }
    }

    public static class Builder extends BusinessesRequestBase.AbstractBuilder<Builder>
            implements
            ObjectBuilder<MatchBusinessesRequest> {

        private String postal_code;
        private Integer limit;
        private String match_threshold;

        public final Builder postal_code(String address3) {
            this.postal_code = address3;
            return this;
        }

        public final Builder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        public final Builder match_threshold(String match_threshold) {
            this.match_threshold = match_threshold;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public MatchBusinessesRequest build() {
            _checkSingleUse();
            return new MatchBusinessesRequest(this);
        }
    }

    public static final JsonpDeserializer<MatchBusinessesRequest> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
            MatchBusinessesRequest::setupBusinessMatchDeserializer);

    protected static void setupBusinessMatchDeserializer(ObjectDeserializer<Builder> op) {
        setupBusinessRequestBaseDeserializer(op);

        op.add(Builder::postal_code, JsonpDeserializer.stringDeserializer(), "postal_code");
        op.add(Builder::limit, JsonpDeserializer.integerDeserializer(), "limit");
        op.add(Builder::match_threshold, JsonpDeserializer.stringDeserializer(), "match_threshold");
    }

    public static final SimpleEndpoint<MatchBusinessesRequest, ?> _ENDPOINT = new SimpleEndpoint<>("v3/businesses/matches",

            // Request method
            request -> "GET",

            request -> "v3/businesses/matches",

            // Request path
            request -> {
                HashMap<String, String> params = new HashMap<>();
                Location location = request.location();

                if(location != null) {
                    if (request.name() != null) {
                        params.put("name", request.name());
                    }

                    if (location.address1() != null) {
                        params.put("address1", location.address1());
                    }
                    if (location.address2() != null) {
                        params.put("address2", location.address2());
                    }
                    if (location.address3() != null) {
                        params.put("address3", location.address3());
                    }
                    if (location.city() != null) {
                        params.put("city", location.city());
                    }
                    if (location.country() != null) {
                        params.put("country", location.country());
                    }
                    if (location.state() != null) {
                        params.put("state", location.state());
                    }
                    if (request.latitude() != null) {
                        params.put("latitude", String.valueOf(request.latitude()));
                    }
                    if (request.longitude() != null) {
                        params.put("longitude", String.valueOf(request.longitude()));
                    }
                    if (request.phone() != null) {
                        params.put("phone", request.phone());
                    }
                    if (request.limit() != null) {
                        params.put("limit", String.valueOf(request.limit()));
                    }

                    if (request.match_threshold() != null) {
                        params.put("match_threshold", request.match_threshold());
                    }
                }
                return params;

            }, SimpleEndpoint.emptyMap(), false, MatchBusinessesResponse._DESERIALIZER);


}
