package io.github.stewseo.clients.yelpfusion.businesses.reviews;

import io.github.stewseo.clients.yelpfusion.testcases.FunctionalTestCase;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BusinessReviewsTest extends FunctionalTestCase {

    final BusinessReviewsRequest request = BusinessReviewsRequest.of(a -> a.id("brendas-french-soul-food-san-francisco"));

    @Test
    public void testBusinessReviewsEndpoint() {

        assertThat("v3/businesses/brendas-french-soul-food-san-francisco/reviews")
                .isEqualTo(BusinessReviewsRequest._ENDPOINT.requestUrl(request));
    }

    @Test
    public void businessReviewsByIdTest() throws Exception {

        BusinessReviewsResponse response = yelpFusionServiceCtx.getYelpFusionAsyncClient().businesses().businessReviews(request).get();

        response.reviews().forEach(this::testBusinessReviews);

    }

    @Test
    public void businessReviewsByAliasTest() throws Exception {

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
