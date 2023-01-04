package io.github.stewseo.clients.yelpfusion._types;

import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.util.WithJsonObjectBuilderBase;
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

    public static BusinessMatch of(Function<Builder, ObjectBuilder<BusinessMatch>> fn) {
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

    public final Coordinates coordinates() {
        return coordinates;
    }

    public final Location location() {
        return location;
    }

    public final String phone() {
        return phone;
    }

    public final String display() {
        return display;
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
            generator.writeKey("phone");
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

        public final Builder id(String id) {
            this.id = id;
            return this;
        }

        public final Builder alias(String alias) {
            this.alias = alias;
            return this;
        }

        public final Builder name(String name) {
            this.name = name;
            return this;
        }

        public final Builder coordinates(Coordinates coordinates) {
            this.coordinates = coordinates;
            return this;
        }

        public final Builder location(Location location) {
            this.location = location;
            return this;
        }

        public final Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public final Builder display(String display) {
            this.display = display;
            return this;
        }

        @Override
        protected final Builder self() {
            return this;
        }

        public final BusinessMatch build() {
            _checkSingleUse();
            return new BusinessMatch(this);
        }
    }


    public static final JsonpDeserializer<BusinessMatch> _DESERIALIZER =
            ObjectBuilderDeserializer.lazy(Builder::new, BusinessMatch::setupBusinessMatchDeserializer);

    protected static void setupBusinessMatchDeserializer(ObjectDeserializer<Builder> op) {
        op.add(Builder::id, JsonpDeserializer.stringDeserializer(), "id");
        op.add(Builder::alias, JsonpDeserializer.stringDeserializer(), "alias");
        op.add(Builder::name, JsonpDeserializer.stringDeserializer(), "name");
        op.add(Builder::phone, JsonpDeserializer.stringDeserializer(), "phone");
        op.add(Builder::display, JsonpDeserializer.stringDeserializer(), "display");
        op.add(Builder::coordinates, Coordinates._DESERIALIZER, "coordinates");
        op.add(Builder::location, Location._DESERIALIZER, "location");
    }
}
