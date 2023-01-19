package io.github.stewseo.clients.transport.endpoints;

import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapperBase;
import io.github.stewseo.clients.transport.Endpoint;
import io.github.stewseo.clients.transport.JsonEndpoint;
import io.github.stewseo.clients.transport.TransportTest;
import io.github.stewseo.clients.yelpfusion._types.ErrorResponse;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessesRequest;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessesResult;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchResponse;
import io.github.stewseo.clients.yelpfusion.businesses.transactions.SearchTransactionRequest;
import io.github.stewseo.clients.yelpfusion.testcases.RequestTestCase;
import jakarta.json.stream.JsonGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EndpointWithResponseMapperAttrTest implements RequestTestCase<SearchBusinessesRequest> {

    @SuppressWarnings("unchecked")
    @TransportTest
    void testEndpointWithResponseMapperAttribute() {

        JsonpDeserializer<SearchBusinessesRequest> resultDeserializer = JsonpMapperBase.findDeserializer(SearchBusinessesRequest.class);

        JsonEndpoint<SearchTransactionRequest, SearchResponse<SearchBusinessesResult>, ErrorResponse> endpoint =
                (JsonEndpoint<SearchTransactionRequest, SearchResponse<SearchBusinessesResult>, ErrorResponse>) SearchTransactionRequest._ENDPOINT;

        endpoint = new EndpointWithResponseMapperAttr<>(endpoint,
                "io.github.stewseo.clients.yelpfusion:Deserializer:_global.search.ResultT", resultDeserializer);

        assertThat(endpoint.id()).isEqualTo("v3/transactions");
        assertThat(endpoint.hasRequestBody()).isFalse();

    }

    @Override
    public JsonGenerator generator() {
        return null;
    }

    @Override
    public SearchBusinessesRequest of() {
        return null;
    }

    @TransportTest
    public void testBuilder() {

    }

    @TransportTest
    public void testOf() {

    }

    @Override
    public Endpoint<SearchBusinessesRequest, ?, ?> endpoint() {
        return SearchBusinessesRequest._ENDPOINT;
    }

    @TransportTest
    public void testEndpoint() {

    }

    @Test
    void testResponseDeserializer() {

    }
}