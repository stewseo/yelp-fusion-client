package org.example.elasticsearch.client.elasticsearch.core;

import org.example.elasticsearch.client.json.*;
import org.example.elasticsearch.client.util.*;
import jakarta.json.*;
import jakarta.json.stream.*;


import java.io.*;
import java.util.*;
import java.util.function.*;

@JsonpDeserializable
public class Hit<TDocument> implements Serializable {

    private final String index;

    private final String id;

    
    private final Double score;

    private final Map<String, JsonData> fields;

    private final Map<String, List<String>> highlight;

    private final Map<String, InnerHitsResult> innerHits;

    private final List<String> matchedQueries;

    private final Map<String, List<String>> ignoredFieldValues;

    
    private final String shard;

    
    private final String node;

    
    private final String routing;

    
    private final TDocument source;

    
    private final Long seqNo;

    
    private final Long primaryTerm;

    
    private final Long version;

    private final List<String> sort;

    private Hit(Builder<TDocument> builder) {

        this.index = ApiTypeHelper.requireNonNull(builder.index, this, "index");
        this.id = ApiTypeHelper.requireNonNull(builder.id, this, "id");
        this.score = builder.score;
        this.fields = ApiTypeHelper.unmodifiable(builder.fields);
        this.highlight = ApiTypeHelper.unmodifiable(builder.highlight);
        this.innerHits = ApiTypeHelper.unmodifiable(builder.innerHits);
        this.matchedQueries = ApiTypeHelper.unmodifiable(builder.matchedQueries);

        this.ignoredFieldValues = ApiTypeHelper.unmodifiable(builder.ignoredFieldValues);
        this.shard = builder.shard;
        this.node = builder.node;
        this.routing = builder.routing;
        this.source = builder.source;
        this.seqNo = builder.seqNo;
        this.primaryTerm = builder.primaryTerm;
        this.version = builder.version;
        this.sort = ApiTypeHelper.unmodifiable(builder.sort);
        this.tDocumentSerializer = builder.tDocumentSerializer;

    }

    public static <TDocument> Hit<TDocument> of(Function<Builder<TDocument>, ObjectBuilder<Hit<TDocument>>> fn) {
        return fn.apply(new Hit.Builder<>()).build();
    }
    
    private final JsonpSerializer<TDocument> tDocumentSerializer;

    public final String index() {
        return this.index;
    }


    public final String id() {
        return this.id;
    }


    
    public final Double score() {
        return this.score;
    }



    public final Map<String, org.example.elasticsearch.client.json.JsonData> fields() {
        return this.fields;
    }

    /**
     * API name: {@code highlight}
     */
    public final Map<String, List<String>> highlight() {
        return this.highlight;
    }

    /**
     * API name: {@code inner_hits}
     */
    public final Map<String, InnerHitsResult> innerHits() {
        return this.innerHits;
    }

    /**
     * API name: {@code matched_queries}
     */
    @SuppressWarnings("unused")
    public final List<String> matchedQueries() {
        return this.matchedQueries;
    }


    /**
     * API name: {@code ignored_field_values}
     */
    public final Map<String, List<String>> ignoredFieldValues() {
        return this.ignoredFieldValues;
    }

    /**
     * API name: {@code _shard}
     */
    
    public final String shard() {
        return this.shard;
    }

    /**
     * API name: {@code _node}
     */
    
    public final String node() {
        return this.node;
    }


    @SuppressWarnings("unused")
    
    public final String routing() {
        return this.routing;
    }

    
    public final TDocument source() {
        return this.source;
    }

    
    public final Long seqNo() {
        return this.seqNo;
    }


    
    public final Long primaryTerm() {
        return this.primaryTerm;
    }


    
    public final Long version() {
        return this.version;
    }


    public final List<String> sort() {
        return this.sort;
    }


