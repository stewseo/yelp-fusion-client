package io.github.stewseo.json;

import io.github.stewseo.clients._types.Property;
import io.github.stewseo.clients.json.DeserializeFromJson;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.UnionDeserializer;
import jakarta.json.stream.JsonParser;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.assertj.core.api.Assertions.assertThat;

class UnionDeserializerTest implements DeserializeFromJson {

    private final JsonpDeserializer<Property> propertyJsonpDeserializer = new UnionDeserializer.Builder<Property, Property.Kind, Object>(Property::new, false)
            .addMember(Property.Kind.Binary, JsonpDeserializer.stringDeserializer()).build();

    @Test
    void testNativeEvents() {
        assertThat(propertyJsonpDeserializer.nativeEvents().toString()).isEqualTo("[VALUE_STRING]");
    }

    @Override
    public JsonParser parser() {
        return null;
    }
    @Test
    public void testDeserializer() {
        assertThat(propertyJsonpDeserializer.acceptedEvents().toString()).isEqualTo("[VALUE_STRING]");

    }

    @Test
    public void testDeserialize() {


        JsonParser parser = mapper.jsonProvider().createParser(new StringReader("\"field\":\"value\""));

        Property property = propertyJsonpDeserializer.deserialize(parser, mapper);

        assertThat(property._get()).isEqualTo("field");
        assertThat(property._kind().toString()).isEqualTo("_Custom");
    }

    @Test
    void testDeserializeEvent() {

        JsonParser parser = mapper.jsonProvider().createParser(new StringReader("\"field\":\"value\""));
        JsonParser.Event event = parser.next();
        Property property = propertyJsonpDeserializer.deserialize(parser, mapper, event);
        assertThat(property._get()).isEqualTo("field");
        assertThat(property._kind().toString()).isEqualTo("_Custom");
    }
}