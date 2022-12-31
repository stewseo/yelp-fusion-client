package io.github.stewseo.client.yelpfusion.json;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import io.github.stewseo.client.json.JsonTestCase;
import io.github.stewseo.client.json.JsonpDeserializer;
import io.github.stewseo.client.json.JsonpMapper;
import io.github.stewseo.client.json.SimpleJsonpMapper;
import io.github.stewseo.client.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.client.json.jsonb.JsonbJsonpMapper;
import io.github.stewseo.client.yelpfusion.business.Business;

import jakarta.json.spi.JsonProvider;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class YelpFusionJsonTestCase extends Assertions implements JsonTestCase {

    private static final Logger logger = LoggerFactory.getLogger(YelpFusionJsonTestCase.class);

    private static final int RAND = new Random().nextInt(100);


    public final JsonpMapper mapper;

    private JsonpMapper setupMapper(int rand) {
        // Randomly choose json-b or jackson
        switch (rand % 3) {
            case 0 -> {
                return new JsonbJsonpMapper() {
                    @Override
                    public boolean ignoreUnknownFields() {
                        return false;
                    }
                };
            }
            case 1 -> {
                return new JacksonJsonpMapper() {
                    @Override
                    public boolean ignoreUnknownFields() {
                        return false;
                    }
                };
            }
            default -> {
                return SimpleJsonpMapper.INSTANCE_REJECT_UNKNOWN_FIELDS;
            }
        }
    }

    private final Gson gson;

    public YelpFusionJsonTestCase() {
        this(RAND);
    }

    protected YelpFusionJsonTestCase(int rand) {
        mapper = setupMapper(rand);
        gson = new Gson();
    }

    /**
     * @param business Business instance
     * @link <a href="https://www.baeldung.com/java-validate-json-string">...</a>
     */
    public void testSerializeToJSON(Business business) {

        String businessToString = business.toString();

        boolean validJsonByGson = isValidJson(businessToString);
        assertTrue(validJsonByGson);

        // assert that expected data as input stream
        // is equal to actual data as input stream
        String toJson = gson.toJson(business);
        testByteArray(toJson, businessToString);
    }

    static AtomicInteger count = new AtomicInteger(0);

    public <T> int assertIsValidJson(T json) {
        String actualJsonString = json.toString();

        // assert that business to String is valid JSON
        boolean validJsonByGson = isValidJson(actualJsonString);

        assertTrue(validJsonByGson);

        return count.getAndIncrement();
    }

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
        assertThat(expectedJson).isEqualTo(toJson(value));
        return fromJson(expectedJson, (Class<T>)value.getClass());
    }

    @Override
    public boolean isValidJson(String json) {
        final TypeAdapter<Business> strictAdapter = gson.getAdapter(Business.class);

        try {
            strictAdapter.fromJson(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public <T> T fromJson(String json, JsonpDeserializer<T> deserializer) {
        return fromJson(json, deserializer, mapper);
    }

    protected <T> T fromJson(String json, JsonpDeserializer<T> deserializer, JsonpMapper mapper) {
        JsonParser parser = mapper.jsonProvider().createParser(new StringReader(json));
        return deserializer.deserialize(parser, mapper);
    }

    private Stream<byte[]> testByteArray(String toJson, String businessToString) {

        final TypeAdapter<Business> strictAdapter = new Gson().getAdapter(Business.class);

        if (!toJson.equals(businessToString)) {
            assertThat(toJson).isEqualTo(businessToString);
            toJson = replaceInvalidJsonCharacters(toJson);
            businessToString = replaceInvalidJsonCharacters(businessToString);
        }
        assertThat(toJson).isEqualTo(businessToString);

        final byte[] toJsonToByteArray = toJson.getBytes(StandardCharsets.UTF_8);
        final byte[] businessToStringToByteArray = businessToString.getBytes(StandardCharsets.UTF_8);
        return Stream.of(toJsonToByteArray, businessToStringToByteArray);
    }

    private Stream<InputStream> testInputStream(String expectedJsonString, String actualJsonString) {

        final InputStream expectedJsonToIS = IOUtils.toInputStream(expectedJsonString, StandardCharsets.UTF_8);
        final InputStream actualJsonToIS = IOUtils.toInputStream(actualJsonString, StandardCharsets.UTF_8);

        try (expectedJsonToIS; actualJsonToIS) {

            expectedJsonToIS.close();

            actualJsonToIS.close();

            return Stream.of(expectedJsonToIS, actualJsonToIS);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String replaceInvalidJsonCharacters(String jsonString) {
        String validJson = "[^a-zA-Z0-9,{}:\"\n]";
        return jsonString.replaceAll(validJson, "");

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
}
