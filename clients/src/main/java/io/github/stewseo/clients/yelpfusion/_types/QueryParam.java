package io.github.stewseo.clients.yelpfusion._types;

import io.github.stewseo.clients.json.JsonEnum;

public enum QueryParam implements JsonEnum {
    LOCALE("en_US"),
    ALIAS("alias"),
    COUNTRY_WHITELIST("country_whitelist"),
    COUNTRY_BLACKLIST("country_blacklist"),
    TEXT("text"),
    ID("id"),
    NAME("name"),
    TIMESTAMP("timestamp"),
    META_FIELD_ID("_id"),
    PRICE("price")
    ;

    public static final Deserializer<QueryParam> _DESERIALIZER = new Deserializer<>(QueryParam.values());
    private final String jsonValue;
    QueryParam(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    public String jsonValue() {
        return this.jsonValue;
    }


}