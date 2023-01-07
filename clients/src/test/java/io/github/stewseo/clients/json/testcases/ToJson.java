package io.github.stewseo.clients.json.testcases;

import io.github.stewseo.clients.json.JsonpMapper;

public interface ToJson extends JsonTestCase {
    <T> String toJson(T value);
    <T> String toJson(T value, JsonpMapper mapper);
}
