package io.github.stewseo.clients.json;

import io.github.stewseo.clients.json.testcases.JsonTestCase;
import jakarta.json.stream.JsonGenerator;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;

public interface SerializeToJson extends JsonTestCase {

    JsonGenerator generator = mapper.jsonProvider().createGenerator(new StringWriter());

    // test instantiating objects using Builder setters
    @Test
    void testOf();

    // test object's serialize method
    @Test
    void testSerialize();

    //  test object's serializeInternal methods
    @Test
    void testSerializeInternal();

}
