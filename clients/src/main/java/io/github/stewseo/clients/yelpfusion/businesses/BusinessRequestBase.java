package io.github.stewseo.clients.yelpfusion.businesses;

import co.elastic.clients.elasticsearch._types.mapping.CorePropertyBase;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.yelpfusion._types.RequestBase;
import jakarta.json.stream.JsonGenerator;


public abstract class BusinessRequestBase extends RequestBase implements JsonpSerializable {

    private final String id;
    private final String alias;

    protected BusinessRequestBase(AbstractBuilder<?> builder) {
        this.id = builder.id;
        this.alias = builder.alias;

    }

    public final String id() {
        return id;
    }

    public final String alias() {
        return alias;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {
        if (this.id != null) {
            generator.writeKey("id");
            generator.write(this.id);
        }
        if (this.alias != null) {
            generator.writeKey("alias");
            generator.write(this.alias);
        }
    }

    protected abstract static class AbstractBuilder<BuilderT extends AbstractBuilder<BuilderT>>
            extends
            RequestBase.AbstractBuilder<BuilderT> {
        private String id;
        private String alias;

        public final BuilderT id(String value) {
            this.id = value;
            return self();
        }

        public final BuilderT alias(String value) {
            this.alias = value;
            return self();
        }
    }
}
