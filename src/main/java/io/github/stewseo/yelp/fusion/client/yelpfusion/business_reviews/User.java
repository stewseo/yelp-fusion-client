package io.github.stewseo.yelp.fusion.client.yelpfusion.business_reviews;

import io.github.stewseo.yelp.fusion.client.json.JsonData;
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

import java.util.Map;

@JsonpDeserializable
public class User implements JsonpSerializable {
    private final String id;
    private final String profile_url;
    private final String image_url;
    private final String name;

    private User(Builder builder) {
        this.id = builder.id;
        this.profile_url = builder.profile_url;
        this.image_url = builder.image_url;
        this.name = builder.name;

    }

    public String id() {
        return id;
    }

    public String profile_url() {
        return profile_url;
    }

    public String image_url() {
        return image_url;
    }

    public String name() {
        return name;
    }

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
        if (this.profile_url != null) {
            generator.writeKey("profile_url");
            generator.write(this.profile_url);
        }
        if (this.image_url != null) {
            generator.writeKey("image_url");
            generator.write(this.image_url);
        }
        if (this.name != null) {
            generator.writeKey("name");
            generator.write(this.name);
        }
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    public static class Builder extends WithJsonObjectBuilderBase<User.Builder>
            implements ObjectBuilder<User> {

        private String id;
        private String profile_url;
        private String image_url;
        private String name;
        // setters

        public User.Builder id(String id) {
            this.id = id;
            return self();
        }
        public User.Builder profile_url(String profile_url) {
            this.profile_url = profile_url;
            return self();
        }
        public User.Builder image_url(String image_url) {
            this.image_url = image_url;
            return self();
        }
        public User.Builder name(String name) {
            this.name = name;
            return self();
        }
        protected User.Builder self() {
            return this;
        }

        @Override
        public User build() {
            _checkSingleUse();
            return new User(this);
        }

}

    public static final JsonpDeserializer<User> _DESERIALIZER =
            ObjectBuilderDeserializer.lazy(User.Builder::new, User::setupUserDeserializer);

    protected static void setupUserDeserializer(ObjectDeserializer<User.Builder> op) {
        op.add(Builder::id, JsonpDeserializer.stringDeserializer(), "id");
        op.add(Builder::name, JsonpDeserializer.stringDeserializer(), "name");
        op.add(Builder::profile_url, JsonpDeserializer.stringDeserializer(), "profile_url");
        op.add(Builder::image_url, JsonpDeserializer.stringDeserializer(), "image_url");
    }

}
