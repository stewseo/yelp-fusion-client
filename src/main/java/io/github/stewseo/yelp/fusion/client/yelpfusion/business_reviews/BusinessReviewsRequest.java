package io.github.stewseo.yelp.fusion.client.yelpfusion.business_reviews;

import io.github.stewseo.yelp.fusion.client._types.RequestBase;
import io.github.stewseo.yelp.fusion.client.json.JsonpDeserializable;
import io.github.stewseo.yelp.fusion.client.json.JsonpMapper;
import io.github.stewseo.yelp.fusion.client.json.JsonpSerializable;
import io.github.stewseo.yelp.fusion.client.json.JsonpUtils;
import io.github.stewseo.yelp.fusion.client.transport.endpoints.SimpleEndpoint;
import io.github.stewseo.yelp.fusion.client.util.ObjectBuilder;
import jakarta.json.stream.JsonGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@JsonpDeserializable
public class BusinessReviewsRequest extends RequestBase implements JsonpSerializable {

    private final String id;

    private final String alias;

    private BusinessReviewsRequest(BusinessReviewsRequest.Builder builder) {
        this.id = builder.id;
        this.alias = builder.alias;
    }

    public static BusinessReviewsRequest of(Function<BusinessReviewsRequest.Builder, ObjectBuilder<BusinessReviewsRequest>> fn) {
        return fn.apply(new BusinessReviewsRequest.Builder()).build();
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

    public static class Builder extends RequestBase.AbstractBuilder<BusinessReviewsRequest.Builder>
            implements
            ObjectBuilder<BusinessReviewsRequest> {
        private String id;
        private String alias;

        public final BusinessReviewsRequest.Builder id(String id) {
            this.id = id;
            return this;
        }

        public final BusinessReviewsRequest.Builder alias(String alias) {
            this.alias = alias;
            return this;
        }
        @Override
        protected BusinessReviewsRequest.Builder self() {
            return this;
        }

        @Override
        public BusinessReviewsRequest build() {
            _checkSingleUse();
            return new BusinessReviewsRequest(this);
        }
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    public static final SimpleEndpoint<BusinessReviewsRequest, ?> _ENDPOINT = new SimpleEndpoint<>("v3/businesses",

            // Request method
            request -> "GET",

            // Request path
            request -> {
                if (request.id() != null) {
                    return "v3/businesses/" + request.id() + "/reviews";
                }
                else if (request.alias() != null) {
                    return "v3/businesses/" + request.alias() + "/reviews";
                }
                throw SimpleEndpoint.noPathTemplateFound("path");
            },

            // Request parameters
            request -> {
                return new HashMap<String, String>();
            },

            SimpleEndpoint.emptyMap(), false, BusinessReviewsResponse._DESERIALIZER); // Business Details endpoint accepts a business id path param and returns a Business with additional fields.

}
