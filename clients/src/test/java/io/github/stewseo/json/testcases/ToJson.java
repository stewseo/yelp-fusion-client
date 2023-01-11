package io.github.stewseo.json.testcases;

import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.testcases.JsonTestCase;

public interface ToJson extends JsonTestCase {
    <T> String toJson(T value);
    <T> String toJson(T value, JsonpMapper mapper);
}
