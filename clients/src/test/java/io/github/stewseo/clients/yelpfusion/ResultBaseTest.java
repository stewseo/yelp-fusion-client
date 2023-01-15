package io.github.stewseo.clients.yelpfusion;

import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessResult;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.ALIAS;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.IMAGE_URL;
import static org.assertj.core.api.Assertions.assertThat;


class ResultBaseTest {


    @YelpFusionTest
    void id() {

        SearchBusinessResult searchBusinessResult = SearchBusinessResult.of(sbr -> sbr
                .image_url(IMAGE_URL)
                .alias(ALIAS)
        );

        assertThat(searchBusinessResult).isNotNull();
        assertThat(searchBusinessResult.image_url()).isNotNull();
        assertThat(searchBusinessResult.alias()).isNotNull();
    }
}