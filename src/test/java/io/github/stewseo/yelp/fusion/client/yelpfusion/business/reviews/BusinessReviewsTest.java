package io.github.stewseo.yelp.fusion.client.yelpfusion.business.reviews;

import io.github.stewseo.yelp.fusion.client.connection.YelpConnection;
import io.github.stewseo.yelp.fusion.client.yelpfusion.YelpFusionClient;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class BusinessReviewsTest {

    private static final Logger logger = LoggerFactory.getLogger(BusinessReviewsTest.class);

    // "id": "V7lXZKBDzScDeGB8JmnzSA",
    // "alias": "katzs-delicatessen-new-york",
    String katzReview = "{\"reviews\":[{\"id\":\"cPVymAHgQ8VHIyJ6l_izmw\",\"text\":\"Absolutely beautiful, scintillating, mouth pleasuring time of our life. 10/10 would come again\"," +
            "\"url\":\"https://www.yelp.com/biz/katzs-delicatessen-new-york?adjust_creative=ccj3y1UCH-4gsdWSMdEDOw&hrid=cPVymAHgQ8VHIyJ6l_izmw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_reviews&utm_source=ccj3y1UCH-4gsdWSMdEDOw\"," +
            "\"rating\":5.0,\"time_created\":\"2022-11-25 17:03:45\"," +
            "\"user\":{\"id\":\"ouIC0dXW3gL4IvDRkd6KUg\",\"profile_url\":\"https://www.yelp.com/user_details?userid=ouIC0dXW3gL4IvDRkd6KUg\",\"name\":\"Dawson C.\"}}," +
            "{\"id\":\"NF5O2hr42wGSyztE1c7gpg\",\"text\":\"Amazing sandwiches so juicy and tender and not a long wait in and out great for on the fly\"," +
            "\"url\":\"https://www.yelp.com/biz/katzs-delicatessen-new-york?adjust_creative=ccj3y1UCH-4gsdWSMdEDOw&hrid=NF5O2hr42wGSyztE1c7gpg&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_reviews&utm_source=ccj3y1UCH-4gsdWSMdEDOw\"," +
            "\"rating\":5.0,\"time_created\":\"2022-11-26 08:59:54\",\"user\":{\"id\":\"SJ5N64aaNGG-uAiXcy8qTA\",\"profile_url\":\"https://www.yelp.com/user_details?userid=SJ5N64aaNGG-uAiXcy8qTA\",\"name\":\"Marc R.\"}},{\"id\":\"Zs-kzpk16S5MddWwCvz1mQ\",\"text\":\"What else is there to say? It's Katz's Delicatessen. Get the pastrami sandwich. NYC service and vibe.\",\"url\":\"https://www.yelp.com/biz/katzs-delicatessen-new-york?adjust_creative=ccj3y1UCH-4gsdWSMdEDOw&hrid=Zs-kzpk16S5MddWwCvz1mQ&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_reviews&utm_source=ccj3y1UCH-4gsdWSMdEDOw\",\"rating\":5.0,\"time_created\":\"2022-12-09 08:17:59\",\"user\":{\"id\":\"UG4xU04QJrcu-tCSDlO7kg\",\"profile_url\":\"https://www.yelp.com/user_details?userid=UG4xU04QJrcu-tCSDlO7kg\",\"name\":\"Andrew G.\"}}],\"possible_languages\":[\"en\",\"de\",\"fr\",\"sv\",\"it\",\"nb\",\"da\",\"es\",\"ja\",\"pt\",\"nl\",\"pl\"],\"total\":14488}\"";
    @Test
    public void businessReviewsByIdTest() throws Exception {
        String id = "V7lXZKBDzScDeGB8JmnzSA";
        ReviewsRequest request = ReviewsRequest.of(a -> a.id(id));
        YelpConnection.initYelpFusionClient();
        YelpFusionClient client = YelpConnection.getYelpClient();
        ReviewsResponse response = client.businesses().businessReviews(request);
        assertThat(response.toString()).isEqualTo("t");

        assertThat(response.reviews().size()).isEqualTo(3);
        Review review = response.reviews().get(0);
        assertThat(review.id()).isEqualTo("WoTuZDEv1_9cVfydgellYg");
        assertThat(review.url()).isNotNull();
        assertThat(review.rating()).isEqualTo(5.0);
        assertThat(response.reviews().get(0).text().toString()).isEqualTo("Best pastrami sandwiches in the world.  Not sure about the other entrees since I only order pastrami which is hard to get this good at other places.");
        assertThat(review.user().name()).isEqualTo("Bonnie W.");
        assertThat(review.user().profile_url()).isEqualTo("https://www.yelp.com/user_details?userid=4niVGibeUve5Xu8pep380g");
        assertThat(review.user().id()).isEqualTo("4niVGibeUve5Xu8pep380g");
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
