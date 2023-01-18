package io.github.stewseo.clients.yelpfusion.businesses.search;

import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ObjectBuilder;
import jakarta.json.stream.JsonGenerator;

import java.util.function.Function;

@JsonpDeserializable
public class SearchBusinessesResult extends SearchBusinessesResultBase {

    private final String display_phone;
    private final Boolean is_closed;
    private final Double distance;

    private SearchBusinessesResult(Builder builder) {
        super(builder);
        this.distance = builder.distance;
        this.display_phone = builder.display_phone;
        this.is_closed = builder.is_closed;
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

    }

    /**
     * Builder for {@link SearchBusinessesResult}.
     */
    public static class Builder extends SearchBusinessesResultBase.AbstractBuilder<SearchBusinessesResult.Builder>
            implements
            ObjectBuilder<SearchBusinessesResult> {

        private String display_phone;
        private Boolean is_closed;
        private Double distance;


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
            SearchBusinessesResult::setUpSearchBusinessDeserializer);

    protected static void setUpSearchBusinessDeserializer(ObjectDeserializer<Builder> op) {

        setupSearchResultBaseBaseDeserializer(op);
        op.add(Builder::display_phone, JsonpDeserializer.stringDeserializer(), "display_phone");
        op.add(Builder::is_closed, JsonpDeserializer.booleanDeserializer(), "is_closed");
        op.add(Builder::distance, JsonpDeserializer.doubleDeserializer(), "distance");

    }

}
