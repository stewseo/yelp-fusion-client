package io.github.elasticsearch.client.elasticsearch.core;

import io.github.elasticsearch.client.json.*;
import io.github.elasticsearch.client.util.ApiTypeHelper;
import io.github.elasticsearch.client.util.ObjectBuilder;
import io.github.elasticsearch.client.util.WithJsonObjectBuilderBase;

import jakarta.json.stream.*;

import java.util.function.*;

@JsonpDeserializable
public class InnerHitsResult implements JsonpSerializable {
    private final HitsMetadata<JsonData> hits;

    // ---------------------------------------------------------------------------------------------

    private InnerHitsResult(InnerHitsResult.Builder builder) {

        this.hits = ApiTypeHelper.requireNonNull(builder.hits, this, "hits");

    }

    public static InnerHitsResult of(Function<InnerHitsResult.Builder, ObjectBuilder<InnerHitsResult>> fn) {
        return fn.apply(new InnerHitsResult.Builder()).build();
    }


    public final HitsMetadata<JsonData> hits() {
        return this.hits;
    }


    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        generator.writeKey("hits");
        this.hits.serialize(generator, mapper);

    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    // ---------------------------------------------------------------------------------------------


    public static class Builder extends WithJsonObjectBuilderBase<Builder> implements ObjectBuilder<InnerHitsResult> {
        private HitsMetadata<JsonData> hits;


        public final InnerHitsResult.Builder hits(HitsMetadata<JsonData> value) {
            this.hits = value;
            return this;
        }

        /**
         * Required - API name: {@code hits}
         */
        public final InnerHitsResult.Builder hits(Function<HitsMetadata.Builder<JsonData>, ObjectBuilder<HitsMetadata<JsonData>>> fn) {
            return this.hits(fn.apply(new HitsMetadata.Builder<JsonData>()).build());
        }

        @Override
        protected InnerHitsResult.Builder self() {
            return this;
        }

        public InnerHitsResult build() {
            _checkSingleUse();

            return new InnerHitsResult(this);
        }
    }

    // ---------------------------------------------------------------------------------------------


    public static final JsonpDeserializer<InnerHitsResult> _DESERIALIZER = ObjectBuilderDeserializer.lazy(InnerHitsResult.Builder::new,
            InnerHitsResult::setupInnerHitsResultDeserializer);

    protected static void setupInnerHitsResultDeserializer(ObjectDeserializer<InnerHitsResult.Builder> op) {

        op.add(InnerHitsResult.Builder::hits, HitsMetadata.createHitsMetadataDeserializer(JsonData._DESERIALIZER), "hits");

    }

}
