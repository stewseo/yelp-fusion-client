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

import java.util.List;
import java.util.function.Function;

@JsonpDeserializable
public class GetCategoriesResponse extends RequestBase implements JsonpSerializable {
    private List<Category> categories;

    private GetCategoriesResponse(Builder builder) {
        this.categories = builder.categories;
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

    public static GetCategoriesResponse of(Function<Builder, ObjectBuilder<GetCategoriesResponse>> fn) {
        return fn.apply(new GetCategoriesResponse.Builder()).build();
    }
    public List<Category> categories() {return categories;}

    public static class Builder extends RequestBase.AbstractBuilder<GetCategoriesResponse.Builder>
            implements
            ObjectBuilder<GetCategoriesResponse> {

        private List<Category>  categories;

        public final GetCategoriesResponse.Builder categories(List<Category> categories) {
            this.categories = _listAddAll(this.categories, categories);;
            return this;
        }

        public final GetCategoriesResponse.Builder categories(Category value, Category... values) {
            this.categories = _listAdd(categories, value, values);
            return this;
        }

        public final GetCategoriesResponse.Builder categories(Function<Category.Builder, ObjectBuilder<Category>> fn) {
            return categories(fn.apply(new Category.Builder()).build());
        }

        @Override
        protected GetCategoriesResponse.Builder self() {
            return this;
        }

        @Override
        public GetCategoriesResponse build() {
            _checkSingleUse();
            return new GetCategoriesResponse(this);
        }
    }

        public static final JsonpDeserializer<GetCategoriesResponse> _DESERIALIZER = ObjectBuilderDeserializer.lazy(GetCategoriesResponse.Builder::new,
                GetCategoriesResponse::setGetCategoriesResponseDeserializer);
        protected static void setGetCategoriesResponseDeserializer(ObjectDeserializer<GetCategoriesResponse.Builder> op) {
            op.add(Builder::categories, JsonpDeserializer.arrayDeserializer(Category._DESERIALIZER), "categories");
        }


}
