package io.github.stewseo.clients._type;

import io.github.stewseo.clients.json.JsonEnum;
import io.github.stewseo.clients.json.JsonpDeserializable;

@JsonpDeserializable
public enum Term implements JsonEnum {

    MICHELIN_STAR("michelin+star")
    ;

    private final String jsonValue;

    Term(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    public String jsonValue() {
        return this.jsonValue;
    }

    public static final Deserializer<SomeEnum> _DESERIALIZER = new Deserializer<>(SomeEnum.values());
}
