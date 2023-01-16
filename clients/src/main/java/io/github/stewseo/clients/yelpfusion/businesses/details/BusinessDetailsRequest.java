package io.github.stewseo.clients.yelpfusion.businesses.details;

import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.transport.endpoints.SimpleEndpoint;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.yelpfusion.businesses.BusinessesRequestBase;
import jakarta.json.stream.JsonGenerator;

import java.util.HashMap;
import java.util.function.Function;


public class BusinessDetailsRequest extends BusinessesRequestBase {

    private BusinessDetailsRequest(Builder builder) {
        super(builder);
    }

    public static BusinessDetailsRequest of(Function<Builder, ObjectBuilder<BusinessDetailsRequest>> fn) {
        return fn.apply(new Builder()).build();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {
        super.serializeInternal(generator, mapper);
    }

    public static class Builder extends AbstractBuilder<Builder>
            implements
            ObjectBuilder<BusinessDetailsRequest> {

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public BusinessDetailsRequest build() {
            _checkSingleUse();
            return new BusinessDetailsRequest(this);
        }

    }



    public static final SimpleEndpoint<BusinessDetailsRequest, ?> _ENDPOINT = new SimpleEndpoint<>("v3/businesses",

            // Request method
            request -> "GET",

            // Request path
            request -> {

                if (request.alias() != null) {
                    return "v3/businesses/" + request.alias();
                }
                else if (request.id() != null) {
                    return "v3/businesses/" + request.id();
                }
                throw SimpleEndpoint.noPathTemplateFound("path");
            },

            // Request parameters
            request -> new HashMap<>(),

            SimpleEndpoint.emptyMap(), false, BusinessDetailsResponse._DESERIALIZER);


}
