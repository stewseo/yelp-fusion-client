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

import io.github.stewseo.client.json.ExternallyTaggedUnion;
import io.github.stewseo.client.json.JsonData;
import io.github.stewseo.client.json.JsonEnum;
import io.github.stewseo.client.json.JsonpDeserializer;
import io.github.stewseo.client.json.JsonpMapper;
import io.github.stewseo.client.json.JsonpSerializable;
import io.github.stewseo.client.json.JsonpUtils;
import io.github.stewseo.client.util.ApiTypeHelper;
import io.github.stewseo.client.util.ObjectBuilder;
import io.github.stewseo.client.util.ObjectBuilderBase;
import io.github.stewseo.client.util.OpenTaggedUnion;
import io.github.stewseo.client.util.TaggedUnionUtils;
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

// typedef: _types.aggregations.Aggregate

/**
 *
 * @see <a href=
 *      "../../doc-files/api-spec.html#_types.aggregations.Aggregate">API
 *      specification</a>
 */

public class Aggregate implements OpenTaggedUnion<Aggregate.Kind, Object>, JsonpSerializable {

	/**
	 * {@link Aggregate} variant kinds.
	 * 
	 * @see <a href=
	 *      "../../doc-files/api-spec.html#_types.aggregations.Aggregate">API
	 *      specification</a>
	 */

	public enum Kind implements JsonEnum {
		AdjacencyMatrix("adjacency_matrix"),

		AutoDateHistogram("auto_date_histogram"),

		Avg("avg"),

		BoxPlot("box_plot"),

		BucketMetricValue("bucket_metric_value"),

		Cardinality("cardinality"),

		Children("children"),

		Composite("composite"),

		SimpleLongValue("simple_long_value"),

		DateHistogram("date_histogram"),

		DateRange("date_range"),

		Derivative("derivative"),

		Dterms("dterms"),

		ExtendedStats("extended_stats"),

		ExtendedStatsBucket("extended_stats_bucket"),

		Filter("filter"),

		Filters("filters"),

		GeoBounds("geo_bounds"),

		GeoCentroid("geo_centroid"),

		GeoDistance("geo_distance"),

		GeohashGrid("geohash_grid"),

		GeohexGrid("geohex_grid"),

		GeoLine("geo_line"),

		GeotileGrid("geotile_grid"),

		Global("global"),

		HdrPercentileRanks("hdr_percentile_ranks"),

		HdrPercentiles("hdr_percentiles"),

		Histogram("histogram"),

		Inference("inference"),

		IpPrefix("ip_prefix"),

		IpRange("ip_range"),

		Lrareterms("lrareterms"),

		Lterms("lterms"),

		MatrixStats("matrix_stats"),

		Max("max"),

		MedianAbsoluteDeviation("median_absolute_deviation"),

		Min("min"),

		Missing("missing"),

		MultiTerms("multi_terms"),

		Nested("nested"),

		Parent("parent"),

		PercentilesBucket("percentiles_bucket"),

		Range("range"),

		Rate("rate"),

		ReverseNested("reverse_nested"),

		Sampler("sampler"),

		ScriptedMetric("scripted_metric"),

		Siglterms("siglterms"),

		Sigsterms("sigsterms"),

		SimpleValue("simple_value"),

		Stats("stats"),

		StatsBucket("stats_bucket"),

		Srareterms("srareterms"),

		StringStats("string_stats"),

		Sterms("sterms"),

		Sum("sum"),

		TdigestPercentileRanks("tdigest_percentile_ranks"),

		TdigestPercentiles("tdigest_percentiles"),

		TTest("t_test"),

		TopHits("top_hits"),

		TopMetrics("top_metrics"),

		Umrareterms("umrareterms"),

		UnmappedSampler("unmapped_sampler"),

		Umsigterms("umsigterms"),

		Umterms("umterms"),

		ValueCount("value_count"),

		VariableWidthHistogram("variable_width_histogram"),

		WeightedAvg("weighted_avg"),

		/** A custom {@code Aggregate} defined by a plugin */
		_Custom(null)

		;

		private final String jsonValue;

		Kind(String jsonValue) {
			this.jsonValue = jsonValue;
		}

		public String jsonValue() {
			return this.jsonValue;
		}

	}

	private final Kind _kind;
	private final Object _value;

	@Override
	public final Kind _kind() {
		return _kind;
	}

	@Override
	public final Object _get() {
		return _value;
	}

