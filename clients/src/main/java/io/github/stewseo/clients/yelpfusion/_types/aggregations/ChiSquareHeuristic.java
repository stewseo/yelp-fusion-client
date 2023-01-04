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
import io.github.stewseo.clients.util.ApiTypeHelper;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.util.WithJsonObjectBuilderBase;
import jakarta.json.stream.JsonGenerator;

import java.util.function.Function;

// typedef: _types.aggregations.ChiSquareHeuristic

/**
 * @see <a href=
 * "../../doc-files/api-spec.html#_types.aggregations.ChiSquareHeuristic">API
 * specification</a>
 */
@JsonpDeserializable
public class ChiSquareHeuristic implements JsonpSerializable {
    /**
     * Json deserializer for {@link ChiSquareHeuristic}
     */
    public static final JsonpDeserializer<ChiSquareHeuristic> _DESERIALIZER = ObjectBuilderDeserializer
            .lazy(Builder::new, ChiSquareHeuristic::setupChiSquareHeuristicDeserializer);
    private final boolean backgroundIsSuperset;

    // ---------------------------------------------------------------------------------------------
    private final boolean includeNegatives;

    private ChiSquareHeuristic(Builder builder) {

        this.backgroundIsSuperset = ApiTypeHelper.requireNonNull(builder.backgroundIsSuperset, this,
                "backgroundIsSuperset");
        this.includeNegatives = ApiTypeHelper.requireNonNull(builder.includeNegatives, this, "includeNegatives");

    }

    public static ChiSquareHeuristic of(Function<Builder, ObjectBuilder<ChiSquareHeuristic>> fn) {
        return fn.apply(new Builder()).build();
    }

    protected static void setupChiSquareHeuristicDeserializer(ObjectDeserializer<Builder> op) {

        op.add(Builder::backgroundIsSuperset, JsonpDeserializer.booleanDeserializer(), "background_is_superset");
        op.add(Builder::includeNegatives, JsonpDeserializer.booleanDeserializer(), "include_negatives");

    }

    /**
     * Required - API name: {@code background_is_superset}
     */
    public final boolean backgroundIsSuperset() {
        return this.backgroundIsSuperset;
    }

    /**
     * Required - API name: {@code include_negatives}
     */
    public final boolean includeNegatives() {
        return this.includeNegatives;
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

        generator.writeKey("background_is_superset");
        generator.write(this.backgroundIsSuperset);

        generator.writeKey("include_negatives");
        generator.write(this.includeNegatives);

    }

    // ---------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    /**
     * Builder for {@link ChiSquareHeuristic}.
     */

    public static class Builder extends WithJsonObjectBuilderBase<Builder>
            implements
            ObjectBuilder<ChiSquareHeuristic> {
        private Boolean backgroundIsSuperset;

        private Boolean includeNegatives;

        /**
         * Required - API name: {@code background_is_superset}
         */
        public final Builder backgroundIsSuperset(boolean value) {
            this.backgroundIsSuperset = value;
            return this;
        }

        /**
         * Required - API name: {@code include_negatives}
         */
        public final Builder includeNegatives(boolean value) {
            this.includeNegatives = value;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        /**
         * Builds a {@link ChiSquareHeuristic}.
         *
         * @throws NullPointerException if some of the required fields are null.
         */
        public ChiSquareHeuristic build() {
            _checkSingleUse();

            return new ChiSquareHeuristic(this);
        }
    }

}
