package io.github.stewseo.clients.yelpfusion.businesses.search;

import io.github.stewseo.clients.json.testcases.ModelJsonTestCase;
import io.github.stewseo.clients.yelpfusion.YelpFusionTest;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.CATEGORY;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.CENTER;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.DISTANCE;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.ID;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.IMAGE_URL;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.IS_CLOSED;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.LOCATION;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.NAME;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.PHONE;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.PRICE;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.RATING;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.REVIEW_COUNT;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.TRANSACTIONS;


class SearchBusinessResultTest extends ModelJsonTestCase {

    private final SearchBusinessResult searchBusinessResult = SearchBusinessResult.of(e -> e
            .distance(DISTANCE)
            .id(ID)
            .is_closed(IS_CLOSED)
            .review_count(REVIEW_COUNT)
            .categories(CATEGORY)
            .location(LOCATION)
            .center(CENTER)
            .image_url(IMAGE_URL)
            .phone(PHONE)
            .name(NAME)
            .price(String.valueOf(PRICE))
            .rating(RATING)
            .transactions(TRANSACTIONS)

    );

    private final String expected = "" +
            "{\"id\":\"id\"," +
            "\"name\":\"name\"," +
            "\"phone\":\"phoneValue\",\"price\":\"3\",\"center\":{\"latitude\":37.7829,\"longitude\":-122.4189},\"categories\":[{\"alias\":\"alias\"}],\"image_url\":\"imageUrlValue\",\"is_closed\":false,\"distance\":1.0,\"rating\":4.5,\"review_count\":1,\"transactions\":[\"transactionValue\"],\"location\":{\"address1\":\"addressOneValue\",\"city\":\"cityValue\",\"country\":\"countryValue\"," +
            "\"state\":\"stateValue\"}}";

    @YelpFusionTest
    public void testOf() {
        assertThat(searchBusinessResult.distance()).isEqualTo(DISTANCE);
        assertThat(searchBusinessResult.id()).isEqualTo(ID);
        assertThat(searchBusinessResult.is_closed()).isEqualTo(IS_CLOSED);
        assertThat(searchBusinessResult.location()).isEqualTo(LOCATION);
        assertThat(searchBusinessResult.categories().get(0)).isEqualTo(CATEGORY);
        assertThat(searchBusinessResult.center()).isEqualTo(CENTER);
        assertThat(searchBusinessResult.phone()).isEqualTo(PHONE);
        assertThat(searchBusinessResult.image_url()).isEqualTo(IMAGE_URL);
        assertThat(searchBusinessResult.phone()).isEqualTo(PHONE);
        assertThat(searchBusinessResult.price()).isEqualTo(String.valueOf(PRICE));
        assertThat(searchBusinessResult.name()).isEqualTo(NAME);
        assertThat(searchBusinessResult.rating()).isEqualTo(RATING);
        assertThat(searchBusinessResult.transactions()).isEqualTo(TRANSACTIONS);
    }

    @YelpFusionTest
    public void testSerialize() {
        JsonGenerator generator = generator();
        searchBusinessResult.serialize(generator, mapper);

        assertThat(searchBusinessResult.toString()).isEqualTo(expected);
    }

    @YelpFusionTest
    public void testSerializeInternal() {
        JsonGenerator generator = generator();
        generator.writeStartObject();
        searchBusinessResult.serializeInternal(generator, mapper);
        generator.writeEnd();

        assertThat(searchBusinessResult.toString()).isEqualTo(expected);
    }

    @YelpFusionTest
    public void testDeserializer() {
        assertThat(SearchBusinessResult._DESERIALIZER.toString()).contains("io.github.stewseo.clients.json.LazyDeserializer");

    }

    @YelpFusionTest
    public void testDeserialize() {

        JsonParser parser = parser();

        SearchBusinessResult searchBusinessRes = SearchBusinessResult._DESERIALIZER.deserialize(parser, mapper);

        assertThat(searchBusinessRes.toString()).isEqualTo(expected);
    }

    @Override
    public JsonParser parser() {
        return parser(searchBusinessResult);
    }
}