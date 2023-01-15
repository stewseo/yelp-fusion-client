package io.github.stewseo.clients.json;

import io.github.stewseo.clients.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.clients.util.AllowForbiddenApis;
import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetails;
import jakarta.json.Json;
import jakarta.json.JsonException;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import jakarta.json.spi.JsonProvider;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Enumeration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class JsonpUtilsTest implements SerializeToJson, DeserializeFromJson {

    private final JsonProvider jsonProvider = new JacksonJsonpMapper().jsonProvider();

    @JsonTest
    @AllowForbiddenApis("Testing JsonpUtil.provider()")
    public void testProviderLoading() {
        // See https://github.com/elastic/elasticsearch-java/issues/163

        // Create an empty non-delegating classloader and set it as the context classloader. It simulates a
        // plugin system that doesn't set the context classloader to the plugins classloader.
        ClassLoader emptyLoader = new ClassLoader() {
            @Override
            public Enumeration<URL> getResources(String name) {
                return Collections.emptyEnumeration();
            }
        };

        ClassLoader savedLoader = Thread.currentThread().getContextClassLoader();
        try {
            Thread.currentThread().setContextClassLoader(emptyLoader);

            assertThrows(JsonException.class, () -> assertNotNull(JsonProvider.provider()));

            assertNotNull(JsonpUtils.provider());

        } finally {
            Thread.currentThread().setContextClassLoader(savedLoader);
        }
    }

    private final JsonGenerator generator = generator();

    private final JsonParser parser = parser();


    @JsonTest
    void expectNextEvent() {

        JsonParser.Event expectedEvent = JsonpUtils.expectNextEvent(parser, JsonParser.Event.START_OBJECT);

        assertThat(expectedEvent).isEqualTo(JsonParser.Event.START_OBJECT);
    }

    @JsonTest
    void expectEvent() {

        UnexpectedJsonEventException unexpectedJsonEventException = assertThrows(UnexpectedJsonEventException.class,
                () -> JsonpUtils.expectEvent(parser, JsonParser.Event.KEY_NAME, JsonParser.Event.START_OBJECT));

        assertThat(unexpectedJsonEventException.getMessage()).isEqualTo("Unexpected JSON event 'START_OBJECT' instead of 'KEY_NAME'");
    }

    @JsonTest
    void expectKeyName() {

        JsonParser.Event expectedEvent = JsonParser.Event.START_ARRAY;

        UnexpectedJsonEventException unexpectedJsonEventException = assertThrows(UnexpectedJsonEventException.class,
                () -> JsonpUtils.expectKeyName(parser, expectedEvent));

        assertThat(unexpectedJsonEventException.getMessage())
                .isEqualTo("Unexpected JSON event 'START_ARRAY' instead of 'KEY_NAME'");

        assertThat(JsonpUtils.expectKeyName(parser, JsonParser.Event.KEY_NAME)).isNull();

    }

    @JsonTest
    void ensureAccepts() {

        UnexpectedJsonEventException unexpectedJsonEventException = assertThrows(UnexpectedJsonEventException.class,
                () -> JsonpUtils.ensureAccepts(JsonpDeserializer.stringDeserializer(), parser, JsonParser.Event.START_ARRAY));

        assertThat(unexpectedJsonEventException.getMessage())
                .isEqualTo("Unexpected JSON event 'START_ARRAY' instead of '[KEY_NAME, VALUE_STRING, VALUE_NUMBER, VALUE_TRUE, VALUE_FALSE]'");

    }

    @JsonTest
    void ensureCustomVariantsAllowed() {

        JsonpUtils.ensureCustomVariantsAllowed(parser, mapper);

        String mapperAttribute = mapper.attribute(JsonpMapperFeatures.FORBID_CUSTOM_VARIANTS);
        assertThat(mapperAttribute).isNull();

    }

    @JsonTest
    void testSkipValue() {

      JsonpUtils.skipValue(parser);

      assertThat(parser.hasNext()).isFalse();
    }


    @JsonTest
    void lookAheadFieldValue() {

        assertThrows(IllegalStateException.class,
                () -> JsonpUtils.lookAheadFieldValue("name", "defaultValue", parser, mapper));

    }

    @JsonTest
    void objectParser() {

        JsonObject jsonObject = Json.createObjectBuilder().add("name", 1).build();

        try (JsonParser parser = JsonpUtils.objectParser(jsonObject, mapper)) {
            assertThat(parser.next().name()).isEqualTo("START_OBJECT");
        }

    }

    @JsonTest
    void serializeDoubleOrNull() {

        double doubleValue = 5.0;

        JsonpUtils.serializeDoubleOrNull(generator, doubleValue, 0);

        assertThat(doubleValue).isEqualTo(5.0);
    }

    @JsonTest
    void serializeIntOrNull() {

        int value = 1;

        JsonpUtils.serializeIntOrNull(generator, value, 0);

        assertThat(value).isEqualTo(1);
    }


    @JsonTest
    void typedKeysToString() {
        assertThat(JsonpUtils.typedKeysToString(BusinessDetails.of(b -> b.id("id")))).isEqualTo("BusinessDetails: {...");
    }

    @JsonTest
    void maxToStringLength() {
        assertThat(JsonpUtils.maxToStringLength()).isEqualTo(JsonpUtils.MAX_TO_STRING_LENGTH);
    }

    @JsonTest
    void testMaxToStringLength() {
        JsonpUtils.maxToStringLength(1);
        assertThat(JsonpUtils.maxToStringLength()).isEqualTo(1);
    }

    @JsonTest
    void testToString1() {
        assertThat(JsonpUtils.toString(BusinessDetails.of(b -> b.id("id")))).isEqualTo("{...");

    }


    @JsonTest
    void testToString3() {
        assertThat(JsonpUtils.toString(JsonValue.TRUE)).isEqualTo("true");

    }

    @JsonTest
    public void testSerialize() {

    }

    @JsonTest
    public void testSerializeInternal() {

    }

    @JsonTest
    public void testDeserializer() {

    }

    @JsonTest
    public void testDeserialize() {

    }

    public JsonpMapper mapper() {
        return new JacksonJsonpMapper();
    }

    public JsonGenerator generator() {
        return jsonProvider.createGenerator(new StringWriter());
    }

    @Override
    public JsonParser parser()   {

        try(InputStream content = IOUtils.toInputStream("{\"date\":\"1/6/2023\"}", StandardCharsets.UTF_8)) {

            return jsonProvider.createParser(content);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
