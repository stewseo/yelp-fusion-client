package io.github.stewseo.clients.json;

import io.github.stewseo.clients.json.JsonpSerializer;
import io.github.stewseo.clients.json.SimpleJsonpMapper;

class ToStringMapper extends SimpleJsonpMapper {

    static final ToStringMapper INSTANCE = new ToStringMapper();

    private static final JsonpSerializer<?> toStringSerializer = (JsonpSerializer<Object>) (value, generator, mapper) -> {
        if (value == null) {
            generator.writeNull();
        } else {
            generator.write(value.toString());
        }
    };

    @Override
    @SuppressWarnings("unchecked")
    protected <T> JsonpSerializer<T> getDefaultSerializer(T value) {
        return (JsonpSerializer<T>) toStringSerializer;
    }
}