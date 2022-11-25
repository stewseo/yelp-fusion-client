package org.example.yelp.fusion.client.business.search;

import org.example.elasticsearch.client.json.*;

import org.example.elasticsearch.client.elasticsearch.core.Hit;

import org.example.elasticsearch.client.util.*;
import jakarta.json.stream.JsonGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.util.*;


public abstract class ResponseBody<TDocument> implements JsonpSerializable {

    private static final Logger logger = LoggerFactory.getLogger(ResponseBody.class);

    // ---------------------------------------------------------------------------------------------
    @Nullable
    private final Integer total;
    
    private final List<Hit<TDocument>> hits;



    @Nullable 
    private final JsonpSerializer<TDocument> tDocumentSerializer;

    // ---------------------------------------------------------------------------------------------

    public Integer total() {
        return total;
    }

    public List<Hit<TDocument>> hits() {
        return this.hits;
    }

    public JsonpSerializer<TDocument> tDocumentSerializer() {
        return tDocumentSerializer;
    }

    protected ResponseBody(AbstractBuilder<TDocument, ?> builder) {
        this.total = builder.total;
        this.hits = builder.hits;
        this.tDocumentSerializer = builder.tDocumentSerializer;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        if (this.hits != null) {
            generator.writeKey("businesses");
            generator.writeStartArray();
            for (Hit<TDocument> item0 : this.hits()) {
                item0.serialize(generator, mapper);
            }
            generator.writeEnd();
        }

        if (this.total != null) {
            generator.writeKey("total");
            generator.write(this.total);
        }

    }


    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }


    protected abstract static class AbstractBuilder<TDocument, BuilderT extends AbstractBuilder<TDocument, BuilderT>>
            extends
            WithJsonObjectBuilderBase<BuilderT> {
        @Nullable
        private Integer total;

        private List<Hit<TDocument>> hits;

        @Nullable
        private JsonpSerializer<TDocument> tDocumentSerializer;


        private final BuilderT total(Integer value) {
            this.total = value;
            return self();
        }

        public final BuilderT hits(List<Hit<TDocument>> list) {
            this.hits = _listAddAll(this.hits, list);
            return self();
        }

        public final BuilderT hits(Hit<TDocument> value, Hit<TDocument>... values) {
            this.hits = _listAdd(this.hits, value, values);
            return self();
        }


        public final BuilderT tDocumentSerializer(JsonpSerializer<TDocument> value) {
            this.tDocumentSerializer = value;
            return self();
            
        }
        protected abstract BuilderT self();

    }


    // ---------------------------------------------------------------------------------------------
    protected static <TDocument, BuilderT extends AbstractBuilder<TDocument, BuilderT>> void setupResponseBodyDeserializer(
            ObjectDeserializer<BuilderT> op, JsonpDeserializer<TDocument> tDocumentDeserializer) {


        op.add(AbstractBuilder::hits, Hit.createHitDeserializer(tDocumentDeserializer), "businesses");
        op.add(AbstractBuilder::total, JsonpDeserializer.integerDeserializer(), "total");
    }

}
