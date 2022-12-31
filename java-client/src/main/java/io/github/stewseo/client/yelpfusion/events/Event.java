package io.github.stewseo.client.yelpfusion.events;

import io.github.stewseo.client.json.JsonpDeserializable;
import io.github.stewseo.client.json.JsonpDeserializer;
import io.github.stewseo.client.json.JsonpMapper;
import io.github.stewseo.client.json.JsonpSerializable;
import io.github.stewseo.client.json.ObjectBuilderDeserializer;
import io.github.stewseo.client.json.ObjectDeserializer;
import io.github.stewseo.client.util.ObjectBuilder;
import io.github.stewseo.client.util.WithJsonObjectBuilderBase;
import io.github.stewseo.client.yelpfusion.business.Location;
import io.github.stewseo.client.json.JsonpUtils;
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;
import java.util.function.Function;

@JsonpDeserializable
public class Event implements JsonpSerializable {
    public static final JsonpDeserializer<Event> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
            Event::setUpEventDeserializer);
    private final String category;
    private final String description;
    private final String event_site_url;
    private final String id;
    private final String name;
    private final String tickets_url;
    private final String image_url;
    private final String time_end;
    private final String time_start;
    private final Integer attending_count;
    private final Integer interested_count;
    private final Boolean is_canceled;
    private final Boolean is_free;
    private final Boolean is_official;
    private final Double cost;
    private final Double cost_max;
    private final Double latitude;
    private final Double longitude;
    @Nullable
    private final Location location;

    private Event(Builder builder) {
        this.category = builder.category;
        this.description = builder.description;
        this.event_site_url = builder.event_site_url;
        this.id = builder.id;
        this.name = builder.name;
        this.tickets_url = builder.tickets_url;
        this.image_url = builder.image_url;
        this.time_end = builder.time_end;
        this.time_start = builder.time_start;
        this.attending_count = builder.attending_count;
        this.interested_count = builder.interested_count;
        this.is_canceled = builder.is_canceled;
        this.is_free = builder.is_free;
        this.is_official = builder.is_official;
        this.cost = builder.cost;
        this.cost_max = builder.cost_max;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
        this.location = builder.location;
    }

    public static Event of(Function<Builder, ObjectBuilder<Event>> fn) {
        return fn.apply(new Builder()).build();
    }

    protected static void setUpEventDeserializer(ObjectDeserializer<Builder> op) {
        op.add(Builder::category, JsonpDeserializer.stringDeserializer(), "category");
        op.add(Builder::description, JsonpDeserializer.stringDeserializer(), "description");
        op.add(Builder::event_site_url, JsonpDeserializer.stringDeserializer(), "event_site_url");
        op.add(Builder::id, JsonpDeserializer.stringDeserializer(), "id");
        op.add(Builder::name, JsonpDeserializer.stringDeserializer(), "name");
        op.add(Builder::tickets_url, JsonpDeserializer.stringDeserializer(), "tickets_url");
        op.add(Builder::time_end, JsonpDeserializer.stringDeserializer(), "time_end");
        op.add(Builder::time_start, JsonpDeserializer.stringDeserializer(), "time_start");

        op.add(Builder::is_canceled, JsonpDeserializer.booleanDeserializer(), "is_canceled");
        op.add(Builder::is_free, JsonpDeserializer.booleanDeserializer(), "is_free");
        op.add(Builder::is_official, JsonpDeserializer.booleanDeserializer(), "is_official");

        op.add(Builder::attending_count, JsonpDeserializer.integerDeserializer(), "attending_count");
        op.add(Builder::interested_count, JsonpDeserializer.integerDeserializer(), "interested_count");

        op.add(Builder::cost, JsonpDeserializer.doubleDeserializer(), "cost");
        op.add(Builder::cost_max, JsonpDeserializer.doubleDeserializer(), "cost_max");
        op.add(Builder::latitude, JsonpDeserializer.doubleDeserializer(), "latitude");
        op.add(Builder::longitude, JsonpDeserializer.doubleDeserializer(), "longitude");

        op.add(Builder::location, Location._DESERIALIZER, "location");
    }

    public String category() {
        return category;
    }

    public String description() {
        return description;
    }

    public String event_site_url() {
        return event_site_url;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String tickets_url() {
        return tickets_url;
    }

    public String image_url() {
        return image_url;
    }

    public String time_end() {
        return time_end;
    }

    public String time_start() {
        return time_start;
    }

    public Integer attending_count() {
        return attending_count;
    }

    public Integer interested_count() {
        return interested_count;
    }

    public Boolean is_canceled() {
        return is_canceled;
    }

    public Boolean is_free() {
        return is_free;
    }

    public Boolean is_official() {
        return is_official;
    }

    public Double cost() {
        return cost;
    }

    public Double cost_max() {
        return cost_max;
    }

    public Double latitude() {
        return latitude;
    }

    public Double longitude() {
        return longitude;
    }

    @Nullable
    public Location location() {
        return location;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {
        if (this.category != null) {
            generator.writeKey("category");
            generator.write(this.category);
        }
        if (this.description != null) {
            generator.writeKey("description");
            generator.write(this.description);
        }
        if (this.event_site_url != null) {
            generator.writeKey("event_site_url");
            generator.write(this.event_site_url);
        }
        if (this.id != null) {
            generator.writeKey("id");
            generator.write(this.id);
        }
        if (this.name != null) {
            generator.writeKey("name");
            generator.write(this.name);
        }
        if (this.tickets_url != null) {
            generator.writeKey("tickets_url");
            generator.write(this.tickets_url);
        }
        if (this.image_url != null) {
            generator.writeKey("image_url");
            generator.write(this.image_url);
        }
        if (this.time_end != null) {
            generator.writeKey("time_end");
            generator.write(this.time_end);
        }
        if (this.time_start != null) {
            generator.writeKey("time_start");
            generator.write(this.time_start);
        }
        if (this.attending_count != null) {
            generator.writeKey("attending_count");
            generator.write(this.attending_count);
        }
        if (this.interested_count != null) {
            generator.writeKey("interested_count");
            generator.write(this.interested_count);
        }
        if (this.is_canceled != null) {
            generator.writeKey("is_canceled");
            generator.write(this.is_canceled);
        }
        if (this.is_free != null) {
            generator.writeKey("is_free");
            generator.write(this.is_free);
        }
        if (this.is_official != null) {
            generator.writeKey("is_official");
            generator.write(this.is_official);
        }
        if (this.cost != null) {
            generator.writeKey("cost");
            generator.write(this.cost);
        }
        if (this.cost_max != null) {
            generator.writeKey("cost_max");
            generator.write(this.cost_max);
        }
        if (this.latitude != null) {
            generator.writeKey("latitude");
            generator.write(this.latitude);
        }
        if (this.longitude != null) {
            generator.writeKey("longitude");
            generator.write(this.longitude);
        }
        if (this.location != null) {
            generator.writeKey("location");
            this.location.serialize(generator, mapper);
        }
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    // ---------------------------------------------- Builder ---------------------------------- //
    @SuppressWarnings("UnusedReturnValue")
    public static class Builder extends WithJsonObjectBuilderBase<Builder> implements ObjectBuilder<Event> {
        private String description;
        private String category;
        private String event_site_url;
        private String name;
        private String tickets_url;
        private String image_url;
        private String id;
        private String time_end;
        private String time_start;
        private Integer attending_count;
        private Integer interested_count;
        private Boolean is_canceled;
        private Boolean is_free;
        private Boolean is_official;
        private Double cost;
        private Double cost_max;
        private Double latitude;
        private Double longitude;
        private Location location;

        public final Builder description(String description) {
            this.description = description;
            return this;
        }

        public final Builder category(String category) {
            this.category = category;
            return this;
        }

        public final Builder event_site_url(String event_site_url) {
            this.event_site_url = event_site_url;
            return this;
        }

        public final Builder name(String name) {
            this.name = name;
            return this;
        }

        public final Builder tickets_url(String tickets_url) {
            this.tickets_url = tickets_url;
            return this;
        }

        public final Builder image_url(String image_url) {
            this.image_url = image_url;
            return this;
        }

        public final Builder id(String id) {
            this.id = id;
            return this;
        }

        public final Builder time_end(String time_end) {
            this.time_end = time_end;
            return this;
        }

        public final Builder time_start(String time_start) {
            this.time_start = time_start;
            return this;
        }

        public final Builder attending_count(Integer attending_count) {
            this.attending_count = attending_count;
            return this;
        }

        public final Builder interested_count(Integer interested_count) {
            this.interested_count = interested_count;
            return this;
        }

        public final Builder is_canceled(Boolean is_canceled) {
            this.is_canceled = is_canceled;
            return this;
        }

        public final Builder is_free(Boolean is_free) {
            this.is_free = is_free;
            return this;
        }

        public final Builder is_official(Boolean is_official) {
            this.is_official = is_official;
            return this;
        }

        public final Builder cost(Double cost) {
            this.cost = cost;
            return this;
        }

        public final Builder cost_max(Double cost_max) {
            this.cost_max = cost_max;
            return this;
        }

        public final Builder latitude(Double latitude) {
            this.latitude = latitude;
            return this;
        }

        public final Builder longitude(Double longitude) {
            this.longitude = longitude;
            return this;
        }

        public final Builder location(Location value) {
            this.location = value;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        public Event build() {
            _checkSingleUse();
            return new Event(this);
        }
    }
}
