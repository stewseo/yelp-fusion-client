package io.github.stewseo.yelp.fusion.client.yelpfusion.business.reviews;

import io.github.stewseo.yelp.fusion.client.YelpConnection;
import io.github.stewseo.yelp.fusion.client.yelpfusion.YelpFusionClient;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class BusinessReviewsTest {

    private static final Logger logger = LoggerFactory.getLogger(BusinessReviewsTest.class);

    // "id": "V7lXZKBDzScDeGB8JmnzSA",
    // "alias": "katzs-delicatessen-new-york",
    @Test
    public void businessReviewsByIdTest() throws Exception {
        String id = "V7lXZKBDzScDeGB8JmnzSA";
        ReviewsRequest request = ReviewsRequest.of(a -> a.id(id));
        YelpConnection.initYelpFusionClient();
        YelpFusionClient client = YelpConnection.getYelpClient();
        ReviewsResponse response = client.businesses().businessReviews(request);
        assertThat(response.reviews().size()).isEqualTo(3);
        assertThat(response.toString().length()).isEqualTo(1959);
        assertThat(response.reviews().get(0).id().toString()).isEqualTo("WoTuZDEv1_9cVfydgellYg");
        assertThat(response.reviews().get(0).url().toString()).isEqualTo("https://www.yelp.com/biz/katzs-delicatessen-new-york?adjust_creative=ccj3y1UCH-4gsdWSMdEDOw&hrid=WoTuZDEv1_9cVfydgellYg&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_reviews&utm_source=ccj3y1UCH-4gsdWSMdEDOw");
        assertThat(response.reviews().get(0).rating()).isEqualTo(5.0);
        assertThat(response.reviews().get(0).text().toString()).isEqualTo("Best pastrami sandwiches in the world.  Not sure about the other entrees since I only order pastrami which is hard to get this good at other places.");
        assertThat(response.reviews().get(0).user().name()).isEqualTo("Bonnie W.");
        assertThat(response.reviews().get(0).user().profile_url()).isEqualTo("https://www.yelp.com/user_details?userid=4niVGibeUve5Xu8pep380g");
        assertThat(response.reviews().get(0).user().id()).isEqualTo("4niVGibeUve5Xu8pep380g");
    }


    @Test
    public void businessReviewsByAliasTest() throws Exception {
        String alias = "katzs-delicatessen-new-york";
        ReviewsRequest request = ReviewsRequest.of(a -> a.alias(alias));
        YelpConnection.initYelpFusionClient();
        YelpFusionClient client = YelpConnection.getYelpClient();
        ReviewsResponse response = client.businesses().businessReviews(request);
        assertThat(response.reviews().size()).isEqualTo(3);
        assertThat(response.reviews().get(0).id().toString()).isEqualTo("WoTuZDEv1_9cVfydgellYg");
        assertThat(response.reviews().get(0).url().toString()).isEqualTo("https://www.yelp.com/biz/katzs-delicatessen-new-york?adjust_creative=ccj3y1UCH-4gsdWSMdEDOw&hrid=WoTuZDEv1_9cVfydgellYg&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_reviews&utm_source=ccj3y1UCH-4gsdWSMdEDOw");
        assertThat(response.reviews().get(0).rating()).isEqualTo(5.0);
        assertThat(response.reviews().get(0).text().toString()).isEqualTo("Best pastrami sandwiches in the world.  Not sure about the other entrees since I only order pastrami which is hard to get this good at other places.");
        assertThat(response.reviews().get(0).user().name()).isEqualTo("Bonnie W.");
        assertThat(response.reviews().get(0).user().profile_url()).isEqualTo("https://www.yelp.com/user_details?userid=4niVGibeUve5Xu8pep380g");
        assertThat(response.reviews().get(0).user().id()).isEqualTo("4niVGibeUve5Xu8pep380g");
    }
}
