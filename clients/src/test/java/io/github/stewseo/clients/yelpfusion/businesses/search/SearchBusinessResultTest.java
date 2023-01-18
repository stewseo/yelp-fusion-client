package io.github.stewseo.clients.yelpfusion.businesses.search;

import io.github.stewseo.clients.json.testcases.AbstractJsonTestCase;
import io.github.stewseo.clients.yelpfusion.YelpFusionTest;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.ALIAS;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.CATEGORY;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.CENTER;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.DISPLAY_PHONE;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.DISTANCE;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.FIELD_URL;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.ID;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.IMAGE_URL;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.IS_CLOSED;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.LOCATION;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.NAME;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.PHONE;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.RATING;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.REVIEW_COUNT;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.TRANSACTIONS;
import static org.assertj.core.api.Assertions.assertThat;


class SearchBusinessResultTest extends AbstractJsonTestCase {

    private final SearchBusinessesResult searchBusinessResult = SearchBusinessesResult.of(e -> e
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
            .price("$")
            .rating(RATING)
            .transactions(TRANSACTIONS)
            .display_phone(DISPLAY_PHONE)
            .alias(ALIAS)
            .url(FIELD_URL)
    );

    private final String expected = "" +
            "{\"id\":\"id\"," +
            "\"alias\":\"alias\"," +
            "\"url\":\"url\"," +
            "\"location\":{\"address1\":\"addressOneValue\",\"city\":\"cityValue\",\"country\":\"countryValue\",\"state\":\"stateValue\"}," +
            "\"image_url\":\"imageUrlValue\"," +
            "\"display_phone\":\"display_phone\"," +
            "\"is_closed\":false," +
            "\"distance\":1.0" +
            "}";

    @YelpFusionTest
    public void testOf() {
        assertThat(searchBusinessResult).hasNoNullFieldsOrProperties();
//        assertThat(searchBusinessResult.distance()).isEqualTo(DISTANCE);
//        assertThat(searchBusinessResult.id()).isEqualTo(ID);
//        assertThat(searchBusinessResult.is_closed()).isEqualTo(IS_CLOSED);
//        assertThat(searchBusinessResult.location()).isEqualTo(LOCATION);
//        assertThat(searchBusinessResult.categories().get(0)).isEqualTo(CATEGORY);
//        assertThat(searchBusinessResult.center()).isEqualTo(CENTER);
//        assertThat(searchBusinessResult.phone()).isEqualTo(PHONE);
//        assertThat(searchBusinessResult.image_url()).isEqualTo(IMAGE_URL);
//        assertThat(searchBusinessResult.phone()).isEqualTo(PHONE);
//        assertThat(searchBusinessResult.price()).isEqualTo(String.valueOf(PRICE));
//        assertThat(searchBusinessResult.name()).isEqualTo(NAME);
//        assertThat(searchBusinessResult.rating()).isEqualTo(RATING);
//        assertThat(searchBusinessResult.transactions()).isEqualTo(TRANSACTIONS);
    }

    @YelpFusionTest
    public void testSerialize() {
        JsonGenerator generator = generator();
        searchBusinessResult.serialize(generator, mapper);

        assertThat(searchBusinessResult).satisfies(res -> {
            assertThat(res).isNotNull();
            assertThat(res).isExactlyInstanceOf(SearchBusinessesResult.class);
        });
    }

    @YelpFusionTest
    public void testSerializeInternal() {
        JsonGenerator generator = generator();
        generator.writeStartObject();
        searchBusinessResult.serializeInternal(generator, mapper);
        generator.writeEnd();

        assertThat(searchBusinessResult.toString()).startsWith("{\"id\":\""+ID+"\"");
        assertThat(searchBusinessResult.toString()).endsWith(DISTANCE+"}");
    }

    @YelpFusionTest
    public void testDeserializer() {

        assertThat(SearchBusinessesResult._DESERIALIZER.toString()).contains("io.github.stewseo.clients.json.LazyDeserializer");

    }

    @YelpFusionTest
    public void testDeserialize() {

        JsonParser parser = parser();

        SearchBusinessesResult searchBusinessRes = SearchBusinessesResult._DESERIALIZER.deserialize(parser, mapper);

        assertThat(searchBusinessRes.toString()).isEqualTo(expected);
    }

    @Override
    public JsonParser parser() {
        return parser(searchBusinessResult);
    }
}