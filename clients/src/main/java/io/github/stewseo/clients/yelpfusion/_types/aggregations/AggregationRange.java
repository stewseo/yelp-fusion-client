/*
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

package io.github.stewseo.clients.yelpfusion._types.aggregations;

import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.util.WithJsonObjectBuilderBase;
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;
import java.util.function.Function;

// typedef: _types.aggregations.AggregationRange

/**
 * @see <a href=
 * "../../doc-files/api-spec.html#_types.aggregations.AggregationRange">API
 * specification</a>
 */
@JsonpDeserializable
public class AggregationRange implements JsonpSerializable {
    /**
     * Json deserializer for {@link AggregationRange}
     */
    public static final JsonpDeserializer<AggregationRange> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
            AggregationRange::setupAggregationRangeDeserializer);
    @Nullable
    private final String from;
    @Nullable
    private final String key;

    // ---------------------------------------------------------------------------------------------
    @Nullable
    private final String to;

    private AggregationRange(Builder builder) {

        this.from = builder.from;
        this.key = builder.key;
        this.to = builder.to;

    }

    public static AggregationRange of(Function<Builder, ObjectBuilder<AggregationRange>> fn) {
        return fn.apply(new Builder()).build();
    }

    protected static void setupAggregationRangeDeserializer(ObjectDeserializer<Builder> op) {

        op.add(Builder::from, JsonpDeserializer.stringDeserializer(), "from");
        op.add(Builder::key, JsonpDeserializer.stringDeserializer(), "key");
        op.add(Builder::to, JsonpDeserializer.stringDeserializer(), "to");

    }

    /**
     * API name: {@code from}
     */
    @Nullable
    public final String from() {
        return this.from;
    }

    /**
     * API name: {@code key}
     */
    @Nullable
    public final String key() {
        return this.key;
    }

    /**
     * API name: {@code to}
     */
    @Nullable
    public final String to() {
        return this.to;
    }

    /**
     * Serialize this object to JSON.
     */
    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    // ---------------------------------------------------------------------------------------------

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        if (this.from != null) {
            generator.writeKey("from");
            generator.write(this.from);

        }
        if (this.key != null) {
            generator.writeKey("key");
            generator.write(this.key);

        }
        if (this.to != null) {
            generator.writeKey("to");
            generator.write(this.to);

        }

    }

    // ---------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    /**
     * Builder for {@link AggregationRange}.
     */

    public static class Builder extends WithJsonObjectBuilderBase<Builder> implements ObjectBuilder<AggregationRange> {
        @Nullable
        private String from;

        @Nullable
        private String key;

        @Nullable
        private String to;

        /**
         * API name: {@code from}
         */
        public final Builder from(@Nullable String value) {
            this.from = value;
            return this;
        }

        /**
         * API name: {@code key}
         */
        public final Builder key(@Nullable String value) {
            this.key = value;
            return this;
        }

        /**
         * API name: {@code to}
         */
        public final Builder to(@Nullable String value) {
            this.to = value;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        /**
         * Builds a {@link AggregationRange}.
         *
         * @throws NullPointerException if some of the required fields are null.
         */
        public AggregationRange build() {
            _checkSingleUse();

            return new AggregationRange(this);
        }
    }

}
