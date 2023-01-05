package io.github.stewseo.clients.util;

import co.elastic.clients.elasticsearch._types.aggregations.TermsAggregation;
import io.github.stewseo.clients.yelpfusion._types.SortOrder;
import io.github.stewseo.clients.json.JsonData;
import io.github.stewseo.clients.testcase.YelpFusionTestCase;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class NamedValueTest extends YelpFusionTestCase {

    @Test
    public void namedValueTest() {

        String json = "{\"order\":[{\"a\":\"asc\"},{\"b\":\"desc\"}]}";

        NamedValue<SortOrder> namedValueAsc = NamedValue.of("a", SortOrder.Asc);

        NamedValue<SortOrder> namedValueDesc = NamedValue.of("b", SortOrder.Desc);

        assertThat(namedValueAsc.name()).isEqualTo("a");
        assertThat(namedValueAsc.value()).isEqualTo(SortOrder.Asc);

        assertThat(namedValueDesc.name()).isEqualTo("b");
        assertThat(namedValueDesc.value()).isEqualTo(SortOrder.Desc);
    }

}