package io.github.stewseo.clients.yelpfusion.businesses.search.test_model;

import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.JsonpSerializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ApiTypeHelper;
import io.github.stewseo.clients.util.WithJsonObjectBuilderBase;
import io.github.stewseo.clients.yelpfusion._types.Region;
import io.github.stewseo.clients.yelpfusion.businesses.details.Hit;

import javax.annotation.Nullable;
import java.util.List;

public abstract class ResponseBody<TDocument> implements JsonpSerializable {
    
    private final List<Hit<TDocument>> hits;

    @Nullable
    private final Integer total;

    @Nullable
    private final Region region;
    
    @Nullable
    private final JsonpSerializer<TDocument> tDocumentSerializer;

    protected ResponseBody(ResponseBody.AbstractBuilder<TDocument, ?> builder) {

        this.total = ApiTypeHelper.requireNonNull(builder.total, this, "total");
        this.region = ApiTypeHelper.requireNonNull(builder.region, this, "region");
        this.hits = ApiTypeHelper.requireNonNull(builder.hits, this, "hits");
        this.tDocumentSerializer = builder.tDocumentSerializer;

    }


    public final List<Hit<TDocument>> hits() {
        return hits;
    }

    @Nullable
    public final Integer total() {
        return total;
    }

    @Nullable
    public final Region region() {
        return region;
    }

    protected abstract static class AbstractBuilder<TDocument, BuilderT extends ResponseBody.AbstractBuilder<TDocument, BuilderT>>
            extends
            WithJsonObjectBuilderBase<BuilderT> {
        
        private List<Hit<TDocument>> hits;
        @Nullable
        private Integer total;
        @Nullable
        private Region region;

        @Nullable
        private JsonpSerializer<TDocument> tDocumentSerializer;

        public final BuilderT total(@Nullable Integer value) {
            this.total = value;
            return self();
        }

        public final BuilderT region(@Nullable Region value) {
            this.region = value;
            return self();
        }

        public final BuilderT hits(List<Hit<TDocument>> list) {
            this.hits = _listAddAll(this.hits, list);
            return self();
        }

        public final BuilderT hits(Hit<TDocument> value, Hit<TDocument>... values) {
            this.hits = _listAdd(this.hits, value, values);
            return self();
        }

        public final BuilderT tDocumentSerializer(@Nullable JsonpSerializer<TDocument> value) {
            this.tDocumentSerializer = value;
            return self();
        }
        
        protected abstract BuilderT self();

    }

    protected static <TDocument, BuilderT extends ResponseBody.AbstractBuilder<TDocument, BuilderT>> void setupResponseBodyDeserializer(
            ObjectDeserializer<BuilderT> op, JsonpDeserializer<TDocument> tDocumentDeserializer) {

        op.add(AbstractBuilder::hits, JsonpDeserializer.arrayDeserializer(Hit.createHitDeserializer(tDocumentDeserializer)), "hits");

    }
}
