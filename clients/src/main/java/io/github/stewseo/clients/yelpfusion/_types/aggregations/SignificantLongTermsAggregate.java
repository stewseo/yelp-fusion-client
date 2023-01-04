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
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ObjectBuilder;

import java.util.function.Function;

// typedef: _types.aggregations.SignificantLongTermsAggregate

/**
 * @see <a href=
 * "../../doc-files/api-spec.html#_types.aggregations.SignificantLongTermsAggregate">API
 * specification</a>
 */
@JsonpDeserializable
public class SignificantLongTermsAggregate extends SignificantTermsAggregateBase<SignificantLongTermsBucket>
        implements
        AggregateVariant {
    // ---------------------------------------------------------------------------------------------

    /**
     * Json deserializer for {@link SignificantLongTermsAggregate}
     */
    public static final JsonpDeserializer<SignificantLongTermsAggregate> _DESERIALIZER = ObjectBuilderDeserializer
            .lazy(Builder::new, SignificantLongTermsAggregate::setupSignificantLongTermsAggregateDeserializer);

    private SignificantLongTermsAggregate(Builder builder) {
        super(builder);

    }

    public static SignificantLongTermsAggregate of(Function<Builder, ObjectBuilder<SignificantLongTermsAggregate>> fn) {
        return fn.apply(new Builder()).build();
    }

    // ---------------------------------------------------------------------------------------------

    protected static void setupSignificantLongTermsAggregateDeserializer(
            ObjectDeserializer<Builder> op) {
        setupSignificantTermsAggregateBaseDeserializer(op,
                SignificantLongTermsBucket._DESERIALIZER);

    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Aggregate variant kind.
     */
    @Override
    public Aggregate.Kind _aggregateKind() {
        return Aggregate.Kind.Siglterms;
    }

    /**
     * Builder for {@link SignificantLongTermsAggregate}.
     */

    public static class Builder
            extends
            AbstractBuilder<SignificantLongTermsBucket, Builder>
            implements
            ObjectBuilder<SignificantLongTermsAggregate> {
        @Override
        protected Builder self() {
            return this;
        }

        /**
         * Builds a {@link SignificantLongTermsAggregate}.
         *
         * @throws NullPointerException if some of the required fields are null.
         */
        public SignificantLongTermsAggregate build() {
            _checkSingleUse();
            super.tSerializer(null);

            return new SignificantLongTermsAggregate(this);
        }
    }

}
