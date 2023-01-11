package io.github.stewseo.yelpfusion.businesses.match;

import io.github.stewseo.clients.transport.Endpoint;
import io.github.stewseo.clients.yelpfusion.businesses.match.BusinessMatchRequest;
import io.github.stewseo.clients.yelpfusion.testcases.ModelTestCase;
import io.github.stewseo.clients.yelpfusion.testcases.RequestTestCase;
import jakarta.json.stream.JsonGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BusinessMatchRequestTest extends ModelTestCase<BusinessMatchRequest>
        implements RequestTestCase<BusinessMatchRequest> {

    private final String city = "sf", name = "Brenda's+French+Soul+Food", phone = "4151111111",
            address1="625+polk+st", state="ca", country="US", postal_code="94111", match_threshold="none";

    private final BusinessMatchRequest businessMatchRequest = of();

    public BusinessMatchRequest of() {
        return BusinessMatchRequest.of(b -> b
                .city(city)
                .name(name)
                .phone(phone)
                .address1(address1)
                .address2(null)
                .address3(null)
                .state(state)
                .country(country)
                .postal_code(postal_code)
                .match_threshold(match_threshold)
        );
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

    private final String expected = "" +
            "BusinessMatchRequest: GET v3/businesses/matches?" +
            "country=US&match_threshold=none&city=sf&phone=4151111111&address1=625%2Bpolk%2Bst&" +
            "name=Brenda%27s%2BFrench%2BSoul%2BFood&state=ca " +
            "{\"name\":\"Brenda's+French+Soul+Food\"," +
            "\"address1\":\"625+polk+st\"," +
            "\"city\":\"sf\",\"state\":\"ca\",\"country\":\"US\",\"postal_code\":\"94111\"," +
            "\"phone\":\"4151111111\",\"match_threshold\":\"none\"}";

    private final JsonGenerator generator = generator();

    @Test
    public void testSerialize() {
        businessMatchRequest.serialize(generator, mapper);
        assertThat(businessMatchRequest.toString()).isEqualTo(expected);
    }

    @Test
    public void testSerializeInternal() {
        generator.writeStartObject();
        businessMatchRequest.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        assertThat(businessMatchRequest.toString()).isEqualTo(expected);
    }

    @Override
    public Endpoint<BusinessMatchRequest, ?, ?> endpoint() {
        return BusinessMatchRequest._ENDPOINT;
    }

    @Test
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

    @Test
    public void testBuilder() {
        BusinessMatchRequest.Builder builder = new BusinessMatchRequest.Builder().name("nameValue");

        BusinessMatchRequest.Builder self = builder.self();

        assertThat(self).isEqualTo(builder);

        BusinessMatchRequest businessMatchReq = builder.build();

        assertThat(businessMatchReq.toString()).isEqualTo("BusinessMatchRequest: GET v3/businesses/matches?name=nameValue {\"name\":\"nameValue\"}");

    }
}
