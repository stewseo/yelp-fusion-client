package org.example.yelp.fusion.client.businesses.search;



import org.example.elasticsearch.client.elasticsearch.core.HitsMetadata;

import org.example.elasticsearch.client.json.JsonData;
import org.example.elasticsearch.client.json.JsonpDeserializer;
import org.example.elasticsearch.client.json.JsonpMapper;
import org.example.elasticsearch.client.json.JsonpSerializable;
import org.example.elasticsearch.client.json.JsonpSerializer;
import org.example.elasticsearch.client.json.JsonpUtils;
import org.example.elasticsearch.client.json.ObjectDeserializer;

import org.example.elasticsearch.client.util.*;
import org.example.yelp.fusion.client.businesses.*;
import jakarta.json.stream.JsonGenerator;
import java.util.*;
import java.util.function.*;


public abstract class ResponseBody<TDocument> implements JsonpSerializable {

    // ---------------------------------------------------------------------------------------------
    private final int total;

    private final Error error;

    private final List<Business> business;

    private final String region;

    private final long took;

    private final boolean timedOut;

    private final HitsMetadata<TDocument> hits;


    private final Map<String, JsonData> fields;

    
    private final Double maxScore;

    
    private final Long numReducePhases;



    private final String pitId;

    
    private final String scrollId;

    
    private final Boolean terminatedEarly;

    
    private final JsonpSerializer<TDocument> tDocumentSerializer;


    // ---------------------------------------------------------------------------------------------

    public int total() {
        return total;
    }

    public Error error() {
        return error;
    }

    public List<Business> business() {
        return business;
    }

    public String region() {
        return region;
    }

    
    public JsonpSerializer<TDocument> tDocumentSerializer() {
        return tDocumentSerializer;
    }

    protected ResponseBody(AbstractBuilder<TDocument, ?> builder) {

        this.total = builder.total;
        this.error = builder.error;
        this.business = builder.business;
        this.took = ApiTypeHelper.requireNonNull(builder.took, this, "took");
        this.timedOut = ApiTypeHelper.requireNonNull(builder.timedOut, this, "timedOut");
        this.hits = ApiTypeHelper.requireNonNull(builder.hits, this, "hits");
        this.fields = ApiTypeHelper.unmodifiable(builder.fields);
        this.maxScore = builder.maxScore;
        this.numReducePhases = builder.numReducePhases;

        this.pitId = builder.pitId;
        this.scrollId = builder.scrollId;
        this.terminatedEarly = builder.terminatedEarly;
        this.tDocumentSerializer = builder.tDocumentSerializer;
        this.region = builder.region;
    }

    public final long took() {
        return this.took;
    }

    public final boolean timedOut() {
        return this.timedOut;
    }


    public final HitsMetadata<TDocument> hits() {
        return this.hits;
    }


