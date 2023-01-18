package io.github.stewseo.clients.json;

import io.github.stewseo.clients.json.testcases.ModelJsonTestCase;
import io.github.stewseo.clients.yelpfusion._types.QueryParameter;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessesResult;
import jakarta.json.stream.JsonParser;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;
import java.util.List;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.QUERY_PARAMETER;
import static org.assertj.core.api.Assertions.assertThat;

public class JsonpDeserializerBaseTest extends ModelJsonTestCase {


    @JsonTest
    public void testArrayDeserializer() {


        JsonpDeserializerBase.ArrayDeserializer<Integer> deser =
                new JsonpDeserializerBase.ArrayDeserializer<Integer>(JsonpDeserializer.integerDeserializer()
                );

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
        JsonpDeserializerBase<SearchBusinessesResult> jsonpDeserializerBase;
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

    @JsonTest
    void testEnumMapDeserializer() {
        JsonpDeserializerBase.EnumMapDeserializer<?, ?> enumMapDeserializer =
                new JsonpDeserializerBase.EnumMapDeserializer<>(
                        JsonpDeserializer.stringDeserializer(),
                        JsonpDeserializer.stringDeserializer());
        assertThat(enumMapDeserializer.acceptedEvents()).isNotNull();
        assertThat(enumMapDeserializer.nativeEvents()).isNotNull();

    }

    @JsonTest
    void testStringMapDeserializer() {
        JsonpDeserializer<QueryParameter> deserializer = QueryParameter._DESERIALIZER;

        JsonpDeserializerBase.StringMapDeserializer<QueryParameter> jsonpDeserializerBase =
                new JsonpDeserializerBase.StringMapDeserializer<QueryParameter>(deserializer);

        JsonpMappingException unexpectedJsonEventException = assertThrows(JsonpMappingException.class, () ->
                jsonpDeserializerBase.deserialize(parser(), mapper));

        assertThat(unexpectedJsonEventException.getMessage()).startsWith("");

        JsonpMappingException jsonMappingException =
                assertThrows(JsonpMappingException.class, () ->
                        jsonpDeserializerBase.deserialize(parser(), mapper, JsonParser.Event.START_ARRAY));

        assertThat(jsonpDeserializerBase.acceptedEvents()).isEqualTo(EnumSet.of(JsonParser.Event.START_OBJECT));
        assertThat(jsonpDeserializerBase.nativeEvents()).isEqualTo(EnumSet.of(JsonParser.Event.START_OBJECT));
    }

    @Override
    public JsonParser parser() {
        return parser(QUERY_PARAMETER);
    }
}