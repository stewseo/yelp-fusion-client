package io.github.stewseo.yelp.fusion.client.json;

import jakarta.json.JsonNumber;
import jakarta.json.JsonValue;
import jakarta.json.stream.JsonParser;
import jakarta.json.stream.JsonParser.Event;

import java.util.*;

public abstract class JsonpDeserializerBase<V> implements JsonpDeserializer<V> {

    private final EnumSet<Event> acceptedEvents;
    private final EnumSet<Event> nativeEvents;

    protected JsonpDeserializerBase(EnumSet<Event> acceptedEvents) {
        this(acceptedEvents, acceptedEvents);
    }

    protected JsonpDeserializerBase(EnumSet<Event> acceptedEvents, EnumSet<Event> nativeEvents) {
        this.acceptedEvents = acceptedEvents;
        this.nativeEvents = nativeEvents;
    }


    protected static EnumSet<Event> allAcceptedEvents(JsonpDeserializer<?>... deserializers) {
        EnumSet<Event> result = EnumSet.noneOf(Event.class);
        for (JsonpDeserializer<?> deserializer: deserializers) {

            EnumSet<Event> set = deserializer.acceptedEvents();


            result.addAll(set);
        }
        return result;
    }

    @Override
    public EnumSet<Event> nativeEvents() {
        return nativeEvents;
    }

    public final EnumSet<Event> acceptedEvents() {
        return acceptedEvents;
    }

    public final boolean accepts(Event event) {
        return acceptedEvents.contains(event);
    }

    //---------------------------------------------------------------------------------------------

    //----- Builtin types

    static final JsonpDeserializer<String> STRING =
            // String parsing is lenient and accepts any other primitive type
            new JsonpDeserializerBase<>(EnumSet.of(
                    Event.KEY_NAME, Event.VALUE_STRING, Event.VALUE_NUMBER,
                    Event.VALUE_FALSE, Event.VALUE_TRUE
            ),
                    EnumSet.of(Event.VALUE_STRING)
            ) {
                @Override
                public String deserialize(JsonParser parser, JsonpMapper mapper, Event event) {
                    if (event == Event.VALUE_TRUE) {
                        return "true";
                    }
                    if (event == Event.VALUE_FALSE) {
                        return "false";
                    }
                    return parser.getString(); // also accepts numbers
                }
            };

    static final JsonpDeserializer<Integer> INTEGER =
            new JsonpDeserializerBase<>(
                    EnumSet.of(Event.VALUE_NUMBER, Event.VALUE_STRING),
                    EnumSet.of(Event.VALUE_NUMBER)
            ) {
                @Override
                public Integer deserialize(JsonParser parser, JsonpMapper mapper, Event event) {
                    if (event == Event.VALUE_STRING) {
                        return Integer.valueOf(parser.getString());
                    }
                    return parser.getInt();
                }
            };

    static final JsonpDeserializer<Boolean> BOOLEAN =
            new JsonpDeserializerBase<>(
                    EnumSet.of(Event.VALUE_FALSE, Event.VALUE_TRUE, Event.VALUE_STRING),
                    EnumSet.of(Event.VALUE_FALSE, Event.VALUE_TRUE)
            ) {
                @Override
                public Boolean deserialize(JsonParser parser, JsonpMapper mapper, Event event) {
                    if (event == Event.VALUE_STRING) {
                        return Boolean.parseBoolean(parser.getString());
                    } else {
                        return event == Event.VALUE_TRUE;
                    }
                }
            };

    static final JsonpDeserializer<Long> LONG =
            new JsonpDeserializerBase<>(
                    EnumSet.of(Event.VALUE_NUMBER, Event.VALUE_STRING),
                    EnumSet.of(Event.VALUE_NUMBER)
            ) {
                @Override
                public Long deserialize(JsonParser parser, JsonpMapper mapper, Event event) {
                    if (event == Event.VALUE_STRING) {
                        return Long.valueOf(parser.getString());
                    }
                    return parser.getLong();
                }
            };

    static final JsonpDeserializer<Float> FLOAT =
            new JsonpDeserializerBase<>(
                    EnumSet.of(Event.VALUE_NUMBER, Event.VALUE_STRING),
                    EnumSet.of(Event.VALUE_NUMBER)

            ) {
                @Override
                public Float deserialize(JsonParser parser, JsonpMapper mapper, Event event) {
                    if (event == Event.VALUE_STRING) {
                        return Float.valueOf(parser.getString());
                    }
                    return parser.getBigDecimal().floatValue();
                }
            };

    static final JsonpDeserializer<Double> DOUBLE =
            new JsonpDeserializerBase<>(
                    EnumSet.of(Event.VALUE_NUMBER, Event.VALUE_STRING),
                    EnumSet.of(Event.VALUE_NUMBER)
            ) {
                @Override
                public Double deserialize(JsonParser parser, JsonpMapper mapper, Event event) {
                    if (event == Event.VALUE_STRING) {
                        return Double.valueOf(parser.getString());
                    }
                    return parser.getBigDecimal().doubleValue();
                }
            };

    static final class DoubleOrNullDeserializer extends JsonpDeserializerBase<Double> {
        static final EnumSet<Event> nativeEvents = EnumSet.of(Event.VALUE_NUMBER, Event.VALUE_NULL);
        static final EnumSet<Event> acceptedEvents = EnumSet.of(Event.VALUE_STRING, Event.VALUE_NUMBER, Event.VALUE_NULL);
        private final double defaultValue;

        DoubleOrNullDeserializer(double defaultValue) {
            super(acceptedEvents, nativeEvents);
            this.defaultValue = defaultValue;
        }

        @Override
        public Double deserialize(JsonParser parser, JsonpMapper mapper, Event event) {
            if (event == Event.VALUE_NULL) {
                return defaultValue;
            }
            if (event == Event.VALUE_STRING) {
                return Double.valueOf(parser.getString());
            }
            return parser.getBigDecimal().doubleValue();
        }
    }

    static final class IntOrNullDeserializer extends JsonpDeserializerBase<Integer> {
        static final EnumSet<Event> nativeEvents = EnumSet.of(Event.VALUE_NUMBER, Event.VALUE_NULL);
        static final EnumSet<Event> acceptedEvents = EnumSet.of(Event.VALUE_STRING, Event.VALUE_NUMBER, Event.VALUE_NULL);
        private final int defaultValue;

        IntOrNullDeserializer(int defaultValue) {
            super(acceptedEvents, nativeEvents);
            this.defaultValue = defaultValue;
        }

        @Override
        public Integer deserialize(JsonParser parser, JsonpMapper mapper, Event event) {
            if (event == Event.VALUE_NULL) {
                return defaultValue;
            }
            if (event == Event.VALUE_STRING) {
                return Integer.valueOf(parser.getString());
            }
            return parser.getInt();
        }
    }

    static final JsonpDeserializer<Double> DOUBLE_OR_NAN =
            new JsonpDeserializerBase<>(
                    EnumSet.of(Event.VALUE_NUMBER, Event.VALUE_STRING, Event.VALUE_NULL),
                    EnumSet.of(Event.VALUE_NUMBER, Event.VALUE_NULL)
            ) {
                @Override
                public Double deserialize(JsonParser parser, JsonpMapper mapper, Event event) {
                    if (event == Event.VALUE_NULL) {
                        return Double.NaN;
                    }
                    if (event == Event.VALUE_STRING) {
                        return Double.valueOf(parser.getString());
                    }
                    return parser.getBigDecimal().doubleValue();
                }
            };

    static final JsonpDeserializer<Number> NUMBER =
            new JsonpDeserializerBase<>(
                    EnumSet.of(Event.VALUE_NUMBER, Event.VALUE_STRING),
                    EnumSet.of(Event.VALUE_NUMBER)
            ) {
                @Override
                public Number deserialize(JsonParser parser, JsonpMapper mapper, Event event) {
                    if (event == Event.VALUE_STRING) {
                        return Double.valueOf(parser.getString());
                    }
                    return ((JsonNumber) parser.getValue()).numberValue();
                }
            };

    static final JsonpDeserializer<JsonValue> JSON_VALUE =
            new JsonpDeserializerBase<>(
                    EnumSet.allOf(Event.class)
            ) {
                @Override
                public JsonValue deserialize(JsonParser parser, JsonpMapper mapper, Event event) {
                    return parser.getValue();
                }
            };

    static final JsonpDeserializer<Void> VOID = new JsonpDeserializerBase<>(
            EnumSet.allOf(Event.class)
    ) {
        @Override
        public Void deserialize(JsonParser parser, JsonpMapper mapper, Event event) {
            JsonpUtils.skipValue(parser, event);
            return null;
        }
    };

    //----- Collections

    static class ArrayDeserializer<T> implements JsonpDeserializer<List<T>> {
        private final JsonpDeserializer<T> itemDeserializer;
        private EnumSet<Event> acceptedEvents;
        private static final EnumSet<Event> nativeEvents = EnumSet.of(Event.START_ARRAY);

        protected ArrayDeserializer(JsonpDeserializer<T> itemDeserializer) {
            this.itemDeserializer = itemDeserializer;
        }

        @Override
        public EnumSet<Event> nativeEvents() {
            return nativeEvents;
        }

        @Override
        public EnumSet<Event> acceptedEvents() {
            // Accepted events is computed lazily
            // no need for double-checked lock, we don't care about computing it several times
            EnumSet<Event> events = acceptedEvents;
            if (events == null) {
                events = EnumSet.of(Event.START_ARRAY);
                events.addAll(itemDeserializer.acceptedEvents());
                acceptedEvents = events;
            }
            return events;
        }

        @Override
        public List<T> deserialize(JsonParser parser, JsonpMapper mapper, Event event) {
            if (event == Event.START_ARRAY) {
                List<T> result = new ArrayList<>();
                try {
                    while ((event = parser.next()) != Event.END_ARRAY) {
                        // JSON null: add null unless the deserializer can handle it
                        if (event == Event.VALUE_NULL && !itemDeserializer.accepts(event)) {
                            result.add(null);
                        } else {
                            JsonpUtils.ensureAccepts(itemDeserializer, parser, event);
                            result.add(itemDeserializer.deserialize(parser, mapper, event));
                        }
                    }
                } catch (Exception e) {
                    throw JsonpMappingException.from(e, result.size(), parser);
                }
                return result;
            } else {
                // Single-value mode
                JsonpUtils.ensureAccepts(itemDeserializer, parser, event);
                return Collections.singletonList(itemDeserializer.deserialize(parser, mapper, event));
            }
        }
    }

    static class StringMapDeserializer<T> extends JsonpDeserializerBase<Map<String, T>> {
        private final JsonpDeserializer<T> itemDeserializer;

        protected StringMapDeserializer(JsonpDeserializer<T> itemDeserializer) {
            super(EnumSet.of(Event.START_OBJECT));
            this.itemDeserializer = itemDeserializer;
        }

        @Override
        public Map<String, T> deserialize(JsonParser parser, JsonpMapper mapper, Event event) {
            Map<String, T> result = new HashMap<>();
            String key = null;
            try {
                while ((event = parser.next()) != Event.END_OBJECT) {
                    JsonpUtils.expectEvent(parser, Event.KEY_NAME, event);
                    key = parser.getString();
                    T value = itemDeserializer.deserialize(parser, mapper);
                    result.put(key, value);
                }
            } catch (Exception e) {
                throw JsonpMappingException.from(e, null, key, parser);
            }
            return result;
        }
    }

    static class EnumMapDeserializer<K, V> extends JsonpDeserializerBase<Map<K, V>> {
        private final JsonpDeserializer<K> keyDeserializer;
        private final JsonpDeserializer<V> valueDeserializer;

        protected EnumMapDeserializer(JsonpDeserializer<K> keyDeserializer, JsonpDeserializer<V> valueDeserializer) {
            super(EnumSet.of(Event.START_OBJECT));
            this.keyDeserializer = keyDeserializer;
            this.valueDeserializer = valueDeserializer;
        }

        @Override
        public Map<K, V> deserialize(JsonParser parser, JsonpMapper mapper, Event event) {
            Map<K, V> result = new HashMap<>();
            String keyName = null;
            try {
                while ((event = parser.next()) != Event.END_OBJECT) {
                    JsonpUtils.expectEvent(parser, Event.KEY_NAME, event);
                    keyName = parser.getString();
                    K key = keyDeserializer.deserialize(parser, mapper, event);
                    V value = valueDeserializer.deserialize(parser, mapper);
                    result.put(key, value);
                }
            } catch (Exception e) {
                throw JsonpMappingException.from(e, null, keyName, parser);
            }
            return result;
        }
    }
}