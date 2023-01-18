package io.github.stewseo.clients.json.testcases;

import io.github.stewseo.clients.json.DeserializeFromJson;
import io.github.stewseo.clients.json.SerializeToJson;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;

import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

public abstract class AbstractJsonTestCase extends Assertions implements SerializeToJson, DeserializeFromJson {

    @Override
    public final JsonGenerator generator() {
        return mapper.jsonProvider().createGenerator(new StringWriter());
    }

    public <T> JsonParser parser(T t) {

        InputStream content = IOUtils.toInputStream(t.toString(), StandardCharsets.UTF_8);
        return mapper.jsonProvider().createParser(content);
    }

}
