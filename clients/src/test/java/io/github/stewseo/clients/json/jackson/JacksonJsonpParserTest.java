package io.github.stewseo.clients.json.jackson;

import io.github.stewseo.clients.json.testcases.ModelJsonTestCase;
import io.github.stewseo.clients.json.testcases.TestJsonp;
import jakarta.json.stream.JsonParser;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.Map;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.TestReqVars.SEARCH_BUSINESSES_REQUEST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class JacksonJsonpParserTest extends ModelJsonTestCase {

    private final JacksonJsonProvider jacksonJsonProvider = new JacksonJsonProvider();

    @Test
    void hasNext() {

    }

    @Test
    void next() {
    }

    @Test
    void getString() {

        JacksonJsonProvider jacksonJsonProvider = new JacksonJsonProvider();

        assertThat(jacksonJsonProvider.createArrayBuilder()).isNotNull();

        JsonParser parser = jacksonJsonProvider.createParser(new StringReader("json"));

        assertThrows(Exception.class, () -> jacksonJsonProvider.createParserFactory(Map.of("k", "v"))
        );
    }
}