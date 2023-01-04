package io.github.stewseo.clients.yelpfusion._types.aggregations;


public interface AggregationVariant {

    Aggregation.Kind _aggregationKind();

    default Aggregation _toAggregation() {
        return new Aggregation(this);
    }

}
