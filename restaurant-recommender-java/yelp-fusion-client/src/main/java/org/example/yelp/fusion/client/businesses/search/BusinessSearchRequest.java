package org.example.yelp.fusion.client.businesses.search;

import jakarta.json.stream.*;
import org.example.elasticsearch.client._types.RequestBase;
import org.example.elasticsearch.client.json.JsonData;
import org.example.elasticsearch.client.json.JsonpDeserializer;
import org.example.elasticsearch.client.json.JsonpMapper;
import org.example.elasticsearch.client.json.JsonpSerializable;
import org.example.elasticsearch.client.json.JsonpUtils;
import org.example.elasticsearch.client.json.ObjectBuilderDeserializer;
import org.example.elasticsearch.client.json.ObjectDeserializer;
import org.example.elasticsearch.client.transport.endpoints.*;
import org.example.elasticsearch.client.util.*;

import java.util.*;
import java.util.function.*;

// Build a BusinessSearchRequest for the business search endpoint
public class BusinessSearchRequest extends RequestBase implements JsonpSerializable {
    private final String id;
    private final List<String> location;
    private final Double latitude;
    private final Double longitude;
    private final String radius;
    private final String sort_by;

    
    private final Integer limit;
    
    private final Integer offset;
    private final String price;
    
    private final String open_at;

    
    private final Boolean open_now;

    
    private final List<String> categories;
    
    private final List<String> attributes;

    
    private final List<String> terms;

    private BusinessSearchRequest(Builder builder){
        this.id = builder.id; // not business id
        this.location = builder.location; // location or latitude/longitude is the only required field
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
        this.radius = builder.radius;
        this.limit = builder.limit;
        this.offset = builder.offset;
        this.sort_by = builder.sort_by;
        this.categories = builder.categories; // update categories and stored business ids

        // TODO: Create terms and attributes methods that will update a json file containing accurate up-to-date terms and attributes.
        this.terms = builder.terms;
        this.attributes = builder.attributes;

        this.price = builder.price;
        this.open_at = builder.open_at;
        this.open_now = builder.open_now;
    }


    public static BusinessSearchRequest of(Function<Builder, ObjectBuilder<BusinessSearchRequest>> fn) {
        return fn.apply(new Builder()).build();
    }

    // methods for
    public final String id() {
        return this.id;
    }
    public final List<String> location() {
        return this.location;
    }
    public final List<String> terms() {
        return this.terms;
    }
    public final Double latitude() {
        return this.latitude;
    }
    public final Double longitude() {
        return this.longitude;
    }
    public final String radius() {
        return this.radius;
    }
    public final String sort_by() {return this.sort_by;}
    public final Integer limit() {
        return this.limit;
    }
    public final Integer offset() {
        return this.offset;
    }
    public final List<String> categories() {
        return this.categories;
    }
    public final Boolean open_now() {
        return this.open_now;
    }
    public final String price() {
        return this.price;
    }
    public final String open_at() {
        return this.open_at;
    }

    public final List<String> attributes() {
        return this.attributes();
    }

    // -----------------------------------------------------------------------------------------------------------------

