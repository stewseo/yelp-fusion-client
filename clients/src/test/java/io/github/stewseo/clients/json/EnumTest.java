package io.github.stewseo.clients.json;


import io.github.stewseo.clients.json.testcases.TestJson;
import io.github.stewseo.clients.yelpfusion._types.Bytes;
import io.github.stewseo.clients.yelpfusion._types.GeoOrientation;
import io.github.stewseo.clients.yelpfusion._types.Refresh;
import io.github.stewseo.clients.yelpfusion._types.Result;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class EnumTest extends TestJson {


    @Test
    public void testSimpleEnum() {

        assertThat(Bytes.GigaBytes.aliases()).isNull();

        assertThat(Bytes.GigaBytes).isEqualTo(Bytes._DESERIALIZER.parse("gb"));


        assertThat(Result.Created).isEqualTo(Result._DESERIALIZER.parse("created"));

        assertThat(Result.NoOp).isEqualTo(Result._DESERIALIZER.parse("noop"));

        assertThat(Result.Deleted).isEqualTo(Result._DESERIALIZER.parse("deleted"));

        assertThat(Result.Updated).isEqualTo(Result._DESERIALIZER.parse("updated"));

    }

    @Test
    public void testEnumWithAliases() {

        assertThat("left").isEqualTo(GeoOrientation.Left.jsonValue());

        assertThat(GeoOrientation.Left.aliases()).isNotNull();

        final Stream<String> stream = Stream.of("right", "RIGHT", "counterclockwise", "ccw");

        stream.forEach(alias -> assertThat(GeoOrientation.Right).isEqualTo(GeoOrientation._DESERIALIZER.parse(alias)));

    }

    @Test
    public void testBooleanEnum() {

        // Quoted value
        assertThat(Refresh.WaitFor).isEqualTo(checkJsonRoundtrip(Refresh.WaitFor, "\"wait_for\""));

        // Unquoted boolean values
        assertThat(Refresh.True).isEqualTo(checkJsonRoundtrip(Refresh.True, "true"));

        assertThat(Refresh.False).isEqualTo(checkJsonRoundtrip(Refresh.False, "false"));

        // True/False as strings
        assertThat(Refresh.True).isEqualTo(fromJson("true", Refresh.class));

        assertThat(Refresh.False).isEqualTo(fromJson("false", Refresh.class));
    }
}