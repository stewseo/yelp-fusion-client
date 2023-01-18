package io.github.stewseo.clients.yelpfusion._types;

import io.github.stewseo.clients.json.JsonData;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.json.jsonb.JsonbJsonpMapper;
import io.github.stewseo.clients.json.testcases.ModelJsonTestCase;
import io.github.stewseo.clients.util.MissingRequiredPropertyException;
import io.github.stewseo.clients.util.Pair;
import jakarta.json.spi.JsonProvider;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;

import static org.assertj.core.api.Assertions.assertThat;


public class QueryParameterTest extends ModelJsonTestCase {

    private final TermQueryParameter termQueryField = TermQueryParameter.of(variant -> variant.term(TermEnum.Restaurants));

    private final QueryParameter queryField = QueryParameter.of(qf -> qf.term(termQueryField));

    private final String json = "{\"term\":{\"term\":\"restaurants\"}}";

    @TypeTest
    void test_kind() {
        assertThat(termQueryField._queryFieldKind()).isEqualTo(QueryParameter.Kind.Term);
    }

    @TypeTest
    void test_get() {
        assertThat(queryField._get().toString()).isEqualTo("{\"term\":\"restaurants\"}");
    }

    @TypeTest
    void test_customKind() {
        QueryParameter.of(qf -> qf._custom("customKind", JsonData.of("jsonData")));
    }

    @TypeTest
    void test_isCustom() {
        assertThat(queryField._isCustom()).isFalse();
    }

    @TypeTest
    void testIsTerm() {
        assertThat(queryField.isTerm()).isTrue();
    }


    @TypeTest
    void testTerm() {
        assertThat(queryField.term()).isExactlyInstanceOf(TermQueryParameter.class);
    }

    @TypeTest
    public void testSerialize() {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        JsonProvider provider = JsonpUtils.provider();

        JsonGenerator generator = provider.createGenerator(baos);

        queryField.serialize(generator, new JsonbJsonpMapper());

        generator.close();

        assertEquals(json, baos.toString());

    }


    @TypeTest
    public void testMissingVariantDeserialization() {

        String json = "{}";

        JsonParser parser = mapper.jsonProvider().createParser(new StringReader(json));

        MissingRequiredPropertyException missingRequiredPropertyException = assertThrows(MissingRequiredPropertyException.class, () -> {

            QueryParameter termProperty = QueryParameter._DESERIALIZER.deserialize(parser, new JsonbJsonpMapper());

            assertThat(termProperty.toString()).isNotNull();
        });

        assertThat(missingRequiredPropertyException.getMessage()).contains("Missing required property 'Builder.<variant kind>'");
    }

    @TypeTest
    void testDeserializer() {

        JsonpDeserializer<?> pairJsonpDeserializer =
                Pair.deserializer((k) -> JsonpDeserializer.stringDeserializer(), TermQueryParameter._DESERIALIZER);

        assertThat(pairJsonpDeserializer).isInstanceOf(JsonpDeserializer.class);
    }


    @TypeTest
    public void testDeserialization() {
        QueryParameter qf = fromJson(json, QueryParameter.class);
        assertThat(qf.toString()).isEqualTo(queryField.toString());
    }


    @Test
    void _kind() {
    }

    @Test
    void _get() {
    }

    @Test
    void of() {
    }

    @Test
    void _customKind() {
    }

    @Test
    void _isCustom() {
    }

    @Test
    void isTerm() {
    }

    @Test
    void term() {
    }

    @Test
    void serialize() {
    }

    @Test
    void testToString() {
    }

    @Test
    void setupQueryFieldDeserializer() {
    }
}