package io.github.stewseo.client.json;

import io.github.stewseo.client.util.TriFunction;
import jakarta.json.JsonValue;
import jakarta.json.stream.JsonParser;
import jakarta.json.stream.JsonParser.Event;

import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public interface JsonpDeserializer<V> {
    EnumSet<Event> nativeEvents();

    EnumSet<Event> acceptedEvents();


    default boolean accepts(Event event) {
        return acceptedEvents().contains(event);
    }


    default V deserialize(JsonParser parser, JsonpMapper mapper) {
        Event event = parser.next();
        // JSON null: return null unless the deserializer can handle it
        if (event == Event.VALUE_NULL && !accepts(Event.VALUE_NULL)) {
            return null;
        }

        JsonpUtils.ensureAccepts(this, parser, event);
        return deserialize(parser, mapper, event);
    }


    V deserialize(JsonParser parser, JsonpMapper mapper, Event event);

    //---------------------------------------------------------------------------------------------
    /**
     * Creates a deserializer for a type that delegates to the mapper provided to
     * {@link #deserialize(JsonParser, JsonpMapper)}.
     */
    static <T>JsonpDeserializer<T> of(Type type) {
        return new JsonpDeserializerBase<>(EnumSet.allOf(Event.class)) {
            @Override
            public T deserialize(JsonParser parser, JsonpMapper mapper) {
                return mapper.deserialize(parser, type);
            }

            @Override
            public T deserialize(JsonParser parser, JsonpMapper mapper, Event event) {
                throw new UnsupportedOperationException();
            }
        };
    }

    static <T> JsonpDeserializer<T> of(EnumSet<Event> acceptedEvents, BiFunction<JsonParser, JsonpMapper, T> fn) {
        return new JsonpDeserializerBase<>(acceptedEvents) {
            @Override
            public T deserialize(JsonParser parser, JsonpMapper mapper) {
                return fn.apply(parser, mapper);
            }

            @Override
            public T deserialize(JsonParser parser, JsonpMapper mapper, Event event) {
                throw new UnsupportedOperationException();
            }
        };
    }

    static <T> JsonpDeserializer<T> of(EnumSet<Event> acceptedEvents, TriFunction<JsonParser, JsonpMapper, Event, T> fn) {
        return new JsonpDeserializerBase<>(acceptedEvents) {
            @Override
            public T deserialize(JsonParser parser, JsonpMapper mapper, Event event) {
                return fn.apply(parser, mapper, event);
            }
        };
    }


    static <T> JsonpDeserializer<T> lazy(Supplier<JsonpDeserializer<T>> ctor) {
        return new LazyDeserializer<>(ctor);
    }

    //----- Builtin types

    static <T> JsonpDeserializer<T> fixedValue(T value) {
        return new JsonpDeserializerBase<>(EnumSet.noneOf(Event.class)) {
            @Override
            public T deserialize(JsonParser parser, JsonpMapper mapper, Event event) {
                return value;
            }
        };
    }


    static <T> JsonpDeserializer<T> emptyObject(T value) {
        return new JsonpDeserializerBase<>(EnumSet.of(Event.START_OBJECT)) {
            @Override
            public T deserialize(JsonParser parser, JsonpMapper mapper, Event event) {
                if (event == Event.VALUE_NULL) {
                    return null;
                }

                JsonpUtils.expectNextEvent(parser, Event.END_OBJECT);
                return value;
            }
        };
    }


    static JsonpDeserializer<String> stringDeserializer() {
        return JsonpDeserializerBase.STRING;
    }

    static JsonpDeserializer<Integer> integerDeserializer() {
        return JsonpDeserializerBase.INTEGER;
    }

    static JsonpDeserializer<Boolean> booleanDeserializer() {
        return JsonpDeserializerBase.BOOLEAN;
    }

    static JsonpDeserializer<Long> longDeserializer() {
        return JsonpDeserializerBase.LONG;
    }

    static JsonpDeserializer<Float> floatDeserializer() {
        return JsonpDeserializerBase.FLOAT;
    }

    static JsonpDeserializer<Double> doubleDeserializer() {
        return JsonpDeserializerBase.DOUBLE;
    }


    static JsonpDeserializer<Double> doubleOrNullDeserializer(double defaultValue) {
        return new JsonpDeserializerBase.DoubleOrNullDeserializer(defaultValue);
    }


    static JsonpDeserializer<Integer> intOrNullDeserializer(int defaultValue) {
        return new JsonpDeserializerBase.IntOrNullDeserializer(defaultValue);
    }

    static JsonpDeserializer<Number> numberDeserializer() {
        return JsonpDeserializerBase.NUMBER;
    }



    static JsonpDeserializer<JsonValue> jsonValueDeserializer() {
        return JsonpDeserializerBase.JSON_VALUE;
    }


    static JsonpDeserializer<Void> voidDeserializer() {
        return JsonpDeserializerBase.VOID;
    }


    static <T> JsonpDeserializer<List<T>> arrayDeserializer(JsonpDeserializer<T> itemDeserializer) {
        return new JsonpDeserializerBase.ArrayDeserializer<>(itemDeserializer);
    }

    static <T> JsonpDeserializer<Map<String, T>> stringMapDeserializer(JsonpDeserializer<T> itemDeserializer) {
        return new JsonpDeserializerBase.StringMapDeserializer<>(itemDeserializer);
    }

    static <K extends JsonEnum, V> JsonpDeserializer<Map<K, V>> enumMapDeserializer(
            JsonpDeserializer<K> keyDeserializer, JsonpDeserializer<V> valueDeserializer
    ) {
        return new JsonpDeserializerBase.EnumMapDeserializer<>(keyDeserializer, valueDeserializer);
    }

    static <T> JsonpDeserializer<T> jsonString(JsonpDeserializer<T> valueDeserializer) {
        EnumSet<Event> acceptedEvents = EnumSet.copyOf(valueDeserializer.acceptedEvents());
        acceptedEvents.add(Event.VALUE_STRING);

        EnumSet<Event> nativeEvents = EnumSet.copyOf(valueDeserializer.nativeEvents());
        nativeEvents.add(Event.VALUE_STRING);

        return new JsonpDeserializerBase<>(acceptedEvents, nativeEvents) {
            @Override
            public T deserialize(JsonParser parser, JsonpMapper mapper, Event event) {
                if (event == Event.VALUE_STRING) {
                    JsonParser stringParser = mapper.jsonProvider().createParser(new StringReader(parser.getString()));
                    return valueDeserializer.deserialize(stringParser, mapper);
                } else {
                    return valueDeserializer.deserialize(parser, mapper, event);
                }
            }
        };
    }
}