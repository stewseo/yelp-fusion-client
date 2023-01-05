package io.github.stewseo.clients.yelpfusion._types;

import io.github.stewseo.clients.json.JsonEnum;
import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpMapper;
import jakarta.json.stream.JsonGenerator;

@JsonpDeserializable
public enum Refresh implements JsonEnum {
    True("true"),

    False("false"),

    WaitFor("wait_for"),
    ;

    private final String jsonValue;

    Refresh(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    public String jsonValue() {
        return this.jsonValue;
    }

    @Override
    public void serialize(JsonGenerator generator, JsonpMapper params) {

        if (this == Refresh.True) {
            generator.write(true);
        } else if (this == Refresh.False) {
            generator.write(false);
        } else {
            generator.write(jsonValue());
        }
    }

    public static final Deserializer<Refresh> _DESERIALIZER = new Deserializer.AllowingBooleans<>(
            Refresh.values());
}