package io.github.stewseo.clients.yelpfusion.businesses.reviews;

import io.github.stewseo.clients.testcase.YelpFusionTestCase;
import io.github.stewseo.clients.yelpfusion.businesses.match.BusinessMatchResponse;
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

        final BusinessReviewsRequest request = BusinessReviewsRequest.of(a -> a.id(id));

        BusinessReviewsResponse response = yelpFusionServiceCtx.getYelpFusionAsyncClient().businesses().businessReviews(request).get();

        response.reviews().forEach(this::testBusinessReviews);

    }

    @Test
    public void businessReviewsByAliasTest() throws Exception {

        String alias = "katzs-delicatessen-new-york";

        BusinessReviewsRequest request = BusinessReviewsRequest.of(a -> a.alias(alias));

        BusinessReviewsResponse response = yelpFusionServiceCtx.getYelpFusionAsyncClient().businesses().businessReviews(request).get();

        response.reviews().forEach(this::testBusinessReviews);

    }

    void testBusinessReviews(BusinessReview review) {

        assertThat(review).isNotNull();
        assertThat(review.id()).isNotNull();
        assertThat(review.url()).isNotNull();
        assertThat(review.text()).isNotNull();
        assertThat(review.time_created()).isNotNull();
        assertThat(review.user()).isNotNull();
        assertThat(review.rating()).isInstanceOf(Double.class);

        final BusinessReviewsResponse businessReviewsResponse = BusinessReviewsResponse.of(b -> b
                .reviews(review));

        assertThat(businessReviewsResponse).isNotNull();
    }
}
