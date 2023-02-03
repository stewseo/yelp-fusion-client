package io.github.stewseo.clients.elasticsearch;

import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.elasticsearch._types.aggregations.TermsAggregation;

public interface AggregationBuilderUtils {

    public static Aggregation termsAggregation(String field, int size) {
        return Aggregation.of(a -> a
                .terms(t -> t // Select the terms aggregation variant.
                        .field(field)
                        .size(size))
        );
    }
}
