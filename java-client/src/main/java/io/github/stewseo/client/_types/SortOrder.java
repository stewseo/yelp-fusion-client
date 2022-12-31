package io.github.stewseo.client._types;

import io.github.stewseo.client.json.JsonEnum;
import io.github.stewseo.client.json.JsonpDeserializable;

/**
 * @see <a href="../doc-files/api-spec.html#_types.SortOrder">API
 * specification</a>
 */
@JsonpDeserializable
public enum SortOrder implements JsonEnum {
    Asc("asc"),

    Desc("desc"),

    ;

    public static final Deserializer<SortOrder> _DESERIALIZER = new Deserializer<>(
            SortOrder.values());
    private final String jsonValue;

    SortOrder(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    public String jsonValue() {
        return this.jsonValue;
    }
}
