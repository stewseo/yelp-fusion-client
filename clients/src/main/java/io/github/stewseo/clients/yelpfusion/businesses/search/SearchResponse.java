package io.github.stewseo.clients.yelpfusion.businesses.search;


import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.NamedDeserializer;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ObjectBuilder;

import java.util.function.Function;
import java.util.function.Supplier;

@JsonpDeserializable
public class SearchResponse<ResultT> extends ResponseBody<ResultT> {

    private SearchResponse(Builder<ResultT> builder) {
        super(builder);

    }

    public static <ResultT> SearchResponse<ResultT> of(
            Function<Builder<ResultT>, ObjectBuilder<SearchResponse<ResultT>>> fn) {
        return fn.apply(new Builder<>()).build();
    }

    public static class Builder<ResultT> extends AbstractBuilder<ResultT, Builder<ResultT>>
            implements
            ObjectBuilder<SearchResponse<ResultT>> {

        @Override
        protected Builder<ResultT> self() {
            return this;
        }

        public SearchResponse<ResultT> build() {
            _checkSingleUse();

            return new SearchResponse<ResultT>(this);
        }
    }

    public static <ResultT> JsonpDeserializer<SearchResponse<ResultT>> createSearchResponseDeserializer(
            JsonpDeserializer<ResultT> tDocumentDeserializer) {
        return ObjectBuilderDeserializer.createForObject(
                
                (Supplier<Builder<ResultT>>) Builder::new,
                op -> SearchResponse.setupSearchResponseDeserializer(op, tDocumentDeserializer));
    }
    ;

    public static final JsonpDeserializer<SearchResponse<Object>> _DESERIALIZER = JsonpDeserializer
            .lazy(() -> createSearchResponseDeserializer(
                    new NamedDeserializer<>("io.github.stewseo.clients:Deserializer:_global.searchBusinesses._types.ResultT")));

    protected static <ResultT> void setupSearchResponseDeserializer(
            ObjectDeserializer<Builder<ResultT>> op,
            JsonpDeserializer<ResultT> tDocumentDeserializer) {
        ResponseBody.setupResponseBodyDeserializer(op, tDocumentDeserializer);

    }
    
}

