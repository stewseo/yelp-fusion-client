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

// typedef: _types.aggregations.SimpleValueAggregate

/**
 * @see <a href=
 * "../../doc-files/api-spec.html#_types.aggregations.SimpleValueAggregate">API
 * specification</a>
 */
@JsonpDeserializable
public class SimpleValueAggregate extends SingleMetricAggregateBase implements AggregateVariant {
    // ---------------------------------------------------------------------------------------------

    /**
     * Json deserializer for {@link SimpleValueAggregate}
     */
    public static final JsonpDeserializer<SimpleValueAggregate> _DESERIALIZER = ObjectBuilderDeserializer
            .lazy(Builder::new, SimpleValueAggregate::setupSimpleValueAggregateDeserializer);

    private SimpleValueAggregate(Builder builder) {
        super(builder);

    }

    public static SimpleValueAggregate of(Function<Builder, ObjectBuilder<SimpleValueAggregate>> fn) {
        return fn.apply(new Builder()).build();
    }

    // ---------------------------------------------------------------------------------------------

    protected static void setupSimpleValueAggregateDeserializer(ObjectDeserializer<Builder> op) {
        setupSingleMetricAggregateBaseDeserializer(op);

    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Aggregate variant kind.
     */
    @Override
    public Aggregate.Kind _aggregateKind() {
        return Aggregate.Kind.SimpleValue;
    }

    /**
     * Builder for {@link SimpleValueAggregate}.
     */

    public static class Builder extends AbstractBuilder<Builder>
            implements
            ObjectBuilder<SimpleValueAggregate> {
        @Override
        protected Builder self() {
            return this;
        }

        /**
         * Builds a {@link SimpleValueAggregate}.
         *
         * @throws NullPointerException if some of the required fields are null.
         */
        public SimpleValueAggregate build() {
            _checkSingleUse();

            return new SimpleValueAggregate(this);
        }
    }

}