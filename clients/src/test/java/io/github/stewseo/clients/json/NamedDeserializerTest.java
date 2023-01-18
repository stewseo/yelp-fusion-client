package io.github.stewseo.clients.json;

import io.github.stewseo.clients.json.testcases.AbstractJsonTestCase;
import jakarta.json.stream.JsonParser;

import java.util.EnumSet;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.SEARCH_BUSINESS_RESULT;
import static jakarta.json.stream.JsonParser.Event.VALUE_FALSE;
import static jakarta.json.stream.JsonParser.Event.VALUE_NULL;
import static jakarta.json.stream.JsonParser.Event.VALUE_NUMBER;
import static jakarta.json.stream.JsonParser.Event.VALUE_STRING;
import static jakarta.json.stream.JsonParser.Event.VALUE_TRUE;
import static org.assertj.core.api.Assertions.assertThat;

class NamedDeserializerTest extends AbstractJsonTestCase {

    private final EnumSet<JsonParser.Event> enumSet = EnumSet.of(JsonParser.Event.START_OBJECT,
            JsonParser.Event.START_ARRAY, VALUE_STRING, VALUE_NUMBER, VALUE_TRUE, VALUE_FALSE, VALUE_NULL);

    private final NamedDeserializer<String> namedDeserializer =
            new NamedDeserializer<String>("io.github.stewseo.clients:Deserializer:_global.search._types.ResultT");


    @JsonTest
    void nativeEvents() {
        assertThat(namedDeserializer.nativeEvents()).isEqualTo(enumSet);
    }

    @JsonTest
    void acceptedEvents() {
        assertThat(namedDeserializer.acceptedEvents()).isEqualTo(enumSet);
        assertThat(namedDeserializer.accepts(JsonParser.Event.KEY_NAME)).isFalse();
    }

    @JsonTest
    void testDeserialize() {

    }

    @JsonTest
    void testDeserializeEvent() {

    }

    @Override
    public JsonParser parser() {
        return parser(SEARCH_BUSINESS_RESULT);
    }
}