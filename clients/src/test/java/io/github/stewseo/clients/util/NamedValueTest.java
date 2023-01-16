package io.github.stewseo.clients.util;

import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.yelpfusion._types.SortOrder;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessesResult;
import org.junit.jupiter.api.Tag;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Tag("utils")

class NamedValueTest {

    @UtilTest
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

    @UtilTest
    void testDeserializer() {

        JsonpDeserializer<NamedValue<String>> jsonpDeserializer = NamedValue.deserializer(JsonpDeserializer.stringDeserializer());

        assertThat(jsonpDeserializer).isInstanceOf(JsonpDeserializer.class);

        JsonpDeserializer<NamedValue<SearchBusinessesResult>> searchBusinessResultNamedDeser =
                NamedValue.deserializer(() -> SearchBusinessesResult._DESERIALIZER);

        assertThat(searchBusinessResultNamedDeser).isInstanceOf(JsonpDeserializer.class);

    }

}