package io.github.stewseo.clients.yelpfusion.businesses.search;

import io.github.stewseo.clients.yelpfusion.YelpFusionTest;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionTestCase;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.ALIAS;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.CATEGORY;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.CENTER;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.DISPLAY_PHONE;
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
import static org.assertj.core.api.Assertions.assertThat;

class BusinessResultBaseTest extends YelpFusionTestCase<SearchBusinessesResult> {

    SearchBusinessesResult searchBusinessResult = of();


    public SearchBusinessesResult of() {
        return SearchBusinessesResult.of(e -> e
                .id(ID)
                .image_url(IMAGE_URL)
                .phone(PHONE)
                .name(NAME)
                .price(String.valueOf(PRICE))
                .alias(ALIAS)
                .display_phone(DISPLAY_PHONE)
                .url("url")
                .categories(CATEGORY)
                .center(CENTER)
                .distance(DISTANCE)
                .is_closed(IS_CLOSED)
                .review_count(REVIEW_COUNT)
                .rating(RATING)
                .location(LOCATION)
                .transactions(TRANSACTIONS)

        );
    }

    @YelpFusionTest
    public void testBuilder() {
        assertThat(new SearchBusinessesResult.Builder()
                .id(ID)
                .image_url(IMAGE_URL)
                .phone(PHONE)
                .name(NAME)
                .price(String.valueOf(PRICE))
                .alias(ALIAS)
                .display_phone(DISPLAY_PHONE)
                .url("url")
                .categories(CATEGORY)
                .center(CENTER)
                .distance(DISTANCE)
                .is_closed(IS_CLOSED)
                .review_count(REVIEW_COUNT)
                .rating(RATING)
                .location(LOCATION)
                .transactions(TRANSACTIONS))
                .hasNoNullFieldsOrProperties();
    }

    @YelpFusionTest
    public void testOf() {
        assertThat(searchBusinessResult).hasNoNullFieldsOrProperties();

    }

    @YelpFusionTest
    public void testSerialize() {

    }

    @YelpFusionTest
    public void testSerializeInternal() {

    }

    @YelpFusionTest
    void testToString() {
    }

    @YelpFusionTest
    void setupBusinessResultBaseDeserializer() {
    }

}