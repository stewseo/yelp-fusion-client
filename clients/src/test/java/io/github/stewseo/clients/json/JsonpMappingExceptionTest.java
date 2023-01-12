package io.github.stewseo.clients.json;

import io.github.stewseo.clients.json.testcases.TestJson;
import io.github.stewseo.clients.util.MissingRequiredPropertyException;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessResponse;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessResult;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class JsonpMappingExceptionTest extends TestJson {

    private final String json = "{" +
            "   \"id\":\"KscSF2Bnwa_wlg1MbdFjvQ\"," +
            "   \"alias\":\"tre-sorelle-new-york\"," +
            "   \"name\":\"Tre Sorelle\"," +
            "   \"image_url\":\"https://s3-media4.fl.yelpcdn.com/bphoto/A8tp7PU_6SaKi3G2AP83WQ/o.jpg\"," +
            "   \"url\":\"https://www.yelp.com/biz/tre-sorelle-new-york?adjust_creative=ccj3y1UCH-4gsdWSMdEDOw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=ccj3y1UCH-4gsdWSMdEDOw\"," +
            "   \"phone\":\"+12126191080\"," +
            "   \"price\":\"$$\"," +
            "   \"display_phone\":\"(212) 619-1080\"," +
            "   \"is_closed\":false," +
            "   \"distance\":78.46171453078941," +
            "   \"rating\":3.5," +
            "   \"review_count\":186," +
            "   \"invalid_field\":\"invalid\"," +
            "   \"transactions\":[\"delivery\",\"pickup\"]," +

            "   \"location\":{" +
            "       \"address1\":\"61 Reade St\",\"address2\":\"\",\"address3\":\"\",\"city\":\"New York\"," +
            "       \"zip_code\":\"10007\",\"country\":\"US\",\"state\":\"NY\",\"display_address\":[\"61 Reade St\",\"New York, NY 10007\"]}," +
            "   \"coordinates\":{\"latitude\":40.7149022072554,\"longitude\":-74.0064939111471}," +
            "   \"categories\":" +
            "       [" +
            "           {\"" +
            "               alias\":\"italian\",\"title\":\"Italian\"" +
            "           }," +
            "           {\"" +
            "               alias\":\"pizza\",\"title\":\"Pizza\"" +
            "           }" +
            "   ]" +
            "}";


    @Test
    public void testObjectAndArrayPath() {

        JsonpMappingException e = assertThrows(JsonpMappingException.class, () -> {
            // withJson() will read values of the generic parameter type as JsonData
            SearchBusinessResult.of(b -> b
                    .withJson(new StringReader(json))
            );
        });

        assertTrue(e.getMessage().contains("io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessResult"));

        assertTrue(e.getMessage().contains("Unknown field 'invalid_field'"));

        // check path
        assertEquals("invalid_field", e.path());
    }

    @Test
    public void testMissingPropertiesException() {

        // Error deserializing co.elastic.clients.elasticsearch._types.mapping.TextProperty:
        // Unknown field 'baz' (JSON path: properties['foo-bar'].baz) (in object at line no=1, column no=36, offset=35)

        MissingRequiredPropertyException e = assertThrows(MissingRequiredPropertyException.class, () -> fromJson(json, SearchBusinessResponse.class));

        assertThat("businesses").isEqualTo(e.getPropertyName());

        String msg = e.getMessage();

        assertThat(msg).contains("Missing required property 'SearchBusinessResponse");
    }

    @Test
    void from() {
    }

    @Test
    void testFrom() {
    }

    @Test
    void getMessage() {
    }

    @Test
    void path() {
    }

    @Test
    void testPath() {
    }

    @Test
    void prepend() {
    }

    @Test
    void testPrepend() {
    }
}