package org.example.yelp.fusion.client.business;

import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.example.elasticsearch.client.json.*;
import org.example.elasticsearch.client.util.ObjectBuilder;
import org.example.elasticsearch.client.util.WithJsonObjectBuilderBase;
import org.example.yelp.fusion.client.business.model.Business;
import org.example.yelp.fusion.client.business.model.Region;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@JsonpDeserializable
public class BusinessSearchResponse_ implements JsonpSerializable {
    // ------------------------------ Fields ------------------------------------ //
    private final List<Business> result;

    private final Integer total;

    private final Region region;


    // ------------------------------ Constructor ------------------------------------ //
    private BusinessSearchResponse_(BusinessSearchResponse_.Builder builder) {
        this.region = builder.region;
        this.total = builder.total;
        this.result = builder.result;
    }

    public static BusinessSearchResponse_ of(Function<BusinessSearchResponse_.Builder, ObjectBuilder<BusinessSearchResponse_>> fn) {
        return fn.apply(new BusinessSearchResponse_.Builder()).build();
    }

    // ------------------------------ Methods ------------------------------------ //
    public final List<Business> result() {
        return this.result;
    }
    public Integer total() {
        return this.total;
    }
    public Region region() {
        return this.region;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }
    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {
        if (this.result != null) {
            generator.writeStartObject();
            for (Business item0 : this.result) {
                item0.serialize(generator, mapper);
            }
            generator.writeEnd();
        }

        if(this.region != null) {
            generator.writeKey("region");
            region.serialize(generator, mapper);
        }

        if(this.total != null) {
            generator.writeKey("total");
            generator.write(this.total);
        }
    }
    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }


    // ------------------------------ Builder ------------------------------------ //

    public static class Builder extends WithJsonObjectBuilderBase<BusinessSearchResponse_.Builder>
            implements
            ObjectBuilder<BusinessSearchResponse_> {
        private List<Business> result = new ArrayList<>();
        private Integer total;
        private Region region;

        public final BusinessSearchResponse_.Builder total(Integer total) {
            this.total = total;
            return this;
        }
        public final BusinessSearchResponse_.Builder region(Region region) {
            this.region = region;
            return this;
        }

        public final BusinessSearchResponse_.Builder result(List<Business> list) {
            this.result = _listAddAll(this.result, list);
            return this;
        }

        public final BusinessSearchResponse_.Builder result(Business value) {
            this.result = _listAdd(this.result, value);
            return this;
        }

        public final BusinessSearchResponse_.Builder result(Function<Business.Builder, ObjectBuilder<Business>> fn) {
            return result(fn.apply(new Business.Builder()).build());
        }

        @Override
        public BusinessSearchResponse_.Builder withJson(JsonParser parser, JsonpMapper mapper) {

            List<Business> value = (List<Business>) JsonpDeserializer
                    .arrayDeserializer(Business._DESERIALIZER).deserialize(parser, mapper);

            return this.result(value);
        }

        @Override
        protected BusinessSearchResponse_.Builder self() {
            return this;
        }

        public BusinessSearchResponse_ build() {
            _checkSingleUse();
            return new BusinessSearchResponse_(this);
        }
    }


    // ------------------------------ Deserializer ------------------------------------ //

//    public static final JsonpDeserializer<BusinessSearchResponse_> _DESERIALIZER = createBusinessSearchResponse_Deserializer();
    protected static JsonpDeserializer<BusinessSearchResponse_> createBusinessSearchResponse_Deserializer() {

        JsonpDeserializer<List<Business>> valueDeserializer = JsonpDeserializer
                .arrayDeserializer(Business._DESERIALIZER);

        return JsonpDeserializer.of(valueDeserializer.acceptedEvents(), (parser, mapper, event) -> new BusinessSearchResponse_.Builder()
                .result(valueDeserializer.deserialize(parser, mapper, event)).build());
    }

    public static JsonpDeserializer<BusinessSearchResponse_> createBusinessSearchResponse_Deserializer(
            JsonpDeserializer<BusinessSearchResponse_> tDeserializer) {
        return ObjectBuilderDeserializer.createForObject((Supplier<BusinessSearchResponse_.Builder>) BusinessSearchResponse_.Builder::new,
                op -> BusinessSearchResponse_.setupBusinessSearchResponse_Deserializer(op, tDeserializer));
    }

    public static final JsonpDeserializer<BusinessSearchResponse_> _DESERIALIZER = JsonpDeserializer
            .lazy(() -> createBusinessSearchResponse_Deserializer(
                    new NamedDeserializer<>("org.example.clients:Deserializer:_global.search.Business")));
    protected static void setupBusinessSearchResponse_Deserializer(ObjectDeserializer<BusinessSearchResponse_.Builder> op,
                                                           JsonpDeserializer<BusinessSearchResponse_> tDeserializer) {

        op.add(BusinessSearchResponse_.Builder::result, JsonpDeserializer.arrayDeserializer(Business._DESERIALIZER), "businesses");
        op.add(BusinessSearchResponse_.Builder::total, JsonpDeserializer.integerDeserializer(), "total");
        op.add(BusinessSearchResponse_.Builder::region, Region._DESERIALIZER, "region");

    }
    
}
