package io.github.stewseo.client.yelpfusion.business;


import io.github.stewseo.client.json.JsonpDeserializable;
import io.github.stewseo.client.json.JsonpDeserializer;
import io.github.stewseo.client.json.JsonpMapper;
import io.github.stewseo.client.json.JsonpSerializable;
import io.github.stewseo.client.json.ObjectBuilderDeserializer;
import io.github.stewseo.client.json.ObjectDeserializer;
import io.github.stewseo.client.util.ObjectBuilder;
import io.github.stewseo.client.util.ObjectBuilderBase;
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;

@JsonpDeserializable
public class Attribute implements JsonpSerializable {
    @Nullable
    private final String attribute;
    public String attribute(){return this.attribute;}
    private Attribute(Builder builder) {
        this.attribute = builder.attribute;
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

    public static class Builder extends ObjectBuilderBase implements ObjectBuilder<Attribute> {
        @Nullable
        private String attribute;


        public final Builder attribute(@Nullable String value) {
            this.attribute = value;
            return this;
        }

        public Attribute build() {
            _checkSingleUse();
            return new Attribute(this);
        }
    }

    public static final JsonpDeserializer<Attribute> _DESERIALIZER =
            ObjectBuilderDeserializer.lazy(Builder::new,
                    Attribute::setupAttributesDeserializer);

    protected static void setupAttributesDeserializer(ObjectDeserializer<Builder> op) {
        op.add(Builder::attribute, JsonpDeserializer.stringDeserializer(), "attribute");
    }
}
