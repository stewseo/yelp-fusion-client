package io.github.stewseo.client.yelpfusion.business.details;

import io.github.stewseo.client.json.JsonpDeserializable;
import io.github.stewseo.client.json.JsonpDeserializer;
import io.github.stewseo.client.json.JsonpMapper;
import io.github.stewseo.client.json.JsonpSerializable;
import io.github.stewseo.client.json.JsonpUtils;
import io.github.stewseo.client.util.ObjectBuilder;
import io.github.stewseo.client.util.WithJsonObjectBuilderBase;
import io.github.stewseo.client.yelpfusion.business.Business;

import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@JsonpDeserializable
public class BusinessDetailsResponse implements JsonpSerializable {
    // ------------------------------ Fields ------------------------------------ //
    private final Business result;

    // ------------------------------ Constructor ------------------------------------ //
    private BusinessDetailsResponse(Builder builder) {
        this.result = builder.result;
    }

    public static BusinessDetailsResponse of(Function<BusinessDetailsResponse.Builder, ObjectBuilder<BusinessDetailsResponse>> fn) {
        return fn.apply(new BusinessDetailsResponse.Builder()).build();
    }

    // ------------------------------ Methods ------------------------------------ //
    public final Business result() {
        return this.result;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeKey("businesses");
        this.result.serialize(generator, mapper);
        generator.writeEnd();
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }


    // ------------------------------ Builder ------------------------------------ //

    public static class Builder extends WithJsonObjectBuilderBase<Builder>
            implements
            ObjectBuilder<BusinessDetailsResponse> {

        private Business result;

        public final Builder result(Business business) {
            this.result = business;
            return this;
        }
        public final Builder result(Function<Business.Builder, ObjectBuilder<Business>> fn) {
            return result(fn.apply(new Business.Builder()).build());
        }

        @Override
        public Builder withJson(JsonParser parser, JsonpMapper mapper) {

            Business value = Business._DESERIALIZER.deserialize(parser, mapper);

            return this.result(value);
        }

        @Override
        protected Builder self() {
            return this;
        }

        public BusinessDetailsResponse build() {
            _checkSingleUse();
            return new BusinessDetailsResponse(this);
        }
    }

    // ------------------------------ Deserializer ------------------------------------ //

    public static final JsonpDeserializer<BusinessDetailsResponse> _DESERIALIZER = createBusinessDetailsResponse_Deserializer();
    protected static JsonpDeserializer<BusinessDetailsResponse> createBusinessDetailsResponse_Deserializer() {
        JsonpDeserializer<Business> valueDeserializer = Business._DESERIALIZER;

        return JsonpDeserializer.of(valueDeserializer.acceptedEvents(), (parser, mapper, event) -> new BusinessDetailsResponse.Builder()
                .result(valueDeserializer.deserialize(parser, mapper, event)).build());
    }
}
