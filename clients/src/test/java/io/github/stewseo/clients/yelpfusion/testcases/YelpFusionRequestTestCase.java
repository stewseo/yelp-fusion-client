package io.github.stewseo.clients.yelpfusion.testcases;

import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.SerializeToJson;
import io.github.stewseo.clients.yelpfusion._types.Category;
import jakarta.json.spi.JsonProvider;
import jakarta.json.stream.JsonGenerator;

import java.io.StringWriter;

public abstract class YelpFusionRequestTestCase<RequestT> implements EndpointTestCase<RequestT> {

    // fields that all Requests share
    public String locale = "locale", sort_by = "sort_by", alias = "alias",
            location = "location", term = "term";

    public int offset = 5, limit = 50, radius = 20000, price = 3;

    public double latitude = 44.0, longitude = -122.0;

    public Category category = Category.of(c -> c.alias("catAlias"));

    public JsonGenerator generator() {
        return mapper.jsonProvider().createGenerator(new StringWriter());
    }

    public <T> String toJson(T value) {
        return toJson(value, mapper);
    }

    public <T> String toJson(T value, JsonpMapper mapper) {
        StringWriter sw = new StringWriter();
        JsonProvider provider = mapper.jsonProvider();
        JsonGenerator generator = provider.createGenerator(sw);
        mapper.serialize(value, generator);
        generator.close();
        return sw.toString();
    }

}
