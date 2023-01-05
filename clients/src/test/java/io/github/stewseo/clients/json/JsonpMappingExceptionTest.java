package io.github.stewseo.clients.json;

import io.github.stewseo.clients.testcase.YelpFusionJsonTestCase;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessResult;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessResponse;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


public class JsonpMappingExceptionTest extends YelpFusionJsonTestCase {

    @Test
    public void testObjectAndArrayPath() {

        String json = "{" +
                "  \"took\" : 9," +
                "  \"timed_out\" : false," +
                "  \"_shards\" : {" +
                "    \"total\" : 1," +
                "    \"successful\" : 1," +
                "    \"skipped\" : 0," +
                "    \"failed\" : 0" +
                "  }," +
                "  \"hits\" : {" +
                "    \"total\" : {" +
                "      \"value\" : 1," +
                "      \"relation\" : \"eq\"" +
                "    }," +
                "    \"max_score\" : 1.0," +
                "    \"hits\" : [" +
                "      {" +
                "        \"_index\" : \"test\"," +
                "        \"_id\" : \"8aSerXUBs1w7Wkuj31zd\"," +
                "        \"_score\" : \"1.0\"," +
                "        \"_source\" : {" +
                "          \"foo\" : \"bar\"" +
                "        }" +
                "      }," +
                "      {" +
                "        \"_index\" : \"test\"," +
                "        \"_id\" : \"8aSerXUBs1w7Wkuj31zd\"," +
                "        \"_score\" : \"abc\"," + // <====== error here
                "        \"_source\" : {" +
                "          \"foo\" : \"bar\"" +
                "        }" +
                "      }" +
                "    ]" +
                "  }" +
                "}";

        JsonpMappingException e = assertThrows(JsonpMappingException.class, () -> {
            // withJson() will read values of the generic parameter type as JsonData
            SearchBusinessResponse.of(b -> b
                    .withJson(new StringReader(json))
            );
        });

        assertTrue(e.getMessage().contains("io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessResponse"));
        assertTrue(e.getMessage().contains("Unknown field 'took'"));

        // check path
        assertEquals("took", e.path());
    }

    @Test
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
        // Unknown field 'baz' (JSON path: properties['foo-bar'].baz) (in object at line no=1, column no=36, offset=35)

        JsonpMappingException e = assertThrows(JsonpMappingException.class, () -> fromJson(json, SearchBusinessResult.class));

        assertThat("properties").isEqualTo(e.path());

        String msg = e.getMessage();
        assertTrue(msg.contains("Unknown field 'properties'"));
        // Check look ahead position (see JsonpUtils.lookAheadFieldValue)
        assertTrue(msg.contains("(line no=1"));
    }
}