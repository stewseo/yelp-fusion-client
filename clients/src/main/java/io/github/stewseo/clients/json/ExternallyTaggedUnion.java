package io.github.stewseo.clients.json;

import io.github.stewseo.clients.util.OpenTaggedUnion;
import io.github.stewseo.clients.util.TaggedUnion;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Utilities for union types whose discriminant is not directly part of the structure, either as an enclosing property name or as
 * an inner property. This is used for Elasticsearch aggregation results and suggesters, using the {@code typed_keys} parameter that
 * encodes a name+type in a single JSON property.
 *
 */
public class ExternallyTaggedUnion {

    private ExternallyTaggedUnion() {
    }

    /**
     * A deserializer for externally-tagged unions. Since the union variant discriminant is provided externally, this cannot be a
     * regular {@link JsonpDeserializer} as the caller has to provide the discriminant value.
     */
    public static <T extends TaggedUnion<?, ?>> JsonpDeserializer<Map<String, List<T>>> arrayMapDeserializer(
            TypedKeysDeserializer<T> deserializer
    ) {
        return JsonpDeserializer.of(
                EnumSet.of(JsonParser.Event.START_OBJECT),
                (parser, mapper, event) -> {
                    Map<String, List<T>> result = new HashMap<>();
                    String key = null;
                    try {
                        while ((event = parser.next()) != JsonParser.Event.END_OBJECT) {
                            JsonpUtils.expectEvent(parser, event, JsonParser.Event.KEY_NAME);
                            // Split key and type
                            key = parser.getString();
                            int hashPos = key.indexOf('#');
                            if (hashPos == -1) {
                                throw new JsonpMappingException(
                                        "Property name '" + key + "' is not in the 'type#name' format. Make sure the request has 'typed_keys' set.",
                                        parser.getLocation()
                                ).prepend(null, key);
                            }

                            String type = key.substring(0, hashPos);
                            String name = key.substring(hashPos + 1);

                            List<T> list = new ArrayList<>();
                            JsonpUtils.expectNextEvent(parser, JsonParser.Event.START_ARRAY);
                            try {
                                while ((event = parser.next()) != JsonParser.Event.END_ARRAY) {
                                    list.add(deserializer.deserializer.deserialize(type, parser, mapper, event));
                                }
                            } catch (Exception e) {
                                throw JsonpMappingException.from(e, list.size(), parser);
                            }
                            result.put(name, list);
                        }
                    } catch (Exception e) {
                        throw JsonpMappingException.from(e, null, key, parser);
                    }
                    return result;
                }
        );
    }

    /**
     * Serialize a map of externally tagged union objects.
     * <p>
     * If {@link JsonpMapperFeatures#SERIALIZE_TYPED_KEYS} is <code>true</code> (the default), the typed keys encoding
     * (<code>type#name</code>) is used.
     */
    public static <T extends JsonpSerializable & TaggedUnion<? extends JsonEnum, ?>> void serializeTypedKeys(
            Map<String, T> map, JsonGenerator generator, JsonpMapper mapper
    ) {
        generator.writeStartObject();
        serializeTypedKeysInner(map, generator, mapper);
        generator.writeEnd();
    }

    /**
     * Serialize a map of externally tagged union object arrays.
     * <p>
     * If {@link JsonpMapperFeatures#SERIALIZE_TYPED_KEYS} is <code>true</code> (the default), the typed keys encoding
     * (<code>type#name</code>) is used.
     */
    public static <T extends JsonpSerializable & TaggedUnion<? extends JsonEnum, ?>> void serializeTypedKeysArray(
            Map<String, List<T>> map, JsonGenerator generator, JsonpMapper mapper
    ) {
        generator.writeStartObject();

        if (mapper.attribute(JsonpMapperFeatures.SERIALIZE_TYPED_KEYS, true)) {
            for (Map.Entry<String, List<T>> entry : map.entrySet()) {
                List<T> list = entry.getValue();
                if (list.isEmpty()) {
                    continue; // We can't know the kind, skip this entry
                }

                generator.writeKey(getKind(list.get(0)) + "#" + entry.getKey());
                generator.writeStartArray();
                for (T value : list) {
                    value.serialize(generator, mapper);
                }
                generator.writeEnd();
            }
        } else {
            for (Map.Entry<String, List<T>> entry : map.entrySet()) {
                generator.writeKey(entry.getKey());
                generator.writeStartArray();
                for (T value : entry.getValue()) {
                    value.serialize(generator, mapper);
                }
                generator.writeEnd();
            }
        }

        generator.writeEnd();
    }

