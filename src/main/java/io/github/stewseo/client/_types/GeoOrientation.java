package io.github.stewseo.client._types;


import io.github.stewseo.client.json.JsonEnum;
import io.github.stewseo.client.json.JsonpDeserializable;

@JsonpDeserializable
public enum GeoOrientation implements JsonEnum {
    Right("right", "RIGHT", "counterclockwise", "ccw"),

    Left("left", "LEFT", "clockwise", "cw"),
    ;

    private final String jsonValue;
    private final String[] aliases;

    GeoOrientation(String jsonValue, String... aliases) {
        this.jsonValue = jsonValue;
        this.aliases = aliases;
    }

    public String jsonValue() {
        return this.jsonValue;
    }

    public String[] aliases() {
        return this.aliases;
    }

    public static final JsonEnum.Deserializer<GeoOrientation> _DESERIALIZER = new JsonEnum.Deserializer<>(
            GeoOrientation.values());
}