package io.github.stewseo.clients.util;

import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.yelpfusion._types.SortOrder;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessResult;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class NamedValueTest {

    @Test
    public void namedValueTest() {

        NamedValue<SortOrder> namedValueAsc = NamedValue.of("a", SortOrder.Asc);

        NamedValue<SortOrder> namedValueDesc = NamedValue.of("b", SortOrder.Desc);

        assertThat(namedValueAsc.name()).isEqualTo("a");
        assertThat(namedValueAsc.value()).isEqualTo(SortOrder.Asc);

        assertThat(namedValueDesc.name()).isEqualTo("b");
        assertThat(namedValueDesc.value()).isEqualTo(SortOrder.Desc);

        JsonpDeserializer<NamedValue<String>> jsonpDeserializer = NamedValue.deserializer(JsonpDeserializer.stringDeserializer());

        assertThat(jsonpDeserializer).isNotNull();
    }

    @Test
    void testDeserializer() {

        JsonpDeserializer<NamedValue<String>> jsonpDeserializer = NamedValue.deserializer(JsonpDeserializer.stringDeserializer());

        assertThat(jsonpDeserializer).isInstanceOf(JsonpDeserializer.class);

        JsonpDeserializer<NamedValue<SearchBusinessResult>> searchBusinessResultNamedDeser =
                NamedValue.deserializer(() -> SearchBusinessResult._DESERIALIZER);

        assertThat(searchBusinessResultNamedDeser).isInstanceOf(JsonpDeserializer.class);

    }

}