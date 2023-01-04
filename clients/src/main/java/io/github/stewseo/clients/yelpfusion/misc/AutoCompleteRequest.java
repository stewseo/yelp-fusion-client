package io.github.stewseo.clients.yelpfusion.misc;


import io.github.stewseo.clients.yelpfusion._types.RequestBase;
import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.transport.endpoints.SimpleEndpoint;
import io.github.stewseo.clients.util.ObjectBuilder;
import jakarta.json.stream.JsonGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@JsonpDeserializable
public class AutoCompleteRequest extends RequestBase implements JsonpSerializable {


    private final String text;
    private final Double latitude;
    private final Double longitude;
    private final String locale;

    private AutoCompleteRequest(Builder builder) {
        this.text = builder.text;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
        this.locale = builder.locale;
    }

    public static AutoCompleteRequest of(Function<Builder, ObjectBuilder<AutoCompleteRequest>> fn) {
        return fn.apply(new Builder()).build();
    }

    public final String text() {
        return text;
    }

    public final Double latitude() {
        return latitude;
    }

    public final Double longitude() {
        return longitude;
    }

    public final String locale() {
        return locale;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        if (this.text != null) {
            generator.writeKey("text");
            generator.write(this.text);
        }
        if (this.latitude != null) {
            generator.writeKey("latitude");
            generator.write(this.latitude);
        }
        if (this.longitude != null) {
            generator.writeKey("longitude");
            generator.write(this.longitude);
        }
        if (this.locale != null) {
            generator.writeKey("locale");
            generator.write(this.locale);
        }

    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    public static class Builder extends AbstractBuilder<Builder>
            implements
            ObjectBuilder<AutoCompleteRequest> {
        private String text;

        private Double latitude;

        private Double longitude;

        private String locale;

        public final Builder text(String value) {
            this.text = value;
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

        @Override
        public AutoCompleteRequest build() {
            _checkSingleUse();
            return new AutoCompleteRequest(this);
        }

    }

    public static final SimpleEndpoint<AutoCompleteRequest, ?> _ENDPOINT = new SimpleEndpoint<>("v3/autocomplete",

            // Request method
            request -> "GET",

            // Request path
            request -> "v3/autocomplete",

            // Request parameters
            request -> {
                Map<String, String> params = new HashMap<>();
                if (request.text() != null) {
                    params.put("text", request.text());
                }
                if (request.latitude() != null) {
                    params.put("text", request.text());
                }
                if (request.longitude() != null) {
                    params.put("text", request.text());
                }
                if (request.locale() != null) {
                    params.put("text", request.text());
                }
                return params;
            },

            SimpleEndpoint.emptyMap(), false, AutoCompleteResponse._DESERIALIZER);
}
