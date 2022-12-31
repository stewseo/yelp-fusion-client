package io.github.stewseo.client._types;

import io.github.stewseo.client.json.JsonpDeserializable;
import io.github.stewseo.client.json.JsonpDeserializer;
import io.github.stewseo.client.json.JsonpMapper;
import io.github.stewseo.client.json.JsonpSerializable;
import io.github.stewseo.client.json.JsonpUtils;
import io.github.stewseo.client.json.ObjectBuilderDeserializer;
import io.github.stewseo.client.json.ObjectDeserializer;
import io.github.stewseo.client.util.WithJsonObjectBuilderBase;
import io.github.stewseo.client.util.ApiTypeHelper;
import io.github.stewseo.client.util.ObjectBuilder;
import jakarta.json.stream.JsonGenerator;

import java.util.function.Function;

@JsonpDeserializable
public class ErrorResponse implements JsonpSerializable {
    /**
     * Json deserializer for {@link ErrorResponse}
     */
    public static final JsonpDeserializer<ErrorResponse> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
            ErrorResponse::setupErrorResponseDeserializer);
    private final ErrorCause error;

    // ---------------------------------------------------------------------------------------------
    private final int status;

    private ErrorResponse(Builder builder) {

        this.error = ApiTypeHelper.requireNonNull(builder.error, this, "error");
        this.status = ApiTypeHelper.requireNonNull(builder.status, this, "status");

    }

    public static ErrorResponse of(Function<Builder, ObjectBuilder<ErrorResponse>> fn) {
        return fn.apply(new Builder()).build();
    }

    protected static void setupErrorResponseDeserializer(ObjectDeserializer<Builder> op) {

        op.add(Builder::error, ErrorCause._DESERIALIZER, "error");
        op.add(Builder::status, JsonpDeserializer.integerDeserializer(), "status");

    }

    public final ErrorCause error() {
        return this.error;
    }

    /**
     * Required - API name: {@code status}
     */
    public final int status() {
        return this.status;
    }

    /**
     * Serialize this object to JSON.
     */
    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    // ---------------------------------------------------------------------------------------------

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        generator.writeKey("error");
        this.error.serialize(generator, mapper);

        generator.writeKey("status");
        generator.write(this.status);

    }

    // ---------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    /**
     * Builder for {@link ErrorResponse}.
     */

    public static class Builder extends WithJsonObjectBuilderBase<Builder> implements ObjectBuilder<ErrorResponse> {
        private ErrorCause error;

        private Integer status;


        public final Builder error(ErrorCause value) {
            this.error = value;
            return this;
        }


        public final Builder error(Function<ErrorCause.Builder, ObjectBuilder<ErrorCause>> fn) {
            return this.error(fn.apply(new ErrorCause.Builder()).build());
        }


        public final Builder status(int value) {
            this.status = value;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        public ErrorResponse build() {
            _checkSingleUse();

            return new ErrorResponse(this);
        }
    }

}
