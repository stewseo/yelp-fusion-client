package io.github.stewseo.clients.yelpfusion;


import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ApiTypeHelper;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.util.WithJsonObjectBuilderBase;
import io.github.stewseo.clients.yelpfusion._types.Category;
import io.github.stewseo.clients.yelpfusion._types.Center;
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Function;

@JsonpDeserializable
public abstract class ResultBase implements JsonpSerializable {

    private final String id;
    private final String name;
    private final String alias;
    private final String phone;
    private final String price;
    private final Center center;
    private final List<Category> categories;

    protected ResultBase(AbstractBuilder<?> builder) {

        this.price = builder.price;
        this.id = builder.id;
        this.alias = builder.alias;
        this.name = builder.name;
        this.phone = builder.phone;
        this.center = builder.center;
        this.categories = ApiTypeHelper.unmodifiable(builder.categories);

    }

    public final String id() {
        return id;
    }

    public final String name() {
        return name;
    }

    public final String alias() {
        return alias;
    }

    public final String phone() {
        return phone;
    }

    public final String price() {
        return price;
    }

    public final Center center() {
        return center;
    }

    public final List<Category> categories() {
        return categories;
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
        if (this.alias != null) {
            generator.writeKey("alias");
            generator.write(this.alias);
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
        if (this.center != null) {
            generator.writeKey("center");
            center.serialize(generator, mapper);
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
            WithJsonObjectBuilderBase<BuilderT> {

        private String id;
        private String name;
        private String alias;
        private String phone;
        private String price;
        private Center center;
        private List<Category> categories;

        public final BuilderT id(String value) {
            this.id = value;
            return self();
        }

        public final BuilderT name(String value) {
            this.name = value;
            return self();
        }

        public final BuilderT alias(String value) {
            this.alias = value;
            return self();
        }

        public final BuilderT phone(String value) {
            this.phone = value;
            return self();
        }

        public final BuilderT price(String value) {
            this.price = value;
            return self();
        }

        public final BuilderT center(Center value) {
            this.center = value;
            return self();
        }
        @Nullable
        public final BuilderT center(Function<Center.Builder, ObjectBuilder<Center>> fn) {
            return center(fn.apply(new Center.Builder()).build());
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

        protected abstract BuilderT self();

    }

    protected static <BuilderT extends AbstractBuilder<BuilderT>> void setupResultBaseDeserializer(
            ObjectDeserializer<BuilderT> op) {

        op.add(AbstractBuilder::id, JsonpDeserializer.stringDeserializer(), "id");
        op.add(AbstractBuilder::name, JsonpDeserializer.stringDeserializer(), "name");
        op.add(AbstractBuilder::alias, JsonpDeserializer.stringDeserializer(), "alias");
        op.add(AbstractBuilder::phone, JsonpDeserializer.stringDeserializer(), "phone");
        op.add(AbstractBuilder::price, JsonpDeserializer.stringDeserializer(), "price");
        op.add(AbstractBuilder::center, Center._DESERIALIZER, "center");
        op.add(AbstractBuilder::categories, JsonpDeserializer.arrayDeserializer(Category._DESERIALIZER), "categories");

    }

}
