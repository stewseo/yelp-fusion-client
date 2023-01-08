package io.github.stewseo.clients.yelpfusion.events.search;

import io.github.stewseo.clients.yelpfusion._types.RequestBase;
import io.github.stewseo.clients.json.JsonpDeserializer;
import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.JsonpSerializable;
import io.github.stewseo.clients.json.JsonpUtils;
import io.github.stewseo.clients.json.ObjectBuilderDeserializer;
import io.github.stewseo.clients.json.ObjectDeserializer;
import io.github.stewseo.clients.util.ApiTypeHelper;
import io.github.stewseo.clients.util.ObjectBuilder;
import io.github.stewseo.clients.util.ObjectBuilderBase;
import io.github.stewseo.clients.yelpfusion._types.Event;
import jakarta.json.stream.JsonGenerator;

import java.util.List;
import java.util.function.Function;

//Returns events that match search criteria
public class SearchEventsResponse extends RequestBase implements JsonpSerializable {

    private final List<Event> events;

    private final Integer total;

    private SearchEventsResponse(Builder builder) {
        this.events = ApiTypeHelper.unmodifiable(builder.events);
        this.total = builder.total;
    }

    public static SearchEventsResponse of(Function<Builder, ObjectBuilder<SearchEventsResponse>> fn) {
        return fn.apply(new Builder()).build();
    }

    public final List<Event> events() {
        return this.events;
    }

    public final Integer total() {
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

    public static class Builder extends AbstractBuilder<Builder>
            implements
            ObjectBuilder<SearchEventsResponse> {

        private List<Event> events;
        private Integer total;

        public Builder events(List<Event> events) {
            this.events = ObjectBuilderBase._listAddAll(this.events, events);
            return this;
        }

        public Builder events(Event event, Event... events) {
            this.events = ObjectBuilderBase._listAdd(this.events, event, events);
            return this;
        }

        public final Builder events(Function<Event.Builder, ObjectBuilder<Event>> fn) {
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
        public SearchEventsResponse build() {
            _checkSingleUse();
            return new SearchEventsResponse(this);
        }
    }

    public static final JsonpDeserializer<SearchEventsResponse> _DESERIALIZER = ObjectBuilderDeserializer.lazy(Builder::new,
            SearchEventsResponse::setEventsResponseDeserializer);

    protected static void setEventsResponseDeserializer(ObjectDeserializer<Builder> op) {
        op.add(Builder::events, JsonpDeserializer.arrayDeserializer(Event._DESERIALIZER), "events");
        op.add(Builder::total, JsonpDeserializer.integerDeserializer(), "total");
    }
}

