package io.github.stewseo.clients.json;

import io.github.stewseo.clients.json.testcases.TestJson;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessResult;
import jakarta.json.stream.JsonParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void allAcceptedEvents() {
        JsonpDeserializerBase<SearchBusinessResult> jsonpDeserializerBase;
    }

    @Test
    void nativeEvents() {

    }

    @Test
    void acceptedEvents() {
    }

    @Test
    void accepts() {
    }

    @Test
    void testStringMapDeserializer() {

    }

    @JsonTest
    void testDoubleOrNullDeserializer() {

        double expected = 72.10;

        JsonpDeserializerBase.DoubleOrNullDeserializer doubleOrNullDeserializer =
                new JsonpDeserializerBase.DoubleOrNullDeserializer(0);

        assertThat(doubleOrNullDeserializer.nativeEvents()).contains(JsonParser.Event.VALUE_NUMBER);
        assertThat(doubleOrNullDeserializer.acceptedEvents()).contains(JsonParser.Event.VALUE_NUMBER);

        double value = fromJson("72.10", doubleOrNullDeserializer);

        assertThat(value).isEqualTo(expected);
    }

    @JsonTest
    void testIntOrNullDeserializer() {
        int expected = 72;

        JsonpDeserializerBase.IntOrNullDeserializer intOrNullDeserializer =
                new JsonpDeserializerBase.IntOrNullDeserializer(0);

        assertThat(intOrNullDeserializer.nativeEvents()).contains(JsonParser.Event.VALUE_NUMBER);
        assertThat(intOrNullDeserializer.acceptedEvents()).contains(JsonParser.Event.VALUE_NUMBER);

        double value = fromJson("72", intOrNullDeserializer);

        assertThat(value).isEqualTo(expected);

    }
    @Test
    void testEnumMapDeserializer() {

    }
}