package io.github.yelp.fusion.client.test_models;

import io.github.yelp.fusion.client.json.*;
import io.github.yelp.fusion.client.util.ObjectBuilder;
import io.github.yelp.fusion.client.util.WithJsonObjectBuilderBase;
import io.github.yelp.fusion.client.yelpfusion.business.Region;
import io.github.yelp.fusion.client.yelpfusion.core.HitsMetadata;
import jakarta.json.stream.JsonGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.util.function.Function;


public abstract class ResponseBody<TDocument> implements JsonpSerializable {

    private static final Logger logger = LoggerFactory.getLogger(ResponseBody.class);

    @Nullable
    private final JsonpSerializer<TDocument> tDocumentSerializer;
    private final HitsMetadata<TDocument> hits;
    @Nullable
    private final Integer total;
    @Nullable
    private final Region region;

    public HitsMetadata<TDocument> hits() {
        return this.hits;
    }

    public Integer total() {
        return this.total;
    }

    public Region region() {
        return this.region;
    }

    protected ResponseBody(AbstractBuilder<TDocument, ?> builder) {
        this.hits = builder.hits;
//        this.hits = ApiTypeHelper.requireNonNull(builder.hits, this, "hits");
        this.tDocumentSerializer = builder.tDocumentSerializer;
        this.total = builder.total;
        this.region = builder.region;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeKey("businesses");
        this.hits.serialize(generator, mapper);

        if (this.total != null) {
            generator.writeKey("total");
            generator.write(this.total);
        }
        if (this.region != null) {
            generator.writeKey("region");
            region.serialize(generator, mapper);
        }
    }


    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }


    protected abstract static class AbstractBuilder<TDocument, BuilderT extends AbstractBuilder<TDocument, BuilderT>>
            extends
            WithJsonObjectBuilderBase<BuilderT> {

//        private HitsMetadata<TDocument> hits;
        private HitsMetadata<TDocument> hits;

        @Nullable
        private JsonpSerializer<TDocument> tDocumentSerializer;

        private Integer total;
        private Region region;

        public final BuilderT total(Integer total) {
            this.total = total;
            return self();
        }
        public final BuilderT region(Region region) {
            this.region = region;
            return self();
        }

//        public final BuilderT hits(HitsMetadata<TDocument> list) {
//            this.hits = _listAddAll(this.hits, list);
//            return self();
//        }

//        public final BuilderT hits(Hit<TDocument> value, Hit<TDocument>... values) {
//            this.hits = _listAdd(this.hits, value, values);
//            return self();
//        }
//
//        public final BuilderT hits(Function<Hit.Builder<TDocument>, ObjectBuilder<Hit<TDocument>>> fn) {
//            return hits(fn.apply(new Hit.Builder<>()).build());
//        }
//
        public final BuilderT hits(HitsMetadata<TDocument> value) {
            this.hits = value;
            return self();
        }

        /**
         * Required - API name: {@code hits}
         */
        public final BuilderT hits(
                Function<HitsMetadata.Builder<TDocument>, ObjectBuilder<HitsMetadata<TDocument>>> fn) {
            return this.hits(fn.apply(new HitsMetadata.Builder<TDocument>()).build());
        }


        public final BuilderT tDocumentSerializer(@Nullable JsonpSerializer<TDocument> value) {
            this.tDocumentSerializer = value;
            return self();
        }

        protected abstract BuilderT self();
    }

    // ---------------------------------------------------------------------------------------------
    protected static <TDocument, BuilderT extends AbstractBuilder<TDocument, BuilderT>> void setupResponseBodyDeserializer(
            ObjectDeserializer<BuilderT> op, JsonpDeserializer<TDocument> tDocumentDeserializer) {

        // builder setter, JsonpDeseriazlier.Type, key name
        op.add(AbstractBuilder::hits, HitsMetadata.createHitsMetadataDeserializer(tDocumentDeserializer), "businesses");
//        op.add(AbstractBuilder::hits, JsonpDeserializer.arrayDeserializer(Hit.createHitDeserializer(tDocumentDeserializer)), "businesses");
        op.add(AbstractBuilder::total, JsonpDeserializer.integerDeserializer(), "total");
        op.add(AbstractBuilder::region, Region._DESERIALIZER, "region");

        // if none match add name to JsonArray, default to Business
    }

}
