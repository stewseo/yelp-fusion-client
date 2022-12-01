package io.github.yelp.fusion.client.yelpfusion.business;


import io.github.yelp.fusion.client.json.*;
import jakarta.json.stream.JsonGenerator;
import io.github.yelp.fusion.client.util.ObjectBuilder;
import io.github.yelp.fusion.client.util.ObjectBuilderBase;

import javax.annotation.Nullable;

import static io.github.yelp.fusion.client.json.JsonpDeserializer.stringDeserializer;

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
            ObjectBuilderDeserializer.lazy(Attribute.Builder::new,
                    Attribute::setupAttributesDeserializer);

    protected static void setupAttributesDeserializer(ObjectDeserializer<Builder> op) {
        op.add(Attribute.Builder::attribute, stringDeserializer(), "attribute");
    }
}
