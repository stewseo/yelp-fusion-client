package io.github.stewseo.clients.yelpfusion.businesses.match;

import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ApiTypeHelper;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.util.WithJsonObjectBuilderBase;
import jakarta.json.stream.JsonGenerator;

import java.util.List;
import java.util.function.Function;

@JsonpDeserializable
public class MatchBusinessesResponse implements JsonpSerializable {
    
    private final List<BusinessMatch> businesses;

    private MatchBusinessesResponse(Builder builder) {
        this.businesses = builder.businesses;
    }

    public static MatchBusinessesResponse of(Function<Builder, ObjectBuilder<MatchBusinessesResponse>> fn) {
        return fn.apply(new Builder()).build();
    }

    public final List<BusinessMatch> businesses() {
        return businesses;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    // ------------------------------ Deserializer ------------------------------------ //

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        if (ApiTypeHelper.isDefined(this.businesses)) {
            generator.writeKey("businesses");
            generator.writeStartArray();
            for(BusinessMatch item0: businesses) {
                item0.serialize(generator, mapper);
            }
            generator.writeEnd();
        }
    }

    public String toString() {
        return JsonpUtils.toString(this);
    }
    public static class Builder extends WithJsonObjectBuilderBase<Builder>
            implements
            ObjectBuilder<MatchBusinessesResponse> {
        private List<BusinessMatch> businesses;

        public final Builder businesses(List<BusinessMatch> businesses) {
            this.businesses = businesses;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        public MatchBusinessesResponse build() {
            _checkSingleUse();
            return new MatchBusinessesResponse(this);
        }
    }

    public static final JsonpDeserializer<MatchBusinessesResponse> _DESERIALIZER =
            ObjectBuilderDeserializer.lazy(Builder::new, MatchBusinessesResponse::setupBusinessResponseDeserializer);

    protected static void setupBusinessResponseDeserializer(ObjectDeserializer<Builder> op) {

        op.add(Builder::businesses, JsonpDeserializer.arrayDeserializer(BusinessMatch._DESERIALIZER), "businesses");

    }
}
