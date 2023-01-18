package io.github.stewseo.clients.util;

import io.github.stewseo.clients.yelpfusion._types.QueryParameter;
import io.github.stewseo.clients.json.JsonData;
import io.github.stewseo.clients.json.testcases.ModelJsonTestCase;
import org.junit.jupiter.api.Tag;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@Tag("utils")

class PairTest extends ModelJsonTestCase {

    private final QueryParameter queryField = QueryParameter.of(qf -> qf
            ._custom("some-union", JsonData.of("{variantA:value}")));


    private final String json = "{\"term\":{\"type\":\"term\"}}";

    @UtilTest
    void pairTest() {
        Pair<String, QueryParameter> pair = Pair.of("key", queryField);
        assertThat(pair).isInstanceOf(Pair.class);
        assertThat(pair.key()).isEqualTo("key");
        assertThat(pair.value()).isEqualTo(queryField);
    }


}