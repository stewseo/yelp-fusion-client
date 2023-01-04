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
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ApiTypeHelper;
import io.github.stewseo.clients.util.ObjectBuilder;
import jakarta.json.stream.JsonGenerator;

import java.util.function.Function;

// typedef: _types.aggregations.SignificantStringTermsBucket

/**
 * @see <a href=
 * "../../doc-files/api-spec.html#_types.aggregations.SignificantStringTermsBucket">API
 * specification</a>
 */
@JsonpDeserializable
public class SignificantStringTermsBucket extends SignificantTermsBucketBase {
    /**
     * Json deserializer for {@link SignificantStringTermsBucket}
     */
    public static final JsonpDeserializer<SignificantStringTermsBucket> _DESERIALIZER = ObjectBuilderDeserializer
            .lazy(Builder::new, SignificantStringTermsBucket::setupSignificantStringTermsBucketDeserializer);

    // ---------------------------------------------------------------------------------------------
    private final String key;

    private SignificantStringTermsBucket(Builder builder) {
        super(builder);

        this.key = ApiTypeHelper.requireNonNull(builder.key, this, "key");

    }

    public static SignificantStringTermsBucket of(Function<Builder, ObjectBuilder<SignificantStringTermsBucket>> fn) {
        return fn.apply(new Builder()).build();
    }

    protected static void setupSignificantStringTermsBucketDeserializer(
            ObjectDeserializer<Builder> op) {
        setupSignificantTermsBucketBaseDeserializer(op);
        op.add(Builder::key, JsonpDeserializer.stringDeserializer(), "key");

    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Required - API name: {@code key}
     */
    public final String key() {
        return this.key;
    }

    // ---------------------------------------------------------------------------------------------

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        super.serializeInternal(generator, mapper);
        generator.writeKey("key");
        generator.write(this.key);

    }

    /**
     * Builder for {@link SignificantStringTermsBucket}.
     */

    public static class Builder extends AbstractBuilder<Builder>
            implements
            ObjectBuilder<SignificantStringTermsBucket> {
        private String key;

        /**
         * Required - API name: {@code key}
         */
        public final Builder key(String value) {
            this.key = value;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        /**
         * Builds a {@link SignificantStringTermsBucket}.
         *
         * @throws NullPointerException if some of the required fields are null.
         */
        public SignificantStringTermsBucket build() {
            _checkSingleUse();

            return new SignificantStringTermsBucket(this);
        }
    }

}
