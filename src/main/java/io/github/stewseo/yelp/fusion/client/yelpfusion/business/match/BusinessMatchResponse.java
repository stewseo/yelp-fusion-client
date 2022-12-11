package io.github.stewseo.yelp.fusion.client.yelpfusion.business.match;

import io.github.stewseo.yelp.fusion.client.json.JsonpDeserializable;
import io.github.stewseo.yelp.fusion.client.json.JsonpDeserializer;
import io.github.stewseo.yelp.fusion.client.json.JsonpMapper;
import io.github.stewseo.yelp.fusion.client.json.JsonpSerializable;
import io.github.stewseo.yelp.fusion.client.json.ObjectBuilderDeserializer;
import io.github.stewseo.yelp.fusion.client.json.ObjectDeserializer;
import io.github.stewseo.yelp.fusion.client.util.ObjectBuilder;
import io.github.stewseo.yelp.fusion.client.util.WithJsonObjectBuilderBase;
import jakarta.json.stream.JsonGenerator;

import java.util.List;
import java.util.function.Function;

@JsonpDeserializable
public class BusinessMatchResponse implements JsonpSerializable {

    private final List<BusinessMatch> businesses;

    private BusinessMatchResponse(Builder builder) {
        this.businesses = builder.businesses;
    }

    public List<BusinessMatch> businesses() {
        return businesses;
    }


    public static BusinessMatchResponse of(Function<Builder, ObjectBuilder<BusinessMatchResponse>> fn) {
        return fn.apply(new Builder()).build();
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        if (this.businesses != null) {
            generator.writeKey("businesses");
            for(BusinessMatch bu : businesses) {
                bu.serialize(generator, mapper);
            }
        }

    }


    public static class Builder extends WithJsonObjectBuilderBase<BusinessMatchResponse.Builder>
            implements
            ObjectBuilder<BusinessMatchResponse> {
        private List<BusinessMatch> businesses;

        public Builder businesses(List<BusinessMatch> businesses) {
            this.businesses = _listAddAll(this.businesses, businesses);;
            return this;
        }

        public Builder businesses(BusinessMatch business, BusinessMatch ... businesses) {
            this.businesses = _listAdd(this.businesses, business, businesses);
            return this;
        }
        @Override
        protected BusinessMatchResponse.Builder self() {
            return this;
        }

        public BusinessMatchResponse build() {
            _checkSingleUse();
            return new BusinessMatchResponse(this);
        }
    }

    // ------------------------------ Deserializer ------------------------------------ //

    public static final JsonpDeserializer<BusinessMatchResponse> _DESERIALIZER =
            ObjectBuilderDeserializer.lazy(BusinessMatchResponse.Builder::new, BusinessMatchResponse::setupAutoCompleteDeserializer);

    protected static void setupAutoCompleteDeserializer(ObjectDeserializer<BusinessMatchResponse.Builder> op) {

        op.add(BusinessMatchResponse.Builder::businesses, JsonpDeserializer.arrayDeserializer(BusinessMatch._DESERIALIZER), "businesses");

    }
}
