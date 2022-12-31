package io.github.stewseo.client.yelpfusion.event;

import com.brein.domain.results.BreinTemporalDataResult;
import com.brein.domain.results.temporaldataparts.BreinLocationResult;
import io.github.stewseo.client.yelpfusion.YelpFusionTestCase;
import io.github.stewseo.client.yelpfusion.events.Event;
import io.github.stewseo.client.yelpfusion.events.EventSearchRequest;
import io.github.stewseo.client.yelpfusion.events.EventSearchResponse;
import io.github.stewseo.client.yelpfusion.json.YelpFusionJsonTestCase;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Pattern;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class EventSearchTest extends YelpFusionTestCase {

    private static final Logger logger = LoggerFactory.getLogger(EventSearchTest.class);


    //  "Event: " +
    //                        "{" +
    //                        "\"category\":\"nightlife\"," +
    //                        "\"description\":\"Come join the Yelp Team and all of Yelpland in celebrating our 3rd Annual Yelp Holiday Party! Just some of the \\\"funny, useful and cool\\\" thrills will include...\"," +
    //                        "\"event_site_url\":\"https://www.yelp.com/events/san-francisco-peace-love-and-yelp-our-3rd-annual-holiday-party?adjust_creative=ccj3y1UCH-4gsdWSMdEDOw&utm_campaign=yelp_api_v3&utm_medium=api_v3_event_search&utm_source=ccj3y1UCH-4gsdWSMdEDOw\"," +
    //                        "\"id\":\"san-francisco-peace-love-and-yelp-our-3rd-annual-holiday-party\"," +
    //                        "\"name\":\"Peace, Love & Yelp: Our 3rd Annual Holiday Party!\"," +
    //                        "\"tickets_url\":\"\"," +
    //                        "\"time_end\":\"2007-12-05T23:00:00-08:00\"," +
    //                        "\"time_start\":\"2007-12-05T20:30:00-08:00\"," +
    //                        "\"attending_count\":926," +
    //                        "\"interested_count\":73," +
    //                        "\"is_canceled\":false," +
    //                        "\"is_free\":true," +
    //                        "\"is_official\":false," +
    //                        "\"latitude\":37.78574," +
    //                        "\"longitude\":-122.40255}"
    static private final int validNumChars = 10;
    static private final Pattern timeStampPattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
    static int maxRadius = 40000;
    YelpFusionJsonTestCase testJson = getTestJson();

    // test results from endpoint: https://docs.developer.yelp.com/reference/v3_events_search
    @Test
    public void eventSearchTest() throws Exception {

        // https://worldpopulationreview.com/us-cities top 20 cities concat cities in California with great restaurants
        long count = 0;

        count += getStreamOfTemporalData()
                .map(BreinTemporalDataResult::getLocation)
                .map(this::testSearchEvents)
                .map(this::testSearchEvents).count();

        logger.info("SearchEvent test complete. Total events tested: " + count);

    }

    // build an EventSearchRequest using BreinLocationResult
    private EventSearchRequest testSearchEvents(BreinLocationResult breinLocationResult) {

        Double latitude = breinLocationResult.getLat();
        Double longitude = breinLocationResult.getLon();
        String resultCity = breinLocationResult.getCity();

        // Map<String, Map<String, Object>> geoJsons = breinLocationResult.getGeoJsons();

        return EventSearchRequest.of(e -> e
                .longitude(longitude)
                .latitude(latitude)
                .limit(50)
                .radius(maxRadius));
    }

    private long testSearchEvents(EventSearchRequest eventSearchRequest) {

        CompletableFuture<List<Event>> future = null;

        try {
            // execute the request using the YelpFusionAsyncClient
            future = yelpFusionAsyncClient.events()
                    .search(eventSearchRequest)
                    .whenComplete((response, exception) -> {

                        if (exception != null) {

                            logger.error("Search Events failed: " + exception);

                        } else {
                            logger.info("Search Events successful. total events: " + response.total());
                        }

                    }).thenApply(EventSearchResponse::events);

            List<Event> events = future.get();

            assertThat(events.size()).isGreaterThanOrEqualTo(1);

            return events.stream().map(this::testSearchEvents).count();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    // assert that each event contained in a EventSearchResponse body is not null
    private int testSearchEvents(Event event) {

        assertThat(event).isNotNull();

        assertThat(event.id().length()).isGreaterThanOrEqualTo(validNumChars);

        assertThat(event.event_site_url()).contains("https:");

        assertThat(event.time_start()).containsPattern(timeStampPattern);

        assertThat(event.description().length()).isGreaterThanOrEqualTo(validNumChars);

        assertThat(event.category()).isNotNull();

        assertThat(event.event_site_url().length()).isGreaterThanOrEqualTo(validNumChars);

        return assertIsValidJson(event);

    }

    private int assertIsValidJson(Event event) {

        return testJson.assertIsValidJson(event);

    }
}
