package io.github.stewseo.yelp.fusion.client.yelpfusion.business.search;


import io.github.stewseo.yelp.fusion.client.json.*;
import io.github.stewseo.yelp.fusion.client.transport.endpoints.SimpleEndpoint;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.Attribute;
import io.github.stewseo.yelp.fusion.client.yelpfusion.categories.Category;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.Coordinates;
import jakarta.json.stream.JsonGenerator;
import io.github.stewseo.yelp.fusion.client._types.RequestBase;
import io.github.stewseo.yelp.fusion.client.util.ApiTypeHelper;
import io.github.stewseo.yelp.fusion.client.util.ObjectBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;


// Build a BusinessSearchRequest for the business search endpoint
public class SearchBusinessRequest extends RequestBase implements JsonpSerializable {

    private static final Logger logger = LoggerFactory.getLogger(SearchBusinessRequest.class);
    @Nullable
    private final List<String> term;
    @Nullable
    private final List<String> location;
    @Nullable
    private final Integer radius;
    @Nullable
    private final Category categories;
    @Nullable
    private final String locale;
    @Nullable
    private final Integer limit;
    @Nullable
    private final Integer offset;

    private final Coordinates coordinates;
    @Nullable
    private final String sort_by;
    @Nullable
    private final String price;
    @Nullable
    private final Boolean open_now;
    @Nullable
    private final Integer open_at;
    @Nullable
    private final List<Attribute> attributes;
    
    private SearchBusinessRequest(Builder builder){

        this.term = builder.term;
        this.location = builder.location;
        this.coordinates = builder.coordinates;
        this.radius = builder.radius;
        this.categories = builder.categories;
        this.locale = builder.locale;
        this.limit = builder.limit;
        this.offset = builder.offset;
        this.sort_by = builder.sort_by;
        this.price = builder.price;
        this.open_now = builder.open_now;
        this.open_at = builder.open_at;
        this.attributes = builder.attributes;
    }

    public static SearchBusinessRequest of(Function<Builder, ObjectBuilder<SearchBusinessRequest>> fn) {
        return fn.apply(new Builder()).build();
    }

    public List<String> term() {
        return this.term;
    }
    public List<String> location() {
        return this.location;
    }

    public int radius() {
        return this.radius;
    }

    public Category categories() {
        return this.categories;
    }

    public Coordinates coordinates() {
        return this.coordinates;
    }

    public String locale() {
        return this.locale;
    }

    public Integer limit() {
        return this.limit;
    }

    public Integer offset() {
        return this.offset;
    }

    public String sort_by() {return this.sort_by;}

    public Boolean open_now() {
        return this.open_now;
    }

    public int open_at() {
        return this.open_at;
    }

    public String price() {
        return this.price;
    }

    public List<Attribute> attributes() {
        return this.attributes;
    }

    // -----------------------------------------------------------------------------------------------------------------

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {
        if (ApiTypeHelper.isDefined(this.term)) {
            generator.writeKey("term");
            generator.writeStartArray();
            for (String item0 : this.term) {
                generator.write(item0);
            }
            generator.writeEnd();
        }

        if (ApiTypeHelper.isDefined(this.location)) {
            generator.writeKey("location");
            generator.writeStartArray();
            for (String item0 : this.location) {
                generator.write(item0);
            }
            generator.writeEnd();
        }

        if(this.categories != null) {
            generator.writeKey("all");
            this.categories.serialize(generator, mapper);
        }

        if(this.coordinates != null) {
            generator.writeKey("coordinates");
            this.coordinates.serialize(generator, mapper);
        }

        if (this.radius != null) {
            generator.writeKey("radius");
            generator.write(this.radius);
        }

        if (this.offset != null) {
            generator.writeKey("offset");
            generator.write(this.offset);
        }

        if (this.sort_by != null) {
            generator.writeKey("sort_by");
            generator.write(this.sort_by);
        }

        if (this.limit != null) {
            generator.writeKey("limit");
            generator.write(this.limit);
        }

        if (this.open_at != null) {
            generator.writeKey("open_at");
            generator.write(this.open_at);
        }

        if (this.price != null) {
            generator.writeKey("price");
            generator.write(this.price);
        }

        if (ApiTypeHelper.isDefined(this.attributes)) {
            generator.writeKey("attributes");
            generator.writeStartArray();
            for (Attribute item0 : this.attributes) {
                item0.serialize(generator, mapper);
            }
            generator.writeEnd();

        }
    }

        // Builder for BusinessSearchRequest

    public static class Builder extends RequestBase.AbstractBuilder<SearchBusinessRequest.Builder> implements ObjectBuilder<SearchBusinessRequest> {

        @Nullable
        private List<String> term;
        @Nullable
        private List<String> location;
        @Nullable
        private Coordinates coordinates;
        @Nullable
        private Double latitude;
        @Nullable
        private Double longitude;
        @Nullable
        private Integer radius;
        @Nullable
        private Category categories;
        @Nullable
        private String locale;
        @Nullable
        private Integer limit;
        @Nullable
        private Integer offset;
        @Nullable
        private String price;
        @Nullable
        private String sort_by;
        @Nullable
        private Boolean open_now;
        @Nullable
        private Integer open_at;
        @Nullable
        private List<Attribute> attributes;

        public final Builder term(@Nullable List<String> list) {
            this.term = _listAddAll(this.term, list);
            return this;                               
        }
        public final Builder term(@Nullable String value, String... values) {
            this.term = _listAdd(this.term, value, values);
            return this;
        }

