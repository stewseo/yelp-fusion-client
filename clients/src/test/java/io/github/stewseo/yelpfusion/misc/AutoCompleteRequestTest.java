package io.github.stewseo.yelpfusion.misc;

import io.github.stewseo.clients.transport.Endpoint;
import io.github.stewseo.clients.yelpfusion.misc.AutoCompleteRequest;
import io.github.stewseo.clients.yelpfusion.testcases.ModelTestCase;
import io.github.stewseo.clients.yelpfusion.testcases.RequestTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.junit.jupiter.api.Test;

import static io.github.stewseo.clients.yelpfusion._types.TestData.LATITUDE;
import static io.github.stewseo.clients.yelpfusion._types.TestData.LOCALE;
import static io.github.stewseo.clients.yelpfusion._types.TestData.LONGITUDE;
import static org.assertj.core.api.Assertions.assertThat;

public class AutoCompleteRequestTest
        extends ModelTestCase<AutoCompleteRequest>
        implements RequestTestCase<AutoCompleteRequest> {

    private final String text = "text";

    private final AutoCompleteRequest autoCompleteRequest = of();

    public AutoCompleteRequest of() {
        return AutoCompleteRequest.of(a -> a
                .text(text)
                .locale(LOCALE)
                .latitude(LATITUDE)
                .longitude(LONGITUDE));
    }

    @Test
    public void testBuilder() {

        AutoCompleteRequest.Builder builder = new AutoCompleteRequest.Builder().text("textValue");

        AutoCompleteRequest.Builder self = builder.self();

        assertThat(self).isEqualTo(builder);

        AutoCompleteRequest searchBusinessReq = builder.build();

        assertThat(searchBusinessReq.toString()).isEqualTo("{\"text\":\"textValue\"}");
    }

    @Test
    public void testOf() {
        assertThat(autoCompleteRequest.text()).isEqualTo(text);
        assertThat(autoCompleteRequest.locale()).isEqualTo(LOCALE);
        assertThat(autoCompleteRequest.latitude()).isEqualTo(LATITUDE);
        assertThat(autoCompleteRequest.longitude()).isEqualTo(LONGITUDE);
    }

    private final String expected = "{\"text\":\"text\",\"latitude\":37.7829,\"longitude\":-122.4189,\"locale\":\"en_US\"}";

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

    public JsonParser parser() {
        return parser(autoCompleteRequest);
    }
}
