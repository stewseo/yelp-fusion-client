package io.github.stewseo.client._types;

import io.github.stewseo.client.json.JsonEnum;
import io.github.stewseo.client.json.JsonpDeserializable;

@JsonpDeserializable
public enum Bytes implements JsonEnum {
    Bytes("b"),

    KiloBytes("kb"),

    MegaBytes("mb"),

    GigaBytes("gb"),

    TeraBytes("tb"),

    PetaBytes("pb"),
    ;

    public static final Deserializer<Bytes> _DESERIALIZER = new Deserializer<>(io.github.stewseo.client._types.Bytes.values());
    private final String jsonValue;

    Bytes(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    public String jsonValue() {
        return this.jsonValue;
    }
}
