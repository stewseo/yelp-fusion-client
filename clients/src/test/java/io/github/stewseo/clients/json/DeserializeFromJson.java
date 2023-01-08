package io.github.stewseo.clients.json;

import io.github.stewseo.clients.json.testcases.JsonTestCase;
import jakarta.json.spi.JsonProvider;
import jakarta.json.stream.JsonParser;
import org.junit.jupiter.api.Test;

import java.io.StringReader;


public interface DeserializeFromJson extends JsonTestCase {

    @Test
    void testDeserializer();

    @Test
    void testDeserialize();

    JsonParser parser();

}
