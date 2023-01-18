package io.github.stewseo.clients.yelpfusion._types;


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
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Function;

@JsonpDeserializable
public class Location implements JsonpSerializable {

    private final String address1;
    @Nullable
    private final String address2;
    @Nullable
    private final String address3;

    private final String zip_code;
    private final String city;
    private final String state;
    private final String country;
    private final List<String> display_address;

    private Location(Builder builder) {
        this.address1 = builder.address1;
        this.address2 = builder.address2;
        this.address3 = builder.address3;
        this.zip_code = builder.zip_code;
        this.country = builder.country;
        this.city = builder.city;
        this.state = builder.state;
        this.display_address = ApiTypeHelper.unmodifiable(builder.display_address);
    }

    public static Location of(Function<Builder, ObjectBuilder<Location>> fn) {
        return fn.apply(new Builder()).build();
    }

    public final String address1() {
        return address1;
    }

    public final String address2() {
        return address2;
    }

    public final String address3() {
        return address3;
    }

    public final String city() {
        return city;
    }

    public final String zip_code() {
        return zip_code;
    }

    public String country() {
        return country;
    }

    public String state() {
        return state;
    }

    public final List<String> display_address() {
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

        if (ApiTypeHelper.isDefined(this.display_address)) {
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

    public static class Builder extends WithJsonObjectBuilderBase<Builder> implements ObjectBuilder<Location> {

        private String address1;
        private String address2;
        private String address3;
        private String city;
        private String state;
        private String zip_code;
        private String country;
        private String county;
        private String cross_streets;
        private List<String> display_address;

        public final Builder address1(String value) {
            this.address1 = value;
            return this;
        }

        public final Builder address2(@Nullable String value) {
            this.address2 = value;
            return this;
        }
        public final Builder address3(@Nullable String value) {
            this.address3 = value;
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
        public final Builder country(String value) {
            this.country = value;
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

        public final Builder display_address(List<String> value) {
            this.display_address = _listAddAll(this.display_address, value);
            return this;
        }

        public final Builder display_address(String value, String... values) {
            this.display_address = _listAdd(this.display_address, value, values);
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

    public static final JsonpDeserializer<Location> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
            Location::setupLocationDeserializer);

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
}

