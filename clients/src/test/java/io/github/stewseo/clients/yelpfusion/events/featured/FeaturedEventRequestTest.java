package io.github.stewseo.clients.yelpfusion.events.featured;

import io.github.stewseo.clients.transport.Endpoint;
import io.github.stewseo.clients.yelpfusion.YelpFusionTest;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionTestCase;
import io.github.stewseo.clients.yelpfusion.testcases.RequestTestCase;
import jakarta.json.stream.JsonGenerator;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.LATITUDE;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.TestReqVars.LOCALE;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.LOCATION;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.LONGITUDE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class FeaturedEventRequestTest extends YelpFusionTestCase<FeaturedEventRequest>
        implements RequestTestCase<FeaturedEventRequest> {

    @Override
    public Endpoint<FeaturedEventRequest, ?, ?> endpoint() {
        return FeaturedEventRequest._ENDPOINT;
    }

    private final String location = LOCATION.city();

    @Override
    public FeaturedEventRequest of() {
        return FeaturedEventRequest.of(b -> b
                .latitude(LATITUDE)
                .longitude(LONGITUDE)
                .location(location)
                .locale(LOCALE)
        );
    }

    private final FeaturedEventRequest featuredEventRequest = of();
    @YelpFusionTest
    public void testOf() {
        assertThat(featuredEventRequest.latitude()).isEqualTo(LATITUDE);
        assertThat(featuredEventRequest.longitude()).isEqualTo(LONGITUDE);
        assertThat(featuredEventRequest.location()).isEqualTo(location);
        assertThat(featuredEventRequest.locale()).isEqualTo(LOCALE);

    }

    private final String expected = "{\"location\":\"cityValue\",\"latitude\":37.7829,\"longitude\":-122.4189,\"locale\":\"en_US\"}";
    private final JsonGenerator generator = generator();

    @YelpFusionTest
    public void testSerialize() {
        featuredEventRequest.serialize(generator, mapper);
        assertThat(featuredEventRequest.toString()).isEqualTo(expected);
    }

    @YelpFusionTest
    public void testSerializeInternal() {
        generator.writeStartObject();
        featuredEventRequest.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        assertThat(featuredEventRequest.toString()).isEqualTo(expected);
    }

    @YelpFusionTest
    public void testEndpoint() {

        assertThat(endpoint().id()).isEqualTo("v3/events/featured");

        assertThat(endpoint().requestUrl(featuredEventRequest)).isEqualTo("v3/events/featured");

        assertThat(endpoint().hasRequestBody()).isFalse();

        assertThat(endpoint().queryParameters(featuredEventRequest).values().size()).isEqualTo(4);

        assertThat(endpoint().isError(200)).isFalse();
        assertThat(endpoint().headers(featuredEventRequest).toString()).isEqualTo("{}");
        assertThat(endpoint().method(featuredEventRequest)).isEqualTo("GET");

    }

    @Override
    public void testBuilder() {

    }
}