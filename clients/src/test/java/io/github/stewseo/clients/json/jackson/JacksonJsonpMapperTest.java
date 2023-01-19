package io.github.stewseo.clients.json.jackson;

import io.github.stewseo.clients.json.JsonTest;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.testcases.AbstractJsonTestCase;
import jakarta.json.stream.JsonParser;

import static org.assertj.core.api.Assertions.assertThat;


class JacksonJsonpMapperTest extends AbstractJsonTestCase {

    @JsonTest
    void withAttribute() {
        JsonpMapper jacksonJsonpMapper = new JacksonJsonpMapper().withAttribute("att-name", "att-value");
        assertThat(jacksonJsonpMapper).isExactlyInstanceOf(JacksonJsonpMapper.class);
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