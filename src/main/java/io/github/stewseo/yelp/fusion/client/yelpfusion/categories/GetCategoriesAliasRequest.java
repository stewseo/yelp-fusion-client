package io.github.stewseo.yelp.fusion.client.yelpfusion.categories;

import io.github.stewseo.yelp.fusion.client._types.RequestBase;
import io.github.stewseo.yelp.fusion.client.json.JsonpDeserializable;
import io.github.stewseo.yelp.fusion.client.json.JsonpMapper;
import io.github.stewseo.yelp.fusion.client.json.JsonpSerializable;
import io.github.stewseo.yelp.fusion.client.json.JsonpUtils;
import io.github.stewseo.yelp.fusion.client.transport.endpoints.SimpleEndpoint;
import io.github.stewseo.yelp.fusion.client.util.ObjectBuilder;
import jakarta.json.stream.JsonGenerator;

import java.util.HashMap;
import java.util.function.Function;

@JsonpDeserializable
public class GetCategoriesAliasRequest extends RequestBase implements JsonpSerializable {

    private final String alias;
    private GetCategoriesAliasRequest(Builder builder) {
        this.alias = builder.alias;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        if (this.alias != null) {
            generator.writeKey("alias");
            generator.write(this.alias);
        }
        generator.writeEnd();
    }

    public static GetCategoriesAliasRequest of(Function<GetCategoriesAliasRequest.Builder, ObjectBuilder<GetCategoriesAliasRequest>> fn) {
        return fn.apply(new GetCategoriesAliasRequest.Builder()).build();
    }
    public String alias() {
        return alias;
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }


    public static class Builder extends RequestBase.AbstractBuilder<GetCategoriesAliasRequest.Builder>
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
