package io.github.stewseo.client.yelpfusion.business.search;

import io.github.stewseo.client.yelpfusion.categories.Category;
import io.github.stewseo.client.json.JsonpDeserializable;
import io.github.stewseo.client.json.JsonpDeserializer;
import io.github.stewseo.client.json.JsonpMapper;
import io.github.stewseo.client.json.JsonpSerializable;
import io.github.stewseo.client.json.JsonpUtils;
import io.github.stewseo.client.json.ObjectBuilderDeserializer;
import io.github.stewseo.client.json.ObjectDeserializer;
import io.github.stewseo.client.util.ApiTypeHelper;
import io.github.stewseo.client.util.ObjectBuilder;
import io.github.stewseo.client.util.WithJsonObjectBuilderBase;
import io.github.stewseo.client.yelpfusion.business.Coordinates;
import io.github.stewseo.client.yelpfusion.business.Location;
import jakarta.json.stream.JsonGenerator;

import java.util.List;
import java.util.function.Function;

@JsonpDeserializable
public class SearchBusiness implements JsonpSerializable {

    private final String id;
    private final String alias;
    private final String name;
    private final String image_url;
    private final String url;
    private final String display_phone;
    private final String phone;
    private final String price;
    private final Boolean is_closed;
    private final Double distance;
    private final Double rating;
    private final Integer review_count;
    private final List<String> transactions;
    private final Coordinates coordinates;
    private final Location location;
    private final List<Category> categories;

    private SearchBusiness(Builder builder) {
        this.id = builder.id;
        this.alias = builder.alias;
        this.name = builder.name;
        this.distance = builder.distance;
        this.display_phone = builder.display_phone;
        this.image_url = builder.image_url;
        this.is_closed = builder.is_closed;
        this.phone = builder.phone;
        this.price = builder.price;
        this.url = builder.url;
        this.rating = builder.rating;
        this.review_count = builder.review_count;
        this.location = builder.location;
        this.coordinates = builder.coordinates;
        this.categories = ApiTypeHelper.unmodifiable(builder.categories);
        this.transactions = ApiTypeHelper.unmodifiable(builder.transactions);
    }

    public static SearchBusiness of(Function<Builder, ObjectBuilder<SearchBusiness>> fn) {
        return fn.apply(new Builder()).build();
    }

    public String id() {
        return id;
    }

    public String alias() {
        return alias;
    }

    public String name() {
        return name;
    }

    public String price() {
        return price;
    }

    public String image_url() {
        return image_url;
    }

    public String url() {
        return url;
    }

    public Boolean is_closed() {
        return is_closed;
    }

    public Double distance() {
        return this.distance;
    }

    public String phone() {
        return phone;
    }

    public String display_phone() {
        return display_phone;
    }

    public Integer review_count() {
        return review_count;
    }

    public Double rating() {
        return rating;
    }

    public Location location() {
        return location;
    }

    public Coordinates coordinates() {
        return coordinates;
    }

    public List<Category> categories() {
        return categories;
    }

    public List<String> transactions() {
        return transactions;
    }

    // ------------------------------ serialize ------------------------------------ //
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
        if (this.image_url != null) {
            generator.writeKey("image_url");
            generator.write(this.image_url);
        }
        if (this.url != null) {
            generator.writeKey("url");
            generator.write(this.url);
        }
        if (this.phone != null) {
            generator.writeKey("phone");
            generator.write(this.phone);
        }
        if (this.price != null) {
            generator.writeKey("price");
            generator.write(this.price);
        }
        if (this.display_phone != null) {
            generator.writeKey("display_phone");
            generator.write(this.display_phone);
        }

        if (this.is_closed != null) {
            generator.writeKey("is_closed");
            generator.write(this.is_closed);
        }

        if (this.distance != null) {
            generator.writeKey("distance");
            generator.write(this.distance);
        }

        if (this.rating != null) {
            generator.writeKey("rating");
            generator.write(this.rating);
        }

        if (this.review_count != null) {
            generator.writeKey("review_count");
            generator.write(this.review_count);
        }

