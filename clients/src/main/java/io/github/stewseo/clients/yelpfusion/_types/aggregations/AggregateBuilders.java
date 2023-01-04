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

import io.github.stewseo.clients.util.ObjectBuilder;

import java.util.function.Function;

/**
 * Builders for {@link Aggregate} variants.
 */
@SuppressWarnings("unused")
public class AggregateBuilders {
    private AggregateBuilders() {
    }


    /**
     * Creates a builder for the {@link DateHistogramAggregate date_histogram}
     * {@code Aggregate} variant.
     */
    public static DateHistogramAggregate.Builder dateHistogram() {
        return new DateHistogramAggregate.Builder();
    }

    /**
     * Creates an Aggregate of the {@link DateHistogramAggregate date_histogram}
     * {@code Aggregate} variant.
     */
    public static Aggregate dateHistogram(
            Function<DateHistogramAggregate.Builder, ObjectBuilder<DateHistogramAggregate>> fn) {
        Aggregate.Builder builder = new Aggregate.Builder();
        builder.dateHistogram(fn.apply(new DateHistogramAggregate.Builder()).build());
        return builder.build();
    }

    /**
     * Creates a builder for the {@link DateRangeAggregate date_range}
     * {@code Aggregate} variant.
     */
    public static DateRangeAggregate.Builder dateRange() {
        return new DateRangeAggregate.Builder();
    }

    /**
     * Creates an Aggregate of the {@link DateRangeAggregate date_range}
     * {@code Aggregate} variant.
     */
    public static Aggregate dateRange(Function<DateRangeAggregate.Builder, ObjectBuilder<DateRangeAggregate>> fn) {
        Aggregate.Builder builder = new Aggregate.Builder();
        builder.dateRange(fn.apply(new DateRangeAggregate.Builder()).build());
        return builder.build();
    }


    /**
     * Creates a builder for the {@link DoubleTermsAggregate dterms}
     * {@code Aggregate} variant.
     */
    public static DoubleTermsAggregate.Builder dterms() {
        return new DoubleTermsAggregate.Builder();
    }

    /**
     * Creates a Aggregate of the {@link DoubleTermsAggregate dterms}
     * {@code Aggregate} variant.
     */
    public static Aggregate dterms(Function<DoubleTermsAggregate.Builder, ObjectBuilder<DoubleTermsAggregate>> fn) {
        Aggregate.Builder builder = new Aggregate.Builder();
        builder.dterms(fn.apply(new DoubleTermsAggregate.Builder()).build());
        return builder.build();
    }

    /**
     * Creates a builder for the {@link HistogramAggregate histogram}
     * {@code Aggregate} variant.
     */
    public static HistogramAggregate.Builder histogram() {
        return new HistogramAggregate.Builder();
    }

    /**
     * Creates a Aggregate of the {@link HistogramAggregate histogram}
     * {@code Aggregate} variant.
     */
    public static Aggregate histogram(Function<HistogramAggregate.Builder, ObjectBuilder<HistogramAggregate>> fn) {
        Aggregate.Builder builder = new Aggregate.Builder();
        builder.histogram(fn.apply(new HistogramAggregate.Builder()).build());
        return builder.build();
    }

    /**
     * Creates a builder for the {@link LongRareTermsAggregate lrareterms}
     * {@code Aggregate} variant.
     */
    public static LongRareTermsAggregate.Builder lrareterms() {
        return new LongRareTermsAggregate.Builder();
    }

    /**
     * Creates a Aggregate of the {@link LongRareTermsAggregate lrareterms}
     * {@code Aggregate} variant.
     */
    public static Aggregate lrareterms(
            Function<LongRareTermsAggregate.Builder, ObjectBuilder<LongRareTermsAggregate>> fn) {
        Aggregate.Builder builder = new Aggregate.Builder();
        builder.lrareterms(fn.apply(new LongRareTermsAggregate.Builder()).build());
        return builder.build();
    }

    /**
     * Creates a builder for the {@link LongTermsAggregate lterms} {@code Aggregate}
     * variant.
     */
    public static LongTermsAggregate.Builder lterms() {
        return new LongTermsAggregate.Builder();
    }

    /**
     * Creates a Aggregate of the {@link LongTermsAggregate lterms}
     * {@code Aggregate} variant.
     */
    public static Aggregate lterms(Function<LongTermsAggregate.Builder, ObjectBuilder<LongTermsAggregate>> fn) {
        Aggregate.Builder builder = new Aggregate.Builder();
        builder.lterms(fn.apply(new LongTermsAggregate.Builder()).build());
        return builder.build();
    }

