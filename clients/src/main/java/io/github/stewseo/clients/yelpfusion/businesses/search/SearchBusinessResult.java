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
public class SearchBusinessResult extends SearchResultBase {

    private final String url;
    private final String display_phone;
    private final Integer review_count;
    private final Boolean is_closed;
    private final Double distance;
    private final Double rating;
    private final Location location;
    private final List<String> transactions;

    private SearchBusinessResult(Builder builder) {
        super(builder);
        this.distance = builder.distance;
        this.display_phone = builder.display_phone;
        this.is_closed = builder.is_closed;
        this.url = builder.url;
        this.rating = builder.rating;
        this.review_count = builder.review_count;
        this.location = builder.location;
        this.transactions = ApiTypeHelper.unmodifiable(builder.transactions);
    }

    public static SearchBusinessResult of(Function<Builder, ObjectBuilder<SearchBusinessResult>> fn) {
        return fn.apply(new Builder()).build();
    }

    public final String display_phone() {
        return display_phone;
    }

    public final String url() {
        return url;
    }

    public final Integer review_count() {
        return review_count;
    }

    public final Boolean is_closed() {
        return is_closed;
    }

    public final Double distance() {
        return this.distance;
    }

    public final Double rating() {
        return rating;
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

        if (this.url != null) {
            generator.writeKey("url");
            generator.write(this.url);
        }

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

        if (this.rating != null) {
            generator.writeKey("rating");
            generator.write(this.rating);
        }

        if (this.review_count != null) {
            generator.writeKey("review_count");
            generator.write(this.review_count);
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
     * Builder for {@link SearchBusinessResult}.
     */
    public static class Builder extends SearchResultBase.AbstractBuilder<SearchBusinessResult.Builder>
            implements
            ObjectBuilder<SearchBusinessResult> {

        private String url;
        private String display_phone;
        private Boolean is_closed;
        private Double rating;
        private Double distance;
        private Integer review_count;
        private Location location;
        private List<String> transactions;

        private Builder url(String value) {
            this.url = value;
            return this;
        }

        public final Builder display_phone(String value) {
            this.display_phone = value;
            return this;
        }

        public final Builder rating(Double value) {
            this.rating = value;
            return this;
        }

        public final Builder distance(Double value) {
            this.distance = value;
            return this;
        }

        public final Builder review_count(Integer value) {
            this.review_count = value;
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

        public SearchBusinessResult build() {
            _checkSingleUse();
            return new SearchBusinessResult(this);
        }
    }

    public static final JsonpDeserializer<SearchBusinessResult> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
            SearchBusinessResult::setUpBusinessDeserializer);

    protected static void setUpBusinessDeserializer(ObjectDeserializer<Builder> op) {

        setupSearchResultBaseBaseDeserializer(op);

        op.add(Builder::display_phone, JsonpDeserializer.stringDeserializer(), "display_phone");
        op.add(Builder::url, JsonpDeserializer.stringDeserializer(), "url");
        op.add(Builder::is_closed, JsonpDeserializer.booleanDeserializer(), "is_closed");
        op.add(Builder::rating, JsonpDeserializer.doubleDeserializer(), "rating");
        op.add(Builder::distance, JsonpDeserializer.doubleDeserializer(), "distance");
        op.add(Builder::review_count, JsonpDeserializer.integerDeserializer(), "review_count");
        op.add(Builder::transactions, JsonpDeserializer.arrayDeserializer(JsonpDeserializer.stringDeserializer()), "transactions");
        op.add(Builder::location, Location._DESERIALIZER, "location");


    }

}
