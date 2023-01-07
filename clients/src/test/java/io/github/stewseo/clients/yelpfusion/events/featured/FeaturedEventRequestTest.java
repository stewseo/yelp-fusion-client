package io.github.stewseo.clients.yelpfusion.events.featured;

import io.github.stewseo.clients.transport.Endpoint;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionRequestTestCase;
import jakarta.json.stream.JsonGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FeaturedEventRequestTest extends YelpFusionRequestTestCase<FeaturedEventRequest> {

    private final FeaturedEventRequest featuredEventRequest = FeaturedEventRequest.of(b -> b
            .latitude(latitude)
            .longitude(longitude)
            .location(location)
            .locale(locale)
    );

    @Override
    public Endpoint<FeaturedEventRequest, ?, ?> endpoint() {
        return FeaturedEventRequest._ENDPOINT;
    }

    @Test
    public void testOf() {
        assertThat(featuredEventRequest.latitude()).isEqualTo(latitude);
        assertThat(featuredEventRequest.longitude()).isEqualTo(longitude);
        assertThat(featuredEventRequest.location()).isEqualTo(location);
        assertThat(featuredEventRequest.locale()).isEqualTo(locale);

    }

    private final String expected =
            "{\"location\":\"location\",\"latitude\":44.0,\"longitude\":-122.0,\"locale\":\"locale\"}";

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
}