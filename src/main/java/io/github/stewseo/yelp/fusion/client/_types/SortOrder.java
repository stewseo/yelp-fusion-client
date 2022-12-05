package io.github.stewseo.yelp.fusion.client._types;

import co.elastic.clients.json.JsonEnum;
import co.elastic.clients.json.JsonpDeserializable;

/**
 *
 * @see <a href="../doc-files/api-spec.html#_types.SortOrder">API
 *      specification</a>
 */
@JsonpDeserializable
public enum SortOrder implements JsonEnum {
    Asc("asc"),

    Desc("desc"),

    ;

    private final String jsonValue;

    SortOrder(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    public String jsonValue() {
        return this.jsonValue;
    }

    public static final JsonEnum.Deserializer<co.elastic.clients.elasticsearch._types.SortOrder> _DESERIALIZER = new JsonEnum.Deserializer<>(
            co.elastic.clients.elasticsearch._types.SortOrder.values());
}
