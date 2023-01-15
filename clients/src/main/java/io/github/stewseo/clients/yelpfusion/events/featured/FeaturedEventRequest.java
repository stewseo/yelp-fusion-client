package io.github.stewseo.clients.yelpfusion.events.featured;

import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.transport.endpoints.SimpleEndpoint;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.yelpfusion.YelpFusionRequestBase;
import jakarta.json.stream.JsonGenerator;

import java.util.HashMap;
import java.util.function.Function;

public class FeaturedEventRequest extends YelpFusionRequestBase {

    // constructor
    private FeaturedEventRequest(Builder builder) {
        super(builder);
    }

    public static FeaturedEventRequest of(Function<Builder, ObjectBuilder<FeaturedEventRequest>> fn) {
        return fn.apply(new Builder()).build();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {
        super.serializeInternal(generator, mapper);
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    public static class Builder extends AbstractBuilder<Builder>
            implements
                ObjectBuilder<FeaturedEventRequest> {

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
