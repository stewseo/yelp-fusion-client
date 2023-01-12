package io.github.stewseo.clients.json;

import io.github.stewseo.clients.json.JsonpMapper;
import jakarta.json.stream.JsonGenerator;

public interface JsonpSerializable {
    void serialize(JsonGenerator generator, JsonpMapper mapper);

}