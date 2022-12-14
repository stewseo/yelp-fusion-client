package io.github.stewseo.clients.yelpfusion.businesses.details;

import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.transport.endpoints.SimpleEndpoint;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.yelpfusion._types.RequestBase;
import jakarta.json.stream.JsonGenerator;

import java.util.HashMap;
import java.util.function.Function;


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
        if (this.alias != null) {
            generator.writeKey("alias");
            generator.write(this.alias);
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

    public static final SimpleEndpoint<BusinessDetailsRequest, ?> _ENDPOINT = new SimpleEndpoint<>("v3/businesses",

            // Request method
            request -> "GET",

            // Request path
            request -> {
                String endpoint = "v3/businesses" + "/";
                if (request.alias != null) {
                    return endpoint += request.alias();

                } else if (request.id != null) {
                    return endpoint += request.id();
                }
                throw SimpleEndpoint.noPathTemplateFound("path");
            },

            // Request parameters
            request -> new HashMap<>(),

            SimpleEndpoint.emptyMap(), false, BusinessDetailsResponse._DESERIALIZER);


}
