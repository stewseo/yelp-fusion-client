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
package io.github.stewseo.client._types.aggregations;

import io.github.stewseo.client.json.JsonData;
import io.github.stewseo.client.json.JsonEnum;
import io.github.stewseo.client.json.JsonpDeserializable;
import io.github.stewseo.client.json.JsonpDeserializer;
import io.github.stewseo.client.json.JsonpMapper;
import io.github.stewseo.client.json.JsonpSerializable;
import io.github.stewseo.client.json.JsonpUtils;
import io.github.stewseo.client.json.ObjectBuilderDeserializer;
import io.github.stewseo.client.json.ObjectDeserializer;
import io.github.stewseo.client.util.ApiTypeHelper;
import io.github.stewseo.client.util.ObjectBuilder;
import io.github.stewseo.client.util.OpenTaggedUnion;
import io.github.stewseo.client.util.TaggedUnionUtils;
import io.github.stewseo.client.util.WithJsonObjectBuilderBase;
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.Function;

// typedef: _types.aggregations.AggregationContainer

/**
 * @see <a href=
 * "../../doc-files/api-spec.html#_types.aggregations.AggregationContainer">API
 * specification</a>
 */
@SuppressWarnings("unused")
@JsonpDeserializable
public class Aggregation implements OpenTaggedUnion<Aggregation.Kind, Object>, JsonpSerializable {

    private final Kind _kind;
    private final Object _value;
    private final Map<String, Aggregation> aggregations;
    private final Map<String, JsonData> meta;
    @Nullable
    private final String _customKind;

    public Aggregation(AggregationVariant value) {

        this._kind = ApiTypeHelper.requireNonNull(value._aggregationKind(), this, "<variant kind>");
        this._value = ApiTypeHelper.requireNonNull(value, this, "<variant value>");
        this._customKind = null;

        this.aggregations = null;
        this.meta = null;

    }

    private Aggregation(Builder builder) {

        this._kind = ApiTypeHelper.requireNonNull(builder._kind, builder, "<variant kind>");
        this._value = ApiTypeHelper.requireNonNull(builder._value, builder, "<variant value>");
        this._customKind = builder._customKind;

        this.aggregations = ApiTypeHelper.unmodifiable(builder.aggregations);
        this.meta = ApiTypeHelper.unmodifiable(builder.meta);

    }

    public static Aggregation of(Function<Builder, ObjectBuilder<Aggregation>> fn) {
        return fn.apply(new Builder()).build();
    }

    protected static void setupAggregationDeserializer(ObjectDeserializer<Builder> op) {

        op.add(Builder::aggregations, JsonpDeserializer.stringMapDeserializer(_DESERIALIZER), "aggregations", "aggs");
        op.add(Builder::meta, JsonpDeserializer.stringMapDeserializer(JsonData._DESERIALIZER), "meta");
        op.add(Builder::dateHistogram, DateHistogramAggregation._DESERIALIZER, "date_histogram");
        op.add(Builder::dateRange, DateRangeAggregation._DESERIALIZER, "date_range");
        op.add(Builder::histogram, HistogramAggregation._DESERIALIZER, "histogram");
        op.add(Builder::terms, TermsAggregation._DESERIALIZER, "terms");
        op.setUnknownFieldHandler((builder, name, parser, mapper) -> {
            JsonpUtils.ensureCustomVariantsAllowed(parser, mapper);
            builder._custom(name, JsonData._DESERIALIZER.deserialize(parser, mapper));
        });

    }

    @Override
    public final Kind _kind() {
        return _kind;
    }

    @Override
    public final Object _get() {
        return _value;
    }

    /**
     * Sub-aggregations for this aggregation. Only applies to bucket aggregations.
     * <p>
     * API name: {@code aggregations}
     */
    public final Map<String, Aggregation> aggregations() {
        return this.aggregations;
    }

    /**
     * API name: {@code meta}
     */
    public final Map<String, JsonData> meta() {
        return this.meta;
    }

    /**
     * Is this variant instance of kind {@code date_histogram}?
     */
    public boolean isDateHistogram() {
        return _kind == Kind.DateHistogram;
    }

    /**
     * Get the {@code date_histogram} variant value.
     *
     * @throws IllegalStateException if the current variant is not of the {@code date_histogram} kind.
     */
    public DateHistogramAggregation dateHistogram() {
        return TaggedUnionUtils.get(this, Kind.DateHistogram);
    }

    /**
     * Is this variant instance of kind {@code date_range}?
     */
    public boolean isDateRange() {
        return _kind == Kind.DateRange;
    }

    /**
     * Get the {@code date_range} variant value.
     *
     * @throws IllegalStateException if the current variant is not of the {@code date_range} kind.
     */
    public DateRangeAggregation dateRange() {
        return TaggedUnionUtils.get(this, Kind.DateRange);
    }

    /**
     * Is this variant instance of kind {@code histogram}?
     */
    public boolean isHistogram() {
        return _kind == Kind.Histogram;
    }

    /**
     * Get the {@code histogram} variant value.
     *
     * @throws IllegalStateException if the current variant is not of the {@code histogram} kind.
     */
    public HistogramAggregation histogram() {
        return TaggedUnionUtils.get(this, Kind.Histogram);
    }

    /**
     * Is this variant instance of kind {@code multi_terms}?
     */
    public boolean isMultiTerms() {
        return _kind == Kind.MultiTerms;
    }

    /**
     * Get the {@code multi_terms} variant value.
     *
     * @throws IllegalStateException if the current variant is not of the {@code multi_terms} kind.
     */
    public MultiTermsAggregation multiTerms() {
        return TaggedUnionUtils.get(this, Kind.MultiTerms);
    }

    /**
     * Is this variant instance of kind {@code range}?
     */
    public boolean isRange() {
        return _kind == Kind.Range;
    }

    /**
     * Get the {@code range} variant value.
     *
     * @throws IllegalStateException if the current variant is not of the {@code range} kind.
     */
    public RangeAggregation range() {
        return TaggedUnionUtils.get(this, Kind.Range);
    }

    /**
     * Is this variant instance of kind {@code significant_terms}?
     */
    public boolean isSignificantTerms() {
        return _kind == Kind.SignificantTerms;
    }

    /**
     * Get the {@code significant_terms} variant value.
     *
     * @throws IllegalStateException if the current variant is not of the {@code significant_terms}
     *                               kind.
     */
    public SignificantTermsAggregation significantTerms() {
        return TaggedUnionUtils.get(this, Kind.SignificantTerms);
    }

    /**
     * Is this variant instance of kind {@code significant_text}?
     */
    public boolean isSignificantText() {
        return _kind == Kind.SignificantText;
    }

    /**
     * Get the {@code significant_text} variant value.
     *
     * @throws IllegalStateException if the current variant is not of the {@code significant_text}
     *                               kind.
     */
    public SignificantTextAggregation significantText() {
        return TaggedUnionUtils.get(this, Kind.SignificantText);
    }

    /**
     * Is this variant instance of kind {@code terms}?
     */
    public boolean isTerms() {
        return _kind == Kind.Terms;
    }

    /**
     * Get the {@code terms} variant value.
     *
     * @throws IllegalStateException if the current variant is not of the {@code terms} kind.
     */
    public TermsAggregation terms() {
        return TaggedUnionUtils.get(this, Kind.Terms);
    }

    /**
     * Is this variant instance of kind {@code weighted_avg}?
     */
    public boolean isWeightedAvg() {
        return _kind == Kind.WeightedAvg;
    }

    /**
     * Get the {@code weighted_avg} variant value.
     *
     * @throws IllegalStateException if the current variant is not of the {@code weighted_avg} kind.
     */
    public WeightedAverageAggregation weightedAvg() {
        return TaggedUnionUtils.get(this, Kind.WeightedAvg);
    }

    /**
     * Is this a custom {@code Aggregation} defined by a plugin?
     */
    public boolean _isCustom() {
        return _kind == Kind._Custom;
    }

    /**
     * Get the actual kind when {@code _kind()} equals {@link Kind#_Custom}
     * (plugin-defined variant).
     */
    @Nullable
    public final String _customKind() {
        return _customKind;
    }

    /**
     * Get the custom plugin-defined variant value.
     *
     * @throws IllegalStateException if the current variant is not {@link Kind#_Custom}.
     */
    public JsonData _custom() {
        return TaggedUnionUtils.get(this, Kind._Custom);
    }

