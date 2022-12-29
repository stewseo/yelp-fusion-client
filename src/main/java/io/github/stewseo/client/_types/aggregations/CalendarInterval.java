package io.github.stewseo.client._types.aggregations;


import io.github.stewseo.client.json.JsonEnum;
import io.github.stewseo.client.json.JsonpDeserializable;

@JsonpDeserializable
public enum CalendarInterval implements JsonEnum {
    Second("second", "1s"),

    Minute("minute", "1m"),

    Hour("hour", "1h"),

    Day("day", "1d"),

    Week("week", "1w"),

    Month("month", "1M"),

    Quarter("quarter", "1q"),

    Year("year", "1Y"),

    ;

    private final String jsonValue;
    private final String[] aliases;

    CalendarInterval(String jsonValue, String... aliases) {
        this.jsonValue = jsonValue;
        this.aliases = aliases;
    }

    public String jsonValue() {
        return this.jsonValue;
    }

    public String[] aliases() {
        return this.aliases;
    }

    public static final JsonEnum.Deserializer<CalendarInterval> _DESERIALIZER = new JsonEnum.Deserializer<>(CalendarInterval.values());
}

