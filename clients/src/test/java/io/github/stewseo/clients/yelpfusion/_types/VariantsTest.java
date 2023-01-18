package io.github.stewseo.clients.yelpfusion._types;

import io.github.stewseo.clients.json.testcases.ModelJsonTestCase;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class VariantsTest extends ModelJsonTestCase {

    @TypeTest
    public void testNested() {
        // nested containers: query > intervals > interval
        // intervals is a single key dictionary
        // query has container properties

        QueryParameter qp = QueryParameter.of(_0 -> _0
                .term(_1 -> _1
                        .term(TermEnum.Restaurants)
                        .queryParams("term-qp", _2 -> _2
                                .location(_3 -> _3
                                        .location(LocationEnum.City))
                        )
                )
        );

        assertThat(QueryParameter.Kind.Term).isEqualTo(qp._kind());
        assertThat(qp.term()).isNotNull();
        assertThat(qp.term().queryParams().toString()).isEqualTo("{term-qp={\"location\":{\"location\":\"city\"}}}");

        String json = toJson(qp);

        assertThat(json).isEqualTo("{\"term\":{\"query_parameters\":{\"term-qp\":{\"location\":{\"location\":\"city\"}}},\"term\":\"restaurants\"}}");


        QueryParameter qp2 = fromJson(json, QueryParameter.class);

//        assertThat(qp2).isEqualTo(json);

        assertThat(QueryParameter.Kind.Term).isEqualTo(qp2._kind());


        assertThat(qp2.term().queryParams()).isNotNull();
    }

    @TypeTest
    public void testInternalTag() {
        String expected = "" +
                "{" +
                    "\"term\":" +
                        "{" +
                            "\"query_parameters\":" +
                                "{" +
                                    "\"term-query-parameter\":" +
                                        "{" +
                                            "\"location\":" +
                                                "{" +
                                                    "\"location\":\"city\"" +
                                                "}" +
                                        "}" +
                                "}," +
                            "\"term\":\"restaurants\"" +
                        "}" +
                "}";

        QueryParameter qp = QueryParameter.of(_0 -> _0 // TaggedUnion
                .term(_1 -> _1
                                .term(TermEnum.Restaurants)

                        .queryParams("term-query-parameter", _2 -> _2
                                .location(_4 -> _4
                                        .location(LocationEnum.City)
                                )
                        )
                )
        );

        Exception exception = assertThrows(Exception.class, () ->
                assertThat(qp.location().queryParams()).isNotNull()
        );

        assertThat(toJson(qp)).isEqualTo(expected);

        QueryParameter queryParameter = fromJson(expected, QueryParameter.class);

        TermQueryParameter termQueryParameter = queryParameter.term();

        assertThat(termQueryParameter._queryFieldKind()).isEqualTo(QueryParameter.Kind.Term);
        assertThat(termQueryParameter.queryParams()).isNotNull();

        TermEnum termEnum = termQueryParameter.term();

        assertThat(termEnum.jsonValue()).isEqualTo("restaurants");

        assertThrows(Exception.class, () ->
                assertThat(queryParameter.location().queryParams()).isNotNull()
        );

    }

}
