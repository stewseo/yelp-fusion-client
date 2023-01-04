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

// typedef: _types.aggregations.DateRangeAggregate

/**
 * Result of a <code>date_range</code> aggregation. Same format as a for a
 * <code>range</code> aggregation: <code>from</code> and <code>to</code> in
 * <code>buckets</code> are milliseconds since the Epoch, represented as a
 * floating point number.
 *
 * @see <a href=
 * "../../doc-files/api-spec.html#_types.aggregations.DateRangeAggregate">API
 * specification</a>
 */
@JsonpDeserializable
public class DateRangeAggregate extends RangeAggregate implements AggregateVariant {
    // ---------------------------------------------------------------------------------------------

    /**
     * Json deserializer for {@link DateRangeAggregate}
     */
    public static final JsonpDeserializer<DateRangeAggregate> _DESERIALIZER = ObjectBuilderDeserializer
            .lazy(Builder::new, DateRangeAggregate::setupDateRangeAggregateDeserializer);

    private DateRangeAggregate(Builder builder) {
        super(builder);

    }

    public static DateRangeAggregate of(Function<Builder, ObjectBuilder<DateRangeAggregate>> fn) {
        return fn.apply(new Builder()).build();
    }

    // ---------------------------------------------------------------------------------------------

    protected static void setupDateRangeAggregateDeserializer(ObjectDeserializer<Builder> op) {
        setupRangeAggregateDeserializer(op);

    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Aggregate variant kind.
     */
    @Override
    public Aggregate.Kind _aggregateKind() {
        return Aggregate.Kind.DateRange;
    }

    /**
     * Builder for {@link DateRangeAggregate}.
     */

    public static class Builder extends AbstractBuilder<Builder>
            implements
            ObjectBuilder<DateRangeAggregate> {
        @Override
        protected Builder self() {
            return this;
        }

        /**
         * Builds a {@link DateRangeAggregate}.
         *
         * @throws NullPointerException if some of the required fields are null.
         */
        public DateRangeAggregate build() {
            _checkSingleUse();

            return new DateRangeAggregate(this);
        }
    }

}