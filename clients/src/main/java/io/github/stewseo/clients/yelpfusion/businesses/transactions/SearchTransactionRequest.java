package io.github.stewseo.clients.yelpfusion.businesses.transactions;

import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.transport.endpoints.SimpleEndpoint;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.yelpfusion._types.RequestBase;
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@JsonpDeserializable
public class SearchTransactionRequest extends RequestBase implements JsonpSerializable {

    private final String transaction_type;
    private final Double latitude;
    private final Double longitude;
    private final String location;
    private final String term;
    private final String categories;
    private final Integer price;

    private SearchTransactionRequest(Builder builder) {

        this.transaction_type = builder.transaction_type;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
        this.location = builder.location;
        this.term = builder.term;
        this.categories = builder.categories;
        this.price = builder.price;
    }

    public static SearchTransactionRequest of(Function<Builder, ObjectBuilder<SearchTransactionRequest>> fn) {
        return fn.apply(new Builder()).build();
    }

    public final String transaction_type() {
        return transaction_type;
    }

    public final Double latitude() {
        return latitude;
    }

    public final Double longitude() {
        return longitude;
    }

    public final String location() {
        return location;
    }

    public final String term() {
        return term;
    }

    public final Integer price() {
        return this.price;
    }

    public String categories() {
        return categories;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        if (this.categories != null) {
            generator.writeKey("categories");
            generator.write(this.categories);
        }
        if (this.term != null) {
            generator.writeKey("term");
            generator.write(this.term);
        }
        if (this.transaction_type != null) {
            generator.writeKey("transaction_type");
            generator.write(this.transaction_type);
        }
        if (this.latitude != null) {
            generator.writeKey("latitude");
            generator.write(this.latitude);
        }

        if (this.longitude != null) {
            generator.writeKey("longitude");
            generator.write(this.longitude);
        }

        if (this.price != null) {
            generator.writeKey("price");
            generator.write(this.price);
        }
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    public static class Builder extends AbstractBuilder<Builder> implements ObjectBuilder<SearchTransactionRequest> {
        private String transaction_type;
        private Double latitude;
        private Double longitude;
        private String location;
        private String term;
        private String categories;
        private Integer price;

        // setters

        public final Builder price(@Nullable Integer price) {
            this.price = price;
            return this;
        }
        public final Builder term(@Nullable String value) {
            this.term = value;
            return this;
        }

        public final Builder categories(@Nullable String categories) {
            this.categories = categories;
            return this;
        }

        public final Builder transaction_type(@Nullable String transaction_type) {
            this.transaction_type = transaction_type;
            return this;
        }

        public final Builder location(@Nullable String location) {
            this.location = location;
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

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public SearchTransactionRequest build() {
            _checkSingleUse();
            return new SearchTransactionRequest(this);
        }
    }


    public static final JsonpDeserializer<SearchTransactionRequest> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
            SearchTransactionRequest::setupSearchRequestDeserializer);

    protected static void setupSearchRequestDeserializer(ObjectDeserializer<Builder> op) {
        op.add(Builder::transaction_type, JsonpDeserializer.stringDeserializer(), "transaction_type");
        op.add(Builder::categories, JsonpDeserializer.stringDeserializer(), "categories");
        op.add(Builder::location, JsonpDeserializer.stringDeserializer(), "location");
        op.add(Builder::price, JsonpDeserializer.integerDeserializer(), "price");
        op.add(Builder::latitude, JsonpDeserializer.doubleDeserializer(), "latitude");
        op.add(Builder::longitude, JsonpDeserializer.doubleDeserializer(), "longitude");
    }


    // endpoint
    public static final SimpleEndpoint<SearchTransactionRequest, ?> _ENDPOINT = new SimpleEndpoint<>("v3/transactions",
            // Request method
            request -> "GET",
            // Request path
            request -> {
                if (request.transaction_type != null) {
                    return "v3/transactions" + "/" + request.transaction_type + "/search";
                }
                throw SimpleEndpoint.noPathTemplateFound("path");
            },
            // Request parameters
            request -> {

                Map<String, String> parameters = new HashMap<>();

                if (request.transaction_type != null) {
                    parameters.put("transaction_type", request.transaction_type);
                }
                if (request.term != null) {
                    parameters.put("term", request.term);
                }
                if (request.location != null) {
                    parameters.put("location", request.location);
                }
                if (request.categories != null) {
                    parameters.put("categories", request.categories);
                }
                if (request.price != null) {
                    parameters.put("price", String.valueOf(request.price));
                }
                if (request.latitude != null) {
                    parameters.put("latitude", String.valueOf(request.latitude));
                }
                if (request.longitude != null) {
                    parameters.put("longitude", String.valueOf(request.longitude));
                }

                return parameters;
            },
            SimpleEndpoint.emptyMap(),
            false,
            SearchTransactionResponse._DESERIALIZER);
}



