package io.github.stewseo.clients.yelpfusion.categories.alias;

import io.github.stewseo.clients.transport.Endpoint;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionRequestTestCase;
import jakarta.json.stream.JsonGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CategoriesAliasRequestTest extends YelpFusionRequestTestCase<CategoriesAliasRequest> {


    private final CategoriesAliasRequest categoriesAliasRequest = CategoriesAliasRequest.of(b -> b
            .alias(alias)
    );

    @Override
    public Endpoint<CategoriesAliasRequest, ?, ?> endpoint() {
        return CategoriesAliasRequest._ENDPOINT;
    }

    @Test
    public void testOf() {
        assertThat(categoriesAliasRequest.alias()).isEqualTo(alias);
    }

    private final String expected = "{\"alias\":\"alias\"}";

    private final JsonGenerator generator = generator();

    @Test
    public void testSerialize() {
        categoriesAliasRequest.serialize(generator, mapper);
        assertThat(categoriesAliasRequest.toString()).isEqualTo(expected);
    }

    @Test
    public void testSerializeInternal() {
        generator.writeStartObject();
        categoriesAliasRequest.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        assertThat(categoriesAliasRequest.toString()).isEqualTo(expected);
    }


    @Test
    public void testEndpoint() {

        assertThat(endpoint().id()).isEqualTo("v3/categories");

        assertThat(endpoint().requestUrl(categoriesAliasRequest)).isEqualTo("v3/categories/" + alias);

        assertThat(endpoint().hasRequestBody()).isFalse();

        assertThat(endpoint().queryParameters(categoriesAliasRequest).values().size()).isEqualTo(0);

        assertThat(endpoint().isError(200)).isFalse();
        assertThat(endpoint().headers(categoriesAliasRequest).toString()).isEqualTo("{}");
        assertThat(endpoint().method(categoriesAliasRequest)).isEqualTo("GET");

    }
}