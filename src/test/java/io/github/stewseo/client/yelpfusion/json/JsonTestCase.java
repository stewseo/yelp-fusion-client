package io.github.stewseo.client.yelpfusion.json;

import io.github.stewseo.client.json.JsonpDeserializer;
import io.github.stewseo.client.json.JsonpMapper;
import io.github.stewseo.client.yelpfusion.events.Event;

import java.lang.reflect.Type;

public interface JsonTestCase {

    public <T> String toJson(T value);

    public <T> String toJson(T value, JsonpMapper mapper);

    public <T> T fromJson(String json, Class<T> clazz, JsonpMapper mapper);

    public <T> T fromJson(String json, Type type, JsonpMapper mapper);

    <T> T fromJson(String json, Class<T> clazz);

    <T> T fromJson(String json, Type type);

    <T> T checkJsonRoundtrip(T value, String expectedJson);

    <T> T fromJson(String json, JsonpDeserializer<T> deserializer);

    <T> T fromJson(String json, JsonpDeserializer<T> deserializer, JsonpMapper mapper);

    void assertGetterType(Class<?> expected, Class<?> clazz, String name);

    JsonpMapper mapper();

    <T> int assertIsValidJson(T object);
}
