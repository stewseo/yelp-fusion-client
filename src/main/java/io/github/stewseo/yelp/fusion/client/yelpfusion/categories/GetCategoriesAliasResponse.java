package io.github.stewseo.yelp.fusion.client.yelpfusion.categories;

import io.github.stewseo.yelp.fusion.client.json.JsonpDeserializable;
import io.github.stewseo.yelp.fusion.client.json.JsonpDeserializer;
import io.github.stewseo.yelp.fusion.client.json.JsonpMapper;
import io.github.stewseo.yelp.fusion.client.json.JsonpSerializable;
import io.github.stewseo.yelp.fusion.client.json.JsonpUtils;
import io.github.stewseo.yelp.fusion.client.json.ObjectBuilderDeserializer;
import io.github.stewseo.yelp.fusion.client.json.ObjectDeserializer;
import io.github.stewseo.yelp.fusion.client.util.ObjectBuilder;
import io.github.stewseo.yelp.fusion.client.util.ObjectBuilderBase;
import jakarta.json.stream.JsonGenerator;

import java.util.function.Function;
@JsonpDeserializable
public class GetCategoriesAliasResponse implements JsonpSerializable {

    private final String title;
    private final String alias;

    private GetCategoriesAliasResponse(Builder builder) {
        this.alias = builder.alias;
        this.title = builder.title;
    }

    public String alias() {
        return alias;
    }

    private String title() {
        return title;
    }

    public static GetCategoriesAliasResponse of(Function<GetCategoriesAliasResponse.Builder, ObjectBuilder<GetCategoriesAliasResponse>> fn) {
        return fn.apply(new GetCategoriesAliasResponse.Builder()).build();
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

    public static class Builder extends ObjectBuilderBase implements ObjectBuilder<GetCategoriesAliasResponse> {
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

        public GetCategoriesAliasResponse build() {
            _checkSingleUse();
            return new GetCategoriesAliasResponse(this);
        }
    }

    public static final JsonpDeserializer<GetCategoriesAliasResponse> _DESERIALIZER =
            ObjectBuilderDeserializer.lazy(GetCategoriesAliasResponse.Builder::new,
                    GetCategoriesAliasResponse::setupCategoriesDeserializer);

    protected static void setupCategoriesDeserializer(ObjectDeserializer<GetCategoriesAliasResponse.Builder> op) {
        op.add(Builder::alias, JsonpDeserializer.stringDeserializer(), "alias");
        op.add(Builder::title, JsonpDeserializer.stringDeserializer(), "title");
    }
}

