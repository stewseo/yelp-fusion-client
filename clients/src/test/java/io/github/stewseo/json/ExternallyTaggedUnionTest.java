package io.github.stewseo.json;

import io.github.stewseo.clients._types.Property;
import io.github.stewseo.clients.json.ExternallyTaggedUnion;
import io.github.stewseo.clients.json.JsonpMapper;
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

    //     public static final ExternallyTaggedUnion.TypedKeysDeserializer<Property> _TYPED_KEYS_DESERIALIZER;
    //
    //    static {
    //        Map<String, JsonpDeserializer<? extends Property>> deserializers = new HashMap<>();
    //
    //        _TYPED_KEYS_DESERIALIZER = new ExternallyTaggedUnion.Deserializer<>(deserializers, Property::new,
    //                Property::new).typedKeys();
    //    }

    private ExternallyTaggedUnionTest() {

        generator = JsonProvider.provider().createGenerator(new StringWriter());

        mapper = new JsonbJsonpMapper();

        property = Property.of(p -> p
                .kind(Property.Kind.Binary)
                ._value("binaryValue")
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