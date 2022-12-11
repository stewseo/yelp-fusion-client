package io.github.stewseo.yelp.fusion.client.yelpfusion.business.search;


import io.github.stewseo.yelp.fusion.client.json.*;
import io.github.stewseo.yelp.fusion.client.util.ObjectBuilder;
import io.github.stewseo.yelp.fusion.client.util.WithJsonObjectBuilderBase;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.Region;

import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Function;


@JsonpDeserializable
public class BusinessSearchResponse implements JsonpSerializable {

    private final List<BusinessSearch> businesses;
    @Nullable
    private final Integer total;
    @Nullable
    private final Region region;

    public List<BusinessSearch> businesses() {
        return this.businesses;
    }

    public Integer total() {
        return this.total;
    }

    public Region region() {
        return this.region;
    }

    protected BusinessSearchResponse(Builder builder) {
        this.businesses = builder.businesses;
        this.total = builder.total;
        this.region = builder.region;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {
        if(this.businesses != null) {
            generator.writeKey("businesses");
            for(BusinessSearch business: businesses) {
                business.serialize(generator, mapper);
            }
        }
        if (this.total != null) {
            generator.writeKey("total");
            generator.write(this.total);
        }
        if (this.region != null) {
            generator.writeKey("region");
            region.serialize(generator, mapper);
        }
    }

    public String toString() {
        return JsonpUtils.toString(this);
    }


    public static class Builder extends WithJsonObjectBuilderBase<Builder>
            implements
            ObjectBuilder<BusinessSearchResponse> {

        private List<BusinessSearch> businesses;
        @Nullable
        private Integer total;
        @Nullable
        private Region region;

        public final Builder total(@Nullable Integer value) {
            this.total = value;
            return this;
        }
        public final Builder region(@Nullable Region value) {
            this.region = value;
            return this;
        }

        public final Builder businesses(List<BusinessSearch> list) {
            this.businesses = _listAddAll(this.businesses, list);
            return this;
        }

        public final Builder businesses(BusinessSearch value, BusinessSearch... values) {
            this.businesses = _listAdd(this.businesses, value, values);
            return this;
        }

        public final Builder businesses(Function<BusinessSearch.Builder, ObjectBuilder<BusinessSearch>> fn) {
            return businesses(fn.apply(new BusinessSearch.Builder()).build());
        }

        @Override
        protected BusinessSearchResponse.Builder self() {
            return this;
        }

        public BusinessSearchResponse build() {
            _checkSingleUse();

            return new BusinessSearchResponse(this);
        }
    }

    public static final JsonpDeserializer<BusinessSearchResponse> _DESERIALIZER = ObjectBuilderDeserializer.lazy(BusinessSearchResponse.Builder::new,
            BusinessSearchResponse::setupBusinessSearchResponse_Deserializer);

    protected static void setupBusinessSearchResponse_Deserializer(ObjectDeserializer<Builder> op) {

        op.add(Builder::total,JsonpDeserializer.integerDeserializer(), "total");
        op.add(Builder::businesses, JsonpDeserializer.arrayDeserializer(BusinessSearch._DESERIALIZER), "businesses");
        op.add(Builder::region, Region._DESERIALIZER, "region");

    }


}
