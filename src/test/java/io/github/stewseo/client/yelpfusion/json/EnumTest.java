package io.github.stewseo.client.yelpfusion.json;


import io.github.stewseo.client._types.Bytes;
import io.github.stewseo.client._types.GeoOrientation;
import io.github.stewseo.client._types.Refresh;

import io.github.stewseo.client.yelpfusion.business.ModelTestCase;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EnumTest extends ModelTestCase {

    @Test
    public void testSimpleEnum() {

        assertThat(Bytes.GigaBytes.aliases()).isNull();

        assertThat(Bytes.GigaBytes).isEqualTo(Bytes._DESERIALIZER.parse("gb"));
    }

    @Test
    public void testEnumWithAliases() {
        assertThat("left").isEqualTo(GeoOrientation.Left.jsonValue());
        assertThat(GeoOrientation.Left.aliases()).isNotNull();

        final Stream<String> stream = Stream.of("right", "RIGHT", "counterclockwise", "ccw");
        try(stream) {
            stream.forEach(alias -> {
                assertThat(GeoOrientation.Right).isEqualTo(GeoOrientation._DESERIALIZER.parse(alias));
            });
        }
    }

    @Test
    public void testBooleanEnum() {
        // Quoted value
        assertThat(Refresh.WaitFor).isEqualTo(checkJsonRoundtrip(Refresh.WaitFor, "\"WaitFor\""));

        // Unquoted boolean values
        assertThat(Refresh.True).isEqualTo(checkJsonRoundtrip(Refresh.True, "\"True\""));
        assertThat(Refresh.False).isEqualTo(checkJsonRoundtrip(Refresh.False, "\"False\""));

        // True/False as strings
        assertThat(Refresh.True).isEqualTo(fromJson("\"True\"", Refresh.class));
        assertThat(Refresh.False).isEqualTo(fromJson("\"False\"", Refresh.class));
    }
}