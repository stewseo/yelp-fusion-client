package io.github.stewseo.clients.yelpfusion.businesses.reviews;


import co.elastic.clients.util.ApiTypeHelper;
import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.util.WithJsonObjectBuilderBase;
import io.github.stewseo.clients.yelpfusion.businesses.reviews.BusinessReview;
import jakarta.json.stream.JsonGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@JsonpDeserializable
public class BusinessReviewsResponse implements JsonpSerializable {

    private final List<BusinessReview> reviews;
    private final Integer total;
    private final List<String> possible_languages;

    // ------------------------------ Constructor ------------------------------------ //
    private BusinessReviewsResponse(Builder builder) {
        this.reviews = builder.reviews;
        this.total = builder.total;
        this.possible_languages = ApiTypeHelper.unmodifiable(builder.possible_languages);
    }

    public static BusinessReviewsResponse of(Function<Builder, ObjectBuilder<BusinessReviewsResponse>> fn) {
        return fn.apply(new Builder()).build();
    }

    // ------------------------------ Methods ------------------------------------ //
    public final List<BusinessReview> reviews() {
        return this.reviews;
    }

    public final Integer total() {
        return total;
    }

    public final List<String> possible_languages() {
        return possible_languages;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }


    // ------------------------------ Builder ------------------------------------ //

    public void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        if (ApiTypeHelper.isDefined(this.reviews)) {
            generator.writeKey("reviews");
            generator.writeStartArray();
            for (BusinessReview item0 : reviews) {
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

    // ------------------------------ Deserializer ------------------------------------ //

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    public static class Builder extends WithJsonObjectBuilderBase<Builder>
            implements
            ObjectBuilder<BusinessReviewsResponse> {
        private List<BusinessReview> reviews = new ArrayList<>();
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

    public static final JsonpDeserializer<BusinessReviewsResponse> _DESERIALIZER =
            ObjectBuilderDeserializer.lazy(Builder::new, BusinessReviewsResponse::setupReviewResponseDeserializer);
    protected static void setupReviewResponseDeserializer(ObjectDeserializer<Builder> op) {
        op.add(Builder::reviews, JsonpDeserializer.arrayDeserializer(BusinessReview._DESERIALIZER), "reviews");
        op.add(Builder::possible_languages, JsonpDeserializer.arrayDeserializer(JsonpDeserializer.stringDeserializer()), "possible_languages");
        op.add(Builder::total, JsonpDeserializer.integerDeserializer(), "total");

    }
}

