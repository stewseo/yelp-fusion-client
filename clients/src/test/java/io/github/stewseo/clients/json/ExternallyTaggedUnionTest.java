package io.github.stewseo.clients.json;

import co.elastic.clients.elasticsearch._types.aggregations.Aggregate;
import io.github.stewseo.clients._types.Property;
import io.github.stewseo.clients.json.jackson.JacksonJsonpMapper;
import jakarta.json.spi.JsonProvider;
import jakarta.json.stream.JsonGenerator;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ExternallyTaggedUnionTest {
    JsonGenerator generator = JsonProvider.provider().createGenerator(new StringWriter());
    private final JsonpMapper mapper = new JacksonJsonpMapper();

    @Test
    void serializeTypedKeys() {

        final Map<String, Property> map = new HashMap<>();

        ExternallyTaggedUnion.serializeTypedKeys(map, generator, mapper);
        assertThat(map).isNotNull();
    }

    @Test
    void serializeTypedKeysArray() {
        final Map<String, List<Property>> mapp = new HashMap<>();
        ExternallyTaggedUnion.serializeTypedKeysArray(mapp, generator, mapper);
    }

    @Test
    void serializeTypedKeysInner() {
    }
}