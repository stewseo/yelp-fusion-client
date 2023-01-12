package io.github.stewseo.clients._types;

import io.github.stewseo.clients.json.JsonEnum;
import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpMapper;
import jakarta.json.stream.JsonGenerator;

@JsonpDeserializable
public enum Field implements JsonEnum {
    ;

    @Override
    public String jsonValue() {
        return null;
    }

    @Override
    public String[] aliases() {
        return JsonEnum.super.aliases();
    }

    @Override
    public void serialize(JsonGenerator generator, JsonpMapper params) {
        JsonEnum.super.serialize(generator, params);
    }
}