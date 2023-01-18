package io.github.stewseo.clients.yelpfusion.categories.all;

import io.github.stewseo.clients.yelpfusion.YelpFusionTest;
import io.github.stewseo.clients.yelpfusion.testcases.YelpFusionTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Tag;

import java.util.List;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.ALIAS;
import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestVars.CATEGORY;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Tag("categories")
public class CategoriesResponseTest extends YelpFusionTestCase<CategoriesResponse> {

    private final CategoriesResponse categoriesResponse = of();

    // {"categories":[{"alias":"categoryAliasValue"},{"alias":"category"}],"terms":[{"text":"termTextValue"},{"text":"termTextValue"}],"businesses":[{"id":"businessIdValue"}]}
    private final String expected =
            "{\"categories\":[{\"alias\":\"alias\"},{\"alias\":\"alias\"},{\"alias\":\"alias\"}]}";

    private final JsonGenerator generator = generator();

    @Override
    public CategoriesResponse of() {

        return CategoriesResponse.of(a -> a
                .categories(List.of(CATEGORY))
                .categories(CATEGORY)
                .categories(c -> c.alias(ALIAS))
        );
    }

    @YelpFusionTest
    public void testOf() {
        assertThat(categoriesResponse.categories()).isNotNull();
//        assertThat(categoriesResponse.toString()).isEqualTo(expected);
    }

    @YelpFusionTest
    public void testSerialize() {
        categoriesResponse.serialize(generator, mapper);
        assertThat(categoriesResponse.toString()).isEqualTo(expected);
    }

    @YelpFusionTest
    public void testSerializeInternal() {
        generator.writeStartObject();
        categoriesResponse.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        AssertionsForClassTypes.assertThat(categoriesResponse.toString()).isEqualTo(expected);
    }

    @YelpFusionTest
    public void testDeserialize() {

        JsonParser parser = parser();

        CategoriesResponse deserializedCategoriesResponse =
                CategoriesResponse._DESERIALIZER.deserialize(parser, mapper);

        assertThat(deserializedCategoriesResponse.toString()).isEqualTo(expected);
    }

    @YelpFusionTest
    public void testDeserializer() {

        assertThat(CategoriesResponse._DESERIALIZER.toString()).contains("clients.json.LazyDeserializer@");

    }

    @YelpFusionTest
    public void testBuilder() {

        CategoriesResponse.Builder builder = new CategoriesResponse.Builder().categories(CATEGORY);

        CategoriesResponse.Builder self = builder.self();

        assertThat(self).isEqualTo(builder);

        CategoriesResponse searchBusinessReq = builder.build();

        assertThat(searchBusinessReq.toString()).isEqualTo("{\"categories\":[{\"alias\":\"alias\"}]}");
    }

    public JsonParser parser() {
        return parser(categoriesResponse);
    }
}
