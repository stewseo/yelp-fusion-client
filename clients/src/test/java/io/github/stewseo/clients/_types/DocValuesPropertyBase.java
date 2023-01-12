package io.github.stewseo.clients._types;

import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.ObjectDeserializer;
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;

public abstract class DocValuesPropertyBase extends CorePropertyBase {
    @Nullable
    private final Boolean docValues;

    // ---------------------------------------------------------------------------------------------

    protected DocValuesPropertyBase(AbstractBuilder<?> builder) {
        super(builder);
        this.docValues = builder.docValues;
    }

    @Nullable
    public final Boolean docValues() {
        return this.docValues;
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        super.serializeInternal(generator, mapper);
        if (this.docValues != null) {
            generator.writeKey("doc_values");
            generator.write(this.docValues);
        }
    }

    protected abstract static class AbstractBuilder<BuilderT extends AbstractBuilder<BuilderT>>
            extends
            CorePropertyBase.AbstractBuilder<BuilderT> {
        @Nullable
        private Boolean docValues;

        /**
         * API name: {@code doc_values}
         */
        public final BuilderT docValues(@Nullable Boolean value) {
            this.docValues = value;
            return self();
        }

    }

    // ---------------------------------------------------------------------------------------------
    protected static <BuilderT extends AbstractBuilder<BuilderT>> void setupDocValuesPropertyBaseDeserializer(
            ObjectDeserializer<BuilderT> op) {
        CorePropertyBase.setupCorePropertyBaseDeserializer(op);
        op.add(AbstractBuilder::docValues, JsonpDeserializer.booleanDeserializer(), "doc_values");

    }

}