    @SuppressWarnings({"UnusedReturnValue", "unused"})
    public static class Builder<TDocument> extends WithJsonObjectBuilderBase<Hit.Builder<TDocument>>
            implements
            ObjectBuilder<Hit<TDocument>> {
        private String index;

        private String id;

        
        private Double score;

        
        private Map<String, JsonData> fields;

        
        private Map<String, List<String>> highlight;

        
        private Map<String, InnerHitsResult> innerHits;

        
        private List<String> matchedQueries;


        
        private Map<String, List<String>> ignoredFieldValues;

        
        private String shard;

        
        private String node;

        
        private String routing;

        
        private TDocument source;

        
        private Long seqNo;

        
        private Long primaryTerm;

        
        private Long version;

        
        private List<String> sort;

        
        private JsonpSerializer<TDocument> tDocumentSerializer;


        public final Hit.Builder<TDocument> index(String value) {
            this.index = value;
            return this;
        }

        /**
         * Required - API name: {@code _id}
         */
        public final Hit.Builder<TDocument> id(String value) {
            this.id = value;
            return this;
        }

        public final Hit.Builder<TDocument> score( Double value) {
            this.score = value;
            return this;
        }


        public final Hit.Builder<TDocument> fields(Map<String, JsonData> map) {
            this.fields = _mapPutAll(this.fields, map);
            return this;
        }

        public final Hit.Builder<TDocument> fields(String key, JsonData value) {
            this.fields = _mapPut(this.fields, key, value);
            return this;
        }


        public final Hit.Builder<TDocument> highlight(Map<String, List<String>> map) {
            this.highlight = _mapPutAll(this.highlight, map);
            return this;
        }


        public final Hit.Builder<TDocument> highlight(String key, List<String> value) {
            this.highlight = _mapPut(this.highlight, key, value);
            return this;
        }


        public final Hit.Builder<TDocument> innerHits(Map<String, InnerHitsResult> map) {
            this.innerHits = _mapPutAll(this.innerHits, map);
            return this;
        }


        public final Hit.Builder<TDocument> innerHits(String key, InnerHitsResult value) {
            this.innerHits = _mapPut(this.innerHits, key, value);
            return this;
        }


        public final Hit.Builder<TDocument> innerHits(String key,
                                                      Function<InnerHitsResult.Builder, ObjectBuilder<InnerHitsResult>> fn) {
            return innerHits(key, fn.apply(new InnerHitsResult.Builder()).build());
        }

        public final Hit.Builder<TDocument> matchedQueries(List<String> list) {
            this.matchedQueries = _listAddAll(this.matchedQueries, list);
            return this;
        }


        public final Hit.Builder<TDocument> matchedQueries(String value, String... values) {
            this.matchedQueries = _listAdd(this.matchedQueries, value, values);
            return this;
        }


        public final Hit.Builder<TDocument> ignoredFieldValues(Map<String, List<String>> map) {
            this.ignoredFieldValues = _mapPutAll(this.ignoredFieldValues, map);
            return this;
        }

        public final Hit.Builder<TDocument> ignoredFieldValues(String key, List<String> value) {
            this.ignoredFieldValues = _mapPut(this.ignoredFieldValues, key, value);
            return this;
        }

        /**
         * API name: {@code _shard}
         */
        public final Hit.Builder<TDocument> shard( String value) {
            this.shard = value;
            return this;
        }

        /**
         * API name: {@code _node}
         */
        public final Hit.Builder<TDocument> node( String value) {
            this.node = value;
            return this;
        }

        public final Hit.Builder<TDocument> routing( String value) {
            this.routing = value;
            return this;
        }

        public final Hit.Builder<TDocument> source( TDocument value) {
            this.source = value;
            return this;
        }

        /**
         * API name: {@code _seq_no}
         */
        public final Hit.Builder<TDocument> seqNo( Long value) {
            this.seqNo = value;
            return this;
        }

        public final Hit.Builder<TDocument> primaryTerm( Long value) {
            this.primaryTerm = value;
            return this;
        }


        public final Hit.Builder<TDocument> version( Long value) {
            this.version = value;
            return this;
        }

        public final Hit.Builder<TDocument> sort(List<String> list) {
            this.sort = _listAddAll(this.sort, list);
            return this;
        }


        public final Hit.Builder<TDocument> sort(String value, String... values) {
            this.sort = _listAdd(this.sort, value, values);
            return this;
        }

        public final Hit.Builder<TDocument> tDocumentSerializer( JsonpSerializer<TDocument> value) {
            this.tDocumentSerializer = value;
            return this;
        }

        @Override
        protected Hit.Builder<TDocument> self() {
            return this;
        }


        public Hit<TDocument> build() {
            _checkSingleUse();

            return new Hit<TDocument>(this);

        }
    }

    static class MaxScore {
        int score;
        public MaxScore(){}

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }


    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        generator.writeKey("_index");
        generator.write(this.index);

        generator.writeKey("_id");
        generator.write(this.id);

        if (this.score != null) {
            generator.writeKey("_score");
            JsonpUtils.serializeDoubleOrNull(generator, this.score, Double.NaN);
        }

