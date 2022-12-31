package io.github.stewseo.client.yelpfusion.business.details;

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
public class BusinessDetailsRequest extends RequestBase implements JsonpSerializable {
    private final String id;
    private final String alias;

    private BusinessDetailsRequest(Builder builder) {
        this.id = builder.id;
        this.alias = builder.alias;
    }

    public static BusinessDetailsRequest of(Function<Builder, ObjectBuilder<BusinessDetailsRequest>> fn) {
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
    }

    public static class Builder extends AbstractBuilder<Builder>
            implements
            ObjectBuilder<BusinessDetailsRequest> {
        private String id;
        private String alias;

        public final Builder id(String value) {
            this.id = value;
            return this;
        }
        public final Builder alias(String value) {
            this.alias = value;
            return this;
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
            request -> {
                String endpoint = "v3/businesses" + "/";
                if(request.alias != null) {
                    return endpoint += request.alias();

                }else if(request.id != null) {
                    return endpoint += request.id();
                }
                throw SimpleEndpoint.noPathTemplateFound("path");
            },

            // Request parameters
            request -> new HashMap<>(),

            SimpleEndpoint.emptyMap(), false, BusinessDetailsResponse._DESERIALIZER); // Business Details endpoint accepts a business id path param and returns a Business with additional fields.

}
