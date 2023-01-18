package io.github.stewseo.clients.util;

import io.github.stewseo.clients.yelpfusion._types.QueryParameter;
import io.github.stewseo.clients.json.JsonData;
import io.github.stewseo.clients.yelpfusion._types.Time;
import org.junit.jupiter.api.Tag;

import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("utils")

class TaggedUnionUtilsTest {

    @UtilTest
    void get() {
        Time time = Time.of(t -> t.time("value"));
        String str = TaggedUnionUtils.get(time, Time.Kind.Time);
        assertThat(str).isEqualTo("value");
    }

    @UtilTest
    void ndJsonIterator() {
        QueryParameter qf = QueryParameter.of(p -> p
                ._custom("customKindName", JsonData.of("jsonData"))
        );
        Iterator<?> iterator = TaggedUnionUtils.ndJsonIterator(qf);
        iterator.forEachRemaining(e -> {
            assertThat(e).isInstanceOf(QueryParameter.class);
        });
    }
}