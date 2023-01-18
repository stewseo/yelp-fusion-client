package io.github.stewseo.clients.json;

import io.github.stewseo.clients.json.testcases.ModelJsonTestCase;
import io.github.stewseo.clients.util.AllowForbiddenApis;
import io.github.stewseo.clients.yelpfusion._types.Category;
import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetails;
import io.github.stewseo.clients.yelpfusion.businesses.search.Hit;
import jakarta.json.Json;
import jakarta.json.JsonException;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import jakarta.json.spi.JsonProvider;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;

import java.io.StringWriter;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.function.Consumer;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.ALIAS;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.COUNTRY_BLACKLIST;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.COUNTRY_WHITELIST;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.PARENT_ALIAS;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.TITLE;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


public class JsonpUtilsTest extends ModelJsonTestCase {

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

            assertThrows(JsonException.class,
                    () -> assertNotNull(JsonProvider.provider()));

            assertNotNull(JsonpUtils.provider());

        } finally {
            Thread.currentThread().setContextClassLoader(savedLoader);
        }
    }

    @JsonTest
    public void testObjectToString() {

        // Test that we call toString() on application classes.
        Hit<SomeUserData> hit = Hit.of(h -> h.source(new SomeUserData()));

        assertThat(hit.toString())
                .isEqualTo("{\"source\":\"Some user data\"}");
    }

    private static class SomeUserData {
        @Override
        public String toString() {
            return "Some user data";
        }
    }

    @JsonTest
    @AllowForbiddenApis("Testing JsonpUtil.provider()")
    public void testProviderCache() {
        // A new provider at each call
        assertNotSame(JsonpUtils.findProvider(), JsonpUtils.findProvider());

        // Result is cached
        assertSame(JsonpUtils.provider(), JsonpUtils.provider());
    }
    
    @JsonTest
    public void testLargeObjectToString() {
        // Build a large string
        StringBuilder sb = new StringBuilder();
        sb.append("0123456789".repeat(1001));

        String text = sb.toString();
        assertThat(text.length()).isEqualTo(10010);

        Hit<String> hit = Hit.of(hi -> hi
                .source(text)
        );

        String toString = hit.toString();

        assertThat(toString.length())
                .isEqualTo(10000 + "...".length());

        assertThat(toString).startsWith("{\"source\":\"");
        assertThat(toString).endsWith("...");
    }

    @JsonTest
    public void testSerializeDoubleOrNull() {
        // ---- Double values
        assertThat(orNullHelper(g -> JsonpUtils.serializeDoubleOrNull(g, Double.NaN, Double.NaN))).isEqualTo(
                "{\"a\":null}");

        assertThat(orNullHelper(g -> JsonpUtils.serializeDoubleOrNull(g, 1.0, Double.NaN)))
                .isEqualTo("{\"a\":1.0}");


        assertThat(orNullHelper(g -> JsonpUtils.serializeDoubleOrNull(g, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY)))
                .isEqualTo("{\"a\":null}");

        assertThat(orNullHelper(g -> JsonpUtils.serializeDoubleOrNull(g, 1.0, Double.POSITIVE_INFINITY)))
                .isEqualTo("{\"a\":1.0}");

        assertThat(orNullHelper(g -> JsonpUtils.serializeDoubleOrNull(g, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY)))
                .isEqualTo("{\"a\":null}");

        assertThat(orNullHelper(g -> JsonpUtils.serializeDoubleOrNull(g, 1.0, Double.NEGATIVE_INFINITY)))
                .isEqualTo("{\"a\":1.0}");

        assertThat(orNullHelper(g -> JsonpUtils.serializeDoubleOrNull(g, Double.NaN, 0.0)))
                .isEqualTo("{\"a\":null}");

        // Serialize defined default values
        assertThat(orNullHelper(g -> JsonpUtils.serializeDoubleOrNull(g, 0.0, 0.0)))
                .isEqualTo("{\"a\":0.0}");
    }

    @JsonTest
    public void testSerializeIntOrNull() {
        assertThat(orNullHelper(g -> JsonpUtils.serializeIntOrNull(g, Integer.MAX_VALUE, Integer.MAX_VALUE)))
                .isEqualTo("{\"a\":null}");

        assertThat(orNullHelper(g -> JsonpUtils.serializeIntOrNull(g, 1, Integer.MAX_VALUE)))
                .isEqualTo("{\"a\":1}");

        assertThat(orNullHelper(g -> JsonpUtils.serializeIntOrNull(g, 1, 0)))
                .isEqualTo("{\"a\":1}");


        // Integer.MAX_VALUE is valid if not the default value
        assertThat(orNullHelper(g -> JsonpUtils.serializeIntOrNull(g, Integer.MAX_VALUE, 0)))
                .isEqualTo("{\"a\":2147483647}");

        assertThat(orNullHelper(g -> JsonpUtils.serializeIntOrNull(g, Integer.MAX_VALUE, Integer.MIN_VALUE)))
                .isEqualTo("{\"a\":2147483647}");


        // Serialize non infinite default values
        assertThat("{\"a\":0}").isEqualTo(orNullHelper(g -> JsonpUtils.serializeIntOrNull(g, 0, 0)));
    }

    @JsonTest
    public void testJsonString() {

        {
            Category category = Category.of(i -> i
                    .title(TITLE)
                    .alias(ALIAS)
                    .parent_aliases(PARENT_ALIAS)
                    .country_whitelist(COUNTRY_WHITELIST)
                    .country_blacklist(COUNTRY_BLACKLIST)
            );

            String json = "" +
                    "{" +
                        "\"alias\":\"alias\"," +
                        "\"title\":\"title\"," +
                        "\"parent_aliases\":" +
                            "[" +
                                "\"parents\"" +
                            "]," +
                        "\"country_whitelist\":" +
                            "[" +
                                "\"country_whitelist\"" +
                            "]," +
                        "\"country_blacklist\":" +
                            "[" +
                                "\"country_blacklist\"" +
                            "]" +
                    "}";

            assertThat(toJson(category)).isEqualTo(json);

            category = fromJson(json, Category.class);

            assertThat(category.parent_aliases()).isNotNull();

            assertThat(category.parent_aliases().stream()
                    .findAny().orElse(null)
            )
                    .isEqualTo(PARENT_ALIAS);
        }
    }

    @JsonTest
    void testExpectNextEvent() {
        JsonParser parser = parser();
        JsonParser.Event expectedEvent = JsonpUtils.expectNextEvent(parser, JsonParser.Event.START_OBJECT);

        assertThat(expectedEvent).isEqualTo(JsonParser.Event.START_OBJECT);
    }

    @JsonTest
    void testExpectEvent() {
        try(JsonParser parser = parser()) {

            UnexpectedJsonEventException unexpectedJsonEventException = assertThrows(UnexpectedJsonEventException.class,
                    () -> JsonpUtils.expectEvent(parser, JsonParser.Event.KEY_NAME, JsonParser.Event.START_OBJECT));

            assertThat(unexpectedJsonEventException.getMessage()).isEqualTo("Unexpected JSON event 'START_OBJECT' instead of 'KEY_NAME'");
        }
    }

    @JsonTest
    void testExpectKeyName() {
        try(JsonParser parser = parser()) {
            JsonParser.Event expectedEvent = JsonParser.Event.START_ARRAY;

            UnexpectedJsonEventException unexpectedJsonEventException = assertThrows(UnexpectedJsonEventException.class,
                    () -> JsonpUtils.expectKeyName(parser, expectedEvent));

            assertThat(unexpectedJsonEventException.getMessage())
                    .isEqualTo("Unexpected JSON event 'START_ARRAY' instead of 'KEY_NAME'");

            assertThat(JsonpUtils.expectKeyName(parser, JsonParser.Event.KEY_NAME)).isNull();
        }
    }

    @JsonTest
    void testEnsureAccepts() {
        try(JsonParser parser = parser()) {
            UnexpectedJsonEventException unexpectedJsonEventException = assertThrows(UnexpectedJsonEventException.class,
                    () -> JsonpUtils.ensureAccepts(JsonpDeserializer.stringDeserializer(), parser, JsonParser.Event.START_ARRAY));

            assertThat(unexpectedJsonEventException.getMessage())
                    .isEqualTo("Unexpected JSON event 'START_ARRAY' instead of '[KEY_NAME, VALUE_STRING, VALUE_NUMBER, VALUE_TRUE, VALUE_FALSE]'");

        }
    }

    @JsonTest
    void ensureCustomVariantsAllowed() {
        try(JsonParser parser = parser()) {
            JsonpUtils.ensureCustomVariantsAllowed(parser, mapper);

            String mapperAttribute = mapper.attribute(JsonpMapperFeatures.FORBID_CUSTOM_VARIANTS);
            assertThat(mapperAttribute).isNull();
        }

    }

    @JsonTest
    void testSkipValue() {
        try(JsonParser parser = parser()) {
            JsonpUtils.skipValue(parser());

            assertThat(parser.next()).isEqualTo(JsonParser.Event.START_OBJECT);
        }
    }

    @JsonTest
    void testLookAheadFieldValue() {
        try(JsonParser parser = parser()) {
            assertThrows(IllegalStateException.class,
                    () -> JsonpUtils.lookAheadFieldValue("name", "defaultValue", parser, mapper));
        }

    }

    @JsonTest
    void testObjectParser() {

        JsonObject jsonObject = Json.createObjectBuilder().add("name", 1).build();

        try (JsonParser parser = JsonpUtils.objectParser(jsonObject, mapper)) {
            assertThat(parser.next().name()).isEqualTo("START_OBJECT");
        }

    }

    @JsonTest
    void testTypedKeysToString() {

        assertThat(JsonpUtils.typedKeysToString(BusinessDetails.of(b -> b.id("id")))
        )
                .isEqualTo("BusinessDetails: {\"id\":\"id\"}"
                );
    }

    @JsonTest
    void testMaxToStringLength() {
        assertThat(JsonpUtils.maxToStringLength()).isEqualTo(JsonpUtils.MAX_TO_STRING_LENGTH);
    }

    @JsonTest
    void testToString3() {
        assertThat(JsonpUtils.toString(JsonValue.TRUE)).isEqualTo("true");
    }

    @Override
    public JsonParser parser()   {
        Hit<String> hitString = Hit.of(h -> h.source("stringValue"));
        return parser(hitString);
    }

    private static String orNullHelper(Consumer<JsonGenerator> c) {
        StringWriter sw = new StringWriter();
        JsonGenerator generator = JsonpUtils.provider().createGenerator(sw);

        generator.writeStartObject();
        generator.writeKey("a");
        c.accept(generator);
        generator.writeEnd();
        generator.close();

        return sw.toString();
    }
}
