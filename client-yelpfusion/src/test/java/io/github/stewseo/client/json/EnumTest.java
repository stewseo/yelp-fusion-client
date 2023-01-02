package io.github.stewseo.client.json;


import io.github.stewseo.client._types.Bytes;
import io.github.stewseo.client._types.GeoOrientation;
import io.github.stewseo.client._types.Refresh;
import io.github.stewseo.client.yelpfusion.YelpFusionTestCase;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class EnumTest extends YelpFusionTestCase {

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

        stream.forEach(alias -> {
            assertThat(GeoOrientation.Right).isEqualTo(GeoOrientation._DESERIALIZER.parse(alias));
        });

    }

    @Test
    public void testBooleanEnum() {
        // Quoted value
        AssertionsForClassTypes.assertThat(Refresh.WaitFor).isEqualTo(jsonTestCase.checkJsonRoundtrip(Refresh.WaitFor, "\"WaitFor\""));

        // Unquoted boolean values
        assertThat(Refresh.True).isEqualTo(jsonTestCase.checkJsonRoundtrip(Refresh.True, "\"True\""));
        assertThat(Refresh.False).isEqualTo(jsonTestCase.checkJsonRoundtrip(Refresh.False, "\"False\""));

        // True/False as strings
        assertThat(Refresh.True).isEqualTo(jsonTestCase.fromJson("\"True\"", Refresh.class));
        assertThat(Refresh.False).isEqualTo(jsonTestCase.fromJson("\"False\"", Refresh.class));
    }

}