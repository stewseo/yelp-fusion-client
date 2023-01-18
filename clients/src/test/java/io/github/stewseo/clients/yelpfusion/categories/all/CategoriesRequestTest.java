package io.github.stewseo.clients.yelpfusion.categories.all;

import io.github.stewseo.clients.transport.Endpoint;
import io.github.stewseo.clients.yelpfusion.YelpFusionTest;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionTestCase;
import io.github.stewseo.clients.yelpfusion.testcases.RequestTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.junit.jupiter.api.Tag;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.TestReqVars.LOCALE;
import static org.assertj.core.api.Assertions.assertThat;


@Tag("categories")
public class CategoriesRequestTest
        extends YelpFusionTestCase<CategoriesRequest>
        implements RequestTestCase<CategoriesRequest> {

    private final String text = "text";

    private final CategoriesRequest categoriesRequest = of();

    public CategoriesRequest of() {
        return CategoriesRequest.of(a -> a
                .locale(LOCALE)
        );
    }

    @YelpFusionTest
    public void testOf() {
        assertThat(categoriesRequest.locale()).isEqualTo(LOCALE);
    }

    private final String expected = "{\"locale\":\"en_US\"}";

    @YelpFusionTest
    public void testSerialize() {
        JsonGenerator generator = generator();
        categoriesRequest.serialize(generator, mapper);
        assertThat(categoriesRequest.toString()).isEqualTo(expected);
    }

    @YelpFusionTest
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

    @YelpFusionTest
    public void testEndpoint() {

        Endpoint<CategoriesRequest, ?, ?> categoriesEndpoint = endpoint();

        assertThat("v3/categories").isEqualTo(categoriesEndpoint.requestUrl(categoriesRequest));
        assertThat("GET").isEqualTo(categoriesEndpoint.method(categoriesRequest));
        assertThat(categoriesEndpoint.isError(200)).isFalse();

    }

    @YelpFusionTest
    public void testBuilder() {
        CategoriesRequest.Builder builder = new CategoriesRequest.Builder().locale(LOCALE);

        CategoriesRequest.Builder self = builder.self();

        assertThat(self).isEqualTo(builder);

        CategoriesRequest searchBusinessReq = builder.build();

        assertThat(searchBusinessReq.toString()).isEqualTo("{\"locale\":\"en_US\"}");
    }
    @YelpFusionTest
    public void testDeserializer() {
    }

    @YelpFusionTest
    public void testDeserialize() {
    }

    public JsonParser parser() {
        return parser(categoriesRequest);
    }
}
