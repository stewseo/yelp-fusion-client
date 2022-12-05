package io.github.stewseo.yelp.fusion.client.json.jackson;

import io.github.stewseo.yelp.fusion.client.json.jackson.JacksonJsonProvider;
import jakarta.json.stream.JsonParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

public class JacksonJsonParserTest extends Assertions {


    private static final String json = "{ 'foo': 'fooValue', 'bar': { 'baz': 1}, 'quux': [true] }".replace('\'', '"');

    @Test
    public void testEventStream() {

        JacksonJsonProvider provider = new JacksonJsonProvider();

        JsonParser parser = provider.createParser(new StringReader(json));

        assertEquals(JsonParser.Event.START_OBJECT, parser.next());

        assertEquals(JsonParser.Event.KEY_NAME, parser.next());
        assertEquals("foo", parser.getString());

        assertEquals(JsonParser.Event.VALUE_STRING, parser.next());
        assertEquals("fooValue", parser.getString());

        // test it sometimes, but not always to detect invalid state management
        assertTrue(parser.hasNext());

        assertEquals(JsonParser.Event.KEY_NAME, parser.next());
        assertEquals("bar", parser.getString());

        assertEquals(JsonParser.Event.START_OBJECT, parser.next());

        assertEquals(JsonParser.Event.KEY_NAME, parser.next());
        assertEquals("baz", parser.getString());

        assertTrue(parser.hasNext());
        assertEquals(JsonParser.Event.VALUE_NUMBER, parser.next());
        assertEquals(1, parser.getInt());

        assertEquals(JsonParser.Event.END_OBJECT, parser.next());

        assertEquals(JsonParser.Event.KEY_NAME, parser.next());
        assertEquals("quux", parser.getString());

        assertEquals(JsonParser.Event.START_ARRAY, parser.next());

        assertEquals(JsonParser.Event.VALUE_TRUE, parser.next());

        assertEquals(JsonParser.Event.END_ARRAY, parser.next());
        assertEquals(JsonParser.Event.END_OBJECT, parser.next());

        assertFalse(parser.hasNext());
    }

    @Test
    public void testForbidValueGettersAfterHasNext() {

        JacksonJsonProvider provider = new JacksonJsonProvider();
        JsonParser parser = provider.createParser(new StringReader(json));

        assertEquals(JsonParser.Event.START_OBJECT, parser.next());
        assertEquals(JsonParser.Event.KEY_NAME, parser.next());
        assertEquals(JsonParser.Event.VALUE_STRING, parser.next());
        assertEquals("fooValue", parser.getString());

        assertTrue(parser.hasNext());

        try {
            assertEquals("fooValue", parser.getString());
            fail();
        } catch(IllegalStateException e) {
            // expected
        }
    }
}

