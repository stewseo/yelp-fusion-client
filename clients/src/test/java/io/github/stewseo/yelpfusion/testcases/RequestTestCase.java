package io.github.stewseo.yelpfusion.testcases;

import io.github.stewseo.clients.json.SerializeToJson;
import io.github.stewseo.clients.transport.Endpoint;
import io.github.stewseo.clients.yelpfusion.testcases.BuilderTestCase;
import org.junit.jupiter.api.Test;

public interface RequestTestCase<RequestT> extends BuilderTestCase<RequestT>, SerializeToJson {

    Endpoint<RequestT, ?, ?> endpoint();

    @Test
    void testEndpoint();
}
