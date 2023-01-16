package io.github.stewseo.clients.yelpfusion.businesses.reviews;

import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.transport.endpoints.SimpleEndpoint;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.yelpfusion.businesses.BusinessesRequestBase;
import jakarta.json.stream.JsonGenerator;

import java.util.HashMap;
import java.util.function.Function;

@JsonpDeserializable
public class BusinessReviewsRequest extends BusinessesRequestBase {

    private BusinessReviewsRequest(Builder builder) {
        super(builder);
    }

    public static BusinessReviewsRequest of(Function<Builder, ObjectBuilder<BusinessReviewsRequest>> fn) {
        return fn.apply(new Builder()).build();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {
        super.serializeInternal(generator, mapper);
    }

//    @Override
//    public String toString() {
//        return JsonpUtils.toString(this);
//    }

    public static class Builder extends AbstractBuilder<Builder>
            implements
            ObjectBuilder<BusinessReviewsRequest> {

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
