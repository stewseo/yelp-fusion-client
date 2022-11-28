package org.example.yelp.fusion.client.json.json.jackson;

import org.example.elasticsearch.client.json.*;
import org.example.elasticsearch.client.json.jackson.JacksonJsonpMapper;
import org.example.elasticsearch.client.util.ObjectBuilder;
import org.example.elasticsearch.client.util.WithJsonObjectBuilderBase;
import org.example.lowlevel.restclient.PrintUtils;
import org.example.yelp.fusion.client.business.model.Business;
import org.example.yelp.fusion.client.model.ModelTestCase;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.io.StringReader;
import java.util.function.Supplier;

public class JacksonMapperTest extends ModelTestCase {

    Logger logger = LoggerFactory.getLogger(JacksonMapperTest.class);

    @Test
    public void testCustomDeserializer() {
        // See https://github.com/elastic/elasticsearch-java/issues/120
        // Creating Jackson's parser factory from the object mapper makes this mapper available via JsonParser.getCodec()
        JsonpMapper jsonpMapper = new JacksonJsonpMapper();

        String json = "{\"_index\":\"foo\",\"_id\":\"1\",\"businesses\":{\"model\":\"Foo\",\"age\":42}}";


        JsonpDeserializer<TestData<Business>> deserializer = TestData.createTestDataDeserializer(Business._DESERIALIZER);

        jakarta.json.stream.JsonParser parser = mapper.jsonProvider().createParser(new StringReader(json));

        deserializer.deserialize(parser, mapper);

        logger.info(PrintUtils.red("JsonpDeserializer<TestData<Business>> deserializer: " + deserializer));
    }

    @JsonpDeserializable
    public static class TestData<TDocument> implements Serializable {

        private final TDocument businesses;
        private final String index;
        private final String id;


        private String id(){return this.id;}

        private String index(){return this.index;}

        private TDocument businesses(){return businesses;}
        private TestData(Builder<TDocument> builder) {
            this.id = builder.id;
            this.index = builder.index;
            this.businesses = builder.businesses;
        }


        public static <TDocument> JsonpDeserializer<TestData<TDocument>> createTestDataDeserializer(
                JsonpDeserializer<TDocument> tDocumentDeserializer) {
            return ObjectBuilderDeserializer.createForObject((Supplier<TestData.Builder<TDocument>>) TestData.Builder::new,
                    op -> TestData.setupTestDataDeserializer(op, tDocumentDeserializer));
        }

        public static final JsonpDeserializer<TestData<Object>> _DESERIALIZER = JsonpDeserializer
                .lazy(() -> createTestDataDeserializer(
                        new NamedDeserializer<>("org.example.clients:Deserializer:_global._types.TDocument")));

        protected static <TDocument> void setupTestDataDeserializer(ObjectDeserializer<TestData.Builder<TDocument>> op,
                                                                    JsonpDeserializer<TDocument> tDocumentDeserializer) {

            op.add(TestData.Builder::businesses, tDocumentDeserializer, "businesses");
            op.add(TestData.Builder::index, JsonpDeserializer.stringDeserializer(), "_index");
            op.add(TestData.Builder::id, JsonpDeserializer.stringDeserializer(), "_id");


        }


        @SuppressWarnings({"UnusedReturnValue", "unused"})
        public static class Builder<TDocument> extends WithJsonObjectBuilderBase<TestData.Builder<TDocument>>
                implements
                ObjectBuilder<TestData<TDocument>> {
            private TDocument businesses;
            private String index;
            private String id;

            public final TestData.Builder<TDocument> index(String value) {
                this.index = value;
                return this;
            }

            public final TestData.Builder<TDocument> id(String value) {
                this.id = value;
                return this;
            }

            public final TestData.Builder<TDocument> businesses(TDocument value) {
                this.businesses = value;
                return this;
            }

            @Override
            protected TestData.Builder<TDocument> self() {
                return this;
            }

            public TestData<TDocument> build() {
                _checkSingleUse();

                return new TestData<TDocument>(this);

            }
        }

    }
}