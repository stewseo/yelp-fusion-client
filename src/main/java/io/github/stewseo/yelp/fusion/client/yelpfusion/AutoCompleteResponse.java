package io.github.stewseo.yelp.fusion.client.yelpfusion;

import io.github.stewseo.yelp.fusion.client.json.*;
import io.github.stewseo.yelp.fusion.client.util.ObjectBuilder;
import io.github.stewseo.yelp.fusion.client.util.WithJsonObjectBuilderBase;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.Business;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@JsonpDeserializable
public class AutoCompleteResponse implements JsonpSerializable {
    // ------------------------------ Fields ------------------------------------ //
    private final String result;

    // ------------------------------ Constructor ------------------------------------ //
    private AutoCompleteResponse(Builder builder) {
        this.result = builder.result;
    }

    public static AutoCompleteResponse of(Function<Builder, ObjectBuilder<AutoCompleteResponse>> fn) {
        return fn.apply(new AutoCompleteResponse.Builder()).build();
    }

    // ------------------------------ Methods ------------------------------------ //
    public final String result() {
        return this.result;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        generator.write(this.result);
        generator.writeEnd();

    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }


    // ------------------------------ Builder ------------------------------------ //

    public static class Builder extends WithJsonObjectBuilderBase<Builder>
            implements
            ObjectBuilder<AutoCompleteResponse> {
        private String result;

        public final Builder result(String value) {
            this.result = value;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        public AutoCompleteResponse build() {
            _checkSingleUse();
            return new AutoCompleteResponse(this);
        }
    }


    // ------------------------------ Deserializer ------------------------------------ //

    public static final JsonpDeserializer<AutoCompleteResponse> _DESERIALIZER =
            ObjectBuilderDeserializer.lazy(
                    Builder::new,
                    AutoCompleteResponse::setupAutoCompleteDeserializer);

    protected static void setupAutoCompleteDeserializer(ObjectDeserializer<AutoCompleteResponse.Builder> op) {
        op.add(Builder::result, JsonpDeserializer.stringDeserializer(), "result");
    }

}