        if (ApiTypeHelper.isDefined(this.fields)) {
            generator.writeKey("fields");
            generator.writeStartObject();
            for (Map.Entry<String, JsonData> item0 : this.fields.entrySet()) {
                generator.writeKey(item0.getKey());
                item0.getValue().serialize(generator, mapper);

            }
            generator.writeEnd();

        }
        if (ApiTypeHelper.isDefined(this.highlight)) {
            generator.writeKey("highlight");
            generator.writeStartObject();
            for (Map.Entry<String, List<String>> item0 : this.highlight.entrySet()) {
                generator.writeKey(item0.getKey());
                generator.writeStartArray();
                if (item0.getValue() != null) {
                    for (String item1 : item0.getValue()) {
                        generator.write(item1);

                    }
                }
                generator.writeEnd();

            }
            generator.writeEnd();

        }
        if (ApiTypeHelper.isDefined(this.innerHits)) {
            generator.writeKey("inner_hits");
            generator.writeStartObject();
            for (Map.Entry<String, InnerHitsResult> item0 : this.innerHits.entrySet()) {
                generator.writeKey(item0.getKey());
                item0.getValue().serialize(generator, mapper);

            }
            generator.writeEnd();

        }
        if (ApiTypeHelper.isDefined(this.matchedQueries)) {
            generator.writeKey("matched_queries");
            generator.writeStartArray();
            for (String item0 : this.matchedQueries) {
                generator.write(item0);

            }
            generator.writeEnd();

        }
        if (ApiTypeHelper.isDefined(this.ignoredFieldValues)) {
            generator.writeKey("ignored_field_values");
            generator.writeStartObject();
            for (Map.Entry<String, List<String>> item0 : this.ignoredFieldValues.entrySet()) {
                generator.writeKey(item0.getKey());
                generator.writeStartArray();
                if (item0.getValue() != null) {
                    for (String item1 : item0.getValue()) {
                        generator.write(item1);

                    }
                }
                generator.writeEnd();

            }
            generator.writeEnd();

        }
        if (this.shard != null) {
            generator.writeKey("_shard");
            generator.write(this.shard);

        }
        if (this.node != null) {
            generator.writeKey("_node");
            generator.write(this.node);

        }
        if (this.routing != null) {
            generator.writeKey("_routing");
            generator.write(this.routing);

        }
        if (this.source != null) {
            generator.writeKey("_source");
            JsonpUtils.serialize(this.source, generator, tDocumentSerializer, mapper);

        }
        if (this.seqNo != null) {
            generator.writeKey("_seq_no");
            generator.write(this.seqNo);

        }
        if (this.primaryTerm != null) {
            generator.writeKey("_primary_term");
            generator.write(this.primaryTerm);

        }
        if (this.version != null) {
            generator.writeKey("_version");
            generator.write(this.version);

        }
        if (ApiTypeHelper.isDefined(this.sort)) {
            generator.writeKey("sort");
            generator.writeStartArray();
            for (String item0 : this.sort) {
                generator.write(item0);

            }
            generator.writeEnd();

        }

    }

    @Override
    public String toString() {
        return JsonpUtils.toString((JsonValue) this);
    }



    public static <TDocument> JsonpDeserializer<Hit<TDocument>> createHitDeserializer(
            JsonpDeserializer<TDocument> tDocumentDeserializer) {
        return ObjectBuilderDeserializer.createForObject((Supplier<Hit.Builder<TDocument>>) Hit.Builder::new,
                op -> Hit.setupHitDeserializer(op, tDocumentDeserializer));
    };

    public static final JsonpDeserializer<Hit<Object>> _DESERIALIZER = JsonpDeserializer
            .lazy(() -> createHitDeserializer(
                    new NamedDeserializer<>("co.elastic.clients:Deserializer:_global._types.TDocument")));

    protected static <TDocument> void setupHitDeserializer(ObjectDeserializer<Hit.Builder<TDocument>> op,
                                                           JsonpDeserializer<TDocument> tDocumentDeserializer) {

        op.add(Hit.Builder::index, JsonpDeserializer.stringDeserializer(), "_index");
        op.add(Hit.Builder::id, JsonpDeserializer.stringDeserializer(), "_id");
        op.add(Hit.Builder::score, JsonpDeserializer.doubleOrNullDeserializer(Double.NaN), "_score");
        op.add(Hit.Builder::fields, JsonpDeserializer.stringMapDeserializer(JsonData._DESERIALIZER), "fields");
        op.add(Hit.Builder::highlight, JsonpDeserializer.stringMapDeserializer(
                JsonpDeserializer.arrayDeserializer(JsonpDeserializer.stringDeserializer())), "highlight");
        op.add(Hit.Builder::innerHits, JsonpDeserializer.stringMapDeserializer(InnerHitsResult._DESERIALIZER),
                "inner_hits");
        op.add(Hit.Builder::matchedQueries, JsonpDeserializer.arrayDeserializer(JsonpDeserializer.stringDeserializer()),
                "matched_queries");


        op.add(Hit.Builder::ignoredFieldValues,
                JsonpDeserializer.stringMapDeserializer(
                        JsonpDeserializer.arrayDeserializer(JsonpDeserializer.stringDeserializer())),
                "ignored_field_values");
        op.add(Hit.Builder::shard, JsonpDeserializer.stringDeserializer(), "_shard");
        op.add(Hit.Builder::node, JsonpDeserializer.stringDeserializer(), "_node");
        op.add(Hit.Builder::routing, JsonpDeserializer.stringDeserializer(), "_routing");
        op.add(Hit.Builder::source, tDocumentDeserializer, "_source");
        op.add(Hit.Builder::seqNo, JsonpDeserializer.longDeserializer(), "_seq_no");
        op.add(Hit.Builder::primaryTerm, JsonpDeserializer.longDeserializer(), "_primary_term");
        op.add(Hit.Builder::version, JsonpDeserializer.longDeserializer(), "_version");
        op.add(Hit.Builder::sort, JsonpDeserializer.arrayDeserializer(JsonpDeserializer.stringDeserializer()), "sort");

    }

}

