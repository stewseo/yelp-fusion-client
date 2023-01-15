package io.github.stewseo.clients.yelpfusion.businesses.search;

import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.yelpfusion.ResultBase;
import jakarta.json.stream.JsonGenerator;

import javax.annotation.Nullable;

public abstract class SearchResultBase extends ResultBase {
    @Nullable 
    private final String image_url;
    
    protected SearchResultBase(AbstractBuilder<?> builder) {
        super(builder);
        this.image_url = builder.image_url;
    }

    public final String image_url() {
        return image_url;
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        super.serializeInternal(generator, mapper);
        if (this.image_url != null) {
            generator.writeKey("image_url");
            generator.write(this.image_url);
        }
    }

    protected abstract static class AbstractBuilder<BuilderT extends AbstractBuilder<BuilderT>>
            extends
            ResultBase.AbstractBuilder<BuilderT> {

        @Nullable
        private String image_url;

        /**
         * name: {@code image_url}
         */
        public final BuilderT image_url(@Nullable String value) {
            this.image_url = value;
            return self();
        }
    }

    protected static <BuilderT extends AbstractBuilder<BuilderT>> void setupSearchResultBaseBaseDeserializer(
            ObjectDeserializer<BuilderT> op) {

        ResultBase.setupResultBaseDeserializer(op);

        op.add(SearchResultBase.AbstractBuilder::image_url, JsonpDeserializer.stringDeserializer(), "image_url");

    }
}
