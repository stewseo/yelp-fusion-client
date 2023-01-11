package io.github.stewseo.clients.yelpfusion._types;

import io.github.stewseo.clients.json.JsonEnum;
import io.github.stewseo.clients.json.JsonpDeserializable;

@JsonpDeserializable
public enum MappingProperties implements JsonEnum {

    ALIAS("categories.alias.keyword"),

    TITLE("categories.title.keyword"),

    PARENTS("categories.parents.keyword");

    public static final Deserializer<MappingProperties> _DESERIALIZER = new Deserializer<>(values());
    private final String type;

    MappingProperties(String value) {
        this.type = value;
    }

    @Override
    public String jsonValue() {
        return type;
    }

}