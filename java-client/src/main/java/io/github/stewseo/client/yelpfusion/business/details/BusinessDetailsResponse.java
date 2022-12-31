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

import java.util.function.Function;

@JsonpDeserializable
public class BusinessDetailsResponse implements JsonpSerializable {
    public static final JsonpDeserializer<BusinessDetailsResponse> _DESERIALIZER = createBusinessDetailsResponse_Deserializer();
    // ------------------------------ Fields ------------------------------------ //
    private final Business result;

    // ------------------------------ Constructor ------------------------------------ //
    private BusinessDetailsResponse(Builder builder) {
        this.result = builder.result;
    }

    public static BusinessDetailsResponse of(Function<Builder, ObjectBuilder<BusinessDetailsResponse>> fn) {
        return fn.apply(new Builder()).build();
    }

    protected static JsonpDeserializer<BusinessDetailsResponse> createBusinessDetailsResponse_Deserializer() {
        JsonpDeserializer<Business> valueDeserializer = Business._DESERIALIZER;

        return JsonpDeserializer.of(valueDeserializer.acceptedEvents(), (parser, mapper, event) -> new Builder()
                .result(valueDeserializer.deserialize(parser, mapper, event)).build());
    }

    // ------------------------------ Methods ------------------------------------ //
    public final Business result() {
        return this.result;
    }


    // ------------------------------ Builder ------------------------------------ //

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeKey("businesses");
        this.result.serialize(generator, mapper);
        generator.writeEnd();
    }

    // ------------------------------ Deserializer ------------------------------------ //

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

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
}
