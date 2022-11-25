package org.example.yelp.fusion.client.business;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.example.elasticsearch.client.elasticsearch.core.Hit;
import org.example.elasticsearch.client.json.JsonpDeserializer;
import org.example.elasticsearch.client.json.JsonpMapper;
import org.example.elasticsearch.client.json.jackson.JacksonJsonpMapper;
import org.example.yelp.fusion.client.business.model.ModelTestCase;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CustomDeserializeTest extends ModelTestCase {

    Logger logger = LoggerFactory.getLogger(CustomDeserializeTest.class);

    protected JsonpMapper mapper;

    @Test
    void testDeserializer() {
        JsonpMapper jsonpMapper = new JacksonJsonpMapper();

        String json = "{\"_index\":\"foo\",\"_id\":\"1\",\"_source\":{\"model\":\"Foo\",\"age\":42}}";

        Hit<TestData> testDataHit = fromJson(json,
                Hit.createHitDeserializer(JsonpDeserializer.of(TestData.class)),
                jsonpMapper
        );
        logger.info("test data hit: " + testDataHit);

        TestData deserialized = testDataHit.source();
        logger.info("test data hit: " + deserialized);
        System.out.println(deserialized);

    }

    }

        @JsonDeserialize(using = TestData.TestDeserializer.class)
        class TestData {
            public String theModel;
            public int theAge;


            public static class TestDeserializer extends JsonDeserializer<TestData> {

                @Override
                public TestData deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
                    JsonNode node = jp.getCodec().readTree(jp);

                    TestData res = new TestData();

                    if (node.has("age")) {
                        System.out.println("adding: " + node.get("age"));
                        res.theAge = node.get("age").asInt();
                    }
                    if (node.has("model")) {
                        System.out.println("adding: " + node.get("model"));
                        res.theModel = node.get("model").asText();
                    }
                    return res;
                }

            }
        }


