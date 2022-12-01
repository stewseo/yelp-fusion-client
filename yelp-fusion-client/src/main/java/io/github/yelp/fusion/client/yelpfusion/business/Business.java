
package io.github.yelp.fusion.client.yelpfusion.business;


import io.github.yelp.fusion.client.json.*;
import io.github.yelp.fusion.client.util.ApiTypeHelper;
import io.github.yelp.fusion.client.util.ObjectBuilder;
import io.github.yelp.fusion.client.util.WithJsonObjectBuilderBase;
import jakarta.json.stream.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.*;
import java.util.jar.Attributes;

@SuppressWarnings({"unused", "ConstantConditions"})
@JsonpDeserializable
public class Business implements JsonpSerializable {

    private static final Logger logger = LoggerFactory.getLogger(Business.class);

    // ------------------------------ Fields ------------------------------------ //

    private final String id;

    private final String alias;

    private final String name;
    @Nullable
    private final String image_url;
    @Nullable
    private final String url;

    private final String phone;
    @Nullable
    private final String display_phone;
    @Nullable
    private final String price;

    private final Boolean is_claimed;

    private final Boolean  is_closed;

    private final Integer review_count;

    private final Double rating;

    private final Location location;

    private final Coordinates coordinates;

    private final List<String> transactions;
    @Nullable
    private final List<String> photos;

    private final List<Hours> hours;

    private final List<Categories> categories;

    @Nullable
    private final List<Attribute> attributes;

//    @Nullable
//    private final List<SpecialHours> special_hours;



//    @Nullable
//    private final Messaging messaging;



    // ------------------------------ Constructor -------------------------------- //
    private Business(Builder builder) {
        this.id = builder.id;
        this.alias = builder.alias;
        this.name = builder.name;
        this.location = builder.location;
        this.attributes = builder.attributes;
        this.display_phone = builder.display_phone;
        this.categories = builder.categories;
        this.coordinates = builder.coordinates;
        this.hours = builder.hours;
        this.image_url = builder.image_url;
        this.is_claimed = builder.is_claimed;
        this.is_closed = builder.is_closed;
//        this.messaging= builder.messaging;
        this.phone = builder.phone;
        this.photos = builder.photos;
        this.price = builder.price;
        this.url = builder.url;
        this.rating = builder.rating;
        this.review_count = builder.review_count;
//        this.special_hours = builder.special_hours;
        this.transactions = builder.transactions;
    }

    public static Business of(Function<Business.Builder, ObjectBuilder<Business>> fn) {
        return fn.apply(new Business.Builder()).build();
    }

    // ------------------------------ Methods ------------------------------------ //
    public String id() {
        return id;
    }

    public String alias() {
        return alias;
    }

    public String name() {
        return name;
    }

    public String image_url() {
        return image_url;
    }

    public Boolean is_claimed() {
        return is_claimed;
    }

