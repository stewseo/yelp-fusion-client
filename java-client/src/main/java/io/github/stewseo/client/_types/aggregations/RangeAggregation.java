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
import io.github.stewseo.client.json.JsonpMapper;
import io.github.stewseo.client.json.ObjectBuilderDeserializer;
import io.github.stewseo.client.json.ObjectDeserializer;
import io.github.stewseo.client.util.ApiTypeHelper;
import io.github.stewseo.client.util.ObjectBuilder;
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Function;

// typedef: _types.aggregations.RangeAggregation

/**
 * @see <a href=
 * "../../doc-files/api-spec.html#_types.aggregations.RangeAggregation">API
 * specification</a>
 */
@JsonpDeserializable
public class RangeAggregation extends BucketAggregationBase implements AggregationVariant {
    /**
     * Json deserializer for {@link RangeAggregation}
     */
    public static final JsonpDeserializer<RangeAggregation> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
            RangeAggregation::setupRangeAggregationDeserializer);
    @Nullable
    private final String field;
    @Nullable
    private final Integer missing;
    private final List<AggregationRange> ranges;
    @Nullable
    private final Boolean keyed;

    // ---------------------------------------------------------------------------------------------
    @Nullable
    private final String format;

    private RangeAggregation(Builder builder) {
        super(builder);

        this.field = builder.field;
        this.missing = builder.missing;
        this.ranges = ApiTypeHelper.unmodifiable(builder.ranges);
        this.keyed = builder.keyed;
        this.format = builder.format;

    }

    public static RangeAggregation of(Function<Builder, ObjectBuilder<RangeAggregation>> fn) {
        return fn.apply(new Builder()).build();
    }

    protected static void setupRangeAggregationDeserializer(ObjectDeserializer<Builder> op) {
        BucketAggregationBase.setupBucketAggregationBaseDeserializer(op);
        op.add(Builder::field, JsonpDeserializer.stringDeserializer(), "field");
        op.add(Builder::missing, JsonpDeserializer.integerDeserializer(), "missing");
        op.add(Builder::ranges, JsonpDeserializer.arrayDeserializer(AggregationRange._DESERIALIZER), "ranges");
        op.add(Builder::keyed, JsonpDeserializer.booleanDeserializer(), "keyed");
        op.add(Builder::format, JsonpDeserializer.stringDeserializer(), "format");

    }

    /**
     * Aggregation variant kind.
     */
    @Override
    public Aggregation.Kind _aggregationKind() {
        return Aggregation.Kind.Range;
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
    public final Integer missing() {
        return this.missing;
    }

    /**
     * API name: {@code ranges}
     */
    public final List<AggregationRange> ranges() {
        return this.ranges;
    }

    /**
     * API name: {@code keyed}
     */
    @Nullable
    public final Boolean keyed() {
        return this.keyed;
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * API name: {@code format}
     */
    @Nullable
    public final String format() {
        return this.format;
    }

    // ---------------------------------------------------------------------------------------------

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        super.serializeInternal(generator, mapper);
        if (this.field != null) {
            generator.writeKey("field");
            generator.write(this.field);

        }
        if (this.missing != null) {
            generator.writeKey("missing");
            generator.write(this.missing);

        }
        if (ApiTypeHelper.isDefined(this.ranges)) {
            generator.writeKey("ranges");
            generator.writeStartArray();
            for (AggregationRange item0 : this.ranges) {
                item0.serialize(generator, mapper);

            }
            generator.writeEnd();

        }

        if (this.keyed != null) {
            generator.writeKey("keyed");
            generator.write(this.keyed);

        }
        if (this.format != null) {
            generator.writeKey("format");
            generator.write(this.format);

        }

    }

    /**
     * Builder for {@link RangeAggregation}.
     */

    public static class Builder extends AbstractBuilder<Builder>
            implements
            ObjectBuilder<RangeAggregation> {
        @Nullable
        private String field;

        @Nullable
        private Integer missing;

        @Nullable
        private List<AggregationRange> ranges;

        @Nullable
        private Boolean keyed;

        @Nullable
        private String format;

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
        public final Builder missing(@Nullable Integer value) {
            this.missing = value;
            return this;
        }

        /**
         * API name: {@code ranges}
         * <p>
         * Adds all elements of <code>list</code> to <code>ranges</code>.
         */
        public final Builder ranges(List<AggregationRange> list) {
            this.ranges = _listAddAll(this.ranges, list);
            return this;
        }

        /**
         * API name: {@code ranges}
         * <p>
         * Adds one or more values to <code>ranges</code>.
         */
        public final Builder ranges(AggregationRange value, AggregationRange... values) {
            this.ranges = _listAdd(this.ranges, value, values);
            return this;
        }

        /**
         * API name: {@code ranges}
         * <p>
         * Adds a value to <code>ranges</code> using a builder lambda.
         */
        public final Builder ranges(Function<AggregationRange.Builder, ObjectBuilder<AggregationRange>> fn) {
            return ranges(fn.apply(new AggregationRange.Builder()).build());
        }


        /**
         * API name: {@code keyed}
         */
        public final Builder keyed(@Nullable Boolean value) {
            this.keyed = value;
            return this;
        }

        /**
         * API name: {@code format}
         */
        public final Builder format(@Nullable String value) {
            this.format = value;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        /**
         * Builds a {@link RangeAggregation}.
         *
         * @throws NullPointerException if some of the required fields are null.
         */
        public RangeAggregation build() {
            _checkSingleUse();

            return new RangeAggregation(this);
        }
    }

}
