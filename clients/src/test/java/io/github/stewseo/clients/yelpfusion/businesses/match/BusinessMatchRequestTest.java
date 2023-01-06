package io.github.stewseo.clients.yelpfusion.businesses.match;

import io.github.stewseo.clients.transport.Endpoint;
import io.github.stewseo.clients.yelpfusion.events.search.SearchEventsRequest;
import io.github.stewseo.clients.yelpfusion.testcases.RequestTestCase;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BusinessMatchRequestTest implements RequestTestCase<BusinessMatchRequest> {

    private final String phone = "sf", name = "Brenda's+French+Soul+Food",
            address1="625+polk+st", city="sf",
            state="ca", country="US", postal_code="94111", match_threshold="none";

    private final BusinessMatchRequest businessMatchRequest = BusinessMatchRequest.of(b -> b
            .city(city)
            .name(name)
            .address1(address1)
            .address2(null)
            .address3(null)
            .state(state)
            .country(country)
            .postal_code(postal_code)
            .match_threshold(match_threshold)
    );
    @Override
    public Endpoint<BusinessMatchRequest, ?, ?> endpoint() {
        return BusinessMatchRequest._ENDPOINT;
    }

    @Test
    public void testOf() {
        assertThat(businessMatchRequest.city()).isEqualTo(city);
        assertThat(businessMatchRequest.name()).isEqualTo(name);
        assertThat(businessMatchRequest.phone()).isEqualTo(phone);
        assertThat(businessMatchRequest.address1()).isEqualTo(address1);
        assertThat(businessMatchRequest.address2()).isEqualTo(null);
        assertThat(businessMatchRequest.address3()).isEqualTo(null);
        assertThat(businessMatchRequest.state()).isEqualTo(state);
        assertThat(businessMatchRequest.country()).isEqualTo(country);
        assertThat(businessMatchRequest.match_threshold()).isEqualTo(match_threshold);

    }

    private final String expected = "{\"address1\"=\"625+polk+st\", \"city\"=\"sf\", \"country\"=\"US\", \"match_threshold\"=\"none\", \"name\"=\"Brenda's+French+Soul+Food\", \"state\"=\"ca\"}";

    @Test
    public void testSerialize() {
        businessMatchRequest.serialize(generator, mapper);
        assertThat(businessMatchRequest.toString()).isEqualTo(expected);
    }

    @Test
    public void testSerializeInternal() {
        businessMatchRequest.serialize(generator, mapper);
        assertThat(businessMatchRequest.toString()).isEqualTo(expected);
    }


    @Test
    public void testEndpoint() {

        assertThat(endpoint().id()).isEqualTo("v3/businesses/matches");

        assertThat(endpoint().hasRequestBody()).isFalse();

        assertThat(endpoint().requestUrl(businessMatchRequest)).isEqualTo("v3/businesses/matches");

        assertThat(endpoint().queryParameters(businessMatchRequest))
                .isEqualTo("" +
                        "{\"address1\"=\"625+polk+st\", " +
                        "\"city\"=\"sf\", " +
                        "\"country\"=\"US\", " +
                        "\"match_threshold\"=\"none\", " +
                        "\"name\"=\"Brenda's+French+Soul+Food\", " +
                        "\"state\"=\"ca\"}");
    }
}
