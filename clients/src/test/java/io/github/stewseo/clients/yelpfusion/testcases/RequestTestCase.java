package io.github.stewseo.clients.yelpfusion.testcases;

import io.github.stewseo.clients.json.SerializeToJson;
import io.github.stewseo.clients.transport.Endpoint;
import io.github.stewseo.clients.yelpfusion.YelpFusionTest;
import org.junit.jupiter.api.Test;

public interface RequestTestCase<RequestT> extends BuilderTestCase<RequestT>, SerializeToJson {

    Endpoint<RequestT, ?, ?> endpoint();

    @YelpFusionTest
    void testEndpoint();
}
