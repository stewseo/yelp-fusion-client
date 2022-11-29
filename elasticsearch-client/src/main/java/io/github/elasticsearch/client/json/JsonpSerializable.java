package io.github.elasticsearch.client.json;

import jakarta.json.stream.JsonGenerator;
public interface JsonpSerializable {
    void serialize(JsonGenerator generator, JsonpMapper mapper);

}