    /**
     * Creates a builder for the {@link MultiTermsAggregate multi_terms}
     * {@code Aggregate} variant.
     */
    public static MultiTermsAggregate.Builder multiTerms() {
        return new MultiTermsAggregate.Builder();
    }

    /**
     * Creates a Aggregate of the {@link MultiTermsAggregate multi_terms}
     * {@code Aggregate} variant.
     */
    public static Aggregate multiTerms(Function<MultiTermsAggregate.Builder, ObjectBuilder<MultiTermsAggregate>> fn) {
        Aggregate.Builder builder = new Aggregate.Builder();
        builder.multiTerms(fn.apply(new MultiTermsAggregate.Builder()).build());
        return builder.build();
    }

    /**
     * Creates a builder for the {@link RangeAggregate range} {@code Aggregate}
     * variant.
     */
    public static RangeAggregate.Builder range() {
        return new RangeAggregate.Builder();
    }

    /**
     * Creates a Aggregate of the {@link RangeAggregate range} {@code Aggregate}
     * variant.
     */
    public static Aggregate range(Function<RangeAggregate.Builder, ObjectBuilder<RangeAggregate>> fn) {
        Aggregate.Builder builder = new Aggregate.Builder();
        builder.range(fn.apply(new RangeAggregate.Builder()).build());
        return builder.build();
    }

    /**
     * Creates a builder for the {@link SignificantLongTermsAggregate siglterms}
     * {@code Aggregate} variant.
     */
    public static SignificantLongTermsAggregate.Builder siglterms() {
        return new SignificantLongTermsAggregate.Builder();
    }

    /**
     * Creates a Aggregate of the {@link SignificantLongTermsAggregate siglterms}
     * {@code Aggregate} variant.
     */
    public static Aggregate siglterms(
            Function<SignificantLongTermsAggregate.Builder, ObjectBuilder<SignificantLongTermsAggregate>> fn) {
        Aggregate.Builder builder = new Aggregate.Builder();
        builder.siglterms(fn.apply(new SignificantLongTermsAggregate.Builder()).build());
        return builder.build();
    }

    /**
     * Creates a builder for the {@link SignificantStringTermsAggregate sigsterms}
     * {@code Aggregate} variant.
     */
    public static SignificantStringTermsAggregate.Builder sigsterms() {
        return new SignificantStringTermsAggregate.Builder();
    }

    /**
     * Creates a Aggregate of the {@link SignificantStringTermsAggregate sigsterms}
     * {@code Aggregate} variant.
     */
    public static Aggregate sigsterms(
            Function<SignificantStringTermsAggregate.Builder, ObjectBuilder<SignificantStringTermsAggregate>> fn) {
        Aggregate.Builder builder = new Aggregate.Builder();
        builder.sigsterms(fn.apply(new SignificantStringTermsAggregate.Builder()).build());
        return builder.build();
    }

    /**
     * Creates a builder for the {@link SimpleValueAggregate simple_value}
     * {@code Aggregate} variant.
     */
    public static SimpleValueAggregate.Builder simpleValue() {
        return new SimpleValueAggregate.Builder();
    }

    /**
     * Creates a Aggregate of the {@link SimpleValueAggregate simple_value}
     * {@code Aggregate} variant.
     */
    public static Aggregate simpleValue(
            Function<SimpleValueAggregate.Builder, ObjectBuilder<SimpleValueAggregate>> fn) {
        Aggregate.Builder builder = new Aggregate.Builder();
        builder.simpleValue(fn.apply(new SimpleValueAggregate.Builder()).build());
        return builder.build();
    }

    /**
     * Creates a builder for the {@link StringRareTermsAggregate srareterms}
     * {@code Aggregate} variant.
     */
    public static StringRareTermsAggregate.Builder srareterms() {
        return new StringRareTermsAggregate.Builder();
    }

    /**
     * Creates an Aggregate of the {@link StringRareTermsAggregate srareterms}
     * {@code Aggregate} variant.
     */
    public static Aggregate srareterms(
            Function<StringRareTermsAggregate.Builder, ObjectBuilder<StringRareTermsAggregate>> fn) {
        Aggregate.Builder builder = new Aggregate.Builder();
        builder.srareterms(fn.apply(new StringRareTermsAggregate.Builder()).build());
        return builder.build();
    }

