package io.github.stewseo.clients.yelpfusion.businesses.match;

import io.github.stewseo.clients.yelpfusion.testcases.ModelTestCase;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.github.stewseo.clients.yelpfusion._types.TestData.ALIAS;
import static io.github.stewseo.clients.yelpfusion._types.TestData.COORDINATES;
import static io.github.stewseo.clients.yelpfusion._types.TestData.ID;
import static io.github.stewseo.clients.yelpfusion._types.TestData.QueryParameter;
import static io.github.stewseo.clients.yelpfusion._types.TestData.LOCATION;
import static io.github.stewseo.clients.yelpfusion._types.TestData.NAME;
import static io.github.stewseo.clients.yelpfusion._types.TestData.PHONE;
import static org.assertj.core.api.Assertions.assertThat;

public class BusinessMatchTest extends ModelTestCase<BusinessMatch> {

    private final BusinessMatch businessMatch = of();

    public BusinessMatch of() {
        return BusinessMatch.of(a -> a
                .id(ID)
                .alias(ALIAS)
                .coordinates(COORDINATES)
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
                .coordinates(COORDINATES)
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

        assertThat(businessMatch.coordinates()).isNotNull();
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
