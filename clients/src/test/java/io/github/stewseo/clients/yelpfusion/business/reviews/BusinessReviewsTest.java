package io.github.stewseo.clients.yelpfusion.business.reviews;

import io.github.stewseo.clients.yelpfusion._types.Review;
import io.github.stewseo.clients.testcase.YelpFusionTestCase;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;


import static org.assertj.core.api.Assertions.assertThat;

public class BusinessReviewsTest extends YelpFusionTestCase {

    private static final Logger logger = LoggerFactory.getLogger(BusinessReviewsTest.class);

    // "id": "V7lXZKBDzScDeGB8JmnzSA",
    // "alias": "katzs-delicatessen-new-york",

    @Test
    public void businessReviewsByIdTest() throws Exception {

        final String id = "V7lXZKBDzScDeGB8JmnzSA";

        final ReviewsRequest request = ReviewsRequest.of(a -> a.id(id));

        ReviewsResponse response = yelpFusionServiceCtx.getYelpFusionAsyncClient().businesses().businessReviews(request).get();

        response.reviews().forEach(this::testBusinessReviews);

    }

    @Test
    public void businessReviewsByAliasTest() throws Exception {

        String alias = "katzs-delicatessen-new-york";

        ReviewsRequest request = ReviewsRequest.of(a -> a.alias(alias));

        ReviewsResponse response = yelpFusionServiceCtx.getYelpFusionAsyncClient().businesses().businessReviews(request).get();

        response.reviews().forEach(this::testBusinessReviews);

    }

    void testBusinessReviews(Review review) {

        assertThat(review).isNotNull();
        assertThat(review.id()).isNotNull();
        assertThat(review.url()).isNotNull();
        assertThat(review.rating()).isInstanceOf(Double.class);
    }
}
