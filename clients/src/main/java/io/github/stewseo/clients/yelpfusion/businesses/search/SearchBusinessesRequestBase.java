package io.github.stewseo.clients.yelpfusion.businesses.search;

import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ApiTypeHelper;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.yelpfusion._types.Attribute;
import io.github.stewseo.clients.yelpfusion._types.Category;
import io.github.stewseo.clients.yelpfusion._types.Center;
import io.github.stewseo.clients.yelpfusion.businesses.BusinessesRequestBase;
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Function;

public abstract class SearchBusinessesRequestBase extends BusinessesRequestBase {

    @Nullable
    private final Double latitude;
    @Nullable
    private final Double longitude;
    @Nullable
    private final String sort_by;
    @Nullable
    private final String location;
    @Nullable
    private final String locale;
    @Nullable
    private final Integer price;
    @Nullable
    private final Integer radius;
    @Nullable
    private final Integer limit;
    @Nullable
    private final Integer offset;
    @Nullable
    private final Integer open_at;
    @Nullable
    private final List<String> term;
    @Nullable
    private final Center center;
    @Nullable
    private final Boolean open_now;
    @Nullable
    private final Category categories;
    @Nullable
    private final List<Attribute> attributes;

    protected SearchBusinessesRequestBase(AbstractBuilder<?> builder) {

        super(builder);
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
        this.term = ApiTypeHelper.unmodifiable(builder.term);
        this.location = builder.location;
        this.center = builder.center;
        this.radius = builder.radius;
        this.categories = builder.categories;
        this.locale = builder.locale;
        this.limit = builder.limit;
        this.offset = builder.offset;
        this.sort_by = builder.sort_by;
        this.price = builder.price;
        this.open_now = builder.open_now;
        this.open_at = builder.open_at;
        this.attributes = ApiTypeHelper.unmodifiable(builder.attributes);
    }

    @Nullable
    public final Double latitude() {
        return this.latitude;
    }

    @Nullable
    public final Double longitude() {
        return this.longitude;
    }


    public final String locale() {
        return this.locale;
    }
    public final String sort_by() {
        return this.sort_by;
    }

    public final Integer limit() {
        return this.limit;
    }

    public final Integer offset() {
        return this.offset;
    }
    public final Boolean open_now() {
        return this.open_now;
    }
    public final Integer open_at() {
        return this.open_at;
    }

    public final Integer price() {
        return this.price;
    }

    public final Integer radius() {
        return this.radius;
    }


    public final List<String> term() {
        return this.term;
    }

    public final String location() {
        return this.location;
    }

    public final Category categories() {
        return this.categories;
    }

    public final Center center() {
        return this.center;
    }

    public final List<Attribute> attributes() {
        return this.attributes;
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        super.serializeInternal(generator, mapper);

        if (ApiTypeHelper.isDefined(this.term)) {
            generator.writeKey("term");
            generator.writeStartArray();
            for (String item0 : this.term) {
                generator.write(item0);
            }
            generator.writeEnd();
        }

        if (this.location != null) {
            generator.writeKey("location");
            generator.write(this.location);
        }

        if (this.latitude != null) {
            generator.writeKey("latitude");
            generator.write(this.latitude);
        }

        if (this.longitude != null) {
            generator.writeKey("longitude");
            generator.write(this.longitude);
        }

        if (this.categories != null) {
            generator.writeKey("categories");
            this.categories.serialize(generator, mapper);
        }

        if (this.radius != null) {
            generator.writeKey("radius");
            generator.write(this.radius);
        }

        if (this.offset != null) {
            generator.writeKey("offset");
            generator.write(this.offset);
        }

        if (this.sort_by != null) {
            generator.writeKey("sort_by");
            generator.write(this.sort_by);
        }

        if (this.limit != null) {
            generator.writeKey("limit");
            generator.write(this.limit);
        }

        if (this.open_at != null) {
            generator.writeKey("open_at");
            generator.write(this.open_at);
        }

        if (this.price != null) {
            generator.writeKey("price");
            generator.write(this.price);
        }

        if (ApiTypeHelper.isDefined(this.attributes)) {
            generator.writeKey("attributes");
            generator.writeStartArray();
            for (Attribute item0 : this.attributes) {
                item0.serialize(generator, mapper);
            }
            generator.writeEnd();

        }
    }


