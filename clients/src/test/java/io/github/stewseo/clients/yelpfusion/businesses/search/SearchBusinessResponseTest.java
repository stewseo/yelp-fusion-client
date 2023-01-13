//package io.github.stewseo.clients.yelpfusion.businesses.searchBusinesses;
//
//import io.github.stewseo.clients.yelpfusion.testcases.ModelTestCase;
//import jakarta.json.stream.JsonGenerator;
//import jakarta.json.stream.JsonParser;
//import org.assertj.core.api.AssertionsForClassTypes;
//import org.junit.jupiter.api.Test;
//
//import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.EXPECTED_SEARCH_BUSINESS_RESULT;
//import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.REGION;
//import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.TOTAL;
//import static org.assertj.core.api.Assertions.assertThat;
//
//public class SearchBusinessResponseTest extends ModelTestCase<SearchBusinessResponse> {
//
//    @Override
//    public SearchBusinessResponse of() {
//        return SearchBusinessResponse.of(b -> b
//                .total(TOTAL)
//                .businesses(EXPECTED_SEARCH_BUSINESS_RESULT)
//                .region(REGION)
//        );
//    }
//
//    private final SearchBusinessResponse searchBusinessResponse = of();
//
//    private final String expected = "{\"businesses\":[{\"id\":\"id\"," +
//            "\"rating\":4.5," +
//            "\"center\":{\"latitude\":37.7829,\"longitude\":-122.4189}}],\"total\":1," +
//            "\"region\":{\"center\":{\"latitude\":37.7829,\"longitude\":-122.4189}}}";
//    @Test
//    public void testBuilder() {
//
//        SearchBusinessResponse.Builder builder = new SearchBusinessResponse.Builder()
//                .total(TOTAL)
//                .businesses(EXPECTED_SEARCH_BUSINESS_RESULT)
//                .region(REGION);
//
//
//        SearchBusinessResponse.Builder self = builder.self();
//
//        assertThat(self).isEqualTo(builder);
//
//        SearchBusinessResponse response = builder.build();
//
//        assertThat(response.toString()).isEqualTo(expected);
//    }
//    private final JsonGenerator generator = generator();
//
//    @Test
//    public void testOf() {
//        assertThat(searchBusinessResponse.businesses().get(0)).isEqualTo(EXPECTED_SEARCH_BUSINESS_RESULT);
//        AssertionsForClassTypes.assertThat(searchBusinessResponse.region()).isEqualTo(REGION);
//        AssertionsForClassTypes.assertThat(searchBusinessResponse.total()).isEqualTo(TOTAL);
//    }
//
//    @Test
//    public void testSerialize() {
//        searchBusinessResponse.serialize(generator, mapper);
//        assertThat(searchBusinessResponse.toString()).isEqualTo(expected);
//    }
//
//    @Test
//    public void testSerializeInternal() {
//        generator.writeStartObject();
//        searchBusinessResponse.serializeInternal(generator, mapper);
//        generator.writeEnd().close();
//        assertThat(searchBusinessResponse.toString()).isEqualTo(expected);
//    }
//
//    public JsonParser parser() {
//        return parser(searchBusinessResponse);
//    }
//
//    @Test
//    public void testDeserialize() {
//        AssertionsForClassTypes.assertThat(SearchBusinessResponse._DESERIALIZER.toString()).contains("io.github.stewseo.clients.json.LazyDeserializer");
//
//    }
//
//    @Test
//    public void testDeserializer() {
//
//        JsonParser parser = parser();
//
//        SearchBusinessResponse businessMatchRes = SearchBusinessResponse._DESERIALIZER.deserialize(parser, mapper);
//
//        AssertionsForClassTypes.assertThat(businessMatchRes.toString()).isEqualTo(expected);
//    }
//}
