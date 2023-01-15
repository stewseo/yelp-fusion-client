package io.github.stewseo.clients.yelpfusion.events.search;

import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.transport.endpoints.SimpleEndpoint;
import io.github.stewseo.clients.util.ApiTypeHelper;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.yelpfusion._types.RequestBase;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchResponse;
import jakarta.json.stream.JsonGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class SearchEventsRequest extends RequestBase implements JsonpSerializable {

    private final String locale;
    private final String sort_by;
    private final String sort_on;
    private final String location;
    private final Integer offset;
    private final Integer limit;
    private final Integer start_date;
    private final Integer end_date;
    private final Integer radius;
    private final Double latitude;
    private final Double longitude;
    private final Boolean is_free;
    private final List<String> excluded_events;
    private final List<String> categories;

    private SearchEventsRequest(Builder builder) {
        this.locale = builder.locale;
        this.sort_by = builder.sort_by;
        this.sort_on = builder.sort_on;
        this.offset = builder.offset;
        this.limit = builder.limit;
        this.start_date = builder.start_date;
        this.end_date = builder.end_date;
        this.location = builder.location;
        this.radius = builder.radius;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
        this.is_free = builder.is_free;
        this.categories = ApiTypeHelper.unmodifiable(builder.categories);
        this.excluded_events = ApiTypeHelper.unmodifiable(builder.excluded_events);

    }

    public static SearchEventsRequest of(Function<Builder, ObjectBuilder<SearchEventsRequest>> fn) {
        return fn.apply(new Builder()).build();
    }

    public final String locale() {
        return locale;
    }

    public final String sort_by() {
        return sort_by;
    }

    public final String sort_on() {
        return sort_on;
    }

    public final String location() {
        return location;
    }

    public final Integer offset() {
        return offset;
    }

    public final Integer limit() {
        return limit;
    }

    public final Integer start_date() {
        return start_date;
    }

    public final Integer end_date() {
        return end_date;
    }

    public final Integer radius() {
        return radius;
    }

    public final Double latitude() {
        return latitude;
    }

    public final Double longitude() {
        return longitude;
    }

    public final Boolean is_free() {
        return is_free;
    }

    public final List<String> excluded_events() {
        return excluded_events;
    }

    public final List<String> categories() {
        return categories;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        if (ApiTypeHelper.isDefined(this.categories)) {
            generator.writeKey("categories");
            generator.writeStartArray();
            for (String item0 : this.categories) {
                generator.write(item0);
            }
            generator.writeEnd();
        }

        if (ApiTypeHelper.isDefined(this.excluded_events)) {
            generator.writeKey("excluded_events");
            generator.writeStartArray();
            for (String item0 : this.excluded_events) {
                generator.write(item0);
            }
            generator.writeEnd();
        }
        if (this.locale != null) {
            generator.writeKey("locale");
            generator.write(this.locale);
        }
        if (this.sort_by != null) {
            generator.writeKey("sort_by");
            generator.write(this.sort_by);
        }
        if (this.sort_on != null) {
            generator.writeKey("sort_on");
            generator.write(this.sort_on);
        }
        if (this.offset != null) {
            generator.writeKey("offset");
            generator.write(this.offset);
        }
        if (this.location != null) {
            generator.writeKey("location");
            generator.write(this.location);
        }
        if (this.limit != null) {
            generator.writeKey("limit");
            generator.write(this.limit);
        }
        if (this.start_date != null) {
            generator.writeKey("start_date");
            generator.write(this.start_date);
        }
        if (this.end_date != null) {
            generator.writeKey("end_date");
            generator.write(this.end_date);
        }
        if (this.radius != null) {
            generator.writeKey("radius");
            generator.write(this.radius);
        }
        if (this.latitude != null) {
            generator.writeKey("latitude");
            generator.write(this.latitude);
        }
        if (this.longitude != null) {
            generator.writeKey("longitude");
            generator.write(this.longitude);
        }

        if (this.is_free != null) {
            generator.writeKey("is_free");
            generator.write(this.is_free);
        }
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    public static class Builder extends AbstractBuilder<Builder>
            implements
            ObjectBuilder<SearchEventsRequest> {
        private String locale;
        private String sort_by;
        private String sort_on;
        private String location;
        private Integer offset;
        private Integer limit;
        private Integer start_date;
        private Integer end_date;
        private Integer radius;
        private Double latitude;
        private Double longitude;
        private Boolean is_free;
        private List<String> excluded_events;
        private List<String> categories;

        public final Builder locale(String value) {
            this.locale = value;
            return this;
        }

        public final Builder sort_by(String value) {
            this.sort_by = value;
            return this;
        }

        public final Builder sort_on(String value) {
            this.sort_on = value;
            return this;
        }

        public final Builder offset(Integer value) {
            this.offset = value;
            return this;
        }

        public final Builder location(String value) {
            this.location = value;
            return this;
        }

        public final Builder limit(Integer value) {
            this.limit = value;
            return this;
        }

        public final Builder start_date(Integer value) {
            this.start_date = value;
            return this;
        }

        public final Builder end_date(Integer value) {
            this.end_date = value;
            return this;
        }

        public final Builder radius(Integer value) {
            this.radius = value;
            return this;
        }

        public final Builder latitude(Double value) {
            this.latitude = value;
            return this;
        }

        public final Builder longitude(Double value) {
            this.longitude = value;
            return this;
        }

        public final Builder is_free(Boolean value) {
            this.is_free = value;
            return this;
        }

        public final Builder excluded_events(List<String> values) {
            this.excluded_events = _listAddAll(this.excluded_events, values);
            return this;
        }

        public final Builder excluded_events(String value, String values) {
            this.excluded_events = _listAdd(this.excluded_events, value, values);
            return this;
        }

        public final Builder categories(List<String> values) {
            this.categories = _listAddAll(this.categories, values);
            return this;
        }

        public final Builder categories(String value, String values) {
            this.categories = _listAdd(this.categories, value, values);
            return this;
        }


        @Override
        protected Builder self() {
            return this;
        }

        public SearchEventsRequest build() {
            _checkSingleUse();
            return new SearchEventsRequest(this);
        }
    }

    public static final SimpleEndpoint<SearchEventsRequest, ?> _ENDPOINT = new SimpleEndpoint<>("v3/events",

            // Request method
            request -> "GET",
            // Request path
            request -> "v3/events",
            // Query params
            request -> {
                HashMap<String, String> params = new HashMap<>();
                if (request.locale() != null) {
                    params.put("locale", request.locale());
                }
                if (request.sort_by() != null) {
                    params.put("sort_by", request.sort_by());
                }
                if (request.offset() != null) {
                    params.put("offset", String.valueOf(request.offset()));
                }
                if (request.location() != null) {
                    params.put("location", String.valueOf(request.location()));
                }
                if (request.limit() != null) {
                    params.put("limit", String.valueOf(request.limit()));
                }
                if (request.start_date() != null) {
                    params.put("start_date", String.valueOf(request.start_date()));
                }
                if (request.end_date() != null) {
                    params.put("end_date", String.valueOf(request.end_date()));
                }
                if (request.radius() != null) {
                    params.put("radius", String.valueOf(request.radius()));
                }
                if (request.latitude() != null) {
                    params.put("latitude", String.valueOf(request.latitude()));
                }
                if (request.longitude() != null) {
                    params.put("longitude", String.valueOf(request.longitude()));
                }
                if (request.is_free() != null) {
                    params.put("is_free", String.valueOf(request.is_free()));
                }
                return params;

            }, SimpleEndpoint.emptyMap(), false, SearchResponse._DESERIALIZER);
}
