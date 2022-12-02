package io.github.stewseo.yelp.fusion.client.yelpfusion.business;


import io.github.stewseo.yelp.fusion.client.json.*;
import io.github.stewseo.yelp.fusion.client.util.WithJsonObjectBuilderBase;
import io.github.stewseo.yelp.fusion.client.util.ObjectBuilder;
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;
import java.util.*;

import static io.github.stewseo.yelp.fusion.client.json.JsonpDeserializer.stringDeserializer;

@SuppressWarnings("unused")
@JsonpDeserializable
public class Location implements JsonpSerializable {

    // "location": {"address1": "8515 Main St", "address2": "", "address3": null,
    // "city": "Briarwood",
    // "zip_code": "11435",
    // "country": "US",
    // "state": "NY",
    // "display_address": ["8515 Main St", "Briarwood, NY 11435"]},
    private final String address1;
    @Nullable
    private final String address2;
    @Nullable
    private final String address3;
    private final String city;
    private final String zip_code;
    private final String country;
    private final String state;
    private final List<String> display_address;

    private String address1() {
        return address1;
    }

    private String address2() {
        return address2;
    }

    private String address3() {
        return address3;
    }
    private String city() {
        return city;
    }

    private String zip_code() {
        return zip_code;
    }

    private String country() {
        return country;
    }

    private String state() {
        return state;
    }

    private List<String> display_address() {
        return display_address;
    }

    private Location(Builder builder) {
        this.address1 = builder.address1;
        this.address2 = builder.address2;
        this.address3 = builder.address3;
        this.zip_code = builder.zip_code;
        this.country = builder.country;
        this.city = builder.city;
        this.state = builder.state;
        this.display_address = builder.display_address;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        if (this.address1 != null) {
            generator.writeKey("address1");
            generator.write(this.address1);
        }
        if (this.address2 != null) {
            generator.writeKey("address2");
            generator.write(this.address2);
        }
        if (this.address3 != null) {
            generator.writeKey("address3");
            generator.write(this.address3);
        }
        if (this.city != null) {
            generator.writeKey("city");
            generator.write(this.city);
        }
        if (this.state != null) {
            generator.writeKey("state");
            generator.write(this.state);
        }
        if (this.zip_code != null) {
            generator.writeKey("zip_code");
            generator.write(this.zip_code);
        }

        if (this.country != null) {
            generator.writeKey("country");
            generator.write(this.country);
        }
        if (this.display_address != null) {
            generator.writeKey("display_address");
            generator.writeStartArray();
            for (String item0 : this.display_address) {
                generator.write(item0);
            }
            generator.writeEnd();
        }

    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static class Builder extends WithJsonObjectBuilderBase<Builder> implements ObjectBuilder<Location> {
        private String address1, address2, address3, city, county, cross_streets, state, zip_code, country;

        private List<String> display_address;

        public final Builder country(String value) {
            this.country = value;
            return this;
        }

        public final Builder display_address(List<String> value) {
            this.display_address = value;
            return this;
        }

        public final Builder cross_streets(String value) {
            this.cross_streets = value;
            return this;
        }

        public final Builder state(String value) {
            this.state = value;
            return this;
        }

        public final Builder address3(String value) {
            this.address3 = value;
            return this;
        }

        public final Builder address1(String value) {
            this.address1 = value;
            return this;
        }

        public final Builder address2(String value) {
            this.address2 = value;
            return this;
        }

        public final Builder zip_code(String value) {
            this.zip_code = value;
            return this;
        }

        public final Builder city(String value) {
            this.city = value;
            return this;
        }

        public final Builder county(String value) {
            this.county = value;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }


        public Location build() {
            _checkSingleUse();
            return new Location(this);
        }
    }


    public static final JsonpDeserializer<Location> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Location.Builder::new,
            Location::setupLocationDeserializer);

    protected static void setupLocationDeserializer(ObjectDeserializer<Builder> op) {

        op.add(Location.Builder::address1, stringDeserializer(), "address1");
        op.add(Location.Builder::address2, stringDeserializer(), "address2");
        op.add(Location.Builder::address3, stringDeserializer(), "address3");
        op.add(Location.Builder::display_address, JsonpDeserializer.arrayDeserializer(stringDeserializer()), "display_address");
        op.add(Location.Builder::city, stringDeserializer(), "city");
        op.add(Location.Builder::zip_code, stringDeserializer(), "zip_code");
        op.add(Location.Builder::county, stringDeserializer(), "county");
        op.add(Location.Builder::country, stringDeserializer(), "country");
        op.add(Location.Builder::cross_streets, stringDeserializer(), "cross_streets");
    }

}

