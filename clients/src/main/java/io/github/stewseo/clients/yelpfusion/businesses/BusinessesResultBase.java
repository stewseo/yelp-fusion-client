package io.github.stewseo.clients.yelpfusion.businesses;


import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ApiTypeHelper;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.yelpfusion.ResultBase;
import io.github.stewseo.clients.yelpfusion._types.Category;
import io.github.stewseo.clients.yelpfusion._types.Coordinate;
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Function;

@JsonpDeserializable
public abstract class BusinessesResultBase extends ResultBase {

    @Nullable
    private final String name;
    @Nullable
    private final String phone;
    @Nullable
    private final String price;
    @Nullable
    private final Integer review_count;
    @Nullable
    private final Double rating;
    @Nullable
    private final Coordinate center;
    @Nullable
    private final List<String> transactions;
    @Nullable
    private final List<Category> categories;


    protected BusinessesResultBase(AbstractBuilder<?> builder) {
        super(builder);
        this.price = builder.price;
        this.name = builder.name;
        this.phone = builder.phone;
        this.review_count = builder.review_count;
        this.rating = builder.rating;
        this.center = builder.center;
        this.transactions = ApiTypeHelper.unmodifiable(builder.transactions);
        this.categories = ApiTypeHelper.unmodifiable(builder.categories);

    }

    @Nullable
    public final String name() {
        return name;
    }

    @Nullable
    public final String phone() {
        return phone;
    }
    @Nullable
    public final String price() {
        return price;
    }
    @Nullable
    public final Integer review_count() {
        return review_count;
    }
    @Nullable
    public final Double rating() {
        return rating;
    }
    @Nullable
    public final Coordinate center() {
        return center;
    }
    @Nullable
    public final List<String> transactions() {
        return transactions;
    }
    @Nullable
    public final List<Category> categories() {
        return categories;
    }


    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {
        super.serializeInternal(generator, mapper);
        if (this.rating != null) {
            generator.writeKey("rating");
            generator.write(this.rating);
        }
        if (this.name != null) {
            generator.writeKey("name");
            generator.write(this.name);
        }
        if (this.phone != null) {
            generator.writeKey("phone");
            generator.write(this.phone);
        }
        if (this.price != null) {
            generator.writeKey("price");
            generator.write(this.price);
        }
        if (this.review_count != null) {
            generator.writeKey("review_count");
            generator.write(this.review_count);
        }
        if (this.center != null) {
            generator.writeKey("center");
            center.serialize(generator, mapper);
        }

        if (ApiTypeHelper.isDefined(this.transactions)) {
            generator.writeKey("transactions");
            generator.writeStartArray();
            for (String item0 : this.transactions) {
                generator.write(item0);
            }
            generator.writeEnd();
        }

        if (ApiTypeHelper.isDefined(this.categories)) {
            generator.writeKey("categories");
            generator.writeStartArray();
            for (Category item0 : this.categories) {
                item0.serialize(generator, mapper);
            }
            generator.writeEnd();
        }
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    protected abstract static class AbstractBuilder<BuilderT extends AbstractBuilder<BuilderT>>
            extends
            ResultBase.AbstractBuilder<BuilderT> {
        private String name;
        private String phone;
        private String price;
        private Double rating;
        private Integer review_count;
        private Coordinate center;
        private List<Category> categories;
        private List<String> transactions;


        public final BuilderT name(String value) {
            this.name = value;
            return self();
        }

        public final BuilderT phone(@Nullable String value) {
            this.phone = value;
            return self();
        }

        public final BuilderT price(@Nullable String value) {
            this.price = value;
            return self();
        }

        public final BuilderT rating(@Nullable Double value) {
            this.rating = value;
            return self();
        }
        public final BuilderT review_count(@Nullable Integer value) {
            this.review_count = value;
            return self();
        }

        public final BuilderT center(Coordinate value) {
            this.center = value;
            return self();
        }
        public final BuilderT center(Function<Coordinate.Builder, ObjectBuilder<Coordinate>> fn) {
            return center(fn.apply(new Coordinate.Builder()).build());
        }

        public final BuilderT transactions(List<String> value) {
            this.transactions = value;
            return self();
        }
        public final BuilderT transactions(String value, String... values) {
            this.transactions = _listAdd(this.transactions, value, values);
            return self();
        }

        public final BuilderT categories(List<Category> list) {
            this.categories = _listAddAll(this.categories, list);
            return self();
        }
        public final BuilderT categories(Category value, Category... values) {
            this.categories = _listAdd(this.categories, value, values);
            return self();
        }
        public final BuilderT categories(Function<Category.Builder, ObjectBuilder<Category>> fn) {
            return categories(fn.apply(new Category.Builder()).build());
        }

    }

    protected static <BuilderT extends AbstractBuilder<BuilderT>> void setupBusinessResultBaseDeserializer(
            ObjectDeserializer<BuilderT> op) {

        setupResultBaseDeserializer(op);

        op.add(AbstractBuilder::name, JsonpDeserializer.stringDeserializer(), "name");
        op.add(AbstractBuilder::phone, JsonpDeserializer.stringDeserializer(), "phone");
        op.add(AbstractBuilder::price, JsonpDeserializer.stringDeserializer(), "price");
        op.add(AbstractBuilder::rating, JsonpDeserializer.doubleDeserializer(), "rating");
        op.add(AbstractBuilder::review_count, JsonpDeserializer.integerDeserializer(), "review_count");
        op.add(AbstractBuilder::center, Coordinate._DESERIALIZER, "center");
        op.add(AbstractBuilder::transactions, JsonpDeserializer.arrayDeserializer(JsonpDeserializer.stringDeserializer()), "transactions");
        op.add(AbstractBuilder::categories, JsonpDeserializer.arrayDeserializer(Category._DESERIALIZER), "categories");

    }

}
