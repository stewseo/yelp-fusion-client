package io.github.stewseo.clients.yelpfusion;


import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.WithJsonObjectBuilderBase;
import io.github.stewseo.clients.yelpfusion._types.Location;
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;

@JsonpDeserializable
public abstract class ResultBase implements JsonpSerializable {

    @Nullable
    private final String id;

    @Nullable
    private final String alias;

    @Nullable
    private final String url;

    @Nullable
    private final Location location;


    protected ResultBase(AbstractBuilder<?> builder) {
        this.id = builder.id;
        this.alias = builder.alias;
        this.url = builder.url;
        this.location = builder.location;
    }

    public final String id() {
        return id;
    }
    public final String alias() {
        return alias;
    }
    public final String url() {
        return url;
    }

    @Nullable
    public Location location() {
        return location;
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
        if (this.url != null) {
            generator.writeKey("url");
            generator.write(this.url);
        }

        if (this.location != null) {
            generator.writeKey("location");
            location.serialize(generator, mapper);
        }
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    protected abstract static class AbstractBuilder<BuilderT extends AbstractBuilder<BuilderT>>
            extends
            WithJsonObjectBuilderBase<BuilderT> {

        private String id;
        private String alias;
        private String url;
        private Location location;

        public final BuilderT id(String value) {
            this.id = value;
            return self();
        }

        public final BuilderT alias(String value) {
            this.alias = value;
            return self();
        }

        public final BuilderT url(@Nullable String value) {
            this.url = value;
            return self();
        }
        public final BuilderT location(@Nullable Location value) {
            this.location = value;
            return self();
        }
        protected abstract BuilderT self();

    }

    protected static <BuilderT extends AbstractBuilder<BuilderT>> void setupResultBaseDeserializer(
            ObjectDeserializer<BuilderT> op) {

        op.add(AbstractBuilder::id, JsonpDeserializer.stringDeserializer(), "id");
        op.add(AbstractBuilder::alias, JsonpDeserializer.stringDeserializer(), "alias");
        op.add(AbstractBuilder::location, Location._DESERIALIZER, "location");
        op.add(AbstractBuilder::url, JsonpDeserializer.stringDeserializer(), "url");
    }

}
