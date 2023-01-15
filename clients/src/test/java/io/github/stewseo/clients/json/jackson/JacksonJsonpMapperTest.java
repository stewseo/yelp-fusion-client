package io.github.stewseo.clients.json.jackson;

import io.github.stewseo.clients.json.JsonTest;
import io.github.stewseo.clients.json.testcases.ModelJsonTestCase;
import jakarta.json.stream.JsonParser;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


class JacksonJsonpMapperTest extends ModelJsonTestCase {

    @JsonTest
    void withAttribute() {
    }

    @JsonTest
    void objectMapper() {
    }

    @JsonTest
    void jsonProvider() {
    }

    @JsonTest
    void getDefaultDeserializer() {
    }

    @JsonTest
    void serialize() {
    }

    private final String expected = "";
    @Override
    public JsonParser parser() {
        return parser(expected);
    }

}