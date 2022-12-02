package io.github.yelp.fusion.client.yelpfusion.business;


import io.github.yelp.fusion.client.json.*;
import io.github.yelp.fusion.client.util.ObjectBuilder;
import io.github.yelp.fusion.client.util.ObjectBuilderBase;
import jakarta.json.stream.*;


import java.util.function.*;


@JsonpDeserializable
public class Categories implements JsonpSerializable {
    private final String title;
    private final String alias;


    private Categories(Builder builder) {
        this.alias = builder.alias;
        this.title = builder.title;
    }

    public String alias() {
        return alias;
    }

    private String title() {
        return title;
    }

    public static Categories of(Function<Builder, ObjectBuilder<Categories>> fn) {
        return fn.apply(new Builder()).build();
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {
        if (this.alias != null) {
            generator.writeKey("alias");
            generator.write(this.alias);
        }

        if (this.title != null) {
            generator.writeKey("title");
            generator.write(this.title);
        }
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    public static class Builder extends ObjectBuilderBase implements ObjectBuilder<Categories> {
        private String alias;
        private String title;

        public Builder() {
        }

        public final Builder alias(String value) {
            this.alias = value;
            return this;
        }

        public final Builder title(String title) {
            this.title = title;
            return this;
        }

        public Categories build() {
            _checkSingleUse();
            return new Categories(this);
        }
    }

    public static final JsonpDeserializer<Categories> _DESERIALIZER =
            ObjectBuilderDeserializer.lazy(Categories.Builder::new,
                    Categories::setupCategoriesDeserializer);

    protected static void setupCategoriesDeserializer(ObjectDeserializer<Builder> op) {
        op.add(Builder::alias, JsonpDeserializer.stringDeserializer(), "alias");
        op.add(Builder::title, JsonpDeserializer.stringDeserializer(), "title");
    }
}
