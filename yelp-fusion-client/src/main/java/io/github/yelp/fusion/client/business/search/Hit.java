package io.github.yelp.fusion.client.business.search;

import io.github.elasticsearch.client.json.*;

import jakarta.json.stream.JsonGenerator;
import jakarta.json.JsonValue;

import io.github.elasticsearch.client.util.ObjectBuilder;
import io.github.elasticsearch.client.util.WithJsonObjectBuilderBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.annotation.Nullable;
import java.io.*;
import java.util.function.*;

@JsonpDeserializable
public class Hit<TDocument> implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(Hit.class);

    @Nullable
    private final TDocument source;

    private Hit(Hit.Builder<TDocument> builder) {

        this.source = builder.source;
        this.tDocumentSerializer = builder.tDocumentSerializer;
    }

    public static <TDocument> Hit<TDocument> of(Function<Hit.Builder<TDocument>, ObjectBuilder<Hit<TDocument>>> fn) {
        return fn.apply(new Hit.Builder<>()).build();
    }

    private final JsonpSerializer<TDocument> tDocumentSerializer;

    public final TDocument source() {

        return this.source;
    }


    @SuppressWarnings({"UnusedReturnValue", "unused"})
    public static class Builder<TDocument> extends WithJsonObjectBuilderBase<Hit.Builder<TDocument>>
            implements
            ObjectBuilder<Hit<TDocument>> {

        @Nullable
        private String index;

        private String id;

        @Nullable
        private TDocument source;

        private JsonpSerializer<TDocument> tDocumentSerializer;

        public final Hit.Builder<TDocument> index(String value) {
            this.index = value;
            return this;
        }

        public final Hit.Builder<TDocument> id(String value) {
            this.id = value;
            return this;
        }


        public final Builder<TDocument> source(TDocument value) {
            this.source = value;
            return this;
        }

        public final Hit.Builder<TDocument> tDocumentSerializer(JsonpSerializer<TDocument> value) {
            this.tDocumentSerializer = value;
            return this;
        }

        @Override
        protected Hit.Builder<TDocument> self() {
            return this;
        }

        public Hit<TDocument> build() {
            _checkSingleUse();

            return new Hit<TDocument>(this);

        }
    }


    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }


    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        if (this.source != null) {
            generator.writeKey("businesses");
            JsonpUtils.serialize(this.source, generator, tDocumentSerializer, mapper);
        }
    }

    @Override
    public String toString() {
        return JsonpUtils.toString((JsonValue) this);
    }


    public static <TDocument> JsonpDeserializer<Hit<TDocument>> createHitDeserializer(
            JsonpDeserializer<TDocument> tDocumentDeserializer) {
        return ObjectBuilderDeserializer.createForObject((Supplier<Hit.Builder<TDocument>>) Hit.Builder::new,
                op -> Hit.setupHitDeserializer(op, tDocumentDeserializer));
    };

    public static final JsonpDeserializer<Hit<Object>> _DESERIALIZER = JsonpDeserializer
            .lazy(() -> createHitDeserializer(
                    new NamedDeserializer<>("org.example.clients:Deserializer:_global._types.TDocument")));

    protected static <TDocument> void setupHitDeserializer(ObjectDeserializer<Builder<TDocument>> op,
                                                           JsonpDeserializer<TDocument> tDocumentDeserializer) {
        logger.debug("Setting up HitDeserializer");
        op.add(Builder::source, tDocumentDeserializer, "businesses");

    }

}

