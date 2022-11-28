package org.example.yelp.fusion.client.model;

import org.example.elasticsearch.client.json.JsonpDeserializer;
import org.example.elasticsearch.client.json.JsonpMapper;
import jakarta.json.spi.JsonProvider;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.example.elasticsearch.client.json.SimpleJsonpMapper;
import org.example.elasticsearch.client.json.jackson.JacksonJsonpMapper;
import org.example.elasticsearch.client.json.jsonb.JsonbJsonpMapper;
import org.junit.jupiter.api.Assertions;

import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Random;

public abstract class ModelTestCase extends Assertions {

    // Same value for all tests in a test run
    private static final int RAND = new Random().nextInt(100);

    protected final JsonpMapper mapper;

    private JsonpMapper setupMapper(int rand) {
        // Randomly choose json-b or jackson
        switch(rand % 3) {
            case 0:
                System.out.println("Using a JsonB mapper (rand = " + rand + ").");
                return new JsonbJsonpMapper() {
                    @Override
                    public boolean ignoreUnknownFields() {
                        return false;
                    }
                };

            case 1:
                System.out.println("Using a Jackson mapper (rand = " + rand + ").");
                return new JacksonJsonpMapper() {
                    @Override
                    public boolean ignoreUnknownFields() {
                        return false;
                    }
                };

            default:
                System.out.println("Using a simple mapper (rand = " + rand + ").");
                return SimpleJsonpMapper.INSTANCE_REJECT_UNKNOWN_FIELDS;
        }
    }

    protected ModelTestCase() {
        this(RAND);
    }

    protected ModelTestCase(int rand) {
        mapper = setupMapper(rand);
    }

    protected <T> String toJson(T value) {
        return toJson(value, mapper);
    }

    public static <T> String toJson(T value, JsonpMapper mapper) {
        StringWriter sw = new StringWriter();
        JsonProvider provider = mapper.jsonProvider();
        JsonGenerator generator = provider.createGenerator(sw);
        mapper.serialize(value, generator);
        generator.close();
        return sw.toString();
    }

    public static <T> T fromJson(String json, Class<T> clazz, JsonpMapper mapper) {
        return fromJson(json, (Type)clazz, mapper);
    }

    public static <T> T fromJson(String json, Type type, JsonpMapper mapper) {
        JsonParser parser = mapper.jsonProvider().createParser(new StringReader(json));
        return mapper.deserialize(parser, type);
    }

    protected <T> T fromJson(String json, Class<T> clazz) {
        return fromJson(json, clazz, mapper);
    }

    protected <T> T fromJson(String json, Type type) {
        return fromJson(json, type, mapper);
    }

    @SuppressWarnings("unchecked")
    protected <T> T checkJsonRoundtrip(T value, String expectedJson) {
        assertEquals(expectedJson, toJson(value));
        return fromJson(expectedJson, (Class<T>)value.getClass());
    }

    protected <T> T fromJson(String json, JsonpDeserializer<T> deserializer) {
        return fromJson(json, deserializer, mapper);
    }

    protected <T> T fromJson(String json, JsonpDeserializer<T> deserializer, JsonpMapper mapper) {
        JsonParser parser = mapper.jsonProvider().createParser(new StringReader(json));
        return deserializer.deserialize(parser, mapper);
    }


    public static void assertGetterType(Class<?> expected, Class<?> clazz, String name) {
        Method method;
        try {
            method = clazz.getMethod(name);
        } catch (NoSuchMethodException e) {
            fail("Getter '" + clazz.getName() + "." + name + "' doesn't exist");
            return;
        }

        assertSame(expected, method.getReturnType());
    }
}
