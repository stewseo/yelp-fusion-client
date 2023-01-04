package io.github.stewseo.clients.yelpfusion._types.aggregations;

import io.github.stewseo.clients.json.ExternallyTaggedUnion;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ApiTypeHelper;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.util.WithJsonObjectBuilderBase;
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public abstract class MultiBucketBase implements JsonpSerializable {
    private final Map<String, Aggregate> aggregations;

    private final long docCount;

    // ---------------------------------------------------------------------------------------------

    protected MultiBucketBase(AbstractBuilder<?> builder) {

        this.aggregations = ApiTypeHelper.unmodifiable(builder.aggregations);

        this.docCount = ApiTypeHelper.requireNonNull(builder.docCount, this, "docCount");

    }

    // ---------------------------------------------------------------------------------------------
    protected static <BuilderT extends AbstractBuilder<BuilderT>> void setupMultiBucketBaseDeserializer(
            ObjectDeserializer<BuilderT> op) {

        op.add(AbstractBuilder::docCount, JsonpDeserializer.longDeserializer(), "doc_count");

        op.setUnknownFieldHandler((builder, name, parser, mapper) -> {
            if (builder.aggregations == null) {
                builder.aggregations = new HashMap<>();
            }
            Aggregate._TYPED_KEYS_DESERIALIZER.deserializeEntry(name, parser, mapper, builder.aggregations);
        });

    }

    /**
     * Nested aggregations
     */
    public final Map<String, Aggregate> aggregations() {
        return this.aggregations;
    }

    /**
     * Required - API name: {@code doc_count}
     */
    public final long docCount() {
        return this.docCount;
    }

    /**
     * Serialize this object to JSON.
     */
    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {
        ExternallyTaggedUnion.serializeTypedKeysInner(this.aggregations, generator, mapper);

        generator.writeKey("doc_count");
        generator.write(this.docCount);

    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    protected abstract static class AbstractBuilder<BuilderT extends AbstractBuilder<BuilderT>>
            extends
            WithJsonObjectBuilderBase<BuilderT> {
        @Nullable
        protected Map<String, Aggregate> aggregations = new HashMap<>();
        private Long docCount;

        /**
         * Nested aggregations
         * <p>
         * Adds all entries of <code>map</code> to <code>aggregations</code>.
         */
        public final BuilderT aggregations(Map<String, Aggregate> map) {
            this.aggregations = _mapPutAll(this.aggregations, map);
            return self();
        }

        /**
         * Nested aggregations
         * <p>
         * Adds an entry to <code>aggregations</code>.
         */
        public final BuilderT aggregations(String key, Aggregate value) {
            this.aggregations = _mapPut(this.aggregations, key, value);
            return self();
        }

        /**
         * Nested aggregations
         * <p>
         * Adds an entry to <code>aggregations</code> using a builder lambda.
         */
        public final BuilderT aggregations(String key, Function<Aggregate.Builder, ObjectBuilder<Aggregate>> fn) {
            return aggregations(key, fn.apply(new Aggregate.Builder()).build());
        }

        /**
         * Required - API name: {@code doc_count}
         */
        public final BuilderT docCount(long value) {
            this.docCount = value;
            return self();
        }

        protected abstract BuilderT self();

    }

}
