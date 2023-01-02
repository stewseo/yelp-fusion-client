package io.github.stewseo.client.yelpfusion.business.match;

import io.github.stewseo.client.json.JsonpDeserializable;
import io.github.stewseo.client.json.JsonpDeserializer;
import io.github.stewseo.client.json.JsonpMapper;
import io.github.stewseo.client.json.JsonpSerializable;
import io.github.stewseo.client.json.ObjectBuilderDeserializer;
import io.github.stewseo.client.json.ObjectDeserializer;
import io.github.stewseo.client.util.ObjectBuilder;
import io.github.stewseo.client.util.WithJsonObjectBuilderBase;
import jakarta.json.stream.JsonGenerator;

import java.util.function.Function;

@JsonpDeserializable
public class BusinessMatchResponse implements JsonpSerializable {

    public static final JsonpDeserializer<BusinessMatchResponse> _DESERIALIZER =
            ObjectBuilderDeserializer.lazy(Builder::new, BusinessMatchResponse::setupAutoCompleteDeserializer);
    private final BusinessMatch businesses;

    private BusinessMatchResponse(Builder builder) {
        this.businesses = builder.businesses;
    }

    public static BusinessMatchResponse of(Function<Builder, ObjectBuilder<BusinessMatchResponse>> fn) {
        return fn.apply(new Builder()).build();
    }

    protected static void setupAutoCompleteDeserializer(ObjectDeserializer<Builder> op) {

        op.add(Builder::businesses, BusinessMatch._DESERIALIZER, "businesses");

    }

    public final BusinessMatch businesses() {
        return businesses;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    // ------------------------------ Deserializer ------------------------------------ //

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        if (this.businesses != null) {
            generator.writeKey("businesses");
        }
    }


    public static class Builder extends WithJsonObjectBuilderBase<Builder>
            implements
            ObjectBuilder<BusinessMatchResponse> {
        private BusinessMatch businesses;

        public final Builder businesses(BusinessMatch businesses) {
            this.businesses = businesses;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        public BusinessMatchResponse build() {
            _checkSingleUse();
            return new BusinessMatchResponse(this);
        }
    }
}
