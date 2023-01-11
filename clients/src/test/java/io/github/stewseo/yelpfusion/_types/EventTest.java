package io.github.stewseo.yelpfusion._types;

import io.github.stewseo.clients.json.DeserializeFromJson;
import io.github.stewseo.clients.json.SerializeToJson;
import io.github.stewseo.clients.yelpfusion._types.Event;
import io.github.stewseo.clients.yelpfusion._types.Location;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

class EventTest implements SerializeToJson, DeserializeFromJson {

    private final Integer attending_count = 1, interested_count = 1;

    private final Boolean is_free = true, is_official = true, is_canceled = true;

    private final Double cost = 1.0, cost_max = 1.0, latitude = 1.0, longitude = 1.0;

    private final Location location = Location.of(l -> l.city("San Francisco"));

    private final String id = "id", category = "category", description = "description", event_site_url = "event_site_url", name = "name",
            tickets_url = "tickets_url", image_url = "image_url", time_end = "time_end", time_start = "time_start";

    private final Event event = Event.of(e -> e
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

    @Override
    public JsonGenerator generator() {
        return mapper.jsonProvider().createGenerator(new StringWriter());
    }

    @Test
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

    @Test
    public void testSerialize() {
        JsonGenerator generator = generator();
        event.serialize(generator, mapper);
        assertThat(event.toString()).isEqualTo(expected);
    }

    @Test
    public void testSerializeInternal() {
        JsonGenerator generator = generator();
        generator.writeStartObject();
        event.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        assertThat(event).isNotNull();
    }

    @Override
    public JsonParser parser() {
        InputStream content = IOUtils.toInputStream(event.toString(), StandardCharsets.UTF_8);
        return mapper.jsonProvider().createParser(content);
    }
    @Test
    public void testDeserialize() {
        assertThat(Event._DESERIALIZER.toString()).contains("io.github.stewseo.clients.json.LazyDeserializer");

    }

    @Test
    public void testDeserializer() {
        InputStream content = IOUtils.toInputStream(event.toString(), StandardCharsets.UTF_8);

        JsonParser parser = mapper.jsonProvider().createParser(content);

        Event searchBusinessRes = Event._DESERIALIZER.deserialize(parser, mapper);

        assertThat(searchBusinessRes).isNotNull();
    }
}