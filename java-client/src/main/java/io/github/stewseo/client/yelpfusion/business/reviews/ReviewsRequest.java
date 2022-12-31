package io.github.stewseo.client.yelpfusion.business.reviews;

import io.github.stewseo.client._types.RequestBase;
import io.github.stewseo.client.json.JsonpDeserializable;
import io.github.stewseo.client.json.JsonpMapper;
import io.github.stewseo.client.json.JsonpSerializable;
import io.github.stewseo.client.json.JsonpUtils;
import io.github.stewseo.client.util.ObjectBuilder;
import io.github.stewseo.client.transport.endpoints.SimpleEndpoint;
import jakarta.json.stream.JsonGenerator;

import java.util.HashMap;
import java.util.function.Function;

@JsonpDeserializable
public class ReviewsRequest extends RequestBase implements JsonpSerializable {
    public static final SimpleEndpoint<ReviewsRequest, ?> _ENDPOINT = new SimpleEndpoint<>("v3/businesses",

            // Request method
            request -> "GET",

            // Request path
            request -> {
                if (request.id() != null) {
                    return "v3/businesses/" + request.id() + "/reviews";
                } else if (request.alias() != null) {
                    return "v3/businesses/" + request.alias() + "/reviews";
                }
                throw SimpleEndpoint.noPathTemplateFound("path");
            },

            // Request parameters
            request -> {
                return new HashMap<String, String>();
            },

            SimpleEndpoint.emptyMap(), false, ReviewsResponse._DESERIALIZER); // Business Details endpoint accepts a business id path param and returns a Business with additional fields.
    private final String id;
    private final String alias;

    private ReviewsRequest(Builder builder) {
        this.id = builder.id;
        this.alias = builder.alias;
    }

    public static ReviewsRequest of(Function<Builder, ObjectBuilder<ReviewsRequest>> fn) {
        return fn.apply(new Builder()).build();
    }

    public String id() {
        return id;
    }

    public String alias() {
        return alias;
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
        if (this.alias != null) {
            generator.writeKey("alias");
            generator.write(this.alias);
        }
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    public static class Builder extends AbstractBuilder<Builder>
            implements
            ObjectBuilder<ReviewsRequest> {
        private String id;
        private String alias;

        public final Builder id(String id) {
            this.id = id;
            return this;
        }

        public final Builder alias(String alias) {
            this.alias = alias;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public ReviewsRequest build() {
            _checkSingleUse();
            return new ReviewsRequest(this);
        }
    }

}
