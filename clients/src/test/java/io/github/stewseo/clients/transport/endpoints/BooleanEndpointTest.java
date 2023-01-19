package io.github.stewseo.clients.transport.endpoints;


import io.github.stewseo.clients.transport.TransportTest;
import io.github.stewseo.clients.yelpfusion.businesses.reviews.BusinessReviewsRequest;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessesRequest;
import org.junit.jupiter.api.Assertions;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class BooleanEndpointTest extends Assertions {

    @TransportTest
    public void testHasRequestBody() {
        assertThat(BusinessReviewsRequest._ENDPOINT.hasRequestBody()).isFalse();
        assertThat(SearchBusinessesRequest._ENDPOINT.hasRequestBody()).isFalse();

    }
}