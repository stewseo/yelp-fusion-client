package io.github.stewseo.yelp.fusion.client.yelpfusion.business_reviews;


import io.github.stewseo.yelp.fusion.client.json.JsonpDeserializable;
import io.github.stewseo.yelp.fusion.client.json.JsonpDeserializer;
import io.github.stewseo.yelp.fusion.client.json.JsonpMapper;
import io.github.stewseo.yelp.fusion.client.json.JsonpSerializable;
import io.github.stewseo.yelp.fusion.client.json.JsonpUtils;
import io.github.stewseo.yelp.fusion.client.json.ObjectBuilderDeserializer;
import io.github.stewseo.yelp.fusion.client.json.ObjectDeserializer;
import io.github.stewseo.yelp.fusion.client.util.ObjectBuilder;
import io.github.stewseo.yelp.fusion.client.util.WithJsonObjectBuilderBase;
import jakarta.json.stream.JsonGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@JsonpDeserializable
public class BusinessReviewsResponse implements JsonpSerializable {
    // ------------------------------ Fields ------------------------------------ //
    private final List<BusinessReview> reviews;

    // ------------------------------ Constructor ------------------------------------ //
    private BusinessReviewsResponse(Builder builder) {
        this.reviews = builder.reviews;
    }

    public static BusinessReviewsResponse of(Function<Builder, ObjectBuilder<BusinessReviewsResponse>> fn) {
        return fn.apply(new BusinessReviewsResponse.Builder()).build();
    }

    // ------------------------------ Methods ------------------------------------ //
    public final List<BusinessReview> reviews() {return this.reviews;}

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        for (BusinessReview item0 : this.reviews) {
            item0.serialize(generator, mapper);
        }
        generator.writeEnd();

    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }


    // ------------------------------ Builder ------------------------------------ //

    public static class Builder extends WithJsonObjectBuilderBase<Builder>
            implements
            ObjectBuilder<BusinessReviewsResponse> {
        private List<BusinessReview> reviews = new ArrayList<>();

        public final Builder reviews(List<BusinessReview> list) {
            this.reviews = _listAddAll(this.reviews, list);
            return this;
        }

        public final Builder reviews(BusinessReview value, BusinessReview... values) {
            this.reviews = _listAdd(this.reviews, value, values);
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        public BusinessReviewsResponse build() {
            _checkSingleUse();
            return new BusinessReviewsResponse(this);
        }
    }

    // ------------------------------ Deserializer ------------------------------------ //

    public static final JsonpDeserializer<BusinessReviewsResponse> _DESERIALIZER =
            ObjectBuilderDeserializer.lazy(BusinessReviewsResponse.Builder::new, BusinessReviewsResponse::setupAutoCompleteDeserializer);

    protected static void setupAutoCompleteDeserializer(ObjectDeserializer<BusinessReviewsResponse.Builder> op) {
        op.add(Builder::reviews, JsonpDeserializer.arrayDeserializer(BusinessReview._DESERIALIZER), "reviews");
    }
}

