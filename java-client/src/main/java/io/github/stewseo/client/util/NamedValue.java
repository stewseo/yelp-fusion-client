package io.github.stewseo.client.util;

import io.github.stewseo.client.json.JsonpDeserializer;
import io.github.stewseo.client.json.JsonpDeserializerBase;
import io.github.stewseo.client.json.JsonpMapper;
import io.github.stewseo.client.json.JsonpMappingException;
import io.github.stewseo.client.json.JsonpUtils;

import jakarta.json.stream.JsonParser;

import java.util.EnumSet;
import java.util.function.Supplier;

public class NamedValue<T> {

    private final String name;
    private final T value;

    public NamedValue(String name, T value) {
        this.name = name;
        this.value = value;
    }

    public static <T> NamedValue<T> of(String name, T value) {
        return new NamedValue<>(name, value);
    }

    public static <T> JsonpDeserializer<NamedValue<T>> deserializer(Supplier<JsonpDeserializer<T>> valueParserBuilder) {
        return deserializer(valueParserBuilder.get());
    }

    public static <T> JsonpDeserializer<NamedValue<T>> deserializer(JsonpDeserializer<T> valueDeserializer) {
        return new JsonpDeserializerBase<NamedValue<T>>(EnumSet.of(JsonParser.Event.START_OBJECT)) {
            @Override
            public NamedValue<T> deserialize(JsonParser parser, JsonpMapper mapper, JsonParser.Event event) {

                JsonpUtils.expectNextEvent(parser, JsonParser.Event.KEY_NAME);
                String name = parser.getString();

                try {
                    T value = valueDeserializer.deserialize(parser, mapper);
                    JsonpUtils.expectNextEvent(parser, JsonParser.Event.END_OBJECT);

                    return new NamedValue<>(name, value);
                } catch (Exception e) {
                    throw JsonpMappingException.from(e, null, name, parser);
                }
            }
        };
    }

    public String name() {
        return this.name;
    }

    public T value() {
        return this.value;
    }
}