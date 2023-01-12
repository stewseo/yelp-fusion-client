package io.github.stewseo.clients.json;

import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessResult;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchResponse;
import io.github.stewseo.clients.yelpfusion.testcases.ModelTestCase;
import jakarta.json.stream.JsonParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.EXPECTED_SEARCH_RESPONSE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JsonpDeserializerTest extends ModelTestCase<SearchResponse<SearchBusinessResult>> {

    @Test
    public void testNullStringInArray() {

        JsonpDeserializer<List<String>> deser = JsonpDeserializer.arrayDeserializer(JsonpDeserializer.stringDeserializer());
        JsonpMapper jsonpMapper = mapper;
        JsonParser parser = parser(EXPECTED_SEARCH_RESPONSE);
        List<String> list = deser.deserialize(parser, mapper);

        assertThat("a").isEqualTo(list.get(0));
        assertThat(list.get(1)).isNull();
        assertThat("b").isEqualTo(list.get(2));
    }

    @Override
    public SearchResponse<SearchBusinessResult> of() {
        return EXPECTED_SEARCH_RESPONSE;
    }

    @Test
    public void testBuilder() {

    }

    @Test
    public void testOf() {
    }

    @Test
    void testOf1() {
    }

    @Test
    void lazy() {
    }

    @Test
    void fixedValue() {
    }

    @Test
    void emptyObject() {
    }

    @Test
    void stringDeserializer() {
    }

    @Test
    void integerDeserializer() {
    }

    @Test
    void booleanDeserializer() {
    }

    @Test
    void longDeserializer() {
    }

    @Test
    void floatDeserializer() {
    }

    @Test
    void doubleDeserializer() {
    }

    @Test
    void doubleOrNullDeserializer() {
    }

    @Test
    void intOrNullDeserializer() {
    }

    @Test
    void numberDeserializer() {
    }

    @Test
    void jsonValueDeserializer() {
    }

    @Test
    void voidDeserializer() {
    }

    @Test
    void arrayDeserializer() {
    }

    @Test
    void stringMapDeserializer() {
    }

    @Test
    void enumMapDeserializer() {
    }

    @Test
    void testJsonString() {
    }

    @Test
    void testNativeEvents() {
    }

    @Test
    void testAcceptedEvents() {
    }

    @Test
    void testAccepts() {
    }

    @Test
    void deserialize() {
    }

    @Test
    void testDeserialize() {
    }

    @Test
    public void testSerialize() {

    }

    @Test
    public void testSerializeInternal() {

    }
}
