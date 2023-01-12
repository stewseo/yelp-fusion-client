package io.github.stewseo.clients.yelpfusion.businesses.match;

import io.github.stewseo.clients.yelpfusion.businesses.match.BusinessMatch;
import io.github.stewseo.clients.yelpfusion.businesses.match.BusinessMatchResponse;
import io.github.stewseo.clients.yelpfusion.testcases.ModelTestCase;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.ALIAS;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.CENTER;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.ID;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.LOCATION;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.NAME;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.PHONE;
import static org.assertj.core.api.Assertions.assertThat;

public class BusinessMatchTest extends ModelTestCase<BusinessMatch> {

    private final BusinessMatch businessMatch = of();

    public BusinessMatch of() {
        return BusinessMatch.of(a -> a
                .id(ID)
                .alias(ALIAS)
                .center(CENTER)
                .display("display")
                .location(LOCATION)
                .name(NAME)
                .phone(PHONE)
        );
    }

    @Test
    public void testBuilder() {
       assertThat(new BusinessMatch.Builder().id(ID)
                .alias(ALIAS)
                .center(CENTER)
                .display("display")
                .location(LOCATION)
                .name(NAME)
                .phone(PHONE)).isNotNull();

    }

    @Override
    public void testOf() {
        assertThat(businessMatch.alias()).isNotNull();
    }


    void testOf(BusinessMatch businessMatch) {

        assertThat(businessMatch.center()).isNotNull();
        assertThat(businessMatch.location()).isNotNull();
        assertThat(businessMatch.name()).isEqualTo("Brenda's French Soul Food");
        assertThat(businessMatch.alias()).isEqualTo("brendas-french-soul-food-san-francisco-6");
        assertThat(businessMatch.phone()).isNotNull();
        assertThat(businessMatch.display()).isNull();

        List<BusinessMatch> businessMatches = List.of(businessMatch);

        final BusinessMatchResponse businessMatchResponse = BusinessMatchResponse.of(b -> b
                .businesses(businessMatches));

        assertThat(businessMatchResponse).isNotNull();
    }

    @Test
    public void testSerialize() {

    }

    @Test
    public void testSerializeInternal() {

    }


}
