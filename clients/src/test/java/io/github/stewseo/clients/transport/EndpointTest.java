package io.github.stewseo.clients.transport;

import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetailsRequest;
import io.github.stewseo.clients.yelpfusion.businesses.match.BusinessMatchRequest;
import io.github.stewseo.clients.yelpfusion.businesses.reviews.BusinessReviewsRequest;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessRequest;
import io.github.stewseo.clients.yelpfusion.businesses.transactions.SearchTransactionRequest;
import io.github.stewseo.clients.yelpfusion.categories.all.CategoriesRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class EndpointTest extends Assertions {

    @Test
    public void testPathParameter() {

        CategoriesRequest req;

        req = CategoriesRequest.of(b -> b);

        assertThat("v3/categories").isEqualTo(CategoriesRequest._ENDPOINT.requestUrl(req));

        BusinessDetailsRequest businessDetailsRequest = BusinessDetailsRequest.of(b -> b
                .alias("alias"));

        assertThat("v3/businesses/alias").isEqualTo(BusinessDetailsRequest._ENDPOINT.requestUrl(businessDetailsRequest));

    }


}