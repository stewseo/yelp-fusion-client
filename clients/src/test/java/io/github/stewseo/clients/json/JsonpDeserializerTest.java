package io.github.stewseo.clients.json;

import io.github.stewseo.clients.json.testcases.ModelJsonTestCase;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessesRequest;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessesResult;
import jakarta.json.JsonValue;
import jakarta.json.stream.JsonParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.ALIAS;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.SEARCH_BUSINESS_RESULT;
import static org.assertj.core.api.Assertions.assertThat;

public class JsonpDeserializerTest extends ModelJsonTestCase {


    private SearchBusinessesResult searchBusinessResult;

    @BeforeEach
    void beforeEach() {
        searchBusinessResult = SEARCH_BUSINESS_RESULT;
    }

    @JsonTest
    public void testNullStringInArray() {

        JsonpDeserializer<List<String>> deser = JsonpDeserializer.arrayDeserializer(JsonpDeserializer.stringDeserializer());
        JsonParser parser = mapper.jsonProvider().createParser(new StringReader("[\"a\", null, \"b\"]"));
        List<String> list = deser.deserialize(parser, mapper);

        assertThat(list.get(0)).isEqualTo("a");
        assertThat(list.get(1)).isNull();
        assertThat(list.get(2)).isEqualTo("b");
    }

    @JsonTest
    public void testStringInArray() {

        JsonpDeserializer<List<String>> deser = JsonpDeserializer.arrayDeserializer(JsonpDeserializer.stringDeserializer());
        JsonParser parser = mapper.jsonProvider().createParser(new StringReader("[\"a\", \"b\"]"));
        List<String> list = deser.deserialize(parser, mapper);

        assertThat(list.get(0)).isEqualTo("a");
        assertThat(list.get(1)).isEqualTo("b");
    }

    @JsonTest
    void testOfType() {

        Type type = searchBusinessResult.getClass();

        JsonpDeserializer<SearchBusinessesResult> jsonpDeser = JsonpDeserializer.of(type);

        SearchBusinessesResult res = jsonpDeser.deserialize(parser(), mapper);

        assertThat(res).isExactlyInstanceOf(SearchBusinessesResult.class);

        JsonpDeserializer<SearchBusinessesResult> jsonpDes = JsonpDeserializer.of(SearchBusinessesResult.class);
        SearchBusinessesResult result = jsonpDes.deserialize(parser(), mapper);

        assertThat(result).isExactlyInstanceOf(SearchBusinessesResult.class);
    }


    @JsonTest
    void testOfAcceptedEventAndTriFunction() {


        JsonpDeserializer<SearchBusinessesResult> _DESERIALIZER = JsonpDeserializer.of(
                EnumSet.of(JsonParser.Event.START_OBJECT),
                (parser, mapper, event) -> {

                    String name = JsonpUtils.expectKeyName(parser, parser.next());

                    JsonpUtils.expectNextEvent(parser, JsonParser.Event.VALUE_NUMBER);

                    JsonpUtils.expectNextEvent(parser, JsonParser.Event.END_OBJECT);

                    return new SearchBusinessesResult.Builder().id(name).alias(ALIAS).build();
                });
    }


    @JsonTest
    void testStringDeserializer() {
        String json = "\"abc\"";
        JsonParser parser = mapper.jsonProvider().createParser(new StringReader(json));
        String res = JsonpDeserializer.stringDeserializer().deserialize(parser, mapper);
        assertThat(res).isEqualTo("abc");
    }

    @JsonTest
    void testIntegerDeserializer() {
        String json = "123";
        JsonParser parser = mapper.jsonProvider().createParser(new StringReader(json));
        int res = JsonpDeserializer.integerDeserializer().deserialize(parser, mapper);
        assertThat(res).isEqualTo(123);
    }

    @JsonTest
    void testBooleanDeserializer() {
        String json = "true";
        JsonParser parser = mapper.jsonProvider().createParser(new StringReader(json));
        boolean res = JsonpDeserializer.booleanDeserializer().deserialize(parser, mapper);
        assertThat(res).isEqualTo(true);
    }

    @JsonTest
    void testLongDeserializer() {
        String json = "123456789";

        JsonParser parser = mapper.jsonProvider().createParser(new StringReader(json));
        long res = JsonpDeserializer.longDeserializer().deserialize(parser, mapper);
        assertThat(res).isEqualTo(123456789L);
    }

    @JsonTest
    void testFloatDeserializer() {
        String json = "1.12";
        JsonParser parser = mapper.jsonProvider().createParser(new StringReader(json));
        float res = JsonpDeserializer.floatDeserializer().deserialize(parser, mapper);
        assertThat(res).isEqualTo(1.12f);
    }

    @JsonTest
    void doubleDeserializer() {
        String json = "1.0";
        JsonParser parser = mapper.jsonProvider().createParser(new StringReader(json));
        Double res = JsonpDeserializer.doubleDeserializer().deserialize(parser, mapper);
        assertThat(res).isEqualTo(1.0);
    }

    @JsonTest
    void doubleOrNullDeserializer() {
        String json = "1.0";
        JsonParser parser = mapper.jsonProvider().createParser(new StringReader(json));
        Double res = JsonpDeserializer.doubleOrNullDeserializer(Double.NaN).deserialize(parser, mapper);
        assertThat(res).isEqualTo(1.0);
    }

    @JsonTest
    void intOrNullDeserializer() {
        String json = "12345";
        JsonParser parser = mapper.jsonProvider().createParser(new StringReader(json));
        Integer res = JsonpDeserializer.intOrNullDeserializer(Integer.MIN_VALUE).deserialize(parser, mapper);
        assertThat(res).isEqualTo(12345);
    }

    @JsonTest
    void numberDeserializer() {
        String json = "12";
        JsonParser parser = mapper.jsonProvider().createParser(new StringReader(json));
        Number res = JsonpDeserializer.numberDeserializer().deserialize(parser, mapper);
        assertThat(res).isEqualTo(12);
    }

    @JsonTest
    void jsonValueDeserializer() {
        String json = "{}";
        JsonParser parser = mapper.jsonProvider().createParser(new StringReader(json));
        JsonValue res = JsonpDeserializer.jsonValueDeserializer().deserialize(parser, mapper);
        assertThat(res).isEqualTo(JsonValue.EMPTY_JSON_OBJECT);
    }

    @JsonTest
    void voidDeserializer() {
        JsonpDeserializer<Void> res = JsonpDeserializer.voidDeserializer();
        assertThat(res).isNotNull();
    }

    @JsonTest
    void stringMapDeserializer() {
        JsonpDeserializer<Map<String, SearchBusinessesRequest>> stringMapDeserializer =
                JsonpDeserializer.stringMapDeserializer(SearchBusinessesRequest._DESERIALIZER);
        assertThat(stringMapDeserializer).isNotNull();

    }

    public JsonParser parser() {
        return parser(searchBusinessResult);
    }
}
