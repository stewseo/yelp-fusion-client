package io.github.stewseo.clients.json;

import io.github.stewseo.clients.json.testcases.ModelJsonTestCase;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessesResult;
import jakarta.json.stream.JsonParser;

import java.io.StringReader;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ObjectBuilderDeserializerTest extends ModelJsonTestCase {

    @JsonTest
    public void testNullObjectValue() {
        // Should also accept null for optional values
        String json = "{ \"id\": null }";

        SearchBusinessesResult searchBusinessesResult = assertDoesNotThrow(
                () -> fromJson(json, SearchBusinessesResult.class));

        assertThat(searchBusinessesResult).isExactlyInstanceOf(SearchBusinessesResult.class);
        assertThat(searchBusinessesResult.id()).isNull();

    }

    @JsonTest
    public void testNullObjectValueInFunctionBuilder() {
        String json = """
                {
                        "id": null
                      }""";


        SearchBusinessesResult.Builder builder = new SearchBusinessesResult.Builder();
        builder.price(null);
        builder.withJson(new StringReader(json));
        SearchBusinessesResult searchBusinessesResult = builder.build();
        assertThat(searchBusinessesResult.id()).isNull();

    }

    @JsonTest
    public void testStringValue() {

        String json = "{ \"id\": \"abc1234\" }";

        SearchBusinessesResult searchBusinessesResult = assertDoesNotThrow(
                () -> fromJson(json, SearchBusinessesResult.class));

        assertThat(searchBusinessesResult.id()).isEqualTo("abc1234");
    }

    @JsonTest
    public void testIntegerValue() {

        String json = "{ \"distance\": 1.0 }";

        SearchBusinessesResult searchBusinessesResult = assertDoesNotThrow(
                () -> fromJson(json, SearchBusinessesResult.class));

        assertThat(searchBusinessesResult.distance()).isEqualTo(1.0);
    }

    @Override
    public JsonParser parser() {
        return null;
    }
}