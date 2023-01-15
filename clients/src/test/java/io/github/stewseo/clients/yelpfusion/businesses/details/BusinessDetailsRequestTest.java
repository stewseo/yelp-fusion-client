package io.github.stewseo.clients.yelpfusion.businesses.details;

import io.github.stewseo.clients.yelpfusion.YelpFusionTest;

import io.github.stewseo.clients.transport.Endpoint;
import io.github.stewseo.clients.yelpfusion.testcases.ModelTestCase;
import io.github.stewseo.clients.yelpfusion.testcases.RequestTestCase;
import jakarta.json.stream.JsonGenerator;
import org.junit.jupiter.api.Tag;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.ALIAS;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.ID;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Tag("businesses")
class BusinessDetailsRequestTest extends ModelTestCase<BusinessDetailsRequest>
        implements RequestTestCase<BusinessDetailsRequest> {

    private final BusinessDetailsRequest businessDetailsRequest = of();

    public BusinessDetailsRequest of() {
        return BusinessDetailsRequest.of(b -> b
                .id(ID)
                .alias(ALIAS)
        );
    }

    @YelpFusionTest
    public void testBuilder() {

        BusinessDetailsRequest.Builder builder = new BusinessDetailsRequest.Builder().id("idValue");

        BusinessDetailsRequest.Builder self = builder.self();

        assertThat(self).isEqualTo(builder);

        BusinessDetailsRequest searchBusinessReq = builder.build();

        assertThat(searchBusinessReq.toString()).isEqualTo("BusinessDetailsRequest: GET v3/businesses/idValue {\"id\":\"idValue\"}");

    }

    @YelpFusionTest
    public void testOf() {
        assertThat(businessDetailsRequest.id()).isEqualTo(ID);
        assertThat(businessDetailsRequest.alias()).isEqualTo(ALIAS);
    }

    private final String expected = "BusinessDetailsRequest: GET v3/businesses/alias {\"id\":\"id\",\"alias\":\"alias\"}";
    private final JsonGenerator generator = generator();

    @YelpFusionTest
    public void testSerialize() {
        businessDetailsRequest.serialize(generator, mapper);
        assertThat(businessDetailsRequest.toString()).isEqualTo(expected);
    }

    @YelpFusionTest
    public void testSerializeInternal() {
        generator.writeStartObject();
        businessDetailsRequest.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        assertThat(businessDetailsRequest.toString()).isEqualTo(expected);
    }

    @Override
    public Endpoint<BusinessDetailsRequest, ?, ?> endpoint() {
        return BusinessDetailsRequest._ENDPOINT;
    }

    @YelpFusionTest
    public void testEndpoint() {

        Endpoint<BusinessDetailsRequest, ?, ?> endpoint = endpoint();

        testEndpointWithAliasPathParam(endpoint, businessDetailsRequest);

        testEndpointWithIdPathParam(endpoint, BusinessDetailsRequest.of(b -> b
                .alias(ALIAS)
                .id(ID)
        ));
    }

    private final String basePath = "v3/businesses";

    private void testEndpointWithIdPathParam(Endpoint<BusinessDetailsRequest, ?, ?> endpoint, BusinessDetailsRequest businessDetailsRequest) {

        assertThat(endpoint.id()).isEqualTo(basePath);

        assertThat(endpoint.requestUrl(businessDetailsRequest)).isEqualTo("v3/businesses/" + ALIAS);

        assertThat(endpoint.hasRequestBody()).isFalse();

        assertThat(endpoint.queryParameters(businessDetailsRequest).values().size()).isEqualTo(0);

        assertThat(endpoint.isError(200)).isFalse();
        assertThat(endpoint.headers(businessDetailsRequest).toString()).isEqualTo("{}");
        assertThat(endpoint.method(businessDetailsRequest)).isEqualTo("GET");
    }

    private void testEndpointWithAliasPathParam(Endpoint<BusinessDetailsRequest, ?, ?> endpoint, BusinessDetailsRequest businessDetailsRequest) {

        assertThat(endpoint.id()).isEqualTo(basePath);

        assertThat(endpoint.requestUrl(businessDetailsRequest)).isEqualTo("v3/businesses/" + ALIAS);

    }

}