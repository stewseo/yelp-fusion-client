package io.github.stewseo.clients.yelpfusion.businesses;

import co.elastic.clients.elasticsearch.core.SearchRequest;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.yelpfusion._types.RequestBase;
import jakarta.json.stream.JsonGenerator;

public abstract class BusinessesRequestBase extends RequestBase implements JsonpSerializable {

    private final String businessId;
    private final String alias;

    protected BusinessesRequestBase(AbstractBuilder<?> builder) {
        this.businessId = builder.id;
        this.alias = builder.alias;

    }

    public final String id() {
        return businessId;
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
        if (this.businessId != null) {
            generator.writeKey("id");
            generator.write(this.businessId);
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

      protected static <BuilderT extends AbstractBuilder<BuilderT>> void setupBusinessRequestBaseDeserializer(
            ObjectDeserializer<BuilderT> op) {

          op.add(AbstractBuilder::id, JsonpDeserializer.stringDeserializer(), "id");
          op.add(AbstractBuilder::alias, JsonpDeserializer.stringDeserializer(), "alias");
      }

}
