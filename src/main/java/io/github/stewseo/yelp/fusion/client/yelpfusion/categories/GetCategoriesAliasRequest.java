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
    private final String locale;

    private GetCategoriesAliasRequest(Builder builder) {
        this.alias = builder.alias;
        this.locale = builder.locale;
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
        if (this.locale != null) {
            generator.writeKey("locale");
            generator.write(this.locale);
        }
    }

    public static GetCategoriesAliasRequest of(Function<GetCategoriesAliasRequest.Builder, ObjectBuilder<GetCategoriesAliasRequest>> fn) {
        return fn.apply(new GetCategoriesAliasRequest.Builder()).build();
    }

    public String locale() {
        return locale;
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
        private String locale;

        public final GetCategoriesAliasRequest.Builder alias(String alias) {
            this.alias = alias;
            return this;
        }

        public final GetCategoriesAliasRequest.Builder locale(String locale) {
            this.locale = locale;
            return this;
        }

        @Override
        protected GetCategoriesAliasRequest.Builder self() {
            return this;
        }

        @Override
        public GetCategoriesAliasRequest build() {
            _checkSingleUse();
            return new GetCategoriesAliasRequest(this);
        }

        public static final SimpleEndpoint<GetCategoriesAliasRequest, ?> _ENDPOINT = new SimpleEndpoint<>("v3/businesses",

                // Request method
                request -> "GET",

                // Request path
                request -> "v3/categories",

                // Request parameters
                request -> new HashMap<>(),

                SimpleEndpoint.emptyMap(), false, GetCategoriesAliasResponse._DESERIALIZER); // Business Details endpoint accepts a business id path param and returns a Business with additional fields.

    }
}
