
package org.example.yelp.fusion.client.business.model;


import co.elastic.clients.elasticsearch.core.SearchResponse;
import jakarta.json.stream.*;
import org.example.elasticsearch.client.json.*;
import org.example.elasticsearch.client.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.*;

@SuppressWarnings({"unused", "ConstantConditions"})
@JsonpDeserializable
public class Business implements JsonpSerializable {

    private static final Logger logger = LoggerFactory.getLogger(Business.class);

    // ------------------------------ Fields ------------------------------------ //
    @Nullable
    private final String id;
    @Nullable
    private final String alias;
    @Nullable
    private final String name;

    @Nullable
    private final String image_url;

    @Nullable
    private final Boolean is_claimed;
    @Nullable
    private final Boolean  is_closed;
    @Nullable
    private final String url;
    @Nullable
    private final String phone;
    @Nullable
    private final String display_phone;
    @Nullable
    private final Integer review_count;

    @Nullable
    private final List<Categories> categories;

    @Nullable
    private final Location location;

    @Nullable
    private final Boolean open_now;
    @Nullable
    private final Coordinates coordinates;

    @Nullable
    private final String price;

    @Nullable
    private final Integer rating;

    @Nullable
    private final List<String> transactions;
    @Nullable
    private final List<String> photos;
    @Nullable
    private final List<String> special_hours;
    @Nullable
    private final List<Attribute> attributes;

    @Nullable
    private final List<Hours> hours;
    @Nullable
    private final Object messaging;



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
        this.messaging= builder.messaging;
        this.open_now = builder.open_now;
        this.phone = builder.phone;
        this.photos = builder.photos;
        this.price = builder.price;
        this.url = builder.url;
        this.rating = builder.rating;
        this.review_count = builder.review_count;
        this.special_hours = builder.special_hours;
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
    public Integer rating() {
        return rating;
    }
    public Location location() {
        return location;
    }
    public Coordinates coordinates() {
        return coordinates;
    }
    public List<String> photos() {
        return photos;
    }
    public List<Hours> hours() {
        return hours;
    }
    public String price() {return price;}
    @Nullable
    public Boolean open_now() {
        return open_now;
    }

    public List<String> transactions() {
        return transactions;
    }


    public List<String> special_hours() {
        return special_hours;
    }

    public List<Attribute> attributes() {
        return attributes;
    }

