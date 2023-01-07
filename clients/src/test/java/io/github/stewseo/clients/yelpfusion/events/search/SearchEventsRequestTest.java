package io.github.stewseo.clients.yelpfusion.events.search;

import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.clients.transport.Endpoint;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionRequestTestCase;
import jakarta.json.stream.JsonGenerator;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SearchEventsRequestTest extends YelpFusionRequestTestCase<SearchEventsRequest> {

    private final String sort_on = "sort_on";

    private final int start_date = 1, end_date = 5;

    private final boolean is_free = true;

    private final List<String> excludedEvents = List.of("excludedEvent1", "excludedEvent2"), categories = List.of("categories");

    JsonpMapper mapper = new JacksonJsonpMapper();

    private final SearchEventsRequest searchEventsRequest = SearchEventsRequest.of(e -> e
            .locale(locale)
            .sort_by(sort_by)
            .sort_on(sort_on)
            .location(location)
            .offset(offset)
            .limit(limit)
            .start_date(start_date)
            .end_date(end_date)
            .is_free(is_free)
            .excluded_events(excludedEvents)
            .latitude(latitude)
            .longitude(longitude)
            .limit(limit)
            .radius(radius)
            .categories(categories)
    );

    @Override
    public Endpoint<SearchEventsRequest, ?, ?> endpoint() {

        return SearchEventsRequest._ENDPOINT;
    }

    @Override
    public JsonGenerator generator() {
        return mapper.jsonProvider().createGenerator(new StringWriter());
    }

    @Test
    public void testOf() {

        assertThat(searchEventsRequest.locale()).isEqualTo(locale);
        assertThat(searchEventsRequest.sort_by()).isEqualTo(sort_by);
        assertThat(searchEventsRequest.sort_on()).isEqualTo(sort_on);
        assertThat(searchEventsRequest.location()).isEqualTo(location);
        assertThat(searchEventsRequest.offset()).isEqualTo(offset);
        assertThat(searchEventsRequest.limit()).isEqualTo(limit);
        assertThat(searchEventsRequest.start_date()).isEqualTo(start_date);
        assertThat(searchEventsRequest.end_date()).isEqualTo(end_date);
        assertThat(searchEventsRequest.is_free()).isEqualTo(is_free);
        assertThat(searchEventsRequest.latitude()).isEqualTo(latitude);
        assertThat(searchEventsRequest.excluded_events()).isEqualTo(excludedEvents);
        assertThat(searchEventsRequest.longitude()).isEqualTo(longitude);
        assertThat(searchEventsRequest.limit()).isEqualTo(limit);
        assertThat(searchEventsRequest.radius()).isEqualTo(radius);
        assertThat(searchEventsRequest.categories()).isEqualTo(categories);
    }

    private final String expected = "{\"categories\":[\"categories\"]," +
            "\"excluded_events\":[\"excludedEvent1\",\"excludedEvent2\"]," +
            "\"locale\":\"locale\"," +
            "\"sort_by\":\"sort_by\"," +
            "\"sort_on\":\"sort_on\"," +
            "\"offset\":5," +
            "\"location\":\"location\"," +
            "\"limit\":50,\"start_date\":1," +
            "\"end_date\":5," +
            "\"radius\":20000," +
            "\"latitude\":44.0,\"longitude\":-122.0," +
            "\"is_free\":true}";

    @Test
    public void testSerialize() {
        JsonGenerator generator = generator();
        searchEventsRequest.serialize(generator, mapper);

        assertThat(searchEventsRequest.toString()).isEqualTo(expected);
    }

    @Test
    public void testSerializeInternal() {
        JsonGenerator generator = generator();
        generator.writeStartObject();
        searchEventsRequest.serializeInternal(generator, mapper);
        generator.writeEnd();

        assertThat(searchEventsRequest.toString()).isEqualTo(expected);
    }

    @Test
    public void testEndpoint() {
        assertThat("v3/events")
                .isEqualTo(SearchEventsRequest._ENDPOINT.requestUrl(searchEventsRequest));
    }
}