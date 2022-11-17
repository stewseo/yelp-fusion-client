package org.example.yelp.fusion.client.business.search;

import jakarta.json.JsonValue;
import org.example.elasticsearch.client.elasticsearch.core.HitsMetadata;

import org.example.elasticsearch.client.json.*;

import org.example.elasticsearch.client.util.*;
import org.example.lowlevel.restclient.*;
import org.example.yelp.fusion.client.business.*;
import jakarta.json.stream.JsonGenerator;
import org.example.yelp.fusion.client.business.Region;

import javax.swing.plaf.synth.*;
import java.util.*;
import java.util.function.*;


public abstract class ResponseBody<TDocument> implements JsonpSerializable {

    // ---------------------------------------------------------------------------------------------
    private final Integer total;
    private final List<Business> businesses;
    private final Object error;

    private final Region region;


    private final JsonpSerializer<TDocument> tDocumentSerializer;

    // ---------------------------------------------------------------------------------------------

    public Integer total() {
        return total;
    }

    public Object error() {
        return error;
    }

    public List<Business> businesses() {
        return businesses;
    }

    public Region region() {
        return region;
    }

    
    public JsonpSerializer<TDocument> tDocumentSerializer() {
        return tDocumentSerializer;
    }

    protected ResponseBody(AbstractBuilder<TDocument, ?> builder) {
        this.total = builder.total;
        this.error = builder.error;
        this.businesses = builder.businesses;
        this.tDocumentSerializer = builder.tDocumentSerializer;
        this.region = builder.region;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {

        if (this.businesses != null) {
            generator.writeKey("businesses");
            this.businesses.forEach(e-> generator.write((JsonValue) e));
        }
        if (this.total != 0) {
            generator.writeKey("total");
            generator.write(this.total);

        }
        if (this.error != null) {
            generator.writeKey("error");
            generator.write((JsonValue) this.error);
        }

        if (this.region != null) {
            generator.writeKey("region");
            generator.writeStartObject();
            generator.writeEnd();
        }
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }


    protected abstract static class AbstractBuilder<TDocument, BuilderT extends AbstractBuilder<TDocument, BuilderT>>
            extends
            WithJsonObjectBuilderBase<BuilderT> {
        private Integer total;
        private Object error;
        private List<Business> businesses;
        private Region region;

        private JsonpSerializer<TDocument> tDocumentSerializer;

        public final BuilderT total(Integer value) {
            this.total = value;
            return self();
        }
        public final BuilderT region(Region value) {
            this.region = value;
            return self();
        }

        public final BuilderT businesses(Function<Business.Builder, ObjectBuilder<Business>> fn) {
            return businesses(fn.apply(new Business.Builder()).build());
        }

        public final BuilderT businesses(List<Business> list) {
            this.businesses = _listAddAll(this.businesses, list);
            return self();
        }

        public final BuilderT businesses(Business value, Business... values) {
            this.businesses = _listAdd(this.businesses, value, values);
            return self();
        }

        public final BuilderT error(Object value) {
            this.error = value;
            return self();
        }

        public final BuilderT tDocumentSerializer(JsonpSerializer<TDocument> value) {
            this.tDocumentSerializer = value;
            return self();
        }
        protected abstract BuilderT self();

    }

    // ---------------------------------------------------------------------------------------------
    protected static <TDocument, BuilderT extends AbstractBuilder<TDocument, BuilderT>> void setupResponseBodyDeserializer(
            ObjectDeserializer<BuilderT> op, JsonpDeserializer<TDocument> tDocumentDeserializer) {

        PrintUtils.green("ResponseBody setupResponseBodyDeserializer");
        op.add(AbstractBuilder::businesses, JsonpDeserializer.arrayDeserializer(Business._DESERIALIZER), "businesses");
        op.add(AbstractBuilder::total, JsonpDeserializer.integerDeserializer(), "total");
        op.add(AbstractBuilder::region, Region._DESERIALIZER, "region");
        op.add(AbstractBuilder::error, JsonpDeserializer.stringMapDeserializer(JsonpDeserializer.stringDeserializer()), "error");
    }

}
