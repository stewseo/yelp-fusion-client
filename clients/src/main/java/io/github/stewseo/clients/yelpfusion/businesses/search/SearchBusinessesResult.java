package io.github.stewseo.clients.yelpfusion.businesses.search;

import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ApiTypeHelper;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.yelpfusion._types.Location;
import jakarta.json.stream.JsonGenerator;

import java.util.List;
import java.util.function.Function;

@JsonpDeserializable
public class SearchBusinessesResult extends SearchResultBase {

    private final String display_phone;
    private final Boolean is_closed;
    private final Double distance;
    private final Location location;
    private final List<String> transactions;

    private SearchBusinessesResult(Builder builder) {
        super(builder);
        this.distance = builder.distance;
        this.display_phone = builder.display_phone;
        this.is_closed = builder.is_closed;
        this.location = builder.location;
        this.transactions = ApiTypeHelper.unmodifiable(builder.transactions);
    }

    public static SearchBusinessesResult of(Function<Builder, ObjectBuilder<SearchBusinessesResult>> fn) {
        return fn.apply(new Builder()).build();
    }

    public final String display_phone() {
        return display_phone;
    }

    public final Boolean is_closed() {
        return is_closed;
    }

    public final Double distance() {
        return this.distance;
    }

    public final Location location() {
        return location;
    }

    public final List<String> transactions() {
        return transactions;
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

//        generator.write("type", "SearchBusinessResult");
        super.serializeInternal(generator, mapper);

        if (this.display_phone != null) {
            generator.writeKey("display_phone");
            generator.write(this.display_phone);
        }

        if (this.is_closed != null) {
            generator.writeKey("is_closed");
            generator.write(this.is_closed);
        }

        if (this.distance != null) {
            generator.writeKey("distance");
            generator.write(this.distance);
        }

        if (ApiTypeHelper.isDefined(this.transactions)) {
            generator.writeKey("transactions");
            generator.writeStartArray();
            for (String item0 : this.transactions) {
                generator.write(item0);
            }
            generator.writeEnd();
        }

        if (this.location != null) {
            generator.writeKey("location");
            location.serialize(generator, mapper);
        }

    }

    /**
     * Builder for {@link SearchBusinessesResult}.
     */
    public static class Builder extends SearchResultBase.AbstractBuilder<SearchBusinessesResult.Builder>
            implements
            ObjectBuilder<SearchBusinessesResult> {

        private String display_phone;
        private Boolean is_closed;
        private Double distance;
        private Location location;
        private List<String> transactions;


        public final Builder display_phone(String value) {
            this.display_phone = value;
            return this;
        }

        public final Builder distance(Double value) {
            this.distance = value;
            return this;
        }

        public final Builder is_closed(Boolean value) {
            this.is_closed = value;
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

        @Override
        protected Builder self() {
            return this;
        }

        public SearchBusinessesResult build() {
            _checkSingleUse();
            return new SearchBusinessesResult(this);
        }
    }

    public static final JsonpDeserializer<SearchBusinessesResult> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
            SearchBusinessesResult::setUpBusinessDeserializer);

    protected static void setUpBusinessDeserializer(ObjectDeserializer<Builder> op) {

        setupSearchResultBaseBaseDeserializer(op);
        op.add(Builder::display_phone, JsonpDeserializer.stringDeserializer(), "display_phone");
        op.add(Builder::is_closed, JsonpDeserializer.booleanDeserializer(), "is_closed");
        op.add(Builder::distance, JsonpDeserializer.doubleDeserializer(), "distance");
        op.add(Builder::transactions, JsonpDeserializer.arrayDeserializer(JsonpDeserializer.stringDeserializer()), "transactions");
        op.add(Builder::location, Location._DESERIALIZER, "location");


    }

}
