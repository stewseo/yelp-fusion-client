package io.github.stewseo.clients.yelpfusion._types;

import io.github.stewseo.clients.yelpfusion.YelpFusionTest;

import io.github.stewseo.clients.yelpfusion.testcases.ModelTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class EventTest extends ModelTestCase<Event> {

    private final Integer attending_count = 1, interested_count = 1;

    private final Boolean is_free = true, is_official = true, is_canceled = true;

    private final Double cost = 1.0, cost_max = 1.0, latitude = 1.0, longitude = 1.0;

    private final Location location = Location.of(l -> l.city("San Francisco"));

    private final String id = "id", category = "category", description = "description", event_site_url = "event_site_url", name = "name",
            tickets_url = "tickets_url", image_url = "image_url", time_end = "time_end", time_start = "time_start";


    @Override
    public Event of() {
        return Event.of(e -> e
                .id(id)
                .attending_count(attending_count)
                .category(category)
                .cost(cost)
                .cost_max(cost_max)
                .description(description)
                .event_site_url(event_site_url)
                .interested_count(interested_count)
                .is_canceled(is_canceled)
                .is_free(is_free)
                .is_official(is_official)
                .latitude(latitude)
                .location(location)
                .longitude(longitude)
                .name(name)
                .tickets_url(tickets_url)
                .time_end(time_end)
                .time_start(time_start)
                .image_url(image_url));
    }
    private final Event event = of();
    JsonGenerator generator = generator();

    public void testBuilder() {

    }

    @YelpFusionTest
    public void testOf() {
        assertThat(event.id()).isEqualTo(id);
        assertThat(event.attending_count()).isEqualTo(attending_count);
        assertThat(event.cost_max()).isEqualTo(cost_max);
        assertThat(event.event_site_url()).isEqualTo(event_site_url);
        assertThat(event.description()).isEqualTo(description);
        assertThat(event.is_canceled()).isEqualTo(is_canceled);
        assertThat(event.is_free()).isEqualTo(is_free);
        assertThat(event.is_official()).isEqualTo(is_official);
        assertThat(event.latitude()).isEqualTo(latitude);
        assertThat(event.location()).isEqualTo(location);
        assertThat(event.longitude()).isEqualTo(longitude);
        assertThat(event.name()).isEqualTo(name);
        assertThat(event.tickets_url()).isEqualTo(tickets_url);
        assertThat(event.time_end()).isEqualTo(time_end);
        assertThat(event.time_start()).isEqualTo(time_start);
        assertThat(event.image_url()).isEqualTo(image_url);
    }

    private final String expected = "{" +
            "\"category\":\"category\"," +
            "\"description\":\"description\"," +
            "\"event_site_url\":\"event_site_url\"," +
            "\"id\":\"id\"," +
            "\"name\":\"name\"," +
            "\"tickets_url\":\"tickets_url\"," +
            "\"image_url\":\"image_url\"," +
            "\"time_end\":\"time_end\"," +
            "\"time_start\":\"time_start\"," +
            "\"attending_count\":1,\"interested_count\":1," +
            "\"is_canceled\":true," +
            "\"is_free\":true," +
            "\"is_official\":true," +
            "\"cost\":1.0,\"cost_max\":1.0," +
            "\"latitude\":1.0,\"longitude\":1.0,\"location\":{\"city\":\"San Francisco\"}}";

    @YelpFusionTest
    public void testSerialize() {
        event.serialize(generator, mapper);
        assertThat(event.toString()).isEqualTo(expected);
    }

    @YelpFusionTest
    public void testSerializeInternal() {
        generator.writeStartObject();
        event.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        assertThat(event).isNotNull();
    }

    JsonParser parser = parser(event);

    @YelpFusionTest
    public void testDeserialize() {
        assertThat(Event._DESERIALIZER.toString()).contains("io.github.stewseo.clients.json.LazyDeserializer");

    }

    @YelpFusionTest
    public void testDeserializer() {

        Event searchBusinessRes = Event._DESERIALIZER.deserialize(parser, mapper);

        assertThat(searchBusinessRes).isNotNull();
    }
}