    /**
     * Creates a builder for the {@link StringTermsAggregate sterms}
     * {@code Aggregate} variant.
     */
    public static StringTermsAggregate.Builder sterms() {
        return new StringTermsAggregate.Builder();
    }

    /**
     * Creates a Aggregate of the {@link StringTermsAggregate sterms}
     * {@code Aggregate} variant.
     */
    public static Aggregate sterms(Function<StringTermsAggregate.Builder, ObjectBuilder<StringTermsAggregate>> fn) {
        Aggregate.Builder builder = new Aggregate.Builder();
        builder.sterms(fn.apply(new StringTermsAggregate.Builder()).build());
        return builder.build();
    }

    /**
     * Creates a builder for the {@link UnmappedRareTermsAggregate umrareterms}
     * {@code Aggregate} variant.
     */
    public static UnmappedRareTermsAggregate.Builder umrareterms() {
        return new UnmappedRareTermsAggregate.Builder();
    }

    /**
     * Creates a Aggregate of the {@link UnmappedRareTermsAggregate umrareterms}
     * {@code Aggregate} variant.
     */
    public static Aggregate umrareterms(
            Function<UnmappedRareTermsAggregate.Builder, ObjectBuilder<UnmappedRareTermsAggregate>> fn) {
        Aggregate.Builder builder = new Aggregate.Builder();
        builder.umrareterms(fn.apply(new UnmappedRareTermsAggregate.Builder()).build());
        return builder.build();
    }

    /**
     * Creates a builder for the {@link UnmappedSamplerAggregate unmapped_sampler}
     * {@code Aggregate} variant.
     */
    public static UnmappedSamplerAggregate.Builder unmappedSampler() {
        return new UnmappedSamplerAggregate.Builder();
    }

    /**
     * Creates a Aggregate of the {@link UnmappedSamplerAggregate unmapped_sampler}
     * {@code Aggregate} variant.
     */
    public static Aggregate unmappedSampler(
            Function<UnmappedSamplerAggregate.Builder, ObjectBuilder<UnmappedSamplerAggregate>> fn) {
        Aggregate.Builder builder = new Aggregate.Builder();
        builder.unmappedSampler(fn.apply(new UnmappedSamplerAggregate.Builder()).build());
        return builder.build();
    }

    /**
     * Creates a builder for the {@link UnmappedSignificantTermsAggregate
     * umsigterms} {@code Aggregate} variant.
     */
    public static UnmappedSignificantTermsAggregate.Builder umsigterms() {
        return new UnmappedSignificantTermsAggregate.Builder();
    }

    /**
     * Creates a Aggregate of the {@link UnmappedSignificantTermsAggregate
     * umsigterms} {@code Aggregate} variant.
     */
    public static Aggregate umsigterms(
            Function<UnmappedSignificantTermsAggregate.Builder, ObjectBuilder<UnmappedSignificantTermsAggregate>> fn) {
        Aggregate.Builder builder = new Aggregate.Builder();
        builder.umsigterms(fn.apply(new UnmappedSignificantTermsAggregate.Builder()).build());
        return builder.build();
    }

    /**
     * Creates a builder for the {@link UnmappedTermsAggregate umterms}
     * {@code Aggregate} variant.
     */
    public static UnmappedTermsAggregate.Builder umterms() {
        return new UnmappedTermsAggregate.Builder();
    }

    /**
     * Creates a Aggregate of the {@link UnmappedTermsAggregate umterms}
     * {@code Aggregate} variant.
     */
    public static Aggregate umterms(
            Function<UnmappedTermsAggregate.Builder, ObjectBuilder<UnmappedTermsAggregate>> fn) {
        Aggregate.Builder builder = new Aggregate.Builder();
        builder.umterms(fn.apply(new UnmappedTermsAggregate.Builder()).build());
        return builder.build();
    }

    /**
     * Creates a builder for the {@link WeightedAvgAggregate weighted_avg}
     * {@code Aggregate} variant.
     */
    public static WeightedAvgAggregate.Builder weightedAvg() {
        return new WeightedAvgAggregate.Builder();
    }

    /**
     * Creates a Aggregate of the {@link WeightedAvgAggregate weighted_avg}
     * {@code Aggregate} variant.
     */
    public static Aggregate weightedAvg(
            Function<WeightedAvgAggregate.Builder, ObjectBuilder<WeightedAvgAggregate>> fn) {
        Aggregate.Builder builder = new Aggregate.Builder();
        builder.weightedAvg(fn.apply(new WeightedAvgAggregate.Builder()).build());
        return builder.build();
    }

}
