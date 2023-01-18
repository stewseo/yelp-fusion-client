package io.github.stewseo.clients.json.jackson;

import io.github.stewseo.clients.json.JsonTest;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpUtils;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import jakarta.json.stream.JsonParser;
import org.junit.jupiter.api.Assertions;

import java.io.StringReader;
import java.util.Map;

public class JsonValueParserTest extends Assertions {

    public static class Data {
        public Map<String, Object> data;
    }

    @JsonTest
    public void testFloatsShouldDeserializeAsFloats() throws Exception {
        // When using Jackson to target a map of objects, values with a decimal separator
        // should deserialize as a double even if they fit in an int or long.
        // See https://github.com/elastic/elasticsearch-java/issues/156

        String json = "{\"data\": {\"value\": 1.4778125E7, \"value2\": 1.4778125E7 }}";
        JsonpMapper mapper = new JacksonJsonpMapper();

        {
            JsonParser parser = mapper.jsonProvider().createParser(new StringReader(json));
            Data data = mapper.deserialize(parser, Data.class);

            Double d = (Double)data.data.get("value");
            assertEquals(1.4778125E7, d, 0.001);
        }

        {
            // Test with buffering used in union resolution
            JsonParser parser = mapper.jsonProvider().createParser(new StringReader(json));
            parser.next();
            JsonObject object = parser.getObject();

            // Test equals/hashcode
            JsonValue v = object.getJsonObject("data").get("value");
            JsonValue v2 = object.getJsonObject("data").get("value2");

            assertEquals(v.hashCode(), v2.hashCode());
            assertEquals(v, v2);

            parser = JsonpUtils.objectParser(object, mapper);
            Data data = mapper.deserialize(parser, Data.class);

            Double d = (Double)data.data.get("value");
            assertEquals(1.4778125E7, d, 0.001);
        }

    }
}