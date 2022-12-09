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

@JsonpDeserializable
public class BusinessReview implements JsonpSerializable {
    private final String id;
    private final String text;
    private final String url;
    private final Double rating;
    private final String time_created;
    private final User user;
    // getters
    public String id() {
        return id;
    }

    public String text() {
        return text;
    }

    public String url() {
        return url;
    }

    public Double rating() {
        return rating;
    }

    public String time_created() {
        return time_created;
    }


    public User user() {
        return user;
    }

    // constructor
    private BusinessReview(Builder builder) {
        this.id = builder.id;
        this.text = builder.text;
        this.url = builder.url;
        this.rating = builder.rating;
        this.time_created = builder.time_created;
        this.user = builder.user;
    }
    // serialize
    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        if (this.id != null) {
            generator.writeKey("id");
            generator.write(this.id);
        }
        if (this.text != null) {
            generator.writeKey("text");
            generator.write(this.text);
        }
        if (this.url != null) {
            generator.writeKey("url");
            generator.write(this.url);
        }
        if (this.rating != null) {
            generator.writeKey("rating");
            generator.write(this.rating);
        }
        if (this.time_created != null) {
            generator.writeKey("time_created");
            generator.write(this.time_created);
        }
        if (this.user != null) {
            generator.writeKey("user");
            user.serialize(generator, mapper);
        }
    }
    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    // builder
    public static class Builder extends WithJsonObjectBuilderBase<BusinessReview.Builder>
            implements ObjectBuilder<BusinessReview> {

        private String id;
        private String text;
        private String url;
        private Double rating;
        private String time_created;
        private User user;
        // setters

        public Builder id(String id) {
            this.id = id;
            return self();
        }

        public Builder text(String text) {
            this.text = text;
            return self();
        }

        public Builder url(String url) {
            this.url = url;
            return self();
        }

        public Builder rating(Double rating) {
            this.rating = rating;
            return self();
        }

        public Builder time_created(String time_created) {
            this.time_created = time_created;
            return self();
        }

        public Builder user(User value) {
            this.user = value;
            return self();
        }
        @Override
        protected BusinessReview.Builder self() {
            return this;
        }

        @Override
        public BusinessReview build() {
            _checkSingleUse();
            return new BusinessReview(this);
        }
    }

    public static final JsonpDeserializer<BusinessReview> _DESERIALIZER =
            ObjectBuilderDeserializer.lazy(BusinessReview.Builder::new, BusinessReview::setupBusinessReviewDeserializer);

    protected static void setupBusinessReviewDeserializer(ObjectDeserializer<Builder> op) {
        op.add(BusinessReview.Builder::id, JsonpDeserializer.stringDeserializer(), "id");
        op.add(Builder::text, JsonpDeserializer.stringDeserializer(), "text");
        op.add(Builder::url, JsonpDeserializer.stringDeserializer(), "url");
        op.add(Builder::rating, JsonpDeserializer.doubleDeserializer(), "rating");
        op.add(Builder::time_created, JsonpDeserializer.stringDeserializer(), "time_created");
        op.add(Builder::user, User._DESERIALIZER, "user");
    }



    // deserializer
}
