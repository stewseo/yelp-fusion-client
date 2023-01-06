package io.github.stewseo.clients.util;

import io.github.stewseo.clients.yelpfusion.testcases.FunctionalTestCase;
import io.github.stewseo.clients.yelpfusion._types.SortOrder;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class NamedValueTest extends FunctionalTestCase {

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