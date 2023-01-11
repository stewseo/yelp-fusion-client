package io.github.stewseo.json.jackson;

import io.github.stewseo.clients.json.jackson.JacksonJsonpMapper;
import jakarta.json.Json;
import jakarta.json.JsonValue;
import jakarta.json.stream.JsonGenerationException;
import jakarta.json.stream.JsonGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;

import static org.assertj.core.api.Assertions.assertThat;


public class JacksonJsonpGeneratorTest extends Assertions {

    StringWriter sw = new StringWriter();

    JsonGenerator generator = new JacksonJsonpMapper().jsonProvider().createGenerator(sw);

    @Test
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

    @Test
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

    @Test
    public void testWriteStartObjectThrows(){

        StringWriter sw = new StringWriter();
        JsonGenerator generator = new JacksonJsonpMapper().jsonProvider().createGenerator(sw);

        assertThrows(JsonGenerationException.class,
                ()-> generator.writeStartObject("a"));

        generator.close();
    }

    @Test
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

    @Test
    public void testWriteWithName(){

        generator.writeStartObject();
        generator.writeStartObject("name");
        generator.write("long1", 123456789012345L);

        generator.writeEnd().close();

        assertThat(sw.toString()).isEqualTo("{\"name\":{\"long1\":123456789012345}}");

    }

    @Test
    public void testWriteStartArray(){
        generator.writeStartArray();
        generator.writeStartObject();
        generator.write("long1", 123456789012345L);

        generator.writeEnd().close();

        assertThat(sw.toString()).isEqualTo("[{\"long1\":123456789012345}]");
    }

    @Test
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

    @Test
    public void testWriteStartArrayWithName(){
        generator.writeStartArray();
        generator.writeStartObject();
        generator.writeStartArray("name");

        generator.writeStartObject();
        generator.write("long1", 123456789012345L);

        generator.writeEnd().close();

        assertThat(sw.toString()).isEqualTo("[{\"name\":[{\"long1\":123456789012345}]}]");
    }
}