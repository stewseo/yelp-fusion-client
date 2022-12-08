package io.github.stewseo.yelp.fusion.client.yelpfusion.categories;

import co.elastic.clients.elasticsearch._types.RequestBase;
import co.elastic.clients.elasticsearch.ingest.GetPipelineRequest;
import io.github.stewseo.yelp.fusion.client.json.JsonpDeserializable;
import io.github.stewseo.yelp.fusion.client.json.JsonpMapper;
import io.github.stewseo.yelp.fusion.client.json.JsonpSerializable;
import io.github.stewseo.yelp.fusion.client.json.JsonpUtils;
import io.github.stewseo.yelp.fusion.client.transport.endpoints.SimpleEndpoint;
import io.github.stewseo.yelp.fusion.client.util.ObjectBuilder;
import io.github.stewseo.yelp.fusion.client.util.ObjectBuilderBase;
import jakarta.json.stream.JsonGenerator;

import java.util.HashMap;
import java.util.function.Function;

@JsonpDeserializable
public class GetCategoriesRequest extends RequestBase implements JsonpSerializable {
    private final String locale;

    private Integer test;
    private GetCategoriesRequest(Builder builder) {
        this.locale = builder.locale;
    }
    public String locale() {
        return locale;
    }


    public static GetCategoriesRequest of(Function<Builder, ObjectBuilder<GetCategoriesRequest>> fn) {
        return fn.apply(new GetCategoriesRequest.Builder()).build();
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


    public static class Builder extends RequestBase.AbstractBuilder<GetCategoriesRequest.Builder>
            implements
            ObjectBuilder<GetCategoriesRequest> {
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
        Integer test;
        public Builder all(Integer test) {
            this.test = test;
            return this;
        }


    }

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


}
