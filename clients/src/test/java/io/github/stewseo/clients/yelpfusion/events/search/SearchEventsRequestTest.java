package io.github.stewseo.clients.yelpfusion.events.search;

import io.github.stewseo.clients.json.JsonpMapper;
import io.github.stewseo.clients.json.jackson.JacksonJsonpMapper;
import io.github.stewseo.clients.transport.Endpoint;
import io.github.stewseo.clients.yelpfusion.YelpFusionTest;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionTestCase;
import io.github.stewseo.clients.yelpfusion.testcases.RequestTestCase;
import jakarta.json.stream.JsonGenerator;
import org.junit.jupiter.api.Tag;

import java.util.List;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.LATITUDE;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.LIMIT;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.TestReqVars.LOCALE;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.LONGITUDE;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.OFFSET;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.RADIUS;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.TestReqVars.SORT_BY;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Tag("events")
@Tag("search")
class SearchEventsRequestTest extends YelpFusionTestCase<SearchEventsRequest>
        implements RequestTestCase<SearchEventsRequest> {

    private final String sort_on = "sort_on";

    private final int start_date = 1, end_date = 5;

    private final boolean is_free = true;

    private final String location = "locationValue";

    private final List<String> excludedEvents = List.of("excludedEvent1", "excludedEvent2"), categories = List.of("categories");

    JsonpMapper mapper = new JacksonJsonpMapper();

    private final SearchEventsRequest searchEventsRequest = of();

    @Override
    public Endpoint<SearchEventsRequest, ?, ?> endpoint() {

        return SearchEventsRequest._ENDPOINT;
    }

    @Override
    public SearchEventsRequest of() {
        return SearchEventsRequest.of(e -> e
                .locale(LOCALE)
                .sort_by(SORT_BY)
                .sort_on(sort_on)
                .location(location)
                .offset(OFFSET)
                .limit(LIMIT)
                .start_date(start_date)
                .end_date(end_date)
                .is_free(is_free)
                .excluded_events(excludedEvents)
                .latitude(LATITUDE)
                .longitude(LONGITUDE)
                .limit(LIMIT)
                .radius(RADIUS)
                .categories(categories)
        );
    }

    @YelpFusionTest
    public void testOf() {

        assertThat(searchEventsRequest.locale()).isEqualTo(LOCALE);
        assertThat(searchEventsRequest.sort_by()).isEqualTo(SORT_BY);
        assertThat(searchEventsRequest.sort_on()).isEqualTo(sort_on);
        assertThat(searchEventsRequest.location()).isEqualTo(location);
        assertThat(searchEventsRequest.offset()).isEqualTo(OFFSET);
        assertThat(searchEventsRequest.limit()).isEqualTo(LIMIT);
        assertThat(searchEventsRequest.start_date()).isEqualTo(start_date);
        assertThat(searchEventsRequest.end_date()).isEqualTo(end_date);
        assertThat(searchEventsRequest.is_free()).isEqualTo(is_free);
        assertThat(searchEventsRequest.latitude()).isEqualTo(LATITUDE);
        assertThat(searchEventsRequest.excluded_events()).isEqualTo(excludedEvents);
        assertThat(searchEventsRequest.longitude()).isEqualTo(LONGITUDE);
        assertThat(searchEventsRequest.radius()).isEqualTo(RADIUS);
        assertThat(searchEventsRequest.categories()).isEqualTo(categories);
    }

    private final String expected = "" +
            "{\"location\":\"locationValue\"," +
            "\"latitude\":37.7829," +
            "\"longitude\":-122.4189," +
            "\"locale\":\"en_US\"," +
            "\"categories\":[\"categories\"]," +
            "\"excluded_events\":[\"excludedEvent1\",\"excludedEvent2\"]," +
            "\"sort_by\":\"sort_by\",\"sort_on\":\"sort_on\"," +
            "\"offset\":5,\"limit\":50,\"start_date\":1,\"end_date\":5,\"radius\":20000," +
            "\"is_free\":true}";

    @YelpFusionTest
    public void testSerialize() {
        JsonGenerator generator = generator();
        searchEventsRequest.serialize(generator, mapper);

        assertThat(searchEventsRequest.toString()).isEqualTo(expected);
    }

    @YelpFusionTest
    public void testSerializeInternal() {
        JsonGenerator generator = generator();
        generator.writeStartObject();
        searchEventsRequest.serializeInternal(generator, mapper);
        generator.writeEnd();

        assertThat(searchEventsRequest.toString()).isEqualTo(expected);
    }

    @YelpFusionTest
    public void testEndpoint() {
        assertThat("v3/events")
                .isEqualTo(SearchEventsRequest._ENDPOINT.requestUrl(searchEventsRequest));
    }

    @YelpFusionTest
    public void testBuildWithJson() {

    }
    @YelpFusionTest
    public void testBuilder() {

    }
}