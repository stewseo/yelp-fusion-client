package io.github.stewseo.clients.yelpfusion.businesses.details;

import io.github.stewseo.clients.transport.Endpoint;
import io.github.stewseo.clients.transport.endpoints.SimpleEndpoint;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionRequestTestCase;
import jakarta.json.stream.JsonGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BusinessDetailsRequestTest extends YelpFusionRequestTestCase<BusinessDetailsRequest> {

    private final String id = "idVal", alias = "aliasVal";
    private final BusinessDetailsRequest businessDetailsRequest = of();

    @Override
    public Endpoint<BusinessDetailsRequest, ?, ?> endpoint() {
        return BusinessDetailsRequest._ENDPOINT;
    }

    @Override
    public BusinessDetailsRequest of() {
        return BusinessDetailsRequest.of(b -> b
                .id(id)
                .alias(alias)
        );
    }
    @Test
    public void testOf() {
        assertThat(businessDetailsRequest.id()).isEqualTo(id);
        assertThat(businessDetailsRequest.alias()).isEqualTo(alias);
    }

    private final String expected = "BusinessDetailsRequest: GET v3/businesses/aliasVal " +
            "{\"id\":\"idVal\",\"alias\":\"aliasVal\"}";
    private final JsonGenerator generator = generator();

    @Test
    public void testSerialize() {
        businessDetailsRequest.serialize(generator, mapper);
        assertThat(businessDetailsRequest.toString()).isEqualTo(expected);
    }

    @Test
    public void testSerializeInternal() {
        generator.writeStartObject();
        businessDetailsRequest.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        assertThat(businessDetailsRequest.toString()).isEqualTo(expected);
    }

    @Test
    public void testEndpoint() {

        testEndpointWithAliasPathParam(businessDetailsRequest);

        testEndpointWithIdPathParam(BusinessDetailsRequest.of(b -> b
                .alias(alias)
                .id(id)
        ));

    }

    private final String basePath = "v3/businesses";

    private void testEndpointWithIdPathParam(BusinessDetailsRequest businessDetailsRequest) {

        assertThat(endpoint().id()).isEqualTo(basePath);

        assertThat(endpoint().requestUrl(businessDetailsRequest)).isEqualTo("v3/businesses/" + alias);

        assertThat(endpoint().hasRequestBody()).isFalse();

        assertThat(endpoint().queryParameters(businessDetailsRequest).values().size()).isEqualTo(0);

        assertThat(endpoint().isError(200)).isFalse();
        assertThat(endpoint().headers(businessDetailsRequest).toString()).isEqualTo("{}");
        assertThat(endpoint().method(businessDetailsRequest)).isEqualTo("GET");
    }

    private void testEndpointWithAliasPathParam(BusinessDetailsRequest businessDetailsRequest) {

        assertThat(endpoint().id()).isEqualTo(basePath);

        assertThat(endpoint().requestUrl(businessDetailsRequest)).isEqualTo("v3/businesses/" + alias);

        BusinessDetailsRequest req = new BusinessDetailsRequest.Builder().build();
    }

    @Test
    public void testBuilder() {

        BusinessDetailsRequest.Builder builder = new BusinessDetailsRequest.Builder().id("idValue");

        BusinessDetailsRequest.Builder self = builder.self();

        assertThat(self).isEqualTo(builder);

        BusinessDetailsRequest searchBusinessReq = builder.build();

        assertThat(searchBusinessReq.toString()).isEqualTo("BusinessDetailsRequest: GET v3/businesses/idValue {\"id\":\"idValue\"}");

    }
}