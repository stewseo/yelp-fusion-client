package io.github.stewseo.client.yelpfusion.business.reviews;

import io.github.stewseo.client.YelpFusionTestCase;
import io.github.stewseo.client.connection.YelpFusionConnection;
import io.github.stewseo.client.yelpfusion.business.reviews.Review;
import io.github.stewseo.client.yelpfusion.business.reviews.ReviewsRequest;
import io.github.stewseo.client.yelpfusion.business.reviews.ReviewsResponse;

import io.github.stewseo.client.yelpfusion.YelpFusionSyncBlockingClient;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class BusinessReviewsTest extends YelpFusionTestCase {

    private static final Logger logger = LoggerFactory.getLogger(BusinessReviewsTest.class);

    // "id": "V7lXZKBDzScDeGB8JmnzSA",
    // "alias": "katzs-delicatessen-new-york",
    String katzReview = "{\"reviews\":[{\"id\":\"c\",\"text\":\"Absolutely beautiful, scintillating, mouth pleasuring time of our life. 10/10 would come again\"," +
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
        yelpFusionAsyncClient = YelpFusionConnection.createOrGetYelpFusionAsyncClient();

        ReviewsResponse response = yelpFusionAsyncClient.businesses().businessReviews(request).get();

        testBusinessReviews(response.reviews().stream());

    }


    @Test
    public void businessReviewsByAliasTest() throws Exception {
        String alias = "katzs-delicatessen-new-york";
        ReviewsRequest request = ReviewsRequest.of(a -> a.alias(alias));
        yelpFusionAsyncClient = YelpFusionConnection.createOrGetYelpFusionAsyncClient();

        ReviewsResponse response = yelpFusionAsyncClient.businesses().businessReviews(request).get();

        testBusinessReviews(response.reviews().stream());

    }

    void testBusinessReviews(Stream<Review> reviewsStream) {
        reviewsStream.forEach(review -> {
            assertThat(review).isNotNull();
            assertThat(review.id()).isNotNull();
            assertThat(review.url()).isNotNull();
            assertThat(review.rating()).isInstanceOf(Double.class);
        });
    }
}
