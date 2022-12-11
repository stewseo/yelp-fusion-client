package io.github.stewseo.yelp.fusion.client.yelpfusion.event;

import io.github.stewseo.yelp.fusion.client.yelpfusion.YelpFusionClient;
import io.github.stewseo.yelp.fusion.client.yelpfusion.categories.GetCategoriesResponse;
import io.github.stewseo.yelp.fusion.client.yelpfusion.events.EventSearchResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class EventSearchTest {

    @Test
    public void eventSearchTest() throws Exception {
        String apiKey = System.getenv("YELP_API_KEY");

        YelpFusionClient client = YelpFusionClient.createClient(apiKey);

        EventSearchResponse response = client.events().eventSearch(c -> c
                .location("sf")
                .limit(10)
        );

        assertThat(response.total()).isEqualTo(10);
        assertThat(response.events().size()).isEqualTo(10);
        assertThat(response.events().get(0).toString()).isEqualTo(
                "Event: " +
                        "{" +
                        "\"category\":\"nightlife\"," +
                        "\"description\":\"Come join the Yelp Team and all of Yelpland in celebrating our 3rd Annual Yelp Holiday Party! Just some of the \\\"funny, useful and cool\\\" thrills will include...\"," +
                        "\"event_site_url\":\"https://www.yelp.com/events/san-francisco-peace-love-and-yelp-our-3rd-annual-holiday-party?adjust_creative=ccj3y1UCH-4gsdWSMdEDOw&utm_campaign=yelp_api_v3&utm_medium=api_v3_event_search&utm_source=ccj3y1UCH-4gsdWSMdEDOw\"," +
                        "\"id\":\"san-francisco-peace-love-and-yelp-our-3rd-annual-holiday-party\"," +
                        "\"name\":\"Peace, Love & Yelp: Our 3rd Annual Holiday Party!\"," +
                        "\"tickets_url\":\"\"," +
                        "\"time_end\":\"2007-12-05T23:00:00-08:00\"," +
                        "\"time_start\":\"2007-12-05T20:30:00-08:00\"," +
                        "\"attending_count\":926," +
                        "\"interested_count\":73," +
                        "\"is_canceled\":false," +
                        "\"is_free\":true," +
                        "\"is_official\":false," +
                        "\"latitude\":37.78574," +
                        "\"longitude\":-122.40255}");

    }
}
