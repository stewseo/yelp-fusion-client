//package org.example.elasticsearch.client.elasticsearch.core.termvector;
//
//import ch.qos.logback.core.subst.Token;
//
//import jakarta.json.stream.JsonGenerator;
//import org.example.elasticsearch.client.json.*;
//import org.example.elasticsearch.client.util.ApiTypeHelper;
//import org.example.elasticsearch.client.util.ObjectBuilder;
//import org.example.elasticsearch.client.util.WithJsonObjectBuilderBase;
//
//import java.lang.Double;
//import java.lang.Integer;
//import java.util.List;
//import java.util.function.Function;
//import javax.annotation.Nullable;
//
//@JsonpDeserializable
//public class Term implements JsonpSerializable {
//    @Nullable
//    private final Integer docFreq;
//
//    @Nullable
//    private final Double score;
//
//    private final int termFreq;
//
//    private final List<Token> tokens;
//
//    @Nullable
//    private final Integer ttf;
//
//    private Term(Builder builder) {
//
//        this.docFreq = builder.docFreq;
//        this.score = builder.score;
//        this.termFreq = ApiTypeHelper.requireNonNull(builder.termFreq, this, "termFreq");
//        this.tokens = ApiTypeHelper.unmodifiable(builder.tokens);
//        this.ttf = builder.ttf;
//
//    }
//
//    public static Term of(Function<Builder, ObjectBuilder<Term>> fn) {
//        return fn.apply(new Builder()).build();
//    }
//
//    @Nullable
//    public final Integer docFreq() {
//        return this.docFreq;
//    }
//
//    @Nullable
//    public final Double score() {
//        return this.score;
//    }
//
//
//    public final int termFreq() {
//        return this.termFreq;
//    }
//
//    public final List<Token> tokens() {
//        return this.tokens;
//    }
//
//    @Nullable
//    public final Integer ttf() {
//        return this.ttf;
//    }
//
//    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
//        generator.writeStartObject();
//        serializeInternal(generator, mapper);
//        generator.writeEnd();
//    }
//
//    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {
//
//        if (this.docFreq != null) {
//            generator.writeKey("doc_freq");
//            generator.write(this.docFreq);
//
//        }
//        if (this.score != null) {
//            generator.writeKey("score");
//            generator.write(this.score);
//
//        }
//        generator.writeKey("term_freq");
//        generator.write(this.termFreq);
//
//
//        if (this.ttf != null) {
//            generator.writeKey("ttf");
//            generator.write(this.ttf);
//
//        }
//
//    }
//
//    @Override
//    public String toString() {
//        return JsonpUtils.toString(this);
//    }
//
//    // ---------------------------------------------------------------------------------------------
//
//
//    public static class Builder extends WithJsonObjectBuilderBase<Builder> implements ObjectBuilder<Term> {
//        @Nullable
//        private Integer docFreq;
//
//        @Nullable
//        private Double score;
//
//        private Integer termFreq;
//
//        @Nullable
//        private List<Token> tokens;
//
//        @Nullable
//        private Integer ttf;
//
//        public final Builder docFreq(@Nullable Integer value) {
//            this.docFreq = value;
//            return this;
//        }
//
//        public final Builder score(@Nullable Double value) {
//            this.score = value;
//            return this;
//        }
//
//        public final Builder termFreq(int value) {
//            this.termFreq = value;
//            return this;
//        }
//
//        public final Builder tokens(List<Token> list) {
//            this.tokens = ObjectBuilderBase._listAddAll(this.tokens, list);
//            return this;
//        }
//        public final Builder tokens(Token value, Token... values) {
//            this.tokens = ObjectBuilderBase._listAdd(this.tokens, value, values);
//            return this;
//        }
//
//        public final Builder tokens(Function<Token.Builder, ObjectBuilder<Token>> fn) {
//            return tokens(fn.apply(new Token.Builder()).build());
//        }
//
//        public final Builder ttf(@Nullable Integer value) {
//            this.ttf = value;
//            return this;
//        }
//
//        @Override
//        protected Builder self() {
//            return this;
//        }
//
//        public Term build() {
//            _checkSingleUse();
//
//            return new Term(this);
//        }
//    }
//
//    // ---------------------------------------------------------------------------------------------
//
//    public static final JsonpDeserializer<Term> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
//            Term::setupTermDeserializer);
//
//    protected static void setupTermDeserializer(ObjectDeserializer<Term.Builder> op) {
//
//        op.add(Builder::docFreq, JsonpDeserializer.integerDeserializer(), "doc_freq");
//        op.add(Builder::score, JsonpDeserializer.doubleDeserializer(), "score");
//        op.add(Builder::termFreq, JsonpDeserializer.integerDeserializer(), "term_freq");
//        op.add(Builder::tokens, JsonpDeserializer.arrayDeserializer(Token._DESERIALIZER), "tokens");
//        op.add(Builder::ttf, JsonpDeserializer.integerDeserializer(), "ttf");
//
//    }
//
//}