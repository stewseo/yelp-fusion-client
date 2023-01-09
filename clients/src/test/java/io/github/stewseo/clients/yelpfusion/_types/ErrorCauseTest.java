package io.github.stewseo.clients.yelpfusion._types;

import io.github.stewseo.clients.json.JsonData;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.clients.json.testcases.TestJson;
import jakarta.json.stream.JsonGenerator;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ErrorCauseTest extends TestJson {

   private final ErrorCause errorCause = ErrorCause.of(e -> e
            .metadata(Map.of("string", JsonData.of("jsonData")))
            .reason("reason")
            .stackTrace("stacktrace")
            .type("type"));

    @Test
    void setupErrorCauseDeserializer() {

        JsonpDeserializer<ErrorCause> errorCauseJsonpDeserializer = ErrorCause._DESERIALIZER;
        assertThat(errorCauseJsonpDeserializer).isNotNull();
    }

    @Test
    void causedBy() {
        final String expected = "{\"caused_by\":{\"string\":\"jsonData\",\"type\":\"type\",\"reason\":\"reason\",\"stack_trace\":\"stacktrace\"}}";

        ErrorCause causedBy = ErrorCause.of(error -> error
                .causedBy(errorCause)
        );
        assertThat(causedBy.toString()).isEqualTo(expected);
    }

    @Test
    void rootCause() {
        final String expected = "" + "{\"root_cause\":[{\"string\":\"jsonData\",\"type\":\"type\",\"reason\":\"reason\",\"stack_trace\":\"stacktrace\"}]}";

        ErrorCause rootCause = ErrorCause.of(error -> error.rootCause(errorCause));
        assertThat(rootCause.toString()).isEqualTo(expected);
    }

    private final String expectedJson = "{\"string\":\"jsonData\",\"type\":\"type\",\"reason\":\"reason\",\"stack_trace\":\"stacktrace\"}";

    @Test
    void serialize() {
        StringWriter strw = new StringWriter();

        JsonGenerator generator = mapper.jsonProvider().createGenerator(strw);

        errorCause.serialize(generator, new JacksonJsonpMapper());

        assertThat(errorCause.toString()).isEqualTo(expectedJson);
    }

    @Test
    void serializeInternal() {
       String toJson = toJson(errorCause.toString());

       assertThat(toJson).isEqualTo(
               "\"{\\\"string\\\":\\\"jsonData\\\"," +
                       "\\\"type\\\":\\\"type\\\"," +
                       "\\\"reason\\\":\\\"reason\\\"," +
                       "\\\"stack_trace\\\":\\\"stacktrace\\\"}\"");
    }

    @Test
    void testToString() {
        assertThat(errorCause.toString()).isEqualTo(expectedJson);
    }
}