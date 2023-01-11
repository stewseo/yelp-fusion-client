package io.github.stewseo.clients.yelpfusion._types;


import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.util.ObjectBuilderBase;
import io.github.stewseo.clients.yelpfusion.events.search.SearchEventsRequest;
import jakarta.json.stream.JsonGenerator;
import org.w3c.dom.Attr;

import javax.annotation.Nullable;
import java.util.function.Function;

@JsonpDeserializable
public class Attribute implements JsonpSerializable {

    @Nullable
    private final String attribute;

    private Attribute(Builder builder) {
        this.attribute = builder.attribute;
    }

    public static Attribute of(Function<Builder, ObjectBuilder<Attribute>> fn) {
        return fn.apply(new Builder()).build();
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

    protected static void setupAttributesDeserializer(ObjectDeserializer<Builder> op) {
        op.add(Builder::attribute, JsonpDeserializer.stringDeserializer(), "attribute");
    }


    public static final JsonpDeserializer<Attribute> _DESERIALIZER =
            ObjectBuilderDeserializer.lazy(Builder::new,
                    Attribute::setupAttributesDeserializer);
}
