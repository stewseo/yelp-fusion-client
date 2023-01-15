package io.github.stewseo.clients.json;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class SimpleJsonpMapperTest {

    @JsonTest
    void getDefaultDeserializer() {
        JsonpMapper mapper = new SimpleJsonpMapper();
        assertThat(mapper).isExactlyInstanceOf(io.github.stewseo.clients.json.SimpleJsonpMapper.class);
    }

    @JsonTest
    void getDefaultSerializer() {
        JsonpMapper mapper = SimpleJsonpMapper.INSTANCE;
        assertThat(mapper).isExactlyInstanceOf(SimpleJsonpMapper.class);
    }

    @JsonTest
    void testWithAttribute() {
        JsonpSerializer<?> jsonpSerializer = JsonpMapperBase.JsonpSerializableSerializer.INSTANCE;
        assertThat(jsonpSerializer).isExactlyInstanceOf(io.github.stewseo.clients.json.JsonpMapperBase.JsonpSerializableSerializer.class);
    }

    @JsonTest
    void testIgnoreUnknownFields() {
        JsonpSerializer<?> jsonpSerializer = JsonpMapperBase.JsonpValueSerializer.INSTANCE;
        assertThat(jsonpSerializer).isExactlyInstanceOf(io.github.stewseo.clients.json.JsonpMapperBase.JsonpValueSerializer.class);
    }

    @JsonTest
    void testJsonProvider() {
    }

    @JsonTest
    void testSerialize() {
    }

    @JsonTest
    void testGetDefaultDeserializer() {
    }

    @JsonTest
    void testGetDefaultSerializer() {
    }
}