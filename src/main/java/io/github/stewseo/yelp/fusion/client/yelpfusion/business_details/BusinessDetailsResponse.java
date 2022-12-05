package io.github.stewseo.yelp.fusion.client.yelpfusion.business_details;

import io.github.stewseo.yelp.fusion.client.json.*;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.Business;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import io.github.stewseo.yelp.fusion.client.util.ObjectBuilder;
import io.github.stewseo.yelp.fusion.client.util.WithJsonObjectBuilderBase;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@JsonpDeserializable
public class BusinessDetailsResponse implements JsonpSerializable {
    // ------------------------------ Fields ------------------------------------ //
    private final List<Business> result;

    // ------------------------------ Constructor ------------------------------------ //
    private BusinessDetailsResponse(Builder builder) {
        this.result = builder.result;
    }

    public static BusinessDetailsResponse of(Function<BusinessDetailsResponse.Builder, ObjectBuilder<BusinessDetailsResponse>> fn) {
        return fn.apply(new BusinessDetailsResponse.Builder()).build();
    }

    // ------------------------------ Methods ------------------------------------ //
    public final List<Business> result() {
        return this.result;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        for (Business item0 : this.result) {
            item0.serialize(generator, mapper);
        }
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
        private List<Business> result = new ArrayList<>();

        public final Builder result(List<Business> list) {
            this.result = _listAddAll(this.result, list);
            return this;
        }

        public final Builder result(Business value) {
            this.result = _listAdd(this.result, value);
            return this;
        }

        public final Builder result(Function<Business.Builder, ObjectBuilder<Business>> fn) {
            return result(fn.apply(new Business.Builder()).build());
        }

        @Override
        public Builder withJson(JsonParser parser, JsonpMapper mapper) {

            List<Business> value = JsonpDeserializer
                    .arrayDeserializer(Business._DESERIALIZER).deserialize(parser, mapper);

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

        JsonpDeserializer<List<Business>> valueDeserializer = JsonpDeserializer
                .arrayDeserializer(Business._DESERIALIZER);

        return JsonpDeserializer.of(valueDeserializer.acceptedEvents(), (parser, mapper, event) -> new BusinessDetailsResponse.Builder()
                .result(valueDeserializer.deserialize(parser, mapper, event)).build());
    }
}
