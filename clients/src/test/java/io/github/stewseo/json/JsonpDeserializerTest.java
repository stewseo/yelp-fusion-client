package io.github.stewseo.json;

import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.yelpfusion.testcases.FunctionalTestCase;
import jakarta.json.stream.JsonParser;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JsonpDeserializerTest extends FunctionalTestCase {

    @Test
    public void testNullStringInArray() {
        JsonpDeserializer<List<String>> deser = JsonpDeserializer.arrayDeserializer(JsonpDeserializer.stringDeserializer());
        JsonpMapper mapper = testJson.mapper;
        JsonParser parser = mapper.jsonProvider().createParser(new StringReader("[\"a\", null, \"b\"]"));
        List<String> list = deser.deserialize(parser, mapper);

        assertThat("a").isEqualTo(list.get(0));
        assertThat(list.get(1)).isNull();
        assertThat("b").isEqualTo(list.get(2));
    }
}
