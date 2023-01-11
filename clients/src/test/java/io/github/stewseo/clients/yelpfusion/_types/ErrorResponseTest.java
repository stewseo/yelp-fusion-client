package io.github.stewseo.clients.yelpfusion._types;

import io.github.stewseo.clients.json.JsonData;
import io.github.stewseo.clients.yelpfusion._types.ErrorCause;
import io.github.stewseo.clients.yelpfusion._types.ErrorResponse;
import io.github.stewseo.clients.yelpfusion._types.Messaging;
import io.github.stewseo.clients.yelpfusion.testcases.ModelTestCase;
import jakarta.json.JsonValue;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ErrorResponseTest extends ModelTestCase<ErrorResponse> {


    private final ErrorCause errorCause = ErrorCause.of(e -> e
            .metadata(Map.of("string", JsonData.of("jsonData")))
            .reason("reason")
            .stackTrace("stacktrace")
            .type("type"));
    private final ErrorResponse errorResponse = of();

    @Override
    public ErrorResponse of() {
        return ErrorResponse.of(e -> e
                .error(errorCause)
                .status(401)
        );
    }

    @Test
    public void testOf() {
        System.out.println(errorResponse.toString());
        assertThat(errorResponse.status()).isEqualTo(401);
        String expectedError = "{\"string\":\"jsonData\",\"type\":\"type\",\"reason\":\"reason\",\"stack_trace\":\"stacktrace\"}";
        assertThat(errorResponse.error().toString()).isEqualTo(expectedError);
    }

    private final String expectedErrorResp = "" +
            "{\"error\":{\"string\":\"jsonData\"," +
            "\"type\":\"type\",\"reason\":\"reason\"," +
            "\"stack_trace\":\"stacktrace\"},\"status\":401}";
    @Test
    public void testBuilder() {

        ErrorResponse.Builder builder = new ErrorResponse.Builder()
                .error(errorCause)
                .status(401);

        ErrorResponse.Builder self = builder.self();

        assertThat(self).isEqualTo(builder);

        ErrorResponse errorResponse = builder.build();

        assertThat(errorResponse.toString()).isEqualTo(expectedErrorResp);
    }

    private final JsonGenerator generator = generator();

    @Test
    public void testSerialize() {

        errorResponse.serialize(generator, mapper);
        assertThat(errorResponse.toString()).isEqualTo(expectedErrorResp);
    }

    @Test
    public void testSerializeInternal() {
        generator.writeStartObject();
        errorResponse.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        assertThat(errorResponse.toString()).isEqualTo(expectedErrorResp);
    }

    @Test
    public void testDeserializer() {
        assertThat(Messaging._DESERIALIZER.toString()).contains("io.github.stewseo.clients.json.LazyDeserializer");

    }

    @Test
    public void testDeserialize() {

        ErrorResponse errorRes = ErrorResponse._DESERIALIZER.deserialize(parser(), mapper);

        assertThat(errorRes.toString()).isEqualTo(expectedErrorResp);
    }

    public JsonParser parser() {
        return parser(errorResponse);
    }
}