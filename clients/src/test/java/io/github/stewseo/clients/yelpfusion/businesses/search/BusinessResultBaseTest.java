package io.github.stewseo.clients.yelpfusion.businesses.search;

import io.github.stewseo.clients.yelpfusion.YelpFusionTest;
import io.github.stewseo.clients.yelpfusion.testcases.ModelTestCase;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.*;
import static org.assertj.core.api.Assertions.assertThat;

class BusinessResultBaseTest extends ModelTestCase<SearchBusinessesResult> {

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