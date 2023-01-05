package io.github.stewseo.clients.yelpfusion.businesses.details;


import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ApiTypeHelper;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.util.WithJsonObjectBuilderBase;
import io.github.stewseo.clients.yelpfusion._types.Attribute;
import io.github.stewseo.clients.yelpfusion._types.Category;
import io.github.stewseo.clients.yelpfusion._types.Coordinates;
import io.github.stewseo.clients.yelpfusion._types.Hours;
import io.github.stewseo.clients.yelpfusion._types.Location;
import io.github.stewseo.clients.yelpfusion._types.Messaging;
import io.github.stewseo.clients.yelpfusion._types.SpecialHours;
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Function;

@JsonpDeserializable
public class BusinessDetails implements JsonpSerializable {
    private final String id;
    private final String alias;
    private final String name;
    private final String image_url;
    private final String url;
    private final String phone;
    private final String display_phone;
    private final String price;
    private final Boolean is_claimed;
    private final Boolean is_closed;
    private final Integer review_count;
    private final Double rating;
    private final Location location;
    private final Coordinates coordinates;
    private final List<String> transactions;
    private final List<String> photos;
    private final List<Hours> hours;
    private final List<Category> categories;
    private final List<Attribute> attributes;
    private final List<SpecialHours> special_hours;
    private final Messaging messaging;

    private BusinessDetails(Builder builder) {
        this.id = builder.id;
        this.alias = builder.alias;
        this.name = builder.name;
        this.location = builder.location;
        this.display_phone = builder.display_phone;
        this.coordinates = builder.coordinates;
        this.hours = builder.hours;
        this.image_url = builder.image_url;
        this.is_claimed = builder.is_claimed;
        this.is_closed = builder.is_closed;
        this.messaging = builder.messaging;
        this.phone = builder.phone;
        this.photos = builder.photos;
        this.price = builder.price;
        this.url = builder.url;
        this.rating = builder.rating;
        this.review_count = builder.review_count;
        this.attributes = ApiTypeHelper.unmodifiable(builder.attributes);
        this.categories = ApiTypeHelper.unmodifiable(builder.categories);
        this.special_hours = ApiTypeHelper.unmodifiable(builder.special_hours);
        this.transactions = ApiTypeHelper.unmodifiable(builder.transactions);
    }

    public static BusinessDetails of(Function<Builder, ObjectBuilder<BusinessDetails>> fn) {
        return fn.apply(new Builder()).build();
    }

    public final String id() {
        return id;
    }

    public final String alias() {
        return alias;
    }

    public final String name() {
        return name;
    }

    public final String image_url() {
        return image_url;
    }

    public final Boolean is_claimed() {
        return is_claimed;
    }

    public final Boolean is_closed() {
        return is_closed;
    }

    public final String url() {
        return url;
    }

    public final String phone() {
        return phone;
    }

    public final String display_phone() {
        return display_phone;
    }

    public final Integer review_count() {
        return review_count;
    }

    public final List<Category> categories() {
        return categories;
    }

    public final Double rating() {
        return rating;
    }

    public final Location location() {
        return location;
    }

    public final Coordinates coordinates() {
        return coordinates;
    }

    public final List<String> photos() {
        return photos;
    }

    public final List<Hours> hours() {
        return hours;
    }

    public final String price() {
        return price;
    }

    public final List<String> transactions() {
        return transactions;
    }

    public final List<SpecialHours> special_hours() {
        return special_hours;
    }

    public final List<Attribute> attributes() {
        return attributes;
    }