        if (ApiTypeHelper.isDefined(this.transactions)) {
            generator.writeKey("transactions");
            generator.writeStartArray();
            for (String item0 : this.transactions) {
                generator.write(item0);
            }
            generator.writeEnd();
        }
        if (this.location != null) {
            generator.writeKey("location");
            location.serialize(generator, mapper);
        }
        if (this.coordinates != null) {
            generator.writeKey("coordinates");
            coordinates.serialize(generator, mapper);
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

    // ------------------------------ Deserializer ------------------------------------ //

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    // ---------------------------------------------- Builder ---------------------------------- //
    @SuppressWarnings("UnusedReturnValue")
    public static class Builder extends WithJsonObjectBuilderBase<Builder> implements ObjectBuilder<SearchBusiness> {

        private String id;
        private String alias;
        private String display_phone;
        private String price;
        private String url;
        private String name;
        private String phone;
        private String image_url;
        private Boolean is_closed;
        private Double rating;
        private Double distance;
        private Integer review_count;
        private List<String> transactions;
        private Coordinates coordinates;
        private Location location;
        private List<Category> categories;


        // ---------------------------------------------- Setters ---------------------------------- //

        public final Builder display_phone(String value) {
            this.display_phone = value;
            return this;
        }

        public final Builder id(String value) {
            this.id = value;
            return this;
        }

        public final Builder phone(String value) {
            this.phone = value;
            return this;
        }

        public final Builder price(String value) {
            this.price = value;
            return this;
        }

        public final Builder name(String value) {
            this.name = value;
            return this;
        }

        public final Builder image_url(String value) {
            this.image_url = value;
            return this;
        }

        public final Builder alias(String value) {
            this.alias = value;
            return this;
        }

        public final Builder url(String value) {
            this.url = value;
            return this;
        }

        public final Builder rating(Double value) {
            this.rating = value;
            return this;
        }

        public final Builder distance(Double value) {
            this.distance = value;
            return this;
        }

        public final Builder location(Location value) {
            this.location = value;
            return this;
        }

        public final Builder location(Function<Location.Builder, ObjectBuilder<Location>> fn) {
            return this.location(fn.apply(new Location.Builder()).build());
        }

        public final Builder coordinates(Coordinates value) {
            this.coordinates = value;
            return this;
        }

        public final Builder coordinates(Function<Coordinates.Builder, ObjectBuilder<Coordinates>> fn) {
            return this.coordinates(fn.apply(new Coordinates.Builder()).build());
        }

        public final Builder categories(Function<Category.Builder, ObjectBuilder<Category>> fn) {
            return categories(fn.apply(new Category.Builder()).build());
        }

        public final Builder categories(List<Category> list) {
            this.categories = _listAddAll(this.categories, list);
            return this;
        }

        public final Builder categories(Category value, Category... values) {
            this.categories = _listAdd(this.categories, value, values);
            return this;
        }

        public final Builder transactions(List<String> value) {
            this.transactions = _listAddAll(this.transactions, value);
            return this;
        }

        public final Builder transactions(String value, String... values) {
            this.transactions = _listAdd(this.transactions, value, values);
            return this;
        }

        public final Builder review_count(Integer value) {
            this.review_count = value;
            return this;
        }

        public final Builder is_closed(boolean value) {
            this.is_closed = value;
            return this;
        }


        @Override
        protected Builder self() {
            return this;
        }

        public SearchBusiness build() {
            _checkSingleUse();
            return new SearchBusiness(this);
        }
    }

    protected static void setUpBusinessDeserializer(ObjectDeserializer<Builder> op) {
        op.add(Builder::id, JsonpDeserializer.stringDeserializer(), "id");
        op.add(Builder::alias, JsonpDeserializer.stringDeserializer(), "alias");
        op.add(Builder::name, JsonpDeserializer.stringDeserializer(), "name");
        op.add(Builder::image_url, JsonpDeserializer.stringDeserializer(), "image_url");
        op.add(Builder::phone, JsonpDeserializer.stringDeserializer(), "phone");
        op.add(Builder::display_phone, JsonpDeserializer.stringDeserializer(), "display_phone");
        op.add(Builder::price, JsonpDeserializer.stringDeserializer(), "price");
        op.add(Builder::url, JsonpDeserializer.stringDeserializer(), "url");

        op.add(Builder::is_closed, JsonpDeserializer.booleanDeserializer(), "is_closed");

        op.add(Builder::rating, JsonpDeserializer.doubleDeserializer(), "rating");
        op.add(Builder::distance, JsonpDeserializer.doubleDeserializer(), "distance");

        op.add(Builder::review_count, JsonpDeserializer.integerDeserializer(), "review_count");

        op.add(Builder::transactions, JsonpDeserializer.arrayDeserializer(JsonpDeserializer.stringDeserializer()), "transactions");

        op.add(Builder::location, Location._DESERIALIZER, "location");
        op.add(Builder::coordinates, Coordinates._DESERIALIZER, "coordinates");
        op.add(Builder::categories, JsonpDeserializer.arrayDeserializer(Category._DESERIALIZER), "categories");

    }


    public static final JsonpDeserializer<SearchBusiness> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
            SearchBusiness::setUpBusinessDeserializer);
}