    public final Map<String, JsonData> fields() {
        return this.fields;
    }

    
    public final Double maxScore() {
        return this.maxScore;
    }

    
    public final Long numReducePhases() {
        return this.numReducePhases;
    }

    
    public final String pitId() {
        return this.pitId;
    }


    
    public final String scrollId() {
        return this.scrollId;
    }

    
    public final Boolean terminatedEarly() {
        return this.terminatedEarly;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        generator.writeKey("took");
        generator.write(this.took);

        generator.writeKey("timed_out");
        generator.write(this.timedOut);


        generator.writeKey("hits");
        this.hits.serialize(generator, mapper);

        if (ApiTypeHelper.isDefined(this.fields)) {
            generator.writeKey("fields");
            generator.writeStartObject();
            for (Map.Entry<String, JsonData> item0 : this.fields.entrySet()) {
                generator.writeKey(item0.getKey());
                item0.getValue().serialize(generator, mapper);

            }
            generator.writeEnd();

        }
        if (this.maxScore != null) {
            generator.writeKey("max_score");
            generator.write(this.maxScore);

        }
        if (this.numReducePhases != null) {
            generator.writeKey("num_reduce_phases");
            generator.write(this.numReducePhases);

        }
        if (this.pitId != null) {
            generator.writeKey("pit_id");
            generator.write(this.pitId);

        }
        if (this.scrollId != null) {
            generator.writeKey("_scroll_id");
            generator.write(this.scrollId);

        }
        if (this.terminatedEarly != null) {
            generator.writeKey("terminated_early");
            generator.write(this.terminatedEarly);

        }

    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    protected abstract static class AbstractBuilder<TDocument, BuilderT extends AbstractBuilder<TDocument, BuilderT>>
            extends
            WithJsonObjectBuilderBase<BuilderT> {


        private HitsMetadata<TDocument> hits;

        
        private Map<String, JsonData> fields;

        
        private Double maxScore;

        
        private Long numReducePhases;

        
        private String pitId;

        
        private String scrollId;

        
        private Boolean terminatedEarly;

        private Long took;

        private int total;

        private Error error;

        private List<Business> business;

        private String region;

        private boolean timedOut;


        
        private JsonpSerializer<TDocument> tDocumentSerializer;


        public final BuilderT tDocumentSerializer(boolean value) {
            this.timedOut = value;
            return self();
        }
        public final BuilderT suggest(boolean value) {
            this.timedOut = value;
            return self();
        }
        public final BuilderT profile(boolean value) {
            this.timedOut = value;
            return self();
        }
        public final BuilderT clusters(boolean value) {
            this.timedOut = value;
            return self();
        }
        public final BuilderT timedOut(boolean value) {
            this.timedOut = value;
            return self();
        }


        public final BuilderT hits(HitsMetadata<TDocument> value) {
            this.hits = value;
            return self();
        }


        public final BuilderT hits(
                Function<HitsMetadata.Builder<TDocument>, ObjectBuilder<HitsMetadata<TDocument>>> fn) {
            return this.hits(fn.apply(new HitsMetadata.Builder<TDocument>()).build());
        }


        public final BuilderT fields(Map<String, JsonData> map) {
            this.fields = _mapPutAll(this.fields, map);
            return self();
        }


        public final BuilderT fields(String key, JsonData value) {
            this.fields = _mapPut(this.fields, key, value);
            return self();
        }


        public final BuilderT maxScore( Double value) {
            this.maxScore = value;
            return self();
        }

        public final BuilderT numReducePhases( Long value) {
            this.numReducePhases = value;
            return self();
        }


        public final BuilderT pitId( String value) {
            this.pitId = value;
            return self();
        }


        public final BuilderT scrollId( String value) {
            this.scrollId = value;
            return self();
        }


        public final BuilderT terminatedEarly( Boolean value) {
            this.terminatedEarly = value;
            return self();
        }


        public final BuilderT tDocumentSerializer( JsonpSerializer<TDocument> value) {
            this.tDocumentSerializer = value;
            return self();
        }

        protected abstract BuilderT self();

    }

    // ---------------------------------------------------------------------------------------------
    protected static <TDocument, BuilderT extends AbstractBuilder<TDocument, BuilderT>> void setupResponseBodyDeserializer(
            ObjectDeserializer<BuilderT> op, JsonpDeserializer<TDocument> tDocumentDeserializer) {

        op.add(AbstractBuilder::timedOut, JsonpDeserializer.booleanDeserializer(), "timed_out");
        op.add(AbstractBuilder::hits, HitsMetadata.createHitsMetadataDeserializer(tDocumentDeserializer), "hits");
        op.add(AbstractBuilder::fields, JsonpDeserializer.stringMapDeserializer(JsonData._DESERIALIZER), "fields");
        op.add(AbstractBuilder::maxScore, JsonpDeserializer.doubleDeserializer(), "max_score");
        op.add(AbstractBuilder::numReducePhases, JsonpDeserializer.longDeserializer(), "num_reduce_phases");
        op.add(AbstractBuilder::pitId, JsonpDeserializer.stringDeserializer(), "pit_id");
        op.add(AbstractBuilder::scrollId, JsonpDeserializer.stringDeserializer(), "_scroll_id");
        op.add(AbstractBuilder::terminatedEarly, JsonpDeserializer.booleanDeserializer(), "terminated_early");

    }

}
