package io.github.stewseo.client.yelpfusion.business.reviews;


import co.elastic.clients.util.ApiTypeHelper;
import io.github.stewseo.client.json.JsonpDeserializable;
import io.github.stewseo.client.json.JsonpDeserializer;
import io.github.stewseo.client.json.JsonpMapper;
import io.github.stewseo.client.json.JsonpSerializable;
import io.github.stewseo.client.json.JsonpUtils;
import io.github.stewseo.client.json.ObjectBuilderDeserializer;
import io.github.stewseo.client.json.ObjectDeserializer;
import io.github.stewseo.client.util.ObjectBuilder;
import io.github.stewseo.client.util.WithJsonObjectBuilderBase;
import jakarta.json.stream.JsonGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@JsonpDeserializable
public class ReviewsResponse implements JsonpSerializable {
    // ------------------------------ Fields ------------------------------------ //
    private final List<Review> reviews;

    private final Integer total;

    private final List<String> possible_languages;

    // ------------------------------ Constructor ------------------------------------ //
    private ReviewsResponse(Builder builder) {
        this.reviews = builder.reviews;
        this.total = builder.total;
        this.possible_languages = builder.possible_languages;
    }

    public static ReviewsResponse of(Function<Builder, ObjectBuilder<ReviewsResponse>> fn) {
        return fn.apply(new ReviewsResponse.Builder()).build();
    }

    // ------------------------------ Methods ------------------------------------ //
    public final List<Review> reviews() {return this.reviews;}

    public Integer total() {
        return total;
    }

    public List<String> possible_languages() {
        return possible_languages;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }
    public void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        if (ApiTypeHelper.isDefined(this.reviews)) {
            generator.writeKey("reviews");
            generator.writeStartArray();
            for (Review item0 : reviews) {
                item0.serialize(generator, mapper);
            }
            generator.writeEnd();
        }

        if (ApiTypeHelper.isDefined(this.possible_languages)) {
            generator.writeKey("possible_languages");
            generator.writeStartArray();
            for (String item0 : possible_languages) {
                generator.write(item0);
            }
            generator.writeEnd();
        }
        if (this.total != null) {
            generator.writeKey("total");
            generator.write(this.total);

        }


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
        private Integer total;
        private List<String> possible_languages;

        public final Builder total(Integer value) {
            this.total = value;
            return this;
        }
        public final Builder possible_languages(List<String> list) {
            this.possible_languages = _listAddAll(this.possible_languages, list);
            return this;
        }
        public final Builder possible_languages(String value, String... values) {
            this.possible_languages = _listAdd(this.possible_languages, value, values);
            return this;
        }
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

    protected static void setupAutoCompleteDeserializer(ObjectDeserializer<Builder> op) {
        op.add(Builder::reviews, JsonpDeserializer.arrayDeserializer(Review._DESERIALIZER), "reviews");
        op.add(Builder::possible_languages, JsonpDeserializer.arrayDeserializer(JsonpDeserializer.stringDeserializer()), "possible_languages");
        op.add(Builder::total, JsonpDeserializer.integerDeserializer(), "total");

    }
}

