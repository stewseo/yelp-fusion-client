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
public class SearchResponse<TDocument> extends ResponseBody<TDocument> {

    private SearchResponse(Builder<TDocument> builder) {
        super(builder);

    }

    public static <TDocument> SearchResponse<TDocument> of(
            Function<Builder<TDocument>, ObjectBuilder<SearchResponse<TDocument>>> fn) {
        return fn.apply(new Builder<>()).build();
    }

    public static class Builder<TDocument> extends AbstractBuilder<TDocument, Builder<TDocument>>
            implements
            ObjectBuilder<SearchResponse<TDocument>> {

        @Override
        protected Builder<TDocument> self() {
            return this;
        }

        public SearchResponse<TDocument> build() {
            _checkSingleUse();

            return new SearchResponse<TDocument>(this);
        }
    }

    public static <TDocument> JsonpDeserializer<SearchResponse<TDocument>> createSearchResponseDeserializer(
            JsonpDeserializer<TDocument> tDocumentDeserializer) {
        return ObjectBuilderDeserializer.createForObject((Supplier<Builder<TDocument>>) Builder::new,
                op -> SearchResponse.setupSearchResponseDeserializer(op, tDocumentDeserializer));
    }
    ;

    public static final JsonpDeserializer<SearchResponse<Object>> _DESERIALIZER = JsonpDeserializer
            .lazy(() -> createSearchResponseDeserializer(
                    new NamedDeserializer<>("io.github.stewseo.clients:Deserializer:_global.search.TDocument")));

    protected static <TDocument> void setupSearchResponseDeserializer(
            ObjectDeserializer<Builder<TDocument>> op,
            JsonpDeserializer<TDocument> tDocumentDeserializer) {
        ResponseBody.setupResponseBodyDeserializer(op, tDocumentDeserializer);

    }
}

