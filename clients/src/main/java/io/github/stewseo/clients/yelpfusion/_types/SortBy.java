package io.github.stewseo.clients.yelpfusion._types;

import io.github.stewseo.clients.json.JsonEnum;
import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpMapper;
import jakarta.json.stream.JsonGenerator;

@JsonpDeserializable
public enum SortBy implements JsonEnum {

    REVIEW_COUNT("review_count"),

    DISTANCE("distance")
    ;

    public static final Deserializer<SortBy> _DESERIALIZER = new Deserializer<>(SortBy.values());
    private final String jsonValue;

    SortBy(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    public String jsonValue() {
        return this.jsonValue;
    }
}
