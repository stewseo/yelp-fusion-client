package io.github.stewseo.clients.yelpfusion.businesses.transactions;

import io.github.stewseo.clients.json.DeserializeFromJson;
import io.github.stewseo.clients.transport.Endpoint;
import io.github.stewseo.clients.yelpfusion._types.Event;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionRequestTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.apache.commons.io.IOUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SearchTransactionRequestTest extends YelpFusionRequestTestCase<SearchTransactionRequest>
        implements DeserializeFromJson {

    private final String category = "category";
    private final SearchTransactionRequest searchTransactionRequest = SearchTransactionRequest.of(s -> s
            .transaction_type("transactionType")
            .categories(String.valueOf(category))
            .latitude(latitude)
            .longitude(longitude)
            .location(location)
            .term(term)
            .price(price)
    );

    @Test
    public void testOf() {
        assertThat(searchTransactionRequest.transaction_type()).isEqualTo("transactionType");
        assertThat(searchTransactionRequest.categories()).isEqualTo(category);
        assertThat(searchTransactionRequest.location()).isEqualTo(location);
        assertThat(searchTransactionRequest.term()).isEqualTo(term);
    }

    private final JsonGenerator generator = generator();
    String expected = "{\"categories\":\"category\"," +
            "\"term\":\"term\"," +
            "\"transaction_type\":\"transactionType\"," +
            "\"latitude\":44.0,\"longitude\":-122.0," +
            "\"price\":3}";

    @Test
    public void testSerialize() {
        searchTransactionRequest.serialize(generator, mapper);
        assertThat(searchTransactionRequest.toString()).isEqualTo(expected);
    }

    @Test
    public void testSerializeInternal() {
        generator.writeStartObject();
        searchTransactionRequest.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        assertThat(searchTransactionRequest.toString()).isEqualTo(expected);
    }

    @Test
    public void testEndpoint() {

        assertThat(endpoint().id()).isEqualTo("v3/transactions");

        assertThat(endpoint().hasRequestBody()).isFalse();

        assertThat(endpoint().requestUrl(searchTransactionRequest)).isEqualTo("v3/transactions/transactionType/search");

        assertThat(endpoint().queryParameters(searchTransactionRequest).values().toString())
                .isEqualTo("[3, 44.0, term, location, category, transactionType, -122.0]");

        assertThat(endpoint().isError(200)).isFalse();
        assertThat(endpoint().headers(searchTransactionRequest).toString()).isEqualTo("{}");
        assertThat(endpoint().method(searchTransactionRequest)).isEqualTo("GET");

    }


    @Test
    public void testDeserialize() {
        assertThat(SearchTransactionRequest._DESERIALIZER.toString()).contains("io.github.stewseo.clients.json.LazyDeserializer");

    }

    @Test
    public void testDeserializer() {

        JsonParser parser = parser();

        Event searchBusinessRes = Event._DESERIALIZER.deserialize(parser, mapper);

        assertThat(searchBusinessRes).isNotNull();
    }

    @Override
    public JsonParser parser() {
        InputStream content = IOUtils.toInputStream(searchTransactionRequest.toString(), StandardCharsets.UTF_8);
        return mapper.jsonProvider().createParser(content);
    }

    @Override
    public Endpoint<SearchTransactionRequest, ?, ?> endpoint() {

        return SearchTransactionRequest._ENDPOINT;
    }
}