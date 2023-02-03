package io.github.stewseo.clients.transport.endpoints;

import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapperBase;
import io.github.stewseo.clients.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.clients.json.testcases.ModelJsonTestCase;
import io.github.stewseo.clients.transport.JsonEndpoint;
import io.github.stewseo.clients.transport.TransportTest;
import io.github.stewseo.clients.yelpfusion._types.ErrorResponse;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessesRequest;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessesResult;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchResponse;
import io.github.stewseo.clients.yelpfusion.businesses.transactions.SearchTransactionRequest;
import jakarta.json.stream.JsonParser;

import static org.assertj.core.api.Assertions.assertThat;

class EndpointWithResponseMapperAttrTest extends ModelJsonTestCase {

    JsonEndpoint<SearchTransactionRequest, SearchResponse<SearchBusinessesResult>, ErrorResponse> endpoint = getEndpoint();

    @SuppressWarnings("unchecked")
    private JsonEndpoint<SearchTransactionRequest, SearchResponse<SearchBusinessesResult>, ErrorResponse> getEndpoint() {

        JsonpDeserializer<SearchBusinessesRequest> resultDeserializer = JsonpMapperBase.findDeserializer(SearchBusinessesRequest.class);

        JsonEndpoint<SearchTransactionRequest, SearchResponse<SearchBusinessesResult>, ErrorResponse> endpoint =
                (JsonEndpoint<SearchTransactionRequest, SearchResponse<SearchBusinessesResult>, ErrorResponse>) SearchTransactionRequest._ENDPOINT;

        return new EndpointWithResponseMapperAttr<>(endpoint,
                "io.github.stewseo.clients.yelpfusion:Deserializer:_global.search.ResultT", resultDeserializer);

    }

    @TransportTest
    void testEndpointWithResponseMapperAttribute() {
        assertThat(endpoint.id()).isEqualTo("v3/transactions");
        assertThat(endpoint.hasRequestBody()).isFalse();

    }

    @TransportTest
    void testResponseDeserializer() {

        JsonpDeserializer<SearchResponse<SearchBusinessesResult>> responseParser = endpoint.responseDeserializer();
        SearchResponse<SearchBusinessesResult> SearchResponse;
        assertThat(endpoint.responseDeserializer()).isNotNull();

        JsonParser parser = parser();

        SearchResponse<SearchBusinessesResult> searchResponse  = responseParser.deserialize(parser, new JacksonJsonpMapper());

        assertThat(searchResponse).isNotNull();
    }


}