    public void serialize(JsonGenerator generator,JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {


        if (this.longitude != null) {
            generator.writeKey("_source");

        }
        if (this.latitude != null) {
            generator.writeKey("latitude");
        }

        if (this.radius != null) {
            generator.writeKey("radius");

        }

        if (this.sort_by != null) {
            generator.writeKey("sort_by");
        }

        if (this.limit != null) {
            generator.writeKey("limit");
        }

        if (this.open_at != null) {
            generator.writeKey("open_at");

        }

        if (this.price != null) {
            generator.writeKey("price");

        }

        if (this.offset != null) {
            generator.writeKey("offset");

        }

        if (this.price != null) {
            generator.writeKey("price");
        }

        if (ApiTypeHelper.isDefined(this.location)) {
            generator.writeKey("location");
            generator.writeStartArray();
            for (String item0 : this.location) {
                generator.write(item0);

            }
            generator.writeEnd();

        }
        if (ApiTypeHelper.isDefined(this.attributes)) {
            generator.writeKey("attributes");
            generator.writeStartArray();
            for (String item0 : this.attributes) {
                generator.write(item0);

            }
            generator.writeEnd();

        }
        if (ApiTypeHelper.isDefined(this.terms)) {
            generator.writeKey("terms");
            generator.writeStartArray();
            for (String item0 : this.terms) {
                generator.write(item0);

            }
            generator.writeEnd();

        }
        if (ApiTypeHelper.isDefined(this.categories)) {
            generator.writeKey("categories");
            generator.writeStartArray();
            for (String item0 : this.categories) {
                generator.write(item0);

            }
            generator.writeEnd();

        }

    }


        // Builder for BusinessSearchRequest

    public static class Builder extends WithJsonObjectBuilderBase<Builder> implements ObjectBuilder<BusinessSearchRequest> {
        private String id;
        private List<String> location;

        
        private List<String> categories;

        
        private List<String> attributes;

        
        private List<String> terms;

        private Double latitude;
        private Double longitude;
        private String radius;
        private String sort_by;
        
        private Integer limit;
        
        private Integer offset;
        private String price;
        
        private String open_at;

        
        private Boolean open_now;



        public Builder() {
        }



        public final Builder id(String id) {
            this.id = id;
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

        public final Builder radius(String radius) {
            this.radius = radius;
            return this;
        }

        public final Builder sort_by(String sort_by) {
            this.sort_by = sort_by;
            return this;
        }

        public final Builder offset(int offset) {
            this.offset = offset;
            return this;
        }
        public final Builder limit(int limit) {
            this.limit = limit;
            return this;
        }

        public final Builder open_now(boolean open_now) {
            this.open_now = open_now;
            return this;
        }

        public final Builder price(String price) {
            this.price = price;
            return this;
        }

        public final Builder open_at(String open_at) {
            this.open_at = open_at;
            return this;
        }


        public final Builder categories(List<String> list) {
            this.categories = _listAddAll(this.categories, list);
            return this;
        }

        public final Builder categories(String value, String... values) {
            this.categories = _listAdd(this.categories, value, values);
            return this;
        }

        public final Builder terms(List<String> list) {
            this.categories = _listAddAll(this.categories, list);
            return this;
        }
        public final Builder terms(String value, String... values) {
            this.terms = _listAdd(this.terms, value, values);
            return this;
        }
        public final Builder attributes(List<String> list) {
            this.attributes = _listAddAll(this.attributes, list);
            return this;
        }

        public final Builder attributes(String value, String... values) {
            this.attributes = _listAdd(this.attributes, value, values);
            return this;
        }

        public final Builder location(List<String> list) {
            this.location = _listAddAll(this.location, list);
            return this;
        }
        public final Builder location(String value, String... values) {
            this.location = _listAdd(this.location, value, values);
            return this;
        }


        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public BusinessSearchRequest build() {
            _checkSingleUse();
            return new BusinessSearchRequest(this);
        }


    }

    //Json deserializer for BusinessSearchRequest

    public static final JsonpDeserializer<BusinessSearchRequest> _DESERIALIZER =
            ObjectBuilderDeserializer.lazy(BusinessSearchRequest.Builder::new,
                    BusinessSearchRequest::setupSearchRequestDeserializer);

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }
    protected static void setupSearchRequestDeserializer(ObjectDeserializer<Builder> op) {
        op.add(BusinessSearchRequest.Builder::location, JsonpDeserializer.stringDeserializer(), "location");    // required field
        op.add(BusinessSearchRequest.Builder::latitude, JsonpDeserializer.doubleDeserializer(), "latitude");
        op.add(BusinessSearchRequest.Builder::longitude, JsonpDeserializer.doubleDeserializer(), "longitude");

        op.add(BusinessSearchRequest.Builder::radius, JsonpDeserializer.stringDeserializer(), "radius");
        op.add(BusinessSearchRequest.Builder::terms, JsonpDeserializer.arrayDeserializer(JsonpDeserializer.stringDeserializer()), "term");
        op.add(BusinessSearchRequest.Builder::categories, JsonpDeserializer.arrayDeserializer(JsonpDeserializer.stringDeserializer()), "categories");
        op.add(BusinessSearchRequest.Builder::attributes, JsonpDeserializer.arrayDeserializer(JsonpDeserializer.stringDeserializer()), "attributes");

        op.add(BusinessSearchRequest.Builder::limit, JsonpDeserializer.integerDeserializer(), "limit");
        op.add(BusinessSearchRequest.Builder::offset, JsonpDeserializer.integerDeserializer(), "offset");
        op.add(BusinessSearchRequest.Builder::sort_by, JsonpDeserializer.stringDeserializer(), "sort_by");
        op.add(BusinessSearchRequest.Builder::price, JsonpDeserializer.stringDeserializer(), "price");
        op.add(BusinessSearchRequest.Builder::open_now, JsonpDeserializer.booleanDeserializer(), "open_now");
        op.add(BusinessSearchRequest.Builder::open_at, JsonpDeserializer.stringDeserializer(), "open_at");
    }


    public static final SimpleEndpoint<BusinessSearchRequest, ?> _ENDPOINT = new SimpleEndpoint<>("businesses/search",
            request -> {
                return "GET";
            },
            // Request path
            request -> {
                StringBuilder buf = new StringBuilder();
                buf.append("/businesses/search");
                return buf.toString();
            },
            // Request parameters
            request -> {
                Map<String, String> parameters = new HashMap<>();
                if (request.location != null) {
                    request.location.forEach(location -> parameters.put("location", location));
                }
                if (request.terms != null) {
                    request.terms.forEach(term -> parameters.put("term", term));
                }
                if (request.categories != null) {
                    request.categories.forEach(category -> parameters.put("categories", category));
                }
                if (request.attributes != null) {
                    request.attributes.forEach(attribute -> parameters.put("attribute", attribute));
                }
                if (request.latitude != null) {
                    parameters.put("latitude", String.valueOf(request.latitude));
                }
                if (request.longitude != null) {
                    parameters.put("longitude", String.valueOf(request.longitude));
                }
                if (request.radius != null) {
                    parameters.put("radius", String.valueOf(request.radius));
                }
                if (request.offset != 0) {
                    parameters.put("offset", String.valueOf(request.offset));
                }
                if (request.limit != 0) {
                    parameters.put("limit", String.valueOf(request.limit));
                }
                if (request.sort_by != null) {
                    parameters.put("sort_by", request.sort_by);
                }

                return parameters;

            }, SimpleEndpoint.emptyMap(), true, BusinessSearchResponse._DESERIALIZER);
}

