package io.github.stewseo.clients.yelpfusion._types;

import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionResultTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

class SpecialHoursTest extends YelpFusionResultTestCase {

    private final String date = "1/6/2023", start = "0800", end = "1700";

    private final Boolean is_closed = true, is_overnight = true;

    private final SpecialHours specialHours = SpecialHours.of(s -> s
            .date(date)
            .start(start)
            .end(end)
            .is_closed(is_closed)
            .is_overnight(is_overnight));


    @Test
    public void testOf() {
        assertThat(specialHours.date()).isEqualTo(date);
        assertThat(specialHours.start()).isEqualTo(start);
        assertThat(specialHours.end()).isEqualTo(end);
        assertThat(specialHours.is_closed()).isEqualTo(is_closed);
        assertThat(specialHours.is_overnight()).isEqualTo(is_overnight);

    }
    String expected = "{\"date\":\"1/6/2023\",\"is_closed\":true,\"start\":\"0800\",\"end\":\"1700\",\"is_overnight\":true}";
    JsonGenerator generator = generator();
    @Test
    public void testSerialize() {

        specialHours.serialize(generator, mapper);
        assertThat(specialHours.toString()).isEqualTo(expected);
    }

    @Test
    public void testSerializeInternal() {
        generator.writeStartObject();
        specialHours.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        assertThat(specialHours.toString()).isNotNull();
    }

    @Test
    public void testDeserializer() {
        assertThat(SpecialHours._DESERIALIZER.toString()).contains("io.github.stewseo.clients.json.LazyDeserializer");

    }

    @Test
    public void testDeserialize() {
        InputStream content = IOUtils.toInputStream(specialHours.toString(), StandardCharsets.UTF_8);

        JsonParser parser = mapper.jsonProvider().createParser(content);

        SpecialHours searchBusinessRes = SpecialHours._DESERIALIZER.deserialize(parser, mapper);

        assertThat(specialHours.toString()).isEqualTo(expected);
    }

    @Override
    public JsonParser parser() {
        InputStream content = IOUtils.toInputStream(specialHours.toString(), StandardCharsets.UTF_8);
        return mapper.jsonProvider().createParser(content);
    }
}