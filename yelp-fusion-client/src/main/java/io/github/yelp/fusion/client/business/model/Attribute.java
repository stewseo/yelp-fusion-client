package io.github.yelp.fusion.client.business.model;


import io.github.elasticsearch.client.json.*;
import jakarta.json.stream.JsonGenerator;
import io.github.elasticsearch.client.util.ObjectBuilder;
import io.github.elasticsearch.client.util.ObjectBuilderBase;

import java.util.List;

import static io.github.elasticsearch.client.json.JsonpDeserializer.stringDeserializer;

@JsonpDeserializable
public class Attribute implements JsonpSerializable {
    private final List<String> attributes;

    private Attribute(Builder builder) {
        this.attributes = builder.attributes;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {
        if (this.attributes != null) {
            generator.writeKey("attributes");
            generator.writeStartArray();
            for (String item0 : this.attributes) {
                generator.write(item0);
            }
            generator.writeEnd();
        }

    }

    public static class Builder extends ObjectBuilderBase implements ObjectBuilder<Attribute> {
        private List<String> attributes;

        public List<String> getAttributes() {
            return attributes;
        }

        public Builder(){}

        public final Builder attributes(List<String> value) {
            this.attributes = value;
            return this;
        }

        public final Builder attributes(String value, String... values) {
            this.attributes = _listAdd(this.attributes, value, values);
            return this;
        }
        public Attribute build() {
            _checkSingleUse();
            return new Attribute(this);
        }

    }

    public static final JsonpDeserializer<Attribute> _DESERIALIZER =
            ObjectBuilderDeserializer.lazy(Attribute.Builder::new,
                    Attribute::setupCategoriesDeserializer);

    protected static void setupCategoriesDeserializer(ObjectDeserializer<Builder> op) {
        op.add(Attribute.Builder::attributes, JsonpDeserializer.arrayDeserializer(stringDeserializer()), "attributes");
    }
}
