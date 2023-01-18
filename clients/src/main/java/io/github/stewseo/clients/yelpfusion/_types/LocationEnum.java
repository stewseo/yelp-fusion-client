package io.github.stewseo.clients.yelpfusion._types;

import io.github.stewseo.clients.json.JsonEnum;
import io.github.stewseo.clients.json.JsonpDeserializable;

@JsonpDeserializable
public enum LocationEnum implements JsonEnum {

    Address("address"),
    City("city"),
    State("state"),
    Country("country"),
    Zipcode("zipcode"),
    PostalCode("postal_code")
    ;

    private final String jsonValue;

    LocationEnum(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    public String jsonValue() {
        return this.jsonValue;
    }

    public static final Deserializer<LocationEnum> _DESERIALIZER = new Deserializer<>(LocationEnum.values());
}
