package org.example.yelp.fusion.client.business.search;


import jakarta.json.stream.JsonGenerator;
import org.example.elasticsearch.client.elasticsearch.core.Hit;
import org.example.elasticsearch.client.json.*;
import org.example.elasticsearch.client.util.ApiTypeHelper;
import org.example.elasticsearch.client.util.ObjectBuilder;
import org.example.elasticsearch.client.util.WithJsonObjectBuilderBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@JsonpDeserializable
public class Businesses<T> implements JsonpSerializable {
    private static final Logger logger = LoggerFactory.getLogger(Businesses.class);

    private final Integer total;

    private final List<T> businesses;
    private final List<Hit<T>> hits;
    @Nullable
    private final JsonpSerializer<T> tSerializer;

    // ---------------------------------------------------------------------------------------------

    private Businesses(Businesses.Builder<T> builder) {

        this.total = builder.total;
        this.hits = ApiTypeHelper.unmodifiableRequired(builder.hits, this, "businesses");
        this.tSerializer = builder.tSerializer;
        this.businesses = builder.businesses;

    }

    public static <T> Businesses<T> of(Function<Businesses.Builder<T>, ObjectBuilder<Businesses<T>>> fn) {
        return fn.apply(new Businesses.Builder<>()).build();
    }

    public final List<Hit<T>> hits() {
        return this.hits;
    }

    public final List<T> businesses(){return this.businesses;}

    public final Integer total() {
        return this.total;
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
                generator.writeKey("businesses");
                generator.writeStartArray();
                for (Hit<T> item0 : this.hits) {
                    item0.serialize(generator, mapper);

                }
                generator.writeEnd();
            }
        }

        @Override
        public String toString() {
            return JsonpUtils.toString(this);
        }


        public static class Builder<T> extends WithJsonObjectBuilderBase<Builder<T>>
                implements
                ObjectBuilder<Businesses<T>> {

            private List<Hit<T>> hits;

            private List<T> businesses;

            private Integer total;

            @Nullable
            private JsonpSerializer<T> tSerializer;


            public final Businesses.Builder<T> total(Integer total) {
                this.total = total;
                return this;
            }



            public final Businesses.Builder<T> businesses(List<T> list) {
                this.businesses = _listAddAll(this.businesses, list);
                return this;
            }

            public final Businesses.Builder<T> businesses(T value, T... values) {
                this.businesses = _listAdd(this.businesses, value, values);
                return this;
            }


            public final Businesses.Builder<T> tSerializer(@Nullable JsonpSerializer<T> value) {
                this.tSerializer = value;
                return this;
            }

            @Override
            protected Businesses.Builder<T> self() {
                return this;
            }

            public Businesses<T> build() {
                _checkSingleUse();

                return new Businesses<T>(this);
            }
        }

        // ---------------------------------------------------------------------------------------------

        public static <T> JsonpDeserializer<Businesses<T>> createBusinessesDeserializer(
                JsonpDeserializer<T> tDeserializer) {
            return ObjectBuilderDeserializer.createForObject((Supplier<Businesses.Builder<T>>) Businesses.Builder::new,
                    op -> Businesses.setupBusinessesDeserializer(op, tDeserializer));
        }


        public static final JsonpDeserializer<Businesses<Object>> _DESERIALIZER = JsonpDeserializer
                .lazy(() -> createBusinessesDeserializer(
                        new NamedDeserializer<>("com.example.clients:Deserializer:_global.search._types.T")));

        protected static <T> void setupBusinessesDeserializer(ObjectDeserializer<Businesses.Builder<T>> op,
                                                                JsonpDeserializer<T> tDeserializer) {
            logger.info("Object deserializer is adding business, a JsonpDeserializer.arrayDeserializer(Hit.createHitDeserializer(tDeserializer))," +
                    " with the name business tDeserializer = " + tDeserializer);


            op.add(Builder::businesses, JsonpDeserializer.arrayDeserializer(tDeserializer), "businesses");

            op.add(Builder::total, JsonpDeserializer.integerDeserializer(), "total");

        }
    }

