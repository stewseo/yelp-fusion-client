package io.github.stewseo.client.yelpfusion.misc;


import io.github.stewseo.client._types.RequestBase;
import io.github.stewseo.client.json.JsonpDeserializable;
import io.github.stewseo.client.json.JsonpMapper;
import io.github.stewseo.client.json.JsonpSerializable;
import io.github.stewseo.client.json.JsonpUtils;
import io.github.stewseo.client.util.ObjectBuilder;
import io.github.stewseo.client.transport.endpoints.SimpleEndpoint;
import jakarta.json.stream.*;


import java.util.*;
import java.util.function.*;

@JsonpDeserializable
public class AutoCompleteRequest extends RequestBase implements JsonpSerializable {
    private final String text;

    private AutoCompleteRequest(Builder builder) {
        this.text = builder.text;
    }

    public static AutoCompleteRequest of(Function<Builder, ObjectBuilder<AutoCompleteRequest>> fn) {
        return fn.apply(new Builder()).build();
    }

    public String text() {
        return text;
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
    }

    public static class Builder extends RequestBase.AbstractBuilder<AutoCompleteRequest.Builder>
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
    @Override
    public String toString() {
        return JsonpUtils.toString(this);
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
                return params;
            },

            SimpleEndpoint.emptyMap(), false, AutoCompleteResponse._DESERIALIZER); // Business Details endpoint accepts a business id path param and returns a Business with additional fields.

}
