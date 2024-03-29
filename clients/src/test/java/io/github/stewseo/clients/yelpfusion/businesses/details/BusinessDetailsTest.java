package io.github.stewseo.clients.yelpfusion.businesses.details;

import io.github.stewseo.clients.yelpfusion.YelpFusionTest;

import io.github.stewseo.clients.yelpfusion._types.Event;
import io.github.stewseo.clients.yelpfusion._types.Hours;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.junit.jupiter.api.Tag;

import java.util.Objects;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.EXPECTED_BUSINESS_DETAILS_RESULT;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.FIELD_URL;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.IS_CLAIMED;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.REVIEW_COUNT;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Tag("businesses")
public class BusinessDetailsTest extends YelpFusionTestCase<BusinessDetails> {

    private final BusinessDetails business = of();

    public BusinessDetails of() {
        return EXPECTED_BUSINESS_DETAILS_RESULT;
    }

    @YelpFusionTest
    public void testBuilder() {
        BusinessDetails.Builder builder = new BusinessDetails.Builder().name("nameValue");

        BusinessDetails.Builder self = builder.self();

        assertThat(self).isEqualTo(builder);

        BusinessDetails businessDetails = builder.build();

        assertThat(businessDetails.toString()).isEqualTo("{\"name\":\"nameValue\"}");

    }
    @YelpFusionTest
    public void testOf() {
        assertThat(business.price()).isNotNull();
        assertThat(business.toString()).isNotNull();
        assertThat(business.id()).isNotNull();
        assertThat(business.review_count()).isEqualTo(REVIEW_COUNT);
        assertThat(business.rating()).isGreaterThanOrEqualTo(3.5);
        assertThat(business.hours()).isNotNull();
        Hours hours = Objects.requireNonNull(business.hours()).get(0);
        assertThat(hours.open()).isNotNull();
        assertThat(business.categories()).isNotNull();
        assertThat(business.display_phone()).isNotNull();
        assertThat(business.center()).isNotNull();
        assertThat(business.phone()).isNotNull();
        assertThat(business.location()).isNotNull();
        assertThat(business.name()).isNotNull();
        assertThat(business.photos()).isNotNull();
        assertThat(business.url()).isEqualTo(FIELD_URL);
        assertThat(business.is_claimed()).isEqualTo(IS_CLAIMED);
        assertThat(business.is_closed()).isNotNull();
        assertThat(business.special_hours()).isNotNull();
        assertThat(business.transactions()).isNotNull();
    }

    private final String expected = "" +
            "{\"id\":\"id\",\"alias\":\"alias\"," +
            "\"name\":\"name\"," +
            "\"image_url\":\"imageUrlValue\"," +
            "\"url\":\"url\",\"phone\":\"phoneValue\",\"price\":\"$\"," +
            "\"display_phone\":\"display_phone\",\"is_closed\":false,\"rating\":4.5," +
            "\"review_count\":1,\"location\":{\"address1\":\"addressOneValue\",\"city\":\"cityValue\",\"country\":\"countryValue\",\"state\":\"stateValue\"},\"coordinates\":{\"latitude\":37.7829,\"longitude\":-122.4189},\"transactions\":[\"transactionValue\"],\"messaging\":{\"use_case_text\":\"use_case_text\"},\"photos\":[\"photos\",\"photos\"],\"hours\":[{\"hours_type\":\"hoursType\"},{\"hours_type\":\"hoursTypeValue\"},{\"hours_type\":\"hoursType\"}],\"categories\":[{\"alias\":\"catAlias\"}," +
            "{\"alias\":\"catAlias\"},{\"alias\":\"alias\"}]}";

    @YelpFusionTest
    public void testSerialize() {
        JsonGenerator generator = generator();
        business.serialize(generator, mapper);
        assertThat(business).isNotNull();
    }

    @YelpFusionTest
    public void testSerializeInternal() {
        JsonGenerator generator = generator();
        generator.writeStartObject();
        business.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        assertThat(business).isNotNull();
    }

    public JsonParser parser() {
        return parser(business);
    }

    @YelpFusionTest
    public void testDeserialize() {

        JsonParser parser = parser();

        BusinessDetails businessDetails = BusinessDetails._DESERIALIZER.deserialize(parser, mapper);

        assertThat(businessDetails).isNotNull();
    }

    @YelpFusionTest
    public void testDeserializer() {
        assertThat(Event._DESERIALIZER.toString()).contains("io.github.stewseo.clients.json.LazyDeserializer");
    }
}