package io.github.stewseo.clients.yelpfusion.businesses.transactions;

import io.github.stewseo.clients._type.QueryParameter;
import io.github.stewseo.clients.json.DeserializeFromJson;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.transport.Endpoint;
import io.github.stewseo.clients.util.MissingRequiredPropertyException;
import io.github.stewseo.clients.yelpfusion.YelpFusionTest;
import io.github.stewseo.clients.yelpfusion.testcases.ModelTestCase;
import io.github.stewseo.clients.yelpfusion.testcases.RequestTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;

import java.util.List;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.CATEGORY;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.CITY;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.LATITUDE;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.LONGITUDE;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.PRICE;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.TERM;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.TRANSACTION_TYPE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SearchTransactionRequestTest extends ModelTestCase<SearchTransactionRequest>
        implements RequestTestCase<SearchTransactionRequest>, DeserializeFromJson {

    private final SearchTransactionRequest searchTransactionRequest = of();

    public SearchTransactionRequest of() {

        return SearchTransactionRequest.of(s -> s
                .transaction_type(TRANSACTION_TYPE)
                .categories(CATEGORY)
                .latitude(LATITUDE)
                .longitude(LONGITUDE)
                .location(CITY)
                .term(TERM)
                .price(PRICE)
        );

    }


    @YelpFusionTest
    public void testOf() {
        assertThat(searchTransactionRequest.transaction_type()).isEqualTo(TRANSACTION_TYPE);
        assertThat(searchTransactionRequest.categories()).isEqualTo(CATEGORY);
        assertThat(searchTransactionRequest.location()).isEqualTo(CITY);
        assertThat(searchTransactionRequest.price()).isEqualTo(PRICE);
        assertThat(searchTransactionRequest.term()).isEqualTo(List.of(TERM));
    }

    private final JsonGenerator generator = generator();

    String expected = "" +
            "SearchTransactionRequest: GET v3/transactions/"+TRANSACTION_TYPE+"/search?latitude=37.7829&location=sf&transaction_type="+TRANSACTION_TYPE+"&longitude=-122.4189 {\"term\":[\"term\"],\"location\":\"sf\",\"latitude\":37.7829,\"longitude\":-122.4189,\"categories\":{\"alias\":\"alias\"},\"price\":3,\"transaction_type\":\""+TRANSACTION_TYPE+"\"}";
    @YelpFusionTest
    public void testSerialize() {
        searchTransactionRequest.serialize(generator, mapper);
        assertThat(searchTransactionRequest.toString()).isEqualTo(expected);
    }

    @YelpFusionTest
    public void testSerializeInternal() {
        generator.writeStartObject();
        searchTransactionRequest.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        assertThat(searchTransactionRequest.toString()).isEqualTo(expected);
    }

    @Override
    public Endpoint<SearchTransactionRequest, ?, ?> endpoint() {

        return SearchTransactionRequest._ENDPOINT;
    }

    @YelpFusionTest
    public void testEndpoint() {

        assertThat(endpoint().id()).isEqualTo("v3/transactions");

        assertThat(endpoint().hasRequestBody()).isFalse();

        assertThat(endpoint().requestUrl(searchTransactionRequest)).isEqualTo(
                "v3/transactions/" + TRANSACTION_TYPE + "/search");

        assertThat(endpoint().queryParameters(searchTransactionRequest).values().toString())
                .isEqualTo("[37.7829, sf, "+TRANSACTION_TYPE+", -122.4189]");

        assertThat(endpoint().isError(200)).isFalse();
        assertThat(endpoint().headers(searchTransactionRequest).toString()).isEqualTo("{}");
        assertThat(endpoint().method(searchTransactionRequest)).isEqualTo("GET");

    }

    @YelpFusionTest
    public void testDeserialize() {

        JsonParser parser = parser();

        SearchTransactionRequest searchBusinessRes =
                SearchTransactionRequest._DESERIALIZER.deserialize(parser, mapper);

        assertThat(searchBusinessRes).isNotNull();
    }

    @YelpFusionTest
    public void testDeserializer() {

        assertThat(SearchTransactionRequest._DESERIALIZER.toString())
                .contains("io.github.stewseo.clients.json.LazyDeserializer");

        SearchTransactionRequest.Builder builder = new SearchTransactionRequest.Builder()
                .categories(CATEGORY)
                .latitude(LATITUDE)
                .location(CITY)
                .term(TERM)
                .price(PRICE);

        JsonpDeserializer<SearchTransactionRequest> _DESERIALIZER = ObjectBuilderDeserializer.lazy(
                SearchTransactionRequest.Builder::new,
                SearchTransactionRequest::setupSearchTransactionRequestDeserializer);

        assertThat(_DESERIALIZER.toString()).contains("io.github.stewseo.clients.json.LazyDeserializer");
    }

    @Override
    public JsonParser parser() {
        return parser(searchTransactionRequest);
    }

    @YelpFusionTest
    public void testBuilder() {


        MissingRequiredPropertyException missingRequiredPropertyException = assertThrows(MissingRequiredPropertyException.class, () -> {
            SearchTransactionRequest.Builder builderWithoutRequiredProperties = new SearchTransactionRequest.Builder()
                    .latitude(LATITUDE)
                    .longitude(LONGITUDE);
            SearchTransactionRequest._ENDPOINT.requestUrl(builderWithoutRequiredProperties.build());
        });

        assertThat(missingRequiredPropertyException.getPropertyName())
                .isEqualTo(QueryParameter.Kind.TransactionType.jsonValue());

        SearchTransactionRequest.Builder builderWithRequiredProperties = new SearchTransactionRequest.Builder()
                .transaction_type(TRANSACTION_TYPE)
                .latitude(LATITUDE)
                .longitude(LONGITUDE);

        SearchTransactionRequest.Builder self = builderWithRequiredProperties.self();

        assertThat(self).isEqualTo(builderWithRequiredProperties);

        SearchTransactionRequest searchTransactionReq = builderWithRequiredProperties.build();

        assertThat(searchTransactionReq.toString())
                .isEqualTo("" +
                        "SearchTransactionRequest: GET v3/transactions/delivery/search?latitude=37.7829&transaction_type=delivery&longitude=-122.4189 " +
                        "{" +
                            "\"latitude\":37.7829," +
                            "\"longitude\":-122.4189," +
                            "\"transaction_type\":\"delivery\"" +
                        "}"
                );
    }


}