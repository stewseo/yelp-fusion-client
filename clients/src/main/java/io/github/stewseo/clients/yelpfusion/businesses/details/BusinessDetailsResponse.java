package io.github.stewseo.clients.yelpfusion.businesses.details;

import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.util.WithJsonObjectBuilderBase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;

import java.util.function.Function;

@JsonpDeserializable
public class BusinessDetailsResponse implements JsonpSerializable {

    // ------------------------------ Fields ------------------------------------ //
    private final BusinessDetails result;

    // ------------------------------ Constructor ------------------------------------ //
    private BusinessDetailsResponse(Builder builder) {
        this.result = builder.result;
    }

    public static BusinessDetailsResponse of(Function<Builder, ObjectBuilder<BusinessDetailsResponse>> fn) {
        return fn.apply(new Builder()).build();
    }

    // ------------------------------ Methods ------------------------------------ //
    public final BusinessDetails result() {
        return this.result;
    }


    // ------------------------------ Serialize ------------------------------------ //

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        if(this.result != null) {
            generator.writeKey("businesses");
            this.result.serialize(generator, mapper);
        }
    }

    // ------------------------------ Deserializer ------------------------------------ //

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    public static class Builder extends WithJsonObjectBuilderBase<Builder>
            implements
            ObjectBuilder<BusinessDetailsResponse> {

        private BusinessDetails result;

        public final Builder result(BusinessDetails business) {
            this.result = business;
            return this;
        }

        public final Builder result(Function<BusinessDetails.Builder, ObjectBuilder<BusinessDetails>> fn) {
            return result(fn.apply(new BusinessDetails.Builder()).build());
        }

        @Override
        public Builder withJson(JsonParser parser, JsonpMapper mapper) {

            BusinessDetails value = BusinessDetails._DESERIALIZER.deserialize(parser, mapper);

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


    public static final JsonpDeserializer<BusinessDetailsResponse> _DESERIALIZER = createBusinessDetailsResponse_Deserializer();

    protected static JsonpDeserializer<BusinessDetailsResponse> createBusinessDetailsResponse_Deserializer() {
        JsonpDeserializer<BusinessDetails> valueDeserializer = BusinessDetails._DESERIALIZER;

        return JsonpDeserializer.of(valueDeserializer.acceptedEvents(), (parser, mapper, event) -> new Builder()
                .result(valueDeserializer.deserialize(parser, mapper, event)).build());
    }

}
