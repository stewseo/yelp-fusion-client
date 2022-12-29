package io.github.stewseo.client.yelpfusion.events;

import io.github.stewseo.client._types.RequestBase;
import io.github.stewseo.client.json.JsonpDeserializer;
import io.github.stewseo.client.json.JsonpMapper;
import io.github.stewseo.client.json.JsonpSerializable;
import io.github.stewseo.client.json.JsonpUtils;
import io.github.stewseo.client.json.ObjectBuilderDeserializer;
import io.github.stewseo.client.json.ObjectDeserializer;
import io.github.stewseo.client.util.ApiTypeHelper;
import io.github.stewseo.client.util.ObjectBuilder;
import io.github.stewseo.client.util.ObjectBuilderBase;
import jakarta.json.stream.JsonGenerator;

import java.util.List;
import java.util.function.Function;

//Returns events that match search criteria
public class EventSearchResponse extends RequestBase implements JsonpSerializable {

    private final List<Event> events;

    private final Integer total;

    private EventSearchResponse(Builder builder) {
        this.events = builder.events;
        this.total = builder.total;
    }

    public static EventSearchResponse of(Function<EventSearchResponse.Builder, ObjectBuilder<EventSearchResponse>> fn) {
        return fn.apply(new EventSearchResponse.Builder()).build();
    }

    public List<Event> events() {
        return this.events;
    }

    public Integer total() {
        return this.total;
    }

    public void serialize(JsonGenerator generator, JsonpMapper mapper) {
        generator.writeStartObject();
        serializeInternal(generator, mapper);
        generator.writeEnd();
    }

    protected void serializeInternal(JsonGenerator generator, JsonpMapper mapper) {
        if (ApiTypeHelper.isDefined(this.events)) {
            generator.writeKey("events");
            generator.writeStartArray();
            for (Event item0 : this.events) {
                item0.serialize(generator, mapper);
            }
            generator.writeEnd();
        }
        if (this.total != null) {
            generator.writeKey("total");
            generator.write(this.total);
        }
    }

    @Override
    public String toString() {
        return JsonpUtils.toString(this);
    }

    public static class Builder extends RequestBase.AbstractBuilder<EventSearchResponse.Builder>
            implements
            ObjectBuilder<EventSearchResponse> {

        private List<Event> events;
        private Integer total;

        public Builder events(List<Event> events) {
            this.events = ObjectBuilderBase._listAddAll(this.events, events);
            return this;
        }
        public Builder events(Event event, Event ... events) {
            this.events = ObjectBuilderBase._listAdd(this.events, event, events);
            return this;
        }
        public final Builder categories(Function<Event.Builder, ObjectBuilder<Event>> fn) {
            return events(fn.apply(new Event.Builder()).build());
        }
        public Builder total(Integer total) {
            this.total = total;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public EventSearchResponse build() {
            _checkSingleUse();
            return new EventSearchResponse(this);
        }
    }

    public static final JsonpDeserializer<EventSearchResponse> _DESERIALIZER = ObjectBuilderDeserializer.lazy(EventSearchResponse.Builder::new,
            EventSearchResponse::setEventsResponseDeserializer);

    protected static void setEventsResponseDeserializer(ObjectDeserializer<Builder> op) {
        op.add(Builder::events, JsonpDeserializer.arrayDeserializer(Event._DESERIALIZER), "events");
        op.add(Builder::total, JsonpDeserializer.integerDeserializer(), "total");
    }
}

