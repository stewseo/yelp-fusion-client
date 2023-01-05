package io.github.stewseo.clients.yelpfusion.business.transactions;

import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ApiTypeHelper;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.util.WithJsonObjectBuilderBase;

import io.github.stewseo.clients.yelpfusion._types.SearchBusiness;
import io.github.stewseo.clients.yelpfusion.business.search.SearchBusinessRequest;
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Function;

public class SearchTransactionResponse implements JsonpSerializable {

    private final List<SearchBusiness> businesses;
    @Nullable
    private final Integer total;
    @Nullable
    private final Region region;

    private SearchTransactionResponse(Builder builder) {
        this.businesses = ApiTypeHelper.unmodifiable(builder.businesses);
        this.total = builder.total;
        this.region = builder.region;
    }


    public final List<SearchBusiness> businesses() {
        return this.businesses;
    }

    public final Integer total() {
        return this.total;
    }

    public final Region region() {
        return this.region;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {
        if (this.businesses != null) {
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
            ObjectBuilder<SearchTransactionResponse> {

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

        public SearchTransactionResponse build() {
            _checkSingleUse();

            return new SearchTransactionResponse(this);
        }
    }

    public static final JsonpDeserializer<SearchTransactionResponse> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
            SearchTransactionResponse::setupBusinessSearchResponse_Deserializer);

    protected static void setupBusinessSearchResponse_Deserializer(ObjectDeserializer<Builder> op) {
        op.add(Builder::total, JsonpDeserializer.integerDeserializer(), "total");
        op.add(Builder::businesses, JsonpDeserializer.arrayDeserializer(SearchBusiness._DESERIALIZER), "businesses");
        op.add(Builder::region, Region._DESERIALIZER, "region");
    }
}

