package io.github.stewseo.clients.json;

import io.github.stewseo.clients.json.JsonpMapper;
import jakarta.json.stream.JsonGenerator;

public interface JsonpSerializer<T> {
    void serialize(T value, JsonGenerator generator, JsonpMapper mapper);
}