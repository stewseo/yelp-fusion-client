package io.github.stewseo.clients.json.jackson;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import io.github.stewseo.clients.json.JsonTest;
import io.github.stewseo.clients.json.testcases.ModelJsonTestCase;
import jakarta.json.Json;
import jakarta.json.JsonArray;

import java.io.IOException;
import java.io.StringReader;

import static org.assertj.core.api.Assertions.assertThat;

class JacksonJsonpParserTest extends ModelJsonTestCase {

    private final JacksonJsonpParser jacksonJsonpParser = new JacksonJsonpParser(getJsonParser());

    JsonParser getJsonParser() {
        try {
            return new JsonFactory().createParser(new StringReader("{\"coordinates\":{\"latitude\":37.7829,\"longitude\":-122.4189}," +
                    "\"transactions\":[\"transactionValue1\",\"transactionValue2\"]"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @JsonTest
    void testHasNext() {

        assertThat(jacksonJsonpParser.hasNext()).isTrue();

    }

    @JsonTest
    void testNext() {
        assertThat(jacksonJsonpParser.next()).isEqualTo(jakarta.json.stream.JsonParser.Event.START_OBJECT);

    }

    @JsonTest
    void testGetString() {
        while(jacksonJsonpParser.next() != jakarta.json.stream.JsonParser.Event.VALUE_STRING);

        assertThat(jacksonJsonpParser.getString()).isEqualTo("transactionValue1");
    }

    @JsonTest
    void testGetArray() {
        while(jacksonJsonpParser.next() != jakarta.json.stream.JsonParser.Event.START_ARRAY);
        JsonArray jsonArray = Json.createArrayBuilder().add("transactionValue1").add("transactionValue2").build();
        assertThat(jacksonJsonpParser.getArray()).isEqualTo(jsonArray);
    }

}