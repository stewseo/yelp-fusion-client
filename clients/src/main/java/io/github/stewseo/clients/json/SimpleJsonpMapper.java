package io.github.stewseo.clients.json;

import jakarta.json.JsonException;
import jakarta.json.spi.JsonProvider;
import jakarta.json.stream.JsonGenerator;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class SimpleJsonpMapper extends JsonpMapperBase {

    public static final SimpleJsonpMapper INSTANCE_REJECT_UNKNOWN_FIELDS = new SimpleJsonpMapper(false);
    private static final Map<Type, JsonpSerializer<?>> serializers = new HashMap<>();
    private static final Map<Type, JsonpDeserializer<?>> deserializers = new HashMap<>();

    public static SimpleJsonpMapper INSTANCE = new SimpleJsonpMapper(true);

    static {
        serializers.put(String.class, (JsonpSerializer<String>) (value, generator, mapper) -> generator.write(value));
        serializers.put(Boolean.class, (JsonpSerializer<Boolean>) (value, generator, mapper) -> generator.write(value));
        serializers.put(Boolean.TYPE, (JsonpSerializer<Boolean>) (value, generator, mapper) -> generator.write(value));
        serializers.put(Integer.class, (JsonpSerializer<Integer>) (value, generator, mapper) -> generator.write(value));
        serializers.put(Integer.TYPE, (JsonpSerializer<Integer>) (value, generator, mapper) -> generator.write(value));
        serializers.put(Long.class, (JsonpSerializer<Long>) (value, generator, mapper) -> generator.write(value));
        serializers.put(Long.TYPE, (JsonpSerializer<Long>) (value, generator, mapper) -> generator.write(value));
        serializers.put(Float.class, (JsonpSerializer<Float>) (value, generator, mapper) -> generator.write(value));
        serializers.put(Float.TYPE, (JsonpSerializer<Float>) (value, generator, mapper) -> generator.write(value));
        serializers.put(Double.class, (JsonpSerializer<Double>) (value, generator, mapper) -> generator.write(value));
        serializers.put(Double.TYPE, (JsonpSerializer<Double>) (value, generator, mapper) -> generator.write(value));

        deserializers.put(String.class, JsonpDeserializer.stringDeserializer());
        deserializers.put(Boolean.class, JsonpDeserializer.booleanDeserializer());
        deserializers.put(Boolean.TYPE, JsonpDeserializer.booleanDeserializer());
        deserializers.put(Integer.class, JsonpDeserializer.integerDeserializer());
        deserializers.put(Integer.TYPE, JsonpDeserializer.integerDeserializer());
        deserializers.put(Long.class, JsonpDeserializer.longDeserializer());
        deserializers.put(Long.TYPE, JsonpDeserializer.longDeserializer());
        deserializers.put(Float.class, JsonpDeserializer.floatDeserializer());
        deserializers.put(Float.TYPE, JsonpDeserializer.floatDeserializer());
        deserializers.put(Double.class, JsonpDeserializer.doubleDeserializer());
        deserializers.put(Double.TYPE, JsonpDeserializer.doubleDeserializer());
    }

    private final boolean ignoreUnknownFields;

    public SimpleJsonpMapper(boolean ignoreUnknownFields) {
        this.ignoreUnknownFields = ignoreUnknownFields;
    }

    public SimpleJsonpMapper() {
        // Lenient by default
        this(true);
    }

    @Override
    public <T> JsonpMapper withAttribute(String name, T value) {
        return new SimpleJsonpMapper(this.ignoreUnknownFields).addAttribute(name, value);
    }

    @Override
    public boolean ignoreUnknownFields() {
        return ignoreUnknownFields;
    }

    @Override
    public JsonProvider jsonProvider() {
        return JsonpUtils.provider();
    }

    @Override
    public <T> void serialize(T value, JsonGenerator generator) {
        JsonpSerializer<T> serializer = findSerializer(value);

        if (serializer == null) {
            @SuppressWarnings("unchecked")
            JsonpSerializer<T> serializer_ = (JsonpSerializer<T>) serializers.get(value.getClass());
            serializer = serializer_;
        }

        if (serializer == null) {
            serializer = getDefaultSerializer(value);
        }

        if (serializer != null) {
            serializer.serialize(value, generator, this);
        } else {
            throw new JsonException(
                    "Cannot find a serializer for type " + value.getClass().getName() +
                            ". Consider using a full-featured JsonpMapper"
            );
        }
    }

    @Override
    protected <T> JsonpDeserializer<T> getDefaultDeserializer(Type type) {
        @SuppressWarnings("unchecked")
        JsonpDeserializer<T> deserializer = (JsonpDeserializer<T>) deserializers.get(type);
        if (deserializer != null) {
            return deserializer;
        } else {
            throw new JsonException(
                    "Cannot find a deserializer for type " + type.getTypeName() +
                            ". Consider using a full-featured JsonpMapper"
            );
        }
    }

    protected <T> JsonpSerializer<T> getDefaultSerializer(T value) {
        return null;
    }
}