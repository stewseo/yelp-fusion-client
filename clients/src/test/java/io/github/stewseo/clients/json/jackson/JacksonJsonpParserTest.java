package io.github.stewseo.clients.json.jackson;

import io.github.stewseo.clients.json.testcases.ModelJsonTestCase;
import jakarta.json.stream.JsonParser;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

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