   protected abstract static class AbstractBuilder<BuilderT extends AbstractBuilder<BuilderT>>
            extends
            BusinessesRequestBase.AbstractBuilder<BuilderT> {
       @Nullable
       private List<String> term;

       @Nullable
       private String location;

       @Nullable
       private Center center;

       @Nullable
       private Double latitude;

       @Nullable
       private Double longitude;

       @Nullable
       private Integer radius;

       @Nullable
       private Category categories;

       @Nullable
       private String locale;

       @Nullable
       private Integer limit;

       @Nullable
       private Integer offset;

       @Nullable
       private Integer price;

       @Nullable
       private String sort_by;

       @Nullable
       private Boolean open_now;

       @Nullable
       private Integer open_at;

       @Nullable
       private List<Attribute> attributes;

       public final BuilderT latitude(Double value) {
           this.latitude = value;
           return self();
       }

       public final BuilderT longitude(Double value) {
           this.longitude = value;
           return self();
       }

       public final BuilderT price(Integer price) {
           this.price = price;
           return self();
       }

       public final BuilderT sort_by(@Nullable String sort_by) {
           this.sort_by = sort_by;
           return self();
       }

       public final BuilderT locale(@Nullable String locale) {
           this.locale = locale;
           return self();
       }

       public final BuilderT radius(@Nullable Integer radius) {
           this.radius = radius;
           return self();
       }

       public final BuilderT offset(@Nullable Integer offset) {
           this.offset = offset;
           return self();
       }

       public final BuilderT limit(@Nullable Integer limit) {
           this.limit = limit;
           return self();
       }
       public final BuilderT open_now(@Nullable Boolean open_now) {
           this.open_now = open_now;
           return self();
       }
       public final BuilderT open_at(@Nullable Integer open_at) {
           this.open_at = open_at;
           return self();
       }

       public final BuilderT term(@Nullable List<String> value) {
           this.term = _listAddAll(this.term, value);
           return self();
       }

       public final BuilderT term(@Nullable String value, String... values) {
           this.term = _listAdd(this.term, value, values);
           return self();
       }

       public final BuilderT categories(Category value) {
           this.categories = value;
           return self();
       }

       public final BuilderT categories(Function<Category.Builder, ObjectBuilder<Category>> fn) {
           return this.categories(fn.apply(new Category.Builder()).build());
       }

       public final BuilderT center(Center value) {
           this.center = value;
           return self();
       }

       public final BuilderT center(Function<Center.Builder, ObjectBuilder<Center>> fn) {
           return this.center(fn.apply(new Center.Builder()).build());
       }

       public final BuilderT location(@Nullable String value) {
           this.location = value;
           return self();
       }

       public final BuilderT attributes(@Nullable List<Attribute> list) {
           this.attributes = _listAddAll(this.attributes, list);
           return self();
       }

       public final BuilderT attributes(@Nullable Attribute value, Attribute... values) {
           this.attributes = _listAdd(this.attributes, value, values);
           return self();
       }

    }

    protected static <BuilderT extends AbstractBuilder<BuilderT>> void setupSearchBusinessesRequestBaseDeserializer(
            ObjectDeserializer<BuilderT> op) {

        setupBusinessRequestBaseDeserializer(op);
        op.add(AbstractBuilder::latitude, JsonpDeserializer.doubleDeserializer(), "latitude");
        op.add(AbstractBuilder::longitude, JsonpDeserializer.doubleDeserializer(), "longitude");
        op.add(AbstractBuilder::sort_by, JsonpDeserializer.stringDeserializer(), "sort_by");
        op.add(AbstractBuilder::price, JsonpDeserializer.integerDeserializer(), "price");
        op.add(AbstractBuilder::radius, JsonpDeserializer.integerDeserializer(), "radius");
        op.add(AbstractBuilder::limit, JsonpDeserializer.integerDeserializer(), "limit");
        op.add(AbstractBuilder::offset, JsonpDeserializer.integerDeserializer(), "offset");
        op.add(AbstractBuilder::open_at, JsonpDeserializer.integerDeserializer(), "open_at");
        op.add(AbstractBuilder::open_now, JsonpDeserializer.booleanDeserializer(), "open_now");

        op.add(AbstractBuilder::term, JsonpDeserializer.arrayDeserializer(JsonpDeserializer.stringDeserializer()), "term");
        op.add(AbstractBuilder::location, JsonpDeserializer.stringDeserializer(), "location");
        op.add(AbstractBuilder::categories, Category._DESERIALIZER, "categories");
        op.add(AbstractBuilder::attributes, JsonpDeserializer.arrayDeserializer(Attribute._DESERIALIZER), "attributes");
    }

}
