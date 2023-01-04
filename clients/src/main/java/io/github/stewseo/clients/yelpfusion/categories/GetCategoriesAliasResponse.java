package io.github.stewseo.clients.yelpfusion.categories;

import io.github.stewseo.clients.yelpfusion._types.Category;
import io.github.stewseo.clients.yelpfusion._types.RequestBase;
import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ObjectBuilder;
import jakarta.json.stream.JsonGenerator;

import java.util.function.Function;

@JsonpDeserializable
public class GetCategoriesAliasResponse extends RequestBase implements JsonpSerializable {

    private final Category category;

    private GetCategoriesAliasResponse(Builder builder) {
        this.category = builder.category;
    }

    public static GetCategoriesAliasResponse of(Function<Builder, ObjectBuilder<GetCategoriesAliasResponse>> fn) {
        return fn.apply(new Builder()).build();
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeKey("category");
        generator.writeStartObject();
        if (this.category != null) {
            this.category.serialize(generator, mapper);
        }
        generator.writeEnd();
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    public final Category category() {
        return category;
    }

    public static class Builder extends AbstractBuilder<Builder>
            implements
            ObjectBuilder<GetCategoriesAliasResponse> {

        private Category category;

        public final Builder categories(Category value) {
            this.category = value;
            return this;
        }

        public final Builder categories(Function<Category.Builder, ObjectBuilder<Category>> fn) {
            return categories(fn.apply(new Category.Builder()).build());
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public GetCategoriesAliasResponse build() {
            _checkSingleUse();
            return new GetCategoriesAliasResponse(this);
        }

    }

    public static final JsonpDeserializer<GetCategoriesAliasResponse> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
            GetCategoriesAliasResponse::setCategoriesAliasResponseDeserializer);

    protected static void setCategoriesAliasResponseDeserializer(ObjectDeserializer<Builder> op) {
        op.add(Builder::categories, Category._DESERIALIZER, "category");
    }
}

