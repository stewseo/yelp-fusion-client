package io.github.stewseo.clients.yelpfusion._types;

import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.util.WithJsonObjectBuilderBase;
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;
import java.util.function.Function;

@JsonpDeserializable
public class Messaging implements JsonpSerializable {

    @Nullable
    private final String url;
    @Nullable
    private final String use_case_text;

    private Messaging(Builder builder) {
        this.url = builder.url;
        this.use_case_text = builder.use_case_text;
    }

    public static Messaging of(Function<Builder, ObjectBuilder<Messaging>> fn) {
        return fn.apply(new Builder()).build();
    }

    public final String url() {
        return this.url;
    }

    public final String use_case_text() {
        return this.use_case_text;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        if (this.use_case_text != null) {
            generator.writeKey("use_case_text");
            generator.write(this.use_case_text);
        }
        if (this.url != null) {
            generator.writeKey("url");
            generator.write(this.url);
        }
    }

    public String toString() {
        return JsonpUtils.toString(this);
    }
    public static class Builder extends WithJsonObjectBuilderBase<Builder> implements ObjectBuilder<Messaging> {
        @Nullable
        private String use_case_text;

        @Nullable
        private String url;

        public Builder() {
        }

        public final Builder use_case_text(@Nullable String value) {
            this.use_case_text = value;
            return this;
        }

        public final Builder url(@Nullable String value) {
            this.url = value;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        public Messaging build() {
            _checkSingleUse();
            return new Messaging(this);
        }
    }

    protected static void setUpMessagingDeserializer(ObjectDeserializer<Builder> op) {
        op.add(Builder::use_case_text, JsonpDeserializer.stringDeserializer(), "use_case_text");
        op.add(Builder::url, JsonpDeserializer.stringDeserializer(), "url");
    }

    public static final JsonpDeserializer<Messaging> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
            Messaging::setUpMessagingDeserializer);
}
