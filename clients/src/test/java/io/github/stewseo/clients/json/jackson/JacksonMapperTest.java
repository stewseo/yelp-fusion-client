package io.github.stewseo.clients.json.jackson;

import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.JsonpDeserializer;
import co.elastic.clients.json.JsonpMapper;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.github.stewseo.clients.json.JsonTest;

import java.io.IOException;
import java.io.StringReader;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class JacksonMapperTest {

    @JsonTest
    public void testCustomDeserializer() {
        // See https://github.com/elastic/elasticsearch-java/issues/120

        String json = "{\"_index\":\"foo\",\"_id\":\"1\",\"_source\":{\"model\":\"Foo\",\"age\":42}}";

        JsonpDeserializer<Hit<TestData>> deserializer = Hit.createHitDeserializer(JsonpDeserializer.of(TestData.class));

        JsonpMapper mapper = new JacksonJsonpMapper();

        jakarta.json.stream.JsonParser parser = mapper.jsonProvider().createParser(new StringReader(json));

        Hit<TestData> testDataHit = deserializer.deserialize(parser, mapper);
        TestData data = testDataHit.source();
        assertThat("Foo").isEqualTo(data.theModel);
        assertThat(42).isEqualTo(data.theAge);
    }


    @JsonDeserialize(using = TestData.TestDeserializer.class)
    public static class TestData {
        public String theModel;
        public int theAge;

        public static class TestDeserializer extends JsonDeserializer<TestData> {

            @Override
            public TestData deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
                JsonNode node = jp.getCodec().readTree(jp);

                TestData res = new TestData();
                if (node.has("age")) {
                    res.theAge = node.get("age").asInt();
                }
                if (node.has("model")) {
                    res.theModel = node.get("model").asText();
                }
                return res;
            }
        }
    }
}