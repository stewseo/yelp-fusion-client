package io.github.yelp.fusion.client.business;

import io.github.elasticsearch.client._types.RequestBase;
import io.github.elasticsearch.client.json.JsonpMapper;
import io.github.elasticsearch.client.json.JsonpSerializable;
import io.github.elasticsearch.client.json.JsonpUtils;
import io.github.elasticsearch.client.util.ObjectBuilder;
import jakarta.json.stream.*;

import io.github.elasticsearch.client.transport.endpoints.SimpleEndpoint;


import java.util.*;
import java.util.function.*;

public class BusinessDetailsRequest extends RequestBase implements JsonpSerializable {
    private final String id;

    private BusinessDetailsRequest(Builder builder) {
        this.id = builder.id;
    }

    public static BusinessDetailsRequest of(Function<Builder, ObjectBuilder<BusinessDetailsRequest>> fn) {
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
    public static class Builder extends RequestBase.AbstractBuilder<BusinessDetailsRequest.Builder>
            implements
            ObjectBuilder<BusinessDetailsRequest> {
        private String id;

        public final Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder() {
        }

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

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    public static final SimpleEndpoint<BusinessDetailsRequest, ?> _ENDPOINT = new SimpleEndpoint<>("v3/businesses",

            // Request method
            request -> "GET",

            // Request path
            request -> "v3/businesses" + "/" + request.id(),

            // Request parameters
            request -> new HashMap<>(),
            SimpleEndpoint.emptyMap(), false, BusinessDetailsResponse._DESERIALIZER);


}
