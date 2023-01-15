package io.github.stewseo.clients.yelpfusion.businesses.search;

import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.JsonpSerializer;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ApiTypeHelper;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.util.WithJsonObjectBuilderBase;
import io.github.stewseo.clients.yelpfusion._types.Region;
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Function;

public abstract class ResponseBody<ResultT> implements JsonpSerializable {

    private final List<Hit<ResultT>> hits;

    private final JsonpSerializer<ResultT> tDocumentSerializer;

    private final Integer total;

    @Nullable
    private final Region region;

    protected ResponseBody(AbstractBuilder<ResultT, ?> builder) {
        this.hits = ApiTypeHelper.unmodifiableRequired(builder.hits, this, "businesses");
        this.total = ApiTypeHelper.requireNonNull(builder.total, this, "total");
        this.tDocumentSerializer = builder.tDocumentSerializer;
        this.region = builder.region;
    }
    
    public final List<Hit<ResultT>> hits() {
        return hits;
    }

    public final Integer total() {
        return total;
    }

    public final Region region() {
        return region;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        if (ApiTypeHelper.isDefined(this.hits)) {
            generator.writeKey("hits");
            generator.writeStartArray();
            for (Hit<ResultT> item0 : this.hits) {
                item0.serialize(generator, mapper);

            }
            generator.writeEnd();

        }

        generator.writeKey("total");
        generator.write(this.total);

        if (region != null) {
            generator.writeKey("region");
            this.region.serialize(generator, mapper);
        }
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    protected abstract static class AbstractBuilder<ResultT, BuilderT extends AbstractBuilder<ResultT, BuilderT>>
            extends
            WithJsonObjectBuilderBase<BuilderT> {

        private List<Hit<ResultT>> hits;

        private JsonpSerializer<ResultT> tDocumentSerializer;

        private Integer total;
        @Nullable
        private Region region;


        public final BuilderT hits(List<Hit<ResultT>> list) {
            this.hits = _listAddAll(this.hits, list);
            return self();
        }

        @SafeVarargs
        public final BuilderT hits(Hit<ResultT> value, Hit<ResultT>... values) {
            this.hits = _listAdd(this.hits, value, values);
            return self();
        }

        public final BuilderT hits(
                Function<Hit.Builder<ResultT>, ObjectBuilder<Hit<ResultT>>> fn) {
            return this.hits(fn.apply(new Hit.Builder<ResultT>()).build());
        }

        public final BuilderT tDocumentSerializer(JsonpSerializer<ResultT> value) {
            this.tDocumentSerializer = value;
            return self();
        }

        public final BuilderT total(Integer value) {
            this.total = value;
            return self();
        }

        @Nullable
        public final BuilderT region(@Nullable Region value) {
            this.region = value;
            return self();
        }
        @Nullable
        public final BuilderT region(
                Function<Region.Builder, ObjectBuilder<Region>> fn) {
            return region(fn.apply(new Region.Builder()).build());
        }


        protected abstract BuilderT self();

    }

    protected static <TDocument, BuilderT extends AbstractBuilder<TDocument, BuilderT>>
        void setupResponseBodyDeserializer(
                ObjectDeserializer<BuilderT> op, 
                JsonpDeserializer<TDocument> tDocumentDeserializer) {

        op.add(AbstractBuilder::hits, JsonpDeserializer.arrayDeserializer(
                Hit.createHitDeserializer(tDocumentDeserializer)), "businesses");

        op.add(AbstractBuilder::total, JsonpDeserializer.integerDeserializer(), "total");

        op.add(AbstractBuilder::region, Region._DESERIALIZER, "region");
    }
    
    
}
