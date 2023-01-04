package io.github.stewseo.clients._types;


import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.util.ObjectBuilderBase;
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;

@JsonpDeserializable
public class TestAttribute implements JsonpSerializable {

    @Nullable
    private final String attribute;

    private TestAttribute(Builder builder) {
        this.attribute = builder.attribute;
    }

    public String attribute() {
        return this.attribute;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {
        if (this.attribute != null) {
            generator.writeKey("attributes");
            generator.write(this.attribute);
        }
    }

    public static class Builder extends ObjectBuilderBase implements ObjectBuilder<TestAttribute> {
        @Nullable
        private String attribute;


        public final Builder attribute(@Nullable String value) {
            this.attribute = value;
            return this;
        }

        public TestAttribute build() {
            _checkSingleUse();
            return new TestAttribute(this);
        }
    }

    protected static void setupAttributesDeserializer(ObjectDeserializer<Builder> op) {
        op.add(Builder::attribute, JsonpDeserializer.stringDeserializer(), "attribute");
    }


    public static final JsonpDeserializer<TestAttribute> _DESERIALIZER =
            ObjectBuilderDeserializer.lazy(Builder::new,
                    TestAttribute::setupAttributesDeserializer);
}
