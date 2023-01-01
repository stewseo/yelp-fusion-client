package io.github.stewseo.client.yelpfusion.business.search;


import io.github.stewseo.client.json.JsonpDeserializable;
import io.github.stewseo.client.json.JsonpDeserializer;
import io.github.stewseo.client.json.JsonpMapper;
import io.github.stewseo.client.json.JsonpSerializable;
import io.github.stewseo.client.json.JsonpUtils;
import io.github.stewseo.client.json.ObjectBuilderDeserializer;
import io.github.stewseo.client.json.ObjectDeserializer;
import io.github.stewseo.client.util.ApiTypeHelper;
import io.github.stewseo.client.util.ObjectBuilder;
import io.github.stewseo.client.util.WithJsonObjectBuilderBase;
import io.github.stewseo.client.yelpfusion.business.Region;
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Function;


@JsonpDeserializable
public class SearchBusinessResponse implements JsonpSerializable {

    private final List<SearchBusiness> businesses;
    @Nullable
    private final Integer total;
    @Nullable
    private final Region region;

    protected SearchBusinessResponse(Builder builder) {
        this.businesses = ApiTypeHelper.unmodifiableRequired(builder.businesses, this, "businesses");
        this.total = builder.total;
        this.region = builder.region;
    }

    public List<SearchBusiness> businesses() {
        return this.businesses;
    }

    public Integer total() {
        return this.total;
    }

    public Region region() {
        return this.region;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        if (ApiTypeHelper.isDefined(this.businesses)) {
            generator.writeKey("businesses");
            generator.writeStartArray();
            for (SearchBusiness business : businesses) {
                business.serialize(generator, mapper);
            }
            generator.writeEnd();
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
            ObjectBuilder<SearchBusinessResponse> {

        private List<SearchBusiness> businesses;
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

        public final Builder businesses(List<SearchBusiness> list) {
            this.businesses = _listAddAll(this.businesses, list);
            return this;
        }

        public final Builder businesses(SearchBusiness value, SearchBusiness... values) {
            this.businesses = _listAdd(this.businesses, value, values);
            return this;
        }

        public final Builder businesses(Function<SearchBusiness.Builder, ObjectBuilder<SearchBusiness>> fn) {
            return businesses(fn.apply(new SearchBusiness.Builder()).build());
        }

        @Override
        protected Builder self() {
            return this;
        }

        public SearchBusinessResponse build() {
            _checkSingleUse();

            return new SearchBusinessResponse(this);
        }
    }


    public static final JsonpDeserializer<SearchBusinessResponse> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
            SearchBusinessResponse::setupBusinessSearchResponse_Deserializer);

    protected static void setupBusinessSearchResponse_Deserializer(ObjectDeserializer<Builder> op) {
        op.add(Builder::total, JsonpDeserializer.integerDeserializer(), "total");
        op.add(Builder::businesses, JsonpDeserializer.arrayDeserializer(SearchBusiness._DESERIALIZER), "businesses");
        op.add(Builder::region, Region._DESERIALIZER, "region");

    }

}
