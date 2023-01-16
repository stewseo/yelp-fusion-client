package io.github.stewseo.clients.yelpfusion.businesses.details;


import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ApiTypeHelper;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.yelpfusion.ResultBase;
import io.github.stewseo.clients.yelpfusion._types.Hours;
import io.github.stewseo.clients.yelpfusion._types.Location;
import io.github.stewseo.clients.yelpfusion._types.Messaging;
import io.github.stewseo.clients.yelpfusion._types.SpecialHours;
import jakarta.json.stream.JsonGenerator;

import java.util.List;
import java.util.function.Function;

@JsonpDeserializable
public class BusinessDetails extends ResultBase {

    private final String display_phone;
    private final Boolean is_claimed;
    private final Boolean is_closed;
    private final Location location;
    private final List<String> transactions;
    private final List<String> photos;
    private final List<Hours> hours;
    private final List<SpecialHours> special_hours;
    private final Messaging messaging;

    private BusinessDetails(Builder builder) {
        super(builder);
        this.location = builder.location;
        this.display_phone = builder.display_phone;
        this.hours = builder.hours;
        this.is_claimed = builder.is_claimed;
        this.is_closed = builder.is_closed;
        this.messaging = builder.messaging;
        this.photos = ApiTypeHelper.unmodifiable(builder.photos);
        this.special_hours = ApiTypeHelper.unmodifiable(builder.special_hours);
        this.transactions = ApiTypeHelper.unmodifiable(builder.transactions);
    }

    public static BusinessDetails of(Function<Builder, ObjectBuilder<BusinessDetails>> fn) {
        return fn.apply(new Builder()).build();
    }


    public final Boolean is_claimed() {
        return is_claimed;
    }

    public final Boolean is_closed() {
        return is_closed;
    }

    public final String display_phone() {
        return display_phone;
    }

    public final Location location() {
        return location;
    }

    public final List<String> photos() {
        return photos;
    }

    public final List<Hours> hours() {
        return hours;
    }

    public final List<String> transactions() {
        return transactions;
    }

    public final List<SpecialHours> special_hours() {
        return special_hours;
    }

    public final Messaging messaging() {
        return messaging;
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        super.serializeInternal(generator, mapper);

        if (this.display_phone != null) {
            generator.writeKey("display_phone");
            generator.write(this.display_phone);
        }
        if (this.is_claimed != null) {
            generator.writeKey("is_claimed");
            generator.write(this.is_claimed);
        }

        if (is_closed != null) {
            generator.writeKey("is_closed");
            generator.write(this.is_closed);
        }

        if (this.location != null) {
            generator.writeKey("location");
            this.location.serialize(generator, mapper);
        }

        if (ApiTypeHelper.isDefined(this.transactions)) {
            generator.writeKey("transactions");
            generator.writeStartArray();
            for (String item0 : this.transactions) {
                generator.write(item0);
            }
            generator.writeEnd();
        }

        if (this.messaging != null) {
            generator.writeKey("messaging");
            messaging.serialize(generator, mapper);
        }
        if (ApiTypeHelper.isDefined(this.photos)) {
            generator.writeKey("photos");
            generator.writeStartArray();
            for (String item0 : this.photos) {
                generator.write(item0);
            }
            generator.writeEnd();
        }
        if (ApiTypeHelper.isDefined(this.hours)) {
            generator.writeKey("hours");
            generator.writeStartArray();
            for (Hours item0 : this.hours) {
                item0.serialize(generator, mapper);
            }
            generator.writeEnd();
        }

        if (ApiTypeHelper.isDefined(this.special_hours)) {
            generator.writeKey("special_hours");
            generator.writeStartArray();
            for (SpecialHours item0 : this.special_hours) {
                item0.serialize(generator, mapper);
            }
            generator.writeEnd();
        }
    }

    public static class Builder extends ResultBase.AbstractBuilder<BusinessDetails.Builder>
            implements
            ObjectBuilder<BusinessDetails> {

        private String display_phone;

        private Boolean is_claimed;

        private Boolean is_closed;

        private List<String> transactions;

        private List<String> photos;

        private List<SpecialHours> special_hours;

        private Location location;

        private List<Hours> hours;

        private Messaging messaging;

        public final Builder display_phone(String value) {
            this.display_phone = value;
            return this;
        }

        public final Builder is_closed(Boolean value) {
            this.is_closed = value;
            return this;
        }

        public final Builder is_claimed(Boolean value) {
            this.is_claimed = value;
            return this;
        }

        public final Builder messaging(Messaging value) {
            this.messaging = value;
            return this;
        }


        public final Builder hours(Function<Hours.Builder, ObjectBuilder<Hours>> fn) {
            return this.hours(fn.apply(new Hours.Builder()).build());
        }

        public final Builder hours(List<Hours> list) {
            this.hours = _listAddAll(this.hours, list);
            return this;
        }

        public final Builder hours(Hours value, Hours... values) {
            this.hours = _listAdd(this.hours, value, values);
            return this;
        }

        public final Builder photos(List<String> list) {
            this.photos = _listAddAll(this.photos, list);
            return this;
        }

        public final Builder photos(String value, String... values) {
            this.photos = _listAdd(this.photos, value, values);
            return this;
        }

        public final Builder location(Location value) {
            this.location = value;
            return this;
        }

        public final Builder location(Function<Location.Builder, ObjectBuilder<Location>> fn) {
            return this.location(fn.apply(new Location.Builder()).build());
        }


        public final Builder transactions(List<String> value) {
            this.transactions = _listAddAll(this.transactions, value);
            return this;
        }

        public final Builder transactions(String value, String... values) {
            this.transactions = _listAdd(this.transactions, value, values);
            return this;
        }

        public final Builder special_hours(List<SpecialHours> value) {
            this.special_hours = _listAddAll(this.special_hours, value);
            return this;
        }

        public final Builder special_hours(SpecialHours value, SpecialHours... values) {
            this.special_hours = _listAdd(this.special_hours, value, values);
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        public BusinessDetails build() {
            _checkSingleUse();
            return new BusinessDetails(this);
        }
    }

    public static final JsonpDeserializer<BusinessDetails> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
            BusinessDetails::setUpBusinessDeserializer);

    protected static void setUpBusinessDeserializer(ObjectDeserializer<Builder> op) {
        setupResultBaseDeserializer(op);
        op.add(Builder::display_phone, JsonpDeserializer.stringDeserializer(), "display_phone");
        op.add(Builder::is_claimed, JsonpDeserializer.booleanDeserializer(), "is_claimed");
        op.add(Builder::is_closed, JsonpDeserializer.booleanDeserializer(), "is_closed");
        op.add(Builder::location, Location._DESERIALIZER, "location");
        op.add(Builder::messaging, Messaging._DESERIALIZER, "messaging");
        op.add(Builder::photos, JsonpDeserializer.arrayDeserializer(JsonpDeserializer.stringDeserializer()), "photos");
        op.add(Builder::transactions, JsonpDeserializer.arrayDeserializer(JsonpDeserializer.stringDeserializer()), "transactions");
        op.add(Builder::hours, JsonpDeserializer.arrayDeserializer(Hours._DESERIALIZER), "hours");
    }
}


