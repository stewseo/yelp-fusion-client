package io.github.stewseo.clients.yelpfusion._types;

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
public class Term implements JsonpSerializable {

    private final String text;

    private Term(Builder builder) {
        this.text = builder.text;
    }

    public static Term of(Function<Builder, ObjectBuilder<Term>> fn) {
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

    public static class Builder extends ObjectBuilderBase implements ObjectBuilder<Term> {
        private String text;

        public Builder() {
        }

        public final Builder text(String value) {
            this.text = value;
            return this;
        }

        public Term build() {
            _checkSingleUse();
            return new Term(this);
        }
    }

    public static final JsonpDeserializer<Term> _DESERIALIZER =
            ObjectBuilderDeserializer.lazy(Builder::new, Term::setupTermDeserializer);
}
