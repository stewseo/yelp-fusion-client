package io.github.stewseo.clients.yelpfusion.testcases;

import io.github.stewseo.clients.json.SerializeToJson;
import io.github.stewseo.clients.transport.Endpoint;
import org.junit.jupiter.api.Test;

public interface RequestTestCase<RequestT> extends SerializeToJson {

    Endpoint<RequestT, ?, ?> endpoint();

    // fields that all Requests share
    String locale = "locale", sort_by = "sort_by", location = "location";

    int offset = 5, limit = 50, radius = 20000;

    double latitude = 44.0, longitude = -122.0;


    @Test
    void testEndpoint();

}
