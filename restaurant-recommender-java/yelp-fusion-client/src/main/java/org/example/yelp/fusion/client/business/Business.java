
package org.example.yelp.fusion.client.business;


import com.fasterxml.jackson.databind.deser.std.*;
import jakarta.json.stream.*;
import org.example.elasticsearch.client.json.*;
import org.example.elasticsearch.client.util.*;
import org.example.lowlevel.restclient.*;
import org.example.yelp.fusion.client.category.*;

import java.util.*;
import java.util.function.*;

@SuppressWarnings("unused")
@JsonpDeserializable
public class Business implements JsonpSerializable {

    // ------------------------------------------------------- Fields
    private final Coordinates coordinates;
    private final List<Categories> categories;
    private final Location location; // The location of this business, including address, city, state, zip code and country.

    private final String display_phone; // Phone number of the business formatted nicely to be displayed to users. The format is the standard phone number format for the business's country.
    private final String id; // Unique Yelp ID of this business.
    private final String alias, // Unique Yelp alias of this business. Can contain unicode characters.
            image_url, // URL of photo for this business.

            name, // name of business

            phone, // Phone number of the business.
            price, // Price level of the business. Value is one of $, $$, $$$ and $$$$.
            url; // URL for business page on Yelp.
    private final int open_at;
    private final int review_count;
    private final int distance;
    private final int rating;

    private final boolean is_claim, is_closed, open_now;
    private final List<String> transactions, photos, special_hours, attributes;

    private final List<Hours> hours;
    private final Object messaging; // Contains Business Messaging / Request a Quote information for this business.
                            // This field only appears in the response for businesses that have messaging enabled.


    // --------------------------------------------------------  Constructor
    private Business(Builder builder) {
        this.location = builder.location;
        this.alias = builder.alias;
        this.attributes = builder.attributes;
        this.display_phone = builder.display_phone;
        this.id = builder.id;
        this.categories = builder.categories;
        this.coordinates = builder.coordinates;
        this.hours = builder.hours;
        this.image_url = builder.image_url;
        this.is_claim = builder.is_claim;
        this.is_closed = builder.is_closed;
        this.messaging= builder.messaging;
        this.name = builder.name;
        this.open_at = builder.open_at;
        this.open_now = builder.open_now;
        this.phone = builder.phone;
        this.photos = builder.photos;
        this.price = builder.price;
        this.url = builder.url;
        this.rating = builder.rating;
        this.review_count = builder.review_count;
        this.special_hours = builder.special_hours;
        this.transactions = builder.transactions;
        this.distance = builder.distance;
    }

    public static Business of(Function<Business.Builder, ObjectBuilder<Business>> fn) {
        return fn.apply(new Business.Builder()).build();
    }

    public Coordinates coordinates() {
        return coordinates;
    }

    public Location location() {
        return location;
    }

    public String display_phone() {
        return display_phone;
    }

    public String id() {
        return id;
    }

    public String alias() {
        return alias;
    }

    public String image_url() {
        return image_url;
    }

    public String name() {
        return name;
    }

    public String phone() {
        return phone;
    }

    public String price() {
        return price;
    }

    public String url() {
        return url;
    }

    public int open_at() {
        return open_at;
    }

    public int review_count() {
        return review_count;
    }

    public int distance() {
        return distance;
    }

    public double rating() {
        return rating;
    }

    public boolean is_claim() {
        return is_claim;
    }

    public boolean is_closed() {
        return is_closed;
    }

    public boolean open_now() {
        return open_now;
    }

    public List<String> transactions() {
        return transactions;
    }

    public List<Categories> categories() {
        return categories;
    }

    public List<String> photos() {
        return photos;
    }

    public List<String> special_hours() {
        return special_hours;
    }

    public List<String> attributes() {
        return attributes;
    }

