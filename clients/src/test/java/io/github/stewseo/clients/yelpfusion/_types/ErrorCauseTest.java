package io.github.stewseo.clients.yelpfusion._types;

import io.github.stewseo.clients.json.JsonData;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.clients.yelpfusion.YelpFusionTest;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionTestCase;
import jakarta.json.stream.JsonGenerator;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;



class ErrorCauseTest extends YelpFusionTestCase<ErrorCause> {

   private final ErrorCause errorCause = of();

    @Override
    public ErrorCause of() {
        return ErrorCause.of(e -> e
                .metadata(Map.of("string", JsonData.of("jsonData")))
                .rootCause(List.of(ErrorCause.of(er -> er.type("rootCauseType"))))
                .causedBy(ErrorCause.of(cb -> cb.type("causedByType")))
                .reason("reason")
                .stackTrace("stacktrace")
                .type("type"));
    }

    @YelpFusionTest
    public void testBuilder() {

       ErrorCause.Builder builder = new ErrorCause.Builder().metadata(Map.of("string", JsonData.of("jsonData")))
                .rootCause(List.of(ErrorCause.of(er -> er.type("rootCauseType"))))
                .causedBy(ErrorCause.of(cb -> cb.type("causedByType")))
                .reason("reason")
                .stackTrace("stacktrace")
                .type("type");

       assertThat(builder.self()).isNotNull();

       assertThat(builder.build()).isNotNull();
    }

    @YelpFusionTest
    public void testOf() {
        assertThat(errorCause.causedBy().toString()).isEqualTo("{\"type\":\"causedByType\"}");
        assertThat(errorCause.rootCause().toString()).isEqualTo("[{\"type\":\"rootCauseType\"}]");
        assertThat(errorCause.reason()).isEqualTo("reason");
        assertThat(errorCause.stackTrace()).isEqualTo("stacktrace");
        assertThat(errorCause.type()).isEqualTo("type");
        assertThat(errorCause.metadata().toString()).isEqualTo(Map.of("string", JsonData.of("jsonData")).toString());
    }

    @YelpFusionTest
    void setupErrorCauseDeserializer() {

        JsonpDeserializer<ErrorCause> errorCauseJsonpDeserializer = ErrorCause._DESERIALIZER;
        assertThat(errorCauseJsonpDeserializer).isNotNull();
    }

    @YelpFusionTest
    void causedBy() {

        final String expected = "{\"caused_by\":{\"string\":\"jsonData\"," +
                "\"type\":\"type\",\"reason\":\"reason\"," +
                "\"stack_trace\":\"stacktrace\"," +
                "\"caused_by\":{\"type\":\"causedByType\"}," +
                "\"root_cause\":[{\"type\":\"rootCauseType\"}]}}";

        ErrorCause causedBy = ErrorCause.of(error -> error
                .causedBy(errorCause)
        );
        assertThat(causedBy.toString()).isEqualTo(expected);
    }

    @YelpFusionTest
    void rootCause() {
        final String expected = "{\"root_cause\":[{\"string\":\"jsonData\"," +
                "\"type\":\"type\",\"reason\":\"reason\"," +
                "\"stack_trace\":\"stacktrace\"," +
                "\"caused_by\":{\"type\":\"causedByType\"}," +
                "\"root_cause\":[{\"type\":\"rootCauseType\"}]}]}";

        ErrorCause rootCause = ErrorCause.of(error -> error.rootCause(errorCause));
        assertThat(rootCause.toString()).isEqualTo(expected);
    }

    private final String expectedJson = "" +
            "{\"string\":\"jsonData\"," +
            "\"type\":\"type\"," +
            "\"reason\":\"reason\"," +
            "\"stack_trace\":\"stacktrace\"," +
            "\"caused_by\":{\"type\":\"causedByType\"}," +
            "\"root_cause\":[{\"type\":\"rootCauseType\"}]}";
    private final JsonGenerator generator = generator();

    @YelpFusionTest
    public void testSerialize() {

        errorCause.serialize(generator, new JacksonJsonpMapper());

        assertThat(errorCause.toString()).isEqualTo(expectedJson);
    }

    @YelpFusionTest
    public void testSerializeInternal() {
        generator.writeStartObject();
        errorCause.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        assertThat(errorCause.toString()).isEqualTo(expectedJson);
    }

}