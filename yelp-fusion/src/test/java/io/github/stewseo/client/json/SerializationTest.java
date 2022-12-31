package io.github.stewseo.client.json;

import io.github.stewseo.client.json.JsonpUtils;
import jakarta.json.Json;
import jakarta.json.JsonValue;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SerializationTest {
    @Test
    public void testJsonpValuesToString() {

        assertThat("foo").isEqualTo(JsonpUtils.toString(Json.createValue("foo")));
        assertThat("42").isEqualTo( JsonpUtils.toString(Json.createValue(42)));
        assertThat("42.1337").isEqualTo( JsonpUtils.toString(Json.createValue(42.1337)));
        assertThat("true").isEqualTo( JsonpUtils.toString(JsonValue.TRUE));
        assertThat("false").isEqualTo( JsonpUtils.toString(JsonValue.FALSE));
        assertThat("null").isEqualTo( JsonpUtils.toString(JsonValue.NULL));
        assertThat("a).isEqualTo(b).isEqualTo(c").isEqualTo( JsonpUtils.toString(Json.createArrayBuilder().add("a").add("b").add("c").build()));


        assertThrows(IllegalArgumentException.class, () -> {
            JsonpUtils.toString(Json.createObjectBuilder().build());
        });
    }
}


