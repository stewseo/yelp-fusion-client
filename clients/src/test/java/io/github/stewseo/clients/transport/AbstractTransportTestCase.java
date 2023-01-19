package io.github.stewseo.clients.transport;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.clients.transport.restclient.RestClientTransport;
import io.github.stewseo.lowlevel.restclient.RestClient;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.TestTransportVars.HOST_NAME;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.TestTransportVars.PORT;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.TestTransportVars.SCHEME;

public abstract class AbstractTransportTestCase extends Assertions {

    public final RestClientTransport transport;

    private final ThreadLocal<Object> result = new ThreadLocal<>();

    public AbstractTransportTestCase() {

        final JsonpMapper mapper = new JacksonJsonpMapper(
                new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE));

        HttpHost host = new HttpHost(HOST_NAME, PORT, SCHEME);

        Header[] defaultHeader = {new BasicHeader("Authorization", "Bearer " + System.getenv("YELP_API_KEY"))};

        try (RestClient restClient = RestClient.builder(host)
                .setDefaultHeaders(defaultHeader)
                .build()) {

            transport = new RestClientTransport(restClient, mapper);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public RestClientTransport transport() {
        return this.transport;
    }

}
