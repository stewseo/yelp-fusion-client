package io.github.stewseo.yelp.fusion.client.yelpfusion.business.reviews;


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
public class ReviewsResponse implements JsonpSerializable {
    // ------------------------------ Fields ------------------------------------ //
    private final List<Review> reviews;

    // ------------------------------ Constructor ------------------------------------ //
    private ReviewsResponse(Builder builder) {
        this.reviews = builder.reviews;
    }

    public static ReviewsResponse of(Function<Builder, ObjectBuilder<ReviewsResponse>> fn) {
        return fn.apply(new ReviewsResponse.Builder()).build();
    }

    // ------------------------------ Methods ------------------------------------ //
    public final List<Review> reviews() {return this.reviews;}

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        for (Review item0 : this.reviews) {
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
            ObjectBuilder<ReviewsResponse> {
        private List<Review> reviews = new ArrayList<>();

        public final Builder reviews(List<Review> list) {
            this.reviews = _listAddAll(this.reviews, list);
            return this;
        }

        public final Builder reviews(Review value, Review... values) {
            this.reviews = _listAdd(this.reviews, value, values);
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        public ReviewsResponse build() {
            _checkSingleUse();
            return new ReviewsResponse(this);
        }
    }

    // ------------------------------ Deserializer ------------------------------------ //

    public static final JsonpDeserializer<ReviewsResponse> _DESERIALIZER =
            ObjectBuilderDeserializer.lazy(ReviewsResponse.Builder::new, ReviewsResponse::setupAutoCompleteDeserializer);

    protected static void setupAutoCompleteDeserializer(ObjectDeserializer<ReviewsResponse.Builder> op) {
        op.add(Builder::reviews, JsonpDeserializer.arrayDeserializer(Review._DESERIALIZER), "reviews");
    }
}

