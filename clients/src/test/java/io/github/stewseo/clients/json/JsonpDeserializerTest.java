package io.github.stewseo.clients.json;

import io.github.stewseo.clients.json.jackson.JacksonJsonpMapper;
import jakarta.json.stream.JsonParser;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class JsonpDeserializerTest {

    @Test
    public void testNullStringInArray() {

        JsonpDeserializer<List<String>> deser = JsonpDeserializer.arrayDeserializer(JsonpDeserializer.stringDeserializer());

        JsonParser parser = new JacksonJsonpMapper().jsonProvider().createParser(new StringReader("[\"a\", null, \"b\"]"));
        List<String> list = deser.deserialize(parser, new JacksonJsonpMapper());

        assertEquals("a", list.get(0));
        assertNull(list.get(1));
        assertEquals("b", list.get(2));
    }

}
