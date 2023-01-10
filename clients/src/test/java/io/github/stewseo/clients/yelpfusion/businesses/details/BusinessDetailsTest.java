package io.github.stewseo.clients.yelpfusion.businesses.details;

import io.github.stewseo.clients.yelpfusion._types.Event;
import io.github.stewseo.clients.yelpfusion._types.Hours;
import io.github.stewseo.clients.yelpfusion.testcases.ModelTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static io.github.stewseo.clients.yelpfusion._types.TestData.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BusinessDetailsTest extends ModelTestCase<BusinessDetails> {

    private final BusinessDetails business = of();

    public BusinessDetails of() {
        return BusinessDetails.of(b -> b
                .id(ID)
                .alias(ALIAS)
                .coordinates(COORDINATES)
                .hours(HOURS)
                .hours(h -> h.hours_type("hoursTypeValue"))
                .hours(List.of(HOURS))
                .location(LOCATION)
                .review_count(REVIEW_COUNT)
                .rating(RATING)
                .categories(CATEGORY)
                .categories(List.of(CATEGORY))
                .categories(cat -> cat.alias(ALIAS))
                .phone(PHONE)
                .image_url(IMAGE_URL)
                .price(PRICE_STRING)
                .is_closed(IS_CLOSED)
                .display_phone("display_phone")
                .is_claimed(IS_CLAIMED)
                .name(NAME)
                .url(FIELD_URL)
                .messaging(MESSAGING)
                .transactions(TRANSACTIONS)
                .photos(PHOTOS)
                .photos(List.of(PHOTOS))
        );
    }

    @Test
    public void testBuilder() {
        BusinessDetails.Builder builder = new BusinessDetails.Builder().name("nameValue");

        BusinessDetails.Builder self = builder.self();

        assertThat(self).isEqualTo(builder);

        BusinessDetails businessDetails = builder.build();

        assertThat(businessDetails.toString()).isEqualTo("{\"name\":\"nameValue\"}");

    }
    @Test
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
        assertThat(business.coordinates()).isNotNull();
        assertThat(business.image_url()).isNotNull();
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

    JsonGenerator generator = generator();

    private final String expected = "" +
            "{\"id\":\"id\",\"alias\":\"alias\"," +
            "\"name\":\"name\"," +
            "\"image_url\":\"imageUrlValue\"," +
            "\"url\":\"url\",\"phone\":\"phoneValue\",\"price\":\"$\"," +
            "\"display_phone\":\"display_phone\",\"is_closed\":false,\"rating\":4.5," +
            "\"review_count\":1,\"location\":{\"address1\":\"addressOneValue\",\"city\":\"cityValue\",\"country\":\"countryValue\",\"state\":\"stateValue\"},\"coordinates\":{\"latitude\":37.7829,\"longitude\":-122.4189},\"transactions\":[\"transactionValue\"],\"messaging\":{\"use_case_text\":\"use_case_text\"},\"photos\":[\"photos\",\"photos\"],\"hours\":[{\"hours_type\":\"hoursType\"},{\"hours_type\":\"hoursTypeValue\"},{\"hours_type\":\"hoursType\"}],\"categories\":[{\"alias\":\"catAlias\"}," +
            "{\"alias\":\"catAlias\"},{\"alias\":\"alias\"}]}";

    @Test
    public void testSerialize() {
        JsonGenerator generator = generator();
        business.serialize(generator, mapper);
        assertThat(business).isNotNull();
    }

    @Test
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

    @Test
    public void testDeserialize() {

        JsonParser parser = parser();

        BusinessDetails businessDetails = BusinessDetails._DESERIALIZER.deserialize(parser, mapper);

        assertThat(businessDetails).isNotNull();
    }

    @Test
    public void testDeserializer() {
        assertThat(Event._DESERIALIZER.toString()).contains("io.github.stewseo.clients.json.LazyDeserializer");
    }
}