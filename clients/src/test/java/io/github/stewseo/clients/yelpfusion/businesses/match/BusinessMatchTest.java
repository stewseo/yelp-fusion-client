package io.github.stewseo.clients.yelpfusion.businesses.match;

import io.github.stewseo.clients.yelpfusion.YelpFusionTest;
import io.github.stewseo.clients.yelpfusion.testcases.ModelTestCase;
import org.junit.jupiter.api.Tag;

import java.util.List;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.ALIAS;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.CENTER;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.ID;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.LOCATION;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.NAME;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.PHONE;
import static org.assertj.core.api.Assertions.assertThat;


@Tag("businesses")
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

    private final String expected = "{\"id\":\"id\",\"alias\":\"alias\",\"name\":\"name\",\"phone\":\"phoneValue\",\"center\":{\"latitude\":37.7829,\"longitude\":-122.4189},\"display\":\"display\",\"location\":{\"address1\":\"addressOneValue\",\"city\":\"cityValue\",\"country\":\"countryValue\",\"state\":\"stateValue\"}}";

    @YelpFusionTest
    public void testBuilder() {
       BusinessMatch businessMatch1 = new BusinessMatch.Builder().id(ID)
               .id(ID)
               .alias(ALIAS)
               .center(CENTER)
               .display("display")
               .location(LOCATION)
               .name(NAME)
               .phone(PHONE)
               .build();

       assertThat(businessMatch1.toString()).isEqualTo(expected);

    }

    @YelpFusionTest
    public void testOf() {
        assertThat(businessMatch.center()).isEqualTo(CENTER);
        assertThat(businessMatch.location()).isEqualTo(LOCATION);
        assertThat(businessMatch.name()).isEqualTo(NAME);
        assertThat(businessMatch.alias()).isEqualTo(ALIAS);
        assertThat(businessMatch.display()).isEqualTo("display");

        List<BusinessMatch> businessMatches = List.of(businessMatch);

        final MatchBusinessesResponse businessMatchResponse = MatchBusinessesResponse.of(b -> b
                .businesses(businessMatches));

        assertThat(businessMatchResponse).isNotNull();
    }

    @YelpFusionTest
    public void testSerialize() {

    }

    @YelpFusionTest
    public void testSerializeInternal() {

    }


}
