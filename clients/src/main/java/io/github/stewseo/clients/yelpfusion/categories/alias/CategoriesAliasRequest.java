package io.github.stewseo.clients.yelpfusion.categories.alias;

import io.github.stewseo.clients.yelpfusion._types.RequestBase;
import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.transport.endpoints.SimpleEndpoint;
import io.github.stewseo.clients.util.ObjectBuilder;
import jakarta.json.stream.JsonGenerator;

import java.util.HashMap;
import java.util.function.Function;

@JsonpDeserializable
public class CategoriesAliasRequest extends RequestBase implements JsonpSerializable {

    private final String alias;

    private CategoriesAliasRequest(Builder builder) {
        this.alias = builder.alias;
    }

    public static CategoriesAliasRequest of(Function<Builder, ObjectBuilder<CategoriesAliasRequest>> fn) {
        return fn.apply(new Builder()).build();
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {

        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {
        if (this.alias != null) {
            generator.writeKey("alias");
            generator.write(this.alias);
        }
    }


    public final String alias() {
        return alias;
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    public static class Builder extends AbstractBuilder<Builder>
            implements
            ObjectBuilder<CategoriesAliasRequest> {
        private String alias;

        public final Builder alias(String alias) {
            this.alias = alias;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public CategoriesAliasRequest build() {
            _checkSingleUse();
            return new CategoriesAliasRequest(this);
        }
    }

    public static final SimpleEndpoint<CategoriesAliasRequest, ?> _ENDPOINT = new SimpleEndpoint<>("v3/categories",// Request method

            request -> "GET",

            request -> {
                // Request path
                if (request.alias() != null) {
                    return "v3/categories" + "/" + request.alias;
                }
                return null;
            },
            request -> {
                return new HashMap<>();

            }, SimpleEndpoint.emptyMap(), false, CategoriesAliasResponse._DESERIALIZER);

}
