package io.github.stewseo.clients.yelpfusion._types.aggregations;

import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ApiTypeHelper;
import io.github.stewseo.clients.util.ObjectBuilder;
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;
import java.util.function.Function;

public abstract class MultiBucketAggregateBase<TBucket> extends AggregateBase {
    private final Buckets<TBucket> buckets;

    @Nullable
    private final JsonpSerializer<TBucket> tBucketSerializer;

    // ---------------------------------------------------------------------------------------------

    protected MultiBucketAggregateBase(AbstractBuilder<TBucket, ?> builder) {
        super(builder);

        this.buckets = ApiTypeHelper.requireNonNull(builder.buckets, this, "buckets");
        this.tBucketSerializer = builder.tBucketSerializer;

    }

    // ---------------------------------------------------------------------------------------------
    protected static <TBucket, BuilderT extends AbstractBuilder<TBucket, BuilderT>> void setupMultiBucketAggregateBaseDeserializer(
            ObjectDeserializer<BuilderT> op, JsonpDeserializer<TBucket> tBucketDeserializer) {
        setupAggregateBaseDeserializer(op);
        op.add(AbstractBuilder::buckets, Buckets.createBucketsDeserializer(tBucketDeserializer), "buckets");

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

    protected abstract static class AbstractBuilder<TBucket, BuilderT extends AbstractBuilder<TBucket, BuilderT>>
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

}
