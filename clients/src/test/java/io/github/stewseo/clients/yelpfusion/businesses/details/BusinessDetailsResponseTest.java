package io.github.stewseo.clients.yelpfusion.businesses.details;

import io.github.stewseo.clients.yelpfusion.YelpFusionTest;

import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.junit.jupiter.api.Tag;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.ID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Tag("businesses")
class BusinessDetailsResponseTest extends YelpFusionTestCase<BusinessDetailsResponse> {

    private final BusinessDetailsResponse businessDetailsResponse = of();

    private final String expected = "{\"businesses\":{\"id\":\"id\"}}";

    private final JsonGenerator generator = generator();

    @Override
    public final BusinessDetailsResponse of() {

        return BusinessDetailsResponse.of(b -> b
                .result(BusinessDetails.of(bd -> bd
                        .id(ID)))
        );
    }

    @YelpFusionTest
    public void testSerialize() {
        businessDetailsResponse.serialize(generator, mapper);
        assertThat(businessDetailsResponse.toString()).isEqualTo(expected);
    }

    @YelpFusionTest
    public void testSerializeInternal() {
        generator.writeStartObject();
        businessDetailsResponse.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        assertThat(businessDetailsResponse.toString()).isEqualTo(expected);
    }

    @YelpFusionTest
    public void testOf() {
        assertThat(businessDetailsResponse.toString()).isEqualTo(expected);
    }

    @YelpFusionTest
    public void testBuilder() {

        BusinessDetailsResponse.Builder builder = new BusinessDetailsResponse.Builder().result(BusinessDetails.of(b -> b
                .id("idValue")));

        BusinessDetailsResponse.Builder self = builder.self();

        assertThat(self).isEqualTo(builder);

        BusinessDetailsResponse searchBusinessResp = builder.build();

        assertThat(searchBusinessResp.toString()).isEqualTo("{\"businesses\":{\"id\":\"idValue\"}}");

    }

    @YelpFusionTest
    public void testDeserialize() {

        JsonParser parser = parser();

        BusinessDetailsResponse businessDetailsRes = BusinessDetailsResponse._DESERIALIZER.deserialize(parser, mapper);

        assertThat(businessDetailsRes).isNotNull();
    }

    @YelpFusionTest
    public void testDeserializer() {

        assertThat(BusinessDetailsResponse._DESERIALIZER.toString()).contains("io.github.stewseo.clients.json.JsonpDeserializer");

    }

    public JsonParser parser() {
        return parser(businessDetailsResponse);
    }
}