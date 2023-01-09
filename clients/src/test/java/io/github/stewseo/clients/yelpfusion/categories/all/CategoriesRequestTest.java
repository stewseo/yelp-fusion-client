package io.github.stewseo.clients.yelpfusion.categories.all;

import io.github.stewseo.clients.transport.Endpoint;
import io.github.stewseo.clients.yelpfusion.testcases.ModelTestCase;
import io.github.stewseo.clients.yelpfusion.testcases.RequestTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.junit.jupiter.api.Test;

import static io.github.stewseo.clients.yelpfusion._types.TestData.LOCALE;
import static org.assertj.core.api.Assertions.assertThat;

public class CategoriesRequestTest
        extends ModelTestCase<CategoriesRequest>
        implements RequestTestCase<CategoriesRequest> {

    private final String text = "text";

    private final CategoriesRequest categoriesRequest = of();

    public CategoriesRequest of() {
        return CategoriesRequest.of(a -> a
                .locale(LOCALE)
        );
    }

    @Test
    public void testOf() {
        assertThat(categoriesRequest.locale()).isEqualTo(LOCALE);
    }

    private final String expected = "{\"locale\":\"en_US\"}";

    @Test
    public void testSerialize() {
        JsonGenerator generator = generator();
        categoriesRequest.serialize(generator, mapper);
        assertThat(categoriesRequest.toString()).isEqualTo(expected);
    }

    @Test
    public void testSerializeInternal() {
        JsonGenerator generator = generator();
        generator.writeStartObject();
        categoriesRequest.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        assertThat(categoriesRequest.toString()).isEqualTo(expected);
    }

    @Override
    public Endpoint<CategoriesRequest, ?, ?> endpoint() {
        return CategoriesRequest._ENDPOINT;
    }

    @Test
    public void testEndpoint() {

        Endpoint<CategoriesRequest, ?, ?> categoriesEndpoint = endpoint();

        assertThat("v3/categories").isEqualTo(categoriesEndpoint.requestUrl(categoriesRequest));
        assertThat("GET").isEqualTo(categoriesEndpoint.method(categoriesRequest));
        assertThat(categoriesEndpoint.isError(200)).isFalse();

    }

    @Test
    public void testBuilder() {
        CategoriesRequest.Builder builder = new CategoriesRequest.Builder().locale(LOCALE);

        CategoriesRequest.Builder self = builder.self();

        assertThat(self).isEqualTo(builder);

        CategoriesRequest searchBusinessReq = builder.build();

        assertThat(searchBusinessReq.toString()).isEqualTo("{\"locale\":\"en_US\"}");
    }
    @Test
    public void testDeserializer() {
    }

    @Test
    public void testDeserialize() {
    }

    public JsonParser parser() {
        return parser(categoriesRequest);
    }
}
