package io.github.stewseo.clients.testcase;

import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;

import java.lang.reflect.Type;

public interface JsonTestCase {

    <T> String toJson(T value);

    <T> String toJson(T value, JsonpMapper mapper);

    <T> T fromJson(String json, Class<T> clazz, JsonpMapper mapper);

    <T> T fromJson(String json, Type type, JsonpMapper mapper);

    <T> T fromJson(String json, Class<T> clazz);

    <T> T fromJson(String json, Type type);

    <T> T fromJson(String json, JsonpDeserializer<T> deserializer);

    <T> T checkJsonRoundtrip(T value, String expectedJson);

    boolean isValidJson(String json);

}
