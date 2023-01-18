package io.github.stewseo.clients.yelpfusion.businesses.match;

import io.github.stewseo.clients.transport.Endpoint;
import io.github.stewseo.clients.yelpfusion.YelpFusionTest;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionTestCase;
import io.github.stewseo.clients.yelpfusion.testcases.RequestTestCase;
import jakarta.json.stream.JsonGenerator;
import org.junit.jupiter.api.Tag;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Tag("businesses")
public class BusinessMatchRequestTest extends YelpFusionTestCase<MatchBusinessesRequest>
        implements RequestTestCase<MatchBusinessesRequest> {

    private final String city = "sf", name = "Brenda's+French+Soul+Food", phone = "4151111111",
            address1="625+polk+st", state="ca", country="US", postal_code="94111", match_threshold="none";

    private final MatchBusinessesRequest businessMatchRequest = of();

    public MatchBusinessesRequest of() {
        return MatchBusinessesRequest.of(b -> b
                .location(l -> l
                        .city(city)
                        .address1(address1)
                        .address2(null)
                        .address3(null)
                        .state(state)
                        .country(country)
                )
                .name(name)
                .phone(phone)
                .postal_code(postal_code)
                .match_threshold(match_threshold)
        );
    }

    @YelpFusionTest
    public void testOf() {
        assertThat(businessMatchRequest.location()).hasFieldOrPropertyWithValue("address1", "625+polk+st");

        assertThat(businessMatchRequest).hasFieldOrPropertyWithValue("name", name);
        assertThat(businessMatchRequest).hasFieldOrPropertyWithValue("phone", phone);

        assertThat(businessMatchRequest.location().address1()).isEqualTo(address1);
        assertThat(businessMatchRequest.location().address2()).isEqualTo(null);
        assertThat(businessMatchRequest.location().address3()).isEqualTo(null);
        assertThat(businessMatchRequest.location().state()).isEqualTo(state);
        assertThat(businessMatchRequest.location().country()).isEqualTo(country);
        assertThat(businessMatchRequest.match_threshold()).isEqualTo(match_threshold);

    }

    private final String expectedStartsWith = "MatchBusinessesRequest: GET v3/businesses/matches?";

    private final JsonGenerator generator = generator();

    @YelpFusionTest
    public void testSerialize() {
        businessMatchRequest.serialize(generator, mapper);
        assertThat(businessMatchRequest.toString()).startsWith(expectedStartsWith);
    }

    @YelpFusionTest
    public void testSerializeInternal() {
        generator.writeStartObject();
        businessMatchRequest.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        assertThat(businessMatchRequest.toString()).startsWith(expectedStartsWith);
    }

    @Override
    public Endpoint<MatchBusinessesRequest, ?, ?> endpoint() {
        return MatchBusinessesRequest._ENDPOINT;
    }

    @YelpFusionTest
    public void testEndpoint() {

        assertThat(endpoint().id()).isEqualTo("v3/businesses/matches");

        assertThat(endpoint().hasRequestBody()).isFalse();

        assertThat(endpoint().requestUrl(businessMatchRequest)).isEqualTo("v3/businesses/matches");

        assertThat(endpoint().queryParameters(businessMatchRequest).values().toString())
                .isEqualTo("[US, none, sf, 4151111111, 625+polk+st, Brenda's+French+Soul+Food, ca]");

        assertThat(endpoint().isError(200)).isFalse();
        assertThat(endpoint().headers(businessMatchRequest).toString()).isEqualTo("{}");
        assertThat(endpoint().method(businessMatchRequest)).isEqualTo("GET");

    }

    @YelpFusionTest
    public void testBuilder() {
        MatchBusinessesRequest.Builder builder = new MatchBusinessesRequest.Builder().name("nameValue");

        MatchBusinessesRequest.Builder self = builder.self();

        assertThat(self).isEqualTo(builder);

        MatchBusinessesRequest businessMatchReq = builder.build();

        assertThat(businessMatchReq.toString()).startsWith("MatchBusinessesRequest: GET v3/businesses/matches");

    }
}
