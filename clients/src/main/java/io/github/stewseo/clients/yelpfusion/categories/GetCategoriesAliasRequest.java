package io.github.stewseo.clients.yelpfusion.categories;

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
public class GetCategoriesAliasRequest extends RequestBase implements JsonpSerializable {

    private final String alias;

    private GetCategoriesAliasRequest(Builder builder) {
        this.alias = builder.alias;
    }

    public static GetCategoriesAliasRequest of(Function<Builder, ObjectBuilder<GetCategoriesAliasRequest>> fn) {
        return fn.apply(new Builder()).build();
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        if (this.alias != null) {
            generator.writeKey("alias");
            generator.write(this.alias);
        }
        generator.writeEnd();
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
            ObjectBuilder<GetCategoriesAliasRequest> {
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
        public GetCategoriesAliasRequest build() {
            _checkSingleUse();
            return new GetCategoriesAliasRequest(this);
        }
    }

    public static final SimpleEndpoint<GetCategoriesAliasRequest, ?> _ENDPOINT = new SimpleEndpoint<>("v3/categories",// Request method

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

            }, SimpleEndpoint.emptyMap(), false, GetCategoriesAliasResponse._DESERIALIZER);

}
