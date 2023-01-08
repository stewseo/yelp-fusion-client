package io.github.stewseo.clients.yelpfusion.testcases;

import io.github.stewseo.clients.json.testcases.TestJson;
import jakarta.json.spi.JsonProvider;
import jakarta.json.stream.JsonGenerator;

import java.io.StringWriter;

public abstract class YelpFusionResponseTestCase<ResponseT> implements ResponseTestCase<ResponseT> {

    public final TestJson testJson = new TestJson();

    public JsonProvider jsonProvider() {
        return mapper.jsonProvider();
    }

    @Override
    public JsonGenerator generator() {
        return mapper.jsonProvider().createGenerator(new StringWriter());
    }

    public abstract void testBuilder();
}
