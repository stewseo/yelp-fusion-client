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

// typedef: _types.aggregations.SignificantStringTermsAggregate

/**
 * @see <a href=
 * "../../doc-files/api-spec.html#_types.aggregations.SignificantStringTermsAggregate">API
 * specification</a>
 */
@JsonpDeserializable
public class SignificantStringTermsAggregate extends SignificantTermsAggregateBase<SignificantStringTermsBucket>
        implements
        AggregateVariant {
    // ---------------------------------------------------------------------------------------------

    /**
     * Json deserializer for {@link SignificantStringTermsAggregate}
     */
    public static final JsonpDeserializer<SignificantStringTermsAggregate> _DESERIALIZER = ObjectBuilderDeserializer
            .lazy(Builder::new, SignificantStringTermsAggregate::setupSignificantStringTermsAggregateDeserializer);

    private SignificantStringTermsAggregate(Builder builder) {
        super(builder);

    }

    public static SignificantStringTermsAggregate of(
            Function<Builder, ObjectBuilder<SignificantStringTermsAggregate>> fn) {
        return fn.apply(new Builder()).build();
    }

    // ---------------------------------------------------------------------------------------------

    protected static void setupSignificantStringTermsAggregateDeserializer(
            ObjectDeserializer<Builder> op) {
        SignificantTermsAggregateBase.setupSignificantTermsAggregateBaseDeserializer(op,
                SignificantStringTermsBucket._DESERIALIZER);

    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Aggregate variant kind.
     */
    @Override
    public Aggregate.Kind _aggregateKind() {
        return Aggregate.Kind.Sigsterms;
    }

    /**
     * Builder for {@link SignificantStringTermsAggregate}.
     */

    public static class Builder
            extends
            AbstractBuilder<SignificantStringTermsBucket, Builder>
            implements
            ObjectBuilder<SignificantStringTermsAggregate> {
        @Override
        protected Builder self() {
            return this;
        }

        /**
         * Builds a {@link SignificantStringTermsAggregate}.
         *
         * @throws NullPointerException if some of the required fields are null.
         */
        public SignificantStringTermsAggregate build() {
            _checkSingleUse();
            super.tSerializer(null);

            return new SignificantStringTermsAggregate(this);
        }
    }

}
