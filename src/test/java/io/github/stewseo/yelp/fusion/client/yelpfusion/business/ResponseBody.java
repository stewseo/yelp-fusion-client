package io.github.stewseo.yelp.fusion.client.yelpfusion.business;

import io.github.stewseo.yelp.fusion.client.json.*;
import io.github.stewseo.yelp.fusion.client.util.ObjectBuilder;
import io.github.stewseo.yelp.fusion.client.util.WithJsonObjectBuilderBase;
import jakarta.json.stream.JsonGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Function;


public abstract class ResponseBody<TDocument> implements JsonpSerializable {

    private static final Logger logger = LoggerFactory.getLogger(ResponseBody.class);

    @Nullable
    private final JsonpSerializer<TDocument> tDocumentSerializer;
    private final List<TDocument> hits;
    @Nullable
    private final Integer total;
    @Nullable
    private final Region region;

    public List<TDocument> hits() {
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
        private List<TDocument> hits;

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

        public final BuilderT hits(List<TDocument> value) {
            this.hits = _listAddAll(this.hits, value);
            return self();
        }
        public final BuilderT hits(TDocument value, TDocument values) {
            this.hits = _listAdd(this.hits, value, values);
            return self();
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

        op.add(AbstractBuilder::total, JsonpDeserializer.integerDeserializer(), "total");
        op.add(AbstractBuilder::region, Region._DESERIALIZER, "region");

    }

}
