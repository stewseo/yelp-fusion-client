package io.github.stewseo.clients.yelpfusion.businesses.reviews;

import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.transport.endpoints.SimpleEndpoint;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.yelpfusion._types.RequestBase;
import jakarta.json.stream.JsonGenerator;

import java.util.HashMap;
import java.util.function.Function;

@JsonpDeserializable
public class BusinessReviewsRequest extends RequestBase implements JsonpSerializable {

    private final String id;

    private final String alias;

    private BusinessReviewsRequest(Builder builder) {
        this.id = builder.id;
        this.alias = builder.alias;
    }

    public static BusinessReviewsRequest of(Function<Builder, ObjectBuilder<BusinessReviewsRequest>> fn) {
        return fn.apply(new Builder()).build();
    }

    public final String id() {
        return id;
    }

    public final String alias() {
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
            ObjectBuilder<BusinessReviewsRequest> {
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
        public BusinessReviewsRequest build() {
            _checkSingleUse();
            return new BusinessReviewsRequest(this);
        }
    }

    public static final SimpleEndpoint<BusinessReviewsRequest, ?> _ENDPOINT = new SimpleEndpoint<>("v3/businesses",

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
                return new HashMap<>();
            },
            // Business Details endpoint accepts a business id path param and returns a Business with additional fields.

            SimpleEndpoint.emptyMap(), false, BusinessReviewsResponse._DESERIALIZER);

}
