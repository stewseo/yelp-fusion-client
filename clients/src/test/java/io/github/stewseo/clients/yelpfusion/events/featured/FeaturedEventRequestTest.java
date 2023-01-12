package io.github.stewseo.clients.yelpfusion.events.featured;

import io.github.stewseo.clients.transport.Endpoint;
import io.github.stewseo.clients.yelpfusion.events.featured.FeaturedEventRequest;
import io.github.stewseo.clients.yelpfusion.testcases.ModelTestCase;
import io.github.stewseo.clients.yelpfusion.testcases.RequestTestCase;
import jakarta.json.stream.JsonGenerator;
import org.junit.jupiter.api.Test;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.LATITUDE;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.LOCALE;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.LONGITUDE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FeaturedEventRequestTest extends ModelTestCase<FeaturedEventRequest>
        implements RequestTestCase<FeaturedEventRequest> {

    private final FeaturedEventRequest featuredEventRequest = of();

    private final String location = "locationValue";

    @Override
    public Endpoint<FeaturedEventRequest, ?, ?> endpoint() {
        return FeaturedEventRequest._ENDPOINT;
    }

    @Override
    public FeaturedEventRequest of() {
        return FeaturedEventRequest.of(b -> b
                .latitude(LATITUDE)
                .longitude(LONGITUDE)
                .location(location)
                .locale(LOCALE)
        );
    }

    @Test
    public void testOf() {
        assertThat(featuredEventRequest.latitude()).isEqualTo(LATITUDE);
        assertThat(featuredEventRequest.longitude()).isEqualTo(LONGITUDE);
        assertThat(featuredEventRequest.location()).isEqualTo(location);
        assertThat(featuredEventRequest.locale()).isEqualTo(LOCALE);

    }

    private final String expected =
            "{\"location\":\"locationValue\",\"latitude\":37.7829,\"longitude\":-122.4189,\"locale\":\"en_US\"}";

    private final JsonGenerator generator = generator();

    @Test
    public void testSerialize() {
        featuredEventRequest.serialize(generator, mapper);
        assertThat(featuredEventRequest.toString()).isEqualTo(expected);
    }

    @Test
    public void testSerializeInternal() {
        generator.writeStartObject();
        featuredEventRequest.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        assertThat(featuredEventRequest.toString()).isEqualTo(expected);
    }

    @Test
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