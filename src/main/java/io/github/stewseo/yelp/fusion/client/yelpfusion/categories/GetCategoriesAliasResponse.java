package io.github.stewseo.yelp.fusion.client.yelpfusion.categories;

import io.github.stewseo.yelp.fusion.client._types.RequestBase;
import io.github.stewseo.yelp.fusion.client.json.JsonpDeserializable;
import io.github.stewseo.yelp.fusion.client.json.JsonpDeserializer;
import io.github.stewseo.yelp.fusion.client.json.JsonpMapper;
import io.github.stewseo.yelp.fusion.client.json.JsonpSerializable;
import io.github.stewseo.yelp.fusion.client.json.JsonpUtils;
import io.github.stewseo.yelp.fusion.client.json.ObjectBuilderDeserializer;
import io.github.stewseo.yelp.fusion.client.json.ObjectDeserializer;
import io.github.stewseo.yelp.fusion.client.util.ObjectBuilder;
import jakarta.json.stream.JsonGenerator;

import java.util.function.Function;

@JsonpDeserializable
public class GetCategoriesAliasResponse extends RequestBase implements JsonpSerializable {
    private final Category category;

    private GetCategoriesAliasResponse(Builder builder) {
        this.category = builder.category;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeKey("category");
        generator.writeStartObject();
        if(this.category != null) {
            this.category.serialize(generator, mapper);
        }
        generator.writeEnd();
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    public static GetCategoriesAliasResponse of(Function<Builder, ObjectBuilder<GetCategoriesAliasResponse>> fn) {
        return fn.apply(new GetCategoriesAliasResponse.Builder()).build();
    }
    public Category category() {return category;}

    public static class Builder extends RequestBase.AbstractBuilder<GetCategoriesAliasResponse.Builder>
            implements
            ObjectBuilder<GetCategoriesAliasResponse> {

        private Category category;

        public final GetCategoriesAliasResponse.Builder categories(Category value) {
            this.category = value;
            return this;
        }

        public final GetCategoriesAliasResponse.Builder categories(Function<Category.Builder, ObjectBuilder<Category>> fn) {
            return categories(fn.apply(new Category.Builder()).build());
        }

        @Override
        protected GetCategoriesAliasResponse.Builder self() {
            return this;
        }

        @Override
        public GetCategoriesAliasResponse build() {
            _checkSingleUse();
            return new GetCategoriesAliasResponse(this);
        }
    }

    public static final JsonpDeserializer<GetCategoriesAliasResponse> _DESERIALIZER = ObjectBuilderDeserializer.lazy(GetCategoriesAliasResponse.Builder::new,
            GetCategoriesAliasResponse::setCategoriesAliasResponseDeserializer);

    protected static void setCategoriesAliasResponseDeserializer(ObjectDeserializer<GetCategoriesAliasResponse.Builder> op) {
        op.add(Builder::categories, Category._DESERIALIZER, "category");
    }
}

