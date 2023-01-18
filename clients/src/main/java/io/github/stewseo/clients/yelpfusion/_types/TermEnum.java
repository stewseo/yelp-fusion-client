package io.github.stewseo.clients.yelpfusion._types;

import io.github.stewseo.clients.json.JsonEnum;
import io.github.stewseo.clients.json.JsonpDeserializable;

@JsonpDeserializable
public enum TermEnum implements JsonEnum {

    Restaurants("restaurants"),
    Category("category"),
    MichelinStar("michelin+star")
    ;

    private final String jsonValue;

    TermEnum(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    public String jsonValue() {
        return this.jsonValue;
    }

    public static final Deserializer<TermEnum> _DESERIALIZER = new Deserializer<>(TermEnum.values());
}
