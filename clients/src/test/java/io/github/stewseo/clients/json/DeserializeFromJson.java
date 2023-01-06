package io.github.stewseo.clients.json;

import io.github.stewseo.clients.json.testcases.JsonTestCase;
import jakarta.json.stream.JsonParser;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

@FunctionalInterface
public interface DeserializeFromJson extends JsonTestCase {

    JsonParser parser = mapper.jsonProvider().createParser(new StringReader("test"));

    @Test
    void testDeserialize();


}
