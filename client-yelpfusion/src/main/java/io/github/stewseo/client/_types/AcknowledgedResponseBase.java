package io.github.stewseo.client._types;

import io.github.stewseo.client.json.JsonpDeserializer;
import io.github.stewseo.client.json.JsonpMapper;
import io.github.stewseo.client.json.JsonpSerializable;
import io.github.stewseo.client.json.JsonpUtils;
import io.github.stewseo.client.json.ObjectDeserializer;
import io.github.stewseo.client.util.ApiTypeHelper;
import io.github.stewseo.client.util.WithJsonObjectBuilderBase;
import jakarta.json.stream.JsonGenerator;


public abstract class AcknowledgedResponseBase implements AcknowledgedResponse, JsonpSerializable {
    private final boolean acknowledged;

    // ---------------------------------------------------------------------------------------------

    protected AcknowledgedResponseBase(AbstractBuilder<?> builder) {

        this.acknowledged = ApiTypeHelper.requireNonNull(builder.acknowledged, this, "acknowledged");

    }

    // ---------------------------------------------------------------------------------------------
    protected static <BuilderT extends AbstractBuilder<BuilderT>> void setupAcknowledgedResponseBaseDeserializer(
            ObjectDeserializer<BuilderT> op) {

        op.add(AbstractBuilder::acknowledged, JsonpDeserializer.booleanDeserializer(), "acknowledged");

    }

    public final boolean acknowledged() {
        return this.acknowledged;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        generator.writeKey("acknowledged");
        generator.write(this.acknowledged);

    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    protected abstract static class AbstractBuilder<BuilderT extends AbstractBuilder<BuilderT>>
            extends
            WithJsonObjectBuilderBase<BuilderT> {
        private Boolean acknowledged;

        public final BuilderT acknowledged(boolean value) {
            this.acknowledged = value;
            return self();
        }

        protected abstract BuilderT self();

    }

}