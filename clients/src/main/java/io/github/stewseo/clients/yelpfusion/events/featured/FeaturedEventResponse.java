package io.github.stewseo.clients.yelpfusion.events.featured;

import io.github.stewseo.clients.json.JsonpDeserializable;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.util.WithJsonObjectBuilderBase;
import io.github.stewseo.clients.yelpfusion._types.Event;
import jakarta.json.stream.JsonGenerator;

import java.util.function.Function;

@JsonpDeserializable
public class FeaturedEventResponse implements JsonpSerializable {

    //----------------------------- class fields -----------------------------------//
    private final String business_id;

    //----------------------------- constructor -----------------------------------//
    private final Event event;

    private FeaturedEventResponse(Builder builder) {
        this.business_id = builder.business_id;
        this.event = builder.event;
    }

    //----------------------------- getters -----------------------------------//

    public static FeaturedEventResponse of(Function<Builder, ObjectBuilder<FeaturedEventResponse>> fn) {
        return fn.apply(new Builder()).build();
    }

    public final String business_id() {
        return business_id;
    }

    public final Event event() {
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
        public final Builder business_id(String value) {
            this.business_id = value;
            return this;
        }

        public final Builder event(Event value) {
            this.event = value;
            return this;
        }

        @Override
        protected final Builder self() {
            return this;
        }

        @Override
        public final FeaturedEventResponse build() {
            _checkSingleUse();
            return new FeaturedEventResponse(this);
        }
    }

    //----------------------------- deserialize -----------------------------------//

    public static final JsonpDeserializer<FeaturedEventResponse> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
            FeaturedEventResponse::setFeaturedEventsResponseDeserializer);

    protected static void setFeaturedEventsResponseDeserializer(ObjectDeserializer<Builder> op) {
        op.add(Builder::event, Event._DESERIALIZER, "event");
        op.add(Builder::business_id, JsonpDeserializer.stringDeserializer(), "business_id");
    }

}
