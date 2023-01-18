package io.github.stewseo.clients.yelpfusion.businesses.reviews;

import io.github.stewseo.clients.transport.Endpoint;
import io.github.stewseo.clients.yelpfusion.YelpFusionTest;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionTestCase;
import io.github.stewseo.clients.yelpfusion.testcases.RequestTestCase;
import jakarta.json.stream.JsonGenerator;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.ALIAS;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.ID;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class BusinessReviewsRequestTest extends YelpFusionTestCase<BusinessReviewsRequest>
        implements RequestTestCase<BusinessReviewsRequest> {

    private final BusinessReviewsRequest businessReviewsRequest = of();

    public BusinessReviewsRequest of() {
        return BusinessReviewsRequest.of(a -> a.id(ID)
                .alias(ALIAS));
    }
    private final String expected = "BusinessReviewsRequest: GET v3/businesses/id/reviews {\"id\":\"id\",\"alias\":\"alias\"}";

    @YelpFusionTest
    public void testBuilder() {
        BusinessReviewsRequest.Builder builder = new BusinessReviewsRequest.Builder().alias(ALIAS);

        BusinessReviewsRequest.Builder self = builder.self();

        assertThat(self).isEqualTo(builder);

        BusinessReviewsRequest businessReviewsReq = builder.build();

        assertThat(businessReviewsReq.toString()).isEqualTo("BusinessReviewsRequest: GET v3/businesses/alias/reviews {\"alias\":\"alias\"}");
    }

    @YelpFusionTest
    public void testOf() {
        assertThat(businessReviewsRequest.id()).isEqualTo(ID);
        assertThat(businessReviewsRequest.alias()).isEqualTo(ALIAS);
    }

    private final JsonGenerator generator = generator();

    @YelpFusionTest
    public void testSerialize() {
        businessReviewsRequest.serialize(generator, mapper);
        assertThat(businessReviewsRequest.toString()).isEqualTo(expected);
    }

    @YelpFusionTest
    public void testSerializeInternal() {
        generator.writeStartObject();
        businessReviewsRequest.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        assertThat(businessReviewsRequest.toString()).isEqualTo(expected);
    }

    @Override
    public Endpoint<BusinessReviewsRequest, ?, ?> endpoint() {
        return BusinessReviewsRequest._ENDPOINT;
    }

    @YelpFusionTest
    public void testEndpoint() {

        String basePath = "v3/businesses";

        Endpoint<BusinessReviewsRequest, ?, ?> endpoint = endpoint();

        assertThat(endpoint.id()).isEqualTo(basePath);

        assertThat(endpoint.requestUrl(businessReviewsRequest))
                .isEqualTo(basePath + "/id/reviews");

        assertThat(endpoint.hasRequestBody()).isFalse();

        assertThat(endpoint.queryParameters(businessReviewsRequest).values().size()).isEqualTo(0);

        assertThat(endpoint.isError(200)).isFalse();
        assertThat(endpoint.headers(businessReviewsRequest).toString()).isEqualTo("{}");
        assertThat(endpoint.method(businessReviewsRequest)).isEqualTo("GET");
    }
}