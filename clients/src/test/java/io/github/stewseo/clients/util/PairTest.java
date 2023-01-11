package io.github.stewseo.clients.util;

import io.github.stewseo.clients._types.UVariantA;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.json.jsonb.JsonbJsonpMapper;
import io.github.stewseo.clients.json.testcases.TestJson;
import jakarta.json.spi.JsonProvider;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import jakarta.json.stream.JsonParsingException;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class PairTest extends TestJson {

    private final SomeUnion suA = new SomeUnion.Builder()
            .variantA(a_ -> a_
                    .name("a-name")
            ).build();

    private final SomeUnion suB = new SomeUnion.Builder()
            .variantB(a_ -> a_
                    .number(1)
            ).build();

    private final String json = "{\"type\":\"variant_a\",\"name\":\"a-name\"}";

    @Test
    public void testMissingVariantDeserialization() {

        String json = "{}";

        JsonParser parser = mapper.jsonProvider().createParser(new StringReader(json));

        JsonParsingException e = assertThrows(JsonParsingException.class, () -> {
            SomeUnion someUnion = SomeUnion._DESERIALIZER.deserialize(parser, new JsonbJsonpMapper());
        });

        assertTrue(e.getMessage().contains("Property 'type' not found"));
    }

    @Test
    void testInitializeWithKeyAndValue() {
        Pair<String, SomeUnion> pair = Pair.of("key", suA);
        assertThat(pair).isInstanceOf(Pair.class);
        assertThat(pair.key()).isEqualTo("key");
        assertThat(pair.value()).isEqualTo(suA);
    }

    @Test
    void testDeserializer() {

        JsonpDeserializer<?> pairJsonpDeserializer = Pair.deserializer((k) -> JsonpDeserializer.stringDeserializer(), SomeUnion._DESERIALIZER);
        assertThat(pairJsonpDeserializer).isInstanceOf(JsonpDeserializer.class);
    }


    @Test
    public void testTaggedUnion() {

        assertThat(suA.variantA().name()).isEqualTo("a-name");

        assertThat("variant_a").isEqualTo(suA.variantA()._variantType());

        assertThat(suB.variantB().number()).isEqualTo(1);

        assertThat("variant_a").isEqualTo(suB.variantB()._variantType());

    }

    @Test
    public void testDeserialization() {
        SomeUnion u = fromJson(json, SomeUnion.class);
        UVariantA uVariantA = u.variantA();
        assertEquals("a-name", uVariantA.name());
    }

    @Test
    public void testSerialization() {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        JsonProvider provider = JsonpUtils.provider();

        JsonGenerator generator = provider.createGenerator(baos);

        suA.serialize(generator, new JsonbJsonpMapper());
        generator.close();

        System.out.println(baos.toString());

        assertEquals(json, baos.toString());

    }
}