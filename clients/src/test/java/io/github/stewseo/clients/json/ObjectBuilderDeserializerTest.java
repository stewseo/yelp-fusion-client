package io.github.stewseo.clients.json;

import io.github.stewseo.clients.json.testcases.TestJson;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessResult;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

public class ObjectBuilderDeserializerTest extends TestJson {

    @Test
    public void testNullObjectValue() {
        // Should also accept null for optional values
        String json = "{ \"price\": null }";
        fromJson(json, SearchBusinessResult.class);
    }

    @Test
    public void testNullObjectValueInFunctionBuilder() {
        String json = """
                {
                        "price": null
                      }""";

        SearchBusinessResult.Builder builder = new SearchBusinessResult.Builder();
        builder.price(null);
        builder.withJson(new StringReader(json));
        builder.build();
    }

    @Test
    public void testStringValue() {

        String json = "{ \"id\": \"abc1234\" }";
        fromJson(json, SearchBusinessResult.class);
    }

    @Test
    public void testIntegerValue() {

        String json = "{ \"rating\": 4.5 }";
        fromJson(json, SearchBusinessResult.class);
    }

}