package io.github.stewseo.clients._types;


import io.github.stewseo.clients.json.JsonEnum;
import io.github.stewseo.clients.json.JsonpDeserializable;

@JsonpDeserializable
public enum SomeEnum implements JsonEnum {
    NotEq("not_eq"),

    Eq("eq"),

    Lt("lt"),

    Gt("gt"),

    Lte("lte"),

    Gte("gte"),

    ;

    private final String jsonValue;

    SomeEnum(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    public String jsonValue() {
        return this.jsonValue;
    }

    public static final JsonEnum.Deserializer<SomeEnum> _DESERIALIZER = new JsonEnum.Deserializer<>(SomeEnum.values());
}