    public Object messaging() {
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
        if (Boolean.TRUE.equals(this.is_claimed)) {
            generator.writeKey("is_claimed");
            generator.write(this.is_claimed);
        }
        if (Boolean.TRUE.equals(is_closed)) {
            generator.writeKey("is_closed");
            generator.write(this.is_closed);
        }
        if (this.url != null) {
            generator.writeKey("url");
            generator.write(this.url);
        }
        if (this.phone != null) {
            generator.writeKey("phone");
            generator.write(this.phone);
        }

        if (this.display_phone != null) {
            generator.writeKey("display_phone");
            generator.write(this.display_phone);
        }
        if (this.review_count != 0) {
            generator.writeKey("review_count");
            generator.write(this.review_count);
        }
        if (this.hours != null) {
            generator.writeKey("hours");
            generator.writeStartArray();
            for (Hours item0 : this.hours) {
                item0.serialize(generator, mapper);
            }
            generator.writeEnd();
        }

//        if(this.location != null) {
//            generator.writeKey("location");
//            location.serialize(generator, mapper);
//            generator.writeEnd();
//        }

//        if(this.coordinates != null) {
//            generator.writeKey("coordinates");
//            coordinates.serialize(generator, mapper);
//            generator.writeEnd();
//        }

//        if (this.special_hours != null) {
//            generator.writeKey("special_hours");
//            generator.writeStartArray();
//            for (String item0 : this.special_hours) {
//                generator.write(item0);
//
//            }
//            generator.writeEnd();
//        }
//        if (this.transactions != null) {
//            generator.writeKey("transactions");
//            generator.writeStartArray();
//            for (String item0 : this.transactions) {
//                generator.write(item0);
//            }
//            generator.writeEnd();
//        }
        if (this.attributes != null) {
            generator.writeKey("attributes");
            generator.writeStartArray();
            for (Attribute item0 : this.attributes) {
                item0.serialize(generator, mapper);
            }
            generator.writeEnd();
        }
        if (this.rating != 0) {
            generator.writeKey("rating");
            generator.write(this.rating);
        }
        if (this.price != null) {
            generator.writeKey("price");
            generator.write(this.price);
        }

    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    // ---------------------------------------------- Builder ---------------------------------- //
    @SuppressWarnings("UnusedReturnValue")
    public static class Builder extends WithJsonObjectBuilderBase<Builder> implements ObjectBuilder<Business> {
        // ---------------------------------------------- Builder Fields ---------------------------------- //
        @Nullable
        private String id;
        @Nullable
        private String alias;
        @Nullable
        private String display_phone;
        @Nullable
        private String price;
        @Nullable
        private String url;
        @Nullable
        private String name;
        @Nullable
        private String phone;
        @Nullable
        private String image_url;
        @Nullable
        private Integer rating, review_count, distance;
        @Nullable
        private Boolean is_claimed, is_closed;
        @Nullable
        private Boolean open_now;
        @Nullable
        private List<String> transactions;
        @Nullable
        private List<String> photos;
        @Nullable
        private List<String> special_hours;
        @Nullable
        private List<Attribute> attributes;
        @Nullable
        private Coordinates coordinates;
        @Nullable
        private List<Categories> categories;
        @Nullable
        private Location location;
        @Nullable
        private List<Hours> hours;
        @Nullable
        private Object messaging;

        // ---------------------------------------------- Setters ---------------------------------- //

        public final Business.Builder display_phone(String value) {
            this.display_phone = value;
            return this;
        }
        public final Business.Builder id(String value) {
            this.id = value;
            return this;
        }
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
        public final Business.Builder rating(Integer value) {
            this.rating = value;
            return this;
        }
        public final Business.Builder messaging(Object value) {
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
        public final Business.Builder attributes(List<Attribute> value) {
            this.attributes = _listAddAll(this.attributes, value);
            return this;
        }
        public final Business.Builder attributes(Attribute value, Attribute... values) {
            this.attributes = _listAdd(this.attributes, value, values);
            return this;
        }
        public final Business.Builder transactions(List<String> value) {
            this.transactions = value;
            return this;
        }
        public final Business.Builder transactions(String value, String... values) {
            this.transactions = _listAdd(this.transactions, value, values);
            return this;
        }
        public final Business.Builder special_hours(List<String> value) {
            this.special_hours = value;
            return this;
        }
        public final Business.Builder special_hours(String value, String... values) {
            this.special_hours = _listAdd(this.special_hours, value, values);
            return this;
        }
        public final Business.Builder review_count(int value) {
            this.review_count = value;
            return this;
        }
        public final Business.Builder is_closed(boolean value) {
            this.is_closed = value;
            return this;
        }
        public final Business.Builder is_claimed(boolean value) {
            this.is_claimed = value;
            return this;
        }
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
        op.add(Business.Builder::is_closed, JsonpDeserializer.booleanDeserializer(), "is_closed");
        op.add(Business.Builder::is_claimed, JsonpDeserializer.booleanDeserializer(), "is_claimed");
        op.add(Business.Builder::phone, JsonpDeserializer.stringDeserializer(), "phone");
        op.add(Business.Builder::display_phone,  JsonpDeserializer.stringDeserializer(), "display_phone");
        op.add(Business.Builder::review_count, JsonpDeserializer.integerDeserializer(), "review_count");
        op.add(Business.Builder::location, Location._DESERIALIZER, "location");
        op.add(Business.Builder::coordinates, Coordinates._DESERIALIZER, "coordinates");
        op.add(Business.Builder::categories, JsonpDeserializer.arrayDeserializer(Categories._DESERIALIZER), "categories");
        op.add(Business.Builder::hours, JsonpDeserializer.arrayDeserializer(Hours._DESERIALIZER), "hours");
        op.add(Business.Builder::messaging, JsonpDeserializer.stringDeserializer(), "messaging");
        op.add(Business.Builder::photos, JsonpDeserializer.arrayDeserializer(JsonpDeserializer.stringDeserializer()), "photos");
        op.add(Business.Builder::price, JsonpDeserializer.stringDeserializer(), "price");
        op.add(Business.Builder::rating, JsonpDeserializer.integerDeserializer(), "rating");
        op.add(Business.Builder::url, JsonpDeserializer.stringDeserializer(), "url");
        op.add(Business.Builder::transactions, JsonpDeserializer.arrayDeserializer(JsonpDeserializer.stringDeserializer()), "transactions");
        op.add(Business.Builder::attributes, Attribute._DESERIALIZER, "attributes");
        op.add(Business.Builder::special_hours, JsonpDeserializer.arrayDeserializer(JsonpDeserializer.stringDeserializer()), "special_hours");

    }

}

