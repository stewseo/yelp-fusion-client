package io.github.stewseo.util;

import io.github.stewseo.clients.util.SomeUnion;
import io.github.stewseo.clients.util.TaggedUnionUtils;
import io.github.stewseo.clients.yelpfusion._types.Time;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

class TaggedUnionUtilsTest {

    @Test
    void get() {
        Time time = Time.of(t -> t.time("value"));
        String str = TaggedUnionUtils.get(time, Time.Kind.Time);
        assertThat(str).isEqualTo("value");
    }

    private final SomeUnion suA = new SomeUnion.Builder()
            .variantA(a_ -> a_
                    .name("a-name")
            ).build();

    @Test
    void ndJsonIterator() {

        Iterator<?> iterator = TaggedUnionUtils.ndJsonIterator(suA);
        iterator.forEachRemaining(e -> {
            assertThat(e).isInstanceOf(SomeUnion.class);
        });
    }
}