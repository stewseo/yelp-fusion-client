package io.github.stewseo.client.yelpfusion.categories;

import co.elastic.clients.elasticsearch._types.RequestBase;
import io.github.stewseo.client.json.JsonpDeserializable;
import io.github.stewseo.client.json.JsonpMapper;
import io.github.stewseo.client.json.JsonpSerializable;
import io.github.stewseo.client.json.JsonpUtils;
import io.github.stewseo.client.transport.endpoints.SimpleEndpoint;
import io.github.stewseo.client.util.ObjectBuilder;
import jakarta.json.stream.JsonGenerator;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RestClientBuilder;

import java.util.HashMap;
import java.util.function.Function;

@JsonpDeserializable
public class GetCategoriesRequest extends RequestBase implements JsonpSerializable {
    public static final SimpleEndpoint<GetCategoriesRequest, ?> _ENDPOINT = new SimpleEndpoint<>("v3/categories",
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

            }, SimpleEndpoint.emptyMap(), false, GetCategoriesResponse._DESERIALIZER);
    private final String locale;

    private GetCategoriesRequest(Builder builder) {
        this.locale = builder.locale;
    }



    public static GetCategoriesRequest of(Function<Builder, ObjectBuilder<GetCategoriesRequest>> fn) {
        return fn.apply(new Builder()).build();
    }

    public final String locale() {
        return locale;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        if (this.locale != null) {
            generator.writeKey("locale");
            generator.write(this.locale);
        }
        generator.writeEnd();
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    public static class Builder extends AbstractBuilder<Builder>
            implements
            ObjectBuilder<GetCategoriesRequest> {
        Integer test;
        private String locale;

        public final Builder locale(String value) {
            this.locale = value;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        public GetCategoriesRequest build() {
            _checkSingleUse();
            return new GetCategoriesRequest(this);
        }

        public Builder all(Integer test) {
            this.test = test;
            return this;
        }
    }

}