	public Aggregate(AggregateVariant value) {

		this._kind = ApiTypeHelper.requireNonNull(value._aggregateKind(), this, "<variant kind>");
		this._value = ApiTypeHelper.requireNonNull(value, this, "<variant value>");
		this._customKind = null;

	}

	private Aggregate(Builder builder) {

		this._kind = ApiTypeHelper.requireNonNull(builder._kind, builder, "<variant kind>");
		this._value = ApiTypeHelper.requireNonNull(builder._value, builder, "<variant value>");
		this._customKind = builder._customKind;

	}

	public static Aggregate of(Function<Builder, ObjectBuilder<Aggregate>> fn) {
		return fn.apply(new Builder()).build();
	}

	/**
	 * Build a custom plugin-defined {@code Aggregate}, given its kind and some JSON
	 * data
	 */
	public Aggregate(String kind, JsonData value) {
		this._kind = Kind._Custom;
		this._value = value;
		this._customKind = kind;
	}

	/**
	 * Is this variant instance of kind {@code lrareterms}?
	 */
	public boolean isLrareterms() {
		return _kind == Kind.Lrareterms;
	}

	/**
	 * Get the {@code lrareterms} variant value.
	 *
	 * @throws IllegalStateException
	 *             if the current variant is not of the {@code lrareterms} kind.
	 */
	public LongRareTermsAggregate lrareterms() {
		return TaggedUnionUtils.get(this, Kind.Lrareterms);
	}

	/**
	 * Is this variant instance of kind {@code lterms}?
	 */
	public boolean isLterms() {
		return _kind == Kind.Lterms;
	}

