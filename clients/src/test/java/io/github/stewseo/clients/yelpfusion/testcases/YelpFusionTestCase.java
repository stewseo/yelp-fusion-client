package io.github.stewseo.clients.yelpfusion.testcases;

import io.github.stewseo.clients.json.SerializeToJson;
import jakarta.json.spi.JsonProvider;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

public abstract class YelpFusionTestCase<YelpFusionT>
        implements BuilderTestCase<YelpFusionT>, SerializeToJson {

    private JsonProvider jsonProvider() {
        return mapper.jsonProvider();
    }

    public JsonParser parser(YelpFusionT yelpFusionT) {
        String yfString = yelpFusionT.toString();

        if (!isValidJsonString(yfString)) {
            yfString = yfString.substring(yfString.indexOf("{"));
        }
        InputStream content = IOUtils.toInputStream(yfString, StandardCharsets.UTF_8);
        return mapper.jsonProvider().createParser(content);
    }

    private boolean isValidJsonString(String yelpFusionToString) {
        return yelpFusionToString.startsWith("{");
    }

    @Override
    public final JsonGenerator generator() {
        return jsonProvider().createGenerator(new StringWriter());
    }

}
