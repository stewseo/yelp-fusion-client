package io.github.stewseo.clients.yelpfusion.businesses;

import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.yelpfusion._types.Location;
import io.github.stewseo.clients.yelpfusion._types.RequestBase;
import jakarta.json.stream.JsonGenerator;

import java.util.function.Function;

public abstract class BusinessesRequestBase extends RequestBase implements JsonpSerializable {
    
    private final Location location;
    private final Double latitude;
    private final Double longitude;
    private final String id;
    private final String alias;
    private final String name;

    private final String phone;

    protected BusinessesRequestBase(AbstractBuilder<?> builder) {
        this.location = builder.location;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
        this.id = builder.id;
        this.alias = builder.alias;
        this.name = builder.name;
        this.phone = builder.phone;

    }

    public Location location() {
        return location;
    }
    public Double latitude() {
        return latitude;
    }
    public Double longitude() {
        return longitude;
    }
    public final String id() {
        return id;
    }
    public final String alias() {
        return alias;
    }
    public String name() {
        return name;
    }

    public String phone() {
        return phone;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        if (this.location != null) {
            generator.writeKey("location");
            location.serialize(generator, mapper);
        }
        if (this.latitude != null) {
            generator.writeKey("latitude");
            generator.write(this.latitude);
        }
        if (this.longitude != null) {
            generator.writeKey("longitude");
            generator.write(this.longitude);
        }
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
        if (this.phone != null) {
            generator.writeKey("phone");
            generator.write(this.phone);
        }
    }

    protected abstract static class AbstractBuilder<BuilderT extends AbstractBuilder<BuilderT>>
            extends
            RequestBase.AbstractBuilder<BuilderT> {
        
        private Location location;
        private Double latitude;
        private Double longitude;
        private String id;
        private String alias;
        private String name;
        private String phone;

        public final BuilderT location(Location value) {
            this.location = value;
            return self();
        }
        public final BuilderT location(Function<Location.Builder, ObjectBuilder<Location>> fn) {
            return this.location(fn.apply(new Location.Builder()).build());
        }
        public final BuilderT id(String value) {
            this.id = value;
            return self();
        }

        public final BuilderT alias(String value) {
            this.alias = value;
            return self();
        }
        public final BuilderT name(String value) {
            this.name = value;
            return self();
        }
        public final BuilderT phone(String value) {
            this.phone = value;
            return self();
        }
        public final BuilderT latitude(Double value) {
            this.latitude = value;
            return self();
        }
        public final BuilderT longitude(Double value) {
            this.longitude = value;
            return self();
        }
    }

      protected static <BuilderT extends AbstractBuilder<BuilderT>> void setupBusinessRequestBaseDeserializer(
            ObjectDeserializer<BuilderT> op) {

          op.add(AbstractBuilder::latitude, JsonpDeserializer.doubleDeserializer(), "latitude");
          op.add(AbstractBuilder::longitude, JsonpDeserializer.doubleDeserializer(), "longitude");
          op.add(AbstractBuilder::id, JsonpDeserializer.stringDeserializer(), "id");
          op.add(AbstractBuilder::alias, JsonpDeserializer.stringDeserializer(), "alias");
          op.add(AbstractBuilder::name, JsonpDeserializer.stringDeserializer(), "name");
          op.add(AbstractBuilder::phone, JsonpDeserializer.stringDeserializer(), "phone");
      }

}
