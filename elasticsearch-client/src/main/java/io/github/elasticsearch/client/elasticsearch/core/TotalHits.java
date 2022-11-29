package io.github.elasticsearch.client.elasticsearch.core;

import io.github.elasticsearch.client.json.*;
import io.github.elasticsearch.client.util.ApiTypeHelper;
import io.github.elasticsearch.client.util.ObjectBuilder;
import io.github.elasticsearch.client.util.WithJsonObjectBuilderBase;

import jakarta.json.stream.*;

import java.util.function.*;

@JsonpDeserializable
public class TotalHits implements JsonpSerializable {
    private final TotalHitsRelation relation;

    private final long value;

    // ---------------------------------------------------------------------------------------------

    private TotalHits(TotalHits.Builder builder) {

        this.relation = ApiTypeHelper.requireNonNull(builder.relation, this, "relation");
        this.value = ApiTypeHelper.requireNonNull(builder.value, this, "value");

    }

    public static TotalHits of(Function<TotalHits.Builder, ObjectBuilder<TotalHits>> fn) {
        return fn.apply(new TotalHits.Builder()).build();
    }

    public final TotalHitsRelation relation() {
        return this.relation;
    }


    public final long value() {
        return this.value;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        generator.writeKey("relation");
        this.relation.serialize(generator, mapper);
        generator.writeKey("value");
        generator.write(this.value);

    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    // ---------------------------------------------------------------------------------------------


    public static class Builder extends WithJsonObjectBuilderBase<Builder> implements ObjectBuilder<TotalHits> {
        private TotalHitsRelation relation;

        private Long value;


        public final TotalHits.Builder relation(TotalHitsRelation value) {
            this.relation = value;
            return this;
        }

        public final TotalHits.Builder value(long value) {
            this.value = value;
            return this;
        }

        @Override
        protected TotalHits.Builder self() {
            return this;
        }

        public TotalHits build() {
            _checkSingleUse();

            return new TotalHits(this);
        }
    }

    public static final JsonpDeserializer<TotalHits> _DESERIALIZER = ObjectBuilderDeserializer.lazy(TotalHits.Builder::new,
            TotalHits::setupTotalHitsDeserializer);

    protected static void setupTotalHitsDeserializer(ObjectDeserializer<TotalHits.Builder> op) {

        op.add(TotalHits.Builder::relation, TotalHitsRelation._DESERIALIZER, "relation");
        op.add(TotalHits.Builder::value, JsonpDeserializer.longDeserializer(), "value");

    }
}
