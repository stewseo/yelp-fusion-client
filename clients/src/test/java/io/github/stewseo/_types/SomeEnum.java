package io.github.stewseo._types;


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

    public static final Deserializer<SomeEnum> _DESERIALIZER = new Deserializer<>(SomeEnum.values());
}
