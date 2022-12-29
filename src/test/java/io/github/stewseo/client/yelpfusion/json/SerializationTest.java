package io.github.stewseo.client.yelpfusion.json;

import io.github.stewseo.client.json.JsonpUtils;
import jakarta.json.Json;
import jakarta.json.JsonValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SerializationTest {
    @Test
    public void testJsonpValuesToString() {

        assertEquals("foo", JsonpUtils.toString(Json.createValue("foo")));
        assertEquals("42", JsonpUtils.toString(Json.createValue(42)));
        assertEquals("42.1337", JsonpUtils.toString(Json.createValue(42.1337)));
        assertEquals("true", JsonpUtils.toString(JsonValue.TRUE));
        assertEquals("false", JsonpUtils.toString(JsonValue.FALSE));
        assertEquals("null", JsonpUtils.toString(JsonValue.NULL));
        assertEquals("a,b,c", JsonpUtils.toString(Json.createArrayBuilder().add("a").add("b").add("c").build()));

        assertThrows(IllegalArgumentException.class, () -> {
            JsonpUtils.toString(Json.createObjectBuilder().build());
        });
    }
}


