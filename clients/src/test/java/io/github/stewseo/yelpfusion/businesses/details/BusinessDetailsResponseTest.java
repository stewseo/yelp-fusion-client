package io.github.stewseo.yelpfusion.businesses.details;

import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetails;
import io.github.stewseo.clients.yelpfusion.businesses.details.BusinessDetailsResponse;
import io.github.stewseo.clients.yelpfusion.testcases.ModelTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.junit.jupiter.api.Test;

import static io.github.stewseo.clients.yelpfusion._types.TestData.ID;
import static io.github.stewseo.clients.yelpfusion._types.TestData.QueryParameter;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BusinessDetailsResponseTest extends ModelTestCase<BusinessDetailsResponse> {

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

    @Test
    public void testSerialize() {
        businessDetailsResponse.serialize(generator, mapper);
        assertThat(businessDetailsResponse.toString()).isEqualTo(expected);
    }

    @Test
    public void testSerializeInternal() {
        generator.writeStartObject();
        businessDetailsResponse.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        assertThat(businessDetailsResponse.toString()).isEqualTo(expected);
    }

    @Test
    public void testOf() {
        assertThat(businessDetailsResponse.toString()).isEqualTo(expected);
    }

    @Test
    public void testBuilder() {

        BusinessDetailsResponse.Builder builder = new BusinessDetailsResponse.Builder().result(BusinessDetails.of(b -> b
                .id("idValue")));

        BusinessDetailsResponse.Builder self = builder.self();

        assertThat(self).isEqualTo(builder);

        BusinessDetailsResponse searchBusinessResp = builder.build();

        assertThat(searchBusinessResp.toString()).isEqualTo("{\"businesses\":{\"id\":\"idValue\"}}");

    }

    @Test
    public void testDeserialize() {

        JsonParser parser = parser();

        BusinessDetailsResponse businessDetailsRes = BusinessDetailsResponse._DESERIALIZER.deserialize(parser, mapper);

        assertThat(businessDetailsRes).isNotNull();
    }

    @Test
    public void testDeserializer() {

        assertThat(BusinessDetailsResponse._DESERIALIZER.toString()).contains("io.github.stewseo.clients.json.JsonpDeserializer");

    }

    public JsonParser parser() {
        return parser(businessDetailsResponse);
    }
}