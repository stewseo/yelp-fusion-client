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
import io.github.stewseo.clients.util.ObjectBuilderBase;
import jakarta.json.stream.JsonGenerator;

import java.util.List;
import java.util.function.Function;

@JsonpDeserializable
public class GetCategoriesResponse extends RequestBase implements JsonpSerializable {
    public static final JsonpDeserializer<GetCategoriesResponse> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
            GetCategoriesResponse::setGetCategoriesResponseDeserializer);
    private List<Category> categories;

    private GetCategoriesResponse(Builder builder) {
        this.categories = builder.categories;
    }

    public static GetCategoriesResponse of(Function<Builder, ObjectBuilder<GetCategoriesResponse>> fn) {
        return fn.apply(new Builder()).build();
    }

    protected static void setGetCategoriesResponseDeserializer(ObjectDeserializer<Builder> op) {
        op.add(Builder::categories, JsonpDeserializer.arrayDeserializer(Category._DESERIALIZER), "categories");
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeKey("categories");
        generator.writeStartArray();
        for (Category item0 : this.categories) {
            item0.serialize(generator, mapper);
        }
        generator.writeEnd();
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    public List<Category> categories() {
        return categories;
    }

    public static class Builder extends AbstractBuilder<Builder>
            implements
            ObjectBuilder<GetCategoriesResponse> {

        private List<Category> categories;

        public final Builder categories(List<Category> categories) {
            this.categories = ObjectBuilderBase._listAddAll(this.categories, categories);
            ;
            return this;
        }

        public final Builder categories(Category value, Category... values) {
            this.categories = ObjectBuilderBase._listAdd(categories, value, values);
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
        public GetCategoriesResponse build() {
            _checkSingleUse();
            return new GetCategoriesResponse(this);
        }
    }


}
