package io.github.stewseo.clients.json;

import io.github.stewseo.clients.json.testcases.TestJson;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessesResult;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class JsonpMappingExceptionTest extends TestJson {

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
                .contains("Unknown field 'review_count' (JSON path: review_count) (line no=1, column no=496, offset=495)");

        // check path
        assertThat(jsonpMappingException.path()).contains("review_count");
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
                .contains("Error deserializing io.github.stewseo.clients.yelpfusion._types.Category");

        // check path
        assertThat(jsonpMappingException.path()).contains("invalid_field");
    }


    @JsonTest
    void testPrepend() {
    }
    @JsonTest
    void testPrepend1() {
    }

    @JsonTest
    void testPrepend2() {
    }

    @JsonTest
    void testFrom() {
    }

    @JsonTest
    void testPath() {
    }
}