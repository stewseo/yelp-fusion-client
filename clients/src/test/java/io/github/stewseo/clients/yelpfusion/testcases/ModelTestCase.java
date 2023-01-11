package io.github.stewseo.clients.yelpfusion.testcases;

import io.github.stewseo.clients.json.SerializeToJson;
import jakarta.json.spi.JsonProvider;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

public abstract class ModelTestCase<YelpFusionT>
        implements BuilderTestCase<YelpFusionT>, SerializeToJson {


    private JsonProvider jsonProvider() {
        return mapper.jsonProvider();
    }

    public JsonParser parser(YelpFusionT yelpFusionT) {
        InputStream content = IOUtils.toInputStream(yelpFusionT.toString(), StandardCharsets.UTF_8);
        return mapper.jsonProvider().createParser(content);
    }

    @Override
    public final JsonGenerator generator() {
        return jsonProvider().createGenerator(new StringWriter());
    }

}
