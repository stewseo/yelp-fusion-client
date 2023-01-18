package io.github.stewseo.clients.yelpfusion._types;

import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ApiTypeHelper;
import io.github.stewseo.clients.util.ObjectBuilder;
import jakarta.json.stream.JsonGenerator;

import java.util.function.Function;

@JsonpDeserializable
public class TermQueryParameter extends QueryParameterBase implements QueryParameterVariant {

    private final TermEnum term;

    private TermQueryParameter(Builder builder) {
        super(builder);
        System.out.println(builder.term);
        this.term = ApiTypeHelper.requireNonNull(builder.term, this, "term");
    }

    public static TermQueryParameter of(Function<Builder, ObjectBuilder<TermQueryParameter>> fn) {
        return fn.apply(new Builder()).build();
    }

    @Override
    public QueryParameter.Kind _queryFieldKind() {
        return QueryParameter.Kind.Term;
    }

	public final TermEnum term() {
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

        private TermEnum term;

        public final Builder term(TermEnum value) {
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
        op.add(Builder::term, TermEnum._DESERIALIZER, "term");
    }

}