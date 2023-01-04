package io.github.stewseo.clients.yelpfusion._types;


import io.github.stewseo.clients.json.JsonEnum;
import io.github.stewseo.clients.json.JsonpDeserializable;

@JsonpDeserializable
public enum GeoOrientation implements JsonEnum {
    Right("right", "RIGHT", "counterclockwise", "ccw"),

    Left("left", "LEFT", "clockwise", "cw"),
    ;

    public static final Deserializer<GeoOrientation> _DESERIALIZER = new Deserializer<>(
            GeoOrientation.values());
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
}