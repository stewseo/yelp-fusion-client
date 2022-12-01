package io.github.yelp.fusion.client.yelpfusion.core;/*
 * Licensed to Elasticsearch B.V. under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch B.V. licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

//----------------------------------------------------
// THIS CODE IS GENERATED. MANUAL EDITS WILL BE LOST.
//----------------------------------------------------

import io.github.yelp.fusion.client.json.*;
import io.github.yelp.fusion.client.util.ApiTypeHelper;
import io.github.yelp.fusion.client.util.ObjectBuilder;
import io.github.yelp.fusion.client.util.WithJsonObjectBuilderBase;
import io.github.yelp.fusion.client.yelpfusion.core.Hit;
import io.github.yelp.fusion.util.PrintUtils;
import jakarta.json.stream.JsonGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.Double;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.annotation.Nullable;

// typedef: _global.search._types.HitsMetadata

@JsonpDeserializable
public class HitsMetadata<T> implements JsonpSerializable {
    private static final Logger logger = LoggerFactory.getLogger(HitsMetadata.class);
    private final List<Hit<T>> hits;

    @Nullable
    private final JsonpSerializer<T> tSerializer;

    // ---------------------------------------------------------------------------------------------

    private HitsMetadata(Builder<T> builder) {

        this.hits = ApiTypeHelper.unmodifiableRequired(builder.hits, this, "hits");
        this.tSerializer = builder.tSerializer;

    }

    public static <T> HitsMetadata<T> of(Function<Builder<T>, ObjectBuilder<HitsMetadata<T>>> fn) {
        return fn.apply(new Builder<>()).build();
    }

    /**
     * Required - API name: {@code hits}
     */
    public final List<Hit<T>> hits() {
        return this.hits;
    }


    /**
     * Serialize this object to JSON.
     */
    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        logger.info(PrintUtils.red("HitsMetaData.serialize "));
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

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

    // ---------------------------------------------------------------------------------------------

    /**
     * Builder for {@link HitsMetadata}.
     */

    public static class Builder<T> extends WithJsonObjectBuilderBase<Builder<T>>
            implements
            ObjectBuilder<HitsMetadata<T>> {

        private List<Hit<T>> hits;

        @Nullable
        private JsonpSerializer<T> tSerializer;

        public final Builder<T> hits(List<Hit<T>> list) {
            this.hits = _listAddAll(this.hits, list);
            return this;
        }

        public final Builder<T> hits(Hit<T> value, Hit<T>... values) {
            this.hits = _listAdd(this.hits, value, values);
            return this;
        }

        public final Builder<T> hits(Function<Hit.Builder<T>, ObjectBuilder<Hit<T>>> fn) {
            return hits(fn.apply(new Hit.Builder<T>()).build());
        }

        public final Builder<T> tSerializer(@Nullable JsonpSerializer<T> value) {
            this.tSerializer = value;
            return this;
        }

        @Override
        protected Builder<T> self() {
            return this;
        }

        public HitsMetadata<T> build() {
            _checkSingleUse();

            return new HitsMetadata<T>(this);
        }
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Create a JSON deserializer for HitsMetadata
     */
    public static <T> JsonpDeserializer<HitsMetadata<T>> createHitsMetadataDeserializer(
            JsonpDeserializer<T> tDeserializer) {
        logger.info(PrintUtils.red("createHitsMetadataDeserializer "));
        return ObjectBuilderDeserializer.createForObject((Supplier<Builder<T>>) Builder::new,
                op -> HitsMetadata.setupHitsMetadataDeserializer(op, tDeserializer));
    };

    /**
     * Json deserializer for {@link HitsMetadata} based on named deserializers
     * provided by the calling {@code JsonMapper}.
     */
    public static final JsonpDeserializer<HitsMetadata<Object>> _DESERIALIZER = JsonpDeserializer
            .lazy(() -> createHitsMetadataDeserializer(
                    new NamedDeserializer<>("org.example.clients:Deserializer:_global.search._types.T")));

    protected static <T> void setupHitsMetadataDeserializer(ObjectDeserializer<HitsMetadata.Builder<T>> op,
                                                            JsonpDeserializer<T> tDeserializer) {
        logger.info(PrintUtils.red("setupHitsMetadataDeserializer "));
        op.add(Builder::hits, JsonpDeserializer.arrayDeserializer(Hit.createHitDeserializer(tDeserializer)), "hits");

    }

}
