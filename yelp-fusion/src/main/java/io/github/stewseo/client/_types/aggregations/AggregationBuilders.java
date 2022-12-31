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

import io.github.stewseo.client.util.ObjectBuilder;
import java.util.function.Function;

/**
 * Builders for {@link Aggregation} variants.
 */
public class AggregationBuilders {

	private AggregationBuilders() {
	}

	/**
	 * Creates a builder for the {@link DateHistogramAggregation date_histogram}
	 * {@code Aggregation} variant.
	 */
	public static DateHistogramAggregation.Builder dateHistogram() {
		return new DateHistogramAggregation.Builder();
	}

	/**
	 * Creates a Aggregation of the {@link DateHistogramAggregation date_histogram}
	 * {@code Aggregation} variant.
	 */
	public static Aggregation dateHistogram(
			Function<DateHistogramAggregation.Builder, ObjectBuilder<DateHistogramAggregation>> fn) {
		Aggregation.Builder builder = new Aggregation.Builder();
		builder.dateHistogram(fn.apply(new DateHistogramAggregation.Builder()).build());
		return builder.build();
	}

	/**
	 * Creates a builder for the {@link DateRangeAggregation date_range}
	 * {@code Aggregation} variant.
	 */
	public static DateRangeAggregation.Builder dateRange() {
		return new DateRangeAggregation.Builder();
	}

	/**
	 * Creates a Aggregation of the {@link DateRangeAggregation date_range}
	 * {@code Aggregation} variant.
	 */
	public static Aggregation dateRange(
			Function<DateRangeAggregation.Builder, ObjectBuilder<DateRangeAggregation>> fn) {
		Aggregation.Builder builder = new Aggregation.Builder();
		builder.dateRange(fn.apply(new DateRangeAggregation.Builder()).build());
		return builder.build();
	}

	/**
	 * Creates a builder for the {@link HistogramAggregation histogram}
	 * {@code Aggregation} variant.
	 */
	public static HistogramAggregation.Builder histogram() {
		return new HistogramAggregation.Builder();
	}

	/**
	 * Creates a Aggregation of the {@link HistogramAggregation histogram}
	 * {@code Aggregation} variant.
	 */
	public static Aggregation histogram(
			Function<HistogramAggregation.Builder, ObjectBuilder<HistogramAggregation>> fn) {
		Aggregation.Builder builder = new Aggregation.Builder();
		builder.histogram(fn.apply(new HistogramAggregation.Builder()).build());
		return builder.build();
	}


	/**
	 * Creates a builder for the {@link MultiTermsAggregation multi_terms}
	 * {@code Aggregation} variant.
	 */
	public static MultiTermsAggregation.Builder multiTerms() {
		return new MultiTermsAggregation.Builder();
	}

	/**
	 * Creates a Aggregation of the {@link MultiTermsAggregation multi_terms}
	 * {@code Aggregation} variant.
	 */
	public static Aggregation multiTerms(
			Function<MultiTermsAggregation.Builder, ObjectBuilder<MultiTermsAggregation>> fn) {
		Aggregation.Builder builder = new Aggregation.Builder();
		builder.multiTerms(fn.apply(new MultiTermsAggregation.Builder()).build());
		return builder.build();
	}


	/**
	 * Creates a builder for the {@link RangeAggregation range} {@code Aggregation}
	 * variant.
	 */
	public static RangeAggregation.Builder range() {
		return new RangeAggregation.Builder();
	}

	/**
	 * Creates a Aggregation of the {@link RangeAggregation range}
	 * {@code Aggregation} variant.
	 */
	public static Aggregation range(Function<RangeAggregation.Builder, ObjectBuilder<RangeAggregation>> fn) {
		Aggregation.Builder builder = new Aggregation.Builder();
		builder.range(fn.apply(new RangeAggregation.Builder()).build());
		return builder.build();
	}

	/**
	 * Creates a builder for the {@link SignificantTermsAggregation
	 * significant_terms} {@code Aggregation} variant.
	 */
	public static SignificantTermsAggregation.Builder significantTerms() {
		return new SignificantTermsAggregation.Builder();
	}

	/**
	 * Creates a Aggregation of the {@link SignificantTermsAggregation
	 * significant_terms} {@code Aggregation} variant.
	 */
	public static Aggregation significantTerms(
			Function<SignificantTermsAggregation.Builder, ObjectBuilder<SignificantTermsAggregation>> fn) {
		Aggregation.Builder builder = new Aggregation.Builder();
		builder.significantTerms(fn.apply(new SignificantTermsAggregation.Builder()).build());
		return builder.build();
	}

	/**
	 * Creates a builder for the {@link SignificantTextAggregation significant_text}
	 * {@code Aggregation} variant.
	 */
	public static SignificantTextAggregation.Builder significantText() {
		return new SignificantTextAggregation.Builder();
	}

	/**
	 * Creates a Aggregation of the {@link SignificantTextAggregation
	 * significant_text} {@code Aggregation} variant.
	 */
	public static Aggregation significantText(
			Function<SignificantTextAggregation.Builder, ObjectBuilder<SignificantTextAggregation>> fn) {
		Aggregation.Builder builder = new Aggregation.Builder();
		builder.significantText(fn.apply(new SignificantTextAggregation.Builder()).build());
		return builder.build();
	}


	/**
	 * Creates a builder for the {@link TermsAggregation terms} {@code Aggregation}
	 * variant.
	 */
	public static TermsAggregation.Builder terms() {
		return new TermsAggregation.Builder();
	}

	/**
	 * Creates a Aggregation of the {@link TermsAggregation terms}
	 * {@code Aggregation} variant.
	 */
	public static Aggregation terms(Function<TermsAggregation.Builder, ObjectBuilder<TermsAggregation>> fn) {
		Aggregation.Builder builder = new Aggregation.Builder();
		builder.terms(fn.apply(new TermsAggregation.Builder()).build());
		return builder.build();
	}


	/**
	 * Creates a builder for the {@link WeightedAverageAggregation weighted_avg}
	 * {@code Aggregation} variant.
	 */
	public static WeightedAverageAggregation.Builder weightedAvg() {
		return new WeightedAverageAggregation.Builder();
	}

	/**
	 * Creates a Aggregation of the {@link WeightedAverageAggregation weighted_avg}
	 * {@code Aggregation} variant.
	 */
	public static Aggregation weightedAvg(
			Function<WeightedAverageAggregation.Builder, ObjectBuilder<WeightedAverageAggregation>> fn) {
		Aggregation.Builder builder = new Aggregation.Builder();
		builder.weightedAvg(fn.apply(new WeightedAverageAggregation.Builder()).build());
		return builder.build();
	}

}
