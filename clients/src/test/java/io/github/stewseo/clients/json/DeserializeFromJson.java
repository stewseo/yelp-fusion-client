package io.github.stewseo.clients.json;

import io.github.stewseo.clients.json.testcases.JsonTestCase;
import jakarta.json.stream.JsonParser;
import org.junit.jupiter.api.Test;


public interface DeserializeFromJson extends JsonTestCase {

    JsonParser parser();

    @Test
    void testDeserializer();

    @Test
    void testDeserialize();
}
