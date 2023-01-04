package io.github.stewseo.clients._types;

import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.util.ObjectBuilderBase;
import jakarta.json.stream.JsonGenerator;

import java.util.function.Function;

@JsonpDeserializable
public class TestTerm implements JsonpSerializable {
    public static final JsonpDeserializer<TestTerm> _DESERIALIZER =
            ObjectBuilderDeserializer.lazy(Builder::new,
                    TestTerm::setupTermDeserializer);
    private final String text;

    private TestTerm(Builder builder) {
        this.text = builder.text;
    }

    public static TestTerm of(Function<Builder, ObjectBuilder<TestTerm>> fn) {
        return fn.apply(new Builder()).build();
    }

    protected static void setupTermDeserializer(ObjectDeserializer<Builder> op) {
        op.add(Builder::text, JsonpDeserializer.stringDeserializer(), "text");
    }

    private String text() {
        return text;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        if (this.text != null) {
            generator.writeKey("text");
            generator.write(this.text);
        }
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    public static class Builder extends ObjectBuilderBase implements ObjectBuilder<TestTerm> {
        private String text;

        public Builder() {
        }

        public final Builder text(String value) {
            this.text = value;
            return this;
        }

        public TestTerm build() {
            _checkSingleUse();
            return new TestTerm(this);
        }
    }
}
