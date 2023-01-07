package io.github.stewseo.clients.yelpfusion.businesses.transactions;

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

import io.github.stewseo.clients.yelpfusion._types.Region;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessResult;
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Function;

@JsonpDeserializable
public class SearchTransactionResponse implements JsonpSerializable {

    private final List<SearchBusinessResult> businesses;

    @Nullable
    private final Integer total;

    @Nullable
    private final Region region;

    private SearchTransactionResponse(Builder builder) {
        this.businesses = ApiTypeHelper.unmodifiable(builder.businesses);
        this.total = builder.total;
        this.region = builder.region;
    }


    public final List<SearchBusinessResult> businesses() {
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
            for (SearchBusinessResult business : businesses) {
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

        private List<SearchBusinessResult> businesses;

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

        public final Builder businesses(List<SearchBusinessResult> list) {
            this.businesses = _listAddAll(this.businesses, list);
            return this;
        }

        public final Builder businesses(SearchBusinessResult value, SearchBusinessResult... values) {
            this.businesses = _listAdd(this.businesses, value, values);
            return this;
        }

        public final Builder businesses(Function<SearchBusinessResult.Builder, ObjectBuilder<SearchBusinessResult>> fn) {
            return businesses(fn.apply(new SearchBusinessResult.Builder()).build());
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
        op.add(Builder::businesses, JsonpDeserializer.arrayDeserializer(SearchBusinessResult._DESERIALIZER), "businesses");
        op.add(Builder::region, Region._DESERIALIZER, "region");
    }
}

