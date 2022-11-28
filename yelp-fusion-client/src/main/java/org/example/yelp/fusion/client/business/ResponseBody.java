package org.example.yelp.fusion.client.business;

import jakarta.json.JsonValue;
import org.example.elasticsearch.client.json.*;

import org.example.elasticsearch.client.util.*;
import jakarta.json.stream.JsonGenerator;
import org.example.yelp.fusion.client.business.model.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


public abstract class ResponseBody<TDocument> implements JsonpSerializable {

    private static final Logger logger = LoggerFactory.getLogger(ResponseBody.class);

    private final List<TDocument> hits;
    private final Integer total;
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
        this.total = builder.total;
        this.region = builder.region;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {
        if (this.hits != null) {
            generator.writeKey("businesses");
            hits.forEach(business ->  generator.write((JsonValue) business));
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


    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }


    protected abstract static class AbstractBuilder<TDocument, BuilderT extends AbstractBuilder<TDocument, BuilderT>>
            extends
            WithJsonObjectBuilderBase<BuilderT> {

        private List<TDocument> hits;
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

        public final BuilderT hits(List<TDocument> list) {
            this.hits = _listAddAll(this.hits, list);
            return self();
        }

        public final BuilderT hits(TDocument value, TDocument... values) {
            this.hits = _listAdd(this.hits, value, values);
            return self();
        }

        protected abstract BuilderT self();
    }

    // ---------------------------------------------------------------------------------------------
    protected static <TDocument, BuilderT extends AbstractBuilder<TDocument, BuilderT>> void setupResponseBodyDeserializer(
            ObjectDeserializer<BuilderT> op, JsonpDeserializer<TDocument> tDocumentDeserializer) {

        // builder setter, JsonpDeseriazlier.Type, key name

        op.add(AbstractBuilder::total, JsonpDeserializer.integerDeserializer(), "total");
        op.add(AbstractBuilder::region, Region._DESERIALIZER, "region");
        op.add(AbstractBuilder::hits, JsonpDeserializer.arrayDeserializer(tDocumentDeserializer), "businesses");
    }

}
