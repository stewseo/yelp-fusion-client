package io.github.stewseo.clients.elasticsearch;

import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import co.elastic.clients.json.JsonData;

public interface QueryBuilderUtils {


    public static Query termQuery(String field, String value){
        return Query.of(q -> q
                .term(t -> t // Returns documents that contain an exact term in a provided field.
                        .caseInsensitive(true)
                        .field(field)
                        .value(value))
        );
    }


    public static Query matchQuery(String field, String query) {
        return Query.of(q -> q
                .match(m -> m // Returns documents that match a provided text, number, date or boolean value. The provided text is analyzed before matching.
                        .field(field) // search field
                        .query(query) // search text
                )
        );
    }

    public static Query matchAllQuery() {
        return new MatchAllQuery.Builder().build()._toQuery();
    }

    public static Query matchAllQuery(String queryName) {
        return Query.of(q -> q
                .matchAll(ma -> ma
                        .queryName(queryName))
        );
    }

    public static Query rangeQueryByTimestamp(String field, String timestamp) {
        return Query.of(q -> q
                .range(r -> r
                        .field(field)
                        .gte(JsonData.of(timestamp)))
        );
    }

}