    public List<Hours> hours() {
        return hours;
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

        if (this.is_claim) {
            generator.writeKey("is_claim");
            generator.write(this.is_claim);
        }
        if (is_closed) {
            generator.writeKey("is_closed");
            generator.write(this.is_closed);
        }
        if (this.display_phone != null) {
            generator.writeKey("display_phone");
            generator.write(this.display_phone);
        }
        if (this.id != null) {
            generator.writeKey("id");
            generator.write(this.id);
        }
        if (this.alias != null) {
            generator.writeKey("alias");
            generator.write(this.alias);

        }
        if (this.image_url != null) {
            generator.writeKey("image_url");
            generator.write(this.image_url);
        }
        if (this.name != null) {
            generator.writeKey("name");
            generator.write(this.name);
        }
        if (this.phone != null) {
            generator.writeKey("phone");
            generator.write(this.phone);
        }
        if (this.url != null) {
            generator.writeKey("url");
            generator.write(this.url);
        }
        if (this.rating != 0) {
            generator.writeKey("rating");
            generator.write(this.rating);
        }
        if (this.open_at != 0) {
            generator.writeKey("open_at");
            generator.write(this.open_at);
        }
        if (this.price != null) {
            generator.writeKey("price");
            generator.write(this.price);
        }
        if (this.review_count != 0) {
            generator.writeKey("review_count");
            generator.write(this.review_count);
        }
        if (this.distance != 0) {
            generator.writeKey("distance");
            generator.write(this.distance);
        }
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    // ----------------------------------------------------------------- Builder
    public static class Builder extends WithJsonObjectBuilderBase<Builder> implements ObjectBuilder<Business> {
        private String display_phone, id, alias, image_url, name, phone, price, url;
        private int rating, review_count, open_at, distance;
        private boolean is_claim, is_closed, open_now;
        private List<String> transactions, photos,
                special_hours,
                attributes;

        private Coordinates coordinates;

        private List<Categories> categories;
        private Location location; // The location of this business, including address, city, state, zip code and country.
        private List<Hours> hours;
        private Object messaging; // Contains Business Messaging / Request a Quote information for this business.
        // This field only appears in the response for businesses that have messaging enabled.

        public final Business.Builder display_phone(String value) {
            this.display_phone = value;
            return this;
        }
        public final Business.Builder id(String value) {
            this.id = value;
            return this;
        }
        public final Business.Builder price(String value) {
            this.price = value;
            return this;
        }
        public final Business.Builder phone(String value) {
            this.phone = value;
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
        public final Business.Builder attributes(List<String> value) {
            this.special_hours = value;
            return this;
        }
        public final Business.Builder transactions(List<String> value) {
            this.transactions = value;
            return this;
        }
        public final Business.Builder special_hours(List<String> value) {
            this.special_hours = value;
            return this;
        }


        public final Business.Builder review_count(int value) {
            this.review_count = value;
            return this;
        }
        public final Business.Builder open_at(int value) {
            this.open_at = value;
            return this;
        }

        public final Business.Builder distance(int value) {
            this.distance = value;
            return this;
        }
        public final Business.Builder is_closed(boolean value) {
            this.is_closed = value;
            return this;
        }
        public final Business.Builder is_claim(boolean value) {
            this.is_claim = value;
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

    public static final JsonpDeserializer<Business> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Business.Builder::new,
            Business::setUpBusinessDeserializer);

    protected static void setUpBusinessDeserializer(ObjectDeserializer<Builder> op) {
        PrintUtils.green("Business setUpBusinessDeserializer");
        op.add(Business.Builder::id, JsonpDeserializer.stringDeserializer(), "id");
        op.add(Business.Builder::is_closed, JsonpDeserializer.booleanDeserializer(), "is_closed");
        op.add(Business.Builder::display_phone,  JsonpDeserializer.stringDeserializer(), "display_phone");
        op.add(Business.Builder::alias, JsonpDeserializer.stringDeserializer(), "alias");
        op.add(Business.Builder::review_count, JsonpDeserializer.integerDeserializer(), "review_count");
        op.add(Business.Builder::url, JsonpDeserializer.stringDeserializer(), "url");
        op.add(Business.Builder::alias, JsonpDeserializer.stringDeserializer(), "alias");
        op.add(Business.Builder::image_url, JsonpDeserializer.stringDeserializer(), "image_url");
        op.add(Business.Builder::price, JsonpDeserializer.stringDeserializer(), "price");
        op.add(Business.Builder::phone, JsonpDeserializer.stringDeserializer(), "phone");
//        op.add(Business.Builder::latitude, JsonpDeserializer.doubleDeserializer(), "latitude");
//        op.add(Business.Builder::longitude, JsonpDeserializer.doubleDeserializer(), "longitude");
        op.add(Business.Builder::rating, JsonpDeserializer.integerDeserializer(), "rating");
        op.add(Business.Builder::price, JsonpDeserializer.stringDeserializer(), "price");
        op.add(Business.Builder::transactions, JsonpDeserializer.arrayDeserializer(JsonpDeserializer.stringDeserializer()), "transactions");
        op.add(Business.Builder::location, Location._DESERIALIZER, "location");
        op.add(Business.Builder::hours, JsonpDeserializer.arrayDeserializer(Hours._DESERIALIZER), "hours");

        op.add(Business.Builder::categories, JsonpDeserializer.arrayDeserializer(Categories._DESERIALIZER), "categories");
        op.add(Business.Builder::coordinates, Coordinates._DESERIALIZER, "coordinates");
    }

}

