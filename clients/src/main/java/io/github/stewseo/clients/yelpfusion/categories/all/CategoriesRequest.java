package io.github.stewseo.clients.yelpfusion.categories.all;

import co.elastic.clients.elasticsearch._types.RequestBase;
import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.transport.endpoints.SimpleEndpoint;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.yelpfusion.categories.all.CategoriesResponse;
import jakarta.json.stream.JsonGenerator;

import java.util.HashMap;
import java.util.function.Function;

@JsonpDeserializable
public class CategoriesRequest extends RequestBase implements JsonpSerializable {

    private final String locale;

    private CategoriesRequest(Builder builder) {
        this.locale = builder.locale;
    }

    public static CategoriesRequest of(Function<Builder, ObjectBuilder<CategoriesRequest>> fn) {
        return fn.apply(new Builder()).build();
    }

    public final String locale() {
        return locale;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    public void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {
        if (this.locale != null) {
            generator.writeKey("locale");
            generator.write(this.locale);
        }
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    public static class Builder extends AbstractBuilder<Builder>
            implements
            ObjectBuilder<CategoriesRequest> {

        private String locale;

        public final Builder locale(String value) {
            this.locale = value;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        public CategoriesRequest build() {
            _checkSingleUse();
            return new CategoriesRequest(this);
        }
    }

    public static final SimpleEndpoint<CategoriesRequest, ?> _ENDPOINT = new SimpleEndpoint<>("v3/categories",
            // Request method
            request -> "GET",

            request -> "v3/categories",

            // Request path
            request -> {
                HashMap<String, String> params = new HashMap<>();
                if (request.locale() != null) {
                    params.put("locale", request.locale());
                }
                return params;

            }, SimpleEndpoint.emptyMap(), false, CategoriesResponse._DESERIALIZER);

}