    public Boolean is_closed() {
        return is_closed;
    }
    public String url() {
        return url;
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
    public List<Categories> categories() {
        return categories;
    }
    public Double rating() {
        return rating;
    }
    @Nullable
    public Location location() {
        return location;
    }
    @Nullable
    public Coordinates coordinates() {
        return coordinates;
    }
    @Nullable
    public List<String> photos() {
        return photos;
    }
    @Nullable
    public List<Hours> hours() {
        return hours;
    }
    public String price() {return price;}
    @Nullable
    public List<String> transactions() {
        return transactions;
    }
//    @Nullable
//    public List<SpecialHours> special_hours() {
//        return special_hours;
//    }

    public List<Attribute> attributes() {
        return attributes;
    }

//    public Messaging messaging() {
//        return messaging;
//    }

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
        if (this.review_count != 0) {
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
            for (Categories item0 : this.categories) {
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

//        if (this.special_hours != null) {
//            generator.writeKey("special_hours");
//            generator.writeStartArray();
//            for (SpecialHours item0 : this.special_hours) {
//                item0.serialize(generator, mapper);
//            }
//            generator.writeEnd();
//        }

    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    // ---------------------------------------------- Builder ---------------------------------- //
    @SuppressWarnings("UnusedReturnValue")
    public static class Builder extends WithJsonObjectBuilderBase<Builder> implements ObjectBuilder<Business> {
        // ---------------------------------------------- Builder Fields ---------------------------------- //
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
        private List<Attribute> attributes;
        private Coordinates coordinates;
        private List<Categories> categories;
        private Location location;
        private List<Hours> hours;
        private Messaging messaging;


        // ---------------------------------------------- Setters ---------------------------------- //

        public final Business.Builder phone(String value) {
            this.phone = value;
            return this;
        }
        public final Business.Builder price(String value) {
            this.price = value;
            return this;
        }
        public final Business.Builder name(String value) {
            this.name = value;
            return this;
        }
        public final Business.Builder image_url(String value) {
            this.image_url = value;
            return this;
        }
        public final Business.Builder alias(String value) {
            this.alias = value;
            return this;
        }
        @SuppressWarnings("UnusedReturnValue")
        public final Business.Builder url(String value) {
            this.url = value;
            return this;
        }
        public final Business.Builder display_phone(String value) {
            this.display_phone = value;
            return this;
        }
        public final Business.Builder review_count(int value) {
            this.review_count = value;
            return this;
        }
        public final Business.Builder is_closed(Boolean value) {
            this.is_closed = value;
            return this;
        }
        public final Business.Builder is_claimed(Boolean value) {
            this.is_claimed = value;
            return this;
        }
        public final Business.Builder id(String value) {
            this.id = value;
            return this;
        }
        public final Business.Builder rating(Double value) {
            this.rating = value;
            return this;
        }
        public final Business.Builder messaging(Messaging value) {
            this.messaging = value;
            return this;
        }
        public final Business.Builder open_now(Boolean value) {
            this.open_now = value;
            return this;
        }
        public final Builder hours(Function<Hours.Builder, ObjectBuilder<Hours>> fn) {
            return this.hours(fn.apply(new Hours.Builder()).build());
        }
        public final Business.Builder hours(List<Hours> list) {
            this.hours = _listAddAll(this.hours, list);
            return this;
        }
        public final Business.Builder hours(Hours value, Hours... values) {
            this.hours = _listAdd(this.hours, value, values);
            return this;
        }
        public final Business.Builder photos(List<String> list) {
            this.photos = _listAddAll(this.photos, list);
            return this;
        }
        public final Business.Builder photos(String value, String... values) {
            this.photos = _listAdd(this.photos, value, values);
            return this;
        }
        public final Business.Builder location(Location value) {
            this.location = value;
            return this;
        }
        public final Builder location(Function<Location.Builder, ObjectBuilder<Location>> fn) {
            return this.location(fn.apply(new Location.Builder()).build());
        }
        public final Business.Builder coordinates(Coordinates value) {
            this.coordinates = value;
            return this;
        }
        public final Builder coordinates(Function<Coordinates.Builder, ObjectBuilder<Coordinates>> fn) {
            return this.coordinates(fn.apply(new Coordinates.Builder()).build());
        }
        public final Business.Builder categories(Function<Categories.Builder, ObjectBuilder<Categories>> fn) {
            return categories(fn.apply(new Categories.Builder()).build());
        }
        public final Business.Builder categories(List<Categories> list) {
            this.categories = _listAddAll(this.categories, list);
            return this;
        }
        public final Business.Builder categories(Categories value, Categories... values) {
            this.categories = _listAdd(this.categories, value, values);
            return this;
        }
        public final Builder attributes(List<Attribute> value) {
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
//        public final Business.Builder special_hours(@Nullable List<SpecialHours> value) {
//            this.special_hours = _listAddAll(this.special_hours, value);
//            return this;
//        }
//        public final Business.Builder special_hours(@Nullable SpecialHours value, SpecialHours... values) {
//            this.special_hours = _listAdd(this.special_hours, value, values);
//            return this;
//        }

        @Override
        protected Business.Builder self() {
            return this;
        }

        public Business build() {
            _checkSingleUse();
            return new Business(this);
        }
    }

    public static final JsonpDeserializer<Business> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
            Business::setUpBusinessDeserializer);

    protected static void setUpBusinessDeserializer(ObjectDeserializer<Builder> op) {
        op.add(Business.Builder::id, JsonpDeserializer.stringDeserializer(), "id");
        op.add(Business.Builder::alias, JsonpDeserializer.stringDeserializer(), "alias");
        op.add(Business.Builder::name, JsonpDeserializer.stringDeserializer(), "name");
        op.add(Business.Builder::image_url, JsonpDeserializer.stringDeserializer(), "image_url");
        op.add(Business.Builder::phone, JsonpDeserializer.stringDeserializer(), "phone");
        op.add(Business.Builder::display_phone, JsonpDeserializer.stringDeserializer(), "display_phone");
        op.add(Business.Builder::url, JsonpDeserializer.stringDeserializer(), "url");
        op.add(Business.Builder::price, JsonpDeserializer.stringDeserializer(), "price");

        op.add(Business.Builder::is_claimed, JsonpDeserializer.booleanDeserializer(), "is_closed");
        op.add(Business.Builder::is_closed, JsonpDeserializer.booleanDeserializer(), "is_closed");

        op.add(Business.Builder::rating, JsonpDeserializer.doubleDeserializer(), "rating");

        op.add(Business.Builder::review_count, JsonpDeserializer.integerDeserializer(), "review_count");
        op.add(Business.Builder::location, Location._DESERIALIZER, "location");
        op.add(Business.Builder::coordinates, Coordinates._DESERIALIZER, "coordinates");

        op.add(Business.Builder::transactions, JsonpDeserializer.arrayDeserializer(JsonpDeserializer.stringDeserializer()), "photos");
        op.add(Business.Builder::transactions, JsonpDeserializer.arrayDeserializer(JsonpDeserializer.stringDeserializer()), "transactions");
        op.add(Business.Builder::attributes, JsonpDeserializer.arrayDeserializer(Attribute._DESERIALIZER), "attributes");

        op.add(Business.Builder::categories, JsonpDeserializer.arrayDeserializer(Categories._DESERIALIZER), "categories");
        op.add(Business.Builder::hours, JsonpDeserializer.arrayDeserializer(Hours._DESERIALIZER), "hours");

    }

}

