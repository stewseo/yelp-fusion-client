package org.example.elasticsearch.client.elasticsearch.core;


import org.example.elasticsearch.client.json.*;

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
