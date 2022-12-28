package io.github.stewseo.yelp.fusion.client.yelpfusion.business.match;


import io.github.stewseo.yelp.fusion.client.json.JsonpDeserializable;
import io.github.stewseo.yelp.fusion.client.json.JsonpDeserializer;
import io.github.stewseo.yelp.fusion.client.json.JsonpMapper;
import io.github.stewseo.yelp.fusion.client.json.JsonpSerializable;
import io.github.stewseo.yelp.fusion.client.json.JsonpUtils;
import io.github.stewseo.yelp.fusion.client.json.ObjectBuilderDeserializer;
import io.github.stewseo.yelp.fusion.client.json.ObjectDeserializer;
import io.github.stewseo.yelp.fusion.client.util.ObjectBuilder;
import io.github.stewseo.yelp.fusion.client.util.WithJsonObjectBuilderBase;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.Coordinates;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.Location;
import jakarta.json.stream.JsonGenerator;

import java.util.function.Function;

@JsonpDeserializable
public class BusinessMatch implements JsonpSerializable {

    private final String id;

    private final String alias;
    private final String name;

    private final Coordinates coordinates;
    private final Location location;
    private final String phone;

    private final String display;


    private BusinessMatch(Builder builder) {
        this.id = builder.id;
        this.alias = builder.alias;
        this.name = builder.name;
        this.coordinates = builder.coordinates;
        this.location = builder.location;
        this.phone = builder.phone;
        this.display = builder.display;

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

    public Coordinates coordinates() {
        return coordinates;
    }

    public Location location() {
        return location;
    }

    public String phone() {
        return phone;
    }

    public String display() {
        return display;
    }

    public static BusinessMatch of(Function<Builder, ObjectBuilder<BusinessMatch>> fn) {
        return fn.apply(new Builder()).build();
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

        if (this.location != null) {
            generator.writeKey("location");
            location.serialize(generator, mapper);
        }
        if (this.coordinates != null) {
            generator.writeKey("coordinates");
            coordinates.serialize(generator, mapper);
        }
        if (this.phone != null) {
            generator.writeKey("postal_code");
            generator.write(this.phone);
        }
        if (this.display != null) {
            generator.writeKey("display");
            generator.write(this.display);
        }
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }


    public static class Builder extends WithJsonObjectBuilderBase<Builder>
            implements
            ObjectBuilder<BusinessMatch> {
        private String id;

        private String alias;
        private String name;

        private Coordinates coordinates;
        private Location location;
        private String phone;

        private String display;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder alias(String alias) {
            this.alias = alias;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder coordinates(Coordinates coordinates) {
            this.coordinates = coordinates;
            return this;
        }

        public Builder location(Location location) {
            this.location = location;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder display(String display) {
            this.display = display;
            return this;
        }

        @Override
        protected BusinessMatch.Builder self() {
            return this;
        }

        public BusinessMatch build() {
            _checkSingleUse();
            return new BusinessMatch(this);
        }
    }

    // ------------------------------ Deserializer ------------------------------------ //

    public static final JsonpDeserializer<BusinessMatch> _DESERIALIZER =
            ObjectBuilderDeserializer.lazy(BusinessMatch.Builder::new, BusinessMatch::setupAutoCompleteDeserializer);

    protected static void setupAutoCompleteDeserializer(ObjectDeserializer<Builder> op) {
        op.add(BusinessMatch.Builder::id, JsonpDeserializer.stringDeserializer(), "id");
        op.add(BusinessMatch.Builder::alias, JsonpDeserializer.stringDeserializer(), "alias");
        op.add(BusinessMatch.Builder::name, JsonpDeserializer.stringDeserializer(), "name");
        op.add(BusinessMatch.Builder::phone, JsonpDeserializer.stringDeserializer(), "phone");
        op.add(BusinessMatch.Builder::display, JsonpDeserializer.stringDeserializer(), "display");
        op.add(BusinessMatch.Builder::coordinates, Coordinates._DESERIALIZER, "coordinates");
        op.add(BusinessMatch.Builder::location, Location._DESERIALIZER, "location");
    }
}
