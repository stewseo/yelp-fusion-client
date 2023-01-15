package io.github.stewseo.clients.json.testcases;

import io.github.stewseo.clients.json.DeserializeFromJson;
import io.github.stewseo.clients.json.SerializeToJson;
import jakarta.json.spi.JsonProvider;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.apache.commons.io.IOUtils;
import org.assertj.core.api.Assertions;

import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

public abstract class ModelJsonTestCase extends Assertions implements SerializeToJson, DeserializeFromJson {

    private JsonProvider jsonProvider() {
        return mapper.jsonProvider();
    }


    @Override
    public final JsonGenerator generator() {
        return jsonProvider().createGenerator(new StringWriter());
    }

    public <T> JsonParser parser(T t) {

        InputStream content = IOUtils.toInputStream(t.toString(), StandardCharsets.UTF_8);
        return mapper.jsonProvider().createParser(content);
    }

    @Override
    public JsonParser parser() {
        return parser("test");
    }

    public void testDeserializer() {

    }

    public void testDeserialize() {

    }
}
