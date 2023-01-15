package io.github.stewseo.clients._type;

import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ObjectBuilder;
import jakarta.json.stream.JsonGenerator;

import java.util.function.Function;

@JsonpDeserializable
public class TermQueryParameter extends QueryParameterBase implements QueryParameterVariant {

    private final Term term;

    private TermQueryParameter(Builder builder) {
        super(builder);
        this.term = builder.term;
    }

    public static TermQueryParameter of(Function<Builder, ObjectBuilder<TermQueryParameter>> fn) {
        return fn.apply(new Builder()).build();
    }

    @Override
    public QueryParameter.Kind _queryFieldKind() {
        return QueryParameter.Kind.Term;
    }

	public final Term term() {
		return this.term;
	}

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        super.serializeInternal(generator, mapper);

        if(this.term != null) {
            generator.writeKey("term");
            term.serialize(generator, mapper);
        }

    }

    // ---------------------------------------------------------------------------------------------


    public static class Builder extends AbstractBuilder<Builder>
            implements
            ObjectBuilder<TermQueryParameter> {


        private Term term;

        public final Builder term(Term value) {
            this.term = value;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        public TermQueryParameter build() {
            _checkSingleUse();

            return new TermQueryParameter(this);
        }
    }

    // ---------------------------------------------------------------------------------------------

    public static final JsonpDeserializer<TermQueryParameter> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
            TermQueryParameter::setupTermVariantDeserializer);

    protected static void setupTermVariantDeserializer(ObjectDeserializer<Builder> op) {
        setupQueryFieldBaseDeserializer(op);
        op.add(Builder::term, Term._DESERIALIZER, "term");
    }

}