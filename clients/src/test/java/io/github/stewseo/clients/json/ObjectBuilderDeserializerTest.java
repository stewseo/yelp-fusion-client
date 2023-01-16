package io.github.stewseo.clients.json;

import io.github.stewseo.clients.json.testcases.TestJson;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessesResult;

import java.io.StringReader;


public class ObjectBuilderDeserializerTest extends TestJson {

    @JsonTest
    public void testNullObjectValue() {
        // Should also accept null for optional values
        String json = "{ \"price\": null }";
        fromJson(json, SearchBusinessesResult.class);
    }

    @JsonTest
    public void testNullObjectValueInFunctionBuilder() {
        String json = """
                {
                        "price": null
                      }""";

        SearchBusinessesResult.Builder builder = new SearchBusinessesResult.Builder();
        builder.price(null);
        builder.withJson(new StringReader(json));
        builder.build();
    }

    @JsonTest
    public void testStringValue() {

        String json = "{ \"id\": \"abc1234\" }";
        fromJson(json, SearchBusinessesResult.class);
    }

    @JsonTest
    public void testIntegerValue() {

        String json = "{ \"rating\": 4.5 }";
        fromJson(json, SearchBusinessesResult.class);
    }

}