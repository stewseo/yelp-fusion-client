package io.github.stewseo.clients.json.testcases;

import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;

public interface FromJson extends JsonTestCase {

    <T> T fromJson(String json, Class<T> clazz, JsonpMapper mapper);

    <T> String toJson(T value);

    <T> String toJson(T value, JsonpMapper mapper);

    <T> T fromJson(String json, JsonpDeserializer<T> deserializer);

}
