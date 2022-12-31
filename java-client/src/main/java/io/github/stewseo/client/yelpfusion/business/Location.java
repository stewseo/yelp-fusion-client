package io.github.stewseo.client.yelpfusion.business;


import io.github.stewseo.client.json.JsonpDeserializable;
import io.github.stewseo.client.json.JsonpDeserializer;
import io.github.stewseo.client.json.JsonpMapper;
import io.github.stewseo.client.json.JsonpSerializable;
import io.github.stewseo.client.json.JsonpUtils;
import io.github.stewseo.client.json.ObjectBuilderDeserializer;
import io.github.stewseo.client.json.ObjectDeserializer;
import io.github.stewseo.client.util.ObjectBuilder;
import io.github.stewseo.client.util.WithJsonObjectBuilderBase;
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Function;

@SuppressWarnings("unused")
@JsonpDeserializable
public class Location implements JsonpSerializable {

    public static final JsonpDeserializer<Location> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
            Location::setupLocationDeserializer);
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

    public static Location of(Function<Builder, ObjectBuilder<Location>> fn) {
        return fn.apply(new Builder()).build();
    }

    protected static void setupLocationDeserializer(ObjectDeserializer<Builder> op) {

        op.add(Builder::address1, JsonpDeserializer.stringDeserializer(), "address1");
        op.add(Builder::address2, JsonpDeserializer.stringDeserializer(), "address2");
        op.add(Builder::address3, JsonpDeserializer.stringDeserializer(), "address3");
        op.add(Builder::city, JsonpDeserializer.stringDeserializer(), "city");
        op.add(Builder::state, JsonpDeserializer.stringDeserializer(), "state");
        op.add(Builder::zip_code, JsonpDeserializer.stringDeserializer(), "zip_code");
        op.add(Builder::county, JsonpDeserializer.stringDeserializer(), "county");
        op.add(Builder::country, JsonpDeserializer.stringDeserializer(), "country");
        op.add(Builder::display_address, JsonpDeserializer.arrayDeserializer(JsonpDeserializer.stringDeserializer()), "display_address");
        op.add(Builder::cross_streets, JsonpDeserializer.stringDeserializer(), "cross_streets");
    }

    public String address1() {
        return address1;
    }

    public String address2() {
        return address2;
    }

    public String address3() {
        return address3;
    }

    public String city() {
        return city;
    }

    public String zip_code() {
        return zip_code;
    }

    public String country() {
        return country;
    }

    public String state() {
        return state;
    }

    public List<String> display_address() {
        return display_address;
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

        if (this.zip_code != null) {
            generator.writeKey("zip_code");
            generator.write(this.zip_code);
        }

        if (this.country != null) {
            generator.writeKey("country");
            generator.write(this.country);
        }

        if (this.state != null) {
            generator.writeKey("state");
            generator.write(this.state);
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

}

