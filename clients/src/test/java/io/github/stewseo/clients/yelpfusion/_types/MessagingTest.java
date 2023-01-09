package io.github.stewseo.clients.yelpfusion._types;

import io.github.stewseo.clients.json.DeserializeFromJson;
import io.github.stewseo.clients.yelpfusion.testcases.ModelTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MessagingTest extends ModelTestCase<Messaging>
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
    @Test
    public void testBuilder() {

        Messaging.Builder builder = new Messaging.Builder()
                .url(URL)
                .use_case_text(USE_CASE_TEXT);

        Messaging.Builder self = builder.self();

        assertThat(self).isEqualTo(builder);

        Messaging messaging = builder.build();

        Assertions.assertThat(messaging.toString()).isEqualTo(expected);
    }

    @Test
    public void testOf() {
        assertThat(messaging.url()).isEqualTo(URL);
        assertThat(messaging.use_case_text()).isEqualTo(USE_CASE_TEXT);
    }

    JsonGenerator generator = generator();
    @Test
    public void testSerialize() {

        messaging.serialize(generator, mapper);
        assertThat(messaging.toString()).isEqualTo(expected);
    }

    @Test
    public void testSerializeInternal() {
        generator.writeStartObject();
        messaging.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        assertThat(messaging.toString()).isNotNull();
    }

    @Test
    public void testDeserializer() {
        assertThat(Messaging._DESERIALIZER.toString()).contains("io.github.stewseo.clients.json.LazyDeserializer");

    }

    @Test
    public void testDeserialize() {

        Messaging messaging = Messaging._DESERIALIZER.deserialize(parser(), mapper);

        assertThat(messaging.toString()).isEqualTo(expected);
    }

    public JsonParser parser() {
        return parser(messaging);
    }

}