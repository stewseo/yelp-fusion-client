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

import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ApiTypeHelper;
import jakarta.json.stream.JsonGenerator;

// typedef: _types.aggregations.SignificantTermsBucketBase

/**
 * @see <a href=
 * "../../doc-files/api-spec.html#_types.aggregations.SignificantTermsBucketBase">API
 * specification</a>
 */

public abstract class SignificantTermsBucketBase extends MultiBucketBase {
    private final double score;

    private final long bgCount;

    // ---------------------------------------------------------------------------------------------

    protected SignificantTermsBucketBase(AbstractBuilder<?> builder) {
        super(builder);

        this.score = ApiTypeHelper.requireNonNull(builder.score, this, "score");
        this.bgCount = ApiTypeHelper.requireNonNull(builder.bgCount, this, "bgCount");

    }

    // ---------------------------------------------------------------------------------------------
    protected static <BuilderT extends AbstractBuilder<BuilderT>> void setupSignificantTermsBucketBaseDeserializer(
            ObjectDeserializer<BuilderT> op) {
        setupMultiBucketBaseDeserializer(op);
        op.add(AbstractBuilder::score, JsonpDeserializer.doubleDeserializer(), "score");
        op.add(AbstractBuilder::bgCount, JsonpDeserializer.longDeserializer(), "bg_count");

    }

    /**
     * Required - API name: {@code score}
     */
    public final double score() {
        return this.score;
    }

    /**
     * Required - API name: {@code bg_count}
     */
    public final long bgCount() {
        return this.bgCount;
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        super.serializeInternal(generator, mapper);
        generator.writeKey("score");
        generator.write(this.score);

        generator.writeKey("bg_count");
        generator.write(this.bgCount);

    }

    protected abstract static class AbstractBuilder<BuilderT extends AbstractBuilder<BuilderT>>
            extends
            MultiBucketBase.AbstractBuilder<BuilderT> {
        private Double score;

        private Long bgCount;

        /**
         * Required - API name: {@code score}
         */
        public final BuilderT score(double value) {
            this.score = value;
            return self();
        }

        /**
         * Required - API name: {@code bg_count}
         */
        public final BuilderT bgCount(long value) {
            this.bgCount = value;
            return self();
        }

    }

}
