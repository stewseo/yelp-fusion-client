package io.github.stewseo.client.yelpfusion.business;

import io.github.stewseo.client.json.JsonpDeserializable;
import io.github.stewseo.client.json.JsonpDeserializer;
import io.github.stewseo.client.json.NamedDeserializer;
import io.github.stewseo.client.json.ObjectBuilderDeserializer;
import io.github.stewseo.client.json.ObjectDeserializer;
import io.github.stewseo.client.util.ObjectBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;
import java.util.function.Supplier;

@JsonpDeserializable
public class SearchResponse<TDocument> extends ResponseBody<TDocument> {

    public static final JsonpDeserializer<SearchResponse<Object>> _DESERIALIZER = JsonpDeserializer
            .lazy(() -> createSearchResponseDeserializer(
                    new NamedDeserializer<>("org.example.clients:Deserializer:_global.search.TDocument")));
    private static final Logger logger = LoggerFactory.getLogger(SearchResponse.class);

    private SearchResponse(Builder<TDocument> builder) {
        super(builder);
    }

    public static <TDocument> SearchResponse<TDocument> of(
            Function<Builder<TDocument>, ObjectBuilder<SearchResponse<TDocument>>> fn) {
        return fn.apply(new Builder<>()).build();
    }

    public static <TDocument> JsonpDeserializer<SearchResponse<TDocument>> createSearchResponseDeserializer(
            JsonpDeserializer<TDocument> tDocumentDeserializer) {
        return ObjectBuilderDeserializer.createForObject((Supplier<Builder<TDocument>>) Builder::new,
                op -> SearchResponse.setupSearchResponseDeserializer(op, tDocumentDeserializer));
    }

    protected static <TDocument> void setupSearchResponseDeserializer(
            ObjectDeserializer<Builder<TDocument>> op,
            JsonpDeserializer<TDocument> tDocumentDeserializer) {
        setupResponseBodyDeserializer(op, tDocumentDeserializer);
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
            return new SearchResponse<>(this);
        }
    }
}



