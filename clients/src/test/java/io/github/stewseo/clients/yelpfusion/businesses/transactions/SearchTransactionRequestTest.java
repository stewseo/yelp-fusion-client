package io.github.stewseo.clients.yelpfusion.businesses.transactions;

import io.github.stewseo.clients.json.DeserializeFromJson;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.transport.Endpoint;
import io.github.stewseo.clients.yelpfusion.YelpFusionTest;
import io.github.stewseo.clients.yelpfusion._types.Event;
import io.github.stewseo.clients.yelpfusion.testcases.ModelTestCase;
import io.github.stewseo.clients.yelpfusion.testcases.RequestTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.LATITUDE;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.LONGITUDE;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.PRICE;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.TERM;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SearchTransactionRequestTest extends ModelTestCase<SearchTransactionRequest>
        implements RequestTestCase<SearchTransactionRequest>, DeserializeFromJson {

    private final String category = "category";
    private final String location = "locationValue";
    private final SearchTransactionRequest searchTransactionRequest = of();

    public SearchTransactionRequest of() {

        return SearchTransactionRequest.of(s -> s
                .transaction_type("transactionType")
                .categories(category)
                .latitude(LATITUDE)
                .longitude(LONGITUDE)
                .location(location)
                .term(TERM)
                .price(PRICE)
        );

    }


    @YelpFusionTest
    public void testOf() {
        assertThat(searchTransactionRequest.transaction_type()).isEqualTo("transactionType");
        assertThat(searchTransactionRequest.categories()).isEqualTo(category);
        assertThat(searchTransactionRequest.location()).isEqualTo(location);
        assertThat(searchTransactionRequest.price()).isEqualTo(PRICE);
        assertThat(searchTransactionRequest.term()).isEqualTo(TERM);
    }

    private final JsonGenerator generator = generator();

    String expected = "{\"categories\":\"category\",\"term\":\"term\",\"transaction_type\":\"transactionType\",\"latitude\":37.7829,\"longitude\":-122.4189,\"price\":3}";

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

        assertThat(endpoint().requestUrl(searchTransactionRequest)).isEqualTo("v3/transactions/transactionType/search");

        assertThat(endpoint().queryParameters(searchTransactionRequest).values().toString())
                .isEqualTo("[3, 37.7829, term, locationValue, category, transactionType, -122.4189]");

        assertThat(endpoint().isError(200)).isFalse();
        assertThat(endpoint().headers(searchTransactionRequest).toString()).isEqualTo("{}");
        assertThat(endpoint().method(searchTransactionRequest)).isEqualTo("GET");

    }

    @YelpFusionTest
    public void testDeserialize() {

        JsonParser parser = parser();

        Event searchBusinessRes = Event._DESERIALIZER.deserialize(parser, mapper);

        assertThat(searchBusinessRes).isNotNull();
    }

    @YelpFusionTest
    public void testDeserializer() {

        assertThat(SearchTransactionRequest._DESERIALIZER.toString())
                .contains("io.github.stewseo.clients.json.LazyDeserializer");

        SearchTransactionRequest.Builder builder = new SearchTransactionRequest.Builder()
                .categories(category)
                .latitude(LATITUDE)
                .location(location)
                .term(TERM)
                .price(PRICE);

        JsonpDeserializer<SearchTransactionRequest> _DESERIALIZER = ObjectBuilderDeserializer.lazy(
                SearchTransactionRequest.Builder::new,
                SearchTransactionRequest::setupSearchRequestDeserializer);

        assertThat(_DESERIALIZER.toString()).contains("io.github.stewseo.clients.json.LazyDeserializer");
    }

    @Override
    public JsonParser parser() {
        return parser(searchTransactionRequest);
    }

    @YelpFusionTest
    public void testBuilder() {

        SearchTransactionRequest.Builder builder = new SearchTransactionRequest.Builder().term("termValue");

        SearchTransactionRequest.Builder self = builder.self();

        assertThat(self).isEqualTo(builder);

        SearchTransactionRequest searchTransactionReq = builder.build();

        assertThat(searchTransactionReq.toString()).isEqualTo("{\"term\":\"termValue\"}");
    }


}