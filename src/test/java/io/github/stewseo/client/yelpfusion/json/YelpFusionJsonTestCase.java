package io.github.stewseo.client.yelpfusion.json;

import io.github.stewseo.client.json.JsonpDeserializer;
import io.github.stewseo.client.json.JsonpMapper;
import io.github.stewseo.client.json.SimpleJsonpMapper;
import io.github.stewseo.client.json.ValidateJson;
import io.github.stewseo.client.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.client.json.jsonb.JsonbJsonpMapper;
import jakarta.json.spi.JsonProvider;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.junit.jupiter.api.Assertions;

import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class YelpFusionJsonTestCase extends Assertions implements JsonTestCase {

    private static final int RAND = new Random().nextInt(100);

    public final JsonpMapper mapper;

    public final ValidateJson validateJson;

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

    private JsonpMapper setupMapper(int rand) {
        // Randomly choose json-b or jackson
        switch (rand % 3) {
            case 0 -> {
                System.out.println("Using a JsonB mapper (rand = " + rand + ").");
                return new JsonbJsonpMapper() {
                    @Override
                    public boolean ignoreUnknownFields() {
                        return false;
                    }
                };
            }
            case 1 -> {
                System.out.println("Using a Jackson mapper (rand = " + rand + ").");
                return new JacksonJsonpMapper() {
                    @Override
                    public boolean ignoreUnknownFields() {
                        return false;
                    }
                };
            }
            default -> {
                System.out.println("Using a simple mapper (rand = " + rand + ").");
                return SimpleJsonpMapper.INSTANCE_REJECT_UNKNOWN_FIELDS;
            }
        }
    }

    public YelpFusionJsonTestCase() {
        this(RAND, new ValidateJson());
    }

    protected YelpFusionJsonTestCase(int rand, ValidateJson validateJson) {
        this.validateJson = validateJson;
        mapper = setupMapper(rand);
    }

    public <T> T fromJson(String json, Class<T> clazz, JsonpMapper mapper) {
        return fromJson(json, (Type)clazz, mapper);
    }

    public <T> T fromJson(String json, Type type, JsonpMapper mapper) {
        JsonParser parser = mapper.jsonProvider().createParser(new StringReader(json));
        return mapper.deserialize(parser, type);
    }

    public <T> T fromJson(String json, Class<T> clazz) {
        return fromJson(json, clazz, mapper);
    }


    public <T> T fromJson(String json, Type type) {
        return fromJson(json, type, mapper);
    }

    @SuppressWarnings("unchecked")
    public <T> T checkJsonRoundtrip(T value, String expectedJson) {
        assertThat(expectedJson).isEqualTo(value);
        return fromJson(expectedJson, (Class<T>)value.getClass());
    }

    public <T> T fromJson(String json, JsonpDeserializer<T> deserializer) {
        return fromJson(json, deserializer, mapper);
    }

    public <T> T fromJson(String json, JsonpDeserializer<T> deserializer, JsonpMapper mapper) {
        JsonParser parser = mapper.jsonProvider().createParser(new StringReader(json));
        return deserializer.deserialize(parser, mapper);
    }


    public void assertGetterType(Class<?> expected, Class<?> clazz, String name) {
        Method method;
        try {
            method = clazz.getMethod(name);
        } catch (NoSuchMethodException e) {
            fail("Getter '" + clazz.getName() + "." + name + "' doesn't exist");
            return;
        }

        assertSame(expected, method.getReturnType());
    }

    @Override
    public JsonpMapper mapper() {
        return mapper;
    }

    @Override
    public <T> int assertIsValidJson(T json) {
        String actualJsonString = json.toString();
        // assert that business to String is valid JSON
        boolean validJsonByJackson = validateJson.isValidJsonUsingJackson(actualJsonString);
        assertTrue(validJsonByJackson);

        boolean validJsonByGson = validateJson.isValidJsonUsingGson(actualJsonString);
        assertTrue(validJsonByGson);

        return 1;
    }
}

