package io.github.stewseo.clients.yelpfusion.businesses.match;

import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.yelpfusion._types.Location;
import io.github.stewseo.clients.yelpfusion.ResultBase;
import jakarta.json.stream.JsonGenerator;

import java.util.function.Function;

@JsonpDeserializable
public class BusinessMatch extends ResultBase {

    private final String display;
    private final Location location;

    private BusinessMatch(Builder builder) {
        super(builder);
        this.display = builder.display;
        this.location = builder.location;
    }

    public static BusinessMatch of(Function<Builder, ObjectBuilder<BusinessMatch>> fn) {
        return fn.apply(new Builder()).build();
    }

    public String display() {
        return display;
    }

    public Location location() {
        return location;
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        super.serializeInternal(generator, mapper);

        if (this.display != null) {
            generator.writeKey("display");
            generator.write(this.display);
        }

        if (this.location != null) {
            generator.writeKey("location");
            location.serialize(generator, mapper);
        }
    }

    public static class Builder extends ResultBase.AbstractBuilder<BusinessMatch.Builder>
            implements
            ObjectBuilder<BusinessMatch> {

        private Location location;
        private String display;


        public final Builder location(Location location) {
            this.location = location;
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

        setupResultBaseDeserializer(op);
        op.add(Builder::display, JsonpDeserializer.stringDeserializer(), "display");
        op.add(Builder::location, Location._DESERIALIZER, "location");
    }
}
