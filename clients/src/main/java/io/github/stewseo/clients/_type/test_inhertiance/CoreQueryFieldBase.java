package io.github.stewseo.clients._type.test_inhertiance;

import io.github.stewseo.clients._type.QueryParameterBase;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.ObjectDeserializer;
import jakarta.json.stream.JsonGenerator;

public abstract class CoreQueryFieldBase extends QueryParameterBase {

    private final String similarity;

    protected CoreQueryFieldBase(AbstractBuilder<?> builder) {
        super(builder);
        
        this.similarity = builder.similarity;
    }

    public final String similarity() {
        return this.similarity;
    }
    
    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {
        
        if (this.similarity != null) {
            generator.writeKey("similarity");
            generator.write(this.similarity);

        }

    }

    protected abstract static class AbstractBuilder<BuilderT extends AbstractBuilder<BuilderT>>
            extends
            QueryParameterBase.AbstractBuilder<BuilderT> {

        private String similarity;
        
        public final BuilderT similarity(String value) {
            this.similarity = value;
            return self();
        }
        
    }

    // ---------------------------------------------------------------------------------------------
    protected static <BuilderT extends AbstractBuilder<BuilderT>> void setupCorePropertyBaseDeserializer(
            ObjectDeserializer<BuilderT> op) {
        setupQueryFieldBaseDeserializer(op);
        op.add(AbstractBuilder::similarity, JsonpDeserializer.stringDeserializer(), "similarity");
    }

}
