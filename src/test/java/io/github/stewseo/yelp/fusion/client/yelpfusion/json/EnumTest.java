package io.github.stewseo.yelp.fusion.client.yelpfusion.json;


import co.elastic.clients.elasticsearch._types.Bytes;
import co.elastic.clients.elasticsearch._types.Refresh;
import co.elastic.clients.elasticsearch._types.mapping.GeoOrientation;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EnumTest {

    @Test
    public void testSimpleEnum() {
        assertNull(Bytes.GigaBytes.aliases());
        assertEquals(Bytes.GigaBytes, Bytes._DESERIALIZER.parse("gb"));
    }

    @Test
    public void testEnumWithAliases() {
        assertEquals("left", GeoOrientation.Left.jsonValue());
        assertNotNull(GeoOrientation.Left.aliases());

        Arrays.asList("right", "RIGHT", "counterclockwise", "ccw").forEach(alias -> {
            assertEquals(GeoOrientation.Right, GeoOrientation._DESERIALIZER.parse(alias));
        });
    }

//    @Test
//    public void testBooleanEnum() {
//        // Quoted value
//        assertEquals(Refresh.WaitFor, checkJsonRoundtrip(Refresh.WaitFor, "\"wait_for\""));
//
//        // Unquoted boolean values
//        assertEquals(Refresh.True, checkJsonRoundtrip(Refresh.True, "true"));
//        assertEquals(Refresh.False, checkJsonRoundtrip(Refresh.False, "false"));
//
//        // true/false as strings
//        assertEquals(Refresh.True, fromJson("\"true\"", Refresh.class));
//        assertEquals(Refresh.False, fromJson("\"false\"", Refresh.class));
//    }
}