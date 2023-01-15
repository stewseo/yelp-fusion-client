package io.github.stewseo.clients.yelpfusion.businesses.search;


import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.JsonpSerializer;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.json.NamedDeserializer;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.util.WithJsonObjectBuilderBase;
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;
import java.util.function.Function;
import java.util.function.Supplier;

@JsonpDeserializable
public class Hit<TDocument> implements JsonpSerializable {
    
    @Nullable
    private final JsonpSerializer<TDocument> tDocumentSerializer;

    @Nullable
    private final TDocument source;

    private Hit(Builder<TDocument> builder) {
        
        this.source = builder.source;
        this.tDocumentSerializer = builder.tDocumentSerializer;
    }

    public static <TDocument> Hit<TDocument> of(Function<Builder<TDocument>, ObjectBuilder<Hit<TDocument>>> fn) {
        return fn.apply(new Builder<>()).build();
    }

    @Nullable
    public final JsonpSerializer<TDocument> tDocumentSerializer() {
        return tDocumentSerializer;
    }

    @Nullable
    public final TDocument source() {
        return source;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {
        
        if (this.source != null) {
            generator.writeKey("source");
            JsonpUtils.serialize(this.source, generator, tDocumentSerializer, mapper);
        }
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    public static class Builder<TDocument> extends WithJsonObjectBuilderBase<Builder<TDocument>>
            implements
            ObjectBuilder<Hit<TDocument>> {

        private JsonpSerializer<TDocument> tDocumentSerializer;
        
        private TDocument source;
        
        public final Builder<TDocument> tDocumentSerializer(JsonpSerializer<TDocument> value) {
            this.tDocumentSerializer = value;
            return this;
        }

        public final Builder<TDocument> source(TDocument value) {
            this.source = value;
            return this;
        }

        @Override
        protected Builder<TDocument> self() {
            return this;
        }
        
        public Hit<TDocument> build() {
            _checkSingleUse();

            return new Hit<>(this);
        }
    }

    public static <TDocument> JsonpDeserializer<Hit<TDocument>> createHitDeserializer(
            JsonpDeserializer<TDocument> tDocumentDeserializer) {
        return ObjectBuilderDeserializer.createForObject((Supplier<Builder<TDocument>>) Builder::new,
                op -> Hit.setupHitDeserializer(op, tDocumentDeserializer));
    }

    public static final JsonpDeserializer<Hit<Object>> _DESERIALIZER = JsonpDeserializer
            .lazy(() -> createHitDeserializer(
                    new NamedDeserializer<>("io.github.stewseo.clients:Deserializer:_global.searchBusinesses._types.ResultT")));

    protected static <TDocument> void setupHitDeserializer(ObjectDeserializer<Builder<TDocument>> op,
                                                           JsonpDeserializer<TDocument> tDocumentDeserializer) {

        op.add(Builder::source, tDocumentDeserializer, "source");
    }
}
