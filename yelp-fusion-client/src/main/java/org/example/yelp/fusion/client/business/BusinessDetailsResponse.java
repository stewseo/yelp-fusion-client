package org.example.yelp.fusion.client.business;

import org.example.elasticsearch.client.json.JsonpDeserializer;

import org.example.elasticsearch.client.json.NamedDeserializer;
import org.example.elasticsearch.client.json.ObjectBuilderDeserializer;
import org.example.elasticsearch.client.json.ObjectDeserializer;
import org.example.elasticsearch.client.util.ObjectBuilder;
import org.example.elasticsearch.client.json.JsonpDeserializable;
import org.example.yelp.fusion.client.business.search.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.*;


@JsonpDeserializable
public class BusinessDetailsResponse<TDocument> extends ResponseBody<TDocument> {

    private static final Logger logger = LoggerFactory.getLogger(BusinessDetailsResponse.class);

    public BusinessDetailsResponse(Builder<TDocument> builder) {
        super(builder);
    }

    public static <TDocument> BusinessDetailsResponse<TDocument> of(
            Function<Builder<TDocument>, ObjectBuilder<BusinessDetailsResponse<TDocument>>> fn) {
        return fn.apply(new Builder<>()).build();
    }


    public static class Builder<TDocument> extends ResponseBody.AbstractBuilder<TDocument, Builder<TDocument>>
            implements ObjectBuilder<BusinessDetailsResponse<TDocument>> {

        public Builder() {}

        @Override
        protected Builder<TDocument> self() {
            return this;
        }

        public BusinessDetailsResponse<TDocument> build() {
            _checkSingleUse();
            return new BusinessDetailsResponse<TDocument>(this);
        }

    }

    public static <TDocument> JsonpDeserializer<BusinessDetailsResponse<TDocument>> createBusinessDetailsResponseDeserializer(
            JsonpDeserializer<TDocument> tDocumentDeserializer) {
        return ObjectBuilderDeserializer.createForObject((Supplier<Builder<TDocument>>) Builder::new,
                op -> setupBusinessDetailsResponseDeserializer(op, tDocumentDeserializer));
    }



    public static final JsonpDeserializer<BusinessDetailsResponse<Object>> _DESERIALIZER = JsonpDeserializer
            .lazy(() -> createBusinessDetailsResponseDeserializer(
                    new NamedDeserializer<>("org.example.clients:Deserializer:_global.search.TDocument")));
    public static <TDocument> void setupBusinessDetailsResponseDeserializer(
            ObjectDeserializer<BusinessDetailsResponse.Builder<TDocument>> op,
            JsonpDeserializer<TDocument> tDocumentDeserializer) {
        ResponseBody.setupResponseBodyDeserializer(op, tDocumentDeserializer);

    }

}
