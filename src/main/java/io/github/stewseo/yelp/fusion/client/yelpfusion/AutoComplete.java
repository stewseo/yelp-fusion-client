package io.github.stewseo.yelp.fusion.client.yelpfusion;


import io.github.stewseo.yelp.fusion.client.transport.endpoints.SimpleEndpoint;
import io.github.stewseo.yelp.fusion.client._types.RequestBase;
import io.github.stewseo.yelp.fusion.client.json.JsonpDeserializable;
import io.github.stewseo.yelp.fusion.client.json.JsonpMapper;
import io.github.stewseo.yelp.fusion.client.json.JsonpSerializable;
import io.github.stewseo.yelp.fusion.client.json.JsonpUtils;
import io.github.stewseo.yelp.fusion.client.util.ObjectBuilder;
import jakarta.json.stream.*;


import java.util.*;
import java.util.function.*;

@JsonpDeserializable
public class AutoCompleteRequest extends RequestBase implements JsonpSerializable {
    private final String id;

    private AutoCompleteRequest(Builder builder) {
        this.id = builder.id;
    }

    public static AutoCompleteRequest of(Function<Builder, ObjectBuilder<AutoCompleteRequest>> fn) {
        return fn.apply(new Builder()).build();
    }

    public String id() {
        return id;
    }


    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        if (this.id != null) {
            generator.writeKey("id");
            generator.write(this.id);
        }
    }

    public static class Builder extends RequestBase.AbstractBuilder<AutoCompleteRequest.Builder>
            implements
            ObjectBuilder<AutoCompleteRequest> {
        private String id;

        public final Builder id(String value) {
            this.id = value;
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
            request -> "v3/autocomplete" + "/" + request.id(),

            // Request parameters
            request -> new HashMap<>(),

            SimpleEndpoint.emptyMap(), false,
            AutoCompleteResponse._DESERIALIZER); // Business Details endpoint accepts a business id path param and returns a Business with additional fields.

}
