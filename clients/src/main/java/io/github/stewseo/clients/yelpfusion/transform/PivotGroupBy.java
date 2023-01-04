package io.github.stewseo.clients.yelpfusion.transform;

import io.github.stewseo.clients.yelpfusion._types.aggregations.DateHistogramAggregation;
import io.github.stewseo.clients.yelpfusion._types.aggregations.HistogramAggregation;
import io.github.stewseo.clients.yelpfusion._types.aggregations.TermsAggregation;
import io.github.stewseo.clients.json.JsonEnum;
import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ApiTypeHelper;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.util.TaggedUnion;
import io.github.stewseo.clients.util.TaggedUnionUtils;
import io.github.stewseo.clients.util.WithJsonObjectBuilderBase;
import jakarta.json.stream.JsonGenerator;

import java.util.function.Function;

@JsonpDeserializable
public class PivotGroupBy implements TaggedUnion<PivotGroupBy.Kind, Object>, JsonpSerializable {

    public static final JsonpDeserializer<PivotGroupBy> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
            PivotGroupBy::setupPivotGroupByDeserializer, Builder::build);
    private final Kind _kind;
    private final Object _value;

    public PivotGroupBy(PivotGroupByVariant value) {

        this._kind = ApiTypeHelper.requireNonNull(value._pivotGroupByKind(), this, "<variant kind>");
        this._value = ApiTypeHelper.requireNonNull(value, this, "<variant value>");

    }

    private PivotGroupBy(Builder builder) {

        this._kind = ApiTypeHelper.requireNonNull(builder._kind, builder, "<variant kind>");
        this._value = ApiTypeHelper.requireNonNull(builder._value, builder, "<variant value>");

    }

    public static PivotGroupBy of(Function<Builder, ObjectBuilder<PivotGroupBy>> fn) {
        return fn.apply(new Builder()).build();
    }

    protected static void setupPivotGroupByDeserializer(ObjectDeserializer<Builder> op) {

        op.add(Builder::dateHistogram, DateHistogramAggregation._DESERIALIZER, "date_histogram");
        op.add(Builder::histogram, HistogramAggregation._DESERIALIZER, "histogram");
        op.add(Builder::terms, TermsAggregation._DESERIALIZER, "terms");

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

    @Override
    @SuppressWarnings("unchecked")
    public void serialize(JsonGenerator generator, JsonpMapper mapper) {

        generator.writeStartObject();

        generator.writeKey(_kind.jsonValue());
        if (_value instanceof JsonpSerializable) {
            ((JsonpSerializable) _value).serialize(generator, mapper);
        }

        generator.writeEnd();

    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    /**
     * {@link PivotGroupBy} variant kinds.
     *
     * @see <a href=
     * "../doc-files/api-spec.html#transform._types.PivotGroupByContainer">API
     * specification</a>
     */

    public enum Kind implements JsonEnum {
        DateHistogram("date_histogram"),

        GeotileGrid("geotile_grid"),

        Histogram("histogram"),

        Terms("terms"),

        ;

        private final String jsonValue;

        Kind(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public String jsonValue() {
            return this.jsonValue;
        }

    }

    public static class Builder extends WithJsonObjectBuilderBase<Builder> implements ObjectBuilder<PivotGroupBy> {
        private Kind _kind;
        private Object _value;

        @Override
        protected Builder self() {
            return this;
        }

        public ObjectBuilder<PivotGroupBy> dateHistogram(DateHistogramAggregation v) {
            this._kind = Kind.DateHistogram;
            this._value = v;
            return this;
        }

        public ObjectBuilder<PivotGroupBy> dateHistogram(
                Function<DateHistogramAggregation.Builder, ObjectBuilder<DateHistogramAggregation>> fn) {
            return this.dateHistogram(fn.apply(new DateHistogramAggregation.Builder()).build());
        }

        public ObjectBuilder<PivotGroupBy> histogram(HistogramAggregation v) {
            this._kind = Kind.Histogram;
            this._value = v;
            return this;
        }

        public ObjectBuilder<PivotGroupBy> histogram(
                Function<HistogramAggregation.Builder, ObjectBuilder<HistogramAggregation>> fn) {
            return this.histogram(fn.apply(new HistogramAggregation.Builder()).build());
        }

        public ObjectBuilder<PivotGroupBy> terms(TermsAggregation v) {
            this._kind = Kind.Terms;
            this._value = v;
            return this;
        }

        public ObjectBuilder<PivotGroupBy> terms(
                Function<TermsAggregation.Builder, ObjectBuilder<TermsAggregation>> fn) {
            return this.terms(fn.apply(new TermsAggregation.Builder()).build());
        }

        public PivotGroupBy build() {
            _checkSingleUse();
            return new PivotGroupBy(this);
        }

    }
}

