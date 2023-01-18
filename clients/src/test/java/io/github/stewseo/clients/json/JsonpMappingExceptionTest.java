package io.github.stewseo.clients.json;

import co.elastic.clients.elasticsearch._types.mapping.TypeMapping;
import io.github.stewseo.clients.json.testcases.ModelJsonTestCase;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessesResult;
import jakarta.json.JsonException;
import jakarta.json.stream.JsonParser;
import org.opentest4j.AssertionFailedError;

import java.io.StringReader;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonpMappingExceptionTest extends ModelJsonTestCase {

    private final String json = "" +
            "{" +
                "\"id\":\"KscSF2Bnwa_wlg1MbdFjvQ\"," +
                "\"alias\":\"tre-sorelle-new-york\"," +
                "\"name\":\"Tre Sorelle\"," +
                "\"image_url\":\"https://s3-media4.fl.yelpcdn.com/bphoto/A8tp7PU_6SaKi3G2AP83WQ/o.jpg\"," +
                "\"url\":\"https://www.yelp.com/biz/tre-sorelle-new-york?adjust_creative=ccj3y1UCH-4gsdWSMdEDOw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=ccj3y1UCH-4gsdWSMdEDOw\"," +
                "\"phone\":\"+12126191080\"," +
                "\"price\":\"$$\"," +
                "\"display_phone\":\"(212) 619-1080\"," +
                "\"is_closed\":false," +
                "\"distance\":78.46171453078941," +
                "\"rating\":3.5," +
                "\"review_count\":186," +
                "\"invalid_field\":\"invalid\"," +
                "\"transactions\":" +
                    "[" +
                        "\"delivery\",\"pickup\"" +
                    "]," +

                "\"location\":" +
                    "{" +
                "\"address1\":\"61 Reade St\"," +
                "\"address2\":\"\"," +
                "\"address3\":\"\"," +
                "\"city\":\"New York\"," +
                "\"zip_code\":\"10007\"," +
                "\"country\":\"US\"," +
                "\"state\":\"NY\"," +
                "\"display_address\":" +
                    "[" +
                        "\"61 Reade St\",\"New York, NY 10007\"" +
                    "]" +
                "}," +
                "\"coordinates\":" +
                    "{" +
                        "\"latitude\":40.7149022072554," +
                        "\"longitude\":-74.0064939111471" +
                    "}," +
                "\"categories\":" +
                    "[" +
                        "{\"" +
                            "alias\":\"italian\",\"title\":\"Italian\"" +
                        "}," +
                        "{\"" +
                            "alias\":\"pizza\",\"title\":\"Pizza\"" +
                        "}" +
                    "]" +
            "}";
    private final JsonpMappingException jsonpMappingException = assertThrows(JsonpMappingException.class, () -> {
        // withJson() will read values of the generic parameter type as JsonData
        SearchBusinessesResult.of(b -> b
                .withJson(new StringReader(json))
        );
    });

    private final String jsonArray = "{" +
            "\"categories\":" +
                "[" +
                    "{\"" +
                        "alias\":\"italian\",\"title\":\"Italian\"" +
                    "}," +
                    "{\"" +
                        "invalid_field\":\"pizza\",\"title\":\"Pizza\"" +
                    "}" +
                "]" +
            "}";

    @JsonTest
    public void testObjectPath() {

        assertThat(jsonpMappingException.getMessage())
                .contains("Unknown field 'name' (JSON path: name)");

        // check path
        assertThat(jsonpMappingException.path()).contains("name");
    }
    @JsonTest
    public void testArrayPath() {

        JsonpMappingException jsonpMappingException = assertThrows(JsonpMappingException.class, () -> {
            // withJson() will read values of the generic parameter type as JsonData
            SearchBusinessesResult.of(b -> b
                    .withJson(new StringReader(jsonArray))
            );
        });
        assertThat(jsonpMappingException.getMessage())
                .contains("Error deserializing io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessesResult");

        // check path
        assertThat(jsonpMappingException.path()).contains("categories");
    }

    @JsonTest
    public void testLookAhead() {

        String json =
                "{" +
                        "  \"properties\": { " +
                        "    \"foo-bar\": {" +
                        "        \"type\": \"text\"," +
                        "        \"baz\": false" +
                        "    }" +
                        "  }" +
                        "}";

        // Error deserializing co.elastic.clients.elasticsearch._types.mapping.TextProperty:
        // Unknown field 'baz' (JSON path: properties['foo-bar'].baz) (...line no=1, column no=36, offset=35)


        JsonException e = assertThrows(JsonException.class, () -> {
            fromJson(json, TypeMapping.class);
        });

        // Check escaping of non identifier path elements and path from map elements
        assertThat(e.getMessage()).isEqualTo("Jackson exception");

    }
    @JsonTest
    void testPrepend() {
        jsonpMappingException.prepend("ref", "name");
    }

    @JsonTest
    void testFrom() {

        JsonParser parser = parser();

        AssertionFailedError ex = assertThrows(AssertionFailedError.class, () -> assertThat(1).isEqualTo(2));

        JsonpMappingException jsonMappingException = JsonpMappingException.from(ex, 1, parser);

        assertThat(jsonpMappingException).isExactlyInstanceOf(JsonpMappingException.class);
    }

    @JsonTest
    void testPath() {
        assertThat(jsonpMappingException.path()).isOfAnyClassIn(String.class);
    }
    @JsonTest
    void testPathWhereExceptionHappened() {
        jsonpMappingException.path();
    }

}