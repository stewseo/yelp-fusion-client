package io.github.stewseo.client.yelpfusion.business;

import io.github.stewseo.client.json.JsonpDeserializer;
import io.github.stewseo.client.json.JsonpMapper;
import io.github.stewseo.client.json.ObjectBuilderDeserializer;
import io.github.stewseo.client.json.ObjectDeserializer;
import io.github.stewseo.client.util.ObjectBuilder;
import io.github.stewseo.client.util.WithJsonObjectBuilderBase;
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;

public class Messaging {
    @Nullable
    private final String url;
    @Nullable
    private final String use_case_text;

    private Messaging(Builder builder) {
        this.url = builder.url;
        this.use_case_text = builder.use_case_text;
    }

    public String url(){
        return this.url;
    }

    public String use_case_text(){
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

    public static class Builder extends WithJsonObjectBuilderBase<Builder> implements ObjectBuilder<Messaging> {
        @Nullable
        private String use_case_text;

        @Nullable
        private String url;

        public final Builder use_case_text(@Nullable String value) {
            this.use_case_text = value;
            return this;
        }
        public final Builder url(@Nullable String value) {
            this.url = value;
            return this;
        }

        public Builder(){}

        @Override
        protected Builder self() {
            return this;
        }

        public Messaging build() {
            _checkSingleUse();
            return new Messaging(this);
        }
    }
    public static final JsonpDeserializer<Messaging> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Messaging.Builder::new,
            Messaging::setUpMessagingDeserializer);

    protected static void setUpMessagingDeserializer(ObjectDeserializer<Builder> op) {
        op.add(Messaging.Builder::use_case_text, JsonpDeserializer.stringDeserializer(), "use_case_text");
        op.add(Messaging.Builder::url, JsonpDeserializer.stringDeserializer(), "url");
    }

}
