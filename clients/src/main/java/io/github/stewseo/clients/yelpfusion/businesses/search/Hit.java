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
public class Hit<ResultT> implements JsonpSerializable {
    @Nullable
    private final ResultT source;

    @Nullable
    private final JsonpSerializer<ResultT> resultTSerializer;

    private Hit(Builder<ResultT> builder) {
        
        this.source = builder.source;
        this.resultTSerializer = builder.resultTSerializer;
    }

    public static <ResultT> Hit<ResultT> of(Function<Builder<ResultT>, ObjectBuilder<Hit<ResultT>>> fn) {
        return fn.apply(new Builder<>()).build();
    }

    @Nullable
    public final JsonpSerializer<ResultT> resultTSerializer() {
        return resultTSerializer;
    }

    @Nullable
    public final ResultT source() {
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
            JsonpUtils.serialize(this.source, generator, resultTSerializer, mapper);
        }
    }

    @Override
    public String toString() {

        return JsonpUtils.toString(this);
    }

    public static class Builder<ResultT> extends WithJsonObjectBuilderBase<Builder<ResultT>>
            implements
            ObjectBuilder<Hit<ResultT>> {

        private JsonpSerializer<ResultT> resultTSerializer;
        
        private ResultT source;
        
        public final Builder<ResultT> resultTSerializer(JsonpSerializer<ResultT> value) {
            this.resultTSerializer = value;
            return this;
        }

        public final Builder<ResultT> source(ResultT value) {
            this.source = value;
            return this;
        }

        @Override
        protected Builder<ResultT> self() {
            return this;
        }
        
        public Hit<ResultT> build() {
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
                    new NamedDeserializer<>("io.github.stewseo.clients:Deserializer:_global.search._types.ResultT")));

    protected static <ResultT> void setupHitDeserializer(ObjectDeserializer<Builder<ResultT>> op,
                                                         JsonpDeserializer<ResultT> resultTDeserializer) {

        op.add(Builder::source, resultTDeserializer, "source");
    }
}
