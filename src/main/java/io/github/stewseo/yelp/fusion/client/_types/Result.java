package io.github.stewseo.yelp.fusion.client._types;


import io.github.stewseo.yelp.fusion.client.json.JsonEnum;
import io.github.stewseo.yelp.fusion.client.json.JsonpDeserializable;

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

    public static final Deserializer<Result> _DESERIALIZER = new Deserializer<>(Result.values());
}
