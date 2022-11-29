package io.github.yelp.fusion.client.business.search;

import co.elastic.clients.util.ApiTypeHelper;
import io.github.elasticsearch.client.json.*;
import io.github.yelp.fusion.client.business.model.Region;
import jakarta.json.stream.JsonGenerator;

import io.github.elasticsearch.client.util.ObjectBuilder;
import io.github.elasticsearch.client.util.WithJsonObjectBuilderBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@JsonpDeserializable
public class HitsMeta<T> implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(HitsMeta.class);
    private final List<Hit<T>> hits;
    @Nullable
    private final Region region;
    @Nullable
    private final Integer total;

    @Nullable
    private final JsonpSerializer<T> tSerializer;


    public Integer total(){return this.total;}

    public Region region(){return this.region;}

    public List<Hit<T>> hits(){return hits;}

    private HitsMeta(Builder<T> builder) {
        this.total = builder.total;
        this.region = builder.region;
        this.hits = builder.hits;
        this.tSerializer = builder.tSerializer;
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
        
        if (ApiTypeHelper.isDefined(this.hits)) {
            generator.writeKey("hits");
            generator.writeStartArray();
            for (Hit<T> item0 : this.hits) {
                item0.serialize(generator, mapper);

            }
            generator.writeEnd();

        }
        if (this.region != null) {
            generator.writeKey("region");
            region.serialize(generator, mapper);
        }

    }


    @SuppressWarnings({"UnusedReturnValue", "unused"})
    public static class Builder<T> extends WithJsonObjectBuilderBase<Builder<T>>
            implements
            ObjectBuilder<HitsMeta<T>> {
        
        private List<Hit<T>> hits;
        
        @Nullable
        private Region region;
        
        @Nullable
        private Integer total;

        @Nullable
        private JsonpSerializer<T> tSerializer;

        

        public  HitsMeta<T> of(Function<HitsMeta.Builder<T>, ObjectBuilder<HitsMeta<T>>> fn) {
            return fn.apply(new HitsMeta.Builder<>()).build();
        }

        public final HitsMeta.Builder<T> region(Region value) {
            this.region = value;
            return this;
        }

        public final HitsMeta.Builder<T> total(Integer value) {
            this.total = value;
            return this;
        }

        public final HitsMeta.Builder<T> hits(List<Hit<T>> list) {
            this.hits = _listAddAll(this.hits, list);
            return this;
        }

        public final HitsMeta.Builder<T> hits(Hit<T> value, Hit<T>... values) {
            this.hits = _listAdd(this.hits, value, values);
            return this;
        }

        public final HitsMeta.Builder<T> hits(Function<Hit.Builder<T>, ObjectBuilder<Hit<T>>> fn) {
            return hits(fn.apply(new Hit.Builder<T>()).build());
        }

        public final HitsMeta.Builder<T> tSerializer(@Nullable JsonpSerializer<T> value) {
            this.tSerializer = value;
            return this;
        }

        @Override
        protected HitsMeta.Builder<T> self() {
            return this;
        }

        public HitsMeta<T> build() {
            _checkSingleUse();

            return new HitsMeta<>(this);
        }
    }


    public static <T> JsonpDeserializer<HitsMeta<T>> createHitsMetaDeserializer(
            JsonpDeserializer<T> tDocumentDeserializer) {
        return ObjectBuilderDeserializer.createForObject((Supplier<Builder<T>>) HitsMeta.Builder::new,
                op -> HitsMeta.setupHitsMetaDeserializer(op, tDocumentDeserializer));
    }

    public static final JsonpDeserializer<HitsMeta<Object>> _DESERIALIZER = JsonpDeserializer
            .lazy(() -> createHitsMetaDeserializer(
                    new NamedDeserializer<>("org.example.clients:Deserializer:_global._types.TDocument")));

    protected static <T> void setupHitsMetaDeserializer(ObjectDeserializer<Builder<T>> op,
                                                        JsonpDeserializer<T> tDeserializer) {
        logger.debug("Setting up HitsMeta Deserializer JsonpDeserializer.arrayDeserializer(Hit.createHitDeserializer(tDeserializer)");
        op.add(HitsMeta.Builder::hits, JsonpDeserializer.arrayDeserializer(Hit.createHitDeserializer(tDeserializer)), "businesses");
        op.add(HitsMeta.Builder::total, JsonpDeserializer.integerDeserializer(), "total");
        op.add(HitsMeta.Builder::region, Region._DESERIALIZER, "region");

    }


}

