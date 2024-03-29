package io.github.stewseo.clients.yelpfusion.categories.alias;

import io.github.stewseo.clients.transport.Endpoint;
import io.github.stewseo.clients.yelpfusion.YelpFusionTest;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionTestCase;
import io.github.stewseo.clients.yelpfusion.testcases.RequestTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.junit.jupiter.api.Tag;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.ALIAS;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Tag("categories")
class CategoriesAliasRequestTest
        extends YelpFusionTestCase<CategoriesAliasRequest>
        implements RequestTestCase<CategoriesAliasRequest> {

    private final CategoriesAliasRequest categoriesAliasRequest = of();

    @Override
    public Endpoint<CategoriesAliasRequest, ?, ?> endpoint() {
        return CategoriesAliasRequest._ENDPOINT;
    }

    @Override
    public CategoriesAliasRequest of() {
        return CategoriesAliasRequest.of(b -> b
                .alias(ALIAS)
        );
    }

    @YelpFusionTest
    public void testOf() {
        assertThat(categoriesAliasRequest.alias()).isEqualTo(ALIAS);
    }

    private final String expected = "{\"alias\":\"alias\"}";

    private final JsonGenerator generator = generator();

    @YelpFusionTest
    public void testSerialize() {
        categoriesAliasRequest.serialize(generator, mapper);
        assertThat(categoriesAliasRequest.toString()).isEqualTo(expected);
    }

    @YelpFusionTest
    public void testSerializeInternal() {
        generator.writeStartObject();
        categoriesAliasRequest.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        assertThat(categoriesAliasRequest.toString()).isEqualTo(expected);
    }


    @YelpFusionTest
    public void testEndpoint() {

        assertThat(endpoint().id()).isEqualTo("v3/categories");

        assertThat(endpoint().requestUrl(categoriesAliasRequest)).isEqualTo("v3/categories/" + ALIAS);

        assertThat(endpoint().hasRequestBody()).isFalse();

        assertThat(endpoint().queryParameters(categoriesAliasRequest).values().size()).isEqualTo(0);

        assertThat(endpoint().isError(200)).isFalse();
        assertThat(endpoint().headers(categoriesAliasRequest).toString()).isEqualTo("{}");
        assertThat(endpoint().method(categoriesAliasRequest)).isEqualTo("GET");

    }

    @YelpFusionTest
    public void testBuilder() {
        CategoriesAliasRequest.Builder builder = new CategoriesAliasRequest.Builder().alias("aliasValue");

        CategoriesAliasRequest.Builder self = builder.self();

        assertThat(self).isEqualTo(builder);

        CategoriesAliasRequest categoriesAliasReq = builder.build();

        assertThat(categoriesAliasReq.toString()).isEqualTo("{\"alias\":\"aliasValue\"}");
    }

    @YelpFusionTest
    public void testDeserialize() {

//        JsonParser parser = parser();
//
//        CategoriesAliasResponse deserializedCategoriesAliasResponse =
//                CategoriesAliasRequest._DESERIALIZER.deserialize(parser, mapper);
//
//        assertThat(deserializedCategoriesAliasResponse.toString()).isEqualTo(expected);
    }

    @YelpFusionTest
    public void testDeserializer() {

//        assertThat(CategoriesAliasRequest._DESERIALIZER.toString()).contains("clients.json.LazyDeserializer@");

    }

    public JsonParser parser() {
        return parser(categoriesAliasRequest);
    }

}