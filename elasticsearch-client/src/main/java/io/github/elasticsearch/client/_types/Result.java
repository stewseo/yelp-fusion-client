package io.github.elasticsearch.client._types;


import io.github.elasticsearch.client.json.JsonEnum;
import io.github.elasticsearch.client.json.JsonpDeserializable;

@JsonpDeserializable
public enum Result implements JsonEnum {
    Created("created"),

    Updated("updated"),

    Deleted("deleted"),

    NotFound("not_found"),

    NoOp("noop"),
    ;

    private final String jsonValue;

    Result(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    public String jsonValue() {
        return this.jsonValue;
    }

    public static final JsonEnum.Deserializer<Result> _DESERIALIZER = new JsonEnum.Deserializer<>(Result.values());
}
