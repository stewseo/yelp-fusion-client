package org.example.yelp.fusion.client.businesses.search;


import org.example.elasticsearch.client.json.*;
import org.example.elasticsearch.client.util.*;

import java.util.function.*;

@JsonpDeserializable
public class BusinessSearchResponse<TDocument>  extends ResponseBody<TDocument> {

    // super constructor, pass the builder to ResponseBody.
    // Pass the type parameter to Response body.
    // BusinessSearchResponse<Restaurants> 
    private BusinessSearchResponse(Builder<TDocument> builder) {
        super(builder);

    }

    public static <TDocument> BusinessSearchResponse<TDocument> of(
            Function<Builder<TDocument>, ObjectBuilder<BusinessSearchResponse<TDocument>>> fn) {
        return fn.apply(new BusinessSearchResponse.Builder<>()).build();
    }

    public static class Builder<TDocument> extends ResponseBody.AbstractBuilder<TDocument, Builder<TDocument>>
            implements
            ObjectBuilder<BusinessSearchResponse<TDocument>> {

        @Override
        protected Builder<TDocument> self() {
            return this;
        }


        public BusinessSearchResponse<TDocument> build() {
            _checkSingleUse();

            return new BusinessSearchResponse<TDocument>(this);
        }
    }
    // Create a JSON deserializer for SearchResponse
    public static <TDocument> JsonpDeserializer<BusinessSearchResponse<TDocument>> createSearchResponseDeserializer(
            JsonpDeserializer<TDocument> tDocumentDeserializer) {
        return ObjectBuilderDeserializer.createForObject((Supplier<Builder<TDocument>>) Builder::new,
                op -> BusinessSearchResponse.setupSearchResponseDeserializer(op, tDocumentDeserializer));
    };

    // Json deserializer for {@link SearchResponse} based on named deserializers
    //  provided by the calling {@code JsonMapper}.
    public static final JsonpDeserializer<BusinessSearchResponse<Object>> _DESERIALIZER = JsonpDeserializer
            .lazy(() -> createSearchResponseDeserializer(
                    new NamedDeserializer<>("co.elastic.clients:Deserializer:_global.search.TDocument")));

    protected static <TDocument> void setupSearchResponseDeserializer(
            ObjectDeserializer<BusinessSearchResponse.Builder<TDocument>> op,
            JsonpDeserializer<TDocument> tDocumentDeserializer) {
        ResponseBody.setupResponseBodyDeserializer(op, tDocumentDeserializer);
    }
}