    public final Messaging messaging() {
        return messaging;
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
        if (this.is_claimed != null) {
            generator.writeKey("is_claimed");
            generator.write(this.is_claimed);
        }

        if (is_closed != null) {
            generator.writeKey("is_closed");
            generator.write(this.is_closed);
        }

        if (this.rating != null) {
            generator.writeKey("rating");
            generator.write(this.rating);
        }

        if (this.review_count != null) {
            generator.writeKey("review_count");
            generator.write(this.review_count);
        }

        if (this.location != null) {
            generator.writeKey("location");
            this.location.serialize(generator, mapper);
        }

        if (this.coordinates != null) {
            generator.writeKey("coordinates");
            this.coordinates.serialize(generator, mapper);
        }

        if (ApiTypeHelper.isDefined(this.transactions)) {
            generator.writeKey("transactions");
            generator.writeStartArray();
            for (String item0 : this.transactions) {
                generator.write(item0);
            }
            generator.writeEnd();
        }

        if (this.messaging != null) {
            generator.writeKey("messaging");
            messaging.serialize(generator, mapper);
        }

        if (ApiTypeHelper.isDefined(this.hours)) {
            generator.writeKey("hours");
            generator.writeStartArray();
            for (Hours item0 : this.hours) {
                item0.serialize(generator, mapper);
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

        if (ApiTypeHelper.isDefined(this.attributes)) {
            generator.writeKey("attributes");
            generator.writeStartArray();
            for (Attribute item0 : this.attributes) {
                item0.serialize(generator, mapper);
            }
            generator.writeEnd();
        }

        if (ApiTypeHelper.isDefined(this.special_hours)) {
            generator.writeKey("special_hours");
            generator.writeStartArray();
            for (SpecialHours item0 : this.special_hours) {
                item0.serialize(generator, mapper);
            }
            generator.writeEnd();
        }
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    public static class Builder extends WithJsonObjectBuilderBase<Builder> implements ObjectBuilder<BusinessDetails> {

        private String id;

        private String alias;

        private String display_phone;

        private String price;

        private String url;

        private String name;

        private String phone;

        private String image_url;

        private Double rating;

        private Integer review_count;

        private Integer distance;

        private Boolean is_claimed;

        private Boolean is_closed;

        private Boolean open_now;

        private List<String> transactions;

        private List<String> photos;

        private List<SpecialHours> special_hours;

        @Nullable
        private List<Attribute> attributes;

        private Coordinates coordinates;

        @Nullable
        private List<Category> categories;

        private Location location;

        private List<Hours> hours;

        private Messaging messaging;


        // ---------------------------------------------- Setters ---------------------------------- //

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

        public final Builder display_phone(String value) {
            this.display_phone = value;
            return this;
        }

        public final Builder review_count(int value) {
            this.review_count = value;
            return this;
        }

        public final Builder is_closed(Boolean value) {
            this.is_closed = value;
            return this;
        }

        public final Builder is_claimed(Boolean value) {
            this.is_claimed = value;
            return this;
        }

        public final Builder id(String value) {
            this.id = value;
            return this;
        }

        public final Builder rating(Double value) {
            this.rating = value;
            return this;
        }

        public final Builder messaging(Messaging value) {
            this.messaging = value;
            return this;
        }

        public final Builder open_now(Boolean value) {
            this.open_now = value;
            return this;
        }

        public final Builder hours(Function<Hours.Builder, ObjectBuilder<Hours>> fn) {
            return this.hours(fn.apply(new Hours.Builder()).build());
        }

        public final Builder hours(List<Hours> list) {
            this.hours = _listAddAll(this.hours, list);
            return this;
        }

        public final Builder hours(Hours value, Hours... values) {
            this.hours = _listAdd(this.hours, value, values);
            return this;
        }

        public final Builder photos(List<String> list) {
            this.photos = _listAddAll(this.photos, list);
            return this;
        }

        public final Builder photos(String value, String... values) {
            this.photos = _listAdd(this.photos, value, values);
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

        public final Builder attributes(@Nullable List<Attribute> value) {
            this.attributes = _listAddAll(this.attributes, value);
            return this;
        }

        public final Builder attributes(Attribute value, Attribute... values) {
            this.attributes = _listAdd(this.attributes, value, values);
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

        public final Builder special_hours(List<SpecialHours> value) {
            this.special_hours = _listAddAll(this.special_hours, value);
            return this;
        }

        public final Builder special_hours(SpecialHours value, SpecialHours... values) {
            this.special_hours = _listAdd(this.special_hours, value, values);
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        public BusinessDetails build() {
            _checkSingleUse();
            return new BusinessDetails(this);
        }
    }

    public static final JsonpDeserializer<BusinessDetails> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
            BusinessDetails::setUpBusinessDeserializer);

    protected static void setUpBusinessDeserializer(ObjectDeserializer<Builder> op) {
        op.add(Builder::id, JsonpDeserializer.stringDeserializer(), "id");
        op.add(Builder::alias, JsonpDeserializer.stringDeserializer(), "alias");
        op.add(Builder::name, JsonpDeserializer.stringDeserializer(), "name");
        op.add(Builder::image_url, JsonpDeserializer.stringDeserializer(), "image_url");
        op.add(Builder::phone, JsonpDeserializer.stringDeserializer(), "phone");
        op.add(Builder::display_phone, JsonpDeserializer.stringDeserializer(), "display_phone");
        op.add(Builder::url, JsonpDeserializer.stringDeserializer(), "url");
        op.add(Builder::price, JsonpDeserializer.stringDeserializer(), "price");
        op.add(Builder::is_claimed, JsonpDeserializer.booleanDeserializer(), "is_closed");
        op.add(Builder::is_closed, JsonpDeserializer.booleanDeserializer(), "is_closed");
        op.add(Builder::is_closed, JsonpDeserializer.booleanDeserializer(), "open_now");
        op.add(Builder::rating, JsonpDeserializer.doubleDeserializer(), "rating");
        op.add(Builder::review_count, JsonpDeserializer.integerDeserializer(), "review_count");
        op.add(Builder::location, Location._DESERIALIZER, "location");
        op.add(Builder::coordinates, Coordinates._DESERIALIZER, "coordinates");
        op.add(Builder::messaging, Messaging._DESERIALIZER, "messaging");
        op.add(Builder::transactions, JsonpDeserializer.arrayDeserializer(JsonpDeserializer.stringDeserializer()), "photos");
        op.add(Builder::transactions, JsonpDeserializer.arrayDeserializer(JsonpDeserializer.stringDeserializer()), "transactions");
        op.add(Builder::attributes, JsonpDeserializer.arrayDeserializer(Attribute._DESERIALIZER), "attributes");
        op.add(Builder::categories, JsonpDeserializer.arrayDeserializer(Category._DESERIALIZER), "categories");
        op.add(Builder::hours, JsonpDeserializer.arrayDeserializer(Hours._DESERIALIZER), "hours");
    }
}


