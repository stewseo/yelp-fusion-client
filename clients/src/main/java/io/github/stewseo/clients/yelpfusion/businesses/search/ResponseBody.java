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

import java.util.List;
import java.util.function.Function;

public abstract class ResponseBody<TDocument> implements JsonpSerializable {

    private final List<Hit<TDocument>> hits;

    private final JsonpSerializer<TDocument> tDocumentSerializer;

    private final Integer total;

    private final Region region;

    protected ResponseBody(AbstractBuilder<TDocument, ?> builder) {
        this.hits = ApiTypeHelper.unmodifiableRequired(builder.hits, this, "hits");
        this.tDocumentSerializer = builder.tDocumentSerializer;
        this.total = ApiTypeHelper.requireNonNull(builder.total, this, "total");
        this.region = ApiTypeHelper.requireNonNull(builder.region, this, "region");
    }

    public final List<Hit<TDocument>> hits() {
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
            for (Hit<TDocument> item0 : this.hits) {
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

    protected abstract static class AbstractBuilder<TDocument, BuilderT extends AbstractBuilder<TDocument, BuilderT>>
            extends
            WithJsonObjectBuilderBase<BuilderT> {

        private List<Hit<TDocument>> hits;

        private JsonpSerializer<TDocument> tDocumentSerializer;

        private Integer total;

        private Region region;


        public final BuilderT hits(List<Hit<TDocument>> list) {
            this.hits = _listAddAll(this.hits, list);
            return self();
        }

        @SafeVarargs
        public final BuilderT hits(Hit<TDocument> value, Hit<TDocument>... values) {
            this.hits = _listAdd(this.hits, value, values);
            return self();
        }

        public final BuilderT hits(
                Function<Hit.Builder<TDocument>, ObjectBuilder<Hit<TDocument>>> fn) {
            return this.hits(fn.apply(new Hit.Builder<TDocument>()).build());
        }

        public final BuilderT tDocumentSerializer(JsonpSerializer<TDocument> value) {
            this.tDocumentSerializer = value;
            return self();
        }

        public final BuilderT total(Integer value) {
            this.total = value;
            return self();
        }

        public final BuilderT region(Region value) {
            this.region = value;
            return self();
        }

        protected abstract BuilderT self();

    }

    protected static <TDocument, BuilderT extends AbstractBuilder<TDocument, BuilderT>>
        void setupResponseBodyDeserializer(
                ObjectDeserializer<BuilderT> op, JsonpDeserializer<TDocument> tDocumentDeserializer) {

        op.add(AbstractBuilder::hits, JsonpDeserializer.arrayDeserializer(
                Hit.createHitDeserializer(tDocumentDeserializer)), "hits");

        op.add(AbstractBuilder::total, JsonpDeserializer.integerDeserializer(), "total");

        op.add(AbstractBuilder::region, Region._DESERIALIZER, "region");
    }
}
