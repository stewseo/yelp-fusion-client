package io.github.stewseo.client.yelpfusion.business;

import io.github.stewseo.client.json.JsonpDeserializable;
import io.github.stewseo.client.json.JsonpDeserializer;
import io.github.stewseo.client.json.JsonpMapper;
import io.github.stewseo.client.json.JsonpSerializable;
import io.github.stewseo.client.json.JsonpUtils;
import io.github.stewseo.client.json.ObjectBuilderDeserializer;
import io.github.stewseo.client.json.ObjectDeserializer;
import io.github.stewseo.client.util.ObjectBuilder;
import io.github.stewseo.client.util.ObjectBuilderBase;

import jakarta.json.stream.*;


import java.util.function.*;

@JsonpDeserializable
public class Term implements JsonpSerializable {
    private final String text;


    private Term(Builder builder) {
        this.text = builder.text;
    }
    private String text() {
        return text;
    }

    public static Term of(Function<Builder, ObjectBuilder<Term>> fn) {
        return fn.apply(new Builder()).build();
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
            ObjectBuilderDeserializer.lazy(Term.Builder::new,
                    Term::setupTermDeserializer);

    protected static void setupTermDeserializer(ObjectDeserializer<Builder> op) {
        op.add(Builder::text, JsonpDeserializer.stringDeserializer(), "text");
    }
}
