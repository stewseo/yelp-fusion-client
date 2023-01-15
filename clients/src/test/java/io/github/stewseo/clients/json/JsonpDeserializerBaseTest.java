package io.github.stewseo.clients.json;


import io.github.stewseo.clients.json.testcases.TestJson;
import jakarta.json.stream.JsonParser;

import java.util.List;

public class JsonpDeserializerBaseTest extends TestJson {

    @JsonTest
    public void testArrayDeserializer() {

        JsonpDeserializer<List<Integer>> deser =
                JsonpDeserializer.arrayDeserializer(JsonpDeserializer.integerDeserializer());

        assertFalse(deser.nativeEvents().contains(JsonParser.Event.VALUE_NUMBER));
        assertTrue(deser.acceptedEvents().contains(JsonParser.Event.VALUE_NUMBER));

        List<Integer> values = fromJson("[ 42, 43 ]", deser);
        assertEquals(2, values.size());
        assertEquals(42, values.get(0));
        assertEquals(43, values.get(1));

        // Single value representation
        values = fromJson("42", deser);
        assertEquals(1, values.size());
        assertEquals(42, values.get(0));

    }
}