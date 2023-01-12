package io.github.stewseo.clients.yelpfusion.categories.all;

import io.github.stewseo.clients.yelpfusion.categories.all.CategoriesResponse;
import io.github.stewseo.clients.yelpfusion.testcases.ModelTestCase;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.github.stewseo.clients.yelpfusion._types.test_constants.TestData.CATEGORY;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CategoriesResponseTest extends ModelTestCase<CategoriesResponse> {

    private final CategoriesResponse categoriesResponse = of();

    // {"categories":[{"alias":"categoryAliasValue"},{"alias":"category"}],"terms":[{"text":"termTextValue"},{"text":"termTextValue"}],"businesses":[{"id":"businessIdValue"}]}
    private final String expected = "{\"categories\":[{\"alias\":\"catAlias\"},{\"alias\":\"catAlias\"},{\"alias\":\"categoryAliasValue\"}]}";

    private final JsonGenerator generator = generator();

    @Override
    public CategoriesResponse of() {

        return CategoriesResponse.of(a -> a
                .categories(List.of(CATEGORY))
                .categories(CATEGORY)
                .categories(c -> c.alias("categoryAliasValue"))
        );
    }

    @Test
    public void testOf() {
        assertThat(categoriesResponse.categories()).isNotNull();
//        assertThat(categoriesResponse.toString()).isEqualTo(expected);
    }

    @Test
    public void testSerialize() {
        categoriesResponse.serialize(generator, mapper);
        assertThat(categoriesResponse.toString()).isEqualTo(expected);
    }

    @Test
    public void testSerializeInternal() {
        generator.writeStartObject();
        categoriesResponse.serializeInternal(generator, mapper);
        generator.writeEnd().close();
        AssertionsForClassTypes.assertThat(categoriesResponse.toString()).isEqualTo(expected);
    }

    @Test
    public void testDeserialize() {

        JsonParser parser = parser();

        CategoriesResponse deserializedCategoriesResponse =
                CategoriesResponse._DESERIALIZER.deserialize(parser, mapper);

        assertThat(deserializedCategoriesResponse.toString()).isEqualTo(expected);
    }

    @Test
    public void testDeserializer() {

        assertThat(CategoriesResponse._DESERIALIZER.toString()).contains("clients.json.LazyDeserializer@");

    }

    @Test
    public void testBuilder() {

        CategoriesResponse.Builder builder = new CategoriesResponse.Builder().categories(CATEGORY);

        CategoriesResponse.Builder self = builder.self();

        assertThat(self).isEqualTo(builder);

        CategoriesResponse searchBusinessReq = builder.build();

        assertThat(searchBusinessReq.toString()).isEqualTo("{\"categories\":[{\"alias\":\"catAlias\"}]}");
    }

    public JsonParser parser() {
        return parser(categoriesResponse);
    }
}
