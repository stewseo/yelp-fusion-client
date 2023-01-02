package io.github.stewseo.client.yelpfusion.business.match;

import io.github.stewseo.client.transport.endpoints.SimpleEndpoint;
import io.github.stewseo.client._types.RequestBase;
import io.github.stewseo.client.json.JsonpDeserializable;
import io.github.stewseo.client.json.JsonpMapper;
import io.github.stewseo.client.json.JsonpSerializable;
import io.github.stewseo.client.json.JsonpUtils;
import io.github.stewseo.client.util.ObjectBuilder;
import jakarta.json.stream.JsonGenerator;

import java.util.HashMap;
import java.util.function.Function;

@JsonpDeserializable
public class BusinessMatchRequest extends RequestBase implements JsonpSerializable {

    private final String name;
    private final String address1;
    private final String address2;
    private final String address3;
    private final String city;
    private final String state;
    private final String country;
    private final String postal_code;
    private final Double latitude;
    private final Double longitude;
    private final String phone;
    private final Integer limit;
    private final String match_threshold;

    private BusinessMatchRequest(Builder builder) {
        this.name = builder.name;
        this.address1 = builder.address1;
        this.address2 = builder.address2;
        this.address3 = builder.address3;
        this.city = builder.city;
        this.state = builder.state;
        this.country = builder.country;
        this.postal_code = builder.postal_code;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
        this.phone = builder.phone;
        this.limit = builder.limit;
        this.match_threshold = builder.match_threshold;
    }

    public static BusinessMatchRequest of(Function<Builder, ObjectBuilder<BusinessMatchRequest>> fn) {
        return fn.apply(new Builder()).build();
    }

    public final String name() {
        return name;
    }

    public final String address1() {
        return address1;
    }

    public final String address2() {
        return address2;
    }

    public final String address3() {
        return address3;
    }

    public final String city() {
        return city;
    }

    public final String state() {
        return state;
    }

    public final String country() {
        return country;
    }

    public final String postal_code() {
        return postal_code;
    }

    public final Double latitude() {
        return latitude;
    }

    public final Double longitude() {
        return longitude;
    }

    public final String phone() {
        return phone;
    }

    public final Integer limit() {
        return limit;
    }

    public final String match_threshold() {
        return match_threshold;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        if (this.name != null) {
            generator.writeKey("name");
            generator.write(this.name);
        }
        if (this.address1 != null) {
            generator.writeKey("address1");
            generator.write(this.address1);
        }
        if (this.address2 != null) {
            generator.writeKey("address2");
            generator.write(this.address2);
        }
        if (this.address3 != null) {
            generator.writeKey("address3");
            generator.write(this.address3);
        }
        if (this.city != null) {
            generator.writeKey("city");
            generator.write(this.city);
        }
        if (this.state != null) {
            generator.writeKey("state");
            generator.write(this.state);
        }
        if (this.country != null) {
            generator.writeKey("country");
            generator.write(this.country);
        }
        if (this.postal_code != null) {
            generator.writeKey("postal_code");
            generator.write(this.postal_code);
        }
        if (this.latitude != null) {
            generator.writeKey("latitude");
            generator.write(this.latitude);
        }
        if (this.longitude != null) {
            generator.writeKey("longitude");
            generator.write(this.longitude);
        }
        if (this.phone != null) {
            generator.writeKey("phone");
            generator.write(this.phone);
        }
        if (this.limit != null) {
            generator.writeKey("limit");
            generator.write(this.limit);
        }
        if (this.match_threshold != null) {
            generator.writeKey("match_threshold");
            generator.write(this.match_threshold);
        }
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    public static class Builder extends AbstractBuilder<Builder>
            implements
            ObjectBuilder<BusinessMatchRequest> {
        private String name;
        private String address1;
        private String address2;
        private String address3;
        private String city;
        private String state;
        private String country;
        private String postal_code;
        private Double latitude;
        private Double longitude;
        private String phone;
        private Integer limit;
        private String match_threshold;

        public final Builder name(String name) {
            this.name = name;
            return this;
        }

        public final Builder address1(String address1) {
            this.address1 = address1;
            return this;
        }

        public final Builder address2(String address2) {
            this.address2 = address2;
            return this;
        }

        public final Builder address3(String address3) {
            this.address3 = address3;
            return this;
        }

        public final Builder city(String city) {
            this.city = city;
            return this;
        }

        public final Builder state(String state) {
            this.state = state;
            return this;
        }

        public final Builder postal_code(String address3) {
            this.postal_code = address3;
            return this;
        }

        public final Builder latitude(Double city) {
            this.latitude = city;
            return this;
        }

        public final Builder longitude(Double state) {
            this.longitude = state;
            return this;
        }

        public final Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public final Builder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        public final Builder country(String country) {
            this.country = country;
            return this;
        }

        public final Builder match_threshold(String match_threshold) {
            this.match_threshold = match_threshold;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public BusinessMatchRequest build() {
            _checkSingleUse();
            return new BusinessMatchRequest(this);
        }
    }

    public static final SimpleEndpoint<BusinessMatchRequest, ?> _ENDPOINT = new SimpleEndpoint<>("v3/businesses/matches",

            // Request method
            request -> "GET",

            request -> "v3/businesses/matches",

            // Request path
            request -> {
                HashMap<String, String> params = new HashMap<>();
                if (request.name() != null) {
                    params.put("name", request.name());
                }
                if (request.address1() != null) {
                    params.put("address1", request.address1());
                }
                if (request.address2() != null) {
                    params.put("address2", request.address2());
                }
                if (request.address3() != null) {
                    params.put("address3", request.address3());
                }
                if (request.city() != null) {
                    params.put("city", request.city());
                }
                if (request.country() != null) {
                    params.put("country", request.country());
                }
                if (request.state() != null) {
                    params.put("state", request.state());
                }

                if (request.latitude() != null) {
                    params.put("latitude", String.valueOf(request.latitude()));
                }
                if (request.longitude() != null) {
                    params.put("longitude", String.valueOf(request.longitude()));
                }

                if (request.phone() != null) {
                    params.put("phone", String.valueOf(request.phone()));
                }
                if (request.limit() != null) {
                    params.put("limit", String.valueOf(request.limit()));
                }

                if (request.match_threshold() != null) {
                    params.put("match_threshold", String.valueOf(request.match_threshold()));
                }

                return params;

            }, SimpleEndpoint.emptyMap(), false, BusinessMatchResponse._DESERIALIZER);


}
