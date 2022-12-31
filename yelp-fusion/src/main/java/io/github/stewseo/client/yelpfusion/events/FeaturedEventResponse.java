package io.github.stewseo.client.yelpfusion.events;

import io.github.stewseo.client.json.JsonpDeserializable;
import io.github.stewseo.client.json.JsonpDeserializer;
import io.github.stewseo.client.json.JsonpMapper;
import io.github.stewseo.client.json.JsonpSerializable;
import io.github.stewseo.client.util.ObjectBuilder;
import io.github.stewseo.client.util.WithJsonObjectBuilderBase;
import io.github.stewseo.client.json.JsonpUtils;
import jakarta.json.stream.JsonGenerator;

import java.util.List;
import java.util.function.Function;
@JsonpDeserializable
public class FeaturedEventResponse implements JsonpSerializable {
    //----------------------------- class fields -----------------------------------//
    private final String business_id;
    private final Event event;

    //----------------------------- constructor -----------------------------------//

    private FeaturedEventResponse(Builder builder) {
        this.business_id = builder.business_id;
        this.event = builder.event;
    }

    public static FeaturedEventResponse of(Function<Builder, ObjectBuilder<FeaturedEventResponse>> fn) {
        return fn.apply(new Builder()).build();
    }

    //----------------------------- getters -----------------------------------//

    public String business_id() {
        return business_id;
    }

    public Event event() {
        return event;
    }


    //----------------------------- serialize -----------------------------------//
    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {
        if (this.event != null) {
            generator.writeKey("event");
            this.event.serialize(generator, mapper);
        }
        if (this.business_id != null) {
            generator.writeKey("business_id");
            generator.write(this.business_id);
        }
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    //----------------------------- builder -----------------------------------//
    public static class Builder extends WithJsonObjectBuilderBase<Builder>
            implements
            ObjectBuilder<FeaturedEventResponse> {
        //----------------------------- builder fields -----------------------------------//
        private String business_id;

        private Event event;

        //----------------------------- setters -----------------------------------//
        public Builder business_id(String value) {
            this.business_id = value;
            return this;
        }
        public Builder event(Event value) {
            this.event = value;
            return this;
        }
        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public FeaturedEventResponse build() {
            _checkSingleUse();
            return new FeaturedEventResponse(this);
        }
    }

    //----------------------------- deserialize -----------------------------------//
    public static final JsonpDeserializer<FeaturedEventResponse> _DESERIALIZER = createFeaturedEventResponseDeserializer();
    protected static JsonpDeserializer<FeaturedEventResponse> createFeaturedEventResponseDeserializer() {
        JsonpDeserializer<List<Event>> valueDeserializer = JsonpDeserializer
                .arrayDeserializer(Event._DESERIALIZER);

        return JsonpDeserializer.of(valueDeserializer.acceptedEvents(), (parser, mapper, event) -> new Builder()
                .event(valueDeserializer.deserialize(parser, mapper, event).get(0)).build());
    }

}