        public final Builder categories(@Nullable Category value) {
            this.categories = value;
            return this;
        }

        public final Builder categories(@Nullable Function<Category.Builder, ObjectBuilder<Category>> fn) {
            return this.categories(fn.apply(new Category.Builder()).build());
        }
        public final Builder coordinates(@Nullable Coordinates value) {
            this.coordinates = value;
            return this;
        }

        public final Builder coordinates(Function<Coordinates.Builder, ObjectBuilder<Coordinates>> fn) {
            return this.coordinates(fn.apply(new Coordinates.Builder()).build());
        }

        public final Builder location(@Nullable List<String> list) {
            this.location = _listAddAll(this.location, list);
            return this;
        }
        public final Builder location(@Nullable String value, String... values) {
            this.location = _listAdd(this.location, value, values);
            return this;
        }

        public final Builder radius(@Nullable Integer radius) {
            this.radius = radius;
            return this;
        }

        public final Builder locale(@Nullable String locale) {
            this.locale = locale;
            return this;
        }
        public final Builder offset(@Nullable Integer offset) {
            this.offset = offset;
            return this;
        }
        public final Builder limit(@Nullable Integer limit) {
            this.limit = limit;
            return this;
        }

        public final Builder sort_by(@Nullable String sort_by) {
            this.sort_by = sort_by;
            return this;
        }

        public final Builder open_now(@Nullable Boolean open_now) {
            this.open_now = open_now;
            return this;
        }

        public final Builder open_at(@Nullable Integer open_at) {
            this.open_at = open_at;
            return this;
        }

        public final Builder price(String price) {
            this.price = price;
            return this;
        }

        public final Builder attributes(@Nullable List<Attribute> list) {
            this.attributes = _listAddAll(this.attributes, list);
            return this;
        }

        public final Builder attributes(@Nullable Attribute value, Attribute... values) {
            this.attributes = _listAdd(this.attributes, value, values);
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public SearchBusinessRequest build() {
            _checkSingleUse();
            return new SearchBusinessRequest(this);
        }

    }

    //Json deserializer for BusinessSearchRequest

    public static final JsonpDeserializer<SearchBusinessRequest> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
                    SearchBusinessRequest::setupSearchRequestDeserializer);

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    protected static void setupSearchRequestDeserializer(ObjectDeserializer<Builder> op) {

        op.add(Builder::term, JsonpDeserializer.arrayDeserializer(JsonpDeserializer.stringDeserializer()), "term");
        op.add(Builder::location, JsonpDeserializer.stringDeserializer(), "location");
        op.add(Builder::coordinates, Coordinates._DESERIALIZER, "coordinates");
        op.add(Builder::radius, JsonpDeserializer.integerDeserializer(), "radius");
        op.add(Builder::categories, Category._DESERIALIZER, "all");
        op.add(Builder::attributes, JsonpDeserializer.arrayDeserializer(Attribute._DESERIALIZER), "attributes");
        op.add(Builder::limit, JsonpDeserializer.integerDeserializer(), "limit");
        op.add(Builder::offset, JsonpDeserializer.integerDeserializer(), "offset");
        op.add(Builder::sort_by, JsonpDeserializer.stringDeserializer(), "sort_by");
        op.add(Builder::price, JsonpDeserializer.stringDeserializer(), "price");
        op.add(Builder::open_now, JsonpDeserializer.booleanDeserializer(), "open_now");
        op.add(Builder::open_at, JsonpDeserializer.integerDeserializer(), "open_at");
    }
    public static final SimpleEndpoint<SearchBusinessRequest, ?> _ENDPOINT = new SimpleEndpoint<>("v3/businesses/search",
            // Request method
            request -> "GET",

            // Request path
            request -> "v3/businesses/search",
            // Request parameters
            request -> {
                Map<String, String> parameters = new HashMap<>();
                if (request.term != null) {
                    request.term.forEach(term -> parameters.put("term", term));
                }
                if (request.location() != null) {
                    request.location.forEach(location -> parameters.put("location", location));
                }
                if (request.categories() != null) {
                    parameters.put("all", request.categories.alias());
                }
                if (request.coordinates() != null) {
                    Double latitude = request.coordinates().latitude();
                    if (latitude != null) {
                        parameters.put("latitude", String.valueOf(latitude));
                    }
                    Double longitude = request.coordinates().longitude();
                    if (longitude != null) {
                        parameters.put("longitude", String.valueOf(longitude));
                    }
                }

                if (request.radius != null) {
                    parameters.put("radius", String.valueOf(request.radius));
                }
                if (request.locale != null) {
                    parameters.put("locale", String.valueOf(request.locale));
                }
                if (request.limit != null) {
                    parameters.put("limit", String.valueOf(request.limit));
                }
                if (request.offset != null) {
                    parameters.put("offset", String.valueOf(request.offset));
                }
                if (request.sort_by != null) {
                    parameters.put("sort_by", request.sort_by);
                }
                if (request.price != null) {
                    parameters.put("price", request.price);
                }
                if (request.open_now != null) {
                    parameters.put("open_now", String.valueOf(request.open_now));
                }
                if (request.open_at != null) {
                    parameters.put("open_at", String.valueOf(request.open_at));
                }
                if (request.attributes != null) {
                    request.attributes.forEach(attribute -> parameters.put("attributes", attribute.attribute()));
                }

                return parameters;
            },
            SimpleEndpoint.emptyMap(),
            false,
            SearchBusinessResponse._DESERIALIZER);

}
