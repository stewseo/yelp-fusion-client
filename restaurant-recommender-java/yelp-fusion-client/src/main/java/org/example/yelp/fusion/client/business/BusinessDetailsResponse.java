package org.example.yelp.fusion.client.business;


import org.example.elasticsearch.client._types.*;
import org.example.elasticsearch.client.json.*;
import org.example.elasticsearch.client.util.*;
import org.example.yelp.fusion.client.business.search.*;

import java.util.*;
import java.util.function.*;

//@JsonpDeserializable
public class BusinessDetailsResponse<TDocument> extends ResponseBody<TDocument> {

    public int total;

    public Business business;

    List<Business> businesses;

    public BusinessDetailsResponse(Builder<TDocument> builder) {
        super(builder);
    }


    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public static <TDocument> BusinessDetailsResponse<TDocument> of(
            Function<Builder<TDocument>, ObjectBuilder<BusinessDetailsResponse<TDocument>>> fn) {
        return fn.apply(new Builder<>()).build();
    }

    public static class Builder<TDocument> extends ResponseBody.AbstractBuilder<TDocument, Builder<TDocument>>
            implements ObjectBuilder<BusinessDetailsResponse<TDocument>> {
        private String id;

        public Builder<TDocument> id(String value) {
            this.id = value;
            return this;
        }

        public Builder(){}

        @Override
        protected Builder<TDocument> self() {
            return this;
        }

        public BusinessDetailsResponse<TDocument> build() {
            _checkSingleUse();
            return new BusinessDetailsResponse<TDocument>(this);
        }

        public static <TDocument> JsonpDeserializer<BusinessDetailsResponse<TDocument>> createBusinessDetailsResponseDeserializer(
                JsonpDeserializer<TDocument> tDocumentDeserializer) {
            return ObjectBuilderDeserializer.createForObject((Supplier<Builder<TDocument>>) Builder::new,
                    op -> setupBusinessDetailsResponseDeserializer(op, tDocumentDeserializer));
        }

        ;

        public static final JsonpDeserializer<BusinessDetailsResponse<Object>> _DESERIALIZER = JsonpDeserializer
                .lazy(() -> createBusinessDetailsResponseDeserializer(
                        new NamedDeserializer<>("co.elastic.clients:Deserializer:_global.search.TDocument")));

        protected static <TDocument> void setupBusinessDetailsResponseDeserializer(
                ObjectDeserializer<BusinessDetailsResponse.Builder<TDocument>> op,
                JsonpDeserializer<TDocument> tDocumentDeserializer) {
            ResponseBody.setupResponseBodyDeserializer(op, tDocumentDeserializer);

        }
    }

}
