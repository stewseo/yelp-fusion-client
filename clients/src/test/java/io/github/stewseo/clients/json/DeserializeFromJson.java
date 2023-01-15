package io.github.stewseo.clients.json;

import io.github.stewseo.clients.json.testcases.JsonTestCase;
import jakarta.json.stream.JsonParser;

public interface DeserializeFromJson extends JsonTestCase {
    JsonParser parser();
}
