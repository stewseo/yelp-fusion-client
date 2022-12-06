package io.github.stewseo.yelp.fusion.client.yelpfusion.business_match;

import io.github.stewseo.yelp.fusion.client.YelpRequestTestCase;
import io.github.stewseo.yelp.fusion.client.yelpfusion.YelpFusionClient;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business_reviews.BusinessReviewsRequest;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business_reviews.BusinessReviewsResponse;
import io.github.stewseo.yelp.fusion.client.yelpfusion.business_reviews.BusinessReviewsTest;
import io.github.stewseo.yelp.fusion.client.yelpfusion.businss_match.BusinessMatchRequest;
import io.github.stewseo.yelp.fusion.client.yelpfusion.businss_match.BusinessMatchResponse;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class BusinessMatchTest {

    private static final Logger logger = LoggerFactory.getLogger(BusinessReviewsTest.class);


    @Test
    public void businessMatchSendRequestSynchronizedTest() throws Exception {
        String id = "V7lXZKBDzScDeGB8JmnzSA";
        BusinessMatchRequest request = BusinessMatchRequest.of(a -> a
                .city(id));

        YelpRequestTestCase.initYelpFusionClient();
        YelpFusionClient client = YelpRequestTestCase.getYelpClient();
        BusinessMatchResponse response = client.businessMatch(request);
    }

    @Test
    public void businessMatchSendRequestASyncTest() throws Exception {
        String id = "V7lXZKBDzScDeGB8JmnzSA";
        BusinessMatchRequest request = BusinessMatchRequest.of(a -> a
                .city(id));

        YelpRequestTestCase.initYelpFusionClient();
        YelpFusionClient client = YelpRequestTestCase.getYelpClient();
        BusinessMatchResponse response = client.businessMatch(request);
    }

}
