package io.github.stewseo.clients.yelpfusion.businesses.reviews;

import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.yelpfusion.ResultBase;
import io.github.stewseo.clients.yelpfusion._types.User;
import jakarta.json.stream.JsonGenerator;

import java.util.function.Function;

@JsonpDeserializable
public class BusinessReview extends ResultBase {

    private final String text;

    private final String time_created;

    private final User user;

    // constructor
    private BusinessReview(Builder builder) {
        super(builder);
        this.text = builder.text;
        this.time_created = builder.time_created;
        this.user = builder.user;
    }

    public static BusinessReview of(Function<Builder, ObjectBuilder<BusinessReview>> fn) {
        return fn.apply(new Builder()).build();
    }

    // getters

    public final String text() {
        return text;
    }

    public final String time_created() {
        return time_created;
    }

    public final User user() {
        return user;
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        super.serializeInternal(generator, mapper);

        if (this.text != null) {
            generator.writeKey("text");
            generator.write(this.text);
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

    public static class Builder extends AbstractBuilder<BusinessReview.Builder>
            implements
            ObjectBuilder<BusinessReview> {

        private String text;
        private String time_created;
        private User user;
        // setters

        public final Builder text(String text) {
            this.text = text;
            return self();
        }

        public final Builder time_created(String time_created) {
            this.time_created = time_created;
            return self();
        }

        public final Builder user(User value) {
            this.user = value;
            return self();
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public BusinessReview build() {
            _checkSingleUse();
            return new BusinessReview(this);
        }
    }

    public static final JsonpDeserializer<BusinessReview> _DESERIALIZER =
            ObjectBuilderDeserializer.lazy(Builder::new, BusinessReview::setupBusinessReviewDeserializer);
    // deserializer

    protected static void setupBusinessReviewDeserializer(ObjectDeserializer<Builder> op) {
        setupResultBaseDeserializer(op);
        op.add(Builder::text, JsonpDeserializer.stringDeserializer(), "text");
        op.add(Builder::time_created, JsonpDeserializer.stringDeserializer(), "time_created");
        op.add(Builder::user, User._DESERIALIZER, "user");
    }
}
