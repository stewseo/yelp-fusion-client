package io.github.elasticsearch.client.elasticsearch.core;


import io.github.elasticsearch.client.json.JsonEnum;
import io.github.elasticsearch.client.json.JsonpDeserializable;

@JsonpDeserializable
public enum TotalHitsRelation implements JsonEnum {

    Eq("eq"),

    Gte("gte"),
    ;

    private final String jsonValue;

    TotalHitsRelation(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    public String jsonValue() {
        return this.jsonValue;
    }

    public static final JsonEnum.Deserializer<TotalHitsRelation> _DESERIALIZER = new JsonEnum.Deserializer<>(
            TotalHitsRelation.values());
}
