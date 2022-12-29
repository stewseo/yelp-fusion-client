package io.github.stewseo.client._types.aggregations;

import io.github.stewseo.client.json.JsonpDeserializer;
import io.github.stewseo.client.json.JsonpMapper;
import io.github.stewseo.client.json.JsonpSerializer;
import io.github.stewseo.client.json.ObjectDeserializer;
import io.github.stewseo.client.util.ApiTypeHelper;
import io.github.stewseo.client.util.ObjectBuilder;
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;
import java.util.function.Function;

public abstract class MultiBucketAggregateBase<TBucket> extends AggregateBase {
    private final Buckets<TBucket> buckets;

    @Nullable
    private final JsonpSerializer<TBucket> tBucketSerializer;

    // ---------------------------------------------------------------------------------------------

    protected MultiBucketAggregateBase(MultiBucketAggregateBase.AbstractBuilder<TBucket, ?> builder) {
        super(builder);

        this.buckets = ApiTypeHelper.requireNonNull(builder.buckets, this, "buckets");
        this.tBucketSerializer = builder.tBucketSerializer;

    }

    /**
     * Required - API name: {@code buckets}
     */
    public final Buckets<TBucket> buckets() {
        return this.buckets;
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        super.serializeInternal(generator, mapper);
        generator.writeKey("buckets");
        this.buckets.serialize(generator, mapper);

    }

    protected abstract static class AbstractBuilder<TBucket, BuilderT extends MultiBucketAggregateBase.AbstractBuilder<TBucket, BuilderT>>
            extends
            AggregateBase.AbstractBuilder<BuilderT> {
        private Buckets<TBucket> buckets;

        @Nullable
        private JsonpSerializer<TBucket> tBucketSerializer;

        /**
         * Required - API name: {@code buckets}
         */
        public final BuilderT buckets(Buckets<TBucket> value) {
            this.buckets = value;
            return self();
        }

        /**
         * Required - API name: {@code buckets}
         */
        public final BuilderT buckets(Function<Buckets.Builder<TBucket>, ObjectBuilder<Buckets<TBucket>>> fn) {
            return this.buckets(fn.apply(new Buckets.Builder<TBucket>()).build());
        }

        /**
         * Serializer for TBucket. If not set, an attempt will be made to find a
         * serializer from the JSON context.
         */
        public final BuilderT tBucketSerializer(@Nullable JsonpSerializer<TBucket> value) {
            this.tBucketSerializer = value;
            return self();
        }

    }

    // ---------------------------------------------------------------------------------------------
    protected static <TBucket, BuilderT extends MultiBucketAggregateBase.AbstractBuilder<TBucket, BuilderT>> void setupMultiBucketAggregateBaseDeserializer(
            ObjectDeserializer<BuilderT> op, JsonpDeserializer<TBucket> tBucketDeserializer) {
        AggregateBase.setupAggregateBaseDeserializer(op);
        op.add(MultiBucketAggregateBase.AbstractBuilder::buckets, Buckets.createBucketsDeserializer(tBucketDeserializer), "buckets");

    }

}
