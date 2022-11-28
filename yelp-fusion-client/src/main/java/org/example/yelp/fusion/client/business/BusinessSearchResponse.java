package org.example.yelp.fusion.client.business;

import org.example.elasticsearch.client.json.*;
import org.example.elasticsearch.client.util.ObjectBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;
import java.util.function.Supplier;

@JsonpDeserializable
public class BusinessSearchResponse<TDocument> extends ResponseBody<TDocument> {

    private static final Logger logger = LoggerFactory.getLogger(BusinessSearchResponse.class);

    private BusinessSearchResponse(Builder<TDocument> builder) {
        super(builder);
    }

    public static <TDocument> BusinessSearchResponse<TDocument> of(
            Function<Builder<TDocument>, ObjectBuilder<BusinessSearchResponse<TDocument>>> fn) {
        return fn.apply(new Builder<>()).build();
    }

    public static class Builder<TDocument> extends AbstractBuilder<TDocument, Builder<TDocument>>
            implements
            ObjectBuilder<BusinessSearchResponse<TDocument>> {
        @Override
        protected Builder<TDocument> self() {
            return this;
        }


        public BusinessSearchResponse<TDocument> build() {
            _checkSingleUse();
            return new BusinessSearchResponse<>(this);
        }
    }
    public static <TDocument> JsonpDeserializer<BusinessSearchResponse<TDocument>> createSearchResponseDeserializer(
            JsonpDeserializer<TDocument> tDocumentDeserializer) {
        return ObjectBuilderDeserializer.createForObject((Supplier<Builder<TDocument>>) Builder::new,
                op -> BusinessSearchResponse.setupSearchResponseDeserializer(op, tDocumentDeserializer));
    }


    public static final JsonpDeserializer<BusinessSearchResponse<Object>> _DESERIALIZER = JsonpDeserializer
            .lazy(() -> createSearchResponseDeserializer(
                    new NamedDeserializer<>("org.example.clients:Deserializer:_global.search.TDocument")));

    protected static <TDocument> void setupSearchResponseDeserializer(
            ObjectDeserializer<Builder<TDocument>> op,
            JsonpDeserializer<TDocument> tDocumentDeserializer) {

        ResponseBody.setupResponseBodyDeserializer(op, tDocumentDeserializer);
    }
}



