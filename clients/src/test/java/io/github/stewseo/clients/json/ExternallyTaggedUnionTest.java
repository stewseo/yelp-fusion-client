package io.github.stewseo.clients.json;


import io.github.stewseo.clients._type.Property;
import io.github.stewseo.clients.json.jsonb.JsonbJsonpMapper;
import jakarta.json.spi.JsonProvider;
import jakarta.json.stream.JsonGenerator;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ExternallyTaggedUnionTest {

    private final JsonGenerator generator;

    private final JsonpMapper mapper;

    private final Property property;

    private ExternallyTaggedUnionTest() {

        generator = JsonProvider.provider().createGenerator(new StringWriter());

        mapper = new JsonbJsonpMapper();

        property = Property.of(p -> p
                .kind(Property.Kind.Term)
                ._value("termValue")
        );
    }

    @Test
    void serializeTypedKeys() {

        final Map<String, Property> mapOfProperties = Map.of("key", property);

        ExternallyTaggedUnion.serializeTypedKeys(mapOfProperties, generator, mapper);

        assertThat(mapOfProperties).isNotNull();
        assertThat(mapOfProperties.size()).isEqualTo(1);
    }

    @Test
    void serializeTypedKeysArray() {

        final Map<String, List<Property>> mapOfPropertyLists = Map.of("key", List.of(property));
        ExternallyTaggedUnion.serializeTypedKeysArray(mapOfPropertyLists, generator, mapper);
        assertThat(mapOfPropertyLists).isNotNull();
        assertThat(mapOfPropertyLists.size()).isEqualTo(1);
        assertThat(mapOfPropertyLists.get("key").size()).isEqualTo(1);
    }

    @Test
    void serializeTypedKeysInner() {

        final Map<String, Property> mapOfProperties = Map.of("key", property);
        generator.writeStartObject();
        ExternallyTaggedUnion.serializeTypedKeysInner(mapOfProperties, generator, mapper);
        generator.writeEnd();

        assertThat(mapOfProperties).isNotNull();
        assertThat(mapOfProperties.get("key")).isEqualTo(property);

    }
}