package io.github.stewseo.yelp.fusion.client.yelpfusion.event;

import io.github.stewseo.yelp.fusion.client.yelpfusion.YelpFusionClient;
import io.github.stewseo.yelp.fusion.client.yelpfusion.events.FeaturedEventResponse;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FeaturedEventTest {
    @Test
    public void featuredEventTest() throws Exception {
        String apiKey = System.getenv("YELP_API_KEY");

        YelpFusionClient client = YelpFusionClient.createClient(apiKey);

        FeaturedEventResponse response = client.events().featured(f -> f.location("NYC"));

        assertThat(response.event().toString()).isEqualTo(
                "Event: " +
                        "{\"category\":\"sports-active-life\"," +
                        "\"description\":\"Ready to crush your fitness goals in 2023?\\n\\nThis December, Yelp North Jersey will be showcasing some of the best fitness studios for you to add to your New...\"," +
                        "\"event_site_url\":\"https://www.yelp.com/events/jersey-city-yelp-north-jerseys-2023-fitness-preview?adjust_creative=ccj3y1UCH-4gsdWSMdEDOw&utm_campaign=yelp_api_v3&utm_medium=api_v3_event_featured&utm_source=ccj3y1UCH-4gsdWSMdEDOw\"," +
                        "\"id\":\"jersey-city-yelp-north-jerseys-2023-fitness-preview\"," +
                        "\"name\":\"Yelp North Jersey's 2023 Fitness Preview\"," +
                        "\"tickets_url\":\"\"," +
                        "\"time_end\":\"2022-12-31T23:30:00-05:00\"," +
                        "\"time_start\":\"2022-12-01T00:00:00-05:00\"," +
                        "\"attending_count\":165," +
                        "\"interested_count\":12," +
                        "\"is_canceled\":false," +
                        "\"is_free\":true,\"is_official\":true," +
                        "\"cost\":0.0," +
                        "\"cost_max\":0.0," +
                        "\"latitude\":40.71963392976657," +
                        "\"longitude\":-74.04683762380401," +
                        "\"location\":" +
                            "{\"address1\":\"\",\"address2\":\"\",\"address3\":\"\"," +
                            "\"city\":\"Jersey City\"," +
                            "\"zip_code\":\"07302\"," +
                            "\"country\":\"US\"," +
                            "\"display_address\":[\"Jersey City, NJ 07302\"]}" +
                        "}");
    }
}
