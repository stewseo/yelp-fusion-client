package org.example.yelp.fusion.client.business.search;



import org.example.elasticsearch.client.json.*;


import jakarta.json.stream.JsonGenerator;
import jakarta.json.JsonValue;

import org.example.elasticsearch.client.util.ApiTypeHelper;
import org.example.elasticsearch.client.util.ObjectBuilder;
import org.example.elasticsearch.client.util.WithJsonObjectBuilderBase;
import org.example.lowlevel.restclient.PrintUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.annotation.Nullable;
import java.io.*;
import java.util.*;
import java.util.function.*;

@JsonpDeserializable
public class Hit<TDocument> implements Serializable {

    Logger logger = LoggerFactory.getLogger(Hit.class);

    @Nullable
    private final String index;

    @Nullable
    private final String id;

    private final TDocument source;


    private Hit(Hit.Builder<TDocument> builder) {

        this.index = ApiTypeHelper.requireNonNull(builder.index, this, "index");

        this.id = ApiTypeHelper.requireNonNull(builder.id, this, "id");

        this.source = builder.source;

        this.tDocumentSerializer = builder.tDocumentSerializer;

    }

    public static <TDocument> Hit<TDocument> of(Function<Hit.Builder<TDocument>, ObjectBuilder<Hit<TDocument>>> fn) {
        return fn.apply(new Hit.Builder<>()).build();
    }

    private final JsonpSerializer<TDocument> tDocumentSerializer;

    public final String index() {
        return this.index;
    }


    public final String id() {
        return this.id;
    }


    public final TDocument source() {
        logger.info(PrintUtils.cyan("source(): " + source().getClass().getName()));

        return this.source;
    }


    @SuppressWarnings({"UnusedReturnValue", "unused"})
    public static class Builder<TDocument> extends WithJsonObjectBuilderBase<Hit.Builder<TDocument>>
            implements
            ObjectBuilder<Hit<TDocument>> {

        @Nullable
        private String index;

        private String id;

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



        public final Hit.Builder<TDocument> source(TDocument value) {
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

        generator.writeKey("index");
        generator.write(this.index);

        generator.writeKey("id");
        generator.write(this.id);


        if (this.source != null) {
            generator.writeKey("business");
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

    protected static <TDocument> void setupHitDeserializer(ObjectDeserializer<Hit.Builder<TDocument>> op,
                                                           JsonpDeserializer<TDocument> tDocumentDeserializer) {

        op.add(Hit.Builder::index, JsonpDeserializer.stringDeserializer(), "index");
        op.add(Hit.Builder::id, JsonpDeserializer.stringDeserializer(), "id");
        op.add(Hit.Builder::source, tDocumentDeserializer, "businesses");

    }

}

