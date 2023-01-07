package io.github.stewseo.clients.yelpfusion.testcases;

import io.github.stewseo.clients.json.SerializeToJson;
import io.github.stewseo.clients.transport.Endpoint;
import org.junit.jupiter.api.Test;

public interface EndpointTestCase<RequestT> extends SerializeToJson {

    Endpoint<RequestT, ?, ?> endpoint();

    @Test
    void testEndpoint();

}
