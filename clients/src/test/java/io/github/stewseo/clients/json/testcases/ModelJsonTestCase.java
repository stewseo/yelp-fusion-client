package io.github.stewseo.clients.json.testcases;

import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import jakarta.json.spi.JsonProvider;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;

import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class ModelJsonTestCase extends AbstractJsonTestCase implements ToJson, FromJson {

    public <T> String toJson(T value) {
        return toJson(value, mapper);
    }

    public <T> String toJson(T value, JsonpMapper mapper) {
        StringWriter sw = new StringWriter();
        JsonProvider provider = mapper.jsonProvider();
        JsonGenerator generator = provider.createGenerator(sw);
        mapper.serialize(value, generator);
        generator.close();
        return sw.toString();
    }

    public <T> T fromJson(String json, Class<T> clazz) {
        return fromJson(json, clazz, mapper);
    }

    public <T> T fromJson(String json, Class<T> clazz, JsonpMapper mapper) {
        return fromJson(json, (Type) clazz, mapper);
    }

    public <T> T fromJson(String json, Type type, JsonpMapper mapper) {
        JsonParser parser = mapper.jsonProvider().createParser(new StringReader(json));
        return mapper.deserialize(parser, type);
    }

    public <T> T fromJson(String json, JsonpDeserializer<T> deserializer) {
        return fromJson(json, deserializer, mapper);
    }

    protected <T> T fromJson(String json, JsonpDeserializer<T> deserializer, JsonpMapper mapper) {
        JsonParser parser = mapper.jsonProvider().createParser(new StringReader(json));
        return deserializer.deserialize(parser, mapper);
    }

    public <T> T fromJson(String json, Type type) {
        return fromJson(json, type, mapper);
    }

    @SuppressWarnings("unchecked")
    public <T> T checkJsonRoundtrip(T value, String expectedJson) {
        assertThat(expectedJson).isEqualTo(toJson(value));
        return fromJson(expectedJson, (Class<T>) value.getClass());
    }

    public static void assertGetterType(Class<?> expected, Class<?> clazz, String name) {
        Method method;
        try {
            method = clazz.getMethod(name);
        } catch (NoSuchMethodException e) {
            fail("Getter '" + clazz.getName() + "." + name + "' doesn't exist");
            return;
        }

        assertThat(expected).isSameAs(method.getReturnType());
    }

    public JsonParser parser() {
        return parser("test");
    }

}
