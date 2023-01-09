package io.github.stewseo.clients.json;

import io.github.stewseo.clients.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.clients.util.AllowForbiddenApis;
import jakarta.json.JsonException;
import jakarta.json.JsonObject;
import jakarta.json.spi.JsonProvider;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JsonpUtilsTest {

    @Test
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

    @Test
    void expectNextEvent() {
    }

    @Test
    void expectEvent() {
    }

    @Test
    void expectKeyName() {
    }

    @Test
    void ensureAccepts() {
    }

    @Test
    void ensureCustomVariantsAllowed() {
    }

    @Test
    void skipValue() {
    }

    @Test
    void testSkipValue() {
    }

    @Test
    void serialize() {
    }

    @Test
    void lookAheadFieldValue() {
    }

    @Test
    void objectParser() {


    }
    private final JsonGenerator generator = new JacksonJsonpMapper().jsonProvider().createGenerator(new StringWriter());
    @Test
    void serializeDoubleOrNull() {

        double doubleValue = 5.0;

        JsonpUtils.serializeDoubleOrNull(generator, doubleValue, 1.0);

        assertThat(doubleValue).isNotNull();

        int doubleVal = 5;

        JsonpUtils.serializeDoubleOrNull(generator, doubleVal, 1.0);

        assertThat(doubleVal).isNotNull();
    }

    @Test
    void serializeIntOrNull() {
    }

    @Test
    void testToString1() {
    }

    @Test
    void typedKeysToString() {
    }

    @Test
    void maxToStringLength() {
    }

    @Test
    void testMaxToStringLength() {
    }

    @Test
    void testToString2() {
    }

    @Test
    void testToString3() {
    }
}
