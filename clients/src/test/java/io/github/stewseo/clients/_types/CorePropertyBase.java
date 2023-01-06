package io.github.stewseo.clients._types;

import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.ObjectDeserializer;
import jakarta.json.stream.JsonGenerator;

public abstract class CorePropertyBase extends PropertyBase {


    private final String similarity;

    protected CorePropertyBase(CorePropertyBase.AbstractBuilder<?> builder) {
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

    protected abstract static class AbstractBuilder<BuilderT extends CorePropertyBase.AbstractBuilder<BuilderT>>
            extends
            PropertyBase.AbstractBuilder<BuilderT> {

        private String similarity;
        
        public final BuilderT similarity(String value) {
            this.similarity = value;
            return self();
        }
        
    }

    // ---------------------------------------------------------------------------------------------
    protected static <BuilderT extends AbstractBuilder<BuilderT>> void setupCorePropertyBaseDeserializer(
            ObjectDeserializer<BuilderT> op) {
        PropertyBase.setupPropertyBaseDeserializer(op);
        op.add(CorePropertyBase.AbstractBuilder::similarity, JsonpDeserializer.stringDeserializer(), "similarity");
    }

}
