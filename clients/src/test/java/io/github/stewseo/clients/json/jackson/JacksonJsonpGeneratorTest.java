package io.github.stewseo.clients.json.jackson;

import io.github.stewseo.clients.json.JsonTest;
import io.github.stewseo.clients.json.testcases.AbstractJsonTestCase;
import jakarta.json.Json;
import jakarta.json.JsonValue;
import jakarta.json.stream.JsonGenerationException;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class JacksonJsonpGeneratorTest extends AbstractJsonTestCase {

    StringWriter sw = new StringWriter();

    JsonGenerator generator = new JacksonJsonpMapper().jsonProvider().createGenerator(sw);

    @JsonTest
    public void testWrite(){

        StringWriter sw = new StringWriter();
        JsonGenerator generator = new JacksonJsonpMapper().jsonProvider().createGenerator(sw);

        generator.writeStartObject();
        // Boolean
        generator.write("bool1", true);
        generator.writeKey("bool2");
        generator.write(false);

        // String
        generator.write("str1", "foo");
        generator.writeKey("str2");
        generator.write("bar");

        // Integer
        generator.write("int1", 42);
        generator.writeKey("int2");
        generator.write(1337);

        // Long
        generator.write("long1", 123456789012345L);
        generator.writeKey("long2");
        generator.write(123456789012345L);

        generator.write("double1", 0.001);
        generator.writeKey("double2");
        generator.write(12345.6789);

        // JsonValue
        JsonValue jsonValue = Json.createObjectBuilder()
                .add("bool", true)
                .add("str", "foo")
                .add("int", 42)
                .add("long", 123456789012345L)
                .add("double", 12345.6789)
                .build();

        generator.write("value", jsonValue);

        generator.close();

        assertEquals("{" +
                "\"bool1\":true," +
                "\"bool2\":false," +
                "\"str1\":\"foo\"," +
                "\"str2\":\"bar\"," +
                "\"int1\":42," +
                "\"int2\":1337," +
                "\"long1\":123456789012345," +
                "\"long2\":123456789012345," +
                "\"double1\":0.001," +
                "\"double2\":12345.6789," +
                "\"value\":{\"bool\":true,\"str\":\"foo\",\"int\":42,\"long\":123456789012345,\"double\":12345.6789}" +
                "}", sw.toString());
    }

    @JsonTest
    public void testWriteThrows(){

        StringWriter sw = new StringWriter();
        JsonGenerator generator = new JacksonJsonpMapper().jsonProvider().createGenerator(sw);

        assertThrows(JsonGenerationException.class,
                ()-> generator.write("str", "t"));

        assertThrows(JsonGenerationException.class,
                ()-> generator.write("bool",false));

        assertThrows(JsonGenerationException.class,
                ()-> generator.write("int", 1));

        assertThrows(JsonGenerationException.class,
                ()-> generator.write("long", 1L));

        assertThrows(JsonGenerationException.class,
                ()-> generator.write("double", 1.0));


        // JsonValue
        JsonValue jsonValue = Json.createObjectBuilder()
                .add("bool", true)
                .add("str", "foo")
                .add("int", 42)
                .add("long", 123456789012345L)
                .add("double", 12345.6789)
                .build();

        assertThrows(JsonGenerationException.class,
                ()-> generator.write("value", jsonValue));

        generator.close();

    }

    @JsonTest
    public void testWriteStartObjectThrows(){

        StringWriter sw = new StringWriter();
        JsonGenerator generator = new JacksonJsonpMapper().jsonProvider().createGenerator(sw);

        assertThrows(JsonGenerationException.class,
                ()-> generator.writeStartObject("a"));

        generator.close();
    }

    @JsonTest
    public void testWriteStartObjectThrowsWithName(){

        StringWriter sw = new StringWriter();
        JsonGenerator generator = new JacksonJsonpMapper().jsonProvider().createGenerator(sw);

        assertThrows(JsonGenerationException.class,
                ()->{
                    generator.writeStartObject();
                    generator.writeStartObject();
                });

        generator.close();
    }

    @JsonTest
    public void testWriteWithName(){

        generator.writeStartObject();
        generator.writeStartObject("name");
        generator.write("long1", 123456789012345L);

        generator.writeEnd().close();

        assertThat(sw.toString()).isEqualTo("{\"name\":{\"long1\":123456789012345}}");

    }

    @JsonTest
    public void testWriteStartArray(){
        generator.writeStartArray();
        generator.writeStartObject();
        generator.write("long1", 123456789012345L);

        generator.writeEnd().close();

        assertThat(sw.toString()).isEqualTo("[{\"long1\":123456789012345}]");
    }

    @JsonTest
    public void testWriteStartArrayThrows(){

        StringWriter sw = new StringWriter();
        JsonGenerator generator = new JacksonJsonpMapper().jsonProvider().createGenerator(sw);

        assertThrows(JsonGenerationException.class,
                ()-> generator.writeStartArray("a"));

        assertThrows(JsonGenerationException.class,
                ()->{
                    generator.writeStartObject();
                    generator.writeStartArray();
                });


        generator.close();
    }

    @JsonTest
    public void testWriteStartArrayWithName(){
        generator.writeStartArray();
        generator.writeStartObject();
        generator.writeStartArray("name");

        generator.writeStartObject();
        generator.write("long1", 123456789012345L);

        generator.writeEnd().close();

        assertThat(sw.toString()).isEqualTo("[{\"name\":[{\"long1\":123456789012345}]}]");
    }

    private String expected = "";

    @Override
    public JsonParser parser() {
        return parser(expected);
    }

    @Test
    void jacksonGenerator() {
    }

    @Test
    void writeStartObject() {
    }

    @Test
    void testWriteStartObject() {
    }

    @Test
    void writeStartArray() {
    }

    @Test
    void testWriteStartArray1() {
    }

    @Test
    void writeKey() {
    }

    @Test
    void write() {
    }

    @Test
    void testWrite1() {
    }

    @Test
    void testWrite2() {
    }

    @Test
    void testWrite3() {
    }

    @Test
    void testWrite4() {
    }

    @Test
    void testWrite5() {
    }

    @Test
    void testWrite6() {
    }

    @Test
    void testWrite7() {
    }

    @Test
    void writeNull() {
    }

    @Test
    void writeEnd() {
    }

    @Test
    void testWrite8() {
    }

    @Test
    void testWrite9() {
    }

    @Test
    void testWrite10() {
    }

    @Test
    void testWrite11() {
    }

    @Test
    void testWrite12() {
    }

    @Test
    void testWrite13() {
    }

    @Test
    void testWrite14() {
    }

    @Test
    void testWrite15() {
    }

    @Test
    void testWriteNull() {
    }
}