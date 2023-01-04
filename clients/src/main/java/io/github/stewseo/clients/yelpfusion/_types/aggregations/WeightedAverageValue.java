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

// typedef: _types.aggregations.WeightedAverageValue

/**
 * @see <a href=
 * "../../doc-files/api-spec.html#_types.aggregations.WeightedAverageValue">API
 * specification</a>
 */
@JsonpDeserializable
public class WeightedAverageValue implements JsonpSerializable {
    /**
     * Json deserializer for {@link WeightedAverageValue}
     */
    public static final JsonpDeserializer<WeightedAverageValue> _DESERIALIZER = ObjectBuilderDeserializer
            .lazy(Builder::new, WeightedAverageValue::setupWeightedAverageValueDeserializer);
    @Nullable
    private final String field;

    // ---------------------------------------------------------------------------------------------
    @Nullable
    private final Double missing;

    private WeightedAverageValue(Builder builder) {

        this.field = builder.field;
        this.missing = builder.missing;

    }

    public static WeightedAverageValue of(Function<Builder, ObjectBuilder<WeightedAverageValue>> fn) {
        return fn.apply(new Builder()).build();
    }

    protected static void setupWeightedAverageValueDeserializer(ObjectDeserializer<Builder> op) {

        op.add(Builder::field, JsonpDeserializer.stringDeserializer(), "field");
        op.add(Builder::missing, JsonpDeserializer.doubleDeserializer(), "missing");

    }

    /**
     * API name: {@code field}
     */
    @Nullable
    public final String field() {
        return this.field;
    }

    /**
     * API name: {@code missing}
     */
    @Nullable
    public final Double missing() {
        return this.missing;
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

        if (this.field != null) {
            generator.writeKey("field");
            generator.write(this.field);

        }
        if (this.missing != null) {
            generator.writeKey("missing");
            generator.write(this.missing);

        }

    }

    // ---------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    /**
     * Builder for {@link WeightedAverageValue}.
     */

    public static class Builder extends WithJsonObjectBuilderBase<Builder>
            implements
            ObjectBuilder<WeightedAverageValue> {
        @Nullable
        private String field;

        @Nullable
        private Double missing;

        /**
         * API name: {@code field}
         */
        public final Builder field(@Nullable String value) {
            this.field = value;
            return this;
        }

        /**
         * API name: {@code missing}
         */
        public final Builder missing(@Nullable Double value) {
            this.missing = value;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        /**
         * Builds a {@link WeightedAverageValue}.
         *
         * @throws NullPointerException if some of the required fields are null.
         */
        public WeightedAverageValue build() {
            _checkSingleUse();

            return new WeightedAverageValue(this);
        }
    }

}
