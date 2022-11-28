package org.example.yelp.fusion.client.business;


import co.elastic.clients.elasticsearch.ingest.GetPipelineResponse;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.example.elasticsearch.client.json.*;
import org.example.elasticsearch.client.util.ObjectBuilder;
import org.example.elasticsearch.client.util.WithJsonObjectBuilderBase;
import org.example.yelp.fusion.client.business.model.Business;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@JsonpDeserializable
public class BusinessDetailsResponse_ implements JsonpSerializable {
        // ------------------------------ Fields ------------------------------------ //
        private final List<Business> result;

        // ------------------------------ Constructor ------------------------------------ //
        private BusinessDetailsResponse_(Builder builder) {
            this.result = builder.result;
        }

        public static BusinessDetailsResponse_ of(Function<BusinessDetailsResponse_.Builder, ObjectBuilder<BusinessDetailsResponse_>> fn) {
            return fn.apply(new BusinessDetailsResponse_.Builder()).build();
        }

        // ------------------------------ Methods ------------------------------------ //
        public final List<Business> result() {
            return this.result;
        }

        public void serialize(JsonGenerator generator, JsonpMapper mapper) {
            generator.writeStartObject();
            for (Business item0 : this.result) {
                item0.serialize(generator, mapper);
            }
            generator.writeEnd();

        }

        @Override
        public String toString() {
            return JsonpUtils.toString(this);
        }


        // ------------------------------ Builder ------------------------------------ //

        public static class Builder extends WithJsonObjectBuilderBase<Builder>
                implements
                ObjectBuilder<BusinessDetailsResponse_> {
            private List<Business> result = new ArrayList<>();

            public final Builder result(List<Business> list) {
                this.result = _listAddAll(this.result, list);
                return this;
            }

            public final Builder result(Business value) {
                this.result = _listAdd(this.result, value);
                return this;
            }

            public final Builder result(Function<Business.Builder, ObjectBuilder<Business>> fn) {
                return result(fn.apply(new Business.Builder()).build());
            }

            @Override
            public Builder withJson(JsonParser parser, JsonpMapper mapper) {

                List<Business> value = (List<Business>) JsonpDeserializer
                        .arrayDeserializer(Business._DESERIALIZER).deserialize(parser, mapper);

                return this.result(value);
            }

            @Override
            protected Builder self() {
                return this;
            }

            public BusinessDetailsResponse_ build() {
                _checkSingleUse();
                return new BusinessDetailsResponse_(this);
            }
        }


        // ------------------------------ Deserializer ------------------------------------ //

        public static final JsonpDeserializer<BusinessDetailsResponse_> _DESERIALIZER = createBusinessDetailsResponse_Deserializer();
        protected static JsonpDeserializer<BusinessDetailsResponse_> createBusinessDetailsResponse_Deserializer() {

            JsonpDeserializer<List<Business>> valueDeserializer = JsonpDeserializer
                    .arrayDeserializer(Business._DESERIALIZER);

            return JsonpDeserializer.of(valueDeserializer.acceptedEvents(), (parser, mapper, event) -> new BusinessDetailsResponse_.Builder()
                    .result(valueDeserializer.deserialize(parser, mapper, event)).build());
        }

    }
