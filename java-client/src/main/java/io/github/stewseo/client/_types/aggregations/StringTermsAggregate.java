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

package io.github.stewseo.client._types.aggregations;

import io.github.stewseo.client.json.JsonpDeserializable;
import io.github.stewseo.client.json.JsonpDeserializer;
import io.github.stewseo.client.json.ObjectBuilderDeserializer;
import io.github.stewseo.client.json.ObjectDeserializer;
import io.github.stewseo.client.util.ObjectBuilder;

import java.util.function.Function;

// typedef: _types.aggregations.StringTermsAggregate

/**
 * Result of a <code>terms</code> aggregation when the field is a string.
 *
 * @see <a href=
 * "../../doc-files/api-spec.html#_types.aggregations.StringTermsAggregate">API
 * specification</a>
 */
@JsonpDeserializable
public class StringTermsAggregate extends TermsAggregateBase<StringTermsBucket> implements AggregateVariant {
    // ---------------------------------------------------------------------------------------------

    /**
     * Json deserializer for {@link StringTermsAggregate}
     */
    public static final JsonpDeserializer<StringTermsAggregate> _DESERIALIZER = ObjectBuilderDeserializer
            .lazy(Builder::new, StringTermsAggregate::setupStringTermsAggregateDeserializer);

    private StringTermsAggregate(Builder builder) {
        super(builder);

    }

    public static StringTermsAggregate of(Function<Builder, ObjectBuilder<StringTermsAggregate>> fn) {
        return fn.apply(new Builder()).build();
    }

    // ---------------------------------------------------------------------------------------------

    protected static void setupStringTermsAggregateDeserializer(ObjectDeserializer<Builder> op) {
        setupTermsAggregateBaseDeserializer(op, StringTermsBucket._DESERIALIZER);

    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Aggregate variant kind.
     */
    @Override
    public Aggregate.Kind _aggregateKind() {
        return Aggregate.Kind.Sterms;
    }

    /**
     * Builder for {@link StringTermsAggregate}.
     */

    public static class Builder extends AbstractBuilder<StringTermsBucket, Builder>
            implements
            ObjectBuilder<StringTermsAggregate> {
        @Override
        protected Builder self() {
            return this;
        }

        /**
         * Builds a {@link StringTermsAggregate}.
         *
         * @throws NullPointerException if some of the required fields are null.
         */
        public StringTermsAggregate build() {
            _checkSingleUse();
            super.tBucketSerializer(null);

            return new StringTermsAggregate(this);
        }
    }

}
