package io.github.stewseo.clients.yelpfusion.business.search;


import io.github.stewseo.clients.yelpfusion._types.SearchBusiness;
import io.github.stewseo.clients.yelpfusion._types.SearchBusinessRegion;
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

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Function;


@JsonpDeserializable
public class SearchBusinessResponse implements JsonpSerializable {

    private final List<SearchBusiness> businesses;
    @Nullable
    private final Integer total;
    @Nullable
    private final SearchBusinessRegion searchBusinessRegion;

    protected SearchBusinessResponse(Builder builder) {
        this.businesses = ApiTypeHelper.unmodifiableRequired(builder.businesses, this, "businesses");
        this.total = builder.total;
        this.searchBusinessRegion = builder.searchBusinessRegion;
    }

    public List<SearchBusiness> businesses() {
        return this.businesses;
    }

    public Integer total() {
        return this.total;
    }

    public SearchBusinessRegion region() {
        return this.searchBusinessRegion;
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
        if (this.searchBusinessRegion != null) {
            generator.writeKey("region");
            searchBusinessRegion.serialize(generator, mapper);
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
        private SearchBusinessRegion searchBusinessRegion;

        public final Builder total(@Nullable Integer value) {
            this.total = value;
            return this;
        }

        public final Builder region(@Nullable SearchBusinessRegion value) {
            this.searchBusinessRegion = value;
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
        op.add(Builder::region, SearchBusinessRegion._DESERIALIZER, "region");

    }

}
