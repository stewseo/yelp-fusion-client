package io.github.stewseo.clients.json;

import io.github.stewseo.clients.json.testcases.TestJsonp;
import jakarta.json.stream.JsonParser;

public interface DeserializeFromJson extends TestJsonp {
    JsonParser parser();
}
