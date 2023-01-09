package io.github.stewseo.clients.yelpfusion.businesses.reviews;

import io.github.stewseo.clients.transport.Endpoint;
import io.github.stewseo.clients.yelpfusion.misc.AutoCompleteRequest;
import io.github.stewseo.clients.yelpfusion.testcases.ModelTestCase;
import io.github.stewseo.clients.yelpfusion.testcases.RequestTestCase;
import jakarta.json.stream.JsonGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.github.stewseo.clients.yelpfusion._types.TestData.ALIAS;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BusinessReviewsRequestTest extends ModelTestCase<BusinessReviewsRequest>
        implements RequestTestCase<BusinessReviewsRequest> {

    private final BusinessReviewsRequest businessReviewsRequest = of();

    private final String id = "brendas-french-soul-food-san-francisco";
    private final String alias = "";

    public BusinessReviewsRequest of() {
        return BusinessReviewsRequest.of(a -> a.id(id)
                .alias(alias));
    }

    @Test
    public void testBuilder() {
        BusinessReviewsRequest.Builder builder = new BusinessReviewsRequest.Builder().alias(alias);

        BusinessReviewsRequest.Builder self = builder.self();

        Assertions.assertThat(self).isEqualTo(builder);

        BusinessReviewsRequest businessReviewsReq = builder.build();

        Assertions.assertThat(businessReviewsReq.toString()).isEqualTo("{\"alias\":\"\"}");
    }

    @Test
    public void testOf() {
        assertThat(businessReviewsRequest.id()).isEqualTo(id);
        assertThat(businessReviewsRequest.alias()).isEqualTo(alias);
    }

    private final String expected = "{\"id\":\"brendas-french-soul-food-san-francisco\",\"alias\":\"\"}";

    private final JsonGenerator generator = generator();

    @Test
    public void testSerialize() {
        businessReviewsRequest.serialize(generator, mapper);
        assertThat(businessReviewsRequest.toString()).isEqualTo(expected);
    }

    @Test
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

    @Test
    public void testEndpoint() {

        String basePath = "v3/businesses";

        Endpoint<BusinessReviewsRequest, ?, ?> endpoint = endpoint();

        assertThat(endpoint.id()).isEqualTo(basePath);

        assertThat(endpoint.requestUrl(businessReviewsRequest)).isEqualTo(basePath + "/brendas-french-soul-food-san-francisco/reviews");

        assertThat(endpoint.hasRequestBody()).isFalse();

        assertThat(endpoint.queryParameters(businessReviewsRequest).values().size()).isEqualTo(0);

        assertThat(endpoint.isError(200)).isFalse();
        assertThat(endpoint.headers(businessReviewsRequest).toString()).isEqualTo("{}");
        assertThat(endpoint.method(businessReviewsRequest)).isEqualTo("GET");
    }
}