    @Override
    public void serialize(JsonGenerator generator, JsonpMapper mapper) {

        generator.writeStartObject();

        if (ApiTypeHelper.isDefined(this.aggregations)) {
            generator.writeKey("aggregations");
            generator.writeStartObject();
            for (Map.Entry<String, Aggregation> item0 : this.aggregations.entrySet()) {
                generator.writeKey(item0.getKey());
                item0.getValue().serialize(generator, mapper);

            }
            generator.writeEnd();

        }
        if (ApiTypeHelper.isDefined(this.meta)) {
            generator.writeKey("meta");
            generator.writeStartObject();
            for (Map.Entry<String, JsonData> item0 : this.meta.entrySet()) {
                generator.writeKey(item0.getKey());
                item0.getValue().serialize(generator, mapper);

            }
            generator.writeEnd();

        }

        generator.writeKey(_kind == Kind._Custom ? _customKind : _kind.jsonValue());
        if (_value instanceof JsonpSerializable) {
            ((JsonpSerializable) _value).serialize(generator, mapper);
        }

        generator.writeEnd();

    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    public enum Kind implements JsonEnum {
        AdjacencyMatrix("adjacency_matrix"),

        AutoDateHistogram("auto_date_histogram"),

        Avg("avg"),

        AvgBucket("avg_bucket"),

        Boxplot("boxplot"),

        BucketScript("bucket_script"),

        BucketSelector("bucket_selector"),

        BucketSort("bucket_sort"),

        BucketCountKsTest("bucket_count_ks_test"),

        BucketCorrelation("bucket_correlation"),

        Cardinality("cardinality"),

        CategorizeText("categorize_text"),

        Children("children"),

        Composite("composite"),

        CumulativeCardinality("cumulative_cardinality"),

        CumulativeSum("cumulative_sum"),

        DateHistogram("date_histogram"),

        DateRange("date_range"),

        Derivative("derivative"),

        DiversifiedSampler("diversified_sampler"),

        ExtendedStats("extended_stats"),

        ExtendedStatsBucket("extended_stats_bucket"),

        Filter("filter"),

        Filters("filters"),

        GeoBounds("geo_bounds"),

        GeoCentroid("geo_centroid"),

        GeoDistance("geo_distance"),

        GeohashGrid("geohash_grid"),

        GeoLine("geo_line"),

        GeotileGrid("geotile_grid"),

        GeohexGrid("geohex_grid"),

        Global("global"),

        Histogram("histogram"),

        IpRange("ip_range"),

        IpPrefix("ip_prefix"),

        Inference("inference"),

        MatrixStats("matrix_stats"),

        Max("max"),

        MaxBucket("max_bucket"),

        MedianAbsoluteDeviation("median_absolute_deviation"),

        Min("min"),

        MinBucket("min_bucket"),

        Missing("missing"),

        MovingAvg("moving_avg"),

        MovingPercentiles("moving_percentiles"),

        MovingFn("moving_fn"),

        MultiTerms("multi_terms"),

        Nested("nested"),

        Normalize("normalize"),

        Parent("parent"),

        PercentileRanks("percentile_ranks"),

        Percentiles("percentiles"),

        PercentilesBucket("percentiles_bucket"),

        Range("range"),

        RareTerms("rare_terms"),

        Rate("rate"),

        ReverseNested("reverse_nested"),

        Sampler("sampler"),

        ScriptedMetric("scripted_metric"),

        SerialDiff("serial_diff"),

        SignificantTerms("significant_terms"),

        SignificantText("significant_text"),

        Stats("stats"),

        StatsBucket("stats_bucket"),

        StringStats("string_stats"),

        Sum("sum"),

        SumBucket("sum_bucket"),

        Terms("terms"),

        TopHits("top_hits"),

        TTest("t_test"),

        TopMetrics("top_metrics"),

        ValueCount("value_count"),

        WeightedAvg("weighted_avg"),

        VariableWidthHistogram("variable_width_histogram"),

        /**
         * A custom {@code Aggregation} defined by a plugin
         */
        _Custom(null);

        private final String jsonValue;

        Kind(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String jsonValue() {
            return this.jsonValue;
        }

    }

    @SuppressWarnings("UnusedReturnValue")
    public static class Builder extends WithJsonObjectBuilderBase<Builder> {
        private Kind _kind;
        private Object _value;
        private String _customKind;

        @Nullable
        private Map<String, Aggregation> aggregations;

        @Nullable
        private Map<String, JsonData> meta;

        /**
         * Sub-aggregations for this aggregation. Only applies to bucket aggregations.
         * <p>
         * API name: {@code aggregations}
         * <p>
         * Adds all entries of <code>map</code> to <code>aggregations</code>.
         */
        public final Builder aggregations(Map<String, Aggregation> map) {
            this.aggregations = _mapPutAll(this.aggregations, map);
            return this;
        }

        /**
         * Sub-aggregations for this aggregation. Only applies to bucket aggregations.
         * <p>
         * API name: {@code aggregations}
         * <p>
         * Adds an entry to <code>aggregations</code>.
         */
        public final Builder aggregations(String key, Aggregation value) {
            this.aggregations = _mapPut(this.aggregations, key, value);
            return this;
        }

        /**
         * Sub-aggregations for this aggregation. Only applies to bucket aggregations.
         * <p>
         * API name: {@code aggregations}
         * <p>
         * Adds an entry to <code>aggregations</code> using a builder lambda.
         */
        public final Builder aggregations(String key, Function<Builder, ObjectBuilder<Aggregation>> fn) {
            return aggregations(key, fn.apply(new Builder()).build());
        }

        /**
         * API name: {@code meta}
         * <p>
         * Adds all entries of <code>map</code> to <code>meta</code>.
         */
        public final Builder meta(Map<String, JsonData> map) {
            this.meta = _mapPutAll(this.meta, map);
            return this;
        }

        /**
         * API name: {@code meta}
         * <p>
         * Adds an entry to <code>meta</code>.
         */
        public final Builder meta(String key, JsonData value) {
            this.meta = _mapPut(this.meta, key, value);
            return this;
        }

        public ContainerBuilder dateHistogram(DateHistogramAggregation v) {
            this._kind = Kind.DateHistogram;
            this._value = v;
            return new ContainerBuilder();
        }

        public ContainerBuilder dateHistogram(
                Function<DateHistogramAggregation.Builder, ObjectBuilder<DateHistogramAggregation>> fn) {
            return this.dateHistogram(fn.apply(new DateHistogramAggregation.Builder()).build());
        }

        public ContainerBuilder dateRange(DateRangeAggregation v) {
            this._kind = Kind.DateRange;
            this._value = v;
            return new ContainerBuilder();
        }

        public ContainerBuilder dateRange(
                Function<DateRangeAggregation.Builder, ObjectBuilder<DateRangeAggregation>> fn) {
            return this.dateRange(fn.apply(new DateRangeAggregation.Builder()).build());
        }

        public ContainerBuilder histogram(HistogramAggregation v) {
            this._kind = Kind.Histogram;
            this._value = v;
            return new ContainerBuilder();
        }

        public ContainerBuilder histogram(
                Function<HistogramAggregation.Builder, ObjectBuilder<HistogramAggregation>> fn) {
            return this.histogram(fn.apply(new HistogramAggregation.Builder()).build());
        }

        public ContainerBuilder multiTerms(MultiTermsAggregation v) {
            this._kind = Kind.MultiTerms;
            this._value = v;
            return new ContainerBuilder();
        }

        public ContainerBuilder multiTerms(
                Function<MultiTermsAggregation.Builder, ObjectBuilder<MultiTermsAggregation>> fn) {
            return this.multiTerms(fn.apply(new MultiTermsAggregation.Builder()).build());
        }

        public ContainerBuilder range(RangeAggregation v) {
            this._kind = Kind.Range;
            this._value = v;
            return new ContainerBuilder();
        }

        public ContainerBuilder range(Function<RangeAggregation.Builder, ObjectBuilder<RangeAggregation>> fn) {
            return this.range(fn.apply(new RangeAggregation.Builder()).build());
        }

        public ContainerBuilder significantTerms(SignificantTermsAggregation v) {
            this._kind = Kind.SignificantTerms;
            this._value = v;
            return new ContainerBuilder();
        }

        public ContainerBuilder significantTerms(
                Function<SignificantTermsAggregation.Builder, ObjectBuilder<SignificantTermsAggregation>> fn) {
            return this.significantTerms(fn.apply(new SignificantTermsAggregation.Builder()).build());
        }

        public ContainerBuilder significantText(SignificantTextAggregation v) {
            this._kind = Kind.SignificantText;
            this._value = v;
            return new ContainerBuilder();
        }

        public ContainerBuilder significantText(
                Function<SignificantTextAggregation.Builder, ObjectBuilder<SignificantTextAggregation>> fn) {
            return this.significantText(fn.apply(new SignificantTextAggregation.Builder()).build());
        }


        public ContainerBuilder terms(TermsAggregation v) {
            this._kind = Kind.Terms;
            this._value = v;
            return new ContainerBuilder();
        }

        public ContainerBuilder terms(Function<TermsAggregation.Builder, ObjectBuilder<TermsAggregation>> fn) {
            return this.terms(fn.apply(new TermsAggregation.Builder()).build());
        }

        public ContainerBuilder weightedAvg(WeightedAverageAggregation v) {
            this._kind = Kind.WeightedAvg;
            this._value = v;
            return new ContainerBuilder();
        }

        public ContainerBuilder weightedAvg(
                Function<WeightedAverageAggregation.Builder, ObjectBuilder<WeightedAverageAggregation>> fn) {
            return this.weightedAvg(fn.apply(new WeightedAverageAggregation.Builder()).build());
        }


        /**
         * Define this {@code Aggregation} as a plugin-defined variant.
         *
         * @param name the plugin-defined identifier
         * @param data the data for this custom {@code Aggregation}. It is converted
         *             internally to {@link JsonData}.
         */
        public ContainerBuilder _custom(String name, Object data) {
            this._kind = Kind._Custom;
            this._customKind = name;
            this._value = JsonData.of(data);
            return new ContainerBuilder();
        }


        protected Aggregation build() {
            _checkSingleUse();
            return new Aggregation(this);
        }

        @Override
        protected Builder self() {
            return this;
        }

        public class ContainerBuilder implements ObjectBuilder<Aggregation> {

            /**
             * Sub-aggregations for this aggregation. Only applies to bucket aggregations.
             * <p>
             * API name: {@code aggregations}
             * <p>
             * Adds all entries of <code>map</code> to <code>aggregations</code>.
             */
            public final ContainerBuilder aggregations(Map<String, Aggregation> map) {
                Builder.this.aggregations = _mapPutAll(Builder.this.aggregations, map);
                return this;
            }

            /**
             * Sub-aggregations for this aggregation. Only applies to bucket aggregations.
             * <p>
             * API name: {@code aggregations}
             * <p>
             * Adds an entry to <code>aggregations</code>.
             */
            public final ContainerBuilder aggregations(String key, Aggregation value) {
                Builder.this.aggregations = _mapPut(Builder.this.aggregations, key, value);
                return this;
            }

            /**
             * Sub-aggregations for this aggregation. Only applies to bucket aggregations.
             * <p>
             * API name: {@code aggregations}
             * <p>
             * Adds an entry to <code>aggregations</code> using a builder lambda.
             */
            public final ContainerBuilder aggregations(String key,
                                                       Function<Builder, ObjectBuilder<Aggregation>> fn) {
                return aggregations(key, fn.apply(new Builder()).build());
            }

            /**
             * API name: {@code meta}
             * <p>
             * Adds all entries of <code>map</code> to <code>meta</code>.
             */
            public final ContainerBuilder meta(Map<String, JsonData> map) {
                Builder.this.meta = _mapPutAll(Builder.this.meta, map);
                return this;
            }

            /**
             * API name: {@code meta}
             * <p>
             * Adds an entry to <code>meta</code>.
             */
            public final ContainerBuilder meta(String key, JsonData value) {
                Builder.this.meta = _mapPut(Builder.this.meta, key, value);
                return this;
            }

            public Aggregation build() {
                return Builder.this.build();
            }
        }
    }

    public static final JsonpDeserializer<Aggregation> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
            Aggregation::setupAggregationDeserializer, Builder::build);
}

