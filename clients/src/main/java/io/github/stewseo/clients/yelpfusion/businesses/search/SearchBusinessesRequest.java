package io.github.stewseo.clients.yelpfusion.businesses.search;


import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.transport.endpoints.SimpleEndpoint;
import io.github.stewseo.clients.util.ObjectBuilder;
import jakarta.json.stream.JsonGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


public class SearchBusinessesRequest extends SearchBusinessesRequestBase {

    private SearchBusinessesRequest(Builder builder) {
        super(builder);
    }

    public static SearchBusinessesRequest of(Function<Builder, ObjectBuilder<SearchBusinessesRequest>> fn) {
        return fn.apply(new Builder()).build();
    }


    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        super.serializeInternal(generator, mapper);
    }

    public static class Builder extends SearchBusinessesRequestBase.AbstractBuilder<Builder>
                implements ObjectBuilder<SearchBusinessesRequest> {

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public SearchBusinessesRequest build() {
            _checkSingleUse();
            return new SearchBusinessesRequest(this);
        }

    }

    public static final JsonpDeserializer<SearchBusinessesRequest> _DESERIALIZER =
            ObjectBuilderDeserializer.lazy(Builder::new,
            SearchBusinessesRequest::setupSearchBusinessesRequestDeserializer);


    protected static void setupSearchBusinessesRequestDeserializer(ObjectDeserializer<Builder> op) {
        setupSearchBusinessesRequestBaseDeserializer(op);
    }

    public static final SimpleEndpoint<SearchBusinessesRequest, ?> _ENDPOINT = new SimpleEndpoint<>("v3/businesses/search",
            // Request method
            request -> "GET",

            // Request path
            request -> "v3/businesses/search",

            // Request parameters
            request -> {
                Map<String, String> parameters = new HashMap<>();

                Double latitude = request.longitude();
                if (latitude != null) {
                    parameters.put("latitude", String.valueOf(latitude));
                }
                Double longitude = request.longitude();
                if (longitude != null) {
                    parameters.put("longitude", String.valueOf(longitude));
                }
                if (request.term() != null) {
                    request.term().forEach(term -> parameters.put("term", term));
                }
                if (request.location() != null) {
                    parameters.put("location", request.location());
                }
                if (request.categories() != null) {
                    parameters.put("categories", request.categories().alias());
                }
                if (request.center() != null) {
                    Double lat = request.center().latitude();
                    if (latitude != null) {
                        parameters.put("latitude", String.valueOf(latitude));
                    }
                    Double lon = request.center().longitude();
                    if (longitude != null) {
                        parameters.put("longitude", String.valueOf(longitude));
                    }
                }
                if (request.radius() != null) {
                    parameters.put("radius", String.valueOf(request.radius()));
                }
                if (request.locale() != null) {
                    parameters.put("locale", String.valueOf(request.locale()));
                }
                if (request.limit() != null) {
                    parameters.put("limit", String.valueOf(request.limit()));
                }
                if (request.offset() != null) {
                    parameters.put("offset", String.valueOf(request.offset()));
                }
                if (request.sort_by() != null) {
                    parameters.put("sort_by", request.sort_by());
                }
                if (request.price() != null) {
                    parameters.put("price", String.valueOf(request.price()));
                }
                if (request.open_now() != null) {
                    parameters.put("open_now", String.valueOf(request.open_now()));
                }
                if (request.open_at() != null) {
                    parameters.put("open_at", String.valueOf(request.open_at()));
                }
                if (request.attributes() != null) {
                    request.attributes().forEach(attribute -> parameters.put("attributes", attribute.attribute()));
                }
                return parameters;

            }, SimpleEndpoint.emptyMap(), false, SearchResponse._DESERIALIZER);

}
