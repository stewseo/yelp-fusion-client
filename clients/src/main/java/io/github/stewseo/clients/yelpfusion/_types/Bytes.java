package io.github.stewseo.clients.yelpfusion._types;

import io.github.stewseo.clients.json.JsonEnum;
import io.github.stewseo.clients.json.JsonpDeserializable;

@JsonpDeserializable
public enum Bytes implements JsonEnum {
    Bytes("b"),

    KiloBytes("kb"),

    MegaBytes("mb"),

    GigaBytes("gb"),

    TeraBytes("tb"),

    PetaBytes("pb"),
    ;

    public static final Deserializer<Bytes> _DESERIALIZER = new Deserializer<>(io.github.stewseo.clients.yelpfusion._types.Bytes.values());
    private final String jsonValue;

    Bytes(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    public String jsonValue() {
        return this.jsonValue;
    }
}
