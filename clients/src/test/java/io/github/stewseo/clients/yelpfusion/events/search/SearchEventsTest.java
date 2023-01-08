package io.github.stewseo.clients.yelpfusion.events.search;

import com.brein.domain.results.BreinTemporalDataResult;
import com.brein.domain.results.temporaldataparts.BreinLocationResult;
import io.github.stewseo.clients.yelpfusion._types.Event;
import io.github.stewseo.clients.yelpfusion.testcases.FunctionalTestCase;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Pattern;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class SearchEventsTest extends FunctionalTestCase {

    private static final Logger logger = LoggerFactory.getLogger(SearchEventsTest.class);

    private static final int validNumChars = 10;

    private static final Pattern timeStampPattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");

    static int maxRadius = 40000;

//    SearchEventsResponse searchEventsResponse = SearchEventsResponse.of()

    // test results from endpoint: https://docs.developer.yelp.com/reference/v3_events_search

    @Test
    public void eventSearchTest() throws Exception {

        // https://worldpopulationreview.com/us-cities top 20 cities concat cities in California with great restaurants
        long count = 0;

        getStreamOfTemporalData()
                .map(BreinTemporalDataResult::getLocation)
                .map(this::testSearchEvents)
                .forEach(this::testSearchEvents);

    }

    // build an EventSearchRequest using BreinLocationResult
    private SearchEventsRequest testSearchEvents(BreinLocationResult breinLocationResult) {

        Double latitude = breinLocationResult.getLat();
        Double longitude = breinLocationResult.getLon();
        String resultCity = breinLocationResult.getCity();

        // Map<String, Map<String, Object>> geoJsons = breinLocationResult.getGeoJsons();

        return SearchEventsRequest.of(e -> e
                .longitude(longitude)
                .latitude(latitude)
                .limit(50)
                .radius(maxRadius));
    }

    private void testSearchEvents(SearchEventsRequest eventSearchRequest) {

        CompletableFuture<List<Event>> future = null;

        try {
            // execute the request using the yelpFusionServiceCtx.getYelpFusionAsyncClient()
            future = yelpFusionService.yelpFusionAsyncClient().events()
                    .search(eventSearchRequest)
                    .whenComplete((response, exception) -> {

                        if (exception != null) {

                            logger.error("Search Events failed: " + exception);

                        } else {
                            logger.info("Search Events successful. total events: " + response.total());
                        }

                    }).thenApply(SearchEventsResponse::events);

            List<Event> events = future.get();

            assertThat(events.size()).isGreaterThanOrEqualTo(1);

            events.forEach(this::testSearchEvents);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    // assert that each event contained in a EventSearchResponse body is not null
    private void testSearchEvents(Event event) {

        assertThat(event).isNotNull();

        assertThat(event.id().length()).isGreaterThanOrEqualTo(validNumChars);

        assertThat(event.event_site_url()).contains("https:");

        assertThat(event.time_start()).containsPattern(timeStampPattern);

        assertThat(event.description().length()).isGreaterThanOrEqualTo(validNumChars);

        assertThat(event.category()).isNotNull();

//        assertThat(event.tickets_url()).isNotNull();
//
//        assertThat(event.cost()).isNull();
//
//        assertThat(event.cost_max()).isNull();
//
//        assertThat(event.is_canceled()).isNotNull();

        assertThat(event.is_free()).isNotNull();

        assertThat(event.event_site_url().length()).isGreaterThanOrEqualTo(validNumChars);

    }
}
