package io.github.stewseo.clients.json;

import io.github.stewseo.clients.json.jsonb.JsonbJsonpMapper;
import io.github.stewseo.clients.json.testcases.TestJsonp;
import io.github.stewseo.clients.yelpfusion._types.QueryParameter;
import io.github.stewseo.clients.yelpfusion._types.QueryParameterVariant;
import io.github.stewseo.clients.yelpfusion._types.TermQueryParameter;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import jakarta.json.stream.JsonParsingException;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.QUERY_PARAMETER;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.TERM_QUERY_PARAM;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.TRUE_FALSE_LIST;
import static org.assertj.core.api.Assertions.setMaxStackTraceElementsDisplayed;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ExternallyTaggedUnionTest implements TestJsonp {

    private final QueryParameter expectedQueryField = QUERY_PARAMETER;

    final Map<String, List<QueryParameter>> typedKeysArray = Map.of("key", List.of(expectedQueryField));

    final Map<String, QueryParameter> typedKeys = Map.of("key", expectedQueryField);

    private final JsonbJsonpMapper mapper = new JsonbJsonpMapper();

    private final JsonGenerator generator = mapper.jsonProvider().createGenerator(new StringWriter());


    @JsonTest
    void testSerializeTypedKeys() {
        ExternallyTaggedUnion.serializeTypedKeys(typedKeys, generator, mapper);
        assertThat(typedKeysArray.get("key")).isEqualTo(List.of(expectedQueryField));
    }

    @JsonTest
    public void testSerializeTypedKeysInner() {
        Map<String, QueryParameter> typedKeys = Map.of("key", expectedQueryField);
        generator.writeStartObject();
        TRUE_FALSE_LIST.forEach(this::serializeTypedKeysInner);
        generator.writeEnd();
        assertThat(typedKeys).isNotNull();
    }


    @JsonTest
    void testSerializeTypedKeysArray() {
        TRUE_FALSE_LIST.forEach(this::serializeTypedKeysArray);
    }
    
    private void serializeTypedKeysArray(boolean b) {
        generator.writeStartArray();
        mapper.attribute(JsonpMapperFeatures.SERIALIZE_TYPED_KEYS, b);
        ExternallyTaggedUnion.serializeTypedKeysArray(typedKeysArray, generator, mapper);
        assertThat(typedKeysArray.get("key")).isEqualTo(List.of(expectedQueryField));
    }


    private void serializeTypedKeysInner(boolean b) {
        mapper.attribute(JsonpMapperFeatures.SERIALIZE_TYPED_KEYS, b);
        ExternallyTaggedUnion.serializeTypedKeysInner(typedKeys, generator, mapper);
        assertThat(typedKeys.get("key")).isEqualTo(expectedQueryField);
    }


    @JsonTest
    void testTypedKeyDeserializer() {
        
        Map<String, JsonpDeserializer<? extends QueryParameterVariant>> deserializers = new HashMap<>();
        
        ExternallyTaggedUnion.TypedKeysDeserializer<QueryParameter> typedKeysDeserializer =
                new ExternallyTaggedUnion.Deserializer<>(
                        deserializers, QueryParameter::new, QueryParameter::new
                ).typedKeys();

        assertThat(typedKeysDeserializer).isExactlyInstanceOf(ExternallyTaggedUnion.TypedKeysDeserializer.class);

    }
    
    @JsonTest
    void testArrayMapDeserializer() {

        Map<String, JsonpDeserializer<? extends QueryParameterVariant>> deserializers = new HashMap<>();

        ExternallyTaggedUnion.TypedKeysDeserializer<QueryParameter> typedKeysDeserializer
                = new ExternallyTaggedUnion.Deserializer<QueryParameter, QueryParameterVariant>(
                        deserializers, QueryParameter::new).typedKeys();

        JsonpDeserializer<Map<String, List<QueryParameter>>> arrayMapDeserializer =
                ExternallyTaggedUnion.arrayMapDeserializer(typedKeysDeserializer);

        assertThat(arrayMapDeserializer).isNotNull();

        Map<String, List<TermQueryParameter>> map = Map.of("typedKey", List.of(TERM_QUERY_PARAM));

        try(JsonParser parser = parser(map)) {
            JsonParsingException jsonParsingException = assertThrows(JsonParsingException.class,
                    () -> arrayMapDeserializer.deserialize(parser, mapper));

            assertThat(jsonParsingException.getMessage())
                    .isEqualTo("Error deserializing: " +
                            "jakarta.json.stream.JsonParsingException: Unexpected char 121 at (line no=1, " +
                            "column no=3, offset=2), " +
                            "expecting 'r'(line no=1, column no=4, offset=3)");

            assertThat(jsonParsingException.getLocation().getColumnNumber())
                    .isEqualTo(4L);

            assertThat(jsonParsingException.getLocation().getLineNumber())
                    .isEqualTo(1L);


            String cause = "" +
                    "org.eclipse.parsson.JsonTokenizer.expectedChar(JsonTokenizer.java:590), " +
                    "org.eclipse.parsson.JsonTokenizer.readTrue(JsonTokenizer.java:283), " +
                    "org.eclipse.parsson.JsonTokenizer.nextToken(JsonTokenizer.java:368), " +
                    "org.eclipse.parsson.JsonParserImpl$ObjectContext.getNextEvent(JsonParserImpl.java:465), " +
                    "org.eclipse.parsson.JsonParserImpl.next(JsonParserImpl.java:375), " +
                    "io.github.stewseo.clients.json.ExternallyTaggedUnion.lambda$arrayMapDeserializer$0(ExternallyTaggedUnion.java:31), " +
                    "io.github.stewseo.clients.json.JsonpDeserializer$3.deserialize(JsonpDeserializer.java:58), " +
                    "io.github.stewseo.clients.json.JsonpDeserializer.deserialize(JsonpDeserializer.java:188), " +
                    "io.github.stewseo.clients.json.ExternallyTaggedUnionTest.lambda$testArrayMapDeserializer$0(ExternallyTaggedUnionTest.java:113), " +
                    "org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:53), " +
                    "org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35), " +
                    "org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3082), " +
                    "io.github.stewseo.clients.json.ExternallyTaggedUnionTest.testArrayMapDeserializer(ExternallyTaggedUnionTest.java:112), " +
                    "java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method), " +
                    "java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77), " +
                    "java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43), " +
                    "java.base/java.lang.reflect.Method.invoke(Method.java:568), org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:727), org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60), " +
                    "org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131), " +
                    "org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:156), " +
                    "org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestableMethod(TimeoutExtension.java:147), " +
                    "org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestMethod(TimeoutExtension.java:86)";


            setMaxStackTraceElementsDisplayed(95);


            List<StackTraceElement> stackTrace = Arrays.stream(jsonParsingException.getCause().getStackTrace()).toList();

            assertThat(stackTrace.size()).isEqualTo(98);
        }

        assertThat(typedKeysDeserializer).isExactlyInstanceOf(ExternallyTaggedUnion.TypedKeysDeserializer.class);
        
    }

    private JsonParser parser(Map<String, List<TermQueryParameter>> map) {
        InputStream content = IOUtils.toInputStream(map.toString(), StandardCharsets.UTF_8);
        return mapper.jsonProvider().createParser(content);
    }

}