package io.github.stewseo.clients.json;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class SimpleJsonpMapperTest {

    @Test
    void getDefaultDeserializer() {
        JsonpMapper mapper = new SimpleJsonpMapper();
        assertThat(mapper).isExactlyInstanceOf(io.github.stewseo.clients.json.SimpleJsonpMapper.class);
    }

    @Test
    void getDefaultSerializer() {
        JsonpMapper mapper = SimpleJsonpMapper.INSTANCE;
        assertThat(mapper).isExactlyInstanceOf(SimpleJsonpMapper.class);
    }

    @Test
    void testWithAttribute() {
        JsonpSerializer<?> jsonpSerializer = JsonpMapperBase.JsonpSerializableSerializer.INSTANCE;
        assertThat(jsonpSerializer).isExactlyInstanceOf(io.github.stewseo.clients.json.JsonpMapperBase.JsonpSerializableSerializer.class);
    }

    @Test
    void testIgnoreUnknownFields() {
        JsonpSerializer<?> jsonpSerializer = JsonpMapperBase.JsonpValueSerializer.INSTANCE;
        assertThat(jsonpSerializer).isExactlyInstanceOf(io.github.stewseo.clients.json.JsonpMapperBase.JsonpValueSerializer.class);
    }

    @Test
    void testJsonProvider() {
    }

    @Test
    void testSerialize() {
    }

    @Test
    void testGetDefaultDeserializer() {
    }

    @Test
    void testGetDefaultSerializer() {
    }
}