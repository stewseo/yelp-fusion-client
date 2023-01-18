package io.github.stewseo.clients.transport.restclient;

import io.github.stewseo.clients.transport.AbstractTransportTestCase;
import io.github.stewseo.clients.transport.Endpoint;
import io.github.stewseo.clients.transport.JsonEndpoint;
import io.github.stewseo.clients.transport.TransportOptions;
import io.github.stewseo.clients.transport.TransportTest;
import io.github.stewseo.clients.yelpfusion._types.ErrorResponse;
import io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessesRequest;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessesResult;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchResponse;
import io.github.stewseo.lowlevel.restclient.Request;
import io.github.stewseo.lowlevel.restclient.RestClient;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class RestClientTransportTest extends AbstractTransportTestCase {


    private final RestClientTransport transport = transport();

    @InjectMocks
    private RestClientTransport restClientTransport = transport();

    @TransportTest
    void close() throws IOException {

        try (transport) {
            assertThat(transport).isNotNull();
            assertThat(transport.restClient().httpHost().getHostName()).isEqualTo("api.yelp.com");
        }
    }

    @TransportTest
    void restClient() {
        assertThat(transport.restClient()).isExactlyInstanceOf(RestClient.class);
    }


    private final SearchBusinessesRequest requestT = TestVars.TestReqVars.SEARCH_BUSINESSES_REQUEST;

    @SuppressWarnings("unchecked")
    private final JsonEndpoint<SearchBusinessesRequest, SearchResponse<SearchBusinessesResult>, ErrorResponse> endpoint =
            (JsonEndpoint<SearchBusinessesRequest, SearchResponse<SearchBusinessesResult>, ErrorResponse>) SearchBusinessesRequest._ENDPOINT;

    @TransportTest
    void performRequest() {

        RuntimeException runtimeException = assertThrows(RuntimeException.class, () ->
                transport.performRequest(requestT, null, transport.options())
        );

        assertThat(runtimeException.getMessage())
                .isEqualTo("Cannot invoke \"io.github.stewseo.clients.transport.Endpoint.method(Object)\" because \"endpoint\" is null");

    }

    @TransportTest
    void prepareLowLevelRequest() {

        try {
            final Method method = RestClientTransport.class.getDeclaredMethod("prepareLowLevelRequest",
                    Object.class, Endpoint.class, TransportOptions.class);
            method.setAccessible(true);

            Object s = assertDoesNotThrow(() -> method.invoke(restClientTransport, requestT, endpoint, transport.options()));

        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }

    @TransportTest
    void performLowLevelRestClientRequest() {

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                transport.performRequest(requestT, endpoint, transport.options()));
        assertThat(exception.getMessage()).isEqualTo("Request execution cancelled");

    }

    @TransportTest
    void jsonpMapper() {
        assertThat(transport.jsonpMapper()).isNotNull();
    }

    @TransportTest
    void options() {
        assertThat(transport.options()).isNotNull();
    }

    private Method getPrepareLowLevelRequest(String methodName) throws NoSuchMethodException {
        Method method = RestClientTransport.class.getDeclaredMethod(methodName, Request.class);
        method.setAccessible(true);
        return method;
    }
}