    /**
     * Serialize a map of externally tagged union objects.
     * <p>
     * If {@link JsonpMapperFeatures#SERIALIZE_TYPED_KEYS} is <code>true</code> (the default), the typed keys encoding
     * (<code>type#name</code>) is used.
     */
    public static <T extends JsonpSerializable & TaggedUnion<? extends JsonEnum, ?>> void serializeTypedKeysInner(
            Map<String, T> map, JsonGenerator generator, JsonpMapper mapper
    ) {
        if (mapper.attribute(JsonpMapperFeatures.SERIALIZE_TYPED_KEYS, true)) {
            for (Map.Entry<String, T> entry : map.entrySet()) {
                T value = entry.getValue();
                generator.writeKey(getKind(value) + "#" + entry.getKey());
                value.serialize(generator, mapper);
            }
        } else {
            for (Map.Entry<String, T> entry : map.entrySet()) {
                generator.writeKey(entry.getKey());
                entry.getValue().serialize(generator, mapper);
            }
        }
    }


    private static <T extends JsonpSerializable & TaggedUnion<? extends JsonEnum, ?>> String getKind(T value) {
        String kind;
        if (value instanceof OpenTaggedUnion) {
            // Use custom kind if defined, and fall back to regular kind
            kind = ((OpenTaggedUnion<?, ?>) value)._customKind();
            if (kind == null) {
                kind = value._kind().jsonValue();
            }
        } else {
            kind = value._kind().jsonValue();
        }
        return kind;
    }

    /**
     * A deserializer for externally-tagged unions. Since the union variant discriminant is provided externally, this cannot be a
     * regular {@link JsonpDeserializer} as the caller has to provide the discriminant value.
     */
    public static class Deserializer<Union extends TaggedUnion<?, ?>, Member> {
        private final Map<String, JsonpDeserializer<? extends Member>> deserializers;
        private final Function<Member, Union> unionCtor;
        @Nullable
        private final BiFunction<String, JsonData, Union> unknownVariantCtor;

        public Deserializer(Map<String, JsonpDeserializer<? extends Member>> deserializers, Function<Member, Union> unionCtor) {
            this.deserializers = deserializers;
            this.unionCtor = unionCtor;
            this.unknownVariantCtor = null;
        }

        public Deserializer(
                Map<String, JsonpDeserializer<? extends Member>> deserializers,
                Function<Member, Union> unionCtor,
                BiFunction<String, JsonData, Union> unknownVariantCtor
        ) {
            this.deserializers = deserializers;
            this.unionCtor = unionCtor;
            this.unknownVariantCtor = unknownVariantCtor;
        }

        /**
         * Deserialize a union value, given its type.
         */
        public Union deserialize(String type, JsonParser parser, JsonpMapper mapper, JsonParser.Event event) {
            JsonpDeserializer<? extends Member> deserializer = deserializers.get(type);
            if (deserializer == null) {
                if (unknownVariantCtor == null) {
                    throw new JsonpMappingException("Unknown variant type '" + type + "'", parser.getLocation());
                } else {
                    return unknownVariantCtor.apply(type, JsonData._DESERIALIZER.deserialize(parser, mapper, event));
                }
            }

            return unionCtor.apply(deserializer.deserialize(parser, mapper, event));
        }

        /**
         * Deserialize an externally tagged union encoded as typed keys, a JSON dictionary whose property names combine type and name
         * in a single string.
         */
        public TypedKeysDeserializer<Union> typedKeys() {
            return new TypedKeysDeserializer<>(this);
        }
    }

    public static class TypedKeysDeserializer<Union extends TaggedUnion<?, ?>>
            extends JsonpDeserializerBase<Map<String, Union>> {
        Deserializer<Union, ?> deserializer;

        protected TypedKeysDeserializer(Deserializer<Union, ?> deser) {
            super(EnumSet.of(JsonParser.Event.START_OBJECT));
            this.deserializer = deser;
        }

        @Override
        public Map<String, Union> deserialize(JsonParser parser, JsonpMapper mapper, JsonParser.Event event) {
            Map<String, Union> result = new HashMap<>();
            if(event == null) {

            }
            while ((event = parser.next()) != JsonParser.Event.END_OBJECT) {
                JsonpUtils.expectEvent(parser, event, JsonParser.Event.KEY_NAME);
                deserializeEntry(parser.getString(), parser, mapper, result);
            }
            return result;

        }

        public void deserializeEntry(String key, JsonParser parser, JsonpMapper mapper, Map<String, Union> targetMap) {
            int hashPos = key.indexOf('#');
            if (hashPos == -1) {
                throw new JsonpMappingException(
                        "Property name '" + key + "' is not in the 'type#name' format. Make sure the request has 'typed_keys' set.",
                        parser.getLocation()
                );
            }

            String type = key.substring(0, hashPos);
            String name = key.substring(hashPos + 1);

            targetMap.put(name, deserializer.deserialize(type, parser, mapper, parser.next()));
        }
    }
}
