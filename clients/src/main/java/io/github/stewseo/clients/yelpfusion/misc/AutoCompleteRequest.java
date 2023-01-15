package io.github.stewseo.clients.yelpfusion.misc;


import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.transport.endpoints.SimpleEndpoint;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.yelpfusion.YelpFusionRequestBase;
import jakarta.json.stream.JsonGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@JsonpDeserializable
public class AutoCompleteRequest extends YelpFusionRequestBase {

    private final String text;

    private AutoCompleteRequest(Builder builder) {
        super(builder);
        this.text = builder.text;
    }

    public static AutoCompleteRequest of(Function<Builder, ObjectBuilder<AutoCompleteRequest>> fn) {
        return fn.apply(new Builder()).build();
    }

    public final String text() {
        return text;
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        super.serializeInternal(generator, mapper);

        if (this.text != null) {
            generator.writeKey("text");
            generator.write(this.text);
        }
    }


    public static class Builder extends AbstractBuilder<Builder>
            implements
            ObjectBuilder<AutoCompleteRequest> {
        private String text;

        public final Builder text(String value) {
            this.text = value;
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
                    params.put("latitude", String.valueOf(request.latitude()));
                }
                if (request.longitude() != null) {
                    params.put("longitude", String.valueOf(request.longitude()));
                }
                if (request.locale() != null) {
                    params.put("locale", request.locale());
                }
                return params;
            },

            SimpleEndpoint.emptyMap(), false, AutoCompleteResponse._DESERIALIZER);
}
