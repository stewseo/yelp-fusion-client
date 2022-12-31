package io.github.stewseo.client.json.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import io.github.stewseo.client.json.JsonpMapper;
import io.github.stewseo.client.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.client.json.jsonb.JsonbJsonpMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JsonpMapperTest extends Assertions {

    final String json = "{\"children\":[{\"doubleValue\":3.2,\"intValue\":2}],\"doubleValue\":2.1,\"intValue\":1," +
            "\"stringValue\":\"foo\"}";

    @Test
    public void testJsonb() {
        JsonpMapper mapper = new JsonbJsonpMapper();
        testSerialize(mapper, json);
        testDeserialize(mapper, json);
    }

    @Test
    public void testJackson() {
        JacksonJsonpMapper mapper = new JacksonJsonpMapper();
        testSerialize(new JacksonJsonpMapper(), json);
        testDeserialize(new JacksonJsonpMapper(), json);
    }

    @Test
    public void testJacksonCustomMapper() {
        ObjectMapper jkMapper = new ObjectMapper();
        jkMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        jkMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        JacksonJsonpMapper mapper = new JacksonJsonpMapper(jkMapper);

        String json = "{\"children\":[{\"double_value\":3.2,\"int_value\":2}],\"double_value\":2.1,\"int_value\":1," +
                "\"string_value\":\"foo\"}";

        testSerialize(mapper, json);
        testDeserialize(mapper, json);
    }

    @Test
    public void testDeserializeWithType() {

        String json = "{\"foo\":{\"int_value\":1,\"double_value\":1.0},\"bar\":{\"int_value\":2,\"double_value\":2.0}}";

        ObjectMapper jkMapper = new ObjectMapper();
        jkMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        JacksonJsonpMapper mapper = new JacksonJsonpMapper(jkMapper);

        // With type erasure, map values are raw json nodes
        {
            JsonParser parser = mapper.jsonProvider().createParser(new StringReader(json));
            Map<String, SomeClass> map = new HashMap<>();
            map = mapper.deserialize(parser, (Type) map.getClass());

            Map<String, SomeClass> finalMap = map;
            assertThrows(ClassCastException.class, () -> assertEquals(1, finalMap.get("foo").intValue));
        }

        // Use a j.l.reflect.Type to deserialize map values correctly
        {
            TypeReference<Map<String, SomeClass>> typeRef = new TypeReference<>() {
            };

            JsonParser parser = mapper.jsonProvider().createParser(new StringReader(json));
            Map<String, SomeClass> map = mapper.deserialize(parser, typeRef.getType());

            System.out.println(map);
            assertEquals(1, map.get("foo").intValue);
            assertEquals(2, map.get("bar").intValue);
        }
    }

    private void testSerialize(JsonpMapper mapper, String expected) {

        SomeClass something = new SomeClass();
        something.setIntValue(1);
        something.setDoubleValue(2.1);
        something.setStringValue("foo");

        SomeClass other = new SomeClass();
        other.setIntValue(2);
        other.setDoubleValue(3.2);
        something.setChildren(Collections.singletonList(other));

        StringWriter strw = new StringWriter();
        JsonGenerator generator = mapper.jsonProvider().createGenerator(strw);

        mapper.serialize(something, generator);

        generator.close();

        assertEquals(expected, strw.getBuffer().toString());
    }

    private void testDeserialize(JsonpMapper mapper, InputStream json) {

        JsonParser parser = mapper.jsonProvider().createParser(json);
        SomeClass parsed = mapper.deserialize(parser, SomeClass.class);

        assertEquals(1, parsed.getIntValue());
        assertEquals(2.1, parsed.getDoubleValue(), 0.0);
        assertEquals("foo", parsed.getStringValue());

        List<SomeClass> children = parsed.getChildren();
        assertEquals(1, children.size());

        SomeClass child = children.get(0);

        assertEquals(2, child.getIntValue());
        assertEquals(3.2, child.getDoubleValue(), 0.0);
        assertNull(child.getStringValue());
        assertNull(child.getChildren());
    }
    private void testDeserialize(JsonpMapper mapper, String json) {

        JsonParser parser = mapper.jsonProvider().createParser(new StringReader(json));
        SomeClass parsed = mapper.deserialize(parser, SomeClass.class);

        assertEquals(1, parsed.getIntValue());
        assertEquals(2.1, parsed.getDoubleValue(), 0.0);
        assertEquals("foo", parsed.getStringValue());

        List<SomeClass> children = parsed.getChildren();
        assertEquals(1, children.size());

        SomeClass child = children.get(0);

        assertEquals(2, child.getIntValue());
        assertEquals(3.2, child.getDoubleValue(), 0.0);
        assertNull(child.getStringValue());
        assertNull(child.getChildren());
    }

    public static class SomeClass {
        private List<SomeClass> children;
        private double doubleValue;
        private int intValue;
        private String stringValue;

        public int getIntValue() {
            return intValue;
        }

        public void setIntValue(int intValue) {
            this.intValue = intValue;
        }

        public String getStringValue() {
            return stringValue;
        }

        public void setStringValue(String stringValue) {
            this.stringValue = stringValue;
        }

        public double getDoubleValue() {
            return doubleValue;
        }

        public void setDoubleValue(double doubleValue) {
            this.doubleValue = doubleValue;
        }

        public List<SomeClass> getChildren() {
            return children;
        }

        public void setChildren(List<SomeClass> children) {
            this.children = children;
        }
    }
}