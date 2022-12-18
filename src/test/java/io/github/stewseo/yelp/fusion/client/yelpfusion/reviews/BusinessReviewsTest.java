package io.github.stewseo.yelp.fusion.client.yelpfusion.reviews;

import io.github.stewseo.yelp.fusion.client.YelpConnection;
import io.github.stewseo.yelp.fusion.client.yelpfusion.YelpFusionClient;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business.reviews.ReviewsResponse;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BusinessReviewsTest {

    @Test
    public void businessReviewsTest() throws Exception {

        String apiKey = System.getenv("YELP_API_KEY");
        
        YelpFusionClient client = YelpFusionClient.createClient(apiKey);

        String id = "wu3w6IlUct9OvYmYXDMGJA";

        ReviewsResponse response = client.businesses().businessReviews(s -> s.id(id));

        assertThat(response.possible_languages().toString()).isEqualTo("[en]");

        assertThat(response.reviews().size()).isEqualTo(3);
        assertThat(response.reviews().get(2).toString()).isEqualTo(
                "Review: " +
                        "{\"id\":\"Ob6aIbhYOZM3yrLgymjXcQ\"," +
                        "\"text\":\"The food is consistently good. Real deal authentic Mexican cooking much like it's predecessor New Mexico II \\n\\nTacos, nachos, and tortas all slap.\"," +
                        "\"url\":\"https://www.yelp.com/biz/huitlacoche-taqueria-restaurant-ridgewood-2?adjust_creative=ccj3y1UCH-4gsdWSMdEDOw&hrid=Ob6aIbhYOZM3yrLgymjXcQ&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_reviews&utm_source=ccj3y1UCH-4gsdWSMdEDOw\"," +
                        "\"rating\":5.0," +
                        "\"time_created\":\"2021-10-05 17:06:53\"," +
                        "\"user\":{\"id\":\"rO-EHPDpGwNwVLIckZhd5A\"," +
                        "\"profile_url\":\"https://www.yelp.com/user_details?userid=rO-EHPDpGwNwVLIckZhd5A\"," +
                        "\"image_url\":\"https://s3-media3.fl.yelpcdn.com/photo/lpgsilTqtfRtgSjWuy8r7Q/o.jpg\"," +
                        "\"name\":\"Caleb M.\"}}");

        assertThat(response.reviews().get(1).toString()).isEqualTo(
                "Review: {\"id\":\"_4Fy-e2S3zSE0dmgf1jy_w\"," +
                        "\"text\":\"Great birria tacos, awesome horchata, friendly service! Definitely a good place to have around.\"," +
                        "\"url\":\"https://www.yelp.com/biz/huitlacoche-taqueria-restaurant-ridgewood-2?adjust_creative=ccj3y1UCH-4gsdWSMdEDOw&hrid=_4Fy-e2S3zSE0dmgf1jy_w&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_reviews&utm_source=ccj3y1UCH-4gsdWSMdEDOw\"," +
                        "\"rating\":5.0," +
                        "\"time_created\":\"2021-09-20 17:50:48\"," +
                        "\"user\":{\"id\":\"sXof-BspD8zhAUrZGlK6MA\"," +
                        "\"profile_url\":\"https://www.yelp.com/user_details?userid=sXof-BspD8zhAUrZGlK6MA\"," +
                        "\"image_url\":\"https://s3-media4.fl.yelpcdn.com/photo/9soayR_EBgNKuAIhHa8SJg/o.jpg\"," +
                        "\"name\":\"Gabriel R.\"}}"
        );
    }
}
