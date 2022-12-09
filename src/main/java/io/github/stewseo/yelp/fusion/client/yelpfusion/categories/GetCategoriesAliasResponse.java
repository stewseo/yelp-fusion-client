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
import io.github.stewseo.yelp.fusion.client.util.ObjectBuilderBase;
import jakarta.json.stream.JsonGenerator;

import java.util.List;
import java.util.function.Function;

@JsonpDeserializable
public class GetCategoriesAliasResponse extends RequestBase implements JsonpSerializable {
    private final Categories category;

    private GetCategoriesAliasResponse(Builder builder) {
        this.category = builder.category;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {

        generator.writeStartObject();
        if(this.category != null) {
            this.category.serialize(generator, mapper);
            System.out.println("categories: " );
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
    public Categories category() {return category;}

    public static class Builder extends RequestBase.AbstractBuilder<GetCategoriesAliasResponse.Builder>
            implements
            ObjectBuilder<GetCategoriesAliasResponse> {

        private Categories  category;

//        public final GetCategoriesAliasResponse.Builder categories(Category categories) {
//            this.categories = _listAddAll(this.categories, categories);;
//            return this;
//        }
//
//        public final GetCategoriesAliasResponse.Builder categories(Categories value, Categories ... values) {
//            this.categories = _listAdd(categories, value, values);
//            return this;
//        }

        public final GetCategoriesAliasResponse.Builder categories(Categories value) {
            this.category = value;
            return this;
        }

        public final GetCategoriesAliasResponse.Builder categories(Function<Categories.Builder, ObjectBuilder<Categories>> fn) {
            return categories(fn.apply(new Categories.Builder()).build());
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
        op.add(Builder::categories, Categories._DESERIALIZER, "category");

    }
}

