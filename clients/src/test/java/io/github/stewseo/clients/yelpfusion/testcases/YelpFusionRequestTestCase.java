package io.github.stewseo.clients.yelpfusion.testcases;

import io.github.stewseo.clients.json.testcases.TestJson;
import io.github.stewseo.clients.yelpfusion._types.Category;
import io.github.stewseo.clients.yelpfusion._types.Coordinates;
import jakarta.json.stream.JsonGenerator;

import java.io.StringWriter;

public abstract class YelpFusionRequestTestCase<RequestT> implements RequestTestCase<RequestT> {

    public TestJson testJson = new TestJson();
    
    public final boolean open_now = true;

    public final JsonGenerator generator() {
        return mapper.jsonProvider().createGenerator(new StringWriter());
    }


    public abstract void testBuilder();
}
