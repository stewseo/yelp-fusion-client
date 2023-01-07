package io.github.stewseo.clients.yelpfusion.misc;

import io.github.stewseo.clients.transport.Endpoint;
import io.github.stewseo.clients.yelpfusion.events.search.SearchEventsRequest;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionRequestTestCase;
import jakarta.json.stream.JsonGenerator;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoCompleteRequestTest extends YelpFusionRequestTestCase<AutoCompleteRequest> {

    private final String text = "text";

    private final AutoCompleteRequest autoCompleteRequest = AutoCompleteRequest.of(a -> a
            .text(text)
            .locale(locale)
            .latitude(latitude)
            .longitude(longitude));

    @Test
    public void testOf() {
        assertThat(autoCompleteRequest.text()).isEqualTo(text);
        assertThat(autoCompleteRequest.locale()).isEqualTo(locale);
        assertThat(autoCompleteRequest.latitude()).isEqualTo(latitude);
        assertThat(autoCompleteRequest.longitude()).isEqualTo(longitude);
    }

    private final String expected = "{\"text\":\"text\",\"latitude\":44.0,\"longitude\":-122.0,\"locale\":\"locale\"}";

    @Test
    public void testSerialize() {
        JsonGenerator generator = generator();
        autoCompleteRequest.serialize(generator, mapper);
        assertThat(autoCompleteRequest.toString()).isEqualTo(expected);
    }

    @Test
    public void testSerializeInternal() {
        JsonGenerator generator = generator();
        generator.writeStartObject();
        autoCompleteRequest.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        assertThat(autoCompleteRequest.toString()).isEqualTo(expected);
    }

    @Override
    public Endpoint<AutoCompleteRequest, ?, ?> endpoint() {
        return AutoCompleteRequest._ENDPOINT;
    }

    @Test
    public void testEndpoint() {

        Endpoint<AutoCompleteRequest, ?, ?> autoCompleteEndpoint = endpoint();

        assertThat("v3/autocomplete").isEqualTo(autoCompleteEndpoint.requestUrl(autoCompleteRequest));
        assertThat("GET").isEqualTo(autoCompleteEndpoint.method(autoCompleteRequest));
        assertThat(autoCompleteEndpoint.isError(200)).isFalse();

    }

}
