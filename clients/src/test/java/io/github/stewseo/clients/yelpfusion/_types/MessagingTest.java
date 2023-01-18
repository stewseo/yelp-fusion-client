package io.github.stewseo.clients.yelpfusion._types;

import io.github.stewseo.clients.yelpfusion.YelpFusionTest;


import io.github.stewseo.clients.json.DeserializeFromJson;
import io.github.stewseo.clients.json.testcases.TestJsonp;

import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;

import static org.assertj.core.api.Assertions.assertThat;


class MessagingTest extends YelpFusionTestCase<Messaging>
        implements DeserializeFromJson {

    private final Messaging messaging = of();

    private final String USE_CASE_TEXT = "useCaseTextValue", URL = "urlValue";

    @Override
    public Messaging of() {
        return Messaging.of(m -> m
                .url(URL)
                .use_case_text(USE_CASE_TEXT)
        );
    }
    private final String expected = "{\"use_case_text\":\"useCaseTextValue\",\"url\":\"urlValue\"}";
    @YelpFusionTest
    public void testBuilder() {

        Messaging.Builder builder = new Messaging.Builder()
                .url(URL)
                .use_case_text(USE_CASE_TEXT);

        Messaging.Builder self = builder.self();

        assertThat(self).isEqualTo(builder);

        Messaging messaging = builder.build();

        assertThat(messaging.toString()).isEqualTo(expected);
    }

    @YelpFusionTest
    public void testOf() {
        assertThat(messaging.url()).isEqualTo(URL);
        assertThat(messaging.use_case_text()).isEqualTo(USE_CASE_TEXT);
    }

    JsonGenerator generator = generator();
    @YelpFusionTest
    public void testSerialize() {

        messaging.serialize(generator, TestJsonp.mapper);
        assertThat(messaging.toString()).isEqualTo(expected);
    }

    @YelpFusionTest
    public void testSerializeInternal() {
        generator.writeStartObject();
        messaging.serializeInternal(generator, TestJsonp.mapper);
        generator.writeEnd().close();
        assertThat(messaging.toString()).isNotNull();
    }

    @YelpFusionTest
    public void testDeserializer() {
        assertThat(Messaging._DESERIALIZER.toString()).contains("io.github.stewseo.clients.json.LazyDeserializer");

    }

    @YelpFusionTest
    public void testDeserialize() {

        Messaging messaging = Messaging._DESERIALIZER.deserialize(parser(), TestJsonp.mapper);

        assertThat(messaging.toString()).isEqualTo(expected);
    }

    public JsonParser parser() {
        return parser(messaging);
    }

}