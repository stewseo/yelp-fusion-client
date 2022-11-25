package org.example.yelp.fusion.client.business;


import com.fasterxml.jackson.core.JsonFactory;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.example.elasticsearch.client.elasticsearch.core.Hit;
import org.example.elasticsearch.client.json.JsonpDeserializer;
import org.example.elasticsearch.client.json.JsonpMapper;
import org.example.elasticsearch.client.json.jackson.JacksonJsonpMapper;



import com.fasterxml.jackson.core.JsonParser;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.example.lowlevel.restclient.HttpClientImpl;
import org.example.lowlevel.restclient.RestClient;
import org.example.lowlevel.restclient.RestClientBuilder;
import org.example.yelp.fusion.client.YelpFusionClient;
import org.example.yelp.fusion.client.business.model.ModelTestCase;
import org.example.yelp.fusion.client.business.search.Business;
import org.example.yelp.fusion.client.transport.YelpRestTransport;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringReader;
import java.net.http.HttpClient;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

@JsonDeserialize(using = ResponseBodyTest.TestData.TestDeserializer.class)
public class ResponseBodyTest extends ModelTestCase {

    static YelpFusionClient yelpClient;
    private final static Logger logger = LoggerFactory.getLogger(ResponseBodyTest.class);
    static JacksonJsonpMapper mapper;


    // create YelpSearchResponse object
    @BeforeAll
    static void beforeAll() {
        mapper = new JacksonJsonpMapper();
        String yelpFusionHost = "api.yelp.com";
        int port = 443;

        RestClientBuilder restBuilder = RestClient.builder(new HttpHost("api.yelp.com/v3"))
                .setMetaHeaderEnabled(false)
                .setHttpClient(new HttpClientImpl(
                                HttpClient.newBuilder()
                                        .followRedirects(HttpClient.Redirect.NORMAL)
                                        .connectTimeout(Duration.ofSeconds(10))
                                        .version(HttpClient.Version.HTTP_2)
                                        .build()
                        )
                );

        Header[] defaultHeaders = {new BasicHeader("Authorization", "Bearer " + System.getenv("YELP_API_KEY"))};

        restBuilder.setDefaultHeaders(defaultHeaders);

        YelpRestTransport yelpTransport;

        try (RestClient restClient = restBuilder.build()) {

            yelpTransport = new YelpRestTransport(restClient, mapper);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        yelpClient = new YelpFusionClient(yelpTransport);

    }


    @Test
    void jsonParserTest() {

        String id = "wu3w6IlUct9OvYmYXDMGJA";
        BusinessDetailsResponse<Business> response = yelpClient.businessDetails(s -> s
                        .id(id),
                Business.class);

        assertThat(response).isNotNull();
        logger.info("response");
        assertThat(response.total()).isNotNull();
        logger.info("response total: " + response.total());
        assertThat(response.hits()).isNotNull();
        logger.info("response total: " + response.total());

    }


    private static final String json = "{ 'foo': 'fooValue', 'bar': { 'baz': 1}, 'quux': [true] }".replace('\'', '"');


    @Test
    public void testCustomDeserializer() throws IOException {

        JsonpMapper jsonpMapper = new JacksonJsonpMapper();

        String json = "{\"_index\":\"foo\",\"_id\":\"1\",\"_source\":{\"model\":\"Foo\",\"age\":42}}";

        JsonParser jsonParser = new JsonFactory().createParser(new StringReader(json));
//        JsonParser parser = mapper.jsonProvider().createParser(new StringReader(json));

        Hit<TestData> testDataHit = fromJson(json,
                Hit.createHitDeserializer(JsonpDeserializer.of(TestData.class)),
                jsonpMapper
        );

        TestData data = testDataHit.source();
        assertEquals("Foo", data.theModel);
        assertEquals(42, data.theAge);
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
                    System.out.println("the node has The age");
                    res.theAge = node.get("age").asInt();
                }
                if (node.has("model")) {
                    System.out.println("the node has The age");
                    res.theModel = node.get("model").asText();
                }
                return res;
            }
        }
    }




//    @Test
//    void responseBodyTest() {
//        JsonEndpoint<?, ResponseT, ?> jsonEndpoint = (JsonEndpoint<?, ResponseT, ?>) endpoint;
//        // Successful response
//        ResponseT response = null;
//        JsonpDeserializer<ResponseT> responseParser = jsonEndpoint.responseDeserializer();
//        if (responseParser != null) {
//            // Expecting a body
//            if (entity == null) {
//                throw new TransportException(
//                        "Expecting a response body, but none was sent",
//                        endpoint.id(), new ResponseException(clientResp)
//                );
//            }
//            InputStream content = entity.getContent();
//            try (JsonParser parser = mapper.jsonProvider().createParser(content)) {
//                response = responseParser.deserialize(parser, mapper);
//            }
//    }
}
