package org.example.yelp.fusion.client.business.search;

import org.example.elasticsearch.client.json.*;
import org.example.elasticsearch.client.util.*;
import org.example.lowlevel.restclient.*;

import java.util.function.*;

@JsonpDeserializable
public class BusinessSearchResponse<TDocument> extends ResponseBody<TDocument> {

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
    public static <TDocument> JsonpDeserializer<BusinessSearchResponse<TDocument>> createSearchResponseDeserializer(
            JsonpDeserializer<TDocument> tDocumentDeserializer) {
        return ObjectBuilderDeserializer.createForObject((Supplier<Builder<TDocument>>) Builder::new,
                op -> BusinessSearchResponse.setupSearchResponseDeserializer(op, tDocumentDeserializer));
    };


    public static final JsonpDeserializer<BusinessSearchResponse<Object>> _DESERIALIZER = JsonpDeserializer
            .lazy(() -> createSearchResponseDeserializer(
                    new NamedDeserializer<>("com.example.clients:Deserializer:_global.search.TDocument")));

    protected static <TDocument> void setupSearchResponseDeserializer(
            ObjectDeserializer<Builder<TDocument>> op,
            JsonpDeserializer<TDocument> tDocumentDeserializer) {
        PrintUtils.green("BusinessSearchResponse setupSearchResponseDeserializer");
        ResponseBody.setupResponseBodyDeserializer(op, tDocumentDeserializer);
    }
}



