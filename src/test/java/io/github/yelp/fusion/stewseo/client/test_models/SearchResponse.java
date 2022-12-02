package io.github.yelp.fusion.stewseo.client.test_models;

import io.github.stewseo.yelp.fusion.client.json.*;
import io.github.stewseo.yelp.fusion.client.util.ObjectBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;
import java.util.function.Supplier;

@JsonpDeserializable
public class SearchResponse<TDocument> extends ResponseBody<TDocument> {

    private static final Logger logger = LoggerFactory.getLogger(SearchResponse.class);

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
            return new SearchResponse<>(this);
        }
    }
    public static <TDocument> JsonpDeserializer<SearchResponse<TDocument>> createSearchResponseDeserializer(
            JsonpDeserializer<TDocument> tDocumentDeserializer) {
        return ObjectBuilderDeserializer.createForObject((Supplier<Builder<TDocument>>) Builder::new,
                op -> SearchResponse.setupSearchResponseDeserializer(op, tDocumentDeserializer));
    }

    public static final JsonpDeserializer<SearchResponse<Object>> _DESERIALIZER = JsonpDeserializer
            .lazy(() -> createSearchResponseDeserializer(
                    new NamedDeserializer<>("org.example.clients:Deserializer:_global.search.TDocument")));

    protected static <TDocument> void setupSearchResponseDeserializer(
            ObjectDeserializer<Builder<TDocument>> op,
            JsonpDeserializer<TDocument> tDocumentDeserializer) {
        setupResponseBodyDeserializer(op, tDocumentDeserializer);
    }
}