	/**
	 * Get the {@code lterms} variant value.
	 *
	 * @throws IllegalStateException
	 *             if the current variant is not of the {@code lterms} kind.
	 */
	public LongTermsAggregate lterms() {
		return TaggedUnionUtils.get(this, Kind.Lterms);
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
	 * @throws IllegalStateException
	 *             if the current variant is not of the {@code multi_terms} kind.
	 */
	public MultiTermsAggregate multiTerms() {
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
	 * @throws IllegalStateException
	 *             if the current variant is not of the {@code range} kind.
	 */
	public RangeAggregate range() {
		return TaggedUnionUtils.get(this, Kind.Range);
	}

	/**
	 * Is this variant instance of kind {@code siglterms}?
	 */
	public boolean isSiglterms() {
		return _kind == Kind.Siglterms;
	}

	/**
	 * Get the {@code siglterms} variant value.
	 *
	 * @throws IllegalStateException
	 *             if the current variant is not of the {@code siglterms} kind.
	 */
	public SignificantLongTermsAggregate siglterms() {
		return TaggedUnionUtils.get(this, Kind.Siglterms);
	}

	/**
	 * Is this variant instance of kind {@code sigsterms}?
	 */
	public boolean isSigsterms() {
		return _kind == Kind.Sigsterms;
	}

	/**
	 * Get the {@code sigsterms} variant value.
	 *
	 * @throws IllegalStateException
	 *             if the current variant is not of the {@code sigsterms} kind.
	 */
	public SignificantStringTermsAggregate sigsterms() {
		return TaggedUnionUtils.get(this, Kind.Sigsterms);
	}

	/**
	 * Is this variant instance of kind {@code simple_value}?
	 */
	public boolean isSimpleValue() {
		return _kind == Kind.SimpleValue;
	}

	/**
	 * Get the {@code simple_value} variant value.
	 *
	 * @throws IllegalStateException
	 *             if the current variant is not of the {@code simple_value} kind.
	 */
	public SimpleValueAggregate simpleValue() {
		return TaggedUnionUtils.get(this, Kind.SimpleValue);
	}

	/**
	 * Is this variant instance of kind {@code stats}?
	 */
	public boolean isStats() {
		return _kind == Kind.Stats;
	}

	/**
	 * Is this variant instance of kind {@code srareterms}?
	 */
	public boolean isSrareterms() {
		return _kind == Kind.Srareterms;
	}

	/**
	 * Get the {@code srareterms} variant value.
	 *
	 * @throws IllegalStateException
	 *             if the current variant is not of the {@code srareterms} kind.
	 */
	public StringRareTermsAggregate srareterms() {
		return TaggedUnionUtils.get(this, Kind.Srareterms);
	}

	/**
	 * Is this variant instance of kind {@code sterms}?
	 */
	public boolean isSterms() {
		return _kind == Kind.Sterms;
	}

	/**
	 * Get the {@code sterms} variant value.
	 *
	 * @throws IllegalStateException
	 *             if the current variant is not of the {@code sterms} kind.
	 */
	public StringTermsAggregate sterms() {
		return TaggedUnionUtils.get(this, Kind.Sterms);
	}

	/**
	 * Is this variant instance of kind {@code umrareterms}?
	 */
	public boolean isUmrareterms() {
		return _kind == Kind.Umrareterms;
	}

	/**
	 * Get the {@code umrareterms} variant value.
	 *
	 * @throws IllegalStateException
	 *             if the current variant is not of the {@code umrareterms} kind.
	 */
	public UnmappedRareTermsAggregate umrareterms() {
		return TaggedUnionUtils.get(this, Kind.Umrareterms);
	}


	/**
	 * Is this variant instance of kind {@code umsigterms}?
	 */
	public boolean isUmsigterms() {
		return _kind == Kind.Umsigterms;
	}

	/**
	 * Get the {@code umsigterms} variant value.
	 *
	 * @throws IllegalStateException
	 *             if the current variant is not of the {@code umsigterms} kind.
	 */
	public UnmappedSignificantTermsAggregate umsigterms() {
		return TaggedUnionUtils.get(this, Kind.Umsigterms);
	}

	/**
	 * Is this variant instance of kind {@code umterms}?
	 */
	public boolean isUmterms() {
		return _kind == Kind.Umterms;
	}

	/**
	 * Get the {@code umterms} variant value.
	 *
	 * @throws IllegalStateException
	 *             if the current variant is not of the {@code umterms} kind.
	 */
	public UnmappedTermsAggregate umterms() {
		return TaggedUnionUtils.get(this, Kind.Umterms);
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
	 * @throws IllegalStateException
	 *             if the current variant is not of the {@code weighted_avg} kind.
	 */
	public WeightedAvgAggregate weightedAvg() {
		return TaggedUnionUtils.get(this, Kind.WeightedAvg);
	}

	@Nullable
	private final String _customKind;

	/**
	 * Is this a custom {@code Aggregate} defined by a plugin?
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
	 * @throws IllegalStateException
	 *             if the current variant is not {@link Kind#_Custom}.
	 */
	public JsonData _custom() {
		return TaggedUnionUtils.get(this, Kind._Custom);
	}

	@Override
	public void serialize(JsonGenerator generator, JsonpMapper mapper) {

		mapper.serialize(_value, generator);

	}

	@Override
	public String toString() {
		return JsonpUtils.toString(this);
	}

	public static class Builder extends ObjectBuilderBase implements ObjectBuilder<Aggregate> {
		private Kind _kind;
		private Object _value;
		private String _customKind;
		public ObjectBuilder<Aggregate> dateHistogram(DateHistogramAggregate v) {
			this._kind = Kind.DateHistogram;
			this._value = v;
			return this;
		}

		public ObjectBuilder<Aggregate> dateHistogram(
				Function<DateHistogramAggregate.Builder, ObjectBuilder<DateHistogramAggregate>> fn) {
			return this.dateHistogram(fn.apply(new DateHistogramAggregate.Builder()).build());
		}

		public ObjectBuilder<Aggregate> dateRange(DateRangeAggregate v) {
			this._kind = Kind.DateRange;
			this._value = v;
			return this;
		}

		public ObjectBuilder<Aggregate> dateRange(
				Function<DateRangeAggregate.Builder, ObjectBuilder<DateRangeAggregate>> fn) {
			return this.dateRange(fn.apply(new DateRangeAggregate.Builder()).build());
		}

		public ObjectBuilder<Aggregate> dterms(DoubleTermsAggregate v) {
			this._kind = Kind.Dterms;
			this._value = v;
			return this;
		}

		public ObjectBuilder<Aggregate> dterms(
				Function<DoubleTermsAggregate.Builder, ObjectBuilder<DoubleTermsAggregate>> fn) {
			return this.dterms(fn.apply(new DoubleTermsAggregate.Builder()).build());
		}

		public ObjectBuilder<Aggregate> histogram(HistogramAggregate v) {
			this._kind = Kind.Histogram;
			this._value = v;
			return this;
		}

		public ObjectBuilder<Aggregate> histogram(
				Function<HistogramAggregate.Builder, ObjectBuilder<HistogramAggregate>> fn) {
			return this.histogram(fn.apply(new HistogramAggregate.Builder()).build());
		}

		public ObjectBuilder<Aggregate> lrareterms(LongRareTermsAggregate v) {
			this._kind = Kind.Lrareterms;
			this._value = v;
			return this;
		}

		public ObjectBuilder<Aggregate> lrareterms(
				Function<LongRareTermsAggregate.Builder, ObjectBuilder<LongRareTermsAggregate>> fn) {
			return this.lrareterms(fn.apply(new LongRareTermsAggregate.Builder()).build());
		}

		public ObjectBuilder<Aggregate> lterms(LongTermsAggregate v) {
			this._kind = Kind.Lterms;
			this._value = v;
			return this;
		}

		public ObjectBuilder<Aggregate> lterms(
				Function<LongTermsAggregate.Builder, ObjectBuilder<LongTermsAggregate>> fn) {
			return this.lterms(fn.apply(new LongTermsAggregate.Builder()).build());
		}

		public ObjectBuilder<Aggregate> multiTerms(MultiTermsAggregate v) {
			this._kind = Kind.MultiTerms;
			this._value = v;
			return this;
		}

		public ObjectBuilder<Aggregate> multiTerms(
				Function<MultiTermsAggregate.Builder, ObjectBuilder<MultiTermsAggregate>> fn) {
			return this.multiTerms(fn.apply(new MultiTermsAggregate.Builder()).build());
		}

		public ObjectBuilder<Aggregate> range(RangeAggregate v) {
			this._kind = Kind.Range;
			this._value = v;
			return this;
		}

		public ObjectBuilder<Aggregate> range(Function<RangeAggregate.Builder, ObjectBuilder<RangeAggregate>> fn) {
			return this.range(fn.apply(new RangeAggregate.Builder()).build());
		}

		public ObjectBuilder<Aggregate> siglterms(SignificantLongTermsAggregate v) {
			this._kind = Kind.Siglterms;
			this._value = v;
			return this;
		}

		public ObjectBuilder<Aggregate> siglterms(
				Function<SignificantLongTermsAggregate.Builder, ObjectBuilder<SignificantLongTermsAggregate>> fn) {
			return this.siglterms(fn.apply(new SignificantLongTermsAggregate.Builder()).build());
		}

		public ObjectBuilder<Aggregate> sigsterms(SignificantStringTermsAggregate v) {
			this._kind = Kind.Sigsterms;
			this._value = v;
			return this;
		}

		public ObjectBuilder<Aggregate> sigsterms(
				Function<SignificantStringTermsAggregate.Builder, ObjectBuilder<SignificantStringTermsAggregate>> fn) {
			return this.sigsterms(fn.apply(new SignificantStringTermsAggregate.Builder()).build());
		}

		public ObjectBuilder<Aggregate> simpleValue(SimpleValueAggregate v) {
			this._kind = Kind.SimpleValue;
			this._value = v;
			return this;
		}

		public ObjectBuilder<Aggregate> simpleValue(
				Function<SimpleValueAggregate.Builder, ObjectBuilder<SimpleValueAggregate>> fn) {
			return this.simpleValue(fn.apply(new SimpleValueAggregate.Builder()).build());
		}

		public ObjectBuilder<Aggregate> srareterms(StringRareTermsAggregate v) {
			this._kind = Kind.Srareterms;
			this._value = v;
			return this;
		}

		public ObjectBuilder<Aggregate> srareterms(
				Function<StringRareTermsAggregate.Builder, ObjectBuilder<StringRareTermsAggregate>> fn) {
			return this.srareterms(fn.apply(new StringRareTermsAggregate.Builder()).build());
		}

		public ObjectBuilder<Aggregate> sterms(StringTermsAggregate v) {
			this._kind = Kind.Sterms;
			this._value = v;
			return this;
		}

		public ObjectBuilder<Aggregate> sterms(
				Function<StringTermsAggregate.Builder, ObjectBuilder<StringTermsAggregate>> fn) {
			return this.sterms(fn.apply(new StringTermsAggregate.Builder()).build());
		}

		public ObjectBuilder<Aggregate> umrareterms(UnmappedRareTermsAggregate v) {
			this._kind = Kind.Umrareterms;
			this._value = v;
			return this;
		}

		public ObjectBuilder<Aggregate> umrareterms(
				Function<UnmappedRareTermsAggregate.Builder, ObjectBuilder<UnmappedRareTermsAggregate>> fn) {
			return this.umrareterms(fn.apply(new UnmappedRareTermsAggregate.Builder()).build());
		}

		public ObjectBuilder<Aggregate> unmappedSampler(UnmappedSamplerAggregate v) {
			this._kind = Kind.UnmappedSampler;
			this._value = v;
			return this;
		}

		public ObjectBuilder<Aggregate> unmappedSampler(
				Function<UnmappedSamplerAggregate.Builder, ObjectBuilder<UnmappedSamplerAggregate>> fn) {
			return this.unmappedSampler(fn.apply(new UnmappedSamplerAggregate.Builder()).build());
		}

		public ObjectBuilder<Aggregate> umsigterms(UnmappedSignificantTermsAggregate v) {
			this._kind = Kind.Umsigterms;
			this._value = v;
			return this;
		}

		public ObjectBuilder<Aggregate> umsigterms(
				Function<UnmappedSignificantTermsAggregate.Builder, ObjectBuilder<UnmappedSignificantTermsAggregate>> fn) {
			return this.umsigterms(fn.apply(new UnmappedSignificantTermsAggregate.Builder()).build());
		}

		public ObjectBuilder<Aggregate> umterms(UnmappedTermsAggregate v) {
			this._kind = Kind.Umterms;
			this._value = v;
			return this;
		}

		public ObjectBuilder<Aggregate> umterms(
				Function<UnmappedTermsAggregate.Builder, ObjectBuilder<UnmappedTermsAggregate>> fn) {
			return this.umterms(fn.apply(new UnmappedTermsAggregate.Builder()).build());
		}

		public ObjectBuilder<Aggregate> weightedAvg(WeightedAvgAggregate v) {
			this._kind = Kind.WeightedAvg;
			this._value = v;
			return this;
		}

		public ObjectBuilder<Aggregate> weightedAvg(
				Function<WeightedAvgAggregate.Builder, ObjectBuilder<WeightedAvgAggregate>> fn) {
			return this.weightedAvg(fn.apply(new WeightedAvgAggregate.Builder()).build());
		}

		/**
		 * Define this {@code Aggregate} as a plugin-defined variant.
		 *
		 * @param name
		 *            the plugin-defined identifier
		 * @param data
		 *            the data for this custom {@code Aggregate}. It is converted
		 *            internally to {@link JsonData}.
		 */
		public ObjectBuilder<Aggregate> _custom(String name, Object data) {
			this._kind = Kind._Custom;
			this._customKind = name;
			this._value = JsonData.of(data);
			return this;
		}

		public Aggregate build() {
			_checkSingleUse();
			return new Aggregate(this);
		}

	}

	public static final ExternallyTaggedUnion.TypedKeysDeserializer<Aggregate> _TYPED_KEYS_DESERIALIZER;

	static {
		Map<String, JsonpDeserializer<? extends AggregateVariant>> deserializers = new HashMap<>();
		deserializers.put("date_histogram", DateHistogramAggregate._DESERIALIZER);
		deserializers.put("date_range", DateRangeAggregate._DESERIALIZER);
		deserializers.put("dterms", DoubleTermsAggregate._DESERIALIZER);
		deserializers.put("histogram", HistogramAggregate._DESERIALIZER);
		deserializers.put("lrareterms", LongRareTermsAggregate._DESERIALIZER);
		deserializers.put("lterms", LongTermsAggregate._DESERIALIZER);
		deserializers.put("multi_terms", MultiTermsAggregate._DESERIALIZER);
		deserializers.put("range", RangeAggregate._DESERIALIZER);
		deserializers.put("siglterms", SignificantLongTermsAggregate._DESERIALIZER);
		deserializers.put("sigsterms", SignificantStringTermsAggregate._DESERIALIZER);
		deserializers.put("simple_value", SimpleValueAggregate._DESERIALIZER);
		deserializers.put("srareterms", StringRareTermsAggregate._DESERIALIZER);
		deserializers.put("sterms", StringTermsAggregate._DESERIALIZER);
		deserializers.put("umrareterms", UnmappedRareTermsAggregate._DESERIALIZER);
		deserializers.put("unmapped_sampler", UnmappedSamplerAggregate._DESERIALIZER);
		deserializers.put("umsigterms", UnmappedSignificantTermsAggregate._DESERIALIZER);
		deserializers.put("umterms", UnmappedTermsAggregate._DESERIALIZER);
		deserializers.put("weighted_avg", WeightedAvgAggregate._DESERIALIZER);

		_TYPED_KEYS_DESERIALIZER = new ExternallyTaggedUnion.Deserializer<>(deserializers, Aggregate::new,
				Aggregate::new).typedKeys();
	}
}
