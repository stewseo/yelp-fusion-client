package io.github.stewseo.clients.json;

import io.github.stewseo.clients.json.testcases.JsonTestCase;
import jakarta.json.stream.JsonGenerator;
import org.junit.jupiter.api.Test;

public interface SerializeToJson extends JsonTestCase {

    // test object's serialize method
    @Test
    void testSerialize();

    //  test object's serializeInternal methods
    @Test
    void testSerializeInternal();

    JsonGenerator